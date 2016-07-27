package com.netforceinfotech.ibet.dashboard.Teamlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Theme.SoundlistAdapter;

import java.util.ArrayList;

public class TeamlistActivity extends AppCompatActivity
{

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TeamlistAdapter adapter;
    ArrayList<String> teamData = new ArrayList<String>();

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamlist);

        setupToolBar("Team List");
        setupRecyclerView();

    }

    private void setupToolBar(String title)
    {
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


        teamData.add("Team 1");
        teamData.add("Team 2");
        teamData.add("Team 3");

        adapter = new TeamlistAdapter(getApplicationContext(), teamData);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }
}