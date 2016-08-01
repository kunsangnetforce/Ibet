package com.netforceinfotech.ibet.currentbet.upcoming;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class UpcomingBetHolder extends RecyclerView.ViewHolder {


    TextView textViewEnterBetArena;
    View view;


    public UpcomingBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        textViewEnterBetArena = (TextView) itemView.findViewById(R.id.buttonEnterBetArena);
        view = itemView;

    }
}