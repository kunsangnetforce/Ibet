package com.netforceinfotech.ibet1.dashboard.home.startnewbet.currentgame;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.netforceinfotech.ibet1.R;
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
    int lastclicked = 0;

    public CurrentGameAdapter(Context context, List<CurrentGameData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_currentgame, parent, false);
        CurrentGameHolder viewHolder = new CurrentGameHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final CurrentGameHolder currentGameHolder = (CurrentGameHolder) holder;
        if (position == lastclicked) {
            CurrentGameFragment.match_id = itemList.get(position).match_id;
            CurrentGameFragment.home_logo = itemList.get(position).home_logo;
            CurrentGameFragment.away_logo = itemList.get(position).away_logo;
            CurrentGameFragment.home_name = itemList.get(position).home_name;
            CurrentGameFragment.away_name = itemList.get(position).away_name;
            CurrentGameFragment.away_id = itemList.get(position).away_id;
            CurrentGameFragment.home_id = itemList.get(position).home_id;
            currentGameHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
        } else {
            currentGameHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
        }
        Log.i("ibet_position", "" + position);
        currentGameHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastclicked = position;
                notifyDataSetChanged();
            }
        });
        currentGameHolder.textViewTeamA.setText(itemList.get(position).home_name);
        currentGameHolder.textViewTeamB.setText(itemList.get(position).away_name);
        currentGameHolder.textView.setText(itemList.get(position).home_name + " vs " + itemList.get(position).away_name);
        if (itemList.get(position).away_logo.length() > 1) {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(currentGameHolder.teamb);
        } else {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(currentGameHolder.teamb);
        }
        if (itemList.get(position).home_logo.length() > 1) {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.home_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(currentGameHolder.teama);
        } else {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.home_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(currentGameHolder.teama);
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
