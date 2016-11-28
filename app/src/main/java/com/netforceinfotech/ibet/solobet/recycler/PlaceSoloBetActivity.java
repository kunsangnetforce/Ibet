package com.netforceinfotech.ibet.solobet.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.Debugger.Debugger;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class PlaceSoloBetActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    String selectedteam = "";
    Toolbar toolbar;
    Context context;
    String match_id, home_name, home_logo, away_name, away_logo, draw_odds, home_odds, away_odds;
    UserSessionManager userSessionManager;
    ImageView imageViewTeamA, imageViewTeamB;
    TextView textViewTeamA, textViewTeamB, textviewselectAway, textviewselectDraw, textviewselectHome, textViewBetamount;
    RelativeLayout relativeLayoutBetAmount;
    EditText editTextPopupBetAmount;
    private double betamount;
    private MaterialDialog dialogbox;
    RadioButton radioButtonHome, radioButtonDraw, radioButtonAway;
    Button buttonPlaceBet;
    private String selectedOdds = "", bookerid, bookername;
    TextView textViewHomeOdds, textviewAwayOdds, textViewDrawOdds;
    CoordinatorLayout coordinatorLayout;
    View view1;
    private MaterialDialog progressDialog;
    TextView textviewCoins;
    LinearLayout linearLayoutToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_solo_bet);
        final Bundle bundle = getIntent().getExtras();
        context = this;
        userSessionManager = new UserSessionManager(this);
        try {
            match_id = bundle.getString("match_id");
            home_name = bundle.getString("home_name");
            away_name = bundle.getString("away_name");
            home_logo = bundle.getString("home_logo");
            away_logo = bundle.getString("away_logo");
            draw_odds = bundle.getString("draw_odds");
            home_odds = bundle.getString("home_odds");
            away_odds = bundle.getString("away_odds");
            bookerid = bundle.getString("bookerid");
            bookername = bundle.getString("bookername");


        } catch (Exception ex) {
            showMessage("Bundle error");
        }
        setupToolBar(home_name + " vs " + away_name);
        initView();
        setupStatusBar();
        setupTheme();
        setupBackbround();
        updatecoin(0);
    }

    private void initView() {
/*
*   ImageView imageViewTeamA,imageViewTeamB;
    TextView textViewTeamA,textViewTeamB,textviewselectAway,textviewselectDraw,textviewselectHome,textViewBetamount;
    RelativeLayout relativeLayoutBetAmount;

* */
        progressDialog = new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0).build();
        view1 = findViewById(R.id.view);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        textViewHomeOdds = (TextView) findViewById(R.id.textviewHomeOdds);
        textviewAwayOdds = (TextView) findViewById(R.id.textviewAwayOdds);
        textViewDrawOdds = (TextView) findViewById(R.id.textviewDrawOdds);
        textViewHomeOdds.setText(home_odds);
        textviewAwayOdds.setText(away_odds);
        textViewDrawOdds.setText(draw_odds);
        buttonPlaceBet = (Button) findViewById(R.id.buttonPlaceBet);
        buttonPlaceBet.setOnClickListener(this);
        imageViewTeamA = (ImageView) findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (ImageView) findViewById(R.id.imageViewTeamB);
        textViewTeamA = (TextView) findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) findViewById(R.id.textViewTeamB);
        textviewselectAway = (TextView) findViewById(R.id.textviewselectAway);
        textviewselectDraw = (TextView) findViewById(R.id.textviewselectDraw);
        textviewselectHome = (TextView) findViewById(R.id.textviewselectHome);
        textviewselectAway.setOnClickListener(this);
        textviewselectDraw.setOnClickListener(this);
        textviewselectHome.setOnClickListener(this);
        textViewBetamount = (TextView) findViewById(R.id.textViewBetamount);
        relativeLayoutBetAmount = (RelativeLayout) findViewById(R.id.relativeLayoutBetAmount);
        relativeLayoutBetAmount.setOnClickListener(this);
        radioButtonAway = (RadioButton) findViewById(R.id.radioTeamb);
        radioButtonDraw = (RadioButton) findViewById(R.id.radioDraw);
        radioButtonHome = (RadioButton) findViewById(R.id.radioTeama);
        try {
            Glide.with(context) .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.home_logo).error(R.drawable.ic_error).into(imageViewTeamA);
        } catch (Exception ex) {
            Glide.with(context).load(R.drawable.ic_error).into(imageViewTeamA);
        }
        try {
            Glide.with(context) .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo).error(R.drawable.ic_error).into(imageViewTeamB);
        } catch (Exception ex) {
            Glide.with(context).load(R.drawable.ic_error).into(imageViewTeamB);
        }
        textViewTeamA.setText(home_name);
        textViewTeamB.setText(away_name);
        radioButtonAway.setOnCheckedChangeListener(this);
        radioButtonHome.setOnCheckedChangeListener(this);
        radioButtonDraw.setOnCheckedChangeListener(this);

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textViewTitle = (TextView) toolbar.findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);
        textviewCoins = (TextView) toolbar.findViewById(R.id.textViewCoins);
        linearLayoutToolbar = (LinearLayout) toolbar.findViewById(R.id.linearLayoutToolbar);

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

    private void showPopup() {
        boolean wrapInScrollView = true;
        dialogbox = new MaterialDialog.Builder(PlaceSoloBetActivity.this)
                .title("Set bet amount")
                .customView(R.layout.setbetamount, wrapInScrollView)
                .positiveText("Set").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (editTextPopupBetAmount.getText().length() <= 0) {
                            showMessage("Enter Amount");
                            showPopup();
                        } else {
                            betamount = Double.parseDouble(editTextPopupBetAmount.getText().toString());
                            if (betamount > 0) {
                                textViewBetamount.setText(betamount + "");
                                dialogbox.dismiss();
                            } else {
                                betamount = 0;
                                showMessage("Bet amount should be more than 0");
                                showPopup();
                            }
                        }
                    }
                })
                .show();
        dialogbox.setCancelable(false);
        editTextPopupBetAmount = (EditText) dialogbox.findViewById(R.id.editText);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textviewselectHome:
                radioButtonHome.setChecked(true);
                break;
            case R.id.textviewselectAway:
                radioButtonAway.setChecked(true);
                break;
            case R.id.textviewselectDraw:
                radioButtonDraw.setChecked(true);
                break;
            case R.id.relativeLayoutBetAmount:
                showPopup();
                break;
            case R.id.buttonPlaceBet:
                if (validate()) {
                    placeSoloBet(selectedteam, betamount, selectedOdds, bookername, bookerid);
                }
                break;
        }
    }

    private void placeSoloBet(String selectedteam, final double betamount, String selectedOdds, String bookername, String bookerid) {
        /*
        * https://netforcesales.com/ibet_admin/api/services.php?opt=solo_bet&user_id=152
        * &match_id=121&selected_team=254&amt=250&match_status=NS&odds=1x2&bookmaker_id=1&bookmaker_name=10Bet
        * */
        progressDialog.show();
        String baseUrl = getString(R.string.url);
        String url = baseUrl + "/services.php?opt=solo_bet&user_id=" + userSessionManager.getCustomerId() + "&match_id=" + match_id
                + "&selected_team=" + selectedteam + "&amt=" + betamount + "&match_status=NS&odds=" + selectedOdds + "&bookmaker_id=" +
                bookerid + "&bookermaker_name=" + bookername;
        Debugger.i("kunsang_url_placesolobebet", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                progressDialog.dismiss();
                if (result == null) {
                    showMessage(getString(R.string.something_went_wrong));
                    return;
                } else {
                    if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                        updatecoin(0);
                        showMessage(getString(R.string.bet_has_been_placed));

                    } else {
                        try {
                            JsonArray data = result.getAsJsonArray("data");
                            JsonObject jsonObject = data.get(0).getAsJsonObject();
                            String msg = jsonObject.get("Message").getAsString();
                            showMessage(msg);
                            return;
                        } catch (Exception ex) {

                        }
                        showMessage(getString(R.string.bet_not_placed));
                    }
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.radioDraw:
                if (b) {
                    textviewselectDraw.setBackgroundResource(R.drawable.circular_bg_fill);
                    selectedteam = "X";
                    selectedOdds = draw_odds;
                } else {
                    textviewselectDraw.setBackgroundResource(R.drawable.circular_bg);
                }
                break;
            case R.id.radioTeama:
                if (b) {
                    textviewselectHome.setBackgroundResource(R.drawable.circular_bg_fill);
                    selectedOdds = home_odds;
                    selectedteam = "1";
                } else {
                    textviewselectHome.setBackgroundResource(R.drawable.circular_bg);
                }
                break;
            case R.id.radioTeamb:
                if (b) {
                    textviewselectAway.setBackgroundResource(R.drawable.circular_bg_fill);
                    selectedOdds = away_odds;
                    selectedteam = "2";
                } else {
                    textviewselectAway.setBackgroundResource(R.drawable.circular_bg);
                }
                break;

        }
    }

    private boolean validate() {
        if (betamount == 0) {
            showMessage("enter bet amount");
            return false;
        }

        if (selectedteam.equalsIgnoreCase("")) {
            showMessage("select a team");
            return false;
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
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
    }

    private void setupPurlpleTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setupGreenTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setupMarronTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
    }

    private void setupDefaultTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
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
