package com.netforceinfotech.ibet.currentbet.livebet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class LiveBetHolder extends RecyclerView.ViewHolder {


     final TextView textViewEnterBetArena;
    Button buttonClose;
    View view;


    public LiveBetHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        textViewEnterBetArena = (TextView) itemView.findViewById(R.id.buttonEnterBetArena);

        view = itemView;

    }
}