package me.leolin.twse.subscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.*;
import com.astuetz.PagerSlidingTabStrip;
import me.leolin.speedmyandroid.speedview.SpeedView;
import me.leolin.twse.R;
import me.leolin.twse.subscription.business.SubscriptionFacade;
import me.leolin.twse.subscription.vo.SubscriptionSetVo;
import roboguice.fragment.RoboFragment;
import rx.android.app.AppObservable;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Leolin
 */
public class SubscriptionFragment extends RoboFragment {

    @Inject
    private SubscriptionFacade subscriptionFacade;

    private SpeedView speedView;
    private List<SubscriptionSetVo> subscriptionSetVos;
    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subscription, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        speedView = SpeedView.with(view);
        viewPager = speedView.just(R.id.viewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        render();
    }

    private void render() {
        AppObservable.bindFragment(this, subscriptionFacade.getAllSubscriptionSets())
                .subscribe(results -> {
                    subscriptionSetVos = results;
                    PagerSlidingTabStrip slidingTabStrip = speedView.just(R.id.pagerSlidingTabStrip);
                    if (pagerAdapter == null) {
                        pagerAdapter = new PagerAdapter();
                        viewPager.setAdapter(pagerAdapter);
                    } else {
                        pagerAdapter.notifyDataSetChanged();
                    }
                    slidingTabStrip.setViewPager(viewPager);
                });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.subscription_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                startActivity(new Intent(getActivity(), EditSubscriptionSetActivity.class));
                break;
            case R.id.menu_edit:
                SubscriptionSetVo vo = subscriptionSetVos.get(viewPager.getCurrentItem());
                startActivity(new Intent(getActivity(), EditSubscriptionSetActivity.class).putExtra("SubscriptionSetId", vo.getId()));
                break;
        }
        return false;
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter() {
            super(getActivity().getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return new SubscriptionListFragment();
        }

        @Override
        public int getCount() {
            return subscriptionSetVos.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return subscriptionSetVos.get(position).getName();
        }
    }
}
