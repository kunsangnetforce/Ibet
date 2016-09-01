package com.netforceinfotech.ibet.currentbet.livebet;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.currentbet.betarena.EnterBetArenaActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class LiveBetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<LiveBetData> itemList;
    private Context context;

    public LiveBetAdapter(Context context, List<LiveBetData> itemList) {
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
        LiveBetHolder viewHolder = new LiveBetHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
/*
        LiveBetHolder liveBetHolder = (LiveBetHolder) holder;

        liveBetHolder.textViewEnterBetArena.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, EnterBetArenaActivity.class);
                context.startActivity(intent);
            }
        });
*/
        Log.i("ibet_position", "" + position);
        LiveBetHolder betsToJoinHolder = (LiveBetHolder) holder;
        betsToJoinHolder.textViewEnterBetArena.setOnClickListener(new View.OnClickListener() {
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
                    .into(betsToJoinHolder.imageViewDp);
        } else {
            betsToJoinHolder.imageViewDp.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).selectedteamlogo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).selectedteamlogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(betsToJoinHolder.imageViewSelectedTeamLogo);
        } else {
            betsToJoinHolder.imageViewSelectedTeamLogo.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).teamalogo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).teamalogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(betsToJoinHolder.imageViewTeamA);
        } else {
            betsToJoinHolder.imageViewTeamA.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).teamblogo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).teamblogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(betsToJoinHolder.imageViewTeamB);
        } else {
            betsToJoinHolder.imageViewTeamB.setImageResource(R.drawable.ic_error);
        }

        betsToJoinHolder.textViewName.setText(itemList.get(position).name);
        betsToJoinHolder.textViewSelectedName.setText(itemList.get(position).selectedteamname);
        betsToJoinHolder.textViewNumberOfParticipants.setText(itemList.get(position).numberparticipant);
        betsToJoinHolder.textViewNumberPost.setText(itemList.get(position).numberpost);
        betsToJoinHolder.textViewTeamA.setText(itemList.get(position).teamaname);
        betsToJoinHolder.textViewTeamB.setText(itemList.get(position).teambname);
        //  betsToJoinHolder.textViewBetStatus.setText(itemList.get(position).betstatus);
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        //  return 5;
        return itemList.size();
    }
}
