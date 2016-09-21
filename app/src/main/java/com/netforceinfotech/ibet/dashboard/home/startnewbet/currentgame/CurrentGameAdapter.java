package com.netforceinfotech.ibet.dashboard.home.startnewbet.currentgame;


import android.content.Context;
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


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_currentgame, parent, false);
        CurrentGameHolder viewHolder = new CurrentGameHolder(view);
        for (int i = 0; i < itemList.size(); i++) {
            if (i == 0) {
                booleanGames.add(true);
                CurrentGameFragment.match_id = itemList.get(i).match_id;
            } else {
                booleanGames.add(false);
            }
            Log.i("looppp", "" + i);


        }
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (booleanGames.get(position)) {
            ((CurrentGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
        } else {
            ((CurrentGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
        }
        Log.i("ibet_position", "" + position);
        final CurrentGameHolder currentGameHolder = (CurrentGameHolder) holder;
        currentGameHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < itemList.size(); i++) {
                    if (i == position) {
                        currentGameHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
                        booleanGames.set(position, true);
                        CurrentGameFragment.match_id = itemList.get(position).match_id;
                        CurrentGameFragment.home_logo = itemList.get(position).home_logo;
                        CurrentGameFragment.away_logo = itemList.get(position).away_logo;
                        CurrentGameFragment.home_name = itemList.get(position).home_name;
                        CurrentGameFragment.away_name = itemList.get(position).away_name;
                        CurrentGameFragment.away_id = itemList.get(position).away_id;
                        CurrentGameFragment.home_id = itemList.get(position).home_id;

                    } else {
                        currentGameHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
                        booleanGames.set(i, false);
                    }
                }
                notifyDataSetChanged();
            }
        });
        currentGameHolder.textViewTeamA.setText(itemList.get(position).home_name);
        currentGameHolder.textViewTeamB.setText(itemList.get(position).away_name);
        currentGameHolder.textView.setText(itemList.get(position).home_name + " vs " + itemList.get(position).away_name);
        if (itemList.get(position).away_logo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).away_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(currentGameHolder.teamb);
        } else {
            currentGameHolder.teamb.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).home_logo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).home_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(currentGameHolder.teama);
        } else {
            currentGameHolder.teama.setImageResource(R.drawable.ic_error);
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
