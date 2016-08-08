package com.netforceinfotech.ibet.dashboard.Setting.Theme;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Setting.Theme.ThemeColor.ThemeColorActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.io.File;


public class ThemeActivity extends AppCompatActivity
{

    private Toolbar toolbar;
    Button choose_theme,choose_backgropund;
    UserSessionManager userSessionManager;
    int theme;
    Window window;
    RelativeLayout  theam_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);


        deleteCache(getApplicationContext());
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


        setupToolBar("Themes");

    }

    private void setupToolBar(String title)
    {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        theam_layout = (RelativeLayout) findViewById(R.id.theme_layout);

        choose_theme = (Button) findViewById(R.id.buttonTheme);
        choose_backgropund = (Button) findViewById(R.id.buttonBackground);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);


        if(theme == 0)
        {
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme1));
            theam_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme1));

        }
        else if (theme == 1)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme2));
            theam_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme2));

        }
        else if (theme == 2)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme3));
            theam_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme3));

        }
        else if (theme == 3)
        {

            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme4));

            theam_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme4));


        }
        else if (theme == 4)
        {


            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme5));

            theam_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme5));



        }

        choose_theme.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent choosetheme = new Intent(ThemeActivity.this, ThemeColorActivity.class);
                startActivity(choosetheme);

            }


        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}