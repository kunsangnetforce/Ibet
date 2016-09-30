package com.netforceinfotech.ibet.currentbet.betarena.thearena;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.comments_comment.CommentComments;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TheArenaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    public static final int SHARE_INTENT = 190;
    private final LayoutInflater inflater;
    private List<TheArenaData> itemList;
    private Context context;
    String team, bet_id, match_id;
    UserSessionManager userSessionManager;
    ArrayList<Boolean> shareclicked = new ArrayList<>();
    ArrayList<Boolean> likeclicked = new ArrayList<>();
    ArrayList<Boolean> dislikeclicked = new ArrayList<>();


    public TheArenaAdapter(Context context, List<TheArenaData> itemList, String match_id, String bet_id, String team) {
        this.itemList = itemList;
        this.context = context;
        this.match_id = match_id;
        inflater = LayoutInflater.from(context);
        this.bet_id = bet_id;
        this.team = team;
        userSessionManager = new UserSessionManager(context);
        try {
            shareclicked.clear();
            likeclicked.clear();
            dislikeclicked.clear();

        } catch (Exception ex) {

        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for (int i = 0; i < itemList.size(); i++) {
            shareclicked.add(false);
            likeclicked.add(false);
            dislikeclicked.add(false);
        }
        Log.i("sizekunsang", shareclicked.size() + "");
        View view = inflater.inflate(R.layout.row_chat, parent, false);
        TheArenaHolder viewHolder = new TheArenaHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final DatabaseReference _key = FirebaseDatabase.getInstance().getReference().child("bet").child(bet_id).child(team).child("comments").child(itemList.get(position).key);
        final DatabaseReference _share = _key.child("share");
        final DatabaseReference _like = _key.child("like");
        final DatabaseReference _dislike = _key.child("dislike");
        final DatabaseReference _count = _key.child("count");
        final DatabaseReference _comments = _key.child("comments");
        final TheArenaHolder theArenaHolder = (TheArenaHolder) holder;

        theArenaHolder.textViewName.setText(itemList.get(position).name);
        theArenaHolder.textViewDate.setText("22-7-2016");
        theArenaHolder.textViewTime.setText("23:11:02");
        theArenaHolder.textViewSC.setText(itemList.get(position).share);
        theArenaHolder.textViewDC.setText(itemList.get(position).dislike);
        theArenaHolder.textViewLC.setText(itemList.get(position).like);
        theArenaHolder.textViewComment.setText(itemList.get(position).comment);
        _share.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (shareclicked.get(position)) {
                    runTransaction(position);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _like.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (likeclicked.get(position)) {
                    runTransaction(position);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _dislike.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dislikeclicked.get(position)) {
                    runTransaction(position);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _share.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("sharecount", dataSnapshot.getChildrenCount() + "");
                theArenaHolder.textViewSC.setText(dataSnapshot.getChildrenCount() + "");
                itemList.get(position).share = dataSnapshot.getChildrenCount() + "";

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _comments.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                theArenaHolder.textViewCC.setText(dataSnapshot.getChildrenCount() + "");
                itemList.get(position).message = dataSnapshot.getChildrenCount() + "";
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _like.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                theArenaHolder.textViewLC.setText(dataSnapshot.getChildrenCount() + "");
                itemList.get(position).like = dataSnapshot.getChildrenCount() + "";

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        _dislike.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                theArenaHolder.textViewDC.setText(dataSnapshot.getChildrenCount() + "");
                itemList.get(position).dislike = dataSnapshot.getChildrenCount() + "";

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        try {
            Picasso.with(context).load(itemList.get(position).imageurl).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(theArenaHolder.circleImageView);
        } catch (Exception ex) {
            Picasso.with(context).load(R.drawable.ic_error).placeholder(R.drawable.ic_holder).error(R.drawable.ic_error).into(theArenaHolder.circleImageView);
        }
        theArenaHolder.imageViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("kunsangvalue", itemList.get(position).like + ":" + itemList.get(position).dislike);
                Intent intent = new Intent(context, CommentComments.class);
                Bundle bundle = new Bundle();
                bundle.putString("match_id", match_id);
                bundle.putString("bet_id", bet_id);
                bundle.putString("team", team);
                bundle.putString("dp", itemList.get(position).imageurl);
                bundle.putString("name", itemList.get(position).name);
                bundle.putString("from", "bet");
                bundle.putString("commentkey", itemList.get(position).key);
                bundle.putString("comment", itemList.get(position).comment);
                bundle.putString("dislikecount", itemList.get(position).dislike);
                bundle.putString("likecount", itemList.get(position).like);
                bundle.putString("sharecount", itemList.get(position).share);
                bundle.putLong("timestamp", itemList.get(position).timestamp);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        theArenaHolder.imageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", userSessionManager.getCustomerId() + "_" + userSessionManager.getName());
                _share.updateChildren(map);
                shareData(itemList.get(position).comment, itemList.get(position).name);
                shareclicked.set(position, true);
            }
        });
        theArenaHolder.imageViewDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", userSessionManager.getCustomerId() + "_" + userSessionManager.getName());
                _dislike.updateChildren(map);
                dislikeclicked.set(position, true);
            }
        });
        theArenaHolder.imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", userSessionManager.getCustomerId() + "_" + userSessionManager.getName());
                _like.updateChildren(map);
                likeclicked.set(position, true);
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

    public void shareData(String comment, String username) {
        String shareBody = username + ":" + comment + "\n" + "Ibet comment";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, context.getResources().getText(R.string.shareitwith)));
    }

    public void runTransaction(int position) {
        DatabaseReference _count = FirebaseDatabase.getInstance().getReference().child("bet").child(bet_id).child(team).child("comments").child(itemList.get(position).key).child("count");
        _count.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData currentData) {
                if (currentData.getValue() == null) {
                    currentData.setValue(1);
                } else {
                    currentData.setValue((Long) currentData.getValue() + 1);
                }
                return Transaction.success(currentData); //we can also abort by calling Transaction.abort()
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

            }
        });
    }

}
