package com.netforceinfotech.ibet.profilesetting.tutorial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;

import tyrantgit.explosionfield.ExplosionField;

public final class DefaultIntro extends BaseIntro {



    ExplosionField mExplosionField;
    private String from;
    private Intent intent;
    private TutorialFragment tutorialFragment1, tutorialFragment2, tutorialFragment3, tutorialFragment4, tutorialFragment5, tutorialFragment6, tutorialFragment7, tutorialFragment8, tutorialFragment9, tutorialFragment10, tutorialFragment11, tutorialFragment12;
    private Bundle bundle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        try {
            from = bundle.getString("from");
        } catch (Exception ex) {
            from = "login";
        }

        tutorialFragment1 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 1);
        tutorialFragment1.setArguments(bundle1);
        addSlide(tutorialFragment1);

        tutorialFragment2 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 2);
        tutorialFragment2.setArguments(bundle1);
        addSlide(tutorialFragment2);

        tutorialFragment3 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 3);
        tutorialFragment3.setArguments(bundle1);
        addSlide(tutorialFragment3);

        tutorialFragment4 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 4);
        tutorialFragment4.setArguments(bundle1);
        addSlide(tutorialFragment4);

        tutorialFragment5 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 5);
        tutorialFragment5.setArguments(bundle1);
        addSlide(tutorialFragment5);
        tutorialFragment6 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 6);
        tutorialFragment6.setArguments(bundle1);
        addSlide(tutorialFragment6);
        tutorialFragment7 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 7);
        tutorialFragment7.setArguments(bundle1);
        addSlide(tutorialFragment7);
        tutorialFragment8 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 8);
        tutorialFragment8.setArguments(bundle1);
        addSlide(tutorialFragment8);

        tutorialFragment9 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 9);
        tutorialFragment9.setArguments(bundle1);
        addSlide(tutorialFragment9);
        tutorialFragment10 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 10);
        tutorialFragment10.setArguments(bundle1);
        addSlide(tutorialFragment10);
        tutorialFragment11 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 11);
        tutorialFragment11.setArguments(bundle1);
        addSlide(tutorialFragment11);

        tutorialFragment12 = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putInt("screenNo", 12);
        tutorialFragment12.setArguments(bundle1);
        addSlide(tutorialFragment12);


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
