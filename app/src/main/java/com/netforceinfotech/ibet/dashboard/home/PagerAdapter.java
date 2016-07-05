package com.netforceinfotech.ibet.dashboard.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.dashboard.home.finsihed_bet.FinsihedBet;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FinsihedBet finsihedBet = new FinsihedBet();
                return finsihedBet;
            case 1:
                BetsToJoin betsToJoin = new BetsToJoin();
                return betsToJoin;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}