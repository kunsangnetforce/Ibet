package com.netforceinfotech.ibet.currentbet.betarena.stats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.netforceinfotech.ibet.currentbet.betarena.stats.lineup.LineUpFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.summary.SummaryFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.table.TableFragment;

public class PagerAdapterState extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String matchid;
    private Bundle bundle;

    public PagerAdapterState(FragmentManager fm, int NumOfTabs, String matchid) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.matchid = matchid;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SummaryFragment home = new SummaryFragment();
                bundle = new Bundle();
                Log.i("kunsangpager", matchid);
                bundle.putString("matchid", matchid);
                home.setArguments(bundle);
                return home;
            case 1:
                LineUpFragment currentBet = new LineUpFragment();
                bundle = new Bundle();
                Log.i("kunsangpager", matchid);
                bundle.putString("matchid", matchid);
                currentBet.setArguments(bundle);
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