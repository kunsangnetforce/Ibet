package com.netforceinfotech.ibet.currentbet.betarena.live_event;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.currentbet.betarena.live_event.events.EventsFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.lineup.LineUpFragment;

/**
 * Created by Netforce on 8/1/2016.
 */
public class PagerAdapterLiveEvent extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterLiveEvent(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                EventsFragment home = new EventsFragment();
                return home;
            case 1:
                LineUpFragment currentBet = new LineUpFragment();
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