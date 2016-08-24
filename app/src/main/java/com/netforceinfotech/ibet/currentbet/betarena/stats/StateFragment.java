package com.netforceinfotech.ibet.currentbet.betarena.stats;


import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.CustomViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class StateFragment extends Fragment {
    Context context;
    private TabLayout tabLayout;
    private int theme;
    private String teamaid, teambid, teama, teamb, matchid;

    public StateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_state, container, false);
        context = getActivity();
        try {
            teamaid = this.getArguments().getString("teamaid");
            teambid = this.getArguments().getString("teambid");
            matchid = this.getArguments().getString("matchid");
            teama = this.getArguments().getString("teama");
            teamb = this.getArguments().getString("teamb");
        } catch (Exception ex) {
            Log.i("kunsang_exception", "paramenter not set");
        }
        setupTab(view);
        return view;
    }

    private void setupTab(View view) {


        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setIcon(ContextCompat.getDrawable(context, R.drawable.ic_graph)));
        tabLayout.addTab(tabLayout.newTab().setIcon(ContextCompat.getDrawable(context, R.drawable.ic_line_up)));
        tabLayout.addTab(tabLayout.newTab().setIcon(ContextCompat.getDrawable(context, R.drawable.ic_list)));
        final int tabIconColor = ContextCompat.getColor(context, R.color.unselected);
        final int tabIconSelectedColor = ContextCompat.getColor(context, R.color.white);

        tabLayout.getTabAt(0).getIcon().setColorFilter(tabIconSelectedColor, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final CustomViewPager viewPager = (CustomViewPager) view.findViewById(R.id.pager);
        final PagerAdapterState adapter = new PagerAdapterState
                (getChildFragmentManager(), tabLayout.getTabCount(), matchid);
        viewPager.setPagingEnabled(true);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(tabIconSelectedColor, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(context, R.color.unselected);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
