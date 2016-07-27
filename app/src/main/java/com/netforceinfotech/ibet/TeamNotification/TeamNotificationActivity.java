package com.netforceinfotech.ibet.TeamNotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.ibet.GeneralNotification.NotificationAdapter;
import com.netforceinfotech.ibet.GeneralNotification.SoundAdapter;
import com.netforceinfotech.ibet.R;

import java.util.ArrayList;

public class TeamNotificationActivity extends AppCompatActivity
{


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TeamNotificationAdapter adapter;
    ArrayList<String> teamDatas = new ArrayList<String>();
    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    private Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_notification);

        setupToolBar("Team Notification");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupRecyclerView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        icon_list.add(R.drawable.match_reminder);
        icon_list.add(R.drawable.goal_icon);
        icon_list.add(R.drawable.red_cardicon);
        icon_list.add(R.drawable.yellow_card);

        teamDatas.add("Manchester United");
        teamDatas.add("Real Madrid");
        teamDatas.add("Fc Barcelona");
        teamDatas.add("Hapeol Beer Sheva");

        adapter = new TeamNotificationAdapter(getApplicationContext(), teamDatas, icon_list);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        recyclerView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

    }



}
