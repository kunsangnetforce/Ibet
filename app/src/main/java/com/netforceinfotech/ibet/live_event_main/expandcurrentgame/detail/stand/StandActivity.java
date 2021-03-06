package com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.StartNewBetActivity;
import com.netforceinfotech.ibet.general.CustomViewPager;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.PagerAdapterMainChat;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.all.AllAdapter;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.all.AllFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class StandActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "kunsangscroll";
    CustomViewPager viewPager;
    private Context context;

    CoordinatorLayout coordinatorLayout;
    UserSessionManager userSessionManager;
    int theme;
    CircleImageView imageViewTeamA, imageViewTeamB;
    private String tagName;
    private Toolbar toolbar;
    String home_name, away_name, home_id, away_id, team, match_id, home_logo, away_logo;
    private String userid;
    NestedScrollView nestedScrollView;
    EditText editText;
    private boolean nestedbottom = false;
    private InputMethodManager imm;
    private int tabposition = 0;
    public static LinearLayout linearLayoutInput;
    public static boolean chatloaded = false;
    Long homefancount, awayfancount;
    public static TextView textViewHomeFan, textViewAwayFan;
    View view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand);
        context = getApplicationContext();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        userSessionManager = new UserSessionManager(context);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        view1 = findViewById(R.id.view);
        editText = (EditText) findViewById(R.id.editText);
        linearLayoutInput = (LinearLayout) findViewById(R.id.linearLayoutInput);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedscrollview);
        imageViewTeamA = (CircleImageView) findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (CircleImageView) findViewById(R.id.imageViewTeamB);
        textViewAwayFan = (TextView) findViewById(R.id.textViewAwayFan);
        textViewHomeFan = (TextView) findViewById(R.id.textViewHomeFan);

        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
                int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
                nestedbottom = false;
                if (diff == 0) {
                    // do stuff

                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                              @Override
                                              public void onFocusChange(View v, boolean hasFocus) {
                                                  if (hasFocus) {
                                                      nestedbottom = !nestedbottom;
                                                  }
                                              }
                                          }

        );


        Bundle bundle = getIntent().getExtras();
        try

        {
            userid = userSessionManager.getCustomerId();
            team = bundle.getString("team");
            home_name = bundle.getString("home_name");
            away_name = bundle.getString("away_name");
            home_id = bundle.getString("away_id");
            away_id = bundle.getString("away_id");
            match_id = bundle.getString("match_id");
            home_logo = bundle.getString("home_logo");
            away_logo = bundle.getString("away_logo");
        } catch (
                Exception ex
                )

        {
            ex.printStackTrace();

        }
        try {
            Glide.with(context) .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.home_logo).error(R.drawable.ic_error).into(imageViewTeamA);
        } catch (Exception ex) {

        }
        try {
            Glide.with(context) .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo).error(R.drawable.ic_error).into(imageViewTeamB);
        } catch (Exception ex) {

        }

        findViewById(R.id.imageViewSend).setOnClickListener(this);
        theme = userSessionManager.getTheme();
        setupToolBar(home_name + " vs " + away_name);
        setupTab();

    }


    private void showMessage(String s) {
        Toast.makeText(StandActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);
        setupTheme();
        setupBackground();

    }

    private void setupTab() {


        viewPager = (CustomViewPager) findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.all));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.top));
        //tabLayout.addTab(tabLayout.newTab().setText(R.string.friends));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final PagerAdapterMainChat adapter = new PagerAdapterMainChat
                (getSupportFragmentManager(), tabLayout.getTabCount(), team, home_name, away_name, home_id, away_id, match_id, home_logo, away_logo);
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabposition = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {
                    linearLayoutInput.setVisibility(View.GONE);
                } else {
                    if (chatloaded) {
                        linearLayoutInput.setVisibility(View.VISIBLE);
                    }
                }

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
                Intent intent = new Intent(context, StartNewBetActivity.class);
                startActivity(intent);
                break;
            case R.id.imageViewSend:
                switch (tabposition) {
                    case 0:
                        if (editText.getText().length() > 0) {
                            AllFragment.sendMessage(editText.getText().toString());
                            editText.setText("");

                        } else {
                            showMessage("enter text");
                        }
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
                if (editText.isFocused()) {
                    editText.clearFocus();
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                break;
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AllAdapter.SHARE_INTENT) {
            if (resultCode == RESULT_OK) {

            }
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
        linearLayoutInput.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
    }


    private void setupPurlpleTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        linearLayoutInput.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setupGreenTheme() {
        linearLayoutInput.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setupMarronTheme() {
        linearLayoutInput.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        linearLayoutInput.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
    }

    private void setupDefaultTheme() {
        linearLayoutInput.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

    }

}