package com.netforceinfotech.ibet.dashboard.language;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.TeamNotification.TeamNotificationAdapter;

import java.util.ArrayList;


public class LanguageActivity extends AppCompatActivity
{

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    languageAdapter adapter;
    ArrayList<String> languageDatas = new ArrayList<String>();

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        setupToolBar("Language");
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
        languageDatas.add("English");
        languageDatas.add("Hindi");
        languageDatas.add("Spanish");

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);



        adapter = new languageAdapter(getApplicationContext(), languageDatas);

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
