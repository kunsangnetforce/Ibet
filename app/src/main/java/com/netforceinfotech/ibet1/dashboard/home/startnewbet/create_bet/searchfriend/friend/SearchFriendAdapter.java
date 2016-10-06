package com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.friend;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.SearchFriendActivity;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.SearchFriendData;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SearchFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<SearchFriendData> itemList;
    private Context context;
    String customer_id;
    UserSessionManager userSessionManager;

    public SearchFriendAdapter(Context context, List<SearchFriendData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        userSessionManager = new UserSessionManager(context);
        customer_id = userSessionManager.getCustomerId();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_friend, parent, false);
        SearchFriendHolder viewHolder = new SearchFriendHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.i("ibet_position", "" + position);
        SearchFriendHolder searchFriendHolder = (SearchFriendHolder) holder;
        try {
            Glide.with(context).load(itemList.get(position).profilepic).error(R.drawable.ic_error).into(searchFriendHolder.imageView);
        } catch (Exception ex) {
            Glide.with(context).load(R.drawable.ic_error).into(searchFriendHolder.imageView);
        }
        searchFriendHolder.textViewName.setText(itemList.get(position).name);
        searchFriendHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!itemList.get(position).id.equalsIgnoreCase(customer_id)) {
                    if (!SearchFriendActivity.selectedDatas.contains(itemList.get(position)))
                        SearchFriendActivity.selectedDatas.add(itemList.get(position));
                    SearchFriendActivity.selectFriendAdapter.notifyDataSetChanged();
                }
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
