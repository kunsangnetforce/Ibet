package com.netforceinfotech.ibet1.solobet.recycler;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SoloBetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<SoloBetData> itemList;
    private Context context;
    String home_logo, away_logo, home_name, away_name,match_id;

    public SoloBetAdapter(Context context, List<SoloBetData> itemList, String home_logo, String away_logo, String home_name, String away_name,String match_id) {
        this.itemList = itemList;
        this.home_name = home_name;
        this.match_id=match_id;
        this.away_name = away_name;
        this.context = context;
        this.home_logo = home_logo;
        this.away_logo = away_logo;
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

        View view = inflater.inflate(R.layout.row_solo_odds, parent, false);
        SoloBetHolder viewHolder = new SoloBetHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.i("ibet_position", "" + position);
        SoloBetHolder soloBetHolder = (SoloBetHolder) holder;
        soloBetHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlaceSoloBetActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                bundle.putString("match_id", match_id);
                bundle.putString("bookerid", itemList.get(position).bookerid);
                bundle.putString("bookername", itemList.get(position).bookername);
                bundle.putString("home_odds", itemList.get(position).home_odds);
                bundle.putString("away_odds", itemList.get(position).away_odds);
                bundle.putString("draw_odds", itemList.get(position).draw_odds);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        soloBetHolder.textViewDrawOdds.setText(itemList.get(position).draw_odds);
        soloBetHolder.textViewAwayOdds.setText(itemList.get(position).away_odds);
        soloBetHolder.textViewHomeOdds.setText(itemList.get(position).home_odds);
        soloBetHolder.textViewBookerName.setText(itemList.get(position).bookername);


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
