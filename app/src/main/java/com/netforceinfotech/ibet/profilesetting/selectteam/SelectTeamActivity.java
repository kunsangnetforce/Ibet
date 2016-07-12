package com.netforceinfotech.ibet.profilesetting.selectteam;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.upcominggame.UpcomingGameAdapter;

import java.util.ArrayList;

public class SelectTeamActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    ArrayList<SelectTeamData> selectTeamDatas = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);
        findViewById(R.id.buttonDone).setOnClickListener(this);
        context = this;
        setupData();
        setupToolBar("Select Team");
        setupRecycler();
    }

    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);

    }

    private void setupData() {
        try {
            selectTeamDatas.clear();
        } catch (Exception ex) {

        }
        for (int i = 0; i < 20; i++) {
            selectTeamDatas.add(new SelectTeamData("", ""));
        }
    }

    private void setupRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        SelectTeamAdapter upcomingGameAdapter = new SelectTeamAdapter(context, selectTeamDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(upcomingGameAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDone:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
        }
    }
}
