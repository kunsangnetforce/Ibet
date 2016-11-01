package com.netforceinfotech.ibet1.currentbet.upcoming.detail_upcoming_bet;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class DetailBetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<DetailBetData> itemList;
    private Context context;

    public DetailBetAdapter(Context context, List<DetailBetData> itemList) {
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

        View view = inflater.inflate(R.layout.row_detailbet, parent, false);
        DetailBetHolder viewHolder = new DetailBetHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.i("ibet_position", "" + position);
        DetailBetHolder detailBetHolder = (DetailBetHolder) holder;
        DetailBetData detailBetData=itemList.get(position);
        Glide.with(context).load(detailBetData.userdp).error(R.drawable.ic_error).into(detailBetHolder.imageViewDp);
        detailBetHolder.textViewResult.setText(detailBetData.result);
        detailBetHolder.textViewName.setText(detailBetData.username);
        detailBetHolder.textViewScore.setText(detailBetData.score);
        detailBetHolder.textViewSelectedTeam.setText(detailBetData.selectedteam);

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
