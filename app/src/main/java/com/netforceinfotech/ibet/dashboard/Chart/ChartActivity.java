package com.netforceinfotech.ibet.dashboard.Chart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;

public class ChartActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_richest,button_highest;
   Context context;


    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        button_highest= (Button) findViewById(R.id.buttonHighest);
        button_richest = (Button) findViewById(R.id.buttonRichest);


        button_highest.setOnClickListener(this);
        button_richest.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.buttonHighest:

                intent = new Intent(context, ChartTabActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.buttonRichest:
                intent = new Intent(context, ChartTabActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);

                break;

        }

    }

}
