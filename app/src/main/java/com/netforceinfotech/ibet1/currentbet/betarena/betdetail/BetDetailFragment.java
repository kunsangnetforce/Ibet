package com.netforceinfotech.ibet1.currentbet.betarena.betdetail;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetDetailFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Context context;
    LinearLayout linearLayout;
    private DetailBetAdapter adapter;
    CoordinatorLayout coordinatorLayout;
    ArrayList<DetailBetData> detailBetDatas = new ArrayList<>();
    ImageView imageViewTeamA, imageViewTeamB;
    UserSessionManager userSessionManager;
    View view1;
    TextView textViewMSI, textViewBetamount, textViewPlayer, textViewResult, textViewTeam, textViewScore, textViewLoserMessage;

    public BetDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmentet_bet_detail, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        initView(view);
        setupTheme();
        setupBackground();
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        DetailBetAdapter detailBetAdapter = new DetailBetAdapter(context, null);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(detailBetAdapter);
    }

    private void initView(View view) {
        view1 = view.findViewById(R.id.view);
        textViewPlayer = (TextView) view.findViewById(R.id.textViewPlayer);
        textViewResult = (TextView) view.findViewById(R.id.textViewResult);
        textViewScore = (TextView) view.findViewById(R.id.textViewScore);
        textViewTeam = (TextView) view.findViewById(R.id.textViewTeam);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        textViewBetamount = (TextView) view.findViewById(R.id.textViewBetamount);
        textViewMSI = (TextView) view.findViewById(R.id.textViewMSI);
        textViewLoserMessage = (TextView) view.findViewById(R.id.textViewLoserMessage);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        view.findViewById(R.id.buttonClose).setOnClickListener(this);
        imageViewTeamA = (ImageView) view.findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (ImageView) view.findViewById(R.id.imageViewTeamB);
        Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamA);
        Picasso.with(context).load(R.drawable.ic_error).into(imageViewTeamB);
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
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));

    }

    private void setupPurlpleTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));

    }

    private void setupGreenTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

    }

    private void setupMarronTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));

    }

    private void setupLightBlueTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));

    }

    private void setupDefaultTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
