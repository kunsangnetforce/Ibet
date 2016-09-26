package com.netforceinfotech.ibet.dashboard.home.detail_finished_bet;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

public class DetailBetHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    LinearLayout linearLayoutTitleBackground, linearLayoutDetailBackground, linearLayoutRoundCornor, linearLayoutNameTeam;
    TextView textViewParticipants, textViewPost, textViewResult;
    ImageView imageView;
    View view;
    Context context;
    UserSessionManager userSessionManager;


    public DetailBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        context = view.getContext();
        userSessionManager = new UserSessionManager(context);
        imageView = (ImageView) view.findViewById(R.id.imageView);
      //  cardView = (CardView) view.findViewById(R.id.cardview);
        linearLayoutDetailBackground = (LinearLayout) view.findViewById(R.id.linearLayoutDetailBackground);
        linearLayoutRoundCornor = (LinearLayout) view.findViewById(R.id.linearLayoutRoundCornor);
        linearLayoutTitleBackground = (LinearLayout) view.findViewById(R.id.linearLayoutTitleBackground);
        linearLayoutNameTeam = (LinearLayout) view.findViewById(R.id.linearLayoutNameTeam);
        textViewParticipants = (TextView) view.findViewById(R.id.textViewParticipants);
        textViewPost = (TextView) view.findViewById(R.id.textViewPost);
        textViewResult = (TextView) view.findViewById(R.id.textViewResult);

    }


}