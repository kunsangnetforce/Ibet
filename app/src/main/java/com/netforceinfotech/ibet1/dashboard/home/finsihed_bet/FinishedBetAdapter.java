package com.netforceinfotech.ibet1.dashboard.home.finsihed_bet;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.home.finsihed_bet.detail_finished_bet.DetailFinishedBet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class FinishedBetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<FinsihedData> itemList;
    private Context context;

    public FinishedBetAdapter(Context context, List<FinsihedData> itemList) {
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

        View view = inflater.inflate(R.layout.row_finishedbet, parent, false);
        FinishedBetHolder viewHolder = new FinishedBetHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.i("ibet_position", "" + position);
        FinishedBetHolder finishedBetHolder = (FinishedBetHolder) holder;
        finishedBetHolder.textViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailFinishedBet.class);
                context.startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        if (itemList.get(position).userdp.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).userdp)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(finishedBetHolder.imageViewDp);
        } else {
            finishedBetHolder.imageViewDp.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).selectedteamlogo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).selectedteamlogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(finishedBetHolder.imageViewSelectedTeamLogo);
        } else {
            finishedBetHolder.imageViewSelectedTeamLogo.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).teamalogo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).teamalogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(finishedBetHolder.imageViewTeamA);
        } else {
            finishedBetHolder.imageViewTeamA.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).teamblogo.length() > 1) {
            Picasso.with(context)
                    .load(itemList.get(position).teamblogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(finishedBetHolder.imageViewTeamB);
        } else {
            finishedBetHolder.imageViewTeamB.setImageResource(R.drawable.ic_error);
        }

        finishedBetHolder.textViewName.setText(itemList.get(position).name);
        finishedBetHolder.textViewTeamA.setText(itemList.get(position).teamaname);
        finishedBetHolder.textViewTeamB.setText(itemList.get(position).teambname);
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        //return 5;
        return itemList.size();
    }
}
