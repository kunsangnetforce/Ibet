package com.netforceinfotech.ibet.currentbet.betarena;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.currentbet.betarena.betdetail.BetDetailFragment;
import com.netforceinfotech.ibet.currentbet.betarena.live_event.LiveEventFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.StateFragment;
import com.netforceinfotech.ibet.currentbet.betarena.thearena.TheArenaFragmen;
import com.netforceinfotech.ibet.currentbet.livebet.LiveBetFragment;
import com.netforceinfotech.ibet.currentbet.upcoming.UpcomingBetFragment;

/**
 * Created by Netforce on 8/1/2016.
 */
public class PagerAdapterBetArena extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterBetArena(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                LiveEventFragment home = new LiveEventFragment();
                return home;
            case 1:
                StateFragment currentBet = new StateFragment();
                return currentBet;
            case 2:
                BetDetailFragment betDetailFragment = new BetDetailFragment();
                return betDetailFragment;
            case 3:
                TheArenaFragmen theArenaFragmen = new TheArenaFragmen();
                return theArenaFragmen;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}