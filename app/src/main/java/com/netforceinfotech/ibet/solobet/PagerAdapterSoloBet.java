package com.netforceinfotech.ibet.solobet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.netforceinfotech.ibet.solobet.currentgame.CurrentGameFragment;
import com.netforceinfotech.ibet.solobet.upcominggame.UpComingGamesFragment;

/**
 * Created by John on 8/8/2016.
 */
public class PagerAdapterSoloBet extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapterSoloBet(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CurrentGameFragment home = new CurrentGameFragment();
                return home;
            case 1:
                UpComingGamesFragment currentBet = new UpComingGamesFragment();
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