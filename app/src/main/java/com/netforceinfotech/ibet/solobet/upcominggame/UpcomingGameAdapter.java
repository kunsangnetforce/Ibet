package com.netforceinfotech.ibet.solobet.upcominggame;


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
import com.netforceinfotech.ibet.R;

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
    int lastclicked = 0;

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
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final UpcomingGameHolder upcomingGameHolder = (UpcomingGameHolder) holder;
        if (position == lastclicked) {
            Log.i("kunsangcondition", "called");
            upcomingGameHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
            UpComingGamesFragment.match_id = itemList.get(position).match_id;
            UpComingGamesFragment.home_logo = itemList.get(position).home_logo;
            UpComingGamesFragment.away_logo = itemList.get(position).away_logo;
            UpComingGamesFragment.home_name = itemList.get(position).home_name;
            UpComingGamesFragment.away_name = itemList.get(position).away_name;
            UpComingGamesFragment.away_id = itemList.get(position).away_id;
            UpComingGamesFragment.home_id = itemList.get(position).home_id;

            Log.i("kunsangcondition", "called end" + itemList.get(position).match_id);
        } else {
            upcomingGameHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
        }
        upcomingGameHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastclicked = position;
                notifyDataSetChanged();

            }
        });
        upcomingGameHolder.textViewTeamA.setText(itemList.get(position).home_name);
        upcomingGameHolder.textViewTeamB.setText(itemList.get(position).away_name);
        upcomingGameHolder.textView.setText(itemList.get(position).home_name + " vs " + itemList.get(position).away_name);
        if (itemList.get(position).away_logo.length() > 1) {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingGameHolder.teamb);
        } else {
            upcomingGameHolder.teamb.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).home_logo.length() > 1) {
            Glide.with(context) .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.home_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingGameHolder.teama);
        } else {
            upcomingGameHolder.teama.setImageResource(R.drawable.ic_error);
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
