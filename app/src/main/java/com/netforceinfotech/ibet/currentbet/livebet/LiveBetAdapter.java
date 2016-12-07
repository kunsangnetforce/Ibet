package com.netforceinfotech.ibet.currentbet.livebet;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.currentbet.betarena.EnterBetArenaActivity;
import com.netforceinfotech.ibet.currentbet.livebet.detail_live_bet.DetailLiveBet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

        LiveBetHolder upcomingBetHolder = (LiveBetHolder) holder;
        upcomingBetHolder.textViewEnterBetArena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EnterBetArenaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("away_id", itemList.get(position).away_id);
                bundle.putString("home_id", itemList.get(position).home_id);
                bundle.putString("home_logo", itemList.get(position).teamalogo);
                bundle.putString("away_logo", itemList.get(position).teamblogo);
                bundle.putString("bet_id", itemList.get(position).betid);
                bundle.putString("match_id", itemList.get(position).matchid);
                bundle.putString("home_name", itemList.get(position).teamaname);
                bundle.putString("away_name", itemList.get(position).teambname);
                bundle.putString("season_id", itemList.get(position).seasonid);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        upcomingBetHolder.textViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailLiveBet.class);
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
                    .into(upcomingBetHolder.imageViewDp);
        } else {
            upcomingBetHolder.imageViewDp.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).selectedteamlogo.length() > 1) {
            Glide.with(context)
                    .load(itemList.get(position).selectedteamlogo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingBetHolder.imageViewSelectedTeamLogo);
        } else {
            upcomingBetHolder.imageViewSelectedTeamLogo.setImageResource(R.drawable.ic_error);
        }
        if (itemList.get(position).teamalogo.length() > 1) {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))

                    .load(R.drawable.home_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingBetHolder.imageViewTeamA);
        } else {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))

                    .load(R.drawable.home_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingBetHolder.imageViewTeamA);
        }
        if (itemList.get(position).teamblogo.length() > 1) {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingBetHolder.imageViewTeamB);
        } else {
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(upcomingBetHolder.imageViewTeamB);
        }

        upcomingBetHolder.textViewName.setText(itemList.get(position).name);
        upcomingBetHolder.textViewTeamA.setText(itemList.get(position).teamaname);
        upcomingBetHolder.textViewTeamB.setText(itemList.get(position).teambname);
        upcomingBetHolder.textViewParticipants.setText(itemList.get(position).numberparticipant);
        upcomingBetHolder.textViewTime.setText(getFormatdedDate(itemList.get(position).time));


    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        //  return 5;
        return itemList.size();
    }


    private String getFormatdedDate(String date) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date2 = null;
        try {
            date2 = date_format.parse(date);
        } catch (ParseException e) {

            showMessage("error parsing date");
            e.printStackTrace();
            return "";
        }

        SimpleDateFormat outDate = new SimpleDateFormat("EEE dd MMM  yyyy hh:mm a");

        return outDate.format(date2);

    }
}
