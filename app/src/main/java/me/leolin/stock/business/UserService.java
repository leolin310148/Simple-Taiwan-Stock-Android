package me.leolin.stock.business;

import android.util.Log;
import com.eccyan.optional.Optional;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.google.inject.Singleton;
import me.leolin.stock.core.StockApplication;
import me.leolin.stock.data.dto.UserDto;
import me.leolin.stock.data.holder.DefaultResultHolder;
import me.leolin.stock.ws.UserWs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author leolin
 */
@Singleton
public class UserService {

    private static final String LOG_TAG = UserService.class.getSimpleName();

    private UserWs userWs = StockApplication.getRestAdapter().create(UserWs.class);

    public Observable<DefaultResultHolder> refreshUser() {

        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token == null) {
            return Observable.just(new DefaultResultHolder());
        }

        PublishSubject<UserDto> getMeSubject = PublishSubject.create();
        PublishSubject<List<String>> getFriendsSubject = PublishSubject.create();
        PublishSubject<DefaultResultHolder> refreshUserSubject = PublishSubject.create();

        GraphRequest.newMeRequest(token, (jsonObject, graphResponse) ->
                        Optional.ofNullable(jsonObject)
                                .map(this::userJsonResponseToDto)
                                .ifPresent(getMeSubject::onNext)
        ).executeAsync();
        GraphRequest.newMyFriendsRequest(token, (jsonArray, response) ->
                        Optional.ofNullable(jsonArray)
                                .map(this::friendJsonResponseToList)
                                .ifPresent(getFriendsSubject::onNext)
        ).executeAsync();

        Observable.zip(getMeSubject, getFriendsSubject, (userDto, friendIds) -> {
            userDto.setFriends(friendIds);
            return userDto;
        }).flatMap(userWs::refreshUser)
                .timeout(30, TimeUnit.SECONDS, Observable.just(new DefaultResultHolder("timeout")))
                .onErrorReturn(t -> new DefaultResultHolder(t.getMessage()))
                .subscribeOn(Schedulers.io())
                .subscribe(refreshUserSubject::onNext);

        return refreshUserSubject;
    }

    private UserDto userJsonResponseToDto(JSONObject jsonObject) {
        UserDto userDto = new UserDto();
        try {
            userDto.setId(jsonObject.getString("id"));
            userDto.setEmail(jsonObject.getString("email"));
            userDto.setName(jsonObject.getString("name"));
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
        return userDto;
    }

    private List<String> friendJsonResponseToList(JSONArray jsonArray) {
        return Observable.range(0, jsonArray.length())
                .map(i -> {
                    try {
                        return jsonArray.getJSONObject(i).getString("id");
                    } catch (JSONException e) {
                    }
                    return null;
                }).filter(s -> s != null)
                .toList().toBlocking().single();
    }
}
