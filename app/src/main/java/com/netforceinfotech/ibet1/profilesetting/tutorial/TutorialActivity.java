package com.netforceinfotech.ibet1.profilesetting.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.netforceinfotech.ibet1.R;


public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

    }
    public void startDefaultIntro(View v) {
        Intent intent = new Intent(this, DefaultIntro.class);
        startActivity(intent);
    }

}



