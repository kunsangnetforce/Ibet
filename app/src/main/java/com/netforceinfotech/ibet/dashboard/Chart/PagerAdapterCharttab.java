package com.netforceinfotech.ibet.dashboard.Chart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.dashboard.home.startnewbet.currentgame.CurrentGameFragment;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.upcominggame.UpComingGamesFragment;

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
                Highest_Fragment highestFragment = new Highest_Fragment();
                return highestFragment;
            case 1:
                Highest_Fragment richestFragment = new Highest_Fragment();
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
