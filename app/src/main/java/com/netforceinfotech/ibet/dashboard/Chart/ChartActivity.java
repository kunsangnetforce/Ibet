package com.netforceinfotech.ibet.dashboard.Chart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Chart.RichestRank.RichestTabActivity;
import com.netforceinfotech.ibet.dashboard.Chart.highest.HighestTabActivity;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_richest,button_highest;
   Context context;

    Toolbar toolbar;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        context = this;


        setupToolBar("Charts");
        button_highest= (Button) findViewById(R.id.buttonHighest);
        button_richest = (Button) findViewById(R.id.buttonRichest);


        button_highest.setOnClickListener(this);
        button_richest.setOnClickListener(this);


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
    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.buttonHighest:

                intent = new Intent(context, HighestTabActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.buttonRichest:
                intent = new Intent(context, RichestTabActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

                break;

        }

    }

}
