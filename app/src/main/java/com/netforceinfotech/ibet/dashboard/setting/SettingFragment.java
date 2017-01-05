package com.netforceinfotech.ibet.dashboard.setting;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.Debugger.Debugger;
import com.netforceinfotech.ibet.MainActivity;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.dashboard.setting.feedback.FeedbackActivity;
import com.netforceinfotech.ibet.dashboard.setting.language.LanguageActivity;
import com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification.GeneralNotificationActivity;
import com.netforceinfotech.ibet.dashboard.setting.notification.teamNotification.TeamNotificationActivity;
import com.netforceinfotech.ibet.dashboard.setting.odds.OddsActivity;
import com.netforceinfotech.ibet.dashboard.setting.sounds.SoundActivity;
import com.netforceinfotech.ibet.dashboard.setting.theme.ThemeActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;

public class SettingFragment extends Fragment implements BillingProcessor.IBillingHandler, View.OnClickListener {

    CoordinatorLayout coordinatorLayout;
    View view1;
    Context context;

    private UserSessionManager userSessionManager;
    int theme;
    public BillingProcessor bp;
    private Intent intent;
    MaterialRippleLayout rippleRemoveAds;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        userSessionManager = new UserSessionManager(getActivity());
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        bp = new BillingProcessor(context, getString(R.string.license), this);

        view1 = view.findViewById(R.id.view);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        Dashboard.title.setText(getString(R.string.setting));
        initView(view);
        setupTheme();
        setupBackground();


        return view;
    }

    private void initView(View view) {
        rippleRemoveAds = (MaterialRippleLayout) view.findViewById(R.id.rippleRemoveAds);
        if (userSessionManager.getRemoveAds()) {
            rippleRemoveAds.setVisibility(View.GONE);
        }

        view.findViewById(R.id.linearLayoutLanguage).setOnClickListener(this);
        view.findViewById(R.id.linearLayoutTeamNotification).setOnClickListener(this);
        view.findViewById(R.id.linearLayoutGeneralNotification).setOnClickListener(this);
        view.findViewById(R.id.linearLayoutSound).setOnClickListener(this);
        view.findViewById(R.id.linearLayoutTheme).setOnClickListener(this);
        view.findViewById(R.id.linearLayoutOdds).setOnClickListener(this);
        view.findViewById(R.id.linearLayoutInfo).setOnClickListener(this);
        view.findViewById(R.id.linearLayoutFeedback).setOnClickListener(this);
        view.findViewById(R.id.linearLayoutRemoveAds).setOnClickListener(this);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);

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
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setupPurlpleTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
    }

    private void setupGreenTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

    }

    private void setupMarronTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
    }

    private void setupLightBlueTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
    }

    private void setupDefaultTheme() {
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        Debugger.i("inappPurchased", productId + " " + details.toString());

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        Debugger.i("inappPurchased", errorCode + " " + error.toString());
    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayoutLanguage:
                intent = new Intent(context, LanguageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.linearLayoutTeamNotification:
                intent = new Intent(context, TeamNotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.linearLayoutGeneralNotification:
                intent = new Intent(context, GeneralNotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.linearLayoutSound:
                intent = new Intent(context, SoundActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.linearLayoutTheme:
                intent = new Intent(context, ThemeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.linearLayoutOdds:
                intent = new Intent(context, OddsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.linearLayoutInfo:
                intent = new Intent(context, InfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.linearLayoutRemoveAds:
                UserSessionManager userSessionManager = new UserSessionManager(context);
                if (!userSessionManager.getRemoveAds()) {
                    bp.purchase(getActivity(), getString(R.string.remove_ads));
                } else {
                    showMessage("Already removed");
                }
                break;
            case R.id.linearLayoutFeedback:
                intent = new Intent(context, FeedbackActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;

        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
