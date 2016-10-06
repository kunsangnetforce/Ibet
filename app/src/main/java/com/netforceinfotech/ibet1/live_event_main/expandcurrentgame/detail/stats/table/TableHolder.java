package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.detail.stats.table;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet1.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TableHolder extends RecyclerView.ViewHolder {


    View view;
    ImageView imageView;
    TextView textViewName, textViewPosition, textViewGP, textViewGD, textViewPoints;


    public TableHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        imageView = (ImageView) view.findViewById(R.id.imageView);
        textViewGD = (TextView) view.findViewById(R.id.textViewGD);
        textViewGP = (TextView) view.findViewById(R.id.textViewGP);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewPosition = (TextView) view.findViewById(R.id.textViewRank);
        textViewPoints = (TextView) view.findViewById(R.id.textViewPoints);

    }
}