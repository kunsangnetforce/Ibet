package com.netforceinfotech.ibet.dashboard.ThemeColor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.TeamNotification.TeamNotificationAdapter;

import java.util.ArrayList;

public class ThemeColorActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    ThemeColorAdapter adapter;
    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_color);
        setupToolBar("Choose Theme");
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
        recyclerView.setHasFixedSize(true);

        layoutManager =  new GridLayoutManager(ThemeColorActivity.this, 2);
        recyclerView.setLayoutManager(layoutManager);


        icon_list.add(R.drawable.home_theme5);
        icon_list.add(R.drawable.home_theme4);
        icon_list.add(R.drawable.home_theme1);
        icon_list.add(R.drawable.home_theme2);
        icon_list.add(R.drawable.home_theme3);


        adapter = new ThemeColorAdapter(ThemeColorActivity.this, icon_list);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }



}
