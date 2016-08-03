package com.netforceinfotech.ibet.dashboard.Setting.language;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;


public class LanguageActivity extends AppCompatActivity
{


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    languageAdapter adapter;
    ArrayList<String> languageDatas = new ArrayList<String>();
    private Toolbar toolbar;
    private UserSessionManager userSessionManager;
    LinearLayout language_layout;
    int theme;
    Window window;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

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


        setupToolBar("Language");
        setupRecyclerView();
    }

    private void setupToolBar(String title)
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        language_layout = (LinearLayout) findViewById(R.id.language_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);


        if(theme == 0)
        {

            language_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme1));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme1));

        }
        else if (theme == 1)
        {

            language_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme2));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme2));

        }
        else if (theme == 2)
        {

            language_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme3));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme3));

        }
        else if (theme == 3)
        {

            language_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme4));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme4));

        }
        else if (theme == 4)
        {

            language_layout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.navigation_background_theme5));
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.tab_background_theme5));

        }




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
    private void setupRecyclerView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        languageDatas.add("English");
        languageDatas.add("Hindi");
        languageDatas.add("Spanish");

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);



        adapter = new languageAdapter(getApplicationContext(), languageDatas);

        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        recyclerView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

    }



}
