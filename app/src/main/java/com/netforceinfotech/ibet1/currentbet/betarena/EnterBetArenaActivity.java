package com.netforceinfotech.ibet1.currentbet.betarena;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.CustomViewPager;
import com.netforceinfotech.ibet1.general.UserSessionManager;

public class EnterBetArenaActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private UserSessionManager userSessionManager;
    private int theme;
    Toolbar toolbar;
    String home_name, away_name, home_logo, away_logo, home_id, away_id, bet_id, match_id,season_id;
    public static TextView title;
    Context context;
    CoordinatorLayout coordinatorLayout;
    View view1;
    private String betid;

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
            away_id = bundle.getString("home_id");
            home_logo = bundle.getString("home_logo");
            away_logo = bundle.getString("away_logo");
            home_name = bundle.getString("home_name");
            away_name = bundle.getString("away_name");
            bet_id = bundle.getString("bet_id");
            match_id = bundle.getString("match_id");
            season_id=bundle.getString("season_id");
            Debugger.i("kbundle", home_name);
        } catch (Exception ex) {
            showMessage("Bundle error enter bet arena");
        }
        try {

        } catch (Exception ex) {

        }
        setupStatusBar();
        initView();
        setupToolBar("Ibet");
        setupTab();
        setupTheme();
        setupBackbround();
    }

    private void initView() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        view1 = findViewById(R.id.view);
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
        tabLayout.addTab(tabLayout.newTab().setText("Live Events"));
        tabLayout.addTab(tabLayout.newTab().setText("Stats"));
        tabLayout.addTab(tabLayout.newTab().setText("Bet Detail"));
        tabLayout.addTab(tabLayout.newTab().setText("The Arena"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final CustomViewPager viewPager = (CustomViewPager) findViewById(R.id.pager);
        //ragmentManager fm, int NumOfTabs, String bet_id, String match_id,
        // String home_id, String away_id, String home_name, String away_name, String home_logo, String away_logo, String season_id) {
        final PagerAdapterBetArena adapter = new PagerAdapterBetArena
                (getSupportFragmentManager(), tabLayout.getTabCount(), bet_id, match_id, home_id,
                        away_id, home_name, away_name, home_logo, away_logo,season_id);
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

    private void setupBackbround() {

        switch (userSessionManager.getBackground()) {
            case 0:
                coordinatorLayout.setBackgroundResource(R.drawable.blue240);
                break;
            case 1:
                coordinatorLayout.setBackgroundResource(R.drawable.france240);
                break;
            case 2:
                coordinatorLayout.setBackgroundResource(R.drawable.soccer240);
                break;
            case 3:
                coordinatorLayout.setBackgroundResource(R.drawable.spain240);
                break;
            case 4:
                coordinatorLayout.setBackgroundResource(R.drawable.uk240);
                break;
            case 5:
                view1.setVisibility(View.GONE);
                break;
        }

    }

    private void setupTheme() {
        int theme = userSessionManager.getTheme();
        switch (theme) {
            case 0:
                setupDefaultTheme();
                break;
            case 1:
                setupBrownTheme();
                break;
            case 2:
                setupPurlpleTheme();
                break;
            case 3:
                setupGreenTheme();
                break;
            case 4:
                setupMarronTheme();
                break;
            case 5:
                setupLightBlueTheme();
                break;
        }
    }

    private void setupBrownTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentBrown));

        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
    }

    private void setupPurlpleTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentPurple));

        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setupGreenTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentGreen));

        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setupMarronTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentMarron));

        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccentLightBlue));

        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
    }

    private void setupDefaultTheme() {
        tabLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccent));
        tabLayout.setTabTextColors(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.colorAccent));

        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    private void setupStatusBar() {
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        switch (userSessionManager.getTheme()) {
            case 0:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
                }
                break;
            case 3:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
                }
                break;
            case 4:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
                }
                break;
            case 5:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
                }
                break;
        }

    }
}
