package com.netforceinfotech.ibet.scratchview;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.netforceinfotech.ibet.R;
import com.plattysoft.leonids.ParticleSystem;
import com.winsontan520.WScratchView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import pl.droidsonroids.gif.GifImageView;

public class ScratchActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout relativeLayoutCounter;
    WScratchView scratchView0, scratchView1, scratchView2, scratchView3, scratchView4, scratchView5, scratchView6, scratchView7, scratchView8;
    ImageView imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8;
    ArrayList<ScratchData> scratchDatas = new ArrayList<>();
    ArrayList<Type> types = new ArrayList<>();
    ArrayList<Boolean> revealedList = new ArrayList<>();
    private Toolbar toolbar;
    private MaterialDialog customdialog;
    private ParticleSystem confetti;
    private TextView textViewCounter;
    private GifImageView gif;
    private Button buttonColloect;
    private MaterialDialog.Builder dialog;
    private ParticleSystem confetti_top_right;
    private ParticleSystem confetti_top_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch);
        setupToolBar("Scratch card");
        setDummyScratchData();
        initView();

    }

    private void setDummyScratchData() {

        try {
            revealedList.clear();
            scratchDatas.clear();
            types.clear();
        } catch (Exception ex) {

        }
        for (int i = 0; i < 9; i++) {
            revealedList.add(false);
        }
        //ScratchData(int position, String value, String imgUrl, String type) {
        scratchDatas.add(new ScratchData(0, 20, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(1, 1000, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(2, 10, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(3, 1000, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(4, 500, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(5, 10, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(6, 20, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(7, 50, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(8, 20, R.drawable.coin, ""));
        for (int i = 0; i < 9; i++) {
            int value = scratchDatas.get(i).value;
            Type type = new Type(value, 0);
            if (!types.contains(type)) {
                types.add(type);
            }
        }

    }

    private void initView() {
        textViewCounter = (TextView) findViewById(R.id.textViewCounter);
        relativeLayoutCounter = (RelativeLayout) findViewById(R.id.relativeLayoutCounter);
        relativeLayoutCounter.setVisibility(View.GONE);
        gif = (GifImageView) findViewById(R.id.gif);
        dialog = new MaterialDialog.Builder(this)
                .title("You can Choose Only Three Bonus")
                .content("Hi")
                .positiveText("Agree");

        buttonColloect = (Button) findViewById(R.id.buttonCollect);
        buttonColloect.setVisibility(View.GONE);
        buttonColloect.setOnClickListener(this);
        relativeLayoutCounter = (RelativeLayout) findViewById(R.id.relativeLayoutCounter);
        imageView0 = (ImageView) findViewById(R.id.imageView0);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        scratchView0 = (WScratchView) findViewById(R.id.scratchView0);
        scratchView1 = (WScratchView) findViewById(R.id.scratchView1);
        scratchView2 = (WScratchView) findViewById(R.id.scratchView2);
        scratchView3 = (WScratchView) findViewById(R.id.scratchView3);
        scratchView4 = (WScratchView) findViewById(R.id.scratchView4);
        scratchView5 = (WScratchView) findViewById(R.id.scratchView5);
        scratchView6 = (WScratchView) findViewById(R.id.scratchView6);
        scratchView7 = (WScratchView) findViewById(R.id.scratchView7);
        scratchView8 = (WScratchView) findViewById(R.id.scratchView8);

        scratchView0.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(0)) {
                        revealedList.set(0, true);
                        checkGiftStatus(0);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView1.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(1)) {
                        revealedList.set(1, true);
                        checkGiftStatus(1);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView2.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(2)) {
                        revealedList.set(2, true);
                        checkGiftStatus(2);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView3.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(3)) {
                        revealedList.set(3, true);
                        checkGiftStatus(3);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView4.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(4)) {
                        revealedList.set(4, true);
                        checkGiftStatus(4);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView5.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(5)) {
                        revealedList.set(5, true);
                        checkGiftStatus(5);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView6.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(6)) {
                        revealedList.set(6, true);
                        checkGiftStatus(6);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView7.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(7)) {
                        revealedList.set(7, true);
                        checkGiftStatus(7);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView8.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(8)) {
                        revealedList.set(8, true);
                        checkGiftStatus(8);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
    }

    private void checkGiftStatus(int position) {
        int value = scratchDatas.get(position).value;
        for (int i = 0; i < types.size(); i++) {
            if (value == types.get(i).value) {
                types.get(i).count++;
                if (types.get(i).count >= 3) {
                    showPopUpMessage("Congrats");
                }
                break;
            }
        }
    }

    private void showMessage(String congrats) {
        Toast.makeText(this, congrats, Toast.LENGTH_SHORT).show();
    }

    public void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);


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

    private void showPopUpMessage(String s) {
        buttonColloect.setVisibility(View.VISIBLE);
        confetti_top_left = new ParticleSystem(this, 80, R.drawable.confeti3, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 0, 0)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90);
        confetti_top_left.emit(findViewById(R.id.emiter_top_left), 8);
        confetti_top_right = new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
                .setSpeedModuleAndAngleRange(0f, 0.3f, 180, 180)
                .setRotationSpeed(144)
                .setAcceleration(0.00005f, 90);
        confetti_top_right.emit(findViewById(R.id.emiter_top_right), 8);


        gif.setVisibility(View.VISIBLE);

        boolean wrapInScrollView = true;
        customdialog = new MaterialDialog.Builder(this)
                .customView(R.layout.custom_dialog, wrapInScrollView).show();
        Button button = (Button) customdialog.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customdialog.dismiss();
                confetti = new ParticleSystem(ScratchActivity.this, 100, R.drawable.confeti2, 5000)
                        .setSpeedRange(0.1f, 0.25f);
                confetti.oneShot(view, 900);

            }
        });
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCollect:
                //   showPopUpMessage("kunsang");
                try {
                    confetti.stopEmitting();
                    confetti_top_left.stopEmitting();
                    confetti_top_right.stopEmitting();
                    gif.setVisibility(View.GONE);
                    relativeLayoutCounter.setVisibility(View.VISIBLE);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, 1);
                    setupTimeThread(dateFormat.format(cal.getTime()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
