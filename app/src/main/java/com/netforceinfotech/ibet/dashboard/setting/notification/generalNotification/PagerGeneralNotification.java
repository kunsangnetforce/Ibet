package com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification.notification.NotificationFragment;
import com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification.sound.SoundFragment;

/**
 * Created by John on 7/25/2016.
 */
public class PagerGeneralNotification extends FragmentStatePagerAdapter
{

    int mNumOfTabs;

    public PagerGeneralNotification(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    public Fragment getItem(int position)
    {

        switch (position)
        {

            case 0:
                NotificationFragment richFragment = new NotificationFragment();
                return richFragment;

            case 1:
                SoundFragment soundFragment = new SoundFragment();
                return soundFragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
