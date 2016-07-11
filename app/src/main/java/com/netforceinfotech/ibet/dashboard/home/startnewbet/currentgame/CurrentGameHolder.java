package com.netforceinfotech.ibet.dashboard.home.startnewbet.currentgame;

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


    TextView textViewTitle, textViewCategory, textViewPros;
    ImageView imageViewChecked;
    MaterialRippleLayout materialRippleLayout;
    View view;


    public CurrentGameHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        imageViewChecked = (ImageView) itemView.findViewById(R.id.imageViewChecked);
        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

    }
}