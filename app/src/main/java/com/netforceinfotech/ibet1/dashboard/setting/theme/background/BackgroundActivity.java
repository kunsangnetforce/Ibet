package com.netforceinfotech.ibet1.dashboard.setting.theme.background;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.Dashboard;
import com.netforceinfotech.ibet1.general.UserSessionManager;

public class BackgroundActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewBlue, imageViewFrance, imageViewUk, imageViewSpain, imageViewSoccer;
    TextView textViewDefault;
    UserSessionManager userSessionManager;
    private Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    View view1;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        context = this;
        userSessionManager = new UserSessionManager(context);
        initView();
        setupToolBar("Background");
        setupStatusBar();
        setupTheme();
        setupBackground();

    }

    private void initView() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        view1 = findViewById(R.id.view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageViewBlue = (ImageView) findViewById(R.id.imageViewBlue);
        imageViewFrance = (ImageView) findViewById(R.id.imageViewFrance);
        imageViewUk = (ImageView) findViewById(R.id.imageViewUk);
        imageViewSpain = (ImageView) findViewById(R.id.imageViewSpain);
        imageViewSoccer = (ImageView) findViewById(R.id.imageViewSoccer);
        textViewDefault= (TextView) findViewById(R.id.textViewDefault);
        imageViewSoccer.setOnClickListener(this);
        textViewDefault.setOnClickListener(this);
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
            case R.id.textViewDefault:
                userSessionManager.setBackground(5);
                break;

        }
        Intent intent = new Intent(this, Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    private void setupToolBar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);
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

    private void setupStatusBar() {
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        switch (userSessionManager.getTheme()) {
            case 0:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
                }
                break;
            case 3:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
                }
                break;
            case 4:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
                }
                break;
            case 5:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
                }
                break;
        }

    }

    private void setupBackground() {

        switch (userSessionManager.getBackground()) {
            case 0:
                coordinatorLayout.setBackgroundResource(R.drawable.blue240);
                break;
            case 1:
                coordinatorLayout.setBackgroundResource(R.drawable.france240);
                break;
            case 2:
                coordinatorLayout.setBackgroundResource(R.drawable.soccer240);
                break;
            case 3:
                coordinatorLayout.setBackgroundResource(R.drawable.spain240);
                break;
            case 4:
                coordinatorLayout.setBackgroundResource(R.drawable.uk240);
                break;
            case 5:
                view1.setVisibility(View.GONE);
                break;
        }
    }

    private void setupTheme() {
        int theme = userSessionManager.getTheme();
        switch (theme) {
            case 0:
                setupDefaultTheme();
                break;
            case 1:
                setupBrownTheme();
                break;
            case 2:
                setupPurlpleTheme();
                break;
            case 3:
                setupGreenTheme();
                break;
            case 4:
                setupMarronTheme();
                break;
            case 5:
                setupLightBlueTheme();
                break;
        }
    }

    private void setupBrownTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
    }

    private void setupPurlpleTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setupGreenTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setupMarronTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
    }

    private void setupDefaultTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

}
