package com.netforceinfotech.ibet1.currentbet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet1.currentbet.livebet.LiveBetFragment;
import com.netforceinfotech.ibet1.currentbet.upcoming.UpcomingBetFragment;

/**
 * Created by Netforce on 8/1/2016.
 */
public class PagerAdapterCurrentBet  extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterCurrentBet(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                LiveBetFragment home = new LiveBetFragment();
                return home;
            case 1:
                UpcomingBetFragment currentBet = new UpcomingBetFragment();
                return currentBet;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}