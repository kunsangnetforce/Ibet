package com.netforceinfotech.ibet.currentbet.betarena.live_event.events;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class EventsHolder extends RecyclerView.ViewHolder {


    View view;


    public EventsHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;

    }
}