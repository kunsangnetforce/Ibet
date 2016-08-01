package com.netforceinfotech.ibet.dashboard.Theme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.ThemeColor.ThemeColorActivity;


public class ThemeActivity extends AppCompatActivity
{


    private Toolbar toolbar;
    Button choose_theme,choose_backgropund;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        setupToolBar("Themes");

    }

    private void setupToolBar(String title)
    {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        choose_theme = (Button) findViewById(R.id.buttonTheme);
        choose_backgropund = (Button) findViewById(R.id.buttonBackground);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);


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