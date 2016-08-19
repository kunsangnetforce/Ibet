package com.netforceinfotech.ibet.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.currentbet.CurrentBet;
import com.netforceinfotech.ibet.dashboard.home.Home;
import com.netforceinfotech.ibet.live_event.LiveEventsFragment;
import com.netforceinfotech.ibet.solobet.SoloBet;

public class PagerAdapterDashboard extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterDashboard(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Home home = new Home();
                return home;
            case 1:
                CurrentBet currentBet = new CurrentBet();
                return currentBet;
            case 2:
                LiveEventsFragment liveEvents = new LiveEventsFragment();
                return liveEvents;
            case 3:
                SoloBet soloBet = new SoloBet();
                return soloBet;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}