package com.netforceinfotech.ibet.live_event.thearena.comments_comment;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CCAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<CCData> itemList;
    private Context context;
    String team, matchid;
    UserSessionManager userSessionManager;


    public CCAdapter(Context context, List<CCData> itemList, String matchid, String team) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.matchid = matchid;
        this.team = team;
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
        final DatabaseReference _key = FirebaseDatabase.getInstance().getReference().child("all").child(matchid).child(team).child("comments").child(itemList.get(position).key);
        final CCHolder CCHolder = (CCHolder) holder;
        CCHolder.textViewName.setText(itemList.get(position).name);
        CCHolder.textViewDate.setText("22-7-2016");
        CCHolder.textViewTime.setText("23:11:02");
        CCHolder.textViewComment.setText(itemList.get(position).comment);
        try {
            Picasso.with(context).load(itemList.get(position).imageurl).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(CCHolder.circleImageView);
        } catch (Exception ex) {
            Picasso.with(context).load(R.drawable.ic_error).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(CCHolder.circleImageView);
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
