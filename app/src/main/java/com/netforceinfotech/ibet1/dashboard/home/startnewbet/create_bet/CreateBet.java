package com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.kyleduo.switchbutton.SwitchButton;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.StartNewBetActivity;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.SearchFriendActivity;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.SearchFriendData;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class CreateBet extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Toolbar toolbar;
    ImageView setting_list1, setting_list2, setting_list3;
    private MaterialDialog dailog;
    TextView textViewRemaining, textviewCoins;
    EditText editText;
    TextView friendslist;
    SwitchButton switchButtonCanJoin, switchButtonCantView, switchButtonCanView;
    private String switchOption = "0";
    String betoption, selectedteam, home_name, away_name;
    double betamount;
    int homescore, awayscore;
    private MaterialDialog dialogConfirmation;
    String match_id;
    private String frindstring;
    Context context;
    private String friendsidstring = "";
    private String home_id, away_id;
    UserSessionManager userSessionManager;
    private String losercomment = "";
    private CoordinatorLayout coordinatorLayout;
    private Button buttonCreateBet;
    private View view1;
    LinearLayout linearLayoutToolbar;
    private int FRIENDSLIST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bet);/*
          bundle1.putString("betoption", stringbetoption);
                    bundle1.putInt("homescore", homescore);
                    bundle1.putInt("awayscore", awayscore);
                    bundle1.putString("selectedteam", selectedteam);
                    bundle1.putDouble("betamount", betamount);

        */
        Bundle bundle = getIntent().getExtras();
        userSessionManager = new UserSessionManager(getApplicationContext());
        context = this;
        try {
            home_id = bundle.getString("home_id");
            away_id = bundle.getString("away_id");
            match_id = bundle.getString("match_id");
            betamount = bundle.getDouble("betamount");
            betoption = bundle.getString("betoption");
            homescore = bundle.getInt("homescroe");
            awayscore = bundle.getInt("awayscore");
            selectedteam = bundle.getString("selectedteam");
            home_name = bundle.getString("home_name");
            away_name = bundle.getString("away_name");
        } catch (Exception ex) {

        }
        setupStatusBar();
        setupToolBar("Create Bet");
        setuplayout();
        setupTheme();
        setupBackbround();
        findViewById(R.id.buttoncreatebet).setOnClickListener(this);

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
        buttonCreateBet.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));

    }

    private void setupPurlpleTheme() {
        buttonCreateBet.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));

    }

    private void setupGreenTheme() {
        buttonCreateBet.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

    }

    private void setupMarronTheme() {
        buttonCreateBet.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));

    }

    private void setupLightBlueTheme() {
        buttonCreateBet.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));

    }

    private void setupDefaultTheme() {
        buttonCreateBet.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setuplayout() {
        view1 = findViewById(R.id.view);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        switchButtonCanJoin = (SwitchButton) findViewById(R.id.switchbuttonCanJoin);
        switchButtonCantView = (SwitchButton) findViewById(R.id.switchbuttonCantView);
        switchButtonCanView = (SwitchButton) findViewById(R.id.switchbuttonCanView);
        switchButtonCanView.setOnCheckedChangeListener(this);
        switchButtonCantView.setOnCheckedChangeListener(this);
        switchButtonCanJoin.setOnCheckedChangeListener(this);
        editText = (EditText) findViewById(R.id.editText);
        textViewRemaining = (TextView) findViewById(R.id.textViewRemaing);
        friendslist = (TextView) findViewById(R.id.textViewListofFriends);
        setting_list1 = (ImageView) findViewById(R.id.canViewJoin);
        setting_list2 = (ImageView) findViewById(R.id.canView);
        setting_list3 = (ImageView) findViewById(R.id.cantView);
        findViewById(R.id.buttonInviteFriend).setOnClickListener(this);
        buttonCreateBet = (Button) findViewById(R.id.buttoncreatebet);
        buttonCreateBet.setOnClickListener(this);
        boolean wrapInScrollView = true;


        setting_list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp("If all checkboxes are off. By default all users can view and join the bet 1");
            }
        });


        setting_list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp("If all checkboxes are off. By default all users can view and join the bet 2");
            }
        });


        setting_list3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp("If all checkboxes are off. By default all users can view and join the bet 3");
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                int remaining = 200 - editText.length();
                textViewRemaining.setText(remaining + " characters remaining");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void showPopUp(String s) {
        dailog = new MaterialDialog.Builder(CreateBet.this)
                .title("Info")
                .customView(R.layout.custom_view_text, true).build();

        Button b = (Button) dailog.findViewById(R.id.got_it_buttton);
        TextView textView = (TextView) dailog.findViewById(R.id.textView);
        textView.setText(s);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dailog.dismiss();
            }
        });
        dailog.show();

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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonInviteFriend:
                // startActivity(new Intent(CreateBet.this, SearchFriendActivity.class));
                Intent i = new Intent(this, SearchFriendActivity.class);
                startActivityForResult(i, FRIENDSLIST);
                break;
            case R.id.buttoncreatebet:
                if (validate()) {
                    showConfirmation();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FRIENDSLIST) {
            if (resultCode == RESULT_OK) {
                friendsidstring = data.getStringExtra("friendsid");//friendsname
                frindstring = data.getStringExtra("frindstring");
                friendslist.setText(friendsidstring);
                Debugger.i("kactivityresult", friendsidstring);
            }
        }
    }

    private void showConfirmation() {
        dialogConfirmation = new MaterialDialog.Builder(CreateBet.this)
                .title("Bet Detail")
                .customView(R.layout.confirmation, true)
                .positiveText("Ok")
                .negativeText("Reset")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        createbet();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                    }
                })
                .build();

        LinearLayout linearLayoutConfirmation = (LinearLayout) dialogConfirmation.findViewById(R.id.linearLayoutConfirmation);
        LinearLayout linearLayoutScore, linearLayoutTeam;
        linearLayoutScore = (LinearLayout) dialogConfirmation.findViewById(R.id.linearLayoutScore);
        linearLayoutTeam = (LinearLayout) dialogConfirmation.findViewById(R.id.linearLayoutTeam);
        TextView textViewSelectedTeam, textViewScore, textViewBetAmount, textViewLoserMessage, textViewScope, textViewFriendInvited;
        textViewSelectedTeam = (TextView) dialogConfirmation.findViewById(R.id.textViewTeamSelected);
        textViewScope = (TextView) dialogConfirmation.findViewById(R.id.textViewScope);
        textViewScore = (TextView) dialogConfirmation.findViewById(R.id.textViewScore);
        textViewBetAmount = (TextView) dialogConfirmation.findViewById(R.id.textViewBetAmount);
        textViewLoserMessage = (TextView) dialogConfirmation.findViewById(R.id.textViewLoserMessage);
        textViewFriendInvited = (TextView) dialogConfirmation.findViewById(R.id.textViewFriendInvited);
        if (betoption.equalsIgnoreCase("0")) {

            linearLayoutScore.setVisibility(View.GONE);
            linearLayoutTeam.setVisibility(View.VISIBLE);
        } else if (betoption.equalsIgnoreCase("1")) {
            linearLayoutScore.setVisibility(View.VISIBLE);
            linearLayoutTeam.setVisibility(View.GONE);
        } else {
            linearLayoutScore.setVisibility(View.VISIBLE);
            linearLayoutTeam.setVisibility(View.VISIBLE);
        }
        if (selectedteam.equalsIgnoreCase("home_win")) {
            textViewSelectedTeam.setText("Selected Team : " + home_name);
        } else if (selectedteam.equalsIgnoreCase("away_win")) {
            textViewSelectedTeam.setText("Selected Team : " + away_name);
        } else {
            textViewSelectedTeam.setText("Selected Team : " + "Draw");
        }
        textViewScore.setText("Score Prediction : " + home_name + " " + homescore + " - " + awayscore + " " + away_name);

        if (switchOption.equalsIgnoreCase("0")) {
            textViewScope.setText("Bet Scope : " + "Anyone can View and Join the Bet");
        } else if (switchOption.equalsIgnoreCase("1")) {
            textViewScope.setText("Bet Scope : " + "Anyone can View the Bet");
        } else {
            textViewScope.setText("Bet Scope : " + "No one can view or bet the Bet");
        }
        textViewBetAmount.setText("Bet Amount : " + betamount);
        textViewLoserMessage.setText("Loser Message : " + editText.getText().toString());
        textViewFriendInvited.setText("Friend Invited : " + friendslist.getText().toString());
        dialogConfirmation.show();
        switch (userSessionManager.getTheme()) {
            case 0:
                linearLayoutConfirmation.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                break;
            case 1:
                linearLayoutConfirmation.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
                break;
            case 2:
                linearLayoutConfirmation.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
                break;
            case 3:
                linearLayoutConfirmation.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
                break;
            case 4:
                linearLayoutConfirmation.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
                break;
            case 5:
                linearLayoutConfirmation.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
                break;

        }

    }

    private boolean validate() {
        if (editText.length() <= 0) {
            showMessage("Enter loser message");
            return false;
        }
        losercomment = editText.getText().toString();
        try {
            losercomment = URLEncoder.encode(losercomment, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return true;
    }

    private void showMessage(String s) {
        Toast.makeText(CreateBet.this, s, Toast.LENGTH_SHORT).show();
    }

    private void createbet() {
      /*
     https://netforcesales.com/ibet_admin/api/create_bet.php?match_id=638898
     &friends_id=162,163,164&comments=5&home_team_id=1228&away_team_id=2150
     &bet_amount=10.0&bet_option=0&bet_options_to_user=0&user_id=152&bet_remarks=test
      * */
        if (friendsidstring.trim().length() == 0) {
            friendsidstring = userSessionManager.getCustomerId();
        } else {
            friendsidstring = friendsidstring + "," + userSessionManager.getCustomerId();
        }
        String baseUrl = getString(R.string.url);
        String betUrl = "/create_bet.php?match_id=" + match_id + "&friends_id=" + friendsidstring + "&comments=" + losercomment
                + "&home_team_id=" + home_id + "&away_team_id=" + away_id + "&bet_amount=" + betamount + "&bet_status=&bet_match_date=" +
                "&bet_option=" + betoption + "&bet_option_to_user=" + switchOption + "&user_id=" + userSessionManager.getCustomerId() +
                "&bet_remarks=" + losercomment;
        String url = baseUrl + betUrl;
        Debugger.i("kunsang_url_createbet", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (result == null) {
                    showMessage("Something went wrong could not create bet");
                } else {
                    try {
                        if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                            showMessage("bet create successfully");
                            String bet_id = result.get("bet_id").getAsString();
                            String bet_option = result.get("bet_option").getAsString();
                            String bet_amount = result.get("bet_amount").getAsString();
                            String team_home = result.get("team_home").getAsString();
                            String team_away = result.get("team_away").getAsString();
                            joinBet(bet_id, bet_amount, bet_option, "0");
                        } else {
                            try {
                                String message = result.get("message").getAsString();
                                showMessage(message);
                            } catch (Exception ex) {
                                showMessage("Something went wrong");
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
            }
        });
        Log.i("kunsangurl", url);
        //joinbet()
    }


    private void joinBet(String bet_id, String bet_amount, String bet_option, String request_type) {

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
        showMessage("bet losic will be created");
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (result == null) {
                    showMessage("Not able to join");
                } else {
                    try {
                        if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                            updatecoin(0);
                            Debugger.i("kjoinbet", "joinsuccessfull");
                        }
                    } catch (Exception ex) {

                    }

                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switchbuttonCanJoin:
                if (b) {
                    switchButtonCanView.setChecked(false);
                    switchButtonCantView.setChecked(false);
                } else {
                    switchOption = "0";
                }
                switchOption = "0";
                break;
            case R.id.switchbuttonCanView:
                if (b) {
                    switchButtonCanJoin.setChecked(false);
                    switchButtonCantView.setChecked(false);
                } else {
                    switchOption = "0";
                }
                switchOption = "1";
                break;
            case R.id.switchbuttonCantView:
                if (b) {
                    switchButtonCanJoin.setChecked(false);
                    switchButtonCanView.setChecked(false);
                } else {
                    switchOption = "0";
                }
                switchOption = "2";
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
}
