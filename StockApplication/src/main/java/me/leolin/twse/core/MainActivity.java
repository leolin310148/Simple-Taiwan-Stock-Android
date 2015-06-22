package me.leolin.twse.core;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import me.leolin.speedmyandroid.speedview.SpeedView;
import me.leolin.twse.R;
import me.leolin.twse.subscription.SubscriptionFragment;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * @author Leolin
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActionBarActivity {
    @InjectView(R.id.drawerLayout)
    private DrawerLayout drawerLayout;
    @InjectView(R.id.toolbar)
    private Toolbar toolbar;
    private SpeedView speedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        speedView = SpeedView.with(this);

        setSupportActionBar(toolbar);

        NavigationView navigationView = speedView.just(R.id.navigationView);
        onNavMenuItemSelected(navigationView.getMenu().getItem(0));
        navigationView.setNavigationItemSelectedListener(this::onNavMenuItemSelected);
    }

    private boolean onNavMenuItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);

        getSupportActionBar().setTitle(menuItem.getTitle());

        drawerLayout.closeDrawers();

        switch (menuItem.getItemId()) {
            case R.id.nav_item_subscription:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new SubscriptionFragment())
                        .commit();
                break;
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }

}
