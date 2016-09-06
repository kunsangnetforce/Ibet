package com.netforceinfotech.ibet.live_event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.netforceinfotech.ibet.currentbet.betarena.live_event.events.EventsFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.StateFragment;
import com.netforceinfotech.ibet.live_event.stand.StandFragment;

public class PagerAdapterLiveEvents extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String matchid, teamaid, teambid, teama, teamb;
    private Bundle bundle;

    public PagerAdapterLiveEvents(FragmentManager fm, int NumOfTabs, String matchid, String teamaid, String teambid, String teama, String teamb) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.matchid = matchid;
        this.teamaid = teamaid;
        this.teambid = teambid;
        this.teama = teama;
        this.teamb = teamb;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                EventsFragment home = new EventsFragment();
                bundle = new Bundle();
                Log.i("kunsangpager", matchid + " " + teamaid + " " + teambid);
                bundle.putString("matchid", matchid);
                bundle.putString("teamaid", teamaid);
                bundle.putString("teambid", teambid);
                bundle.putString("teama", teama);
                bundle.putString("teamb", teamb);
                home.setArguments(bundle);
                return home;
            case 1:
                StateFragment currentBet = new StateFragment();
                bundle = new Bundle();
                Log.i("kunsangpager", matchid + " " + teamaid + " " + teambid);
                bundle.putString("matchid", matchid);
                bundle.putString("teamaid", teamaid);
                bundle.putString("teambid", teambid);
                bundle.putString("teama", teama);
                bundle.putString("teamb", teamb);
                currentBet.setArguments(bundle);
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