package com.netforceinfotech.ibet.currentbet.betarena.thearena;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.WrapContentViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheArenaFragment extends Fragment {


    private WrapContentViewPager viewPager;
    Context context;

    public TheArenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_the_arena, container, false);
        context = getActivity();
        setupTab(view);
        return view;
    }

    private void setupTab(View view) {
        viewPager = (WrapContentViewPager) view.findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.all));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.top));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.friends));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final PagerAdapterBetTheArena adapter = new PagerAdapterBetTheArena(getChildFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
                viewPager.reMeasureCurrentPage(viewPager.getCurrentItem());

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
