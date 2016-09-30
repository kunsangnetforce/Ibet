package com.netforceinfotech.ibet1.currentbet.betarena.stats.table;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.netforceinfotech.ibet1.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TableHolder extends RecyclerView.ViewHolder {


    View view;
    ImageView imageView;


    public TableHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        imageView = (ImageView) view.findViewById(R.id.imageView);

    }
}