package com.netforceinfotech.ibet.solobet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.solobet.single.SingleFragment;

/**
 * Created by John on 8/8/2016.
 */
public class PagerAdapterSoloBet  extends FragmentStatePagerAdapter
{

    int mNumOfTabs;

    public PagerAdapterSoloBet(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {

        switch (position)
        {
            case 0:

                SingleFragment home = new SingleFragment();
                return home;

            case 1:
                SingleFragment currentBet = new SingleFragment();
                return currentBet;

            case 2:
                SingleFragment currentBet2 = new SingleFragment();
                return currentBet2;

            default:
                return null;
        }
    }


    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}