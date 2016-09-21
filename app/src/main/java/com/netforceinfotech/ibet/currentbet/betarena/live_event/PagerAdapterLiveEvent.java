package com.netforceinfotech.ibet.currentbet.betarena.live_event;

import android.os.Bundle;
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
    String bet_id, match_id, home_id, away_id, home_name, away_name, home_logo, away_logo;
    private Bundle bundle;

    public PagerAdapterLiveEvent(FragmentManager fm, int NumOfTabs, String bet_id, String match_id, String home_id, String away_id, String home_name, String away_name, String home_logo, String away_logo) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.bet_id = bet_id;
        this.match_id = match_id;
        this.home_id = home_id;
        this.away_id = away_id;
        this.home_logo = home_logo;
        this.away_logo = away_logo;
        this.home_name = home_name;
        this.away_name = away_name;
        bundle = new Bundle();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                EventsFragment home = new EventsFragment();
                bundle.putString("match_id", match_id);
                bundle.putString("away_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                bundle.putString("bet_id", bet_id);
                home.setArguments(bundle);
                return home;
            case 1:
                LineUpFragment currentBet = new LineUpFragment();
                bundle.putString("match_id", match_id);
                bundle.putString("away_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                bundle.putString("bet_id", bet_id);
                currentBet.setArguments(bundle);
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