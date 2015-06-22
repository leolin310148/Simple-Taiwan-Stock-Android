package me.leolin.twse.core;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import me.leolin.twse.R;
import me.leolin.twse.rest.GetAllStockResult;
import me.leolin.twse.rest.StockWs;
import roboguice.RoboGuice;
import roboguice.activity.RoboSplashActivity;

import javax.inject.Inject;

/**
 * @author Leolin
 */
public class SplashActivity extends RoboSplashActivity {

    @Inject
    private StockWs stockWs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        minDisplayMs = 300;
    }

    @Override
    protected void startNextActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void doStuffInBackground(Application app) {
        RoboGuice.getInjector(app).injectMembersWithoutViews(this);
        GetAllStockResult allStock = stockWs.getAllStock();
        System.out.println(allStock);
    }
}
