package me.leolin.stock.core;

import android.app.Application;
import android.content.Context;
import com.facebook.FacebookSdk;
import retrofit.RestAdapter;
import roboguice.RoboGuice;

/**
 * @author leolin
 */
public class StockApplication extends Application {

    private static final RestAdapter REST_ADAPTER = new RestAdapter
            .Builder()
//            .setEndpoint("http://192.168.56.1:8080")
            .setEndpoint("http://10.168.1.49:8080")
            .build();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        RoboGuice.getOrCreateBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, RoboGuice.newDefaultRoboModule(this));
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(this);

    }

    public static RestAdapter getRestAdapter() {
        return REST_ADAPTER;
    }
}
