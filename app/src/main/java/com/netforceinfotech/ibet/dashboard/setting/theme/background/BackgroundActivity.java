package com.netforceinfotech.ibet.dashboard.setting.theme.background;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;

public class BackgroundActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewBlue, imageViewFrance, imageViewUk, imageViewSpain, imageViewDefault, imageViewSoccer;
    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        userSessionManager = new UserSessionManager(this);
        imageViewBlue = (ImageView) findViewById(R.id.imageViewBlue);
        imageViewFrance = (ImageView) findViewById(R.id.imageViewFrance);
        imageViewUk = (ImageView) findViewById(R.id.imageViewUk);
        imageViewSpain = (ImageView) findViewById(R.id.imageViewSpain);
        imageViewDefault = (ImageView) findViewById(R.id.imageViewDefault);
        imageViewSoccer = (ImageView) findViewById(R.id.imageViewSoccer);
        imageViewSoccer.setOnClickListener(this);
        imageViewDefault.setOnClickListener(this);
        imageViewSpain.setOnClickListener(this);
        imageViewUk.setOnClickListener(this);
        imageViewFrance.setOnClickListener(this);
        imageViewBlue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewBlue:
                userSessionManager.setBackground(0);
                break;
            case R.id.imageViewFrance:
                userSessionManager.setBackground(1);
                break;
            case R.id.imageViewSoccer:
                userSessionManager.setBackground(2);
                break;
            case R.id.imageViewSpain:
                userSessionManager.setBackground(3);
                break;
            case R.id.imageViewUk:
                userSessionManager.setBackground(4);
                break;
            case R.id.imageViewDefault:
                userSessionManager.setBackground(5);
                break;

        }
        Intent intent = new Intent(this, Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
