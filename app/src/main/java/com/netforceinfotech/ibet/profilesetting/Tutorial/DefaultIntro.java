package com.netforceinfotech.ibet.profilesetting.Tutorial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.paolorotolo.appintro.AppIntro;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;

import tyrantgit.explosionfield.ExplosionField;

public final class DefaultIntro extends BaseIntro {


    Bitmap icon;
    MaterialDialog dailog;
    ExplosionField mExplosionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro4));
        addSlide(new InputDemoSlide());
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);


        mExplosionField = ExplosionField.attach2Window(this);

        mExplosionField.expandExplosionBound(200, 300);

        showPopUp("");

        // loadMainActivity();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
       /* loadMainActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.skip), Toast.LENGTH_SHORT).show();*/


        mExplosionField = ExplosionField.attach2Window(this);

        mExplosionField.expandExplosionBound(200, 300);

        showPopUp("");
    }

    public void getStarted(View v) {
        loadMainActivity();
    }


    private void showPopUp(String s) {
        dailog = new MaterialDialog.Builder(DefaultIntro.this)
                .title("")
                .customView(R.layout.custom_congratulation_dialog, true).build();

        Button b = (Button) dailog.findViewById(R.id.got_it_buttton);
        TextView textView = (TextView) dailog.findViewById(R.id.textView1);

        YoYo.with(Techniques.ZoomIn)
                .duration(700)
                .playOn(dailog.findViewById(R.id.textView1));

        mExplosionField = ExplosionField.attach2Window(this);

        mExplosionField.expandExplosionBound(200, 300);

        icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.coin);

        //   mExplosionField.explode(icon,null,0,5000);
        addListener(dailog.findViewById(R.id.root));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExplosionField.explode(view);

                dailog.dismiss();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        finish();
                        overridePendingTransition(0, 0);
                    }
                }, 800);
            }
        });
        dailog.show();


    }


    private void addListener(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                addListener(parent.getChildAt(i));
            }
        } else {
            root.setClickable(true);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mExplosionField.explode(v);
                    v.setOnClickListener(null);
                }
            });
        }
    }
}
