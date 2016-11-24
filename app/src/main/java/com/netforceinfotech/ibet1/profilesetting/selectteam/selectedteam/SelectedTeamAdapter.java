package com.netforceinfotech.ibet1.profilesetting.selectteam.selectedteam;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.profilesetting.selectteam.SelectTeamActivity;
import com.netforceinfotech.ibet1.profilesetting.selectteam.listofteam.TeamListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SelectedTeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<TeamListData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();

    public SelectedTeamAdapter(Context context, List<TeamListData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_selectedteam, parent, false);
        SelectTeamHolder viewHolder = new SelectTeamHolder(view);
        for (int i = 0; i < itemList.size(); i++) {
            booleanGames.add(false);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        SelectTeamHolder selectTeamHolder = (SelectTeamHolder) holder;
        selectTeamHolder.textViewName.setText(itemList.get(position).name);
        if (itemList.get(position).logo.length() > 1) {
            Glide.with(context)
                    .load(itemList.get(position).logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(selectTeamHolder.ImageViewLogo);
        } else {
            selectTeamHolder.ImageViewLogo.setImageResource(R.drawable.ic_error);
        }
        selectTeamHolder.imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectTeamActivity.selectTeamDatas.remove(position);
                SelectTeamActivity.selectedTeamAdapter.notifyDataSetChanged();
                SelectTeamActivity.listAdapter.notifyDataSetChanged();
            }
        });

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
