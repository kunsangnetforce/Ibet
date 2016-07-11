package com.netforceinfotech.ibet.dashboard.home.bets_to_join;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class BetsToJoinHolder extends RecyclerView.ViewHolder {


    TextView textViewDetail;
    View view;


    public BetsToJoinHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        textViewDetail= (TextView) view.findViewById(R.id.textViewDetail);

    }
}