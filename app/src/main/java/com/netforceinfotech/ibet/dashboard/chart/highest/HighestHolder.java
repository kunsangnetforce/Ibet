package com.netforceinfotech.ibet.dashboard.chart.highest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 1
 * Created by asdf on 7/21/2016.
 */
public class HighestHolder extends RecyclerView.ViewHolder {


    TextView textViewName, textViewLevel, textViewRank;
    CircleImageView imageViewProfilePic;
    View view;


    public HighestHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        textViewRank = (TextView) view.findViewById(R.id.textViewRank);
        textViewLevel = (TextView) view.findViewById(R.id.textViewLevel);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        imageViewProfilePic = (CircleImageView) view.findViewById(R.id.imageViewProfilePic);

    }
}