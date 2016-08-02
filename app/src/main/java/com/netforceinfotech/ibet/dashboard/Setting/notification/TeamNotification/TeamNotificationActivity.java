package com.netforceinfotech.ibet.dashboard.Setting.notification.TeamNotification;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Setting.notification.TeamNotification.Teamlist.TeamlistActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;

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
    private UserSessionManager userSessionManager;
    RelativeLayout team_layout;
    int theme;
    Window window;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_notification);

        userSessionManager = new UserSessionManager(getApplicationContext());
        theme = userSessionManager.getTheme();

        window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if(theme == 0)
        {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme1));
            }

        }
        else if (theme == 1)
        {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme2));
            }


        }
        else if (theme == 2)
        {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme3));
            }

        }
        else if (theme == 3)
        {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme4));
            }
        }
        else if (theme == 4)
        {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme5));
            }
        }


        setupToolBar("Team Notification");
        setupRecyclerView();
    }

    private void setupToolBar(String title)
    {


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        team_layout = (RelativeLayout) findViewById(R.id.teamnotification_layout);

        add_more_notification = (Button) findViewById(R.id.button_add_notifiaction);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);


        if(theme == 0)
        {

            team_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme1));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme1));

        }
        else if (theme == 1)
        {

            team_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme2));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme2));


        }
        else if (theme == 2)
        {

            team_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme3));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme3));

        }
        else if (theme == 3)
        {

            team_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme4));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme4));

        }
        else if (theme == 4)
        {

            team_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme5));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme5));

        }

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
