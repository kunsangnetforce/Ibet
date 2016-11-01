package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.detail.events;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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

import com.bcgdv.asia.lib.ticktock.TickTockView;
import com.bumptech.glide.Glide;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

import at.grabner.circleprogress.CircleProgressView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment implements View.OnClickListener {
    final Handler handler = new Handler();
    LinearLayout linearLayout;
    private Context context;
    ArrayList<EventsData> eventsDatas = new ArrayList<>();
    private EventsAdapter adapter;
    String home_name, away_name, home_id, away_id, match_id;
    ImageView imageViewHome, imageViewAway;
    TextView textViewTeamA, textViewTeamB, textViewTime, textViewHomeGoal, textViewAwayGoal;
    NestedScrollView nestedScrollView;
    LinearLayout linearLayoutProgress;
    LinearLayout linearLayoutVote, linearLayoutVoteButton;
    TextView textViewTeamAVote, textViewTeamBVote, textViewDrawVote;
    TextView textViewGoal;
    private String voted;
    private String login_mode;
    private String matchstatus;
    String home_logo, away_logo;
    TextView textViewMatchStatus;
    CircleProgressView cpvLevel;
    CircleProgress circle_progressA, circle_progressD, circle_progressB;
    TickTockView tickTockView;
    SwipyRefreshLayout swipeRefreshLayout;
    TextView textViewNoVB, textViewNoVA, textViewNoVD;
    private boolean firstTime = true;

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
            home_id = this.getArguments().getString("home_id");
            away_id = this.getArguments().getString("away_id");
            match_id = this.getArguments().getString("match_id");
            home_name = this.getArguments().getString("home_name");
            away_name = this.getArguments().getString("away_name");
            home_logo = this.getArguments().getString("home_logo");
            away_logo = this.getArguments().getString("away_logo");
        } catch (Exception ex) {
            showMessage("bundle error");
            Log.i("kunsang_exception", "paramenter not yet set");
        }
        initView(view);
        Log.i("kunsangeventfr", match_id + " " + home_id + " " + away_id);
        getEvents(match_id, home_id, away_id);
        setupRecycler(view);
        return view;
    }

    private void initView(View view) {
        textViewNoVB = (TextView) view.findViewById(R.id.textViewNoVB);
        textViewNoVA = (TextView) view.findViewById(R.id.textViewNoVA);
        textViewNoVD = (TextView) view.findViewById(R.id.textViewNoVD);
        circle_progressA = (CircleProgress) view.findViewById(R.id.circle_progressA);
        circle_progressB = (CircleProgress) view.findViewById(R.id.circle_progressB);
        circle_progressD = (CircleProgress) view.findViewById(R.id.circle_progressD);
        swipeRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                getEvents(match_id, home_id, away_id);
            }
        });
        tickTockView = (TickTockView) view.findViewById(R.id.tickTockView);
        cpvLevel = (CircleProgressView) view.findViewById(R.id.cpvLevel);
        textViewMatchStatus = (TextView) view.findViewById(R.id.textViewMatchStatus);
        textViewGoal = (TextView) view.findViewById(R.id.textViewGoal);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutInput);
        imageViewAway = (ImageView) view.findViewById(R.id.imageViewTeamB);
        imageViewHome = (ImageView) view.findViewById(R.id.imageViewTeamA);
        textViewTeamA = (TextView) view.findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) view.findViewById(R.id.textViewTeamB);
        textViewTime = (TextView) view.findViewById(R.id.textViewLevelNumber);
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
        //https://netforcesales.com/ibet_admin/api/events_by_match_id.php?matchid=735681&home_team_id=127&away_team_id=174&login_mode=1
        UserSessionManager userSessionManager = new UserSessionManager(context);
        login_mode = userSessionManager.getLoginMode();
        String url = getResources().getString(R.string.url);
        url = url + "/events_by_match_id.php?matchid=" + matchid + "&home_team_id=" + teamaid + "&away_team_id=" + teambid + "&login_mode=" + login_mode + "&user_id=" + userSessionManager.getCustomerId();
        // url = url + "/events_by_match_id.php?match_id=" + "736799" + "&home_team_id=" + "6722" + "&away_team_id=" + "6724";
        Debugger.i("kunsang_url_getevent", url);
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        nestedScrollView.setVisibility(View.VISIBLE);
                        linearLayoutProgress.setVisibility(View.GONE);
                        swipeRefreshLayout.setRefreshing(false);
                        if (result == null) {
                            showMessage("Somethings wrong");
                        } else {
                            setupResult(result);
                        }

                    }
                });
    }

    private void setupResult(JsonObject result) {
        try {
            String status = "";
            if (!result.get("status").isJsonNull()) {
                status = result.get("status").getAsString();
            }

            if (status.equalsIgnoreCase("success")) {
                voted = "0";
                if (!result.get("voted").isJsonNull()) {
                    voted = result.get("voted").getAsString();
                }
                JsonObject dataObject = result.getAsJsonObject("data");
                try {
                    JsonArray vote_count = dataObject.getAsJsonArray("vote_count");
                    JsonObject vote_object = vote_count.get(0).getAsJsonObject();
                    int teamA = Integer.parseInt(vote_object.get("teamA").getAsString());
                    int teamB = Integer.parseInt(vote_object.get("teamB").getAsString());
                    int draw = Integer.parseInt(vote_object.get("draw").getAsString());
                    setupCircieProgress(teamA, teamB, draw);
                } catch (Exception ex) {
                    Debugger.i("kvote", "someerror");
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
                        String id = jsonObject.get("id").getAsString();
                        String team_id = jsonObject.get("team_id").getAsString();
                        String type = jsonObject.get("type").getAsString();
                        String player_name = "";
                        String player_in_name = "", player_out_name = "";
                        if (type.equalsIgnoreCase("substitution")) {
                            try {
                                player_in_name = jsonObject.get("player_in_name").getAsString();
                            } catch (Exception ex) {
                                player_in_name = "not known";
                            }
                            try {
                                player_out_name = jsonObject.get("player_out_name").getAsString();
                            } catch (Exception ex) {
                                player_out_name = "not known";
                            }


                        } else {
                            player_name = jsonObject.get("player_name").getAsString();
                        }

                        String team;
                        if (team_id.equalsIgnoreCase(home_id)) {
                            team = "a";
                        } else {
                            team = "b";
                        }
                        Log.i("kunsangteam", team);
                        String minute = jsonObject.get("minute").getAsString();
                        String extra_min = "0";
                        if (jsonObject.get("extra_min").isJsonNull()) {
                            extra_min = "0";
                        } else {
                            extra_min = jsonObject.get("extra_min").getAsString();
                        }

                        EventsData eventsData = new EventsData(player_name, type, team_id, minute, extra_min, player_in_name, player_out_name, team, id);
                        if (!eventsDatas.contains(eventsData)) {
                            eventsDatas.add(eventsData);
                        }

                    }
                    EventsData eventsData0 = new EventsData("", "", "", "0", "", "", "", "", "12");
                    if (!eventsDatas.contains(eventsData0)) {
                        eventsDatas.add(eventsData0);
                    }
                    Collections.sort(eventsDatas);
                    Collections.reverse(eventsDatas);
                    try {
                        adapter.notifyDataSetChanged();
                    } catch (Exception ex) {

                    }
                }
                JsonArray data_score = dataObject.get("data_score").getAsJsonArray();
                JsonObject score_object = data_score.get(0).getAsJsonObject();
                //status: "FT",
                matchstatus = "";
                String home_score = score_object.get("home_score").getAsString();
                String away_score = score_object.get("away_score").getAsString();
                try {
                    matchstatus = score_object.get("status").getAsString();
                    //NS,LIVE,HT,FT,ET,PEN_LIVE,AET,BREAK,FT_PEN_CANCL,POSTP,INT,ABAN,SUSP,AWARDED,DELAYED

                    if (matchstatus.equalsIgnoreCase("FT")) {
                        String goal = score_object.get("ft_score").getAsString();
                        textViewGoal.setText(goal);
                    } else {
                        textViewGoal.setText(home_score + "-" + away_score);

                    }
                } catch (Exception ex) {


                }

                String minute = score_object.get("minute").getAsString();
                String extra_minute = score_object.get("extra_minute").getAsString();
                textViewTeamA.setText(home_name);
                textViewTeamB.setText(away_name);
                textViewHomeGoal.setText(home_score);
                textViewAwayGoal.setText(away_score);
                switch (matchstatus) {
                    case "NS":
                        //starting_date: "2016-08-20",
                        //starting_time: "16:15:00",
                        String starting_date = score_object.get("starting_date").getAsString();
                        String starting_time = score_object.get("starting_time").getAsString();
                        setupTimeThread(starting_date, starting_time);
                        break;
                    case "LIVE":
                        if (firstTime) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MINUTE, 100);
                            tickTockView.start(calendar);
                            firstTime = !firstTime;

                        }
                        setupProgressThread(minute, extra_minute, 5000);
                        textViewMatchStatus.setText("LIVE");
                        break;
                    case "HT":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        setupProgressThread(minute, extra_minute, 60000);
                        textViewMatchStatus.setText("HALF TIME");
                        break;
                    case "FT":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }

                        setupProgressThread(minute, extra_minute, 5000);
                        textViewMatchStatus.setText("FULL TIME");
                        break;
                    case "ET":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        setupProgressThread(minute, extra_minute, 60000);
                        cpvLevel.setMaxValue(120);
                        textViewMatchStatus.setText("EXTRA TIME");
                        break;
                    case "PEN_LIVE":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        setupProgressThread(minute, extra_minute, 5000);
                        textViewMatchStatus.setText("PENALTY SHOOTOUT");
                        break;
                    case "AET":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        cpvLevel.setMaxValue(120);
                        textViewMatchStatus.setText("FISHED AFTER EXTRA TIME");
                        break;
                    case "BREAK":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        setupProgressThread(minute, extra_minute, 60000);
                        cpvLevel.setMaxValue(120);
                        textViewMatchStatus.setText("Match finished, waiting for extra time to start");
                        break;
                    case "FT_PEN":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        textViewMatchStatus.setText("Full-Time after penalties");
                        break;
                    case "CANCL":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        textViewMatchStatus.setText("Cancelled");
                        break;
                    case "POSTP":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        textViewMatchStatus.setText("PostPhoned");
                        break;
                    case "INT":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        textViewMatchStatus.setText("Interrupted");
                        break;
                    case "ABAN":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        textViewMatchStatus.setText("Abandoned");
                        break;
                    case "SUSP":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        textViewMatchStatus.setText("Suspended");
                        break;
                    case "AWARDED":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        textViewMatchStatus.setText("Awarded");
                        break;
                    case "DELAY":
                        try {
                            tickTockView.stop();
                        } catch (Exception ex) {

                        }
                        textViewMatchStatus.setText("Delayed");
                        break;
                }
                if (extra_minute.equalsIgnoreCase("0")) {
                    textViewTime.setText(minute + "'");
                } else {
                    textViewTime.setText(minute + "'+" + extra_minute);
                }
                if (home_logo.length() > 0) {
/*                    Picasso.with(context)
                            .load(home_logo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewHome);*/
                    Glide.with(context).load(home_logo).placeholder(R.drawable.ic_circle_filled).error(R.drawable.ic_error).into(imageViewHome);
                } else {
                    Glide.with(context).load(R.drawable.ic_error).into(imageViewHome);
                }
                if (away_logo.length() > 0) {
                    Glide.with(context).load(away_logo).placeholder(R.drawable.ic_circle_filled).error(R.drawable.ic_error).into(imageViewAway);
                } else {
                    Glide.with(context).load(R.drawable.ic_error).into(imageViewAway);
                }

                //adapter.notifyItemRangeChanged(0, adapter.getItemCount());
            } else {
                showMessage("json error");
            }
            if (matchstatus.equalsIgnoreCase("NS")) {
                if (login_mode.equalsIgnoreCase("0")) {
                    linearLayoutVoteButton.setVisibility(View.INVISIBLE);
                    linearLayoutVote.setVisibility(View.VISIBLE);
                } else if (login_mode.equalsIgnoreCase("1") && voted.equalsIgnoreCase("1")) {
                    linearLayoutVoteButton.setVisibility(View.INVISIBLE);
                    linearLayoutVote.setVisibility(View.VISIBLE);
                } else {
                    linearLayoutVoteButton.setVisibility(View.VISIBLE);
                    linearLayoutVote.setVisibility(View.INVISIBLE);
                }
            } else {
                linearLayoutVoteButton.setVisibility(View.INVISIBLE);
                linearLayoutVote.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.i("kunsangException", "error");
            ex.printStackTrace();
        }
    }

    private void setupCircieProgress(int teamA, int teamB, int draw) {
        int total = teamA + teamB + draw;
        if (total == 0) {
            circle_progressA.setProgress(0);
            circle_progressB.setProgress(0);
            circle_progressD.setProgress(0);
            textViewNoVA.setText("" + teamA);
            textViewNoVB.setText("" + teamB);
            textViewNoVD.setText("" + draw);
            return;
        }
        int teamAprogress = (teamA * 100) / total;
        int teamBprogress = (teamB * 100) / total;
        int drawProgress = (draw * 100) / total;
        circle_progressA.setProgress(teamAprogress);
        circle_progressB.setProgress(teamBprogress);
        circle_progressD.setProgress(drawProgress);
        textViewNoVA.setText("" + teamA);
        textViewNoVB.setText("" + teamB);
        textViewNoVD.setText("" + draw);
    }

    private void setupProgressThread(String minute, String extra_minute, int i) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                try {
                    getEvents(match_id, home_id, away_id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, i);
    }

    private void setupTimeThread(String starting_date, String starting_time) {
        String datetime = starting_date + " " + starting_time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = Calendar.getInstance().getTime();
        Date myDate = null;
        try {
            myDate = simpleDateFormat.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long milliseconds = (myDate.getTime() - dateNow.getTime());
        new CountDownTimer(milliseconds, 1000) {

            public void onTick(long millisUntilFinished) {

                textViewMatchStatus.setText("" + getFormatedTime(millisUntilFinished));
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                textViewMatchStatus.setText("Live!");
                getEvents(match_id, home_id, away_id);
            }

        }.start();
    }

    private String getFormatedTime(long millisUntilFinished) {
        long seconds = millisUntilFinished / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + "D : " + hours % 24 + " : " + minutes % 60 + " : " + seconds % 60;
        return time;
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
                vote("teama");
                linearLayoutVote.setVisibility(View.VISIBLE);
                linearLayoutVoteButton.setVisibility(View.INVISIBLE);
                break;
            case R.id.textViewTeamBVote:
                vote("teamb");
                linearLayoutVote.setVisibility(View.VISIBLE);
                linearLayoutVoteButton.setVisibility(View.INVISIBLE);
                break;
            case R.id.textViewDrawVote:
                vote("draw");
                linearLayoutVote.setVisibility(View.VISIBLE);
                linearLayoutVoteButton.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void vote(String team) {

        //http://localhost/betting/api/votes_by_match.php?match_id=614704&user_id=12&vote=teama/teamb/draw
        UserSessionManager userSessionManager = new UserSessionManager(context);
        String url = getResources().getString(R.string.url);
        String user_id = userSessionManager.getCustomerId();
        url = url + "/votes_by_match.php?match_id=" + match_id + "&user_id=" + user_id + "&vote=" + team;
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
                            setupVote(result);
                        }

                    }
                });

    }

    private void setupVote(JsonObject result) {
        Log.i("kunsang_vote", result.toString());
        try {
            if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                int teamACount = Integer.parseInt(result.get("teama count").getAsString());
                int teamBCount = Integer.parseInt(result.get("teamb count").getAsString());
                int teamDCount = Integer.parseInt(result.get("draw count").getAsString());
                setupCircieProgress(teamACount, teamBCount, teamDCount);
            }
        } catch (Exception ex) {
            showMessage(getString(R.string.something_went_wrong));
        }
    }
}
