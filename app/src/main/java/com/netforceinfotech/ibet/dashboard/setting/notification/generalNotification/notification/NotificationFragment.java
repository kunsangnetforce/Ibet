package com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification.notification;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    NotificationAdapter adapter;
    ArrayList<NotificationData> notificationDatas = new ArrayList<>();
    CheckBox checkBox;
    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    RelativeLayout relativeLayout;
    Context context;
    LinearLayout general_notification_layout;

    private UserSessionManager userSessionManager;
    int theme;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        context = getActivity();
        relativeLayout = (RelativeLayout) view.findViewById(R.id.header);
        relativeLayout.setVisibility(View.VISIBLE);
        checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        setupRecyclerView(view);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    for (int i = 0; i < NotificationAdapter.arrayListBoolean.size(); i++) {
                        NotificationAdapter.arrayListBoolean.set(i, true);
                        userSessionManager.setGeneralNotification(notificationDatas.get(i).name + "general", true);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void setupRecyclerView(View view) {

        general_notification_layout = (LinearLayout) view.findViewById(R.id.notification_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


        if (theme == 0) {
            general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme1));

        } else if (theme == 1) {

            general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme2));

        } else if (theme == 2) {
            general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme3));

        } else if (theme == 3) {
            general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme4));

        } else if (theme == 4) {
            general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme5));
        }


        recyclerView.setLayoutManager(layoutManager);

        icon_list.add(R.drawable.match_reminder);
        icon_list.add(R.drawable.goal_icon);
        icon_list.add(R.drawable.red_cardicon);
        icon_list.add(R.drawable.yellow_card);
        icon_list.add(R.drawable.match_starticon);
        icon_list.add(R.drawable.half_timeicon);
        icon_list.add(R.drawable.final_icon);

        notificationDatas.add(new NotificationData("Match Reminder", userSessionManager.getGeneralNotification("Match Reminder" + "general")));
        notificationDatas.add(new NotificationData("Goal", userSessionManager.getGeneralNotification("Goal" + "general")));
        notificationDatas.add(new NotificationData("Red Card", userSessionManager.getGeneralNotification("Red Card" + "general")));
        notificationDatas.add(new NotificationData("Yellow Card", userSessionManager.getGeneralNotification("Yellow card" + "general")));
        notificationDatas.add(new NotificationData("Match Start", userSessionManager.getGeneralNotification("Match Start" + "general")));
        notificationDatas.add(new NotificationData("Half Time", userSessionManager.getGeneralNotification("Half Time" + "general")));
        notificationDatas.add(new NotificationData("Final", userSessionManager.getGeneralNotification("Final" + "general")));


        adapter = new NotificationAdapter(getActivity(), notificationDatas, icon_list);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
