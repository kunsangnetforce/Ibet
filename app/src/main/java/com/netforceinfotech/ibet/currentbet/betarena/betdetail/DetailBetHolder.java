package com.netforceinfotech.ibet.currentbet.betarena.betdetail;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class DetailBetHolder extends RecyclerView.ViewHolder {


    LinearLayout linearLayout;
    TextView textViewName, textViewScore;
    View view;
    CircleImageView imageViewDp;
    TextView textViewResult, textViewSelectedTeam;
    UserSessionManager userSessionManager;
    Context context;

    public DetailBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        context = itemView.getContext();
        userSessionManager = new UserSessionManager(context);
        imageViewDp = (CircleImageView) view.findViewById(R.id.imageViewDP);
        textViewResult = (TextView) view.findViewById(R.id.textviewResult);
        textViewSelectedTeam = (TextView) view.findViewById(R.id.textViewSelectedTeam);
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
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewSelectedTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));

    }

    private void setMarronTheme() {

        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewSelectedTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
    }

    private void setGreenTheme() {

        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewSelectedTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
    }

    private void setPurpleTheme() {

        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewSelectedTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));

    }

    private void setBrownTheme() {

        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewSelectedTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setDefaultTheme() {
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewSelectedTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
    }
}