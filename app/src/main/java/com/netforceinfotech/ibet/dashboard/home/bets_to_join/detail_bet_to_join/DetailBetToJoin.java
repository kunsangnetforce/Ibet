package com.netforceinfotech.ibet.dashboard.home.bets_to_join.detail_bet_to_join;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.WhoWillWinActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailBetToJoin extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Context context;
    LinearLayout linearLayout;
    private DetailBetAdapter adapter;
    CoordinatorLayout coordinatorLayout;
    ArrayList<DetailBetData> detailBetDatas = new ArrayList<>();
    private Toolbar toolbar;
    ImageView imageViewTeamA, imageViewTeamB;
    UserSessionManager userSessionManager;
    TextView textViewMSI, textViewBetamount, textViewPlayer, textViewResult, textViewTeam, textViewScore, textViewLoserMessage;
    View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_betstobejoin);
        context = this;
        userSessionManager = new UserSessionManager(this);
        setupStatusBar();
        initView();
        setupRecyclerView();
        setupToolBar("Germany vs Italy");
        setupTheme();
        setupBackground();

    }

    private void initView() {
        view1 = findViewById(R.id.view);
        textViewPlayer = (TextView) findViewById(R.id.textViewPlayer);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewTeam = (TextView) findViewById(R.id.textViewTeam);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        textViewBetamount = (TextView) findViewById(R.id.textViewBetamount);
        textViewMSI = (TextView) findViewById(R.id.textViewMSI);
        textViewLoserMessage = (TextView) findViewById(R.id.textViewLoserMessage);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        findViewById(R.id.buttonJoin).setOnClickListener(this);
        findViewById(R.id.buttonCancel).setOnClickListener(this);
        imageViewTeamA = (ImageView) findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (ImageView) findViewById(R.id.imageViewTeamB);
        Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamA);
        Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamB);
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
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));

    }

    private void setupPurlpleTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));

    }

    private void setupGreenTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

    }

    private void setupMarronTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));

    }

    private void setupLightBlueTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));

    }

    private void setupDefaultTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DetailBetAdapter(context, detailBetDatas);
        recyclerView.setAdapter(adapter);
        setupFinsihedDatas();
        adapter.notifyDataSetChanged();
    }

    private void setupFinsihedDatas() {
        try {
            detailBetDatas.clear();
        } catch (Exception ex) {

        }
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
        detailBetDatas.add(new DetailBetData("Tea", "imageurl"));
    }

    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) toolbar.findViewById(R.id.textViewTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);
        textView.setText(app_name);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonJoin:
                Intent intent = new Intent(context, WhoWillWinActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            case R.id.buttonCancel:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;

        }
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
}
