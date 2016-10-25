package com.netforceinfotech.ibet1.solobet;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.CustomViewPager;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.netforceinfotech.ibet1.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SoloBet extends Fragment implements View.OnClickListener {
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    private TabLayout tabLayout;
    private UserSessionManager userSessionManager;
    private int theme;
    String loinmode;
    private Context context;

    public SoloBet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solo_bet, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        loinmode = userSessionManager.getLoginMode();
        theme = userSessionManager.getTheme();
        initView(view);
        setupTab(view);
        return view;

    }

    private void initView(View view) {
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        view.findViewById(R.id.buttonLogin).setOnClickListener(SoloBet.this);
        if (loinmode.equalsIgnoreCase("0")) {
            linearLayout.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setupTab(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);


        tabLayout.addTab(tabLayout.newTab().setText("Current Game"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming Game"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final CustomViewPager viewPager = (CustomViewPager) view.findViewById(R.id.pager);
        final PagerAdapterSoloBet adapter = new PagerAdapterSoloBet
                (getChildFragmentManager(), tabLayout.getTabCount());
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
