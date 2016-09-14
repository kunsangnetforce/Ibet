package com.netforceinfotech.ibet.dashboard.home;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.StartNewBetActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.general.WrapContentViewPager;
import com.netforceinfotech.ibet.login.LoginActivity;
import com.squareup.picasso.Picasso;

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
    NestedScrollView nestedScrollView;
    private Intent intent;

    public Home() {
        // Required empty public constructor
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
        return view;
    }

    private void initView(View view) {
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

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);

        viewPager = (WrapContentViewPager) view.findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.finished_bet));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.bets_to_join));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        if (theme == 0) {
            //coordinatorLayout.setBackgroundResource(R.drawable.background_theme5);
            coordinatorLayout.setBackgroundResource(R.drawable.background_theme1);

        } else if (theme == 1) {

            coordinatorLayout.setBackgroundResource(R.drawable.background_theme2);

        } else if (theme == 2) {

            coordinatorLayout.setBackgroundResource(R.drawable.background_theme3);

        } else if (theme == 3) {


            coordinatorLayout.setBackgroundResource(R.drawable.background_theme4);


        } else if (theme == 4) {


            coordinatorLayout.setBackgroundResource(R.drawable.background_theme5);


        }

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
}
