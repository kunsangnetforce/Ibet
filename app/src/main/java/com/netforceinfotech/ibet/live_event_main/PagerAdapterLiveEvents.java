package com.netforceinfotech.ibet.live_event_main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.events.EventsFragment;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.StandFragment;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stats.StateFragment;

public class PagerAdapterLiveEvents extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String match_id, home_id, away_id, home_name, away_name, home_logo, away_logo;
    private Bundle bundle;

    public PagerAdapterLiveEvents(FragmentManager fm, int NumOfTabs, String match_id, String home_id, String away_id, String home_name, String away_name, String home_logo, String away_logo) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.match_id = match_id;
        this.home_id = home_id;
        this.away_id = away_id;
        this.home_name = home_name;
        this.away_name = away_name;
        this.home_logo = home_logo;
        this.away_logo = away_logo;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                EventsFragment home = new EventsFragment();
                bundle = new Bundle();
                Log.i("kunsangpager", match_id + " " + home_id + " " + away_id);
                bundle.putString("match_id", match_id);
                bundle.putString("home_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                home.setArguments(bundle);
                return home;
            case 1:
                StateFragment currentBet = new StateFragment();
                bundle = new Bundle();
                Log.i("kunsangpager", match_id + " " + home_id + " " + away_id);
                bundle.putString("match_id", match_id);
                bundle.putString("home_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                currentBet.setArguments(bundle);
                return currentBet;
            case 2:
                StandFragment liveEvents = new StandFragment();
                bundle = new Bundle();
                Log.i("kunsangpager", match_id + " " + home_id + " " + away_id);
                bundle.putString("match_id", match_id);
                bundle.putString("home_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                liveEvents.setArguments(bundle);
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