package com.netforceinfotech.ibet.dashboard.home.detail_bet_to_join;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class DetailBetHolder extends RecyclerView.ViewHolder {


    Button buttonClose;
    View view;
    ImageView imageView;

    public DetailBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        imageView= (ImageView) view.findViewById(R.id.imageView);

    }
}