package com.netforceinfotech.ibet.live_event_main;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.CustomViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveEvents extends Fragment {


    private CoordinatorLayout coordinatorLayout;
    private CustomViewPager viewPager;
    private int theme;

    public LiveEvents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_events, container, false);
        setupTab(view);
        return view;
    }

    private void setupTab(View view) {


        viewPager = (CustomViewPager) view.findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.events));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.stats));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.stand));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final PagerAdapterLiveEvents adapter = new PagerAdapterLiveEvents(getChildFragmentManager(), tabLayout.getTabCount(), "", "", "", "", "","","","");

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
