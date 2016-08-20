package com.netforceinfotech.ibet.live_event;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CurrentGameHolder extends RecyclerView.ViewHolder {


    TextView teama, teamb;
    ImageView logoteama, logoteamb;
    MaterialRippleLayout materialRippleLayout;
    View view;


    public CurrentGameHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
        teama = (TextView) view.findViewById(R.id.textViewTeamA);
        teamb = (TextView) view.findViewById(R.id.textViewTeamB);
        logoteama = (ImageView) view.findViewById(R.id.imageViewTeamA);
        logoteamb = (ImageView) view.findViewById(R.id.imageViewTeamB);
    }
}