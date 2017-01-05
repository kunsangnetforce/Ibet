package com.netforceinfotech.ibet.dashboard.chart.richestrank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet.R;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by John on 7/25/2016.
 */
public class RichestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<RichestData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();


    public RichestAdapter(Context context, List<RichestData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_richest_rank, parent, false);
        RichestHolder viewHolder = new RichestHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        RichestHolder richestHolder = (RichestHolder) holder;
        int rank = position + 1;
        richestHolder.textViewRank.setText(rank + "");
        try {
            Glide.with(context).load(itemList.get(position).imageurl).error(R.drawable.ic_error).into(richestHolder.imageViewProfilePic);
        } catch (Exception ex) {
            Glide.with(context).load(R.drawable.ic_error).into(richestHolder.imageViewProfilePic);
        }
        richestHolder.textViewName.setText(itemList.get(position).title);
        richestHolder.textViewAmount.setText(itemList.get(position).coins + " coins");
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }


    public class RichestHolder extends RecyclerView.ViewHolder {


        TextView textViewName, textViewAmount, textViewRank;
        CircleImageView imageViewProfilePic;
        View view;


        public RichestHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            view = itemView;
            textViewRank = (TextView) view.findViewById(R.id.textViewRank);
            textViewAmount = (TextView) view.findViewById(R.id.textViewAmount);
            textViewName = (TextView) view.findViewById(R.id.textViewName);
            imageViewProfilePic = (CircleImageView) view.findViewById(R.id.imageViewProfilePic);

        }
    }
}