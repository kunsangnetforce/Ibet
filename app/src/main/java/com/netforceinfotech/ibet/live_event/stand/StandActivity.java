package com.netforceinfotech.ibet.live_event.stand;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.StartNewBetActivity;
import com.netforceinfotech.ibet.general.CustomViewPager;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.general.WrapContentViewPager;
import com.netforceinfotech.ibet.live_event.CurrentGameData;
import com.netforceinfotech.ibet.live_event.thearena.PagerAdapterBetTheArena;
import com.netforceinfotech.ibet.live_event.thearena.all.AllFragment;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class StandActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "kunsangscroll";
    CustomViewPager viewPager;
    private Context context;

    CoordinatorLayout coordinatorLayout;
    UserSessionManager userSessionManager;
    int theme;
    private TheArenaFragment theArenaFragment;
    CircleImageView imageViewTeamA, imageViewTeamB;
    private String tagName;
    private Toolbar toolbar;
    String teama, teamb, teamaid, teambid, team, matchid;
    private DatabaseReference root, _matchid, _team;
    private String userid;
    NestedScrollView nestedScrollView;
    EditText editText;
    private boolean nestedbottom = false;
    private InputMethodManager imm;
    private int tabposition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand);


        context = getApplicationContext();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        userSessionManager = new UserSessionManager(context);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        editText = (EditText) findViewById(R.id.editText);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedscrollview);
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
                int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
                nestedbottom = false;
                if (diff == 0) {
                    // do stuff
                    showMessage("end");
                }
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (!nestedbottom) {
                        nestedScrollView.post(new Runnable()

                        {
                            public void run() {
                                nestedScrollView.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                        nestedbottom = !nestedbottom;
                        editText.requestFocus();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "lost the focus", Toast.LENGTH_LONG).show();
                }
            }
        });


        root = FirebaseDatabase.getInstance().getReference().getRoot();
        Bundle bundle = getIntent().getExtras();
        try {
            userid = userSessionManager.getCustomerId();
            team = bundle.getString("team");
            teama = bundle.getString("teama");
            teamb = bundle.getString("teamb");
            teamaid = bundle.getString("teamaid");
            teambid = bundle.getString("teambid");
            matchid = bundle.getString("matchid");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        findViewById(R.id.imageViewSend).setOnClickListener(this);
        imageViewTeamA = (CircleImageView) findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (CircleImageView) findViewById(R.id.imageViewTeamB);
        Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamA);
        Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamB);
        theme = userSessionManager.getTheme();
        setupToolBar(teama + " vs " + teamb);
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

    }

    private void setupTab() {


        viewPager = (CustomViewPager) findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.all));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.top));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.friends));
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

        final PagerAdapterBetTheArena adapter = new PagerAdapterBetTheArena
                (getSupportFragmentManager(), tabLayout.getTabCount(), team, teama, teamb, teamaid, teambid, matchid);
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabposition = tab.getPosition();
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
                Intent intent = new Intent(context, StartNewBetActivity.class);
                startActivity(intent);
                break;
            case R.id.imageViewSend:
                switch (tabposition) {
                    case 0:
                        if (editText.getText().length() > 0) {
                            AllFragment.sendMessage(editText.getText().toString());

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
}