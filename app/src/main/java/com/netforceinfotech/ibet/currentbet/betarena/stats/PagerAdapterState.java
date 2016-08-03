package com.netforceinfotech.ibet.currentbet.betarena.stats;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.currentbet.betarena.stats.lineup.LineUpFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.summary.SummaryFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.table.TableFragment;

public class PagerAdapterState extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterState(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SummaryFragment home = new SummaryFragment();
                return home;
            case 1:
                LineUpFragment currentBet = new LineUpFragment();
                return currentBet;
            case 2:

                TableFragment liveEvents = new TableFragment();
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