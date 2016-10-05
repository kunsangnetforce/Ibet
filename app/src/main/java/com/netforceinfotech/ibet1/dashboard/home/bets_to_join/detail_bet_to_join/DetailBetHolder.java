package com.netforceinfotech.ibet1.dashboard.home.bets_to_join.detail_bet_to_join;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

public class DetailBetHolder extends RecyclerView.ViewHolder {


    LinearLayout linearLayout;
    TextView textViewName, textViewScore;
    Button buttonClose;
    View view;
    ImageView imageView;
    UserSessionManager userSessionManager;
    Context context;

    public DetailBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        context = itemView.getContext();
        userSessionManager = new UserSessionManager(context);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewScore = (TextView) view.findViewById(R.id.textViewScore);

        switch (userSessionManager.getTheme()) {
            case 0:
                setDefaultTheme();
                break;
            case 1:
                setBrownTheme();
                break;
            case 2:
                setPurpleTheme();
                break;
            case 3:
                setGreenTheme();
                break;
            case 4:
                setMarronTheme();
                break;
            case 5:
                setLightBlueTheme();
                break;

        }
    }

    private void setLightBlueTheme() {

        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
    }

    private void setMarronTheme() {

        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
    }

    private void setGreenTheme() {

        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
    }

    private void setPurpleTheme() {

        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
    }

    private void setBrownTheme() {

        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setDefaultTheme() {
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
    }
}