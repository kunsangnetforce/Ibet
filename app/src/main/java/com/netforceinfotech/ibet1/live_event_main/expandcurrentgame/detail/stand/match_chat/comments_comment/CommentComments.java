package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.detail.stand.match_chat.comments_comment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.netforceinfotech.ibet1.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentComments extends AppCompatActivity implements View.OnClickListener {

    private static final int SHARE_INTENT = 120;
    private Toolbar toolbar;
    RecyclerView recyclerView;
    CCAdapter adapter;
    ArrayList<CCData> ccDatas = new ArrayList<>();
    Context context;
    String matchid, team, commentkey, comment, likecount, dislikecount, sharecount, dp, name;
    DatabaseReference _root, _comments;
    EditText editText;
    CircleImageView circleImageView,circleImageViewDp;
    private String tempKey;
    UserSessionManager userSessionManager;
    TextView textViewLC, textViewDC, textViewSC, textViewComment, textViewDate, textViewTime, textViewName;
    Long timestamp;
    boolean buttonclicked = false;
    String from;
    CoordinatorLayout coordinatorLayout;
    View view1;
    private String betid;
    LinearLayout linearLayoutEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_comments);
        context = this;
        userSessionManager = new UserSessionManager(context);

        try {
            Bundle bundle = getIntent().getExtras();
            matchid = bundle.getString("match_id");
            team = bundle.getString("team");
            from = bundle.getString("from");
            dp = bundle.getString("dp");
            name = bundle.getString("name");
            betid = bundle.getString("bet_id");
            commentkey = bundle.getString("commentkey");
            comment = bundle.getString("comment");
            dislikecount = bundle.getString("dislikecount");
            likecount = bundle.getString("likecount");
            sharecount = bundle.getString("sharecount");
            timestamp = bundle.getLong("timestamp");
            Log.i("kunsangvalue", likecount + ":" + dislikecount);
            //  Log.i("localtime", getDateCurrentTimeZone(timestamp));

        } catch (Exception ex) {
            showMessage("bundle error");
        }
        setupToolbar();
        initView();
        setupRecyclerView();
        setupFirebase();
        setupStatusBar();
        setupTheme();
        setupBackground();
    }

    private void initView() {
        circleImageViewDp= (CircleImageView) findViewById(R.id.imageView);
        textViewName= (TextView) findViewById(R.id.textViewName);
        linearLayoutEdit = (LinearLayout) findViewById(R.id.linearLayoutEdit);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        view1 = findViewById(R.id.view1);
        textViewDC = (TextView) findViewById(R.id.textViewDC);
        textViewLC = (TextView) findViewById(R.id.textViewLC);
        textViewSC = (TextView) findViewById(R.id.textViewSC);
        textViewComment = (TextView) findViewById(R.id.textViewComment);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        editText = (EditText) findViewById(R.id.editText);
        circleImageView = (CircleImageView) findViewById(R.id.imageViewSend);
        circleImageView.setOnClickListener(this);
        textViewDC.setText(dislikecount);
        textViewLC.setText(likecount);
        textViewSC.setText(sharecount);
        textViewComment.setText(comment);
        textViewName.setText(name);
        textViewDate.setText(Util.getDateCurrentTimeZone(timestamp));
        textViewTime.setText(Util.getTimeCurrentTimeZone(timestamp));
        try {
            Glide.with(context).load(dp).into(circleImageViewDp);
        } catch (Exception ex) {
            Glide.with(context).load(R.drawable.ic_error).into(circleImageViewDp);
        }
    }

    private void setupFirebase() {
        _root = FirebaseDatabase.getInstance().getReference();
        if (from.equalsIgnoreCase("all")) {
            try {
                _comments = _root.child("all").child(matchid).child(team).child("comments").child(commentkey).child("comments");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            try {
                _comments = _root.child("bet").child(betid).child(team).child("comments").child(commentkey).child("comments");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            _comments.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (buttonclicked) {
                        runTransaction();
                        buttonclicked = false;
                    }
                    for (DataSnapshot election : dataSnapshot.getChildren()) {
                        String message = election.child("message").getValue(String.class);
                        String image = election.child("image").getValue(String.class);
                        String name = election.child("name").getValue(String.class);
                        Long timestamp = election.child("timestamp").getValue(Long.class);
                        String key = election.getKey();
                        /*
                        *   EventsData eventsData = new EventsData(player_name, type, team_id, minute, extra_min, player_in_name, player_out_name, team, id);
                        if (!eventsDatas.contains(eventsData)) {
                            eventsDatas.add(eventsData);
                        }

                        * */
                        CCData ccData = new CCData(image, name, timestamp, message, key);
                        if (!ccDatas.contains(ccData)) {
                            ccDatas.add(ccData);
                        }
                        adapter.notifyDataSetChanged();
                        recyclerView.smoothScrollToPosition(recyclerView.getBottom());

                    }
                    Log.i("messagecount", "" + ccDatas.size());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (Exception ex) {
            showMessage("firebase error");
            ex.printStackTrace();
        }

    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        adapter = new CCAdapter(context, ccDatas, matchid, team, betid, from);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(recyclerView.getBottom());

    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Comments");
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewSend:
                buttonclicked = true;
                if (editText.length() > 0) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    tempKey = _comments.push().getKey();
                    _comments.updateChildren(map);
                    DatabaseReference message_root = _comments.child(tempKey);
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    map1.put("name", userSessionManager.getName());
                    map1.put("message", editText.getText().toString());
                    map1.put("timestamp", ServerValue.TIMESTAMP);
                    map1.put("image", userSessionManager.getProfilePic());
                    message_root.updateChildren(map1);
                } else {
                    showMessage("Type text");
                }
                break;
        }
    }

    private void showMessage(String s) {
        Toast.makeText(CommentComments.this, s, Toast.LENGTH_SHORT).show();
    }

    public void runTransaction() {
        DatabaseReference _count;
        if (from.equalsIgnoreCase("all")) {
            _count = FirebaseDatabase.getInstance().getReference().child("all").child(matchid).child(team).child("comments").child(commentkey).child("count");
        } else {
            _count = FirebaseDatabase.getInstance().getReference().child("bet").child(betid).child(team).child("comments").child(commentkey).child("count");
        }
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

    private void setupStatusBar() {
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        switch (userSessionManager.getTheme()) {
            case 0:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
                }
                break;
            case 3:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
                }
                break;
            case 4:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
                }
                break;
            case 5:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
                }
                break;
        }

    }

    private void setupBackground() {

        switch (userSessionManager.getBackground()) {
            case 0:
                coordinatorLayout.setBackgroundResource(R.drawable.blue240);
                break;
            case 1:
                coordinatorLayout.setBackgroundResource(R.drawable.france240);
                break;
            case 2:
                coordinatorLayout.setBackgroundResource(R.drawable.soccer240);
                break;
            case 3:
                coordinatorLayout.setBackgroundResource(R.drawable.spain240);
                break;
            case 4:
                coordinatorLayout.setBackgroundResource(R.drawable.uk240);
                break;
            case 5:
                view1.setVisibility(View.GONE);
                break;
        }
    }

    private void setupTheme() {
        int theme = userSessionManager.getTheme();
        switch (theme) {
            case 0:
                setupDefaultTheme();
                break;
            case 1:
                setupBrownTheme();
                break;
            case 2:
                setupPurlpleTheme();
                break;
            case 3:
                setupGreenTheme();
                break;
            case 4:
                setupMarronTheme();
                break;
            case 5:
                setupLightBlueTheme();
                break;
        }
    }

    private void setupBrownTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        linearLayoutEdit.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
    }

    private void setupPurlpleTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        linearLayoutEdit.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setupGreenTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        linearLayoutEdit.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setupMarronTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        linearLayoutEdit.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        linearLayoutEdit.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));


    }

    private void setupDefaultTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        linearLayoutEdit.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

    }


}
