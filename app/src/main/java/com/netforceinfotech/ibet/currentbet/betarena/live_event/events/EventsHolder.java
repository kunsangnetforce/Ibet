package com.netforceinfotech.ibet.currentbet.betarena.live_event.events;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class EventsHolder extends RecyclerView.ViewHolder {


    View view;
LinearLayout linearLayouta,linearLayoutb;

    public EventsHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        linearLayouta= (LinearLayout) view.findViewById(R.id.linearLayouta);
        linearLayoutb= (LinearLayout) view.findViewById(R.id.linearLayoutb);

    }
}