package com.netforceinfotech.ibet.currentbet.betarena.thearena;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.currentbet.betarena.betdetail.BetDetailFragment;
import com.netforceinfotech.ibet.currentbet.betarena.live_event.LiveEventFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.StateFragment;
import com.netforceinfotech.ibet.currentbet.betarena.thearena.all.AllFragment;
import com.netforceinfotech.ibet.currentbet.betarena.thearena.friend.FriendFragment;
import com.netforceinfotech.ibet.currentbet.betarena.thearena.top.TopFragment;

/**
 * Created by Netforce on 8/1/2016.
 */
public class PagerAdapterBetTheArena extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterBetTheArena(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AllFragment home = new AllFragment();
                return home;
            case 1:
                TopFragment currentBet = new TopFragment();
                return currentBet;
            case 2:
                FriendFragment betDetailFragment = new FriendFragment();
                return betDetailFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}