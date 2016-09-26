package com.netforceinfotech.ibet.dashboard.profile.selectteam.listofteam;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TeamListHolder extends RecyclerView.ViewHolder {


    TextView textViewTeamName;
    ImageView imageViewChecked,imageViewLogo;
    MaterialRippleLayout materialRippleLayout;
    LinearLayout linearLayout;
    View view;


    public TeamListHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        imageViewLogo= (ImageView) view.findViewById(R.id.logo);
        textViewTeamName= (TextView) view.findViewById(R.id.teamName);
        imageViewChecked = (ImageView) itemView.findViewById(R.id.imageViewChecked);
        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
        linearLayout= (LinearLayout) itemView.findViewById(R.id.linearLayoutMain);

    }
}