package com.netforceinfotech.ibet.dashboard.setting;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

public class SettingFragment extends Fragment
{



    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SettingAdapter adapter;
    ArrayList<String> settingDatas = new ArrayList<String>();
    ArrayList<Integer> icon_list = new ArrayList<Integer>();


    private UserSessionManager userSessionManager;
    LinearLayout setting_layout;
    int theme;

    public SettingFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();

        View view= inflater.inflate(R.layout.activity_setting, container, false);

        Dashboard.title.setText("Setting");
        setupRecyclerView(view);

        return view;
    }


    private void setupRecyclerView(View view)
    {
        setting_layout = (LinearLayout)  view.findViewById(R.id.setting_layout);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        if(theme == 0)
        {

            setting_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_seclector_highlitedcolor_theme1));

        }
        else if (theme == 1)
        {

            setting_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme2));

        }
        else if (theme == 2)
        {

            setting_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme3));

        }
        else if (theme == 3)
        {

            setting_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme4));

        }
        else if (theme == 4)
        {

            setting_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.navigation_background_theme5));

        }

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



        recyclerView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {

                int p = recyclerView.getChildPosition(view);
                Toast.makeText(getActivity(),"Hi=======",Toast.LENGTH_LONG).show();

            }
        });

    }




}
