package com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.comments_comment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CCHolder extends RecyclerView.ViewHolder {


    CircleImageView circleImageView;
    TextView textViewName,textViewDate,textViewTime,textViewComment;
    View view;


    public CCHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        circleImageView= (CircleImageView) view.findViewById(R.id.imageView);
        textViewName= (TextView) view.findViewById(R.id.textViewName);
        textViewDate= (TextView) view.findViewById(R.id.textViewDate);
        textViewTime= (TextView) view.findViewById(R.id.textViewTime);
        textViewComment= (TextView) view.findViewById(R.id.textViewComment);
    }
}