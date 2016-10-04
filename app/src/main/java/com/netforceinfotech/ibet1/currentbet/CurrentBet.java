package com.netforceinfotech.ibet1.currentbet;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.CustomViewPager;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.netforceinfotech.ibet1.login.LoginActivity;

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
    View view1;
    CoordinatorLayout coordinatorLayout;

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
        setupTheme();
        setupBackground();
        return view;
    }

    private void setupBackground() {
        UserSessionManager userSessionManager = new UserSessionManager(getActivity());
        int background = userSessionManager.getBackground();
        switch (background) {
            case 0:
                coordinatorLayout.setBackgroundResource(R.drawable.blue240);
                break;
            case 1:
                coordinatorLayout.setBackgroundResource(R.drawable.france240);
                break;
            case 2:
                coordinatorLayout.setBackgroundResource(R.drawable.soccer240);
                break;
            case 3:
                coordinatorLayout.setBackgroundResource(R.drawable.spain240);
                break;
            case 4:
                coordinatorLayout.setBackgroundResource(R.drawable.uk240);
                break;
            case 5:
                view1.setVisibility(View.GONE);
                break;
        }
    }


    private void initView(View view) {
        view1 = view.findViewById(R.id.view);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
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
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setupPurlpleTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentPurple));
    }

    private void setupGreenTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentGreen));
    }

    private void setupMarronTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentMarron));

    }

    private void setupLightBlueTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentLightBlue));


    }

    private void setupDefaultTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccent));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccent));

    }
}
