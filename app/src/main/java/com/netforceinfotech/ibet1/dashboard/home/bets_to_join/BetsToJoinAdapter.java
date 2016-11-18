package com.netforceinfotech.ibet1.dashboard.home.bets_to_join;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.home.bets_to_join.detail_bet_to_join.AcceptBetActivity;
import com.netforceinfotech.ibet1.dashboard.home.bets_to_join.detail_bet_to_join.DetailBetToJoin;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                Intent intent = new Intent(context, AcceptBetActivity.class);
                Bundle bundle = new Bundle();
                 /*
            *  Bundle bundle = new Bundle();
                bundle.putString("match_id", match_id);
                bundle.putString("bet_id", bet_id);
                bundle.putString("home_name", home_name);
                bundle.putString("home_id", home_id);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_name", away_name);
                bundle.putString("away_id", away_id);
                bundle.putString("away_logo", away_logo);
                bundle.putString("bet_option", "0");
            * */

                bundle.putString("home_logo", itemList.get(position).teamalogo);
                bundle.putString("away_logo", itemList.get(position).teamblogo);
                bundle.putString("bet_id", itemList.get(position).betid);
                bundle.putString("home_name", itemList.get(position).teamaname);
                bundle.putString("away_name", itemList.get(position).teambname);
                bundle.putString("match_id", itemList.get(position).matchid);
                bundle.putString("bet_option", itemList.get(position).bet_option);
                bundle.putString("bet_amount", itemList.get(position).bet_amount);
                bundle.putString("creator_id", itemList.get(position).creator_id);
                Debugger.i("kbetid", itemList.get(position).betid);
                intent.putExtras(bundle);
                context.startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(R.anim.enter, R.anim.exit);
            }
        });
        betsToJoinHolder.textViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailBetToJoin.class);
                Bundle bundle = new Bundle();

                bundle.putString("home_logo", itemList.get(position).teamalogo);
                bundle.putString("away_logo", itemList.get(position).teamblogo);
                bundle.putString("bet_id", itemList.get(position).betid);
                bundle.putString("home_name", itemList.get(position).teamaname);
                bundle.putString("away_name", itemList.get(position).teambname);
                bundle.putString("bet_option", itemList.get(position).bet_option);
                bundle.putString("bet_amount", itemList.get(position).bet_amount);
                bundle.putString("match_id", itemList.get(position).matchid);
                Debugger.i("kbetid", itemList.get(position).betid);
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
                    .into(betsToJoinHolder.imageViewDp);
        } else {
            betsToJoinHolder.imageViewDp.setImageResource(R.drawable.ic_error);
        }

        if (itemList.get(position).teamalogo.length() > 1) {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))

                    .load(R.drawable.home_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(betsToJoinHolder.imageViewTeamA);
        } else {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))

                    .load(R.drawable.home_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(betsToJoinHolder.imageViewTeamA);
        }
        if (itemList.get(position).teamblogo.length() > 1) {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(betsToJoinHolder.imageViewTeamB);
        } else {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(betsToJoinHolder.imageViewTeamB);
        }

        betsToJoinHolder.textViewName.setText(itemList.get(position).name);
        betsToJoinHolder.textViewParticipants.setText(itemList.get(position).numberparticipant);
        betsToJoinHolder.textViewTeamA.setText(itemList.get(position).teamaname);
        betsToJoinHolder.textViewTeamB.setText(itemList.get(position).teambname);
        String formattedTime = getFormattedDate(itemList.get(position).time);
        if (formattedTime == null) {
            betsToJoinHolder.textViewTime.setText("NA");
        } else {
            betsToJoinHolder.textViewTime.setText(formattedTime);
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
}
