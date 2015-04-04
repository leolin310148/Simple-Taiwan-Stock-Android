package me.leolin.stock.ws;

import me.leolin.stock.data.dto.UserDto;
import me.leolin.stock.data.holder.DefaultResultHolder;
import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * @author leolin
 */
public interface UserWs {
    @POST("/user")
    Observable<DefaultResultHolder> refreshUser(@Body UserDto userDto);

}
