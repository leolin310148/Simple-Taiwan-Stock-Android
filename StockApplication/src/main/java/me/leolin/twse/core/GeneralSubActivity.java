package me.leolin.twse.core;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import me.leolin.speedmyandroid.speedview.SpeedView;
import me.leolin.twse.R;
import roboguice.activity.RoboActionBarActivity;

/**
 * @author Leolin
 */
public class GeneralSubActivity extends RoboActionBarActivity {
    protected SpeedView speedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        speedView = SpeedView.with(this);
        Toolbar toolbar = speedView.just(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
