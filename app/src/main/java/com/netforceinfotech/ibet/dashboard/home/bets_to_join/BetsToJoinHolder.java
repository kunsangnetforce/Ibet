package com.netforceinfotech.ibet.dashboard.home.bets_to_join;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class BetsToJoinHolder extends RecyclerView.ViewHolder {

    TextView textViewDetail, textViewName, textViewSelectedName, textViewNumberOfParticipants, textViewNumberPost, textViewTime, textViewTeamA, textViewTeamB, textViewBetStatus;
    ImageView imageViewSelectedTeamLogo, imageViewTeamA, imageViewTeamB;
    CircleImageView imageViewDp;
    View view;


    public BetsToJoinHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        textViewDetail = (TextView) itemView.findViewById(R.id.textViewDetail);
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
        textViewName= (TextView) view.findViewById(R.id.textViewName);
        textViewBetStatus= (TextView) view.findViewById(R.id.textViewResult);

    }
}