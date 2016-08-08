package com.netforceinfotech.ibet.dashboard.Setting.Theme.ThemeColor;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

public class ThemeColorActivity extends AppCompatActivity
{


    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    ThemeColorAdapter adapter;
    ArrayList<Integer> icon_list = new ArrayList<Integer>();
    Toolbar toolbar;
    UserSessionManager userSessionManager;
    int theme;
    Window window;
    RelativeLayout theamcolor_layout;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_color);

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

        setupToolBar("Choose Theme");
        setupRecyclerView();
    }


    private void setupToolBar(String title)
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);

    }

    private void setupRecyclerView()
    {

        theamcolor_layout = (RelativeLayout) findViewById(R.id.theme_back_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager =  new GridLayoutManager(ThemeColorActivity.this, 2);

        if(theme == 0)
        {
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme1));
            theamcolor_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme1));

        }
        else if (theme == 1)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme2));
            theamcolor_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme2));


        }
        else if (theme == 2)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme3));
            theamcolor_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme3));


        }
        else if (theme == 3)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme4));

            theamcolor_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme4));


        }
        else if (theme == 4)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme5));

            theamcolor_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme5));


        }


        recyclerView.setLayoutManager(layoutManager);


        icon_list.add(R.drawable.home_theme5);
        icon_list.add(R.drawable.home_theme4);
        icon_list.add(R.drawable.home_theme1);
        icon_list.add(R.drawable.home_theme2);
        icon_list.add(R.drawable.home_theme3);


        adapter = new ThemeColorAdapter(ThemeColorActivity.this, icon_list);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


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
