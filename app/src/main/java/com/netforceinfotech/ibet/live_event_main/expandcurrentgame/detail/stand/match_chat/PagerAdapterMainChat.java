package com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.all.AllFragment;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.friend.FriendFragment;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.top.TopFragment;

/**
 * Created by Netforce on 8/1/2016.
 */
public class PagerAdapterMainChat extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String team, home_name, away_name, home_id, away_id, match_id, home_logo, away_logo;
    private Bundle bundle;

    public PagerAdapterMainChat(FragmentManager fm, int NumOfTabs, String team, String home_name, String away_name, String home_id, String away_id, String match_id, String home_logo, String away_logo) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.team = team;
        this.home_logo = home_logo;
        this.away_logo = away_logo;
        this.home_name = home_name;
        this.away_name = away_name;
        this.home_id = home_id;
        this.away_id = away_id;
        this.match_id = match_id;
        bundle = new Bundle();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AllFragment home = new AllFragment();
                bundle.putString("match_id", match_id);
                bundle.putString("home_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("team", team);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                home.setArguments(bundle);
                return home;
            case 1:
                TopFragment currentBet = new TopFragment();
                bundle.putString("match_id", match_id);
                bundle.putString("home_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("team", team);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                currentBet.setArguments(bundle);

                return currentBet;
            case 2:
                FriendFragment betDetailFragment = new FriendFragment();
                bundle.putString("match_id", match_id);
                bundle.putString("home_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("team", team);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
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