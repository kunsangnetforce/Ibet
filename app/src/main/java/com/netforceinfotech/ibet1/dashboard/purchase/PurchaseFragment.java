package com.netforceinfotech.ibet1.dashboard.purchase;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseFragment extends Fragment {


    private View view;
    UserSessionManager userSessionManager;
    Context context;
    CoordinatorLayout coordinatorLayout;
    View view1;
    Button buttonPurchase;
    public PurchaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_purchase, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        view1 = view.findViewById(R.id.view);
        buttonPurchase = (Button) view.findViewById(R.id.buttonPurchase);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        setupTheme();
        setupBackground();
        return view;
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
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setupPurlpleTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
    }

    private void setupGreenTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
    }

    private void setupMarronTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
    }

    private void setupLightBlueTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
    }

    private void setupDefaultTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

}
