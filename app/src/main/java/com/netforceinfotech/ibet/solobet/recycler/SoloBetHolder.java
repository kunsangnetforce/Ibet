package com.netforceinfotech.ibet.solobet.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;


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