package com.netforceinfotech.ibet.profilesetting.tutorial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;

import tyrantgit.explosionfield.ExplosionField;

public final class DefaultIntro extends BaseIntro {


    Bitmap icon;
    MaterialDialog dailog;
    ExplosionField mExplosionField;
    private String from;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        try {
            from = bundle.getString("from");
        } catch (Exception ex) {
            from = "login";
        }
        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro4));
        addSlide(new InputDemoSlide());
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // loadMainActivity();
        if (from.equalsIgnoreCase("login")) {
            Intent intent = new Intent(DefaultIntro.this, Dashboard.class);
            startActivity(intent);
            finish();
        } else {
            finish();
        }

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        if (from.equalsIgnoreCase("login")) {
            intent = new Intent(DefaultIntro.this, Dashboard.class);
            startActivity(intent);
            finish();
        } else {
            finish();
        }

       /* loadMainActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.skip), Toast.LENGTH_SHORT).show();*/
    }

    public void getStarted(View v) {
        loadMainActivity();
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
