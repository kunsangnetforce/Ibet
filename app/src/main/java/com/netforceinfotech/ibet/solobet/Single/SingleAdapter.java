package com.netforceinfotech.ibet.solobet.Single;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.currentbet.betarena.EnterBetArenaActivity;
import com.netforceinfotech.ibet.currentbet.livebet.LiveBetData;
import com.netforceinfotech.ibet.currentbet.livebet.LiveBetHolder;

import java.util.List;

/**
 * Created by John on 8/8/2016.
 */



public class SingleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<SingleBetData> itemList;
    private Context context;


    public SingleAdapter(Context context, List<SingleBetData> itemList)
    {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }



    /*  @Override
      public int getItemViewType(int position) {
          if (itemList.get(position).image.isEmpty()) {
              return SIMPLE_TYPE;
          } else {
              return IMAGE_TYPE;
          }
      }
    */




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.row_single, parent, false);
        SingleHolder viewHolder = new SingleHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
       SingleHolder liveBetHolder = (SingleHolder) holder;



    }
    private void showMessage(String s)
    {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount()
    {
        return 5;
        //        return itemList.size();
    }




}
