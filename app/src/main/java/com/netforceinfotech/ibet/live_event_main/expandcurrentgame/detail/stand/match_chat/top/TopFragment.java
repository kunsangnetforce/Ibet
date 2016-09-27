package com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.top;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {
    RelativeLayout relativeLayout;
    LinearLayout linearLayoutProgress, linearLayoutNoComment;
    DatabaseReference _root, _comments;
    String home_id, away_id, home_name, away_name, team, match_id, home_logo, away_logo, profileimage;
    UserSessionManager userSessionManager;
    Context context;
    ArrayList<TopData> topDatas = new ArrayList<>();
    private String stringImage;
    private String chatUsername;
    private String stringComment, share, like, dislike, comments;
    private Long timestamp;
    private TopAdapter adapter;
    private RecyclerView recyclerView;

    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        try {
            home_id = this.getArguments().getString("away_id");
            away_id = this.getArguments().getString("away_id");
            match_id = this.getArguments().getString("match_id");
            home_name = this.getArguments().getString("home_name");
            away_name = this.getArguments().getString("away_name");
            team = this.getArguments().getString("team");

            home_logo = this.getArguments().getString("home_logo");
            away_logo = this.getArguments().getString("away_logo");
            profileimage = userSessionManager.getProfilePic();

        } catch (Exception ex) {
            Log.i("kunsang_exception", "paramenter not set");
        }
        initView(view);
        setupRecycler(view);
        setupFirebase();
        return view;
    }

    private void setupFirebase() {
        _root = FirebaseDatabase.getInstance().getReference();
        try {
            linearLayoutNoComment.setVisibility(View.GONE);
            linearLayoutProgress.setVisibility(View.GONE);
            _comments = _root.child("all").child(match_id).child(team).child("comments");
            // My top posts by number of stars

            Query myTopPostsQuery = _comments.orderByChild("count").limitToLast(4);
            myTopPostsQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    linearLayoutProgress.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    //   Log.i("sizekunsang1",dataSnapshot.getChildrenCount()+"");
                    appendChatConversation(dataSnapshot);

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
                // TODO: implement the ChildEventListener methods as documented above
                // ...
            });

        } catch (Exception ex) {
            showMessage("Something went wrong");
            Log.i("kunsang_test", "something went wrong");
            ex.printStackTrace();

        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void initView(View view) {
        linearLayoutNoComment = (LinearLayout) view.findViewById(R.id.linearLayoutNoComment);
        linearLayoutProgress = (LinearLayout) view.findViewById(R.id.linearLayoutProgress);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        relativeLayout.setVisibility(View.GONE);
        linearLayoutProgress.setVisibility(View.VISIBLE);
    }

    private void setupRecycler(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        adapter = new TopAdapter(getActivity(), topDatas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void appendChatConversation(DataSnapshot dataSnapshot) {
        stringImage = dataSnapshot.child("image").getValue(String.class);
        chatUsername = dataSnapshot.child("name").getValue(String.class);
        stringComment = dataSnapshot.child("message").getValue(String.class);
        timestamp = dataSnapshot.child("timestamp").getValue(Long.class);
        share = dataSnapshot.child("share").getChildrenCount() + "";
        dislike = dataSnapshot.child("dislike").getChildrenCount() + "";
        like = dataSnapshot.child("like").getChildrenCount() + "";
        comments = dataSnapshot.child("comments").getChildrenCount() + "";

        if (stringComment != null) {
            //TopData(String imageurl, String name, Long timestamp, String comment, String share, String dislike, String like, String message, String key) {
            topDatas.add(new TopData(stringImage, chatUsername, timestamp, stringComment, share, dislike, like, comments, dataSnapshot.getKey()));
            Log.i("kunsangcount", like + ":" + dislike+":=="+dataSnapshot.toString());
        }
        if (topDatas.size() != 0) {
            linearLayoutNoComment.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(topDatas.size());
        Log.i("sizekunsang1", stringComment);
        adapter.notifyDataSetChanged();
    }
}
