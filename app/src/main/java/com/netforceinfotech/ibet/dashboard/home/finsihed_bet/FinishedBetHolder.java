package com.netforceinfotech.ibet.dashboard.home.finsihed_bet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class FinishedBetHolder extends RecyclerView.ViewHolder {


    TextView textViewDetail;
    View view;


    public FinishedBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        textViewDetail= (TextView) itemView.findViewById(R.id.textViewDetail);
        view = itemView;

    }
}