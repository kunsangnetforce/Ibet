package com.netforceinfotech.ibet.profilesetting.selectteam.selectedteam;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SelectTeamHolder extends RecyclerView.ViewHolder {


    ImageView imageViewClose, ImageViewLogo;
    TextView textViewName;
    View view;


    public SelectTeamHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        textViewName= (TextView) itemView.findViewById(R.id.textViewName);
        ImageViewLogo = (ImageView) view.findViewById(R.id.imageViewLogo);
        imageViewClose = (ImageView) view.findViewById(R.id.imageViewClose);
    }
}