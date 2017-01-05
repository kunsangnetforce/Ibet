package com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.all;


import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.netforceinfotech.ibet.Debugger.Debugger;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.StandActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment implements View.OnClickListener, ChildEventListener {
    private static final String TAG = "kunsang_firebase";
    static Context context;
    String home_id, away_id, home_name, away_name, team, match_id, home_logo, away_logo;
    EditText editText;
    ArrayList<AllData> allDatas = new ArrayList<>();
    static UserSessionManager userSessionManager;
    String profileimage;
    private String chat_message, chatUsername;
    LinearLayout linearLayoutNoComment;
    private String stringImage;
    private String stringComment;
    private Long timestamp;
    private AllAdapter adapter;
    DatabaseReference _root;
    DatabaseReference _all;
    DatabaseReference _matchid;

    static DatabaseReference _comment;
    Map<String, Object> map_all, map_matchid, map_comment, map_detail, map_fan;
    private static String tempKey;
    public static RecyclerView recyclerView;
    LinearLayout linearLayout, linearLayoutProgress;
    RelativeLayout relativeLayout;
    private boolean _allFlag = false, _matchidFlag = false, _commentFlag = false;

    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        context = getActivity();
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutInput);
        linearLayoutProgress = (LinearLayout) view.findViewById(R.id.linearLayoutProgress);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        userSessionManager = new UserSessionManager(context);
        editText = (EditText) view.findViewById(R.id.editText);
        Debugger.i("kunsang", "test");

        try {
            home_id = this.getArguments().getString("away_id");
            away_id = this.getArguments().getString("away_id");
            match_id = this.getArguments().getString("match_id");
            home_name = this.getArguments().getString("home_name");
            away_name = this.getArguments().getString("away_name");
            team = this.getArguments().getString("team");

            home_logo = this.getArguments().getString("home_logo");
            away_logo = this.getArguments().getString("away_logo");
            linearLayoutNoComment = (LinearLayout) view.findViewById(R.id.linearLayoutNoComment);
            profileimage = userSessionManager.getProfilePic();
            setupHashMap();
            setupRecycler(view);

        } catch (Exception ex) {

        }
        setupFirebaseReferences();
        return view;
    }

    private void setupFirebaseReferences() {
        StandActivity.linearLayoutInput.setVisibility(View.GONE);

        _root = FirebaseDatabase.getInstance().getReference();
        Debugger.i("kunsang", "inside setupfirebase");
        //check weather all references  already exist?
        try {
            Debugger.i("kunsang", "inside try all");

            _all = _root.child("all");
            _allFlag = true;
            try {
                Debugger.i("kunsang", "inside try match");
                _matchid = _all.child(match_id);
                _matchidFlag = true;
                try {
                    Debugger.i("kunsang", "inside comment");
                    _comment = _matchid.child("comments");
                    _commentFlag = true;
                } catch (Exception ex) {
                    Debugger.i("kunsangfirebase", "no comment");
                    _commentFlag = false;
                }
            } catch (Exception ex) {
                Debugger.i("kunsangfirebase", "no match");
                _matchidFlag = false;
            }
        } catch (Exception ex) {
            Debugger.i("kunsangfirebase", "no all");
            _allFlag = false;
        }
        setupReference();

    }

    private void setupReference() {
        Debugger.i("kunsang", "setup Reference");
        if (!_allFlag) {
            Debugger.i("kunsangfirebase", "no all called");
            _root.updateChildren(map_all);
            _root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    _all.updateChildren(map_matchid);
                    _all.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            _matchid.updateChildren(map_detail);
                            _matchid.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    //
                                    linearLayoutProgress.setVisibility(View.GONE);
                                    _comment = _matchid.child("comments");
                                    _comment.addChildEventListener(AllFragment.this);
                                    DatabaseReference _home_fan = _matchid.child("home_fan");
                                    DatabaseReference _away_fan = _matchid.child("away_fan");
                                    if (team.equalsIgnoreCase("home")) {
                                        _home_fan.updateChildren(map_fan);

                                    } else if (team.equalsIgnoreCase("away")) {
                                        _away_fan.updateChildren(map_fan);

                                    }

                                    _away_fan.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            long awayFanCount = dataSnapshot.getChildrenCount();
                                            StandActivity.textViewAwayFan.setText("" + awayFanCount);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });

                                    _home_fan.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            StandActivity.linearLayoutInput.setVisibility(View.VISIBLE);
                                            StandActivity.chatloaded=true;
                                            long homeFanCount = dataSnapshot.getChildrenCount();
                                            StandActivity.textViewHomeFan.setText("" + homeFanCount);

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            showMessage(getString(R.string.something_went_wrong));
                                        }
                                    });
                                }


                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    linearLayoutProgress.setVisibility(View.GONE);
                                    showMessage(getString(R.string.something_went_wrong));
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            linearLayoutProgress.setVisibility(View.GONE);
                            showMessage(getString(R.string.something_went_wrong));
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    linearLayoutProgress.setVisibility(View.GONE);
                    showMessage(getString(R.string.something_went_wrong));
                }
            });
            return;
        }

        if (!_matchidFlag) {

            Debugger.i("kunsangfirebase", "no match called");
            _all.updateChildren(map_matchid);
            _all.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    _matchid.updateChildren(map_detail);
                    _matchid.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //
                            _comment = _matchid.child("comments");
                            _comment.addChildEventListener(AllFragment.this);
                            linearLayoutProgress.setVisibility(View.GONE);
                            DatabaseReference _home_fan = _matchid.child("home_fan");
                            DatabaseReference _away_fan = _matchid.child("away_fan");
                            if (team.equalsIgnoreCase("home")) {
                                _home_fan.updateChildren(map_fan);

                            } else if (team.equalsIgnoreCase("away")) {
                                _away_fan.updateChildren(map_fan);

                            }

                            _away_fan.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    long awayFanCount = dataSnapshot.getChildrenCount();
                                    StandActivity.textViewAwayFan.setText("" + awayFanCount);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            _home_fan.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    StandActivity.linearLayoutInput.setVisibility(View.VISIBLE);
                                    StandActivity.chatloaded=true;
                                    long homeFanCount = dataSnapshot.getChildrenCount();
                                    StandActivity.textViewHomeFan.setText("" + homeFanCount);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    linearLayoutProgress.setVisibility(View.GONE);
                                    showMessage(getString(R.string.something_went_wrong));
                                }
                            });
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            linearLayoutProgress.setVisibility(View.GONE);
                            showMessage(getString(R.string.something_went_wrong));
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    linearLayoutProgress.setVisibility(View.GONE);
                    showMessage(getString(R.string.something_went_wrong));
                }
            });
            return;
        }
        if (!_commentFlag) {
            Debugger.i("kunsangfirebase", "no comment called");
            _matchid.updateChildren(map_detail);
            _matchid.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //
                    _comment = _matchid.child("comments");
                    _comment.addChildEventListener(AllFragment.this);
                    linearLayoutProgress.setVisibility(View.GONE);
                    DatabaseReference _home_fan = _matchid.child("home_fan");
                    DatabaseReference _away_fan = _matchid.child("away_fan");
                    if (team.equalsIgnoreCase("home")) {
                        _home_fan.updateChildren(map_fan);

                    } else if (team.equalsIgnoreCase("away")) {
                        _away_fan.updateChildren(map_fan);

                    }

                    _away_fan.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long awayFanCount = dataSnapshot.getChildrenCount();
                            StandActivity.textViewAwayFan.setText("" + awayFanCount);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    _home_fan.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            StandActivity.linearLayoutInput.setVisibility(View.VISIBLE);
                            StandActivity.chatloaded=true;
                            long homeFanCount = dataSnapshot.getChildrenCount();
                            StandActivity.textViewHomeFan.setText("" + homeFanCount);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            linearLayoutProgress.setVisibility(View.GONE);
                            showMessage(getString(R.string.something_went_wrong));
                        }
                    });
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    linearLayoutProgress.setVisibility(View.GONE);
                    showMessage(getString(R.string.something_went_wrong));
                }
            });
            return;
        }
        _comment.addChildEventListener(AllFragment.this);
        DatabaseReference _home_fan = _matchid.child("home_fan");
        DatabaseReference _away_fan = _matchid.child("away_fan");
        if (team.equalsIgnoreCase("home")) {
            _home_fan.updateChildren(map_fan);

        } else if (team.equalsIgnoreCase("away")) {
            _away_fan.updateChildren(map_fan);

        }

        _away_fan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long awayFanCount = dataSnapshot.getChildrenCount();
                StandActivity.textViewAwayFan.setText("" + awayFanCount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        _home_fan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StandActivity.linearLayoutInput.setVisibility(View.VISIBLE);
                StandActivity.chatloaded=true;
                long homeFanCount = dataSnapshot.getChildrenCount();
                StandActivity.textViewHomeFan.setText("" + homeFanCount);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                linearLayoutProgress.setVisibility(View.GONE);
                showMessage(getString(R.string.something_went_wrong));
            }
        });
        linearLayoutProgress.setVisibility(View.GONE);
        StandActivity.linearLayoutInput.setVisibility(View.VISIBLE);
        StandActivity.chatloaded=true;


    }

    private void setupHashMap() {
        map_all = new HashMap<String, Object>();
        map_all.put("all", "");

        map_matchid = new HashMap<>();
        map_matchid.put(match_id, "");

        map_detail = new HashMap<>();
        map_detail.put("comments", "");
        map_detail.put("home_fan", "");
        map_detail.put("away_fan", "");

        map_fan = new HashMap<>();
        if (userSessionManager.getCustomerId().equalsIgnoreCase("")) {
            String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            map_fan.put(android_id, userSessionManager.getName());
        } else {
            map_fan.put(userSessionManager.getCustomerId(), userSessionManager.getName());
        }


        map_comment = new HashMap<>();

    }

    public void setupRecycler(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new AllAdapter(getActivity(), allDatas, match_id, team);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void appendChatConversation(DataSnapshot dataSnapshot) {
        stringImage = dataSnapshot.child("image").getValue(String.class);
        chatUsername = dataSnapshot.child("name").getValue(String.class);
        stringComment = dataSnapshot.child("message").getValue(String.class);
        timestamp = dataSnapshot.child("timestamp").getValue(Long.class);
        if (stringComment != null) {
            allDatas.add(new AllData(stringImage, chatUsername, timestamp, stringComment, "0", "0", "0", "0", dataSnapshot.getKey()));
        }
        if (allDatas.size() != 0) {
            linearLayoutNoComment.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(allDatas.size());


    }

    @Override
    public void onClick(View view) {


    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Debugger.i("kunsangChildAdded","called"+s);
        appendChatConversation(dataSnapshot);
    }

    private static void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
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


    public static void sendMessage(String chat_message) {
        Map<String, Object> map = new HashMap<String, Object>();
        tempKey = _comment.push().getKey();
        _comment.updateChildren(map);
        DatabaseReference message_root = _comment.child(tempKey);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", userSessionManager.getName());
        map1.put("message", chat_message);
        map1.put("comments", "");
        map1.put("count", 0);
        map1.put("timestamp", ServerValue.TIMESTAMP);
        map1.put("image", userSessionManager.getProfilePic());
        message_root.updateChildren(map1);

    }
}
