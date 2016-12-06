package com.netforceinfotech.ibet.dashboard.chart;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.chart.richestrank.RichestTabActivity;
import com.netforceinfotech.ibet.dashboard.chart.highest.HighestTabActivity;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;

public class ChartFragment extends Fragment implements View.OnClickListener {

    Button button_richest, button_highest;

    private UserSessionManager userSessionManager;
    Intent intent;
    private CoordinatorLayout coordinatorLayout;
    View view1;
    Context context;


    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        View view = inflater.inflate(R.layout.activity_chart, container, false);
        Dashboard.title.setText(getString(R.string.chart));
        setupLayout(view);
        setupTheme();
        setupBackground();
        return view;
    }

    private void setupLayout(View view) {
        view1 = view.findViewById(R.id.view);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        button_highest = (Button) view.findViewById(R.id.buttonHighest);
        button_richest = (Button) view.findViewById(R.id.buttonRichest);

        button_highest.setOnClickListener(this);
        button_richest.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.buttonHighest:

                intent = new Intent(getActivity(), HighestTabActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.buttonRichest:
                intent = new Intent(getActivity(), RichestTabActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

                break;

        }

    }

    private void setupTheme() {
        int theme = userSessionManager.getTheme();
        switch (theme) {
            case 0:
               // setupDefaultTheme();
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
        button_highest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        button_richest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setupPurlpleTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        button_highest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        button_richest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
    }

    private void setupGreenTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        button_highest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        button_richest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
    }

    private void setupMarronTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        button_highest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        button_richest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));

    }

    private void setupLightBlueTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        button_highest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        button_richest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));

    }

    private void setupDefaultTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        button_highest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        button_richest.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    private void setupBackground() {

        switch (userSessionManager.getBackground()) {
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
}
