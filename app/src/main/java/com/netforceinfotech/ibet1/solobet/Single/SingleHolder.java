package com.netforceinfotech.ibet1.solobet.single;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/**
 * Created by John on 8/8/2016.
 */
public class SingleHolder extends RecyclerView.ViewHolder
{



    Button buttonClose;
    View view;


    public SingleHolder(View itemView)
    {

        super(itemView);
        //implementing onClickListener

        view = itemView;


    }
}
