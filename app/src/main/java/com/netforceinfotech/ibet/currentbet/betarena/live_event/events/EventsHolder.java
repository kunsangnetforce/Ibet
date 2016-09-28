package com.netforceinfotech.ibet.currentbet.betarena.live_event.events;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class EventsHolder extends RecyclerView.ViewHolder {


    View view;
    LinearLayout linearLayouta, linearLayoutb, linearLayouta1, linearLayoutb1;
    ImageView imageViewTypea, imageViewTypeb, imageViewTypea1, imageViewTypeb1;
    TextView textViewNamea, textViewNameb, textViewNamea1, textViewNameb1, textViewTime;
    UserSessionManager userSessionManager;
    Context context;

    public EventsHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        context = view.getContext();
        userSessionManager = new UserSessionManager(context);
        linearLayouta1 = (LinearLayout) view.findViewById(R.id.linearLayouta1);
        linearLayoutb1 = (LinearLayout) view.findViewById(R.id.linearLayoutb1);
        imageViewTypea1 = (ImageView) view.findViewById(R.id.imageViewTypea1);
        imageViewTypeb1 = (ImageView) view.findViewById(R.id.imageViewTypeb1);
        textViewTime = (TextView) view.findViewById(R.id.textViewTime);
        textViewNamea1 = (TextView) view.findViewById(R.id.textViewNamea1);
        textViewNameb1 = (TextView) view.findViewById(R.id.textViewNameb1);
        textViewNamea = (TextView) view.findViewById(R.id.textViewNamea);
        textViewNameb = (TextView) view.findViewById(R.id.textViewNameb);
        imageViewTypea = (ImageView) view.findViewById(R.id.imageViewTypea);
        imageViewTypeb = (ImageView) view.findViewById(R.id.imageViewTypeb);
        linearLayouta = (LinearLayout) view.findViewById(R.id.linearLayouta);
        linearLayoutb = (LinearLayout) view.findViewById(R.id.linearLayoutb);

        switch (userSessionManager.getTheme()) {
            case 0:
                setDefaultTheme();
                break;
            case 1:
                setBrownTheme();
                break;
            case 2:
                setPurpleTheme();
                break;
            case 3:
                setGreenTheme();
                break;
            case 4:
                setMarronTheme();
                break;
            case 5:
                setLightBlueTheme();
                break;

        }
    }

    private void setLightBlueTheme() {
        linearLayouta.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        linearLayouta1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        linearLayoutb.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        linearLayoutb1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
    }

    private void setMarronTheme() {
        linearLayouta.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        linearLayouta1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        linearLayoutb.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        linearLayoutb1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
    }

    private void setGreenTheme() {
        linearLayouta.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        linearLayouta1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        linearLayoutb.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        linearLayoutb1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
    }

    private void setPurpleTheme() {
        linearLayouta.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        linearLayouta1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        linearLayoutb.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        linearLayoutb1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
    }

    private void setBrownTheme() {

        linearLayouta.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        linearLayouta1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        linearLayoutb.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        linearLayoutb1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
    }

    private void setDefaultTheme() {
        linearLayouta.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        linearLayouta1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        linearLayoutb.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        linearLayoutb1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
    }
}