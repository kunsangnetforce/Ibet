package com.netforceinfotech.ibet1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.netforceinfotech.ibet1.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 3000;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.splash);
        Glide.with(getApplicationContext()).load(R.drawable.splash).into(imageView);
        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateWithToken(newAccessToken);
            }
        };
        updateWithToken(AccessToken.getCurrentAccessToken());

    }

    private void updateWithToken(AccessToken currentAccessToken) {

        if (currentAccessToken != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);

                    finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);

                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }
}
