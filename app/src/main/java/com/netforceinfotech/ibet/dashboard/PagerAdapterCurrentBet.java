package com.netforceinfotech.ibet.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.currentbet.CurrentBet;
import com.netforceinfotech.ibet.dashboard.home.Home;
import com.netforceinfotech.ibet.live_event.LiveEvents;
import com.netforceinfotech.ibet.solobet.SoloBet;

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
                Home home = new Home();
                return home;
            case 1:
                CurrentBet currentBet = new CurrentBet();
                return currentBet;
            case 2:
                LiveEvents liveEvents = new LiveEvents();
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