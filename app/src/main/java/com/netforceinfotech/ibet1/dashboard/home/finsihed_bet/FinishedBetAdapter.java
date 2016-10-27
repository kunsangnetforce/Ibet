package com.netforceinfotech.ibet1.dashboard.home.finsihed_bet;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.home.finsihed_bet.detail_finished_bet.DetailFinishedBet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                Bundle bundle = new Bundle();
                bundle.putString("home_logo", itemList.get(position).teamalogo);
                bundle.putString("away_logo", itemList.get(position).teamblogo);
                bundle.putString("bet_id", itemList.get(position).betid);
                bundle.putString("home_name", itemList.get(position).teamaname);
                bundle.putString("away_name", itemList.get(position).teambname);
                intent.putExtras(bundle);
                context.startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        if (itemList.get(position).userdp.length() > 1) {
            Glide.with(context)
                    .load(itemList.get(position).userdp)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(finishedBetHolder.imageViewDp);
        } else {
            finishedBetHolder.imageViewDp.setImageResource(R.drawable.ic_error);
        }

        if (itemList.get(position).teamalogo.length() > 1) {
            Glide.with(context)
                    .load(itemList.get(position).teamalogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(finishedBetHolder.imageViewTeamA);
        } else {
            finishedBetHolder.imageViewTeamA.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).teamblogo.length() > 1) {
            Glide.with(context)
                    .load(itemList.get(position).teamblogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(finishedBetHolder.imageViewTeamB);
        } else {
            finishedBetHolder.imageViewTeamB.setImageResource(R.drawable.ic_error);
        }
        finishedBetHolder.textViewResult.setText(itemList.get(position).betstatus);
        finishedBetHolder.textViewName.setText(itemList.get(position).name);
        finishedBetHolder.textViewTeamA.setText(itemList.get(position).teamaname);
        finishedBetHolder.textViewTeamB.setText(itemList.get(position).teambname);
        finishedBetHolder.textViewParticipants.setText(itemList.get(position).numberparticipant);
        finishedBetHolder.textViewTime.setText(itemList.get(position).time);
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public String getFormattedDate(String date) {
        Date date2 = new Date();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            date2 = date_format.parse(date);
            SimpleDateFormat outDate = new SimpleDateFormat("EEE dd MMM yyyy hh:mm");
            return outDate.format(date2);
        } catch (ParseException e) {
            SimpleDateFormat date_format1 = new SimpleDateFormat("yyyy-MM-dd ");
            try {
                date2 = date_format1.parse(date);
                SimpleDateFormat outDate1 = new SimpleDateFormat("EEE dd MMM yyyy");
                return outDate1.format(date2);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }
    @Override
    public int getItemCount() {
        //return 5;
        return itemList.size();
    }
}
