package com.netforceinfotech.ibet.currentbet.betarena.live_event.events;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class EventsHolder extends RecyclerView.ViewHolder {


    View view;
    LinearLayout linearLayouta, linearLayoutb;
    ImageView imageViewTypea,imageViewTypeb;
    TextView textViewNamea,textViewNameb,textViewTime;

    public EventsHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        textViewTime= (TextView) view.findViewById(R.id.textViewTime);
        textViewNamea= (TextView) view.findViewById(R.id.textViewNamea);
        textViewNameb= (TextView) view.findViewById(R.id.textViewNameb);
        imageViewTypea= (ImageView) view.findViewById(R.id.imageViewTypea);
        imageViewTypeb= (ImageView) view.findViewById(R.id.imageViewTypeb);
        linearLayouta = (LinearLayout) view.findViewById(R.id.linearLayouta);
        linearLayoutb = (LinearLayout) view.findViewById(R.id.linearLayoutb);

    }
}