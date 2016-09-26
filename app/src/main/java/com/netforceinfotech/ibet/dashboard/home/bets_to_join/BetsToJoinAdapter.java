package com.netforceinfotech.ibet.dashboard.home.bets_to_join;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.detail_bet_to_join.DetailBetToJoin;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BetsToJoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<BetsToJoinData> itemList;
    private Context context;

    public BetsToJoinAdapter(Context context, List<BetsToJoinData> itemList) {
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

        View view = inflater.inflate(R.layout.row_bets_to_join, parent, false);
        BetsToJoinHolder viewHolder = new BetsToJoinHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.i("ibet_position", "" + position);
        BetsToJoinHolder betsToJoinHolder = (BetsToJoinHolder) holder;
        betsToJoinHolder.textViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailBetToJoin.class);
                context.startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        betsToJoinHolder.textViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailBetToJoin.class);
                context.startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(R.anim.enter, R.anim.exit);
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
        betsToJoinHolder.textViewParticipants.setText(itemList.get(position).numberparticipant);
        betsToJoinHolder.textViewPost.setText(itemList.get(position).numberpost);
        betsToJoinHolder.textViewTeamA.setText(itemList.get(position).teamaname);
        betsToJoinHolder.textViewTeamB.setText(itemList.get(position).teambname);
      //  betsToJoinHolder.textViewBetStatus.setText(itemList.get(position).betstatus);
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
