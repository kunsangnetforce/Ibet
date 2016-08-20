package com.netforceinfotech.ibet.live_event;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CurrentGameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<CurrentGameData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();

    public CurrentGameAdapter(Context context, List<CurrentGameData> itemList) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_live_event, parent, false);
        CurrentGameHolder viewHolder = new CurrentGameHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        CurrentGameHolder currentGameHolder = (CurrentGameHolder) holder;
        currentGameHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LiveEventActivity.class);
                String teamaid = itemList.get(position).teamaid;
                String teambid = itemList.get(position).teambid;
                String matchid = itemList.get(position).matchid;
                String teama = itemList.get(position).teama;
                String teamb = itemList.get(position).teamb;
                Bundle bundle = new Bundle();
                bundle.putString("teamaid", teamaid);
                bundle.putString("teambid", teambid);
                bundle.putString("teama", teama);
                bundle.putString("teamb", teamb);
                bundle.putString("matchid", matchid);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        currentGameHolder.teama.setText(itemList.get(position).teama);
        currentGameHolder.teamb.setText(itemList.get(position).teamb);
        if (itemList.get(position).logoa.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).logoa)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(currentGameHolder.logoteama);
        } else {
            currentGameHolder.logoteama.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).logob.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).logob)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(currentGameHolder.logoteamb);
        } else {
            currentGameHolder.logoteamb.setImageResource(R.drawable.ic_error);
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }
}
