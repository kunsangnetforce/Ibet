package com.netforceinfotech.ibet.live_event.thearena.all;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
public class AllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<AllData> itemList;
    private Context context;
    String team, matchid;
    UserSessionManager userSessionManager;


    public AllAdapter(Context context, List<AllData> itemList, String matchid, String team) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.matchid = matchid;
        this.team = team;
        userSessionManager = new UserSessionManager(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_chat, parent, false);
        AllHolder viewHolder = new AllHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final DatabaseReference _key = FirebaseDatabase.getInstance().getReference().child("all").child(matchid).child(team).child("comments").child(itemList.get(position).key);
        final DatabaseReference _share = _key.child("share");
        final DatabaseReference _like = _key.child("like");
        final DatabaseReference _dislike = _key.child("dislike");
        final DatabaseReference _comments = _key.child("comments");
        final AllHolder allHolder = (AllHolder) holder;
        allHolder.textViewName.setText(itemList.get(position).name);
        allHolder.textViewDate.setText("22-7-2016");
        allHolder.textViewTime.setText("23:11:02");
        allHolder.textViewSC.setText(itemList.get(position).share);
        allHolder.textViewDC.setText(itemList.get(position).dislike);
        allHolder.textViewLC.setText(itemList.get(position).like);
        allHolder.textViewComment.setText(itemList.get(position).comment);
        _share.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("sharecount", dataSnapshot.getChildrenCount() + "");
                allHolder.textViewSC.setText(dataSnapshot.getChildrenCount() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _comments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allHolder.textViewCC.setText(dataSnapshot.getChildrenCount() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _like.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allHolder.textViewLC.setText(dataSnapshot.getChildrenCount() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _dislike.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allHolder.textViewDC.setText(dataSnapshot.getChildrenCount() + "");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        try {
            Picasso.with(context).load(itemList.get(position).imageurl).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(allHolder.circleImageView);
        } catch (Exception ex) {
            Picasso.with(context).load(R.drawable.ic_error).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(allHolder.circleImageView);
        }
        allHolder.imageViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        allHolder.imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", userSessionManager.getCustomerId() + "_" + userSessionManager.getName());
                _share.updateChildren(map);

            }
        });
        allHolder.imageViewDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", userSessionManager.getCustomerId() + "_" + userSessionManager.getName());
                _dislike.updateChildren(map);
            }
        });
        allHolder.imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", userSessionManager.getCustomerId() + "_" + userSessionManager.getName());
                _like.updateChildren(map);
            }
        });
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
