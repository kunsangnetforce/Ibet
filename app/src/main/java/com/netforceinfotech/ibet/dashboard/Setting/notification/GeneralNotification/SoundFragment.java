package com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;


public class SoundFragment  extends Fragment
{


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SoundAdapter adapter;
    ArrayList<String> notificationDatas = new ArrayList<String>();
    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    ArrayList<String>  soundData = new ArrayList<String>();
    private Toolbar toolbar;
    LinearLayout general_notification_layout;
    Context  context;

    private UserSessionManager userSessionManager;
    int theme;

    public SoundFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(getActivity());

        theme = userSessionManager.getTheme();

        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view)
    {

        general_notification_layout = (LinearLayout) view.findViewById(R.id.notification_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        if(theme == 0)
        {
            general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme1));

        }
        else if (theme == 1)
        {

            general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme2));

        }
        else if (theme == 2)
        {
            general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme3));

        }
        else if (theme == 3)
        {
            general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme4));

        }
        else if (theme == 4)
        {
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

        notificationDatas.add("Match Reminder");
        notificationDatas.add("Goal");
        notificationDatas.add("Red Card");
        notificationDatas.add("Yellow card");
        notificationDatas.add("Match Start");
        notificationDatas.add("Half Time");
        notificationDatas.add("Final");

        soundData.add("Sound 3 >");
        soundData.add("Sound 4 >");
        soundData.add("Tennis 2 >");
        soundData.add("Sound 3 >");
        soundData.add("Whistle >");
        soundData.add("Whistle >");
        soundData.add("Whistle >");



        adapter = new SoundAdapter(getActivity(), notificationDatas, icon_list,soundData);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        recyclerView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

            }
        });

    }

}