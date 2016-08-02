package com.netforceinfotech.ibet.dashboard.Setting.notification.TeamNotification.Teamlist;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

public class TeamlistActivity extends AppCompatActivity
{

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TeamlistAdapter adapter;
    ArrayList<String> teamData = new ArrayList<String>();
    private Toolbar toolbar;
    private UserSessionManager userSessionManager;
    LinearLayout team_layout;
    int theme;
    Window window;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamlist);

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


        setupToolBar("Team List");
        setupRecyclerView();

    }

    private void setupToolBar(String title)
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        team_layout = (LinearLayout) findViewById(R.id.team_activity_layout);

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


    }

    private void setupRecyclerView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        teamData.add("Team 1");
        teamData.add("Team 2");
        teamData.add("Team 3");

        adapter = new TeamlistAdapter(getApplicationContext(), teamData);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }
}