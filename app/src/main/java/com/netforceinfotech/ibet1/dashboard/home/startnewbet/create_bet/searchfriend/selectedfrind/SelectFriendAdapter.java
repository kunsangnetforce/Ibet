package com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.selectedfrind;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.SearchFriendActivity;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.SearchFriendData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SelectFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<SearchFriendData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();

    public SelectFriendAdapter(Context context, List<SearchFriendData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_selectedteam, parent, false);
        SelectFriendHolder viewHolder = new SelectFriendHolder(view);
        for (int i = 0; i < itemList.size(); i++) {
            booleanGames.add(false);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        SelectFriendHolder selectFriendHolder = (SelectFriendHolder) holder;
        if (itemList.get(position).profilepic.length() > 1) {
            Glide.with(context)
                    .load(itemList.get(position).profilepic)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(selectFriendHolder.ImageViewLogo);
        } else {
            selectFriendHolder.ImageViewLogo.setImageResource(R.drawable.ic_error);
        }
        selectFriendHolder.imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFriendActivity.selectedDatas.remove(position);
                SearchFriendActivity.selectFriendAdapter.notifyDataSetChanged();
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
