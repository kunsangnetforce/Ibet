package com.netforceinfotech.ibet.dashboard;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Setting.SettingAdapter;
import com.netforceinfotech.ibet.dashboard.Theme.SoundlistAdapter;

import java.util.ArrayList;

public class SoundlistActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SoundlistAdapter adapter;
    ArrayList<String> soundData = new ArrayList<String>();


    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundlist);

        setupToolBar("Settings");
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


        soundData.add("Sound 1");
        soundData.add("Sound 2");
        soundData.add("Sound 3");

        adapter = new SoundlistAdapter(getApplicationContext(), soundData);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }
}
