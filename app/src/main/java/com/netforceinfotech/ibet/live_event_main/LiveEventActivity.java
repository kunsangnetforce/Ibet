package com.netforceinfotech.ibet.live_event_main;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.CustomViewPager;

public class LiveEventActivity extends AppCompatActivity {

    private CustomViewPager viewPager;
    private Toolbar toolbar;
    public static String home_name, away_name, home_id, away_id, match_id, home_logo, away_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_event);
        Bundle bundle = getIntent().getExtras();
        home_name = bundle.getString("home_name");
        away_name = bundle.getString("away_name");
        home_id = bundle.getString("home_id");
        away_id = bundle.getString("away_id");
        match_id = bundle.getString("match_id");
        home_logo = bundle.getString("home_logo");
        away_logo = bundle.getString("away_logo");

        setupToolBar(home_name + " vs " + away_name);
        setupTab();
    }


    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);

    }

    private void setupTab() {


        viewPager = (CustomViewPager) findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.events));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.stats));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.stand));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Log.i("kunsangliveevent", match_id + " " + home_id + " " + away_id);
        final PagerAdapterLiveEvents adapter = new PagerAdapterLiveEvents(getSupportFragmentManager(), tabLayout.getTabCount(), match_id, home_id, away_id, home_name, away_name, home_logo, away_logo);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


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
