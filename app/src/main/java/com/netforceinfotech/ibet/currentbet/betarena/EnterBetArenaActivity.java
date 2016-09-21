package com.netforceinfotech.ibet.currentbet.betarena;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.currentbet.PagerAdapterCurrentBet;
import com.netforceinfotech.ibet.currentbet.betarena.stats.PagerAdapterState;
import com.netforceinfotech.ibet.general.CustomViewPager;
import com.netforceinfotech.ibet.general.UserSessionManager;

public class EnterBetArenaActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private UserSessionManager userSessionManager;
    private int theme;
    Toolbar toolbar;
    String home_name, away_name, home_logo, away_logo, home_id, away_id, bet_id, match_id;
    public static TextView title;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_bet_arena);
        context = this;
        userSessionManager = new UserSessionManager(this);
        theme = userSessionManager.getTheme();
        Bundle bundle = getIntent().getExtras();
        try {
            home_id = bundle.getString("away_id");
            away_id = bundle.getString("away_id");
            home_logo = bundle.getString("home_logo");
            away_logo = bundle.getString("away_logo");
            home_name = bundle.getString("home_name");
            away_name = bundle.getString("away_name");
            bet_id=bundle.getString("bet_id");
            match_id=bundle.getString("match_id");
        } catch (Exception ex) {
            showMessage("Bundle error");
        }
        try {

        } catch (Exception ex) {

        }
        setupToolBar("Ibet");
        setupTab();
    }

    private void showMessage(String s) {
        Toast.makeText(EnterBetArenaActivity.this, s, Toast.LENGTH_SHORT).show();
    }


    private void setupToolBar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = s;
        getSupportActionBar().setTitle(teams);

    }

    private void setupTab() {

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        if (theme == 0) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme1));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme1));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme1));


        } else if (theme == 1) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme2));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.red));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.red));


        } else if (theme == 2) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme3));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme3));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme3));


        } else if (theme == 3) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme4));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme4));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme4));


        } else if (theme == 4) {

            tabLayout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme5));
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme5));
            tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.tab_seclector_text_color_theme5));


        }


        tabLayout.addTab(tabLayout.newTab().setText("Live Events"));
        tabLayout.addTab(tabLayout.newTab().setText("Stats"));
        tabLayout.addTab(tabLayout.newTab().setText("Bet Detail"));
        tabLayout.addTab(tabLayout.newTab().setText("The Arena"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.pager);
        final PagerAdapterBetArena adapter = new PagerAdapterBetArena
                (getSupportFragmentManager(), tabLayout.getTabCount(),bet_id,match_id,home_id,away_id,home_name,away_name,home_logo,away_id);
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


     /*   tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
