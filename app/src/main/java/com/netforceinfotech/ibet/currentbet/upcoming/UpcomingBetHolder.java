package com.netforceinfotech.ibet.currentbet.upcoming;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class UpcomingBetHolder extends RecyclerView.ViewHolder {

    TextView textViewEnterBetArena, textViewName, textViewSelectedName, textViewNumberOfParticipants, textViewNumberPost, textViewTime, textViewTeamA, textViewTeamB, textViewBetStatus;
    ImageView imageViewSelectedTeamLogo, imageViewTeamA, imageViewTeamB;
    CircleImageView imageViewDp;
    View view;


    public UpcomingBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        textViewEnterBetArena = (TextView) itemView.findViewById(R.id.buttonEnterBetArena);

        view = itemView;
        imageViewDp = (CircleImageView) view.findViewById(R.id.circleImageViewDp);
        imageViewSelectedTeamLogo = (ImageView) view.findViewById(R.id.imageViewSelectedLogo);
        imageViewTeamA = (ImageView) view.findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (ImageView) view.findViewById(R.id.imageViewTeamB);
        textViewSelectedName = (TextView) view.findViewById(R.id.textViewSelectedName);
        textViewNumberOfParticipants = (TextView) view.findViewById(R.id.textViewParticipants);
        textViewNumberPost = (TextView) view.findViewById(R.id.textViewPost);
        textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        textViewTeamA = (TextView) view.findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) view.findViewById(R.id.textViewTeamB);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
    }
}