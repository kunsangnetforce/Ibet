package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.detail.stand.match_chat.all;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.ibet1.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class AllHolder extends RecyclerView.ViewHolder {


    CircleImageView circleImageView;
    ImageView imageViewShare,imageViewLike,imageViewDislike,imageViewMessage;
    TextView textViewName,textViewDate,textViewTime,textViewComment,textViewSC,textViewDC,textViewLC,textViewCC;
    View view;


    public AllHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        circleImageView= (CircleImageView) view.findViewById(R.id.imageView);
        textViewName= (TextView) view.findViewById(R.id.textViewName);
        textViewDate= (TextView) view.findViewById(R.id.textViewDate);
        textViewTime= (TextView) view.findViewById(R.id.textViewTime);
        textViewComment= (TextView) view.findViewById(R.id.textViewComment);
        textViewSC= (TextView) view.findViewById(R.id.textViewSC);
        textViewDC= (TextView) view.findViewById(R.id.textViewDC);
        textViewLC= (TextView) view.findViewById(R.id.textViewLC);
        textViewCC= (TextView) view.findViewById(R.id.textViewCC);
        imageViewDislike= (ImageView) view.findViewById(R.id.imageViewDislike);
        imageViewLike= (ImageView) view.findViewById(R.id.imageViewLike);
        imageViewShare= (ImageView) view.findViewById(R.id.imageViewShare);
        imageViewMessage= (ImageView) view.findViewById(R.id.imageViewMessage);
    }
}