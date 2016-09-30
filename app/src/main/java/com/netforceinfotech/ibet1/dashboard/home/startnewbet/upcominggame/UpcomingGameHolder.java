package com.netforceinfotech.ibet1.dashboard.home.startnewbet.upcominggame;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet1.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class UpcomingGameHolder extends RecyclerView.ViewHolder {

    TextView textView,textViewTeamA,textViewTeamB;
    ImageView imageViewChecked, teama, teamb;
    MaterialRippleLayout materialRippleLayout;
    View view;


    public UpcomingGameHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        textViewTeamA= (TextView) view.findViewById(R.id.textViewTeamA);
        textViewTeamB= (TextView) view.findViewById(R.id.textViewTeamB);
        imageViewChecked = (ImageView) itemView.findViewById(R.id.imageViewChecked);
        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
        textView = (TextView) view.findViewById(R.id.textView);
        teama = (ImageView) view.findViewById(R.id.imageViewTeamA);
        teamb = (ImageView) view.findViewById(R.id.imageViewTeamB);
    }
}