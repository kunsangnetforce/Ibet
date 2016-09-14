package com.netforceinfotech.ibet.live_event.thearena.top;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.util.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<TopData> itemList;
    private Context context;

    public TopAdapter(Context context, List<TopData> itemList) {
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

        View view = inflater.inflate(R.layout.row_chat, parent, false);
        TopHolder viewHolder = new TopHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TopHolder topHolder = (TopHolder) holder;
        TopData topData = itemList.get(position);
        try {
            Picasso.with(context).load(topData.imageurl).error(R.drawable.ic_error).into(topHolder.circleImageView);
        } catch (Exception ex) {
            Picasso.with(context).load(R.drawable.ic_error).into(topHolder.circleImageView);
        }
        topHolder.textViewCC.setText(topData.message);
        topHolder.textViewSC.setText(topData.share);
        topHolder.textViewDC.setText(topData.dislike);
        topHolder.textViewLC.setText(topData.like);
        topHolder.textViewComment.setText(topData.comment);
        topHolder.textViewDate.setText(Util.getDateCurrentTimeZone(topData.timestamp));
        topHolder.textViewTime.setText(Util.getDateCurrentTimeZone(topData.timestamp));

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
