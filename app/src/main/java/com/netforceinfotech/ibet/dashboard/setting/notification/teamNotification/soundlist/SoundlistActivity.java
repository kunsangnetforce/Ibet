package com.netforceinfotech.ibet.dashboard.setting.notification.teamNotification.soundlist;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

public class SoundlistActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SoundlistAdapter adapter;
    ArrayList<SoundListData> soundData = new ArrayList<>();
    private Toolbar toolbar;
    UserSessionManager userSessionManager;
    int theme;
    Window window;
    String teamid, teamname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_soundlist);
        userSessionManager = new UserSessionManager(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        try {
            teamid = bundle.getString("teamid");
            teamname = bundle.getString("teamname");
        } catch (Exception ex) {

        }
        theme = userSessionManager.getTheme();
        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (theme == 0) {
/*
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme1));
            }*/

        } else if (theme == 1) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme2));
            }

        } else if (theme == 2) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme3));
            }

        } else if (theme == 3) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme4));
            }
        } else if (theme == 4) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme5));
            }
        }
        setupToolBar("Sound List");
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

    private void setupRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SoundlistAdapter(getApplicationContext(), soundData,teamname,teamid);
        recyclerView.setAdapter(adapter);

        setupFinsihedDatas();
        adapter.notifyDataSetChanged();


    }

    private void setupFinsihedDatas() {
        try {
            soundData.clear();
        } catch (Exception ex) {

        }
        soundData.add(new SoundListData("A Tone", "imageurl"));
        soundData.add(new SoundListData("Air Horn", "imageurl"));
        soundData.add(new SoundListData("cheering", "imageurl"));
        soundData.add(new SoundListData("Crowed Boo", "imageurl"));
        soundData.add(new SoundListData("Crowed Hole", "imageurl"));
        soundData.add(new SoundListData("Doorbell", "imageurl"));
        soundData.add(new SoundListData("Japanese Temple Bell Small", "imageurl"));
        soundData.add(new SoundListData("Sad Trombone Joe Lamb", "imageurl"));
        soundData.add(new SoundListData("Store Door Chime", "imageurl"));
        soundData.add(new SoundListData("Ta Da", "imageurl"));


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


}
