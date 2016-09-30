package com.netforceinfotech.ibet1.dashboard.home.finsihed_bet;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import de.hdodenhof.circleimageview.CircleImageView;
public class FinishedBetHolder extends RecyclerView.ViewHolder {

    //String userdp,name, selectedteamlogo, selectedteamname, numberparticipant, numberpost, time, home_logo, away_logo, home_name, away_name, betstatus, betid;
    TextView textViewDetail, textViewName, textViewSelectedNam, textViewTime, textViewTeamA, textViewTeamB;
    ImageView imageViewSelectedTeamLogo, imageViewTeamA, imageViewTeamB;
    CircleImageView imageViewDp;
    LinearLayout linearLayoutTitleBackground, linearLayoutDetailBackground, linearLayoutRoundCornor, linearLayoutNameTeam;
    TextView textViewParticipants, textViewPost, textViewResult;
    ImageView imageView;
    View view;
    Context context;
    UserSessionManager userSessionManager;


    public FinishedBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        textViewDetail = (TextView) itemView.findViewById(R.id.textViewDetail);
        imageViewDp = (CircleImageView) view.findViewById(R.id.circleImageViewDp);
        imageViewSelectedTeamLogo = (ImageView) view.findViewById(R.id.imageViewSelectedLogo);
        imageViewTeamA = (ImageView) view.findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (ImageView) view.findViewById(R.id.imageViewTeamB);
        textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        textViewTeamA = (TextView) view.findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) view.findViewById(R.id.textViewTeamB);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        context = view.getContext();
        userSessionManager = new UserSessionManager(context);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        linearLayoutDetailBackground = (LinearLayout) view.findViewById(R.id.linearLayoutDetailBackground);
        linearLayoutRoundCornor = (LinearLayout) view.findViewById(R.id.linearLayoutRoundCornor);
        linearLayoutTitleBackground = (LinearLayout) view.findViewById(R.id.linearLayoutTitleBackground);
        linearLayoutNameTeam = (LinearLayout) view.findViewById(R.id.linearLayoutNameTeam);
        textViewParticipants = (TextView) view.findViewById(R.id.textViewParticipants);
        textViewPost = (TextView) view.findViewById(R.id.textViewPost);
        textViewResult = (TextView) view.findViewById(R.id.textViewResult);

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
        linearLayoutNameTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        //cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        linearLayoutTitleBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        linearLayoutDetailBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        textViewParticipants.setBackgroundResource(R.drawable.circular_bg_lightblue);
        textViewPost.setBackgroundResource(R.drawable.circular_bg_lightblue);
        linearLayoutRoundCornor.setBackgroundResource(R.drawable.roundcornor_lightblue);
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));

    }

    private void setMarronTheme() {
        linearLayoutNameTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        //cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        linearLayoutTitleBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        linearLayoutDetailBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        textViewParticipants.setBackgroundResource(R.drawable.circular_bg_marron);
        textViewPost.setBackgroundResource(R.drawable.circular_bg_marron);
        linearLayoutRoundCornor.setBackgroundResource(R.drawable.roundcornormarron);
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));

    }

    private void setGreenTheme() {
        linearLayoutNameTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        //cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        linearLayoutTitleBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        linearLayoutDetailBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        textViewParticipants.setBackgroundResource(R.drawable.circular_bg_green);
        textViewPost.setBackgroundResource(R.drawable.circular_bg_green);
        linearLayoutRoundCornor.setBackgroundResource(R.drawable.roundcornorgreen);
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

    }

    private void setPurpleTheme() {
        linearLayoutNameTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        //cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        linearLayoutTitleBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        linearLayoutDetailBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        textViewParticipants.setBackgroundResource(R.drawable.circular_bg_purlple);
        textViewPost.setBackgroundResource(R.drawable.circular_bg_purlple);
        linearLayoutRoundCornor.setBackgroundResource(R.drawable.roundcornorpurple);
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));

    }

    private void setBrownTheme() {
        linearLayoutNameTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        //cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        linearLayoutTitleBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        linearLayoutDetailBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        textViewParticipants.setBackgroundResource(R.drawable.circular_bg_brown);
        textViewPost.setBackgroundResource(R.drawable.circular_bg_brown);
        linearLayoutRoundCornor.setBackgroundResource(R.drawable.roundcornorbrown);
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));


    }

    private void setDefaultTheme() {
        linearLayoutNameTeam.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        //cardView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
        linearLayoutTitleBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        linearLayoutDetailBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        textViewParticipants.setBackgroundResource(R.drawable.circular_bg);
        textViewPost.setBackgroundResource(R.drawable.circular_bg);
        linearLayoutRoundCornor.setBackgroundResource(R.drawable.roundcornor);
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

    }
}