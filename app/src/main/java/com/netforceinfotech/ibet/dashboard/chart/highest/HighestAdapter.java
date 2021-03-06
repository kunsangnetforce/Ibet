package com.netforceinfotech.ibet.dashboard.chart.highest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asdf on 7/21/2016.1
 */
public class HighestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<HighestFragmentData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();

    public HighestAdapter(Context context, List<HighestFragmentData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rowhighestrank, parent, false);
        HighestHolder viewHolder = new HighestHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        HighestHolder richestHolder = (HighestHolder) holder;
        int rank = position + 1;
        richestHolder.textViewRank.setText(rank + "");
        try {
            Glide.with(context).load(itemList.get(position).imageurl).error(R.drawable.ic_error).into(richestHolder.imageViewProfilePic);
        } catch (Exception ex) {
            Glide.with(context).load(R.drawable.ic_error).into(richestHolder.imageViewProfilePic);
        }
        richestHolder.textViewName.setText(itemList.get(position).title);
        richestHolder.textViewLevel.setText(itemList.get(position).level + " Level");
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