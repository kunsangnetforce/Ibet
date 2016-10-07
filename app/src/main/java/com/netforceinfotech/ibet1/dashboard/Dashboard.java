package com.netforceinfotech.ibet1.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mikepenz.materialdrawer.AccountHeader;
import com.netforceinfotech.ibet1.MainActivity;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.chart.ChartFragment;
import com.netforceinfotech.ibet1.dashboard.profile.ProfileFragment;
import com.netforceinfotech.ibet1.dashboard.purchase.PurchaseFragment;
import com.netforceinfotech.ibet1.dashboard.setting.SettingFragment;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.netforceinfotech.ibet1.login.LoginActivity;
import com.netforceinfotech.ibet1.profilesetting.tutorial.DefaultIntro;
import com.netforceinfotech.ibet1.scratchview.ScratchActivity;
import com.netforceinfotech.ibet1.scratchview.ScratchFragment;
import com.plattysoft.leonids.ParticleSystem;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class
Dashboard extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private UserSessionManager userSessionManager;
    private AccountHeader headerResult;
    private String imageURL, tagName;
    Intent intent;
    public static TextView title;
    ImageView imageViewScratch;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    LinearLayout header_background;
    String loginmode;
    private Menu menu;
    public static CircleImageView imageViewProfilePic;
    public static TextView textViewName;
    Context context;
    private MaterialDialog dailog;
    private ParticleSystem confetti_top_right, confetti_top_left;
    TextView textviewCoins;
    LinearLayout linearLayoutToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        setContentView(R.layout.activity_dashboard);
        context = this;
        userSessionManager = new UserSessionManager(getApplicationContext());
        loginmode = userSessionManager.getLoginMode();
        initView();
        setupToolBar("Ibet");
        setupNavigationView();
        setupTheme();
        setupStatusBar();
        setupDashboardFragment();
        userSessionManager = new UserSessionManager(getApplicationContext());
        String id = userSessionManager.getFBID();
        imageURL = "https://graph.facebook.com/" + id + "/picture?type=large";
        // setupNavigation(imageURL);
        setupNavigationHeader();

        if (userSessionManager.getIsFirstTime() && !userSessionManager.getLoginMode().equalsIgnoreCase("0")) {
            showPopUp("");
        }
        if (userSessionManager.getLoginMode().equalsIgnoreCase("0")) {
            linearLayoutToolbar.setVisibility(View.GONE);
        } else {
            updatecoin(0);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        header_background = (LinearLayout) findViewById(R.id.header_relative);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        imageViewScratch = (ImageView) navigationView.findViewById(R.id.imageViewScratch);
        imageViewScratch.setOnClickListener(this);
        textviewCoins = (TextView) toolbar.findViewById(R.id.textViewCoins);
        linearLayoutToolbar = (LinearLayout) toolbar.findViewById(R.id.linearLayoutToolbar);

    }


    private void setupNavigationHeader() {
        imageViewProfilePic = (CircleImageView) navigationView.getHeaderView(0).findViewById(R.id.imageViewProfilePic);
        textViewName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textViewName);
        if (!userSessionManager.getLoginMode().equals("0")) {
            Log.i("picturekunsang", userSessionManager.getProfilePic());
            Glide.with(context).load(userSessionManager.getProfilePic()).error(R.drawable.ic_error).into(imageViewProfilePic);
            textViewName.setText(userSessionManager.getName());
        }

    }


    private void showMessage(String s) {
        Toast.makeText(Dashboard.this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupToolBar(String s) {
        setSupportActionBar(toolbar);
        title = (TextView) toolbar.findViewById(R.id.textViewTitle);
        title.setText(s);


    }


    private void setupNavigationView() {
        menu = navigationView.getMenu();
        if (loginmode.equalsIgnoreCase("0")) {
            menu.add("Home").setIcon(R.drawable.ic_home);
            menu.add("Setting").setIcon(R.drawable.ic_setting);
            menu.add("Tutorial").setIcon(R.drawable.ic_clipboard);
            menu.add("Share").setIcon(R.drawable.ic_share);
            menu.add("Rate us").setIcon(R.drawable.ic_rateus);
            menu.add("Login").setIcon(R.drawable.ic_logout);
            imageViewScratch.setVisibility(View.GONE);

        } else {
            menu.add("Home").setIcon(R.drawable.ic_home);
            menu.add("Profile").setIcon(R.drawable.ic_profile_setting);
            menu.add("Chart").setIcon(R.drawable.ic_chart);
            menu.add("Store").setIcon(R.drawable.ic_cart);
            menu.add("Setting").setIcon(R.drawable.ic_setting);
            menu.add("Tutorial").setIcon(R.drawable.ic_clipboard);
            menu.add("Share").setIcon(R.drawable.ic_share);
            menu.add("Rate us").setIcon(R.drawable.ic_rateus);
            menu.add("Logout").setIcon(R.drawable.ic_logout);

        }
        menu.setGroupCheckable(0, true, false);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getTitle().toString()) {

                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case "Home":
                        setupDashboardFragment();
                        return true;

                    case "Profile":
                        setupProfileFragment();
                        return true;
                    case "Chart":
                        setupChartFragment();
                        return true;
                    case "Store":
                        setupPurchaseFragment();
                        return true;
                    case "Setting":
                        setupSettingFragment();
                        return true;
                    case "Tutorial":
                        intent = new Intent(getApplicationContext(), DefaultIntro.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("from", "dashboard");
                        intent.putExtras(bundle);
                        startActivity(intent);
                        return true;
                    case "Share":
                        shareData();
                        return true;
                    case "Rate us":
                        Toast.makeText(getApplicationContext(), "App url... yet to implement", Toast.LENGTH_SHORT).show();
                        return true;
                    case "Logout":

                        LoginManager.getInstance().logOut();
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case "Scratch bonus":
                        //setupScratchFragment();
                        Intent bonus = new Intent(Dashboard.this, ScratchActivity.class);
                        startActivity(bonus);
                        return true;
                    case "Login":
                        Intent login = new Intent(Dashboard.this, LoginActivity.class);
                        startActivity(login);
                        finish();
                        return true;
                    default:
                        return false;

                }
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }

        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, newFragment, tag);
        transaction.commit();
    }

    private void setupDashboardFragment() {
        title.setText("Ibet");
        DashboardFragment dashboardFragment = new DashboardFragment();
        tagName = dashboardFragment.getClass().getName();
        replaceFragment(dashboardFragment, tagName);

    }

    private void setupScratchFragment() {
        title.setText("Scratch Bonus");
        ScratchFragment scratchFragment = new ScratchFragment();
        tagName = scratchFragment.getClass().getName();
        replaceFragment(scratchFragment, tagName);
    }

    private void setupProfileFragment() {

        ProfileFragment profileFragment = new ProfileFragment();
        tagName = profileFragment.getClass().getName();
        replaceFragment(profileFragment, tagName);


    }

    private void shareData() {
        String shareBody = "Ibet... app for football lover";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.shareit)));
    }

    private void setupSettingFragment() {

        SettingFragment settingfragment = new SettingFragment();
        tagName = settingfragment.getClass().getName();
        replaceFragment(settingfragment, tagName);


    }


    private void setupChartFragment() {
        ChartFragment chartfragment = new ChartFragment();
        tagName = chartfragment.getClass().getName();
        replaceFragment(chartfragment, tagName);

    }

    private void setupPurchaseFragment() {
        PurchaseFragment purchaseFragment = new PurchaseFragment();
        tagName = purchaseFragment.getClass().getName();
        replaceFragment(purchaseFragment, tagName);

    }

    //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
    private void showPopUp(String s) {


        confetti_top_right = new ParticleSystem(this, 80, R.drawable.confeti3, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90);
        confetti_top_right.emit(findViewById(R.id.emiter_top_right), 8);


        confetti_top_left = new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90);
        confetti_top_left.emit(findViewById(R.id.emiter_top_left), 8);
        dailog = new MaterialDialog.Builder(Dashboard.this)
                .title("")
                .customView(R.layout.custom_congratulation_dialog, true).build();

        Button b = (Button) dailog.findViewById(R.id.got_it_buttton);
        TextView textView = (TextView) dailog.findViewById(R.id.textView1);

        YoYo.with(Techniques.ZoomIn)
                .duration(700)
                .playOn(dailog.findViewById(R.id.textView1));
        dailog.show();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSessionManager.setIsFirstTime(false);
                confetti_top_left.stopEmitting();
                confetti_top_right.stopEmitting();
                dailog.dismiss();
            }
        });

    }

    private void setupTheme() {
        switch (userSessionManager.getTheme()) {
            case 0:
                // setupDefaultTheme();
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

    private void setupLightBlueTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        navigationView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        navigationView.getHeaderView(0).setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));

    }

    private void setupMarronTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        navigationView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        navigationView.getHeaderView(0).setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));

    }

    private void setupGreenTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        navigationView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        navigationView.getHeaderView(0).setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));

    }

    private void setupPurlpleTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        navigationView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        navigationView.getHeaderView(0).setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));

    }

    private void setupBrownTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        navigationView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        navigationView.getHeaderView(0).setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));

    }

    private void setupDefaultTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        navigationView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        navigationView.getHeaderView(0).setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewScratch:
                drawerLayout.closeDrawers();
                Intent intent = new Intent(context, ScratchActivity.class);
                startActivity(intent);
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


