package com.netforceinfotech.ibet.currentbet.betarena.live_event.events;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment implements View.OnClickListener {

    LinearLayout linearLayout;
    private Context context;
    ArrayList<EventsData> eventsDatas = new ArrayList<>();
    private EventsAdapter adapter;
    String teama, teamb, teamaid, teambid, matchid;
    ImageView imageViewHome, imageViewAway;
    TextView textViewTeamA, textViewTeamB, textViewTime, textViewHomeGoal, textViewAwayGoal;
    NestedScrollView nestedScrollView;
    LinearLayout linearLayoutProgress;
    LinearLayout linearLayoutVote, linearLayoutVoteButton;
    TextView textViewTeamAVote, textViewTeamBVote, textViewDrawVote;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        context = getActivity();
        try {
            teamaid = this.getArguments().getString("teamaid");
            teambid = this.getArguments().getString("teambid");
            matchid = this.getArguments().getString("matchid");
            teama = this.getArguments().getString("teama");
            teamb = this.getArguments().getString("teamb");
        } catch (Exception ex) {
            Log.i("kunsang_exception", "paramenter not yet set");
        }
        initView(view);
        Log.i("kunsangeventfr", matchid + " " + teamaid + " " + teambid);
        getEvents(matchid, teamaid, teambid);
        setupRecycler(view);
        return view;
    }

    private void initView(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        imageViewAway = (ImageView) view.findViewById(R.id.imageViewTeamA);
        imageViewHome = (ImageView) view.findViewById(R.id.imageViewTeamB);
        textViewTeamA = (TextView) view.findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) view.findViewById(R.id.textViewTeamB);
        textViewTime = (TextView) view.findViewById(R.id.textViewMinute);
        textViewHomeGoal = (TextView) view.findViewById(R.id.textViewHomeGoal);
        textViewAwayGoal = (TextView) view.findViewById(R.id.textViewAwayGoal);
        linearLayoutProgress = (LinearLayout) view.findViewById(R.id.linearLayoutProgress);
        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nestedscrollview);
        textViewTeamAVote = (TextView) view.findViewById(R.id.textViewTeamAVote);
        textViewTeamBVote = (TextView) view.findViewById(R.id.textViewTeamBVote);
        textViewDrawVote = (TextView) view.findViewById(R.id.textViewDrawVote);
        linearLayoutVote = (LinearLayout) view.findViewById(R.id.linearLayoutVote);
        linearLayoutVoteButton = (LinearLayout) view.findViewById(R.id.linearLayoutVoteButton);
        textViewTeamAVote.setOnClickListener(this);
        textViewTeamBVote.setOnClickListener(this);
        textViewDrawVote.setOnClickListener(this);

    }

    private void setupData() {
        /*eventsDatas.add(new EventsData("neymar", "", "goal", "76"));
        eventsDatas.add(new EventsData("", "bale", "goal", "55"));
        eventsDatas.add(new EventsData("masherano", "", "yellow", "34"));
        eventsDatas.add(new EventsData("Pique", "", "red", "12"));
        eventsDatas.add(new EventsData("", "Ronaldo", "goal", "6"));
        eventsDatas.add(new EventsData("", "", "", "0"));*/

    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapter = new EventsAdapter(context, eventsDatas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getEvents(String matchid, final String teamaid, String teambid) {
        //https://netforcesales.com/ibet_admin/api/events_by_match_id.php?matchid=614704&home_team_id=1370&away_team_id=1377
        String url = getResources().getString(R.string.url);
        url = url + "/events_by_match_id.php?matchid=" + matchid + "&home_team_id=" + teamaid + "&away_team_id=" + teambid;
        // url = url + "/events_by_match_id.php?matchid=" + "736799" + "&home_team_id=" + "6722" + "&away_team_id=" + "6724";
        Log.i("result url", url);
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        nestedScrollView.setVisibility(View.VISIBLE);
                        linearLayoutProgress.setVisibility(View.GONE);
                        if (result == null) {
                            showMessage("Somethings wrong");
                        } else {
                            try {

                                String status = result.get("status").getAsString();
                                if (status.equalsIgnoreCase("success")) {
                                    JsonObject dataObject = result.getAsJsonObject("data");
                                    JsonObject teamObject = dataObject.getAsJsonObject("team");
                                    String teama = "";
                                    String teamb = "";
                                    String teamalogo = "";
                                    String teamblogo = "";
                                    try {
                                        if (teamObject.get("hm_teamname").isJsonNull()) {
                                            teama = "";
                                        } else {
                                            teama = teamObject.get("hm_teamname").getAsString();
                                        }
                                        if (teamObject.get("aw_teamname").isJsonNull()) {
                                            teamb = "";
                                        } else {
                                            teamb = teamObject.get("aw_teamname").getAsString();
                                        }
                                        if (teamObject.get("hm_teamlogo").isJsonNull()) {
                                            teamalogo = "";
                                        } else {
                                            teamalogo = teamObject.get("hm_teamlogo").getAsString();
                                        }
                                        if (teamObject.get("aw_teamlogo").isJsonNull()) {
                                            teamblogo = "";
                                        } else {
                                            teamblogo = teamObject.get("aw_teamlogo").getAsString();
                                        }


                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    JsonArray data_event = dataObject.get("data_event").getAsJsonArray();
                                    if (data_event.size() == 0) {
                                        linearLayout.setVisibility(View.VISIBLE);
                                    } else {
                                        linearLayout.setVisibility(View.GONE);
                                    }
                                    if (data_event.size() > 0) {
                                        for (int i = 0; i < data_event.size(); i++) {
                                            JsonObject jsonObject = data_event.get(i).getAsJsonObject();
                                            String team_id = jsonObject.get("team_id").getAsString();
                                            String teamaName = "", teambName = "";
                                            String type = jsonObject.get("type").getAsString();
                                            String player_name = "";
                                            String player_in_name = "", player_out_name = "";
                                            if (type.equalsIgnoreCase("substitution")) {
                                                player_in_name = jsonObject.get("player_in_name").getAsString();
                                                player_out_name = jsonObject.get("player_out_name").getAsString();

                                            } else {
                                                player_name = jsonObject.get("player_name").getAsString();
                                            }

                                            String team;
                                            if (team_id.equalsIgnoreCase(teamaid)) {
                                                team = "a";
                                            } else {
                                                team = "b";
                                            }
                                            String minute = jsonObject.get("minute").getAsString();
                                            String extra_min = "0";
                                            if (jsonObject.get("extra_min").isJsonNull()) {
                                                extra_min = "0";
                                            } else {
                                                extra_min = jsonObject.get("extra_min").getAsString();
                                            }

                                            String time = "0";
                                            if (extra_min.equalsIgnoreCase("0")) {
                                                time = minute + "'";
                                            } else {
                                                time = minute + "'+" + extra_min;
                                                time = minute + "'+" + extra_min;
                                            }
                                            //EventsData(String name, String type, String id, String event, String time, String in, String out) {
                                            eventsDatas.add(new EventsData(player_name, type, team_id, time, player_in_name, player_out_name, team));
                                        }
                                        Collections.reverse(eventsDatas);
                                        eventsDatas.add(new EventsData("", "", "", "0", "", "", ""));
                                    }
                                    JsonArray data_score = dataObject.get("data_score").getAsJsonArray();
                                    JsonObject score_object = data_score.get(0).getAsJsonObject();
                                    String home_score = score_object.get("home_score").getAsString();
                                    String away_score = score_object.get("away_score").getAsString();
                                    String minute = score_object.get("minute").getAsString();
                                    String extra_minute = score_object.get("extra_minute").getAsString();
                                    textViewTeamA.setText(teama);
                                    textViewTeamB.setText(teamb);
                                    textViewHomeGoal.setText(home_score);
                                    textViewAwayGoal.setText(away_score);

                                    if (extra_minute.equalsIgnoreCase("0")) {
                                        textViewTime.setText(minute + "'");
                                    } else {
                                        textViewTime.setText(minute + "'+" + extra_minute);
                                    }
                                    if (teamalogo.length() > 0) {
                                        Picasso.with(context)
                                                .load(teamalogo)
                                                .placeholder(R.drawable.ic_holder)
                                                .error(R.drawable.ic_error)
                                                .into(imageViewHome);
                                    } else {
                                        imageViewHome.setImageResource(R.drawable.ic_error);
                                    }
                                    if (teamblogo.length() > 0) {
                                        Picasso.with(context)
                                                .load(teamblogo)
                                                .placeholder(R.drawable.ic_holder)
                                                .error(R.drawable.ic_error)
                                                .into(imageViewAway);
                                    } else {
                                        imageViewAway.setImageResource(R.drawable.ic_error);
                                    }

                                    adapter.notifyDataSetChanged();
                                } else {
                                    showMessage("json error");
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                    }
                });
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setHeader() {
        final String appkey = getResources().getString(R.string.appkey);
        Ion.getDefault(context).getHttpClient().insertMiddleware(new AsyncHttpClientMiddleware() {
            @Override
            public void onRequest(OnRequestData data) {
                data.request.setHeader("APPKEY", appkey);
            }

            @Override
            public Cancellable getSocket(GetSocketData data) {
                return null;
            }

            @Override
            public boolean exchangeHeaders(OnExchangeHeaderData data) {
                return false;
            }

            @Override
            public void onRequestSent(OnRequestSentData data) {

            }

            @Override
            public void onHeadersReceived(OnHeadersReceivedDataOnRequestSentData data) {

            }

            @Override
            public void onBodyDecoder(OnBodyDataOnRequestSentData data) {

            }

            @Override
            public void onResponseComplete(OnResponseCompleteDataOnRequestSentData data) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewTeamAVote:
            case R.id.textViewTeamBVote:
            case R.id.textViewDrawVote:
                linearLayoutVote.setVisibility(View.VISIBLE);
                linearLayoutVoteButton.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
