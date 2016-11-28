package com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.comments_comment;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.util.Util;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CCAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<CCData> itemList;
    private Context context;
    String team, matchid, bet_id, from;
    UserSessionManager userSessionManager;


    public CCAdapter(Context context, List<CCData> itemList, String matchid, String team, String bet_id, String from) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.matchid = matchid;
        this.team = team;
        this.bet_id = bet_id;
        this.from = from;
        userSessionManager = new UserSessionManager(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_chat_comment, parent, false);
        CCHolder viewHolder = new CCHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final DatabaseReference _key;
        if (from.equalsIgnoreCase("all")) {
            _key = FirebaseDatabase.getInstance().getReference().child("all").child(matchid).child(team).child("comments").child(itemList.get(position).key);
        } else {
            _key = FirebaseDatabase.getInstance().getReference().child("bet").child(bet_id).child(team).child("comments").child(itemList.get(position).key);
        }
        final CCHolder CCHolder = (CCHolder) holder;
        CCHolder.textViewName.setText(itemList.get(position).name);
        CCHolder.textViewDate.setText(Util.getDateCurrentTimeZone(itemList.get(position).timestamp));
        CCHolder.textViewTime.setText(Util.getTimeCurrentTimeZone(itemList.get(position).timestamp));
        CCHolder.textViewComment.setText(itemList.get(position).comment);
        try {
            Glide.with(context).load(itemList.get(position).imageurl).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(CCHolder.circleImageView);
        } catch (Exception ex) {
            Glide.with(context).load(R.drawable.ic_error).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(CCHolder.circleImageView);
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
