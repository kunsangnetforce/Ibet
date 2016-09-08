package com.netforceinfotech.ibet.live_event.thearena.all;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class AllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<AllData> itemList;
    private Context context;

    public AllAdapter(Context context, List<AllData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_chat, parent, false);
        AllHolder viewHolder = new AllHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AllHolder allHolder = (AllHolder) holder;
        allHolder.textViewName.setText(itemList.get(position).name);
        allHolder.textViewDate.setText("22-7-2016");
        allHolder.textViewTime.setText("23:11:02");
        allHolder.textViewSC.setText(itemList.get(position).share);
        allHolder.textViewDC.setText(itemList.get(position).dislike);
        allHolder.textViewLC.setText(itemList.get(position).like);
        allHolder.textViewComment.setText(itemList.get(position).comment);
        try {
            Picasso.with(context).load(itemList.get(position).imageurl).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(allHolder.circleImageView);
        } catch (Exception ex) {
            Picasso.with(context).load(R.drawable.ic_error).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(allHolder.circleImageView);
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        //     return 10;
        return itemList.size();
    }
}
