package com.netforceinfotech.ibet1.dashboard.setting;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.Dashboard;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;

public class SettingFragment extends Fragment {

    CoordinatorLayout coordinatorLayout;
    View view1;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SettingAdapter adapter;
    ArrayList<String> settingDatas = new ArrayList<String>();
    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    Context context;


    private UserSessionManager userSessionManager;
    int theme;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        userSessionManager = new UserSessionManager(getActivity());
        View view = inflater.inflate(R.layout.activity_setting, container, false);
        view1=view.findViewById(R.id.view);
        coordinatorLayout= (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        Dashboard.title.setText("Setting");
        setupTheme();
        setupBackground();
        setupRecyclerView(view);

        return view;
    }


    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        icon_list.add(R.drawable.language_icon);
        icon_list.add(R.drawable.team_notification);
        icon_list.add(R.drawable.general_notificationicon);
        icon_list.add(R.drawable.sound_notification);
        icon_list.add(R.drawable.theme_notification);
        icon_list.add(R.drawable.odds_notification);
        icon_list.add(R.drawable.info_notification);
        icon_list.add(R.drawable.removeads_icon);
        icon_list.add(R.drawable.feedback_icon);

        settingDatas.add("Languages");
        settingDatas.add("Team Notification");
        settingDatas.add("General Notification");
        settingDatas.add("Sounds");
        settingDatas.add("Themes");
        settingDatas.add("Odds");
        settingDatas.add("Info");
        settingDatas.add("Remove Ads");
        settingDatas.add("Feedback");

        adapter = new SettingAdapter(getActivity(), settingDatas, icon_list);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        recyclerView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int p = recyclerView.getChildPosition(view);
                Toast.makeText(getActivity(), "Hi=======", Toast.LENGTH_LONG).show();

            }
        });

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
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setupPurlpleTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
    }

    private void setupGreenTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

    }

    private void setupMarronTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
    }

    private void setupLightBlueTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
    }

    private void setupDefaultTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

}
