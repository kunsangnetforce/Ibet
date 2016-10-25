package com.netforceinfotech.ibet1.solobet.recycler;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;


public class SoloBetHolder extends RecyclerView.ViewHolder {

    LinearLayout linearLayout;
    TextView textViewBookerName,textViewHomeOdds,textViewDrawOdds,textViewAwayOdds;

    public SoloBetHolder(View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        textViewBookerName= (TextView) itemView.findViewById(R.id.textviewBookerName);
        textViewHomeOdds= (TextView) itemView.findViewById(R.id.textviewHomeOdds);
        textViewDrawOdds= (TextView) itemView.findViewById(R.id.textviewDrawOdds);
        textViewAwayOdds= (TextView) itemView.findViewById(R.id.textviewAwayOdds);
        //implementing onClickListener


    }


}