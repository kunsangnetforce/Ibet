package com.netforceinfotech.ibet1.dashboard.setting.notification.generalNotification.sound;

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
import android.widget.RelativeLayout;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;


public class SoundFragment extends Fragment {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SoundAdapter adapter;
    ArrayList<String> notificationDatas = new ArrayList<String>();
    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    ArrayList<SoundData> soundData = new ArrayList<>();
    private Toolbar toolbar;
    LinearLayout general_notification_layout;
    Context context;
    RelativeLayout relativeLayout;

    private UserSessionManager userSessionManager;
    int theme;

    public SoundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(getActivity());
        relativeLayout = (RelativeLayout) view.findViewById(R.id.header);
        relativeLayout.setVisibility(View.GONE);
        theme = userSessionManager.getTheme();

        setupRecyclerView(view);
        return view;
    }


    private void setupRecyclerView(View view) {

        general_notification_layout = (LinearLayout) view.findViewById(R.id.notification_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        if (theme == 0) {
            //general_notification_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme1));

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

        soundData.add(new SoundData("Match Reminder", userSessionManager.getGeneralNotificationFileName("Match Reminder" + "filename"), userSessionManager.getGeneralNotificationSoundName("Match Reminder" + "soundname")));
        soundData.add(new SoundData("Goal", userSessionManager.getGeneralNotificationFileName("Goal" + "filename"), userSessionManager.getGeneralNotificationSoundName("Goal" + "soundname")));
        soundData.add(new SoundData("Red Card", userSessionManager.getGeneralNotificationFileName("Red Card" + "filename"), userSessionManager.getGeneralNotificationSoundName("Red Card" + "soundname")));
        soundData.add(new SoundData("Yellow Card", userSessionManager.getGeneralNotificationFileName("Yellow Card" + "filename"), userSessionManager.getGeneralNotificationSoundName("Yellow Card" + "soundname")));
        soundData.add(new SoundData("Match Start", userSessionManager.getGeneralNotificationFileName("Match Start" + "filename"), userSessionManager.getGeneralNotificationSoundName("Match Start" + "soundname")));
        soundData.add(new SoundData("Half Time", userSessionManager.getGeneralNotificationFileName("Half Time" + "filename"), userSessionManager.getGeneralNotificationSoundName("Half Time" + "soundname")));
        soundData.add(new SoundData("Final", userSessionManager.getGeneralNotificationFileName("Final" + "filename"), userSessionManager.getGeneralNotificationSoundName("Final" + "soundname")));


        adapter = new SoundAdapter(getActivity(), soundData, icon_list);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            adapter.notifyDataSetChanged();
        } catch (Exception ex) {

        }
    }
}