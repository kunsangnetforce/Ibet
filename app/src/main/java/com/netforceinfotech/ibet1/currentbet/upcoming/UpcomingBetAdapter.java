package com.netforceinfotech.ibet1.currentbet.upcoming;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.currentbet.betarena.EnterBetArenaActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class UpcomingBetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<UpcomingBetData> itemList;
    private Context context;

    public UpcomingBetAdapter(Context context, List<UpcomingBetData> itemList) {
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

        View view = inflater.inflate(R.layout.row_livebet, parent, false);
        UpcomingBetHolder viewHolder = new UpcomingBetHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        UpcomingBetHolder upcomingBetHolder = (UpcomingBetHolder) holder;
        upcomingBetHolder.textViewEnterBetArena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EnterBetArenaActivity.class);
                context.startActivity(intent);
            }
        });

        if (itemList.get(position).userdp.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).userdp)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingBetHolder.imageViewDp);
        } else {
            upcomingBetHolder.imageViewDp.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).selectedteamlogo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).selectedteamlogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingBetHolder.imageViewSelectedTeamLogo);
        } else {
            upcomingBetHolder.imageViewSelectedTeamLogo.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).teamalogo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).teamalogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingBetHolder.imageViewTeamA);
        } else {
            upcomingBetHolder.imageViewTeamA.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).teamblogo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).teamblogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingBetHolder.imageViewTeamB);
        } else {
            upcomingBetHolder.imageViewTeamB.setImageResource(R.drawable.ic_error);
        }

        upcomingBetHolder.textViewName.setText(itemList.get(position).name);
        upcomingBetHolder.textViewTeamA.setText(itemList.get(position).teamaname);
        upcomingBetHolder.textViewTeamB.setText(itemList.get(position).teambname);


    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
//        return 5;
        return itemList.size();
    }
}
