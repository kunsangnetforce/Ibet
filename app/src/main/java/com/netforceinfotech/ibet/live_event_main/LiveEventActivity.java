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
    public static String teamaname, teambname, teamaid, teambid, matchid, logoa, logob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_event);
        Bundle bundle = getIntent().getExtras();
        teamaname = bundle.getString("home_name");
        teambname = bundle.getString("away_name");
        teamaid = bundle.getString("home_id");
        teambid = bundle.getString("away_id");
        matchid = bundle.getString("match_id");
        logoa = bundle.getString("home_logo");
        logob = bundle.getString("away_logo");

        setupToolBar(teamaname + " vs " + teambname);
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
        Log.i("kunsangliveevent", matchid + " " + teamaid + " " + teambid);
        final PagerAdapterLiveEvents adapter = new PagerAdapterLiveEvents(getSupportFragmentManager(), tabLayout.getTabCount(), matchid, teamaid, teambid, teamaname, teambname,logoa,logob);

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
