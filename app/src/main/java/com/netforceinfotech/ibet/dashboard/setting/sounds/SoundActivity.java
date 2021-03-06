package com.netforceinfotech.ibet.dashboard.setting.sounds;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

public class SoundActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SoundAdapter adapter;
    ArrayList<SoundData> soundDatas = new ArrayList<>();
    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    Toolbar toolbar;
    UserSessionManager userSessionManager;
    int theme;
    Window window;
    LinearLayout sound_layout;
    CheckBox checkbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);


        userSessionManager = new UserSessionManager(getApplicationContext());
        theme = userSessionManager.getTheme();
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        if (theme == 0) {

          /*  if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        setupToolBar("Sounds");
        setupRecyclerView();
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    userSessionManager.setSoundOnOff(false);
                    userSessionManager.setBackgroundCrowd(false);
                    userSessionManager.setWinBet(false);
                    userSessionManager.setLoseBet(false);
                    soundDatas.get(0).status = false;
             //       soundDatas.get(1).status = false;
                    soundDatas.get(1).status = false;
                    soundDatas.get(2).status = false;
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        sound_layout = (LinearLayout) findViewById(R.id.sound_activity_layout);


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

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);


        if (theme == 0) {
          /*  toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme1));
            sound_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme1));
*/
        } else if (theme == 1) {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme2));
            sound_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme2));

        } else if (theme == 2) {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme3));
            sound_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme3));


        } else if (theme == 3) {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme4));

            sound_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme4));


        } else if (theme == 4) {


            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme5));

            sound_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme5));


        }


        recyclerView.setLayoutManager(layoutManager);
        soundDatas.add(new SoundData("Sound on/off", userSessionManager.getSoundOnOff()));
        //soundDatas.add(new SoundData("Background Crowded", userSessionManager.getBackgroundCrowd()));
        soundDatas.add(new SoundData("Win Bet", userSessionManager.getWinBet()));
        soundDatas.add(new SoundData("Lose Bet", userSessionManager.getLoseBet()));
//        soundDatas.add(new SoundData("Bonus", false));

        adapter = new SoundAdapter(getApplicationContext(), soundDatas);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }


}
