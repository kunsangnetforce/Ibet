package com.netforceinfotech.ibet.dashboard.chart.highest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by asdf on 7/20/2016.
 */
public class PagerAdapterCharttab  extends FragmentStatePagerAdapter
{

    int mNumOfTabs;

    public PagerAdapterCharttab(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    public Fragment getItem(int position)
    {

        switch (position)
        {
            case 0:
                HighestFragment highestFragment = new HighestFragment();
                return highestFragment;
            case 1:
                HighestFragment richestFragment = new HighestFragment();
                return richestFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
