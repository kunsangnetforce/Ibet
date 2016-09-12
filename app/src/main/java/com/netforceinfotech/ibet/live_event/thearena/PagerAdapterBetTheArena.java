package com.netforceinfotech.ibet.live_event.thearena;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.live_event.thearena.all.AllFragment;
import com.netforceinfotech.ibet.live_event.thearena.friend.FriendFragment;
import com.netforceinfotech.ibet.live_event.thearena.top.TopFragment;

/**
 * Created by Netforce on 8/1/2016.
 */
public class PagerAdapterBetTheArena extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String team, teama, teamb, teamaid, teambid, matchid, logoa, logob;
    private Bundle bundle;

    public PagerAdapterBetTheArena(FragmentManager fm, int NumOfTabs, String team, String teama, String teamb, String teamaid, String teambid, String matchid, String logoa, String logob) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.team = team;
        this.logoa = logoa;
        this.logob = logob;
        this.teama = teama;
        this.teamb = teamb;
        this.teamaid = teamaid;
        this.teambid = teambid;
        this.matchid = matchid;
        bundle = new Bundle();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AllFragment home = new AllFragment();
                bundle.putString("matchid", matchid);
                bundle.putString("teamaid", teamaid);
                bundle.putString("teambid", teambid);
                bundle.putString("teama", teama);
                bundle.putString("teamb", teamb);
                bundle.putString("team", team);
                bundle.putString("logoa",logoa);
                bundle.putString("logob",logob);
                home.setArguments(bundle);
                return home;
            case 1:
                TopFragment currentBet = new TopFragment();
                bundle.putString("matchid", matchid);
                bundle.putString("teamaid", teamaid);
                bundle.putString("teambid", teambid);
                bundle.putString("teama", teama);
                bundle.putString("teamb", teamb);
                bundle.putString("team", team);
                bundle.putString("logoa",logoa);
                bundle.putString("logob",logob);
                currentBet.setArguments(bundle);

                return currentBet;
            case 2:
                FriendFragment betDetailFragment = new FriendFragment();
                bundle.putString("matchid", matchid);
                bundle.putString("teamaid", teamaid);
                bundle.putString("teambid", teambid);
                bundle.putString("teama", teama);
                bundle.putString("teamb", teamb);
                bundle.putString("team", team);
                bundle.putString("logoa",logoa);
                bundle.putString("logob",logob);
                betDetailFragment.setArguments(bundle);
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