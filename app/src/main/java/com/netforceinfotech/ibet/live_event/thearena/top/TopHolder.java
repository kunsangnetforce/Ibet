package com.netforceinfotech.ibet.live_event.thearena.top;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TopHolder extends RecyclerView.ViewHolder {


    TextView textViewDetail;
    TextView button;
    View view;


    public TopHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        button= (TextView) view.findViewById(R.id.buttonJoin);
        textViewDetail= (TextView) view.findViewById(R.id.textViewDetail);

    }
}