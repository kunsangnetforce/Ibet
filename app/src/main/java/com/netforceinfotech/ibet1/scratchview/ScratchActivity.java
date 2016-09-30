package com.netforceinfotech.ibet1.scratchview;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.plattysoft.leonids.ParticleSystem;
import com.winsontan520.WScratchView;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import pl.droidsonroids.gif.GifImageView;

public class ScratchActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout relativeLayoutCounter;
    WScratchView scratchView0, scratchView1, scratchView2, scratchView3, scratchView4, scratchView5, scratchView6, scratchView7, scratchView8;
    //ArrayList<ScratchData> scratchDatas = new ArrayList<>();
    ArrayList<Type> types = new ArrayList<>();
    ArrayList<Boolean> revealedList = new ArrayList<>();
    private Toolbar toolbar;
    private MaterialDialog customdialog;
    private ParticleSystem confetti;
    private TextView textViewCounter;
    private GifImageView gif;
    private Button buttonColloect;
    private MaterialDialog.Builder dialog;
    private ParticleSystem confetti_top_right;
    private ParticleSystem confetti_top_left;
    UserSessionManager userSessionManager;
    Context context;
    Button buttonBuyScratch;
    TextView textView0, textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;
    ArrayList<Integer> selectedCoins;
    TextView textviewCoins;
    LinearLayout linearLayoutToolbar;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch);
        setupToolBar("Scratch card");
        context = this;
        userSessionManager = new UserSessionManager(context);
        try {
            String from = getIntent().getStringExtra("from");
            if (from.equalsIgnoreCase("itself")) {
                showMessage("page refresh");
            }
        } catch (Exception ex) {

        }
        initView();
        updatecoin(0);
        setupStatusBar();
        setupTheme();
        selectedCoins = pickCoins();
        setupCard(selectedCoins);
    }

    private void setupCard(ArrayList<Integer> integers) {
        price = 0;
        try {
            revealedList.clear();
            // scratchDatas.clear();
            types.clear();
        } catch (Exception ex) {

        }
        for (int i = 0; i < 9; i++) {
            revealedList.add(false);
        }
        for (int i = 0; i < 9; i++) {
            int value = integers.get(i);
            Type type = new Type(value, 0);
            if (!types.contains(type)) {
                types.add(type);
            }
        }
        textView0.setText(integers.get(0) + "");
        textView1.setText(integers.get(1) + "");
        textView2.setText(integers.get(2) + "");
        textView3.setText(integers.get(3) + "");
        textView4.setText(integers.get(4) + "");
        textView5.setText(integers.get(5) + "");
        textView6.setText(integers.get(6) + "");
        textView7.setText(integers.get(7) + "");
        textView8.setText(integers.get(8) + "");
    }

    private void setDummyScratchData() {

        try {
            revealedList.clear();
            //  scratchDatas.clear();
            types.clear();
        } catch (Exception ex) {

        }
        for (int i = 0; i < 9; i++) {
            revealedList.add(false);
        }
        //ScratchData(int position, String value, String imgUrl, String type) {
      /*  scratchDatas.add(new ScratchData(0, 20, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(1, 1000, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(2, 10, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(3, 1000, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(4, 500, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(5, 10, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(6, 20, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(7, 50, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(8, 20, R.drawable.coin, ""));*/


    }

    private void initView() {
        buttonBuyScratch = (Button) findViewById(R.id.buttonBuyScratch);
        buttonBuyScratch.setOnClickListener(this);
        textViewCounter = (TextView) findViewById(R.id.textViewCounter);
        relativeLayoutCounter = (RelativeLayout) findViewById(R.id.relativeLayoutCounter);
        relativeLayoutCounter.setVisibility(View.GONE);
        gif = (GifImageView) findViewById(R.id.gif);
        dialog = new MaterialDialog.Builder(this)
                .title("You can Choose Only Three Bonus")
                .content("Hi")
                .positiveText("Agree");

        buttonColloect = (Button) findViewById(R.id.buttonCollect);
        buttonColloect.setVisibility(View.GONE);
        buttonColloect.setOnClickListener(this);
        relativeLayoutCounter = (RelativeLayout) findViewById(R.id.relativeLayoutCounter);
        textView0 = (TextView) findViewById(R.id.textView0);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        scratchView0 = (WScratchView) findViewById(R.id.scratchView0);
        scratchView1 = (WScratchView) findViewById(R.id.scratchView1);
        scratchView2 = (WScratchView) findViewById(R.id.scratchView2);
        scratchView3 = (WScratchView) findViewById(R.id.scratchView3);
        scratchView4 = (WScratchView) findViewById(R.id.scratchView4);
        scratchView5 = (WScratchView) findViewById(R.id.scratchView5);
        scratchView6 = (WScratchView) findViewById(R.id.scratchView6);
        scratchView7 = (WScratchView) findViewById(R.id.scratchView7);
        scratchView8 = (WScratchView) findViewById(R.id.scratchView8);

        scratchView0.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(0)) {
                        revealedList.set(0, true);
                        checkGiftStatus(0);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView1.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(1)) {
                        revealedList.set(1, true);
                        checkGiftStatus(1);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView2.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(2)) {
                        revealedList.set(2, true);
                        checkGiftStatus(2);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView3.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(3)) {
                        revealedList.set(3, true);
                        checkGiftStatus(3);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView4.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(4)) {
                        revealedList.set(4, true);
                        checkGiftStatus(4);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView5.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(5)) {
                        revealedList.set(5, true);
                        checkGiftStatus(5);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView6.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(6)) {
                        revealedList.set(6, true);
                        checkGiftStatus(6);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView7.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(7)) {
                        revealedList.set(7, true);
                        checkGiftStatus(7);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView8.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(8)) {
                        revealedList.set(8, true);
                        checkGiftStatus(8);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
    }

    private void checkGiftStatus(int position) {
        int value = selectedCoins.get(position);
        for (int i = 0; i < types.size(); i++) {
            if (value == types.get(i).value) {
                types.get(i).count++;
                if (types.get(i).count >= 3) {
                    showPopUpMessage("Congrats", value);
                    this.price = value;
                    return;
                }

            }
        }
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (revealedList.get(i)) {
                count++;
            }
        }
        if (count == 9) {
            showMessage("Sorry try again later");
            relativeLayoutCounter.setVisibility(View.VISIBLE);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            setupTimeThread(dateFormat.format(cal.getTime()));
            revealAll();
        }
    }

    private void revealAll() {
        scratchView0.setScratchAll(true);
        scratchView1.setScratchAll(true);
        scratchView2.setScratchAll(true);
        scratchView3.setScratchAll(true);
        scratchView4.setScratchAll(true);
        scratchView5.setScratchAll(true);
        scratchView6.setScratchAll(true);
        scratchView7.setScratchAll(true);
        scratchView8.setScratchAll(true);
    }

    private void showMessage(String congrats) {
        Toast.makeText(this, congrats, Toast.LENGTH_SHORT).show();
    }

    public void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);
        textviewCoins = (TextView) toolbar.findViewById(R.id.textViewCoins);
        linearLayoutToolbar = (LinearLayout) toolbar.findViewById(R.id.linearLayoutToolbar);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

    private void showPopUpMessage(String s, final int value) {
        boolean wrapInScrollView = true;
        customdialog = new MaterialDialog.Builder(this)
                .customView(R.layout.custom_dialog, wrapInScrollView).show();
        Button button = (Button) customdialog.findViewById(R.id.button);
        TextView textViewCoins = (TextView) customdialog.findViewById(R.id.textViewCoins);
        textViewCoins.setText("Congrats!!! You got " + value + " coins");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customdialog.dismiss();
                buttonColloect.setVisibility(View.VISIBLE);
                confetti_top_left = new ParticleSystem(ScratchActivity.this, 80, R.drawable.confeti3, 10000)
                        .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                        .setRotationSpeed(144)
                        .setAcceleration(0.00005f, 90);
                confetti_top_left.emit(findViewById(R.id.emiter_top_left), 8);
                confetti_top_right = new ParticleSystem(ScratchActivity.this, 80, R.drawable.confeti2, 10000)
                        .setSpeedModuleAndAngleRange(0f, 0.3f, 180, 180)
                        .setRotationSpeed(144)
                        .setAcceleration(0.00005f, 90);
                confetti_top_right.emit(findViewById(R.id.emiter_top_right), 8);


                gif.setVisibility(View.VISIBLE);


                revealAll();


            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCollect:
                buttonColloect.setClickable(false);
                //:
                //   showPopUpMessage("kunsang");
                try {
                    updatecoin(price);
                    confetti = new ParticleSystem(ScratchActivity.this, 100, R.drawable.confeti2, 5000)
                            .setSpeedRange(0.1f, 0.25f);
                    confetti.oneShot(view, 900);
                    confetti.stopEmitting();
                    confetti_top_left.stopEmitting();
                    confetti_top_right.stopEmitting();
                    gif.setVisibility(View.GONE);
                    relativeLayoutCounter.setVisibility(View.VISIBLE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case R.id.buttonBuyScratch:
                buyScratch();
                break;
        }

    }


    private void setupTimeThread(String starting_date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = Calendar.getInstance().getTime();
        Date myDate = null;
        try {
            myDate = simpleDateFormat.parse(starting_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long milliseconds = (myDate.getTime() - dateNow.getTime());
        new CountDownTimer(milliseconds, 1000) {

            public void onTick(long millisUntilFinished) {

                textViewCounter.setText("" + getFormatedTime(millisUntilFinished));
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                textViewCounter.setText("Live!");

            }

        }.start();
    }

    private String getFormatedTime(long millisUntilFinished) {
        long seconds = millisUntilFinished / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = hours % 24 + " : " + minutes % 60 + " : " + seconds % 60;
        return time;
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
        buttonBuyScratch.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
    }

    private void setupPurlpleTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        buttonBuyScratch.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setupGreenTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        buttonBuyScratch.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setupMarronTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        buttonBuyScratch.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        buttonBuyScratch.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
    }

    private void setupDefaultTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        buttonBuyScratch.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    public ArrayList<Integer> pickCoins() {
        ArrayList<Integer> selectedCoins = new ArrayList<>();
        ArrayList<Integer> coins = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            coins.add(5);
            coins.add(5);
            coins.add(5);
            coins.add(5);
            coins.add(5);
            coins.add(5);
            coins.add(10);
            coins.add(10);
            coins.add(10);
            coins.add(10);
            coins.add(10);
            coins.add(20);
            coins.add(20);
            coins.add(20);
            coins.add(20);
            coins.add(30);
            coins.add(30);
            coins.add(30);
        }
        coins.add(50);
        coins.add(50);
        coins.add(200);
        coins.add(100);
        int bucketSize = coins.size();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            selectedCoins.add(coins.get(random.nextInt(bucketSize)));
        }
        return selectedCoins;
    }

    private void buyScratch() {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=add_coin&custid=15&amt_new=50
        String baseUrl = getString(R.string.url);
        String updatecointsurl = "/services.php?opt=add_coin&custid=" + userSessionManager.getCustomerId() + "&amt_new=" + -20;
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
                                refreshPage(result);
                                Log.i("kunsangcoins", result.toString());
                            } else {
                                showMessage("Could not set team. Try again");
                            }
                        }
                    }
                });
    }

    private void refreshPage(JsonObject result) {
        Intent intent = new Intent(context, ScratchActivity.class);
        intent.putExtra("from", "itself");
        startActivity(intent);
        finish();
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
                                showMessage("Could not set team. Try again");
                            }
                        }
                    }
                });
    }

    private void refreshCoin(JsonObject result) {
        JsonArray data = result.getAsJsonArray("data");
        JsonObject object = data.get(0).getAsJsonObject();
        String coins = object.get("Current Coin").getAsString();
        textviewCoins.setText(coins);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        setupTimeThread(dateFormat.format(cal.getTime()));
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


