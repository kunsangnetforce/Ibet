package com.netforceinfotech.ibet.live_event;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.currentbet.CurrentBet;
import com.netforceinfotech.ibet.currentbet.betarena.live_event.events.EventsFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.StateFragment;
import com.netforceinfotech.ibet.dashboard.home.Home;
import com.netforceinfotech.ibet.solobet.SoloBet;

public class PagerAdapterLiveEvents extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterLiveEvents(FragmentManager fm, int NumOfTabs) {
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
                StateFragment currentBet = new StateFragment();
                return currentBet;
            case 2:
                StandFragment liveEvents = new StandFragment();
                return liveEvents;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}