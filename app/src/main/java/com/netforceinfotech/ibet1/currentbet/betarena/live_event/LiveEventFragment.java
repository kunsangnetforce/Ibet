package com.netforceinfotech.ibet1.currentbet.betarena.live_event;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.CustomViewPager;
import com.netforceinfotech.ibet1.general.UserSessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveEventFragment extends Fragment {


    private TabLayout tabLayout;
    private int theme = 0;
    String home_id, away_id, match_id, bet_id, home_name, away_name, home_logo, away_logo;
    UserSessionManager userSessionManager;
    Context context;

    public LiveEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_event, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        try {
            // home_id = this.getArguments().getString("home_id");
            home_id = this.getArguments().getString("away_id");
            away_id = this.getArguments().getString("away_id");
            home_name = this.getArguments().getString("home_name");
            away_name = this.getArguments().getString("away_name");
            home_logo = this.getArguments().getString("home_logo");
            away_logo = this.getArguments().getString("away_logo");
            bet_id = this.getArguments().getString("bet_id");
            match_id = this.getArguments().getString("match_id");

        } catch (Exception ex) {

        }
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        setupTab(view);
        setupTheme();
        return view;
    }

    private void setupTab(View view) {


        if (theme == 0) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme1));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme1));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme1));


        } else if (theme == 1) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme2));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.red));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.red));


        } else if (theme == 2) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme3));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme3));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme3));


        } else if (theme == 3) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme4));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme4));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme4));


        } else if (theme == 4) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme5));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme5));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme5));


        }


        tabLayout.addTab(tabLayout.newTab().setText("Events"));
        tabLayout.addTab(tabLayout.newTab().setText("Line up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final CustomViewPager viewPager = (CustomViewPager) view.findViewById(R.id.pager);
        final PagerAdapterLiveEvent adapter = new PagerAdapterLiveEvent
                (getChildFragmentManager(), tabLayout.getTabCount(), bet_id, match_id, home_id, away_id, home_name, away_name, home_logo, away_logo);
        viewPager.setPagingEnabled(false);
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

    private void setupTheme() {
        int theme = userSessionManager.getTheme();
        switch (theme) {
            case 0:
              //  setupDefaultTheme();
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
