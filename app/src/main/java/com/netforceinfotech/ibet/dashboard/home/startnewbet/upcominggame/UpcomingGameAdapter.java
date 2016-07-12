package com.netforceinfotech.ibet.dashboard.home.startnewbet.upcominggame;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class UpcomingGameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<UpcomingGameData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames=new ArrayList<>();

    public UpcomingGameAdapter(Context context, List<UpcomingGameData> itemList) {
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

        View view = inflater.inflate(R.layout.row_currentgame, parent, false);
        UpcomingGameHolder viewHolder = new UpcomingGameHolder(view);
        for (int i = 0; i < itemList.size(); i++) {
            if (i == 0) {
                booleanGames.add(true);
            } else {
                booleanGames.add(false);
            }
            Log.i("looppp",""+i);
        }

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(booleanGames.get(position)){
            ((UpcomingGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
        }
        else {
            ((UpcomingGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
        }
        Log.i("ibet_position", "" + position);
        UpcomingGameHolder upcomingGameHolder = (UpcomingGameHolder) holder;
        upcomingGameHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < itemList.size(); i++) {
                    if (i == position) {
                        ((UpcomingGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
                        booleanGames.set(position, true);
                    } else {
                        ((UpcomingGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
                        booleanGames.set(i, false);
                    }
                }
                notifyDataSetChanged();

            }
        });

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return 5;
//        return itemList.size();
    }
}
