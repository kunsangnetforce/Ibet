package com.netforceinfotech.ibet.currentbet.betarena;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.currentbet.betarena.betdetail.BetDetailFragment;
import com.netforceinfotech.ibet.currentbet.betarena.live_event.LiveEventFragment;
import com.netforceinfotech.ibet.currentbet.betarena.thearena.TheArenaFragment;
import com.netforceinfotech.ibet.currentbet.betarena.stats.StateFragment;

/**
 * Created by Netforce on 8/1/2016.
 */
public class PagerAdapterBetArena extends FragmentStatePagerAdapter {
    private final Bundle bundle;
    int mNumOfTabs;
    String bet_id, match_id, home_id, away_id, home_name, away_name, home_logo, away_logo;

    public PagerAdapterBetArena(FragmentManager fm, int NumOfTabs, String bet_id, String match_id, String home_id, String away_id, String home_name, String away_name, String home_logo, String away_logo) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.away_id = away_id;
        this.home_id = home_id;
        this.home_name = home_name;
        this.away_name = away_name;
        this.home_logo = home_logo;
        this.away_logo = away_logo;
        this.bet_id = bet_id;
        this.match_id = match_id;
        bundle = new Bundle();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                LiveEventFragment home = new LiveEventFragment();
                bundle.putString("bet_id", bet_id);
                bundle.putString("match_id", bet_id);
                bundle.putString("away_id", bet_id);
                bundle.putString("away_id", bet_id);
                bundle.putString("home_name", bet_id);
                bundle.putString("away_name", bet_id);
                bundle.putString("home_logo", bet_id);
                bundle.putString("away_logo", bet_id);
                home.setArguments(bundle);
                return home;
            case 1:
                StateFragment currentBet = new StateFragment();
                bundle.putString("bet_id", bet_id);
                bundle.putString("match_id", bet_id);
                bundle.putString("away_id", bet_id);
                bundle.putString("away_id", bet_id);
                bundle.putString("home_name", bet_id);
                bundle.putString("away_name", bet_id);
                bundle.putString("home_logo", bet_id);
                bundle.putString("away_logo", bet_id);
                currentBet.setArguments(bundle);
                return currentBet;
            case 2:
                BetDetailFragment betDetailFragment = new BetDetailFragment();
                bundle.putString("bet_id", bet_id);
                bundle.putString("match_id", bet_id);
                bundle.putString("away_id", bet_id);
                bundle.putString("away_id", bet_id);
                bundle.putString("home_name", bet_id);
                bundle.putString("away_name", bet_id);
                bundle.putString("home_logo", bet_id);
                bundle.putString("away_logo", bet_id);
                betDetailFragment.setArguments(bundle);
                return betDetailFragment;
            case 3:
                TheArenaFragment theArenaFragment = new TheArenaFragment();
                bundle.putString("bet_id", bet_id);
                bundle.putString("match_id", bet_id);
                bundle.putString("away_id", bet_id);
                bundle.putString("away_id", bet_id);
                bundle.putString("home_name", bet_id);
                bundle.putString("away_name", bet_id);
                bundle.putString("home_logo", bet_id);
                bundle.putString("away_logo", bet_id);
                theArenaFragment.setArguments(bundle);
                return theArenaFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}