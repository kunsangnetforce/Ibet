package com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

public class GeneralNotificationActivity extends AppCompatActivity
{

    Toolbar toolbar;
    private UserSessionManager userSessionManager;
    int theme;
    Window window;
    TabLayout tabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_general_notification);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        userSessionManager = new UserSessionManager(getApplicationContext());
        theme = userSessionManager.getTheme();

        window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        if(theme == 0)
        {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme1));
            }

        }
        else if (theme == 1)
        {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme2));
            }

        }
        else if (theme == 2)
        {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme3));
            }

        }
        else if (theme == 3)
        {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme4));
            }
        }
        else if (theme == 4)
        {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme5));
            }
        }

        setupTab();
        setupToolBar("General Notification");

    }

    private void setupToolBar(String app_name)
    {


        TextView textView = (TextView) toolbar.findViewById(R.id.textViewTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);
        textView.setText(app_name);



    }



    private void setupTab()
    {

        tabLayout.addTab(tabLayout.newTab().setText("Notification"));
        tabLayout.addTab(tabLayout.newTab().setText("Sounds"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        if(theme == 0)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme1));

            tabLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme1));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme1));
            tabLayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_text_color_theme1));


        }
        else if (theme == 1)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme2));

            tabLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme2));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme2));
            tabLayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_text_color_theme2));


        }
        else if (theme == 2)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme3));
            tabLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme3));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme3));
            tabLayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_text_color_theme3));


        }
        else if (theme == 3)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme4));

            tabLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme4));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme4));
            tabLayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_text_color_theme4));

        }
        else if (theme == 4)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme5));

            tabLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme5));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_highlitedcolor_theme5));
            tabLayout.setTabTextColors(ContextCompat.getColor(getApplicationContext(), R.color.white), ContextCompat.getColor(getApplicationContext(), R.color.tab_seclector_text_color_theme5));

        }


        final PagerGeneralNotification adapter = new  PagerGeneralNotification(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {



            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
        }
        return super.onOptionsItemSelected(item);



    }

}