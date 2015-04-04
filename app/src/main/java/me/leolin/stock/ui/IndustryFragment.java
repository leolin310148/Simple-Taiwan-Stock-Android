package me.leolin.stock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.astuetz.PagerSlidingTabStrip;
import me.leolin.stock.R;
import roboguice.inject.InjectView;

/**
 * @author leolin
 */
public class IndustryFragment extends Fragment {

    @InjectView(R.id.tabs)
    private PagerSlidingTabStrip slidingTabStrip;

    @InjectView(R.id.viewPager)
    private ViewPager viewPager;

    public static IndustryFragment newInstance() {
        return newInstance(null);
    }

    public static IndustryFragment newInstance(Intent intent) {
        return new IndustryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_industry, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    class PagerAdapter extends FragmentPagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
