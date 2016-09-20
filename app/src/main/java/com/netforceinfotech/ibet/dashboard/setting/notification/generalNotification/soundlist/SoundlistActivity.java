package com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification.soundlist;

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
import android.widget.LinearLayout;
import android.widget.Toast;

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
    Window window;
    LinearLayout activity_sound_layout;
    private String event_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_soundlist);
        userSessionManager = new UserSessionManager(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        try {
            event_name = bundle.getString("name");
        } catch (Exception ex) {
            showMessage("bundle error");
        }
        window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        setupToolBar("Sound List");
        setupRecyclerView();

    }

    private void showMessage(String s) {
        Toast.makeText(SoundlistActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        activity_sound_layout = (LinearLayout) findViewById(R.id.activity_sound_layout);

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

        adapter = new SoundlistAdapter(getApplicationContext(), soundData, event_name);
        recyclerView.setAdapter(adapter);

        setupFinsihedDatas();
        adapter.notifyDataSetChanged();


    }

    private void setupFinsihedDatas() {
        try {
            soundData.clear();
        } catch (Exception ex) {

        }
        soundData.add(new SoundListData("A Tone", "A_tone.mp3"));
        soundData.add(new SoundListData("Air Horn", "Air_Horn.mp3"));
        soundData.add(new SoundListData("Cheering", "Cheering.mp3"));
        soundData.add(new SoundListData("Crowed Boo", "Crowd_Boo.mp3"));
        soundData.add(new SoundListData("Crowed Hole", "CrowedHole.wav"));
        soundData.add(new SoundListData("Doorbell", "Doorbell.mp3"));
        soundData.add(new SoundListData("Japanese Temple Bell Small", "Japanese_Temple_Bell_Small.mp3"));
        soundData.add(new SoundListData("Sad Trombone Joe Lamb", "Sad_Trombone-Joe_Lamb.mp3"));
        soundData.add(new SoundListData("Store Door Chime", "Store_Door_Chime.mp3"));
        soundData.add(new SoundListData("Ta Da", "Ta_Da.mp3"));


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
