package com.netforceinfotech.ibet1.dashboard.home.bets_to_join.detail_bet_to_join;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.Dashboard;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.CreateBet;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class AcceptBetActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    RelativeLayout relativeLayoutTeam;
    int homescore = 0, awayscore = 0;

    Button buttonPlaceBet;
    LinearLayout linearLayoutScoreMain, linearLayoutToolbar, linearLayoutHome, linearLayoutAway;
    String bet_option, bet_id, home_name, home_logo, away_name, away_logo, match_id, bet_amount;
    String selectedteam = "";
    TextView textViewBetAmount, textviewselectHome, textviewselectDraw, textviewselectAway, textViewScoreHome, textViewScoreAway, textviewCoins, textViewTeamA, textViewTeamB;
    RadioButton radioButtonHome, radioButtonDraw, radioButtonAway;
    ImageView imageViewTeamA, imageViewTeamB, imageViewHomeIncrement, imageViewHomeDecrement, imageViewAwayIncrement, imageViewAwayDecrement;
    UserSessionManager userSessionManager;
    Context context;
    Toolbar toolbar;
    CoordinatorLayout coordinatorLayout;
    View view1;
    private MaterialDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_bet);
        context = this;
        userSessionManager = new UserSessionManager(context);
        try {
            Bundle bundle = getIntent().getExtras();
            bet_option = bundle.getString("bet_option");
            bet_id = bundle.getString("bet_id");
            bet_amount = bundle.getString("bet_amount");
            match_id = bundle.getString("match_id");
            home_name = bundle.getString("home_name");
            home_logo = bundle.getString("home_logo");
            away_name = bundle.getString("away_name");
            away_logo = bundle.getString("away_logo");
            try {
                bet_amount = bundle.getString("bet_amount");
            } catch (Exception ex) {

            }


        } catch (Exception ex) {
            Debugger.i("kbundle", "bundle erre");

        }
        initView();
        setupToolBar(home_name + " vs " + away_name);
        updatecoin(0);
        setupStatusBar();
        setupTheme();
        setupBackbround();

    }


    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textViewTitle = (TextView) toolbar.findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);
        textviewCoins = (TextView) toolbar.findViewById(R.id.textViewCoins);
        linearLayoutToolbar = (LinearLayout) toolbar.findViewById(R.id.linearLayoutToolbar);

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


    private void initView() {
        textViewTeamA = (TextView) findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) findViewById(R.id.textViewTeamB);
        progressDialog = new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0).build();
        linearLayoutAway = (LinearLayout) findViewById(R.id.linearLayoutAway);
        linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayoutHome);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        view1 = findViewById(R.id.view);
        buttonPlaceBet = (Button) findViewById(R.id.buttonPlaceBet);
        buttonPlaceBet.setOnClickListener(this);
        textViewScoreHome = (TextView) findViewById(R.id.textViewHomeScore);
        textViewScoreAway = (TextView) findViewById(R.id.textViewAwayScore);
        imageViewTeamA = (ImageView) findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (ImageView) findViewById(R.id.imageViewTeamB);
        imageViewHomeIncrement = (ImageView) findViewById(R.id.imageViewHomeIncrement);
        imageViewHomeDecrement = (ImageView) findViewById(R.id.imageViewHomeDecrement);
        imageViewAwayIncrement = (ImageView) findViewById(R.id.imageviewAwayincrement);
        imageViewAwayDecrement = (ImageView) findViewById(R.id.imageViewAwayDecrement);
        radioButtonAway = (RadioButton) findViewById(R.id.radioTeamb);
        radioButtonDraw = (RadioButton) findViewById(R.id.radioDraw);
        radioButtonHome = (RadioButton) findViewById(R.id.radioTeama);
        radioButtonAway.setOnCheckedChangeListener(this);
        radioButtonHome.setOnCheckedChangeListener(this);
        radioButtonDraw.setOnCheckedChangeListener(this);
        imageViewAwayIncrement.setOnClickListener(this);
        imageViewHomeIncrement.setOnClickListener(this);
        imageViewAwayDecrement.setOnClickListener(this);
        imageViewHomeDecrement.setOnClickListener(this);

        textviewselectHome = (TextView) findViewById(R.id.textviewselectHome);
        textviewselectDraw = (TextView) findViewById(R.id.textviewselectDraw);
        textviewselectAway = (TextView) findViewById(R.id.textviewselectAway);
        textviewselectAway.setOnClickListener(this);
        textviewselectHome.setOnClickListener(this);
        textviewselectDraw.setOnClickListener(this);
        textViewBetAmount = (TextView) findViewById(R.id.textViewBetamount);
        textViewBetAmount.setText(bet_amount);
        relativeLayoutTeam = (RelativeLayout) findViewById(R.id.relativeLayoutTeam);
        linearLayoutScoreMain = (LinearLayout) findViewById(R.id.linearLayoutScoreMain);
        if (bet_option.equalsIgnoreCase("0")) {
            relativeLayoutTeam.setVisibility(View.VISIBLE);
            linearLayoutScoreMain.setVisibility(View.GONE);
        } else if (bet_option.equalsIgnoreCase("1")) {
            relativeLayoutTeam.setVisibility(View.GONE);
            linearLayoutScoreMain.setVisibility(View.VISIBLE);
        } else {
            relativeLayoutTeam.setVisibility(View.VISIBLE);
            linearLayoutScoreMain.setVisibility(View.VISIBLE);
        }
        Glide.with(context).load(home_logo).into(imageViewTeamA);
        Glide.with(context).load(away_logo).into(imageViewTeamB);
        textViewTeamA.setText(home_name);
        textViewTeamB.setText(away_name);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.radioDraw:
                if (b) {
                    textviewselectDraw.setBackgroundResource(R.drawable.circular_bg_fill);
                    selectedteam = "draw";
                } else {
                    textviewselectDraw.setBackgroundResource(R.drawable.circular_bg);
                }
                break;
            case R.id.radioTeama:
                if (b) {
                    textviewselectHome.setBackgroundResource(R.drawable.circular_bg_fill);
                    selectedteam = "home_win";
                } else {
                    textviewselectHome.setBackgroundResource(R.drawable.circular_bg);
                }
                break;
            case R.id.radioTeamb:
                if (b) {
                    textviewselectAway.setBackgroundResource(R.drawable.circular_bg_fill);
                    selectedteam = "away_win";
                } else {
                    textviewselectAway.setBackgroundResource(R.drawable.circular_bg);
                }
                break;

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewHomeIncrement:
                homescore++;
                textViewScoreHome.setText("" + homescore);
                break;
            case R.id.imageviewAwayincrement:
                awayscore++;
                textViewScoreAway.setText("" + awayscore);
                break;
            case R.id.imageViewHomeDecrement:
                homescore--;
                if (homescore < 0) {
                    homescore = 0;
                }
                textViewScoreHome.setText("" + homescore);
                break;
            case R.id.imageViewAwayDecrement:
                awayscore--;
                if (awayscore < 0) {
                    awayscore = 0;
                }
                textViewScoreAway.setText("" + awayscore);
                break;
            case R.id.textviewselectHome:
                radioButtonHome.setChecked(true);
                break;
            case R.id.textviewselectAway:
                radioButtonAway.setChecked(true);
                break;
            case R.id.textviewselectDraw:
                radioButtonDraw.setChecked(true);
                break;
            case R.id.buttonPlaceBet:
                if (validate()) {
                    joinBet(bet_id, bet_amount, bet_option, "0");
                }
                break;

        }
    }

    private void joinBet(String bet_id, String bet_amount, String bet_option, String request_type) {
        progressDialog.show();
        /*
        * https://netforcesales.com/ibet_admin/api/accept_bet_request.php?
        * match_status=home_win&option=0&user_id=164&bet_id=237&user_bet_amt=10&away_scrore=0&home_s
        * */
        String baseUrl = getString(R.string.url);
        String joinBetUrl = "/accept_bet_request.php?match_status=" + selectedteam + "&option=" + bet_option
                + "&user_id=" + userSessionManager.getCustomerId() +
                "&bet_id=" + bet_id + "&user_bet_amt=" + bet_amount + "&away_scrore="
                + awayscore + "&home_scrore=" + awayscore + "&request_type=" + request_type + "&match_id=" + match_id;
        String url = baseUrl + joinBetUrl;
        Debugger.i("kunsang_url_JoinBet", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                progressDialog.dismiss();
                if (result == null) {
                    showMessage("Not able to join");
                } else {
                    try {
                        if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                            updatecoin(0);
                            showMessage("Join successfully");
                            Intent intent = new Intent(context, Dashboard.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    } catch (Exception ex) {

                    }

                }
            }
        });
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void updatecoin(int coins) {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=add_coin&custid=15&amt_new=50
        String baseUrl = getString(R.string.url);
        String updatecointsurl = "/services.php?opt=add_coin&custid=" + userSessionManager.getCustomerId() + "&amt_new=" + coins;
        String url = baseUrl + updatecointsurl;
        setupSelfSSLCert();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result == null) {
                            showMessage("Something is wrong");
                        } else {
                            Log.i("kunsangresult", result.toString());
                            if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                                refreshCoin(result);
                                Log.i("kunsangcoins", result.toString());
                            } else {
                                showMessage("Something went wrong");
                            }
                        }
                    }
                });
    }


    private void refreshCoin(JsonObject result) {
        JsonArray data = result.getAsJsonArray("data");
        JsonObject object = data.get(0).getAsJsonObject();
        String coins = object.get("Current Coin").getAsString();
        userSessionManager.setCoins(coins);
        textviewCoins.setText(coins);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(linearLayoutToolbar);
    }

    public void setupSelfSSLCert() {
        final Trust trust = new Trust();
        final TrustManager[] trustmanagers = new TrustManager[]{trust};
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustmanagers, new SecureRandom());
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private static class Trust implements X509TrustManager {

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }

    private boolean validate() {

        if (bet_option.equalsIgnoreCase("0")) {
            if (selectedteam.equalsIgnoreCase("")) {
                showMessage("select a team");
                return false;
            }
        }
        return true;
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

    private void setupBackbround() {

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
        linearLayoutHome.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        linearLayoutAway.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
    }

    private void setupPurlpleTheme() {
        linearLayoutHome.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        linearLayoutAway.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setupGreenTheme() {
        linearLayoutHome.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        linearLayoutAway.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setupMarronTheme() {
        linearLayoutHome.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        linearLayoutAway.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        linearLayoutHome.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        linearLayoutAway.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
    }

    private void setupDefaultTheme() {
        linearLayoutHome.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        linearLayoutAway.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }
}
