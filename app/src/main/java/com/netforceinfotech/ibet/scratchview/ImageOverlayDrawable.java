package com.netforceinfotech.ibet.scratchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.netforceinfotech.ibet.R;
import com.winsontan520.WScratchView;


import java.util.ArrayList;

public class ImageOverlayDrawable extends AppCompatActivity implements View.OnTouchListener {

    WScratchView scratchView0, scratchView1, scratchView2, scratchView3, scratchView4, scratchView5, scratchView6, scratchView7, scratchView8;
    TextView percentageView;
    float mPercentage;
    ArrayList<Boolean> status = new ArrayList<>();
    ArrayList<Boolean> revealStatus = new ArrayList<>();
    int count = 0;
    int revealCount = 0;
    private MaterialDialog.Builder dialog;
    Toolbar toolbar;
    LinearLayout bonus_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scratch_data);

        setupToolBar("Scratch Bonus");

        for (int i = 0; i < 9; i++) {
            status.add(false);
            revealStatus.add(false);
        }

        dialog = new MaterialDialog.Builder(this)
                .title("You can Choose Only Three Bonus")
                .content("Hi")
                .positiveText("Agree");


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

        scratchView0.setOnTouchListener(this);
        scratchView1.setOnTouchListener(this);
        scratchView2.setOnTouchListener(this);
        scratchView3.setOnTouchListener(this);
        scratchView4.setOnTouchListener(this);
        scratchView5.setOnTouchListener(this);
        scratchView6.setOnTouchListener(this);
        scratchView7.setOnTouchListener(this);
        scratchView8.setOnTouchListener(this);


        scratchView0.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {


                if (status.get(0) == true) {
                    if (revealStatus.get(0) == false) {
                        if (v > 90) {
                            revealStatus.set(0, true);
                            revealCount++;
                        }
                    }
                }
                else {
                    if (count >= 3) {
                        scratchView0.resetView();
                    } else {
                        if (status.get(0) == false) {

                            if (v > 3) {
                                count++;
                                status.set(0, true);

                            }
                        }
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

                if (revealCount == 3) {
                    showPopUpMessage("All card Revealed... Collect Your Price");
                } else {
                    if (status.get(0) == false) {
                        if (count >= 3) {
                            showPopUpMessage("Cant scratch more than 3 card. Please scratch 3 cards completely");
                            scratchView0.resetView();
                        }
                    }
                }


            }
        });


        // add callback for update scratch percentage
        scratchView1.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {


                if (status.get(1) == true) {
                    if (revealStatus.get(1) == false) {
                        if (v > 90) {
                            revealStatus.set(1, true);
                            revealCount++;
                        }
                    }
                } else {
                    if (count >= 3) {
                        scratchView1.resetView();
                    } else {
                        if (status.get(1) == false) {

                            if (v > 3) {
                                count++;
                                status.set(1, true);

                            }
                        }
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

                if (revealCount == 3) {
                    showPopUpMessage("All card Revealed... Collect Your Price");
                } else {
                    if (status.get(1) == false) {
                        if (count >= 3) {
                            showPopUpMessage("Cant scratch more than 3 card. Please scratch 3 cards completely");
                            scratchView1.resetView();
                        }
                    }
                }

            }
        });
        scratchView2.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {


                if (status.get(2) == true) {
                    if (revealStatus.get(2) == false) {
                        if (v > 90) {
                            revealStatus.set(2, true);
                            revealCount++;
                        }
                    }
                } else {
                    if (count >= 3) {
                        scratchView2.resetView();
                    } else {
                        if (status.get(2) == false) {

                            if (v > 3) {
                                count++;
                                status.set(2, true);

                            }
                        }
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {
                if (revealCount == 3) {
                    showPopUpMessage("All card Revealed... Collect Your Price");
                } else {
                    if (status.get(2) == false) {

                        if (count >= 3) {
                            showPopUpMessage("Cant scratch more than 3 card. Please scratch 3 cards completely");
                            scratchView2.resetView();
                        }
                    }
                }

            }
        });
        scratchView3.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {


                if (status.get(3) == true) {
                    if (revealStatus.get(3) == false) {
                        if (v > 90) {
                            revealStatus.set(3, true);
                            revealCount++;
                        }
                    }
                } else {
                    if (count >= 3) {
                        scratchView3.resetView();
                    } else {
                        if (status.get(3) == false) {

                            if (v > 3) {
                                count++;
                                status.set(3, true);

                            }
                        }
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {


                if (revealCount == 3) {
                    showPopUpMessage("All card Revealed... Collect Your Price");
                } else {
                    if (status.get(3) == false) {
                        if (count >= 3) {
                            showPopUpMessage("Cant scratch more than 3 card. Please scratch 3 cards completely");
                            scratchView3.resetView();
                        }
                    }
                }

            }
        });
        scratchView4.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {


                if (status.get(4) == true) {
                    if (revealStatus.get(4) == false) {
                        if (v > 90) {
                            revealStatus.set(4, true);
                            revealCount++;
                        }
                    }
                } else {
                    if (count >= 3) {
                        scratchView4.resetView();
                    } else {
                        if (status.get(4) == false) {

                            if (v > 3) {
                                count++;
                                status.set(4, true);

                            }
                        }
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {
                if (revealCount == 3) {
                    showPopUpMessage("All card Revealed... Collect Your Price");
                } else {
                    if (status.get(4) == false) {
                        if (count >= 3) {
                            showPopUpMessage("Cant scratch more than 3 card. Please scratch 3 cards completely");
                            scratchView4.resetView();
                        }
                    }
                }

            }
        });
        scratchView5.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {


                if (status.get(5) == true) {
                    if (revealStatus.get(5) == false) {
                        if (v > 90) {
                            revealStatus.set(5, true);
                            revealCount++;
                        }
                    }
                } else {
                    if (count >= 3) {
                        scratchView5.resetView();
                    } else {
                        if (status.get(5) == false) {

                            if (v > 3) {
                                count++;
                                status.set(5, true);

                            }
                        }
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {
                if (revealCount == 3) {
                    showPopUpMessage("All card Revealed... Collect Your Price");
                } else {
                    if (status.get(5) == false) {
                        if (count >= 3) {
                            showPopUpMessage("Cant scratch more than 3 card. Please scratch 3 cards completely");
                            scratchView5.resetView();
                        }
                    }
                }

            }
        });
        scratchView6.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {


                if (status.get(6) == true) {
                    if (revealStatus.get(6) == false) {
                        if (v > 90) {
                            revealStatus.set(6, true);
                            revealCount++;
                        }
                    }
                } else {
                    if (count >= 3) {
                        scratchView6.resetView();
                    } else {
                        if (status.get(6) == false) {

                            if (v > 3) {
                                count++;
                                status.set(6, true);

                            }
                        }
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

                if (revealCount == 3) {
                    showPopUpMessage("All card Revealed... Collect Your Price");
                } else {
                    if (status.get(6) == false) {
                        if (count >= 3) {
                            showPopUpMessage("Cant scratch more than 3 card. Please scratch 3 cards completely");
                            scratchView6.resetView();
                        }
                    }
                }

            }
        });
        scratchView7.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {


                if (status.get(7) == true) {
                    if (revealStatus.get(7) == false) {
                        if (v > 90) {
                            revealStatus.set(7, true);
                            revealCount++;
                        }
                    }
                } else {
                    if (count >= 3) {
                        scratchView7.resetView();
                    } else {
                        if (status.get(7) == false) {

                            if (v > 3) {
                                count++;
                                status.set(7, true);

                            }
                        }
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {
                if (revealCount == 3) {
                    showPopUpMessage("All card Revealed... Collect Your Price");
                } else {
                    if (status.get(7) == false) {
                        if (count >= 3) {
                            showPopUpMessage("Cant scratch more than 3 card. Please scratch 3 cards completely");
                            scratchView7.resetView();
                        }
                    }
                }

            }
        });
        scratchView8.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {


                if (status.get(8) == true) {
                    if (revealStatus.get(8) == false) {
                        if (v > 90) {
                            revealStatus.set(8, true);
                            revealCount++;
                        }
                    }
                } else {
                    if (count >= 3) {
                        scratchView8.resetView();
                    } else {
                        if (status.get(8) == false) {

                            if (v > 3) {
                                count++;
                                status.set(8, true);

                            }
                        }
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {
                if (revealCount == 3) {
                    showPopUpMessage("All card Revealed... Collect Your Price");
                } else {
                    if (status.get(8) == false) {
                        if (count >= 3) {
                            showPopUpMessage("Cant scratch more than 3 card. Please scratch 3 cards completely");
                            scratchView8.resetView();
                        }
                    }
                }

            }
        });

        // updatePercentage(0f);
    }

    private void showPopUpMessage(String s) {
        new MaterialDialog.Builder(this)
                .title("Bonus")
                .content(s)
                .positiveText("OK").show();
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

    protected void updatePercentage(float percentage) {
        mPercentage = percentage;
        String percentage2decimal = String.format("%.2f", percentage) + " %";
        //percentageView.setText(percentage2decimal);
    }


    private void showMessage(String s) {
        dialog.build().dismiss();
        dialog.build().setTitle(s);
        dialog.build().show();
        //Toast.makeText(ImageOverlayDrawable.this, "Hi Kunsang", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

      /*  switch (view.getId()) {
            case R.id.sample_image1:
                if (!scratchView0.isScratchable()) {
                    showMessage("Cannot Scratch anymore");
                }
                break;
            case R.id.sample_image2:
                if (!scratchView1.isScratchable()) {
                    showMessage("Cannot Scratch anymore");
                }
                break;
            case R.id.sample_image3:
                if (!scratchView2.isScratchable()) {
                    showMessage("Cannot Scratch anymore");
                }
                break;
            case R.id.sample_image4:
                if (!scratchView0.isScratchable()) {
                    showMessage("Cannot Scratch anymore");
                }
                break;
            case R.id.sample_image5:
                if (!scratchView1.isScratchable()) {
                    showMessage("Cannot Scratch anymore");
                }
                break;
            case R.id.sample_image6:
                if (!scratchView2.isScratchable()) {
                    showMessage("Cannot Scratch anymore");
                }
                break;
            case R.id.sample_image7:
                if (!scratchView0.isScratchable()) {
                    showMessage("Cannot Scratch anymore");
                }
                break;
            case R.id.sample_image8:
                if (!scratchView1.isScratchable()) {
                    showMessage("Cannot Scratch anymore");
                }
                break;
            case R.id.sample_image9:
                if (!scratchView2.isScratchable()) {
                    showMessage("Cannot Scratch anymore");
                }
                break;
        }
*/
        return false;
    }
}
