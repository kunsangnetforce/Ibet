package com.netforceinfotech.ibet.currentbet.betarena.betdetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class DetailBetHolder extends RecyclerView.ViewHolder {


    TextView textViewTitle, textViewCategory, textViewPros;
    ImageView imageView;
    View view;


    public DetailBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        imageView= (ImageView) view.findViewById(R.id.imageView);

    }
}