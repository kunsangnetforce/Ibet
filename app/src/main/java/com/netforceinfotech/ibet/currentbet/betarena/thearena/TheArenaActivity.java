package com.netforceinfotech.ibet.currentbet.betarena.thearena;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import it.carlom.stikkyheader.core.StikkyHeaderBuilder;

public class TheArenaActivity extends AppCompatActivity implements ValueEventListener, ChildEventListener, View.OnClickListener {

    private Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayout relativeLayout;
    ArrayList<TheArenaData> theArenaDatas = new ArrayList<>();
    String match_id, bet_id, team, home_id, home_name, home_logo, away_id, away_name, away_logo;
    private LinearLayoutManager linearLayoutManager;
    private TheArenaAdapter adapter;
    DatabaseReference _root, _bet, _betid, _team, _comments;
    Map<String, Object> map_bet, map_betid, map_team, map_comment, map_teamdetail;
    private String stringImage;
    private String chatUsername;
    private String stringComment;
    private Long timestamp;
    private String tempKey;
    EditText editText;
    CircleImageView imageViewSend;
    LinearLayout linearLayoutInput, linearLayoutNoComment, linearLayoutContainer;
    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_arena);
        Bundle bundle = getIntent().getExtras();
        try {
           /* match_id = bundle.getString("match_id");
            bet_id = bundle.getString("bet_id");
            team = bundle.getString("team");*/
            match_id = "123";
            bet_id = "123";
            team = "draw";

        } catch (Exception ex) {

        }
        userSessionManager = new UserSessionManager(this);
        imageViewSend = (CircleImageView) findViewById(R.id.imageViewSend);
        editText = (EditText) findViewById(R.id.editText);
        relativeLayout = (LinearLayout) findViewById(R.id.header);
        linearLayoutInput = (LinearLayout) findViewById(R.id.linearLayoutInput);
        linearLayoutContainer = (LinearLayout) findViewById(R.id.linearLayoutContainer);
        linearLayoutNoComment = (LinearLayout) findViewById(R.id.linearLayoutNoComment);
        _root = FirebaseDatabase.getInstance().getReference();
        imageViewSend.setOnClickListener(this);
        setupToolBar("The Arena");
        setupHashMap();
        setupRecyclerView();
        setupFirebase();
    }

    private void setupFirebase() {
        linearLayoutInput.setVisibility(View.GONE);
        _root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                linearLayoutInput.setVisibility(View.VISIBLE);
                if (dataSnapshot.child("bet").exists()) {
                    _bet = _root.child("bet");
                    _bet.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(bet_id).exists()) {
                                _betid = _bet.child(bet_id);
                                _betid.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.child(team).exists()) {
                                            _team = _betid.child(team);
                                            _team.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.child("comments").exists()) {
                                                        _comments = _team.child("comments");
                                                    } else {
                                                        _team.updateChildren(map_comment);
                                                        _team.addValueEventListener(TheArenaActivity.this);
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });
                                        } else {
                                            _bet.updateChildren(map_team);
                                            _bet.addValueEventListener(TheArenaActivity.this);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            } else {
                                _bet.updateChildren(map_betid);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                } else {
                    _root.updateChildren(map_bet);
                    _root.addValueEventListener(TheArenaActivity.this);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showMessage(String s) {
        Toast.makeText(TheArenaActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupHashMap() {
        map_bet = new HashMap<String, Object>();
        map_bet.put("bet", "");

        map_betid = new HashMap<>();
        map_betid.put(bet_id, "");

        map_team = new HashMap<>();
        map_team.put(team, "");

        if (team.equalsIgnoreCase("home")) {
            map_teamdetail = new HashMap<>();
            map_teamdetail.put("id", home_id);
            map_teamdetail.put("name", home_name);
            map_teamdetail.put("logo", home_logo);
            map_teamdetail.put("comments", "");

        } else if (team.equalsIgnoreCase("away")) {
            map_teamdetail = new HashMap<>();
            map_teamdetail.put("id", away_id);
            map_teamdetail.put("name", away_name);
            map_teamdetail.put("logo", away_logo);
            map_teamdetail.put("comments", "");

        } else {
            map_teamdetail = new HashMap<>();
            map_teamdetail.put("id", "0");
            map_teamdetail.put("name", "draw");
            map_teamdetail.put("logo", "draw");
            map_teamdetail.put("comments", "");


        }

        map_comment = new HashMap<>();

    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new TheArenaAdapter(this, theArenaDatas, bet_id, team);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);

    }

    @Override
    protected void onStart() {
        super.onStart();
        StikkyHeaderBuilder.stickTo(recyclerView)
                .setHeader(R.id.header, linearLayoutContainer)
                .minHeightHeader(0)
                .build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
         firebaseTask(dataSnapshot);
    }

    private void firebaseTask(DataSnapshot dataSnapshot) {
        if (dataSnapshot.getKey().equalsIgnoreCase("bet")) {
            _bet = _root.child("all");
            _bet.updateChildren(map_betid);
            _bet.addChildEventListener(TheArenaActivity.this);
        } else if (dataSnapshot.getKey().equalsIgnoreCase(match_id)) {
            _betid = _bet.child(bet_id);
            _betid.updateChildren(map_team);
            _betid.addChildEventListener(TheArenaActivity.this);
        } else if (dataSnapshot.getKey().equalsIgnoreCase(team)) {
            _team = _betid.child(team);
            _team.updateChildren(map_teamdetail);
            _team.addChildEventListener(TheArenaActivity.this);
        } else if (dataSnapshot.getKey().equalsIgnoreCase(tempKey)) {
            appendChatConversation(dataSnapshot);
        } else if (dataSnapshot.getKey().equalsIgnoreCase("comments")) {
            _comments = _team.child("comments");
            relativeLayout.setVisibility(View.VISIBLE);
            _comments.addChildEventListener(TheArenaActivity.this);
        } else {
            appendChatConversation(dataSnapshot);

        }
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
       firebaseTask(dataSnapshot);
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

    public void appendChatConversation(DataSnapshot dataSnapshot) {
        stringImage = dataSnapshot.child("image").getValue(String.class);
        chatUsername = dataSnapshot.child("name").getValue(String.class);
        stringComment = dataSnapshot.child("message").getValue(String.class);
        timestamp = dataSnapshot.child("timestamp").getValue(Long.class);
        if (stringComment != null) {
            theArenaDatas.add(new TheArenaData(stringImage, chatUsername, timestamp, stringComment, "0", "0", "0", "0", dataSnapshot.getKey()));
        }
        if (theArenaDatas.size() != 0) {
            linearLayoutNoComment.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(theArenaDatas.size());


    }

    public void sendMessage(String chat_message) {
        showMessage("clicked");
        Map<String, Object> map = new HashMap<String, Object>();
        tempKey = _comments.push().getKey();
        _comments.updateChildren(map);
        DatabaseReference message_root = _comments.child(tempKey);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", userSessionManager.getName());
        map1.put("message", chat_message);
        map1.put("comments", "");
        map1.put("count", 0);
        map1.put("timestamp", ServerValue.TIMESTAMP);
        map1.put("image", userSessionManager.getProfilePic());
        message_root.updateChildren(map1);

    }

    @Override
    public void onClick(View view) {
        if (editText.length() > 0) {
            sendMessage(editText.getText().toString());
        } else {
            showMessage("Enter text");
        }
    }
}
