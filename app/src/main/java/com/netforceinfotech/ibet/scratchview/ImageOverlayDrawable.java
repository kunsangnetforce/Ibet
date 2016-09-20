package com.netforceinfotech.ibet.scratchview;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.netforceinfotech.ibet.R;
import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.AccelerationModifier;
import com.plattysoft.leonids.modifiers.ParticleModifier;
import com.winsontan520.WScratchView;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import pl.droidsonroids.gif.GifImageView;
import tyrantgit.explosionfield.ExplosionField;

public class ImageOverlayDrawable extends AppCompatActivity implements View.OnClickListener {

    WScratchView scratchView0, scratchView1, scratchView2, scratchView3, scratchView4, scratchView5, scratchView6, scratchView7, scratchView8;
    TextView percentageView;
    float mPercentage;
    private MaterialDialog.Builder dialog;
    Toolbar toolbar;
    LinearLayout bonus_layout;
    ArrayList<ScratchData> scratchDatas = new ArrayList<>();
    ArrayList<Boolean> enables = new ArrayList<>();
    ArrayList<Boolean> revealed = new ArrayList<>();
    ArrayList<Integer> sameKindCount = new ArrayList<>();
    Button buttonColloect;
    View view;
    private ExplosionField mExplosionField;
    LinearLayout linearLayoutScratch;
    private GifImageView gif;
    private MaterialDialog customdialog;
    private ParticleSystem confetti_top_right;
    private ParticleSystem confetti_top_left;
    private ParticleSystem confetti;
    RelativeLayout relativeLayoutCounter;
    TextView textViewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scratch_data);
        setScratchData();
        for (int i = 0; i < 9; i++) {
            enables.add(true);
            revealed.add(false);
        }
        for (int i = 0; i < 9; i++) {
            sameKindCount.add(0);
        }
        setupToolBar("Scratch Bonus");
        // updatePercentage(0f);
        linearLayoutScratch = (LinearLayout) findViewById(R.id.linearLayoutScratch);
        setupView();
    }

    private void setupView() {
        textViewCounter = (TextView) findViewById(R.id.textViewCounter);
        relativeLayoutCounter = (RelativeLayout) findViewById(R.id.relativeLayoutCounter);
        relativeLayoutCounter.setVisibility(View.GONE);
        gif = (GifImageView) findViewById(R.id.gif);
        dialog = new MaterialDialog.Builder(this)
                .title("You can Choose Only Three Bonus")
                .content("Hi")
                .positiveText("Agree");

        buttonColloect = (Button) findViewById(R.id.buttonCollect);
        percentageView = (TextView) findViewById(R.id.textview1);
        scratchView0 = (WScratchView) findViewById(R.id.scratchView0);
        scratchView1 = (WScratchView) findViewById(R.id.scratchView1);
        scratchView2 = (WScratchView) findViewById(R.id.scratchView2);
        scratchView3 = (WScratchView) findViewById(R.id.scratchView3);
        scratchView4 = (WScratchView) findViewById(R.id.scratchView4);
        scratchView5 = (WScratchView) findViewById(R.id.scratchView5);
        scratchView6 = (WScratchView) findViewById(R.id.scratchView6);
        scratchView7 = (WScratchView) findViewById(R.id.scratchView7);
        scratchView8 = (WScratchView) findViewById(R.id.scratchView8);
        // set drawable to scratchview
        scratchView0.setScratchDrawable(getResources().getDrawable(R.drawable.ic_scratch_bonus));
        scratchView1.setScratchDrawable(getResources().getDrawable(R.drawable.ic_scratch_bonus));
        scratchView2.setScratchDrawable(getResources().getDrawable(R.drawable.ic_scratch_bonus));
        scratchView3.setScratchDrawable(getResources().getDrawable(R.drawable.ic_scratch_bonus));
        scratchView4.setScratchDrawable(getResources().getDrawable(R.drawable.ic_scratch_bonus));
        scratchView5.setScratchDrawable(getResources().getDrawable(R.drawable.ic_scratch_bonus));
        scratchView6.setScratchDrawable(getResources().getDrawable(R.drawable.ic_scratch_bonus));
        scratchView7.setScratchDrawable(getResources().getDrawable(R.drawable.ic_scratch_bonus));
        scratchView8.setScratchDrawable(getResources().getDrawable(R.drawable.ic_scratch_bonus));
        scratchView0.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                mPercentage = v;
            }

            @Override
            public void onDetach(boolean b) {
                if (mPercentage > 85) {
                    if (!revealed.get(0)) {
                        revealed.set(0, true);
                        showMessage("card revealed");
                        mPercentage = 0;
                        scratchView1.setScratchable(true);
                        scratchView2.setScratchable(true);
                        scratchView3.setScratchable(true);
                        scratchView4.setScratchable(true);
                        scratchView5.setScratchable(true);
                        scratchView6.setScratchable(true);
                        scratchView7.setScratchable(true);
                        scratchView8.setScratchable(true);
                        String type = scratchDatas.get(0).type;
                        switch (type) {
                            case "a":
                                int a_count = sameKindCount.get(0);
                                ++a_count;
                                sameKindCount.set(0, a_count);
                                break;
                            case "b":
                                int b_count = sameKindCount.get(1);
                                ++b_count;
                                sameKindCount.set(1, b_count);
                                break;
                            case "c":
                                int c_count = sameKindCount.get(2);
                                ++c_count;
                                sameKindCount.set(2, c_count);
                                break;
                            case "d":
                                int d_count = sameKindCount.get(3);
                                ++d_count;
                                sameKindCount.set(3, d_count);
                                break;
                            case "e":
                                int e_count = sameKindCount.get(4);
                                ++e_count;
                                sameKindCount.set(4, e_count);
                                break;
                            case "f":
                                int f_count = sameKindCount.get(5);
                                ++f_count;
                                sameKindCount.set(5, f_count);
                                break;
                            case "g":
                                int g_count = sameKindCount.get(6);
                                ++g_count;
                                sameKindCount.set(6, g_count);
                                break;
                            case "h":
                                int h_count = sameKindCount.get(7);
                                ++h_count;
                                sameKindCount.set(7, h_count);
                                break;
                            case "i":
                                int i_count = sameKindCount.get(8);
                                ++i_count;
                                sameKindCount.set(8, i_count);
                                break;

                        }
                    } else {
                        showMessage("card already revealed");
                    }
                } else {
                    scratchView1.setScratchable(false);
                    scratchView2.setScratchable(false);
                    scratchView3.setScratchable(false);
                    scratchView4.setScratchable(false);
                    scratchView5.setScratchable(false);
                    scratchView6.setScratchable(false);
                    scratchView7.setScratchable(false);
                    scratchView8.setScratchable(false);
                }
                for (int i = 0; i < 9; i++) {
                    int counter = sameKindCount.get(i);
                    if (counter >= 3) {
                        showPopUpMessage("Congrats");
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
                }
            }
        });
        scratchView1.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                mPercentage = v;
            }

            @Override
            public void onDetach(boolean b) {
                if (mPercentage > 85) {
                    if (!revealed.get(1)) {
                        revealed.set(1, true);
                        showMessage("card revealed");
                        mPercentage = 0;
                        scratchView0.setScratchable(true);
                        scratchView2.setScratchable(true);
                        scratchView3.setScratchable(true);
                        scratchView4.setScratchable(true);
                        scratchView5.setScratchable(true);
                        scratchView6.setScratchable(true);
                        scratchView7.setScratchable(true);
                        scratchView8.setScratchable(true);
                        String type = scratchDatas.get(1).type;
                        switch (type) {
                            case "a":
                                int a_count = sameKindCount.get(0);
                                ++a_count;
                                sameKindCount.set(0, a_count);
                                break;
                            case "b":
                                int b_count = sameKindCount.get(1);
                                ++b_count;
                                sameKindCount.set(1, b_count);
                                break;
                            case "c":
                                int c_count = sameKindCount.get(2);
                                ++c_count;
                                sameKindCount.set(2, c_count);
                                break;
                            case "d":
                                int d_count = sameKindCount.get(3);
                                ++d_count;
                                sameKindCount.set(3, d_count);
                                break;
                            case "e":
                                int e_count = sameKindCount.get(4);
                                ++e_count;
                                sameKindCount.set(4, e_count);
                                break;
                            case "f":
                                int f_count = sameKindCount.get(5);
                                ++f_count;
                                sameKindCount.set(5, f_count);
                                break;
                            case "g":
                                int g_count = sameKindCount.get(6);
                                ++g_count;
                                sameKindCount.set(6, g_count);
                                break;
                            case "h":
                                int h_count = sameKindCount.get(7);
                                ++h_count;
                                sameKindCount.set(7, h_count);
                                break;
                            case "i":
                                int i_count = sameKindCount.get(8);
                                ++i_count;
                                sameKindCount.set(8, i_count);
                                break;

                        }
                    } else {
                        showMessage("card already revealed");
                    }
                } else {
                    scratchView0.setScratchable(false);
                    scratchView2.setScratchable(false);
                    scratchView3.setScratchable(false);
                    scratchView4.setScratchable(false);
                    scratchView5.setScratchable(false);
                    scratchView6.setScratchable(false);
                    scratchView7.setScratchable(false);
                    scratchView8.setScratchable(false);
                }
                for (int i = 0; i < 9; i++) {
                    int counter = sameKindCount.get(i);
                    if (counter >= 3) {
                        showPopUpMessage("Congrats");
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
                }
            }
        });
        scratchView2.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                mPercentage = v;
            }

            @Override
            public void onDetach(boolean b) {
                if (mPercentage > 85) {
                    if (!revealed.get(2)) {
                        revealed.set(2, true);
                        showMessage("card revealed");
                        mPercentage = 0;
                        scratchView1.setScratchable(true);
                        scratchView0.setScratchable(true);
                        scratchView3.setScratchable(true);
                        scratchView4.setScratchable(true);
                        scratchView5.setScratchable(true);
                        scratchView6.setScratchable(true);
                        scratchView7.setScratchable(true);
                        scratchView8.setScratchable(true);
                        String type = scratchDatas.get(2).type;
                        switch (type) {
                            case "a":
                                int a_count = sameKindCount.get(0);
                                ++a_count;
                                sameKindCount.set(0, a_count);
                                break;
                            case "b":
                                int b_count = sameKindCount.get(1);
                                ++b_count;
                                sameKindCount.set(1, b_count);
                                break;
                            case "c":
                                int c_count = sameKindCount.get(2);
                                ++c_count;
                                sameKindCount.set(2, c_count);
                                break;
                            case "d":
                                int d_count = sameKindCount.get(3);
                                ++d_count;
                                sameKindCount.set(3, d_count);
                                break;
                            case "e":
                                int e_count = sameKindCount.get(4);
                                ++e_count;
                                sameKindCount.set(4, e_count);
                                break;
                            case "f":
                                int f_count = sameKindCount.get(5);
                                ++f_count;
                                sameKindCount.set(5, f_count);
                                break;
                            case "g":
                                int g_count = sameKindCount.get(6);
                                ++g_count;
                                sameKindCount.set(6, g_count);
                                break;
                            case "h":
                                int h_count = sameKindCount.get(7);
                                ++h_count;
                                sameKindCount.set(7, h_count);
                                break;
                            case "i":
                                int i_count = sameKindCount.get(8);
                                ++i_count;
                                sameKindCount.set(8, i_count);
                                break;

                        }
                    } else {
                        showMessage("card already revealed");
                    }
                } else {
                    scratchView0.setScratchable(false);
                    scratchView1.setScratchable(false);
                    scratchView3.setScratchable(false);
                    scratchView4.setScratchable(false);
                    scratchView5.setScratchable(false);
                    scratchView6.setScratchable(false);
                    scratchView7.setScratchable(false);
                    scratchView8.setScratchable(false);
                }
                for (int i = 0; i < 9; i++) {
                    int counter = sameKindCount.get(i);
                    if (counter >= 3) {
                        showPopUpMessage("Congrats");
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
                }
            }
        });
        scratchView3.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                mPercentage = v;
            }

            @Override
            public void onDetach(boolean b) {
                if (mPercentage > 85) {
                    if (!revealed.get(3)) {
                        revealed.set(3, true);
                        showMessage("card revealed");
                        mPercentage = 0;
                        scratchView1.setScratchable(true);
                        scratchView2.setScratchable(true);
                        scratchView0.setScratchable(true);
                        scratchView4.setScratchable(true);
                        scratchView5.setScratchable(true);
                        scratchView6.setScratchable(true);
                        scratchView7.setScratchable(true);
                        scratchView8.setScratchable(true);
                        String type = scratchDatas.get(3).type;
                        switch (type) {
                            case "a":
                                int a_count = sameKindCount.get(0);
                                ++a_count;
                                sameKindCount.set(0, a_count);
                                break;
                            case "b":
                                int b_count = sameKindCount.get(1);
                                ++b_count;
                                sameKindCount.set(1, b_count);
                                break;
                            case "c":
                                int c_count = sameKindCount.get(2);
                                ++c_count;
                                sameKindCount.set(2, c_count);
                                break;
                            case "d":
                                int d_count = sameKindCount.get(3);
                                ++d_count;
                                sameKindCount.set(3, d_count);
                                break;
                            case "e":
                                int e_count = sameKindCount.get(4);
                                ++e_count;
                                sameKindCount.set(4, e_count);
                                break;
                            case "f":
                                int f_count = sameKindCount.get(5);
                                ++f_count;
                                sameKindCount.set(5, f_count);
                                break;
                            case "g":
                                int g_count = sameKindCount.get(6);
                                ++g_count;
                                sameKindCount.set(6, g_count);
                                break;
                            case "h":
                                int h_count = sameKindCount.get(7);
                                ++h_count;
                                sameKindCount.set(7, h_count);
                                break;
                            case "i":
                                int i_count = sameKindCount.get(8);
                                ++i_count;
                                sameKindCount.set(8, i_count);
                                break;

                        }
                    } else {
                        showMessage("card already revealed");
                    }
                } else {
                    scratchView0.setScratchable(false);
                    scratchView1.setScratchable(false);
                    scratchView2.setScratchable(false);
                    scratchView4.setScratchable(false);
                    scratchView5.setScratchable(false);
                    scratchView6.setScratchable(false);
                    scratchView7.setScratchable(false);
                    scratchView8.setScratchable(false);
                }
                for (int i = 0; i < 9; i++) {
                    int counter = sameKindCount.get(i);
                    if (counter >= 3) {
                        showPopUpMessage("Congrats");
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
                }
            }
        });
        scratchView4.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                mPercentage = v;
            }

            @Override
            public void onDetach(boolean b) {
                if (mPercentage > 85) {
                    if (!revealed.get(1)) {
                        revealed.set(4, true);
                        showMessage("card revealed");
                        mPercentage = 0;
                        scratchView1.setScratchable(true);
                        scratchView2.setScratchable(true);
                        scratchView3.setScratchable(true);
                        scratchView0.setScratchable(true);
                        scratchView5.setScratchable(true);
                        scratchView6.setScratchable(true);
                        scratchView7.setScratchable(true);
                        scratchView8.setScratchable(true);
                        String type = scratchDatas.get(4).type;
                        switch (type) {
                            case "a":
                                int a_count = sameKindCount.get(0);
                                ++a_count;
                                sameKindCount.set(0, a_count);
                                break;
                            case "b":
                                int b_count = sameKindCount.get(1);
                                ++b_count;
                                sameKindCount.set(1, b_count);
                                break;
                            case "c":
                                int c_count = sameKindCount.get(2);
                                ++c_count;
                                sameKindCount.set(2, c_count);
                                break;
                            case "d":
                                int d_count = sameKindCount.get(3);
                                ++d_count;
                                sameKindCount.set(3, d_count);
                                break;
                            case "e":
                                int e_count = sameKindCount.get(4);
                                ++e_count;
                                sameKindCount.set(4, e_count);
                                break;
                            case "f":
                                int f_count = sameKindCount.get(5);
                                ++f_count;
                                sameKindCount.set(5, f_count);
                                break;
                            case "g":
                                int g_count = sameKindCount.get(6);
                                ++g_count;
                                sameKindCount.set(6, g_count);
                                break;
                            case "h":
                                int h_count = sameKindCount.get(7);
                                ++h_count;
                                sameKindCount.set(7, h_count);
                                break;
                            case "i":
                                int i_count = sameKindCount.get(8);
                                ++i_count;
                                sameKindCount.set(8, i_count);
                                break;

                        }
                    } else {
                        showMessage("card already revealed");
                    }
                } else {
                    scratchView0.setScratchable(false);
                    scratchView1.setScratchable(false);
                    scratchView2.setScratchable(false);
                    scratchView3.setScratchable(false);
                    scratchView5.setScratchable(false);
                    scratchView6.setScratchable(false);
                    scratchView7.setScratchable(false);
                    scratchView8.setScratchable(false);
                }
                for (int i = 0; i < 9; i++) {
                    int counter = sameKindCount.get(i);
                    if (counter >= 3) {
                        showPopUpMessage("Congrats");
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
                }
            }
        });
        scratchView5.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                mPercentage = v;
            }

            @Override
            public void onDetach(boolean b) {
                if (mPercentage > 85) {
                    if (!revealed.get(5)) {
                        revealed.set(5, true);
                        showMessage("card revealed");
                        mPercentage = 0;
                        scratchView1.setScratchable(true);
                        scratchView2.setScratchable(true);
                        scratchView3.setScratchable(true);
                        scratchView4.setScratchable(true);
                        scratchView0.setScratchable(true);
                        scratchView6.setScratchable(true);
                        scratchView7.setScratchable(true);
                        scratchView8.setScratchable(true);
                        String type = scratchDatas.get(5).type;
                        switch (type) {
                            case "a":
                                int a_count = sameKindCount.get(0);
                                ++a_count;
                                sameKindCount.set(0, a_count);
                                break;
                            case "b":
                                int b_count = sameKindCount.get(1);
                                ++b_count;
                                sameKindCount.set(1, b_count);
                                break;
                            case "c":
                                int c_count = sameKindCount.get(2);
                                ++c_count;
                                sameKindCount.set(2, c_count);
                                break;
                            case "d":
                                int d_count = sameKindCount.get(3);
                                ++d_count;
                                sameKindCount.set(3, d_count);
                                break;
                            case "e":
                                int e_count = sameKindCount.get(4);
                                ++e_count;
                                sameKindCount.set(4, e_count);
                                break;
                            case "f":
                                int f_count = sameKindCount.get(5);
                                ++f_count;
                                sameKindCount.set(5, f_count);
                                break;
                            case "g":
                                int g_count = sameKindCount.get(6);
                                ++g_count;
                                sameKindCount.set(6, g_count);
                                break;
                            case "h":
                                int h_count = sameKindCount.get(7);
                                ++h_count;
                                sameKindCount.set(7, h_count);
                                break;
                            case "i":
                                int i_count = sameKindCount.get(8);
                                ++i_count;
                                sameKindCount.set(8, i_count);
                                break;

                        }
                    } else {
                        showMessage("card already revealed");
                    }
                } else {
                    scratchView0.setScratchable(false);
                    scratchView2.setScratchable(false);
                    scratchView3.setScratchable(false);
                    scratchView4.setScratchable(false);
                    scratchView1.setScratchable(false);
                    scratchView6.setScratchable(false);
                    scratchView7.setScratchable(false);
                    scratchView8.setScratchable(false);
                }
                for (int i = 0; i < 9; i++) {
                    int counter = sameKindCount.get(i);
                    if (counter >= 3) {
                        showPopUpMessage("Congrats");
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
                }
            }
        });
        scratchView6.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                mPercentage = v;
            }

            @Override
            public void onDetach(boolean b) {
                if (mPercentage > 85) {
                    if (!revealed.get(6)) {
                        revealed.set(6, true);
                        showMessage("card revealed");
                        mPercentage = 0;
                        scratchView1.setScratchable(true);
                        scratchView2.setScratchable(true);
                        scratchView3.setScratchable(true);
                        scratchView4.setScratchable(true);
                        scratchView5.setScratchable(true);
                        scratchView0.setScratchable(true);
                        scratchView7.setScratchable(true);
                        scratchView8.setScratchable(true);
                        String type = scratchDatas.get(6).type;
                        switch (type) {
                            case "a":
                                int a_count = sameKindCount.get(0);
                                ++a_count;
                                sameKindCount.set(0, a_count);
                                break;
                            case "b":
                                int b_count = sameKindCount.get(1);
                                ++b_count;
                                sameKindCount.set(1, b_count);
                                break;
                            case "c":
                                int c_count = sameKindCount.get(2);
                                ++c_count;
                                sameKindCount.set(2, c_count);
                                break;
                            case "d":
                                int d_count = sameKindCount.get(3);
                                ++d_count;
                                sameKindCount.set(3, d_count);
                                break;
                            case "e":
                                int e_count = sameKindCount.get(4);
                                ++e_count;
                                sameKindCount.set(4, e_count);
                                break;
                            case "f":
                                int f_count = sameKindCount.get(5);
                                ++f_count;
                                sameKindCount.set(5, f_count);
                                break;
                            case "g":
                                int g_count = sameKindCount.get(6);
                                ++g_count;
                                sameKindCount.set(6, g_count);
                                break;
                            case "h":
                                int h_count = sameKindCount.get(7);
                                ++h_count;
                                sameKindCount.set(7, h_count);
                                break;
                            case "i":
                                int i_count = sameKindCount.get(8);
                                ++i_count;
                                sameKindCount.set(8, i_count);
                                break;

                        }
                    } else {
                        showMessage("card already revealed");
                    }
                } else {
                    scratchView0.setScratchable(false);
                    scratchView2.setScratchable(false);
                    scratchView3.setScratchable(false);
                    scratchView4.setScratchable(false);
                    scratchView5.setScratchable(false);
                    scratchView1.setScratchable(false);
                    scratchView7.setScratchable(false);
                    scratchView8.setScratchable(false);
                }
                for (int i = 0; i < 9; i++) {
                    int counter = sameKindCount.get(i);
                    if (counter >= 3) {
                        showPopUpMessage("Congrats");
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
                }
            }
        });
        scratchView7.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                mPercentage = v;
            }

            @Override
            public void onDetach(boolean b) {
                if (mPercentage > 85) {
                    if (!revealed.get(7)) {
                        revealed.set(7, true);
                        showMessage("card revealed");
                        mPercentage = 0;
                        scratchView1.setScratchable(true);
                        scratchView2.setScratchable(true);
                        scratchView3.setScratchable(true);
                        scratchView4.setScratchable(true);
                        scratchView5.setScratchable(true);
                        scratchView6.setScratchable(true);
                        scratchView0.setScratchable(true);
                        scratchView8.setScratchable(true);
                        String type = scratchDatas.get(7).type;
                        switch (type) {
                            case "a":
                                int a_count = sameKindCount.get(0);
                                ++a_count;
                                sameKindCount.set(0, a_count);
                                break;
                            case "b":
                                int b_count = sameKindCount.get(1);
                                ++b_count;
                                sameKindCount.set(1, b_count);
                                break;
                            case "c":
                                int c_count = sameKindCount.get(2);
                                ++c_count;
                                sameKindCount.set(2, c_count);
                                break;
                            case "d":
                                int d_count = sameKindCount.get(3);
                                ++d_count;
                                sameKindCount.set(3, d_count);
                                break;
                            case "e":
                                int e_count = sameKindCount.get(4);
                                ++e_count;
                                sameKindCount.set(4, e_count);
                                break;
                            case "f":
                                int f_count = sameKindCount.get(5);
                                ++f_count;
                                sameKindCount.set(5, f_count);
                                break;
                            case "g":
                                int g_count = sameKindCount.get(6);
                                ++g_count;
                                sameKindCount.set(6, g_count);
                                break;
                            case "h":
                                int h_count = sameKindCount.get(7);
                                ++h_count;
                                sameKindCount.set(7, h_count);
                                break;
                            case "i":
                                int i_count = sameKindCount.get(8);
                                ++i_count;
                                sameKindCount.set(8, i_count);
                                break;

                        }
                    } else {
                        showMessage("card already revealed");
                    }
                } else {
                    scratchView0.setScratchable(false);
                    scratchView2.setScratchable(false);
                    scratchView3.setScratchable(false);
                    scratchView4.setScratchable(false);
                    scratchView5.setScratchable(false);
                    scratchView6.setScratchable(false);
                    scratchView1.setScratchable(false);
                    scratchView8.setScratchable(false);
                }
                for (int i = 0; i < 9; i++) {
                    int counter = sameKindCount.get(i);
                    if (counter >= 3) {
                        showPopUpMessage("Congrats");
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
                }
            }
        });
        scratchView8.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                mPercentage = v;
            }

            @Override
            public void onDetach(boolean b) {
                if (mPercentage > 85) {
                    if (!revealed.get(8)) {
                        revealed.set(8, true);
                        showMessage("card revealed");
                        mPercentage = 0;
                        scratchView1.setScratchable(true);
                        scratchView2.setScratchable(true);
                        scratchView3.setScratchable(true);
                        scratchView4.setScratchable(true);
                        scratchView5.setScratchable(true);
                        scratchView6.setScratchable(true);
                        scratchView7.setScratchable(true);
                        scratchView0.setScratchable(true);
                        String type = scratchDatas.get(8).type;
                        switch (type) {
                            case "a":
                                int a_count = sameKindCount.get(0);
                                ++a_count;
                                sameKindCount.set(0, a_count);
                                break;
                            case "b":
                                int b_count = sameKindCount.get(1);
                                ++b_count;
                                sameKindCount.set(1, b_count);
                                break;
                            case "c":
                                int c_count = sameKindCount.get(2);
                                ++c_count;
                                sameKindCount.set(2, c_count);
                                break;
                            case "d":
                                int d_count = sameKindCount.get(3);
                                ++d_count;
                                sameKindCount.set(3, d_count);
                                break;
                            case "e":
                                int e_count = sameKindCount.get(4);
                                ++e_count;
                                sameKindCount.set(4, e_count);
                                break;
                            case "f":
                                int f_count = sameKindCount.get(5);
                                ++f_count;
                                sameKindCount.set(5, f_count);
                                break;
                            case "g":
                                int g_count = sameKindCount.get(6);
                                ++g_count;
                                sameKindCount.set(6, g_count);
                                break;
                            case "h":
                                int h_count = sameKindCount.get(7);
                                ++h_count;
                                sameKindCount.set(7, h_count);
                                break;
                            case "i":
                                int i_count = sameKindCount.get(8);
                                ++i_count;
                                sameKindCount.set(8, i_count);
                                break;

                        }
                    } else {
                        showMessage("card already revealed");
                    }
                } else {
                    scratchView0.setScratchable(false);
                    scratchView2.setScratchable(false);
                    scratchView3.setScratchable(false);
                    scratchView4.setScratchable(false);
                    scratchView5.setScratchable(false);
                    scratchView6.setScratchable(false);
                    scratchView1.setScratchable(false);
                    scratchView7.setScratchable(false);
                }
                for (int i = 0; i < 9; i++) {
                    int counter = sameKindCount.get(i);
                    if (counter >= 3) {
                        showPopUpMessage("Congrats");
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
                }
            }
        });
        view = findViewById(R.id.view);
        view.bringToFront();
        mExplosionField = ExplosionField.attach2Window(this);

        buttonColloect.setOnClickListener(this);
    }

    private void setScratchData() {
        scratchDatas.add(new ScratchData(0, "180", "", "a"));
        scratchDatas.add(new ScratchData(1, "20", "", "b"));
        scratchDatas.add(new ScratchData(2, "70", "", "c"));
        scratchDatas.add(new ScratchData(3, "70", "", "c"));
        scratchDatas.add(new ScratchData(4, "180", "", "a"));
        scratchDatas.add(new ScratchData(5, "90", "", "d"));
        scratchDatas.add(new ScratchData(6, "100", "", "e"));
        scratchDatas.add(new ScratchData(7, "0", "", "f"));
        scratchDatas.add(new ScratchData(8, "70", "", "c"));


    }

    private void showPopUpMessage(String s) {
        buttonColloect.setVisibility(View.VISIBLE);
        confetti_top_right = new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 180, 180)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90);
        confetti_top_right.emit(findViewById(R.id.emiter_top_right), 8);

        confetti_top_left = new ParticleSystem(this, 80, R.drawable.confeti3, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90);
        confetti_top_left.emit(findViewById(R.id.emiter_top_left), 8);
        gif.setVisibility(View.VISIBLE);

        boolean wrapInScrollView = true;
        customdialog = new MaterialDialog.Builder(this)
                .customView(R.layout.custom_dialog, wrapInScrollView).show();
        Button button = (Button) customdialog.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customdialog.dismiss();
                confetti = new ParticleSystem(ImageOverlayDrawable.this, 100, R.drawable.confeti2, 5000)
                        .setSpeedRange(0.1f, 0.25f);
                confetti.oneShot(view, 900);

            }
        });

    }


    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bonus_layout = (LinearLayout) findViewById(R.id.bonus_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);


    }


    private void showMessage(String s) {
        Toast.makeText(ImageOverlayDrawable.this, s, Toast.LENGTH_SHORT).show();
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCollect:
                //   showPopUpMessage("kunsang");
                confetti.stopEmitting();
                confetti_top_left.stopEmitting();
                confetti_top_right.stopEmitting();
                gif.setVisibility(View.GONE);
                relativeLayoutCounter.setVisibility(View.VISIBLE);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                setupTimeThread(dateFormat.format(cal.getTime()));
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

}
