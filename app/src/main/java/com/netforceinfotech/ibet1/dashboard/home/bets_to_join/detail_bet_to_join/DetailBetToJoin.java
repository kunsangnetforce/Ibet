package com.netforceinfotech.ibet1.dashboard.home.bets_to_join.detail_bet_to_join;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.WhoWillWinActivity;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
    TextView textViewMSI, textViewBetamount, textViewPlayer, textViewResult, textViewTeam, textViewScore, textViewLoserMessage, textViewMatchCountdown, textViewTeamA, textViewTeamB;
    View view1;
    private String bet_id, match_id, home_name, away_name, home_logo, away_logo, home_id, away_id;
    private String bet_ammount;
    private String bet_option;
    private TextView textviewCoins;
    private MaterialDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_betstobejoin);
        context = this;
        userSessionManager = new UserSessionManager(this);
        setupStatusBar();
        initView();

        try {
            /*
            * match_id = bundle.getString("match_id");
            home_name = bundle.getString("home_name");
            away_name = bundle.getString("away_name");
            home_logo = bundle.getString("home_logo");
            away_logo = bundle.getString("away_logo");
            home_id = bundle.getString("home_id");
            away_id = bundle.getString("away_id");
            * */
            Bundle bundle = getIntent().getExtras();

            bet_id = bundle.getString("bet_id");
            match_id = bundle.getString("match_id");
            home_id = bundle.getString("home_id");
            home_name = bundle.getString("home_name");
            home_logo = bundle.getString("home_logo");
            away_id = bundle.getString("away_id");
            away_name = bundle.getString("away_name");
            away_logo = bundle.getString("away_logo");
            bet_option = bundle.getString("bet_option");

            Debugger.i("kbundle", bet_id);

            getBetDetail(bet_id);
        } catch (Exception ex) {
            Debugger.e("kbundle", "bundle error");

        }
        setupToolBar(home_name + " vs " + away_name);
        setupRecyclerView();
        setupTheme();
        setupBackground();

    }

    @Override
    protected void onResume() {
        super.onResume();
        textviewCoins.setText(userSessionManager.getCoins());
    }

    private void initView() {
        progressDialog = new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0).build();
        view1 = findViewById(R.id.view);
        textViewMatchCountdown = (TextView) findViewById(R.id.textViewMatchCountdown);
        textViewTeamA = (TextView) findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) findViewById(R.id.textViewTeamB);
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
        Glide.with(context).load(R.drawable.ic_error).into(imageViewTeamA);
        Glide.with(context).load(R.drawable.ic_error).into(imageViewTeamB);
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
    }


    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) toolbar.findViewById(R.id.textViewTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);
        textView.setText(app_name);
        textviewCoins = (TextView) toolbar.findViewById(R.id.textViewCoins);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonJoin:
                Intent intent = new Intent(context, AcceptBetActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("match_id", match_id);
                bundle.putString("bet_id", bet_id);
                bundle.putString("home_name", home_name);
                bundle.putString("home_id", home_id);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_name", away_name);
                bundle.putString("away_id", away_id);
                bundle.putString("away_logo", away_logo);
                bundle.putString("bet_option", bet_option);
                bundle.putString("bet_amount", bet_ammount);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            case R.id.buttonCancel:
                joinBet(bet_id, bet_ammount, bet_option, "1");
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
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
                }
                break;
            case 3:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
                }
                break;
            case 4:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
                }
                break;
            case 5:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
                }
                break;
        }

    }

    private void getBetDetail(String bet_id) {
        //https://netforcesales.com/ibet_admin/api/bets_to_join_detail.php?&bet_id=237
        String baseUrl = getString(R.string.url);
        String url = baseUrl + "/bets_to_join_detail.php?&bet_id=" + bet_id;
        Debugger.i("kunsang_url_BetdetailJoin", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (result == null) {
                    showMessage("Something went wrong");
                    return;
                } else {
                    Debugger.i("kresult", "reached inside");
                    setupBetDetail(result);
                }
            }
        });
    }

    private void showMessage(String s) {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setupBetDetail(JsonObject result) {
        Debugger.i("kresult", result.toString());
        if (result.get("status").getAsString().equalsIgnoreCase("success")) {
            if (!result.getAsJsonObject("bets_detail").isJsonNull()) {
                Debugger.i("kexecuted?", "executed");
                String home_id, away_id, team_away_flag, team_home_flag, bet_match_time, bet_match_date;
                JsonObject bet_detail = result.getAsJsonObject("bets_detail");
                if (!bet_detail.getAsJsonObject("bet").isJsonNull()) {
                    JsonObject bet = bet_detail.getAsJsonObject("bet");

                    if (!bet.get("bet_remarks").isJsonNull()) {
                        String bet_remarks = bet.get("bet_remarks").getAsString();
                        textViewLoserMessage.setText(bet_remarks);
                    }
                    if (!bet.get("team_home_id").isJsonNull()) {
                        home_id = bet.get("team_home_id").getAsString();
                    }
                    if (!bet.get("team_away_id").isJsonNull()) {
                        away_id = bet.get("team_away_id").getAsString();
                    }
                    if (!bet.get("team_away_flag").isJsonNull()) {
                        team_away_flag = bet.get("team_away_flag").getAsString();
                        Glide.with(context).load(team_away_flag).error(R.drawable.ic_error).into(imageViewTeamB);
                    }
                    if (!bet.get("team_home_flag").isJsonNull()) {
                        team_home_flag = bet.get("team_home_flag").getAsString();
                        Glide.with(context).load(team_home_flag).error(R.drawable.ic_error).into(imageViewTeamA);
                    }
                    if (!bet.get("team_away_name").isJsonNull()) {
                        away_name = bet.get("team_away_name").getAsString();
                        textViewTeamB.setText(away_name);
                    }
                    if (!bet.get("team_home_name").isJsonNull()) {
                        home_name = bet.get("team_home_name").getAsString();
                        textViewTeamA.setText(home_name);

                    }
                    if (!bet.get("bet_amount").isJsonNull()) {
                        bet_ammount = bet.get("bet_amount").getAsString();
                        textViewBetamount.setText(bet_ammount);
                    }
                    if (!bet.get("bet_match_time").isJsonNull() && !bet.get("bet_match_date").isJsonNull()) {
                        bet_match_time = bet.get("bet_match_time").getAsString();
                        bet_match_date = bet.get("bet_match_date").getAsString();
                        setupTimeThread(bet_match_date, bet_match_time);
                    }
                    if (!bet.get("bet_option").isJsonNull()) {
                        bet_option = bet.get("bet_option").getAsString();
                    }

                }
                JsonArray users = bet_detail.getAsJsonArray("users");
                int size = users.size();
                Debugger.i("ksize", size + "");
                if (size == 0) {
                    return;
                } else {
                    for (int i = 0; i < size; i++) {
                        Debugger.i("kadd_data", i + "");
                        JsonObject user = users.get(i).getAsJsonObject();
                        String userdp = user.get("profile_image").getAsString();
                        String username = user.get("name").getAsString();
                        String bet_result = user.get("bet_result").getAsString();
                        String selectedTeam = user.get("match_status").getAsString();
                        String home_score = user.get("home_scrore").getAsString();
                        String away_score = user.get("away_scrore").getAsString();
                        String score = home_score + "-" + away_score;
                        if (selectedTeam.equalsIgnoreCase("home_win")) {
                            selectedTeam = home_name;
                        } else if (selectedTeam.equalsIgnoreCase("away_win")) {
                            selectedTeam = away_name;
                        } else {
                            selectedTeam = "draw";
                        }
                        if (bet_option.equalsIgnoreCase("0")) {
                            score = "NA";
                        } else if (bet_option.equalsIgnoreCase("1")) {
                            selectedTeam = "NA";
                        } else {

                        }
                        DetailBetData detailBetData = new DetailBetData(userdp, username, bet_result, selectedTeam, score);
                        detailBetDatas.add(detailBetData);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

        } else {
            showMessage("No data");
        }

    }


    private void setupTimeThread(String starting_date, String starting_time) {
        String datetime = starting_date + " " + starting_time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = Calendar.getInstance().getTime();
        Date myDate = null;
        try {
            myDate = simpleDateFormat.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long milliseconds = (myDate.getTime() - dateNow.getTime());
        new CountDownTimer(milliseconds, 1000) {

            public void onTick(long millisUntilFinished) {

                textViewMatchCountdown.setText("" + getFormatedTime(millisUntilFinished));
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                textViewMatchCountdown.setText("Live!");
            }

        }.start();
    }

    private String getFormatedTime(long millisUntilFinished) {
        long seconds = millisUntilFinished / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + "D : " + hours % 24 + " : " + minutes % 60 + " : " + seconds % 60;
        return time;
    }

    private void joinBet(String bet_id, String bet_amount, String bet_option, String request_type) {
        progressDialog.show();
        /*
        * https://netforcesales.com/ibet_admin/api/accept_bet_request.php?
        * match_status=home_win&option=0&user_id=164&bet_id=237&user_bet_amt=10&away_scrore=0&home_s
        * */
        String baseUrl = getString(R.string.url);
        String joinBetUrl = "/accept_bet_request.php?match_status=" + "" + "&option=" + bet_option
                + "&user_id=" + userSessionManager.getCustomerId() +
                "&bet_id=" + bet_id + "&user_bet_amt=" + bet_amount + "&away_scrore="
                + "" + "&home_scrore=" + "" + "&request_type=" + request_type + "&match_id=" + match_id;
        String url = baseUrl + joinBetUrl;
        Debugger.i("kunsang_url_JoinBet", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                progressDialog.dismiss();
                if (result == null) {
                    showMessage("Not able to Reject. Try again");
                } else {
                    try {
                        if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                            showMessage("Bet Rejected Successfully");
                            finish();
                        }
                    } catch (Exception ex) {

                    }

                }
            }
        });
    }
}
