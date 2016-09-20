package com.netforceinfotech.ibet.dashboard.home.detail_bet_to_join;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kyleduo.switchbutton.SwitchButton;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.CreateBet;
import com.squareup.picasso.Picasso;

public class WhoWillWinActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Toolbar toolbar;
    ImageView imageViewTeamA, imageViewTeamB, imageViewHomeIncrement, imageViewHomeDecrement, imageViewAwayIncrement, imageViewAwayDecrement;
    RelativeLayout relativeLayoutBetAmount;
    TextView textViewBetamount, textViewTeamA, textViewTeamB, textviewselectHome, textviewselectDraw, textviewselectAway, textViewScoreHome, textViewScoreAway;
    String matchid;
    private String betamountString;
    private EditText editTextPopupBetAmount;
    String teama, teamb, teamalogo, teamblogo;
    int homescore = 0, awayscore = 0;
    RadioButton radioButtonHome, radioButtonDraw, radioButtonAway;
    public static String selectedteam, stringbetoption = "0";
    SwitchButton switchButton;
    CheckBox checkBoxScore, checkBoxTeam;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_will_win);
        Bundle bundle = getIntent().getExtras();
        context = this;
        try {
            matchid = bundle.getString("matchid");
            teama = bundle.getString("teamaname");
            teama = bundle.getString("teambname");
            teamalogo = bundle.getString("teamalogo");
            teamblogo = bundle.getString("teamblogo");
            Log.i("kunsangbundle", matchid + ":" + teama + ":" + teamb + ":" + teamalogo + ":" + teamblogo);

        } catch (Exception ex) {
            showMessage("Bundle error");
        }
        setupToolBar("Bet");
        initView();
        checkBoxTeam.setChecked(true);
        checkBoxScore.setChecked(false);
        Button next = (Button) findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(WhoWillWinActivity.this, CreateBet.class);
                startActivity(i);

            }
        });
        try {
            Picasso.with(context).load(teamalogo).error(R.drawable.ic_error).into(imageViewTeamA);
        } catch (Exception ex) {
            Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamA);
        }
        try {
            Picasso.with(context).load(teamblogo).error(R.drawable.ic_error).into(imageViewTeamB);
        } catch (Exception ex) {
            Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamB);
        }
        textViewTeamA.setText(teama);
        textViewTeamB.setText(teamb);

    }

    private void showMessage(String s) {
        Toast.makeText(WhoWillWinActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        checkBoxScore = (CheckBox) findViewById(R.id.checkboxscore);
        checkBoxTeam = (CheckBox) findViewById(R.id.checkboxteam);
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
        radioButtonAway.setOnCheckedChangeListener(this);
        radioButtonHome.setOnCheckedChangeListener(this);
        radioButtonDraw.setOnCheckedChangeListener(this);
        checkBoxScore.setOnCheckedChangeListener(this);
        checkBoxTeam.setOnCheckedChangeListener(this);
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
        MaterialDialog dialogbox = new MaterialDialog.Builder(WhoWillWinActivity.this)
                .title("Set bet amount")
                .customView(R.layout.setbetamount, wrapInScrollView)
                .positiveText("Set").onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        betamountString = editTextPopupBetAmount.getText().toString();
                        textViewBetamount.setText(betamountString);
                    }
                })
                .show();
        editTextPopupBetAmount = (EditText) dialogbox.findViewById(R.id.editText);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
                textViewScoreHome.setText("" + homescore);
                break;
            case R.id.imageViewAwayDecrement:
                awayscore--;
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
                    checkBoxScore.setChecked(true);
                    checkBoxTeam.setChecked(true);
                }
                break;
            case R.id.checkboxscore:
                if (b) {
                    stringbetoption = "1";
                    if (checkBoxTeam.isChecked()) {
                        stringbetoption = "2";
                        switchButton.setChecked(true);
                    }
                } else {
                    stringbetoption = "0";
                    checkBoxTeam.setChecked(true);
                }
                break;
            case R.id.checkboxteam:
                if (b) {
                    stringbetoption = "0";
                    if (checkBoxScore.isChecked()) {
                        stringbetoption = "2";
                        switchButton.setChecked(true);
                    }
                } else {
                    stringbetoption = "1";
                    checkBoxScore.setChecked(true);
                }
                break;
        }

    }
}
