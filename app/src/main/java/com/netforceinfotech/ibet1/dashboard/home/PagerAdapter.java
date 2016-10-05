package com.netforceinfotech.ibet1.dashboard.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet1.dashboard.home.bets_to_join.BetsToJoin;
import com.netforceinfotech.ibet1.dashboard.home.finsihed_bet.FinsihedBet;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private FinsihedBet finsihedBet;

    public PagerAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                finsihedBet = new FinsihedBet();
                return finsihedBet;
            case 1:
                BetsToJoin betsToJoin = new BetsToJoin();
                return betsToJoin;
            default:
                finsihedBet = new FinsihedBet();
                return finsihedBet;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}