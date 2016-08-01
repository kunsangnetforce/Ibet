package com.netforceinfotech.ibet.dashboard.Setting.notification.TeamNotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Setting.notification.TeamNotification.Teamlist.TeamlistActivity;

import java.util.ArrayList;

public class TeamNotificationActivity extends AppCompatActivity
{


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TeamNotificationAdapter adapter;
    ArrayList<String> teamDatas = new ArrayList<String>();
    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    private Toolbar toolbar;
    Button add_more_notification;



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
        add_more_notification = (Button) findViewById(R.id.button_add_notifiaction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);



        add_more_notification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent =  new Intent(TeamNotificationActivity.this, TeamlistActivity.class);
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(intent);


            }
        });

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




    }



}
