package com.netforceinfotech.ibet.dashboard.home;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.LruCache;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.StartNewBetActivity;
import com.netforceinfotech.ibet.general.HttpsTrustManager;
import com.netforceinfotech.ibet.general.NukeSSLCerts;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.general.WrapContentViewPager;
import com.netforceinfotech.ibet.login.LoginActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import at.grabner.circleprogress.CircleProgressView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements View.OnClickListener {
    Boolean mShowUnit = true;
    CircleProgressView circleProgressViewStatus, circleProgressViewLevel;
    TextView textViewRemaining;
    CircleImageView circleImageViewDp;
    WrapContentViewPager viewPager;
    private Context context;
    Button buttonStartNewGame;
    CoordinatorLayout coordinatorLayout;
    UserSessionManager userSessionManager;
    int theme;
    private LruCache<String, Bitmap> mMemoryCache;
    String loginmode;
    LinearLayout linearLayout;
    View view1;
    NestedScrollView nestedScrollView;
    private Intent intent;
    RoundCornerProgressBar roundCornerProgressBarWin, roundCornerProgressBarLost;
    TextView textViewName, textViewWins, textViewLose, textviewLevel;
    private TabLayout tabLayout;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NukeSSLCerts.nuke();
        HttpsTrustManager.allowAllSSL();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        loginmode = userSessionManager.getLoginMode();
        initView(view);
        setupTab(view);
        if (!loginmode.equalsIgnoreCase("0")) {
            getProfileDetail(userSessionManager.getCustomerId());
        }

        setupTheme(theme);
        setupBackground(userSessionManager.getBackground());
        return view;
    }

    private void setupBackground(int background) {
        switch (background) {
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

    private void getProfileDetail(String customerId) {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=get_home_by_userid&custid=15
        final String baseUrl = getString(R.string.url);
        String profileUrl = "/services.php?opt=get_home_by_userid&custid=" + customerId;
        String url = baseUrl + profileUrl;
        Log.i("kunsang_url", url);
        /*RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                createMyReqSuccessListener(),
                createMyReqErrorListener());

        queue.add(myReq);*/
        setupSelfSSLCert();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        Log.i("kunsangresponse", result.toString());
                        if (result == null) {
                            showMessage("Something wrong");
                        } else {
                            if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                                JsonArray data = result.getAsJsonArray("data");
                                JsonObject jsonObject = data.get(0).getAsJsonObject();
                                String name = jsonObject.get("name").getAsString();
                                String profile_image = jsonObject.get("profile_image").getAsString();
                                String total_amt = jsonObject.get("total_amt").getAsString();
                                String id = jsonObject.get("id").getAsString();
                                String win = jsonObject.get("cust_win").getAsString();
                                String lose = jsonObject.get("cust_lost").getAsString();
                                String level = jsonObject.get("cust_level").getAsString();
                                userSessionManager.setCustomerId(id);
                                userSessionManager.setName(name);
                                userSessionManager.setProfilePic(profile_image);
                                Picasso.with(context).load(profile_image).error(R.drawable.ic_error).into(circleImageViewDp);
                                Picasso.with(context).load(profile_image).error(R.drawable.ic_error).into(Dashboard.imageViewProfilePic);
                                textViewName.setText(name);
                                Dashboard.textViewName.setText(name);
                                setupCoin(total_amt);
                                textviewLevel.setText(level);
                                textViewWins.setText(win);
                                textViewLose.setText(lose);

                            } else {
                                showMessage("Authentication failure. Login again");
                            }
                        }

                    }
                });
    }


    public void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    private Response.Listener<JSONObject> createMyReqSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("kunsangresponse", response.toString());
                try {
                    if (response.getString("status").equalsIgnoreCase("Success")) {
                        JSONArray data = response.getJSONArray("data");
                        JSONObject object = data.getJSONObject(0);
                        String name = object.getString("name");
                        String profile_image = object.getString("profile_image");
                        String total_amt = object.getString("total_amt");
                        String id = object.getString("id");
                        userSessionManager.setCustomerId(id);
                        userSessionManager.setName(name);
                        userSessionManager.setProfilePic(profile_image);
                        Picasso.with(context).load(profile_image).error(R.drawable.ic_error).into(circleImageViewDp);
                        textViewName.setText(name);
                        setupCoin(total_amt);

                    } else {
                        showMessage("Authentication failure. Login again");
                    }
                } catch (Exception ex) {

                }
            }
        };

    }

    private void setupCoin(String total_amt) {
        Toolbar toolbar = (Toolbar) ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar);
        TextView textView = (TextView) toolbar.findViewById(R.id.textViewCoins);
        textView.setText(total_amt);
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                linearLayout.setVisibility(View.GONE);
                error.printStackTrace();
            }
        };
    }


    private void initView(View view) {
        view1 = view.findViewById(R.id.view);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        roundCornerProgressBarLost = (RoundCornerProgressBar) view.findViewById(R.id.progressBarLost);
        roundCornerProgressBarWin = (RoundCornerProgressBar) view.findViewById(R.id.progressBarWin);
        textViewWins = (TextView) view.findViewById(R.id.textViewWins);
        textViewLose = (TextView) view.findViewById(R.id.textViewLosses);
        textviewLevel = (TextView) view.findViewById(R.id.textViewLevelNumber);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestedscrollview);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        view.findViewById(R.id.buttonLogin).setOnClickListener(Home.this);
        if (loginmode.equalsIgnoreCase("0")) {
            linearLayout.setVisibility(View.VISIBLE);
            nestedScrollView.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.VISIBLE);
        }
        circleImageViewDp = (CircleImageView) view.findViewById(R.id.circleImageViewDp);
        circleProgressViewStatus = (CircleProgressView) view.findViewById(R.id.cpvstatus);
        circleProgressViewLevel = (CircleProgressView) view.findViewById(R.id.cpvLevel);
        textViewRemaining = (TextView) view.findViewById(R.id.textViewRemaining);
        buttonStartNewGame = (Button) view.findViewById(R.id.buttonStartnewBet);
        buttonStartNewGame.setOnClickListener(this);
        circleProgressViewLevel.setOnProgressChangedListener(new CircleProgressView.OnProgressChangedListener() {

            @Override
            public void onProgressChanged(float value) {
                Log.i("ibetchange", value + "");
                textViewRemaining.setText(value + "%\nto next level");
            }


        });
        circleProgressViewLevel.setValueAnimated(88f, 1500);
        circleProgressViewStatus.setValueAnimated(35f, 1500);
        String fbId = userSessionManager.getFBID();
        Log.i("ibet_fbid", fbId);
        String imageURL = "https://graph.facebook.com/" + fbId + "/picture?type=large";
        Picasso.with(context)
                .load(imageURL)
                .placeholder(R.drawable.david)
                .error(R.drawable.david)
                .into(circleImageViewDp);

    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      /*  stikkyHeader = StikkyHeaderBuilder.stickTo(recyclerView_Commom);
        stikkyHeader.setHeader(R.id.header, (ViewGroup) getView())
                .minHeightHeaderDim(R.dimen.min_height_header)
                .animator(new ParallaxStikkyAnimator())
                .build();*/
    }

    private void setupTab(View view) {
        viewPager = (WrapContentViewPager) view.findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.finished_bet));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.bets_to_join));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonStartnewBet:
                //go to new bet
                intent = new Intent(context, StartNewBetActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonLogin:
                intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
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

    private void setupTheme(int theme) {
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

    private void setupLightBlueTheme() {
        coordinatorLayout.setBackgroundResource(R.color.colorPrimaryLightBlue);
        circleProgressViewLevel.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        circleProgressViewLevel.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlueLight));
        circleProgressViewLevel.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        circleProgressViewLevel.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        circleProgressViewLevel.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        textViewWins.setBackgroundResource(R.drawable.circular_bg_lightblue);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_lightblue);

        //circleProgressViewStatus
        circleProgressViewStatus.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        circleProgressViewStatus.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlueLight));
        circleProgressViewStatus.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        circleProgressViewStatus.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        circleProgressViewStatus.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));

        buttonStartNewGame.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));

        tabLayout.setBackgroundResource(R.color.colorPrimaryDarkLightBlue);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));

        roundCornerProgressBarLost.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlueLight));
        roundCornerProgressBarWin.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlueLight));
    }

    private void setupMarronTheme() {

        coordinatorLayout.setBackgroundResource(R.color.colorPrimaryMarron);
        circleProgressViewLevel.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        circleProgressViewLevel.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightMaron));
        circleProgressViewLevel.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        circleProgressViewLevel.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        circleProgressViewLevel.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));

        textViewWins.setBackgroundResource(R.drawable.circular_bg_marron);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_marron);

        //circleProgressViewStatus
        circleProgressViewStatus.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        circleProgressViewStatus.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightMaron));
        circleProgressViewStatus.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        circleProgressViewStatus.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        circleProgressViewStatus.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));

        buttonStartNewGame.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));

        tabLayout.setBackgroundResource(R.color.colorPrimaryDarkMarron);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentMarron));

        roundCornerProgressBarLost.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightMaron));
        roundCornerProgressBarWin.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightMaron));
    }

    private void setupGreenTheme() {

        coordinatorLayout.setBackgroundResource(R.color.colorPrimaryGreen);

        circleProgressViewLevel.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        circleProgressViewLevel.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightGreen));
        circleProgressViewLevel.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        circleProgressViewLevel.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        circleProgressViewLevel.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));

        textViewWins.setBackgroundResource(R.drawable.circular_bg_green);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_green);

        //circleProgressViewStatus
        circleProgressViewStatus.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        circleProgressViewStatus.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightGreen));
        circleProgressViewStatus.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        circleProgressViewStatus.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        circleProgressViewStatus.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));

        buttonStartNewGame.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

        tabLayout.setBackgroundResource(R.color.colorPrimaryDarkGreen);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

        roundCornerProgressBarLost.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightGreen));
        roundCornerProgressBarWin.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightGreen));
    }

    private void setupPurlpleTheme() {

        coordinatorLayout.setBackgroundResource(R.color.colorPrimaryPurple);

        circleProgressViewLevel.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        circleProgressViewLevel.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightPurple));
        circleProgressViewLevel.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        circleProgressViewLevel.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        circleProgressViewLevel.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));

        textViewWins.setBackgroundResource(R.drawable.circular_bg_purlple);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_purlple);

        //circleProgressViewStatus
        circleProgressViewStatus.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        circleProgressViewStatus.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightPurple));
        circleProgressViewStatus.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        circleProgressViewStatus.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        circleProgressViewStatus.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));

        buttonStartNewGame.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));

        tabLayout.setBackgroundResource(R.color.colorPrimaryDarkPurple);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentPurple));

        roundCornerProgressBarLost.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightPurple));
        roundCornerProgressBarWin.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightPurple));
    }

    private void setupBrownTheme() {

        coordinatorLayout.setBackgroundResource(R.color.colorPrimaryBrown);

        circleProgressViewLevel.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        circleProgressViewLevel.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBrown));
        circleProgressViewLevel.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        circleProgressViewLevel.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        circleProgressViewLevel.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));

        textViewWins.setBackgroundResource(R.drawable.circular_bg_brown);
        textViewLose.setBackgroundResource(R.drawable.circular_bg_brown);

        //circleProgressViewStatus
        circleProgressViewStatus.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        circleProgressViewStatus.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBrown));
        circleProgressViewStatus.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        circleProgressViewStatus.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        circleProgressViewStatus.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));

        buttonStartNewGame.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));

        tabLayout.setBackgroundResource(R.color.colorPrimaryDarkBrown);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccentBrown));

        roundCornerProgressBarLost.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBrown));
        roundCornerProgressBarWin.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBrown));
    }

    private void setupDefaultTheme() {

        coordinatorLayout.setBackgroundResource(R.color.colorPrimary);

        circleProgressViewLevel.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        circleProgressViewLevel.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
        circleProgressViewLevel.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        circleProgressViewLevel.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        circleProgressViewLevel.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

        textViewWins.setBackgroundResource(R.drawable.circular_bg);
        textViewLose.setBackgroundResource(R.drawable.circular_bg);

        //circleProgressViewStatus
        circleProgressViewStatus.setRimColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        circleProgressViewStatus.setBarColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
        circleProgressViewStatus.setSpinBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        circleProgressViewStatus.setContourColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        circleProgressViewStatus.setFillCircleColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

        buttonStartNewGame.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));

        tabLayout.setBackgroundResource(R.color.colorPrimaryDark);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(context, R.color.colorAccent));

        roundCornerProgressBarLost.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
        roundCornerProgressBarWin.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
    }

}
