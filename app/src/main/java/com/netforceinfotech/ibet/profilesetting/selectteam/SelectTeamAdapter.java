package com.netforceinfotech.ibet.profilesetting.selectteam;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SelectTeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<SelectTeamData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();

    public SelectTeamAdapter(Context context, List<SelectTeamData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_team, parent, false);
        SelectTeamHolder viewHolder = new SelectTeamHolder(view);
        for (int i = 0; i < itemList.size(); i++) {
            booleanGames.add(false);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (booleanGames.get(position)) {
            ((SelectTeamHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
        } else {
            ((SelectTeamHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
        }
        Log.i("ibet_position", "" + position);
        final SelectTeamHolder selectTeamHolder = (SelectTeamHolder) holder;
        selectTeamHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booleanGames.set(position, !booleanGames.get(position));
                if (booleanGames.get(position)) {
                    selectTeamHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
                    selectTeamHolder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

                } else {
                    selectTeamHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
                    selectTeamHolder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                }
                notifyDataSetChanged();

            }
        });

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return 5;
//        return itemList.size();
    }
}