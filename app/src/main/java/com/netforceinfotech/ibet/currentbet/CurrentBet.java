package com.netforceinfotech.ibet.currentbet;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.PagerAdapterNewBet;
import com.netforceinfotech.ibet.general.CustomViewPager;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentBet extends Fragment implements View.OnClickListener {


    private TabLayout tabLayout;
    private UserSessionManager userSessionManager;
    private int theme;
    LinearLayout currrentbetlayout, linearLayoutLogin;
    String loginmode;
    Context context;

    public CurrentBet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_bet, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        theme = userSessionManager.getTheme();
        loginmode = userSessionManager.getLoginMode();
        initView(view);
        setupTab(view);
        return view;
    }

    private void initView(View view) {
        currrentbetlayout = (LinearLayout) view.findViewById(R.id.currentbet_layout);
        linearLayoutLogin = (LinearLayout) view.findViewById(R.id.linearLayoutLogin);
        view.findViewById(R.id.buttonLogin).setOnClickListener(CurrentBet.this);
        if (loginmode.equalsIgnoreCase("0")) {
            currrentbetlayout.setVisibility(View.GONE);
            linearLayoutLogin.setVisibility(View.VISIBLE);
        } else {
            currrentbetlayout.setVisibility(View.VISIBLE);
            linearLayoutLogin.setVisibility(View.GONE);
        }
    }

    private void setupTab(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);


        if (theme == 0) {

            currrentbetlayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme1));
            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme1));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme1));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme1));


        } else if (theme == 1) {

            currrentbetlayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme2));
            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme2));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.red));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.red));


        } else if (theme == 2) {

            currrentbetlayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme3));
            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme3));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme3));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme3));


        } else if (theme == 3) {

            currrentbetlayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme4));
            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme4));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme4));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme4));


        } else if (theme == 4) {

            currrentbetlayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme5));
            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme5));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme5));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme5));


        }


        tabLayout.addTab(tabLayout.newTab().setText("Live Bets"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming Bets"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final CustomViewPager viewPager = (CustomViewPager) view.findViewById(R.id.pager);
        final PagerAdapterCurrentBet adapter = new PagerAdapterCurrentBet
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


     /*   tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });*/
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
