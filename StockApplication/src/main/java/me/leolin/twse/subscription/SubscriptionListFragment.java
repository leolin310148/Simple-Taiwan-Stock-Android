package me.leolin.twse.subscription;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.leolin.speedmyandroid.speedview.SpeedView;
import me.leolin.twse.R;
import me.leolin.twse.subscription.dao.SubscriptionSetRepository;
import roboguice.fragment.RoboFragment;

/**
 * @author Leolin
 */
public class SubscriptionListFragment extends RoboFragment {

    private SubscriptionSetRepository subscriptionSetRepository;

    private SpeedView speedView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subscription_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        speedView = SpeedView.with(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
