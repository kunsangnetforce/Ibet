package com.netforceinfotech.ibet.dashboard.RichestRank;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by John on 7/25/2016.
 */
public class PagerRichestAdapter extends FragmentStatePagerAdapter
{

    int mNumOfTabs;

    public PagerRichestAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    public Fragment getItem(int position)
    {

        switch (position)
        {

            case 0:
                RichestFriendsFragment richFragment = new RichestFriendsFragment();
                return richFragment;

            case 1:
                RichestAllFragment richFragment2 = new RichestAllFragment();
                return richFragment2;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
