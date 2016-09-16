package com.netforceinfotech.ibet.dashboard.home.create_bet.searchfriend.friend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SearchFriendHolder extends RecyclerView.ViewHolder {


    TextView textViewName;
    CircleImageView imageView;
    MaterialRippleLayout materialRippleLayout;
    View view;


    public SearchFriendHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        imageView = (CircleImageView) view.findViewById(R.id.imageViewProfilePic);
        materialRippleLayout= (MaterialRippleLayout) view.findViewById(R.id.ripple);

    }
}