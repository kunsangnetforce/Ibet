package com.netforceinfotech.ibet.dashboard.Setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SettingAdapter adapter;
    ArrayList<String> settingDatas = new ArrayList<String>();

    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setupToolBar("Setting");
        setupRecyclerView();
    }

    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);

    }


    private void setupRecyclerView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
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

        adapter = new SettingAdapter(getApplicationContext(), settingDatas, icon_list);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();



        recyclerView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {

                int p = recyclerView.getChildPosition(view);
                Toast.makeText(getApplicationContext(),"Hi=======",Toast.LENGTH_LONG).show();

            }
        });

    }


}
