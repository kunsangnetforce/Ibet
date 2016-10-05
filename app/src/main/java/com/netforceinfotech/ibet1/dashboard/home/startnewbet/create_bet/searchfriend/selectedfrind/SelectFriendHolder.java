package com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.selectedfrind;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.netforceinfotech.ibet1.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SelectFriendHolder extends RecyclerView.ViewHolder {


    ImageView imageViewClose, ImageViewLogo;
    View view;


    public SelectFriendHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        ImageViewLogo = (ImageView) view.findViewById(R.id.imageViewLogo);
        imageViewClose = (ImageView) view.findViewById(R.id.imageViewClose);
    }
}