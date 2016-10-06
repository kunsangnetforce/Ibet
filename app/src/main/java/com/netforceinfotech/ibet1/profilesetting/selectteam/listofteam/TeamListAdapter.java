package com.netforceinfotech.ibet1.profilesetting.selectteam.listofteam;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.profilesetting.selectteam.SelectTeamActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TeamListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<TeamListData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();

    public TeamListAdapter(Context context, List<TeamListData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_team, parent, false);
        TeamListHolder viewHolder = new TeamListHolder(view);
        for (int i = 0; i < itemList.size(); i++) {
            booleanGames.add(false);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final String id = itemList.get(position).id;
        final TeamListHolder teamListHolder = (TeamListHolder) holder;
        teamListHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
        for (int i = 0; i < SelectTeamActivity.selectTeamDatas.size(); i++) {
            if (id.equalsIgnoreCase(SelectTeamActivity.selectTeamDatas.get(i).id)) {
                teamListHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
            }
        }
        teamListHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SelectTeamActivity.selectTeamDatas.size() > 0) {
                    boolean present = false;
                    for (int i = 0; i < SelectTeamActivity.selectTeamDatas.size(); i++) {
                        if (id.equalsIgnoreCase(SelectTeamActivity.selectTeamDatas.get(i).id)) {
                            present = true;
                            break;
                        }
                    }
                    if (!present) {
                        SelectTeamActivity.selectTeamDatas.add(itemList.get(position));
                    }
                } else {
                    SelectTeamActivity.selectTeamDatas.add(itemList.get(position));
                }
                teamListHolder.imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
                SelectTeamActivity.selectTeamAdapter.notifyDataSetChanged();
                if (itemList.size() > 0) {
                    SelectTeamActivity.linearLayoutSelectedTeams.setVisibility(View.VISIBLE);
                }

            }
        });
        teamListHolder.textViewTeamName.setText(itemList.get(position).name);
        if (itemList.get(position).logo.length() > 1) {
            Glide.with(context)
                    .load(itemList.get(position).logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(teamListHolder.imageViewLogo);
        } else {
            teamListHolder.imageViewLogo.setImageResource(R.drawable.ic_error);
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
}
