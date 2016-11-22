package com.netforceinfotech.ibet1.dashboard.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.Dashboard;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.StartNewBetActivity;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.netforceinfotech.ibet1.general.WrapContentViewPager;
import com.netforceinfotech.ibet1.login.LoginActivity;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

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
    String loginmode;
    LinearLayout linearLayout;
    View view1;
    NestedScrollView nestedScrollView;
    private Intent intent;
    RoundCornerProgressBar roundCornerProgressBarWin, roundCornerProgressBarLost;
    TextView textViewName, textViewWins, textViewLose, textviewLevelNumber, textViewLevel;
    private TabLayout tabLayout;
    SwipyRefreshLayout swipyRefreshLayout;
    private PagerAdapter adapter;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
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
        Debugger.i("kunsang_url_getprofiledetail", url);

        setupSelfSSLCert();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
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
                                String wins = jsonObject.get("wincount").getAsString();
                                String losses = jsonObject.get("losscount").getAsString();
                                String level = jsonObject.get("cust_level").getAsString();
                                try {
                                    String levelString = jsonObject.get("level_string").getAsString();
                                    textViewLevel.setText(levelString);
                                } catch (Exception ex) {
                                    textViewLevel.setText("Beginer");
                                }
                                try {
                                    float level_remaining = jsonObject.get("level_remaining").getAsFloat();
                                    circleProgressViewLevel.setValueAnimated(level_remaining, 1500);

                                } catch (Exception ex) {
                                    circleProgressViewLevel.setValueAnimated(0f, 1500);

                                }
                                userSessionManager.setCustomerId(id);
                                userSessionManager.setName(name);
                                userSessionManager.setProfilePic(profile_image);
                                Glide.with(context).load(profile_image).placeholder(R.drawable.ic_circle_filled).error(R.drawable.ic_error).dontAnimate().into(circleImageViewDp);
                                textViewName.setText(name);
                                Dashboard.textViewName.setText(name);
                                textviewLevelNumber.setText("Level\n" + level);
                                textViewWins.setText(wins);
                                textViewLose.setText(losses);
                                setupWinLose(wins, losses);

                            } else {
                                showMessage("Authentication failure. Login again");
                            }
                        }

                    }
                });
    }

    private void setupWinLose(String win, String lose) {
        int win_count = 0, lose_count = 0;
        win_count = Integer.parseInt(win);
        lose_count = Integer.parseInt(lose);
        int total = win_count + lose_count;
        if (total == 0) {
            return;
        }
        float winPercentage = (win_count / total) * 100;
        float losePercentage = (lose_count / total) * 100;
        roundCornerProgressBarWin.setProgress(winPercentage);
        roundCornerProgressBarLost.setProgress(losePercentage);
        //        circleProgressViewStatus.setValueAnimated(-12f);
        float totalPercentage = ((win_count - lose_count) / total) * 100;
        circleProgressViewStatus.setValueAnimated(totalPercentage);
    }


    public void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void initView(View view) {
        view1 = view.findViewById(R.id.view);
        swipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                adapter.notifyDataSetChanged();
                swipyRefreshLayout.setRefreshing(false);
            }
        });
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        roundCornerProgressBarLost = (RoundCornerProgressBar) view.findViewById(R.id.progressBarLost);
        roundCornerProgressBarWin = (RoundCornerProgressBar) view.findViewById(R.id.progressBarWin);
        textViewWins = (TextView) view.findViewById(R.id.textViewWins);
        textViewLose = (TextView) view.findViewById(R.id.textViewLosses);
        textviewLevelNumber = (TextView) view.findViewById(R.id.textViewLevelNumber);
        textViewLevel = (TextView) view.findViewById(R.id.textViewLevel);
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
        textViewRemaining.setText(0 + "%\nto next level");
        circleProgressViewLevel.setOnProgressChangedListener(new CircleProgressView.OnProgressChangedListener() {

            @Override
            public void onProgressChanged(float value) {
                Log.i("ibetchange", value + "");
                textViewRemaining.setText(value + "%\nto next level");
            }


        });
        circleProgressViewLevel.setValueAnimated(0f, 1500);
        circleProgressViewStatus.setValueAnimated(0f, 1500);
        String fbId = userSessionManager.getFBID();
        Log.i("ibet_fbid", fbId);

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

        adapter = new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());

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
