package com.netforceinfotech.ibet.dashboard.home.detail_bet_to_join;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

import java.util.ArrayList;

public class DetailBetToJoin extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Context context;
    private DetailBetAdapter adapter;
    ArrayList<DetailBetData> detailBetDatas = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_betstobejoin);
        context = this;
        findViewById(R.id.buttonJoin).setOnClickListener(this);
        findViewById(R.id.buttonCancel).setOnClickListener(this);
        setupRecyclerView();
        setupToolBar("Germany vs Italy");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DetailBetAdapter(context, detailBetDatas);
        recyclerView.setAdapter(adapter);
        setupFinsihedDatas();
        adapter.notifyDataSetChanged();
    }

    private void setupFinsihedDatas() {
        try {
            detailBetDatas.clear();
        } catch (Exception ex) {

        }
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
    }

    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) toolbar.findViewById(R.id.textViewTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);
        textView.setText(app_name);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonJoin:
                Intent intent = new Intent(context, WhoWillWinActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            case R.id.buttonCancel:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;

        }
    }
}
