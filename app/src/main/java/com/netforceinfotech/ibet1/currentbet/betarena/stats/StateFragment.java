package com.netforceinfotech.ibet1.currentbet.betarena.stats;


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

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.CustomViewPager;
import com.netforceinfotech.ibet1.general.UserSessionManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class StateFragment extends Fragment {
    Context context;
    private TabLayout tabLayout;
    private int theme;
    private String home_id, away_id, home_name, away_name, match_id;
    UserSessionManager userSessionManager;

    public StateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_state, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        try {
            home_id = this.getArguments().getString("away_id");
            away_id = this.getArguments().getString("away_id");
            match_id = this.getArguments().getString("match_id");
            home_name = this.getArguments().getString("home_name");
            away_name = this.getArguments().getString("away_name");
        } catch (Exception ex) {
            Log.i("kunsang_exception", "paramenter not set");
        }
        setupTab(view);
        setupTheme();
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
                (getChildFragmentManager(), tabLayout.getTabCount(), match_id, home_id, away_id);
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

    private void setupTheme() {
        int theme = userSessionManager.getTheme();
        switch (theme) {
            case 0:
                setupDefaultTheme();
                break;
            case 1:
                setupBrownTheme();
                break;
            case 2:
                setupPurlpleTheme();
                break;
            case 3:
                setupGreenTheme();
                break;
            case 4:
                setupMarronTheme();
                break;
            case 5:
                setupLightBlueTheme();
                break;
        }
    }

    private void setupBrownTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setupPurlpleTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentPurple));
    }

    private void setupGreenTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentGreen));
    }

    private void setupMarronTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentMarron));

    }

    private void setupLightBlueTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentLightBlue));


    }

    private void setupDefaultTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccent));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccent));

    }
}
