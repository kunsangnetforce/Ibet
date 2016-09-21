package com.netforceinfotech.ibet.currentbet.betarena.stats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.currentbet.betarena.stats.lineup.LineUpFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.summary.SummaryFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.table.TableFragment;

public class PagerAdapterState extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String match_id, home_id, away_id;
    private Bundle bundle;

    public PagerAdapterState(FragmentManager fm, int NumOfTabs, String match_id, String home_id, String away_id) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.match_id = match_id;
        this.home_id = home_id;
        this.away_id = away_id;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SummaryFragment home = new SummaryFragment();
                bundle = new Bundle();
                bundle.putString("match_id", match_id);
                home.setArguments(bundle);
                return home;
            case 1:
                LineUpFragment currentBet = new LineUpFragment();
                bundle = new Bundle();
                bundle.putString("match_id", match_id);
                currentBet.setArguments(bundle);
                return currentBet;
            case 2:

                TableFragment liveEvents = new TableFragment();
                bundle = new Bundle();
                bundle.putString("match_id", match_id);
                bundle.putString("away_id", home_id);
                bundle.putString("away_id", away_id);
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