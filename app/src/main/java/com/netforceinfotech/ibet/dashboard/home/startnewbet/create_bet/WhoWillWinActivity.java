package com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
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
import com.kyleduo.switchbutton.SwitchButton;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.squareup.picasso.Picasso;

public class WhoWillWinActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Toolbar toolbar;
    ImageView imageViewTeamA, imageViewTeamB, imageViewHomeIncrement, imageViewHomeDecrement, imageViewAwayIncrement, imageViewAwayDecrement;
    RelativeLayout relativeLayoutBetAmount, relativeLayoutScore, relativeLayoutTeam;
    TextView textViewBetamount, textViewTeamA, textViewTeamB, textviewselectHome, textviewselectDraw, textviewselectAway, textViewScoreHome, textViewScoreAway;
    String match_id;
    double betamount = 0;
    private EditText editTextPopupBetAmount;
    String home_name, home_logo, away_logo, away_name, home_id, away_id;
    int homescore = 0, awayscore = 0;
    RadioButton radioButtonHome, radioButtonDraw, radioButtonAway;
    public static String selectedteam = "", stringbetoption = "0";
    SwitchButton switchButton;
    RadioButton radiobuttonScore, radiobuttonTeam;
    Context context;
    View viewTeam, viewScore;
    private MaterialDialog dialogbox;
    CoordinatorLayout coordinatorLayout;
    private UserSessionManager userSessionManager;
    private View view1;
    LinearLayout linearLayoutHome, linearLayoutAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_will_win);
        final Bundle bundle = getIntent().getExtras();
        context = this;
        userSessionManager = new UserSessionManager(this);
        try {
            match_id = bundle.getString("match_id");
            home_name = bundle.getString("home_name");
            away_name = bundle.getString("away_name");
            home_logo = bundle.getString("home_logo");
            away_logo = bundle.getString("away_logo");
            home_id = bundle.getString("home_id");
            away_id = bundle.getString("away_id");

        } catch (Exception ex) {
            showMessage("Bundle error");
        }
        setupToolBar(home_name + " vs " + away_name);
        initView();
        setupTheme();
        setupBackbround();

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

    private boolean validate() {
        if (betamount == 0) {
            showMessage("enter bet amount");
            return false;
        }
        if (stringbetoption.equalsIgnoreCase("0")) {
            if (selectedteam.equalsIgnoreCase("")) {
                showMessage("select a team");
                return false;
            }
        }
        return true;
    }

    private void showMessage(String s) {
        Toast.makeText(WhoWillWinActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        linearLayoutAway = (LinearLayout) findViewById(R.id.linearLayoutAway);
        linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayoutHome);
        view1 = findViewById(R.id.view);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        relativeLayoutScore = (RelativeLayout) findViewById(R.id.relativeLayoutScore);
        relativeLayoutTeam = (RelativeLayout) findViewById(R.id.relativeLayoutTeam);
        relativeLayoutTeam.setOnClickListener(this);
        relativeLayoutScore.setOnClickListener(this);
        viewScore = findViewById(R.id.viewScore);
        viewTeam = findViewById(R.id.viewTeam);
        viewTeam.setOnClickListener(this);
        viewScore.setOnClickListener(this);
        radiobuttonScore = (RadioButton) findViewById(R.id.radiobuttonScore);
        radiobuttonTeam = (RadioButton) findViewById(R.id.radiobuttonTeam);
        switchButton = (SwitchButton) findViewById(R.id.switchJoin);
        relativeLayoutBetAmount = (RelativeLayout) findViewById(R.id.relativeLayoutBetAmount);
        imageViewTeamA = (ImageView) findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (ImageView) findViewById(R.id.imageViewTeamB);
        imageViewHomeIncrement = (ImageView) findViewById(R.id.imageViewHomeIncrement);
        imageViewHomeDecrement = (ImageView) findViewById(R.id.imageViewHomeDecrement);
        imageViewAwayIncrement = (ImageView) findViewById(R.id.imageviewAwayincrement);
        imageViewAwayDecrement = (ImageView) findViewById(R.id.imageViewAwayDecrement);
        textViewTeamA = (TextView) findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) findViewById(R.id.textViewTeamB);
        textviewselectHome = (TextView) findViewById(R.id.textviewselectHome);
        textviewselectDraw = (TextView) findViewById(R.id.textviewselectDraw);
        textviewselectAway = (TextView) findViewById(R.id.textviewselectAway);
        textViewScoreHome = (TextView) findViewById(R.id.textViewHomeScore);
        textViewScoreAway = (TextView) findViewById(R.id.textViewAwayScore);
        textViewBetamount = (TextView) findViewById(R.id.textViewBetamount);
        radioButtonAway = (RadioButton) findViewById(R.id.radioTeamb);
        radioButtonDraw = (RadioButton) findViewById(R.id.radioDraw);
        radioButtonHome = (RadioButton) findViewById(R.id.radioTeama);
        radiobuttonTeam.setChecked(true);
        radiobuttonScore.setChecked(false);
        Button next = (Button) findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Intent i = new Intent(WhoWillWinActivity.this, CreateBet.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("betoption", stringbetoption);
                    bundle1.putInt("homescore", homescore);
                    bundle1.putInt("awayscore", awayscore);
                    bundle1.putString("selectedteam", selectedteam);
                    bundle1.putDouble("betamount", betamount);
                    bundle1.putString("home_name", home_name);
                    bundle1.putString("away_name", away_name);
                    bundle1.putString("match_id", match_id);
                    bundle1.putString("home_id", home_id);
                    bundle1.putString("away_id", away_id);
                    i.putExtras(bundle1);
                    startActivity(i);
                }

            }
        });
        try {
            Picasso.with(context).load(home_logo).error(R.drawable.ic_error).into(imageViewTeamA);
        } catch (Exception ex) {
            Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamA);
        }
        try {
            Picasso.with(context).load(away_logo).error(R.drawable.ic_error).into(imageViewTeamB);
        } catch (Exception ex) {
            Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamB);
        }
        textViewTeamA.setText(home_name);
        textViewTeamB.setText(away_name);

        radioButtonAway.setOnCheckedChangeListener(this);
        radioButtonHome.setOnCheckedChangeListener(this);
        radioButtonDraw.setOnCheckedChangeListener(this);
        radiobuttonScore.setOnCheckedChangeListener(this);
        radiobuttonTeam.setOnCheckedChangeListener(this);
        switchButton.setOnCheckedChangeListener(this);
        imageViewAwayIncrement.setOnClickListener(this);
        imageViewHomeIncrement.setOnClickListener(this);
        imageViewAwayDecrement.setOnClickListener(this);
        imageViewHomeDecrement.setOnClickListener(this);
        textviewselectAway.setOnClickListener(this);
        textviewselectHome.setOnClickListener(this);
        textviewselectDraw.setOnClickListener(this);
        relativeLayoutBetAmount.setOnClickListener(this);
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
        dialogbox = new MaterialDialog.Builder(WhoWillWinActivity.this)
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
            case R.id.relativeLayoutScore:
            case R.id.viewScore:
                radiobuttonScore.setChecked(true);
                break;
            case R.id.relativeLayoutTeam:
            case R.id.viewTeam:
                radiobuttonTeam.setChecked(true);
                break;
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
            case R.id.relativeLayoutBetAmount:
                showPopup();
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.radioDraw:
                if (b) {
                    textviewselectDraw.setBackgroundResource(R.drawable.circular_bg_fill);
                    selectedteam = "D";
                } else {
                    textviewselectDraw.setBackgroundResource(R.drawable.circular_bg);
                }
                break;
            case R.id.radioTeama:
                if (b) {
                    textviewselectHome.setBackgroundResource(R.drawable.circular_bg_fill);
                    selectedteam = "H";
                } else {
                    textviewselectHome.setBackgroundResource(R.drawable.circular_bg);
                }
                break;
            case R.id.radioTeamb:
                if (b) {
                    textviewselectAway.setBackgroundResource(R.drawable.circular_bg_fill);
                    selectedteam = "A";
                } else {
                    textviewselectAway.setBackgroundResource(R.drawable.circular_bg);
                }
                break;
            case R.id.switchJoin:
                if (b) {
                    viewScore.setVisibility(View.GONE);
                    viewTeam.setVisibility(View.GONE);
                    radiobuttonScore.setChecked(true);
                    radiobuttonTeam.setChecked(true);
                } else {
                    viewTeam.setVisibility(View.GONE);
                    viewScore.setVisibility(View.VISIBLE);
                    radiobuttonTeam.setChecked(true);
                    radiobuttonScore.setChecked(false);
                }
                break;
            case R.id.radiobuttonScore:
                if (switchButton.isChecked()) {
                    viewTeam.setVisibility(View.GONE);
                    viewScore.setVisibility(View.GONE);
                    stringbetoption = "2";

                } else {
                    if (b) {
                        radiobuttonTeam.setChecked(false);
                        stringbetoption = "1";
                        viewScore.setVisibility(View.GONE);
                        viewTeam.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.radiobuttonTeam:
                if (switchButton.isChecked()) {
                    viewTeam.setVisibility(View.GONE);
                    viewScore.setVisibility(View.GONE);
                    stringbetoption = "2";

                } else {
                    if (b) {
                        radiobuttonScore.setChecked(false);
                        stringbetoption = "0";
                        viewScore.setVisibility(View.VISIBLE);
                        viewTeam.setVisibility(View.GONE);
                    }
                }
                break;
        }

    }
}
