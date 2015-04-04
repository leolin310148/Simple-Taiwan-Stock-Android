package me.leolin.stock.ui;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import com.facebook.appevents.AppEventsLogger;
import me.leolin.stock.R;
import me.leolin.stock.core.AppConfig;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectFragment;
import roboguice.inject.InjectView;
import rx.android.app.AppObservable;
import rx.android.content.ContentObservable;

public class MainActivity extends RoboActionBarActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @InjectView(R.id.toolbar_actionbar)
    private Toolbar mToolbar;
    @InjectFragment(R.id.fragment_drawer)
    private DrawerMenuFragment drawerMenuFragment;
    @InjectView(R.id.rootContainer)
    private DrawerLayout rootContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(mToolbar);

        drawerMenuFragment.setup(rootContainer, mToolbar);

        AppObservable.bindActivity(this, ContentObservable.fromLocalBroadcast(this, new IntentFilter(AppConfig.ACTION_SELECT_MENU)))
                .map(intent -> intent.getIntExtra(AppConfig.EXTRA_SELECT_MENU, -1))
                .subscribe(this::onSideMenuSelected);
    }

    private void onSideMenuSelected(int viewId) {
        switch (viewId) {
            case R.id.textViewMenuIndustry:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, IndustryFragment.newInstance())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

}
