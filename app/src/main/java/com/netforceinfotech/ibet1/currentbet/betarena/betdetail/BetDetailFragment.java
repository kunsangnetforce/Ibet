package com.netforceinfotech.ibet1.currentbet.betarena.betdetail;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetDetailFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Context context;
    LinearLayout linearLayout;
    private DetailBetAdapter adapter;
    CoordinatorLayout coordinatorLayout;
    ArrayList<DetailBetData> detailBetDatas = new ArrayList<>();
    ImageView imageViewTeamA, imageViewTeamB;
    UserSessionManager userSessionManager;
    View view1;
    String home_name, away_name;
    TextView textViewMSI, textViewBetamount, textViewPlayer, textViewResult, textViewTeam, textViewScore, textViewLoserMessage, textViewMatchCountdown, textViewTeamA, textViewTeamB;

    public BetDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmentet_bet_detail, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        try {
            /*
            *    bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
            * */
            String bet_id = this.getArguments().getString("bet_id");
            home_name = this.getArguments().getString("home_name");
            away_name = this.getArguments().getString("away_name");
            getBetDetail(bet_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initView(view);
        setupTheme();
        setupBackground();
        setupRecyclerView(view);
        return view;
    }

    private void getBetDetail(String bet_id) {
        //https://netforcesales.com/ibet_admin/api/upcoming_bet_detail.php?&bet_id=237
        String baseUrl = getString(R.string.url);
        String url = baseUrl + "/upcoming_bet_detail.php?&bet_id=" + bet_id;
        Debugger.i("kunsang_url_betdetail", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (result == null) {
                    showMessage(getString(R.string.server_down));
                    return;
                } else {
                    setupBetDetail(result);
                }
            }
        });
    }

    private void setupBetDetail(JsonObject result) {
        try {
            if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                if (!result.getAsJsonObject("bet_detail").isJsonNull()) {
                    String home_id, away_id, team_away_flag, team_home_flag, away_name = "", home_name = "", bet_ammount, bet_match_time, bet_match_date;
                    JsonObject bet_detail = result.getAsJsonObject("bet_detail");
                    if (!bet_detail.getAsJsonObject("bet").isJsonNull()) {
                        JsonObject bet = bet_detail.getAsJsonObject("bet");


                        if (!bet.get("team_away_flag").isJsonNull()) {
                            team_away_flag = bet.get("team_away_flag").getAsString();
                            Glide.with(context).load(team_away_flag).error(R.drawable.ic_error).into(imageViewTeamB);
                        }
                        if (!bet.get("team_home_flag").isJsonNull()) {
                            team_home_flag = bet.get("team_home_flag").getAsString();
                            Glide.with(context).load(team_home_flag).error(R.drawable.ic_error).into(imageViewTeamA);
                        }

                        if (!bet.get("bet_ammount").isJsonNull()) {
                            bet_ammount = bet.get("bet_ammount").getAsString();
                            textViewBetamount.setText(bet_ammount);
                        }
                        if (!bet.get("bet_match_time").isJsonNull() && !bet.get("bet_match_date").isJsonNull()) {
                            bet_match_time = bet.get("bet_match_time").getAsString();
                            bet_match_date = bet.get("bet_match_date").getAsString();
                            setupTimeThread(bet_match_date, bet_match_time);
                        }

                    }
                    if (!bet_detail.getAsJsonArray("users").isJsonNull()) {
                        JsonArray users = bet_detail.getAsJsonArray("users");
                        int size = users.size();
                        if (size == 0) {
                            return;
                        } else {
                            for (int i = 0; i < size; i++) {
                                JsonObject user = users.get(i).getAsJsonObject();
                                String userdp = user.get("profile_image").getAsString();
                                String username = user.get("name").getAsString();
                                String selectedTeam = user.get("match_status").getAsString();
                                String bet_result = user.get("bet_result").getAsString();
                                String score = user.get("home_scrore").getAsString() + "-" + user.get("away_scrore");
                                if (selectedTeam.equalsIgnoreCase("home_win")) {
                                    selectedTeam = home_name;
                                } else if (selectedTeam.equalsIgnoreCase("away_win")) {
                                    selectedTeam = away_name;
                                } else {
                                    selectedTeam = "draw";
                                }
                                DetailBetData detailBetData = new DetailBetData(userdp, username, bet_result, selectedTeam, score);
                                detailBetDatas.add(detailBetData);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

            } else {
                showMessage(getString(R.string.no_data));
            }
        } catch (Exception ex) {
            showMessage(getString(R.string.something_went_wrong));
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        DetailBetAdapter detailBetAdapter = new DetailBetAdapter(context, detailBetDatas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(detailBetAdapter);
    }

    private void initView(View view) {
        view1 = view.findViewById(R.id.view);
        textViewPlayer = (TextView) view.findViewById(R.id.textViewPlayer);
        textViewResult = (TextView) view.findViewById(R.id.textViewResult);
        textViewScore = (TextView) view.findViewById(R.id.textViewScore);
        textViewTeam = (TextView) view.findViewById(R.id.textViewTeam);
        textViewTeamA = (TextView) view.findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) view.findViewById(R.id.textViewTeamB);
        textViewTeamA.setText(home_name);
        textViewTeamB.setText(away_name);
        textViewMatchCountdown = (TextView) view.findViewById(R.id.textViewMatchCountdown);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        textViewBetamount = (TextView) view.findViewById(R.id.textViewBetamount);
        textViewMSI = (TextView) view.findViewById(R.id.textViewMSI);
        textViewLoserMessage = (TextView) view.findViewById(R.id.textViewLoserMessage);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        view.findViewById(R.id.buttonClose).setOnClickListener(this);
        imageViewTeamA = (ImageView) view.findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (ImageView) view.findViewById(R.id.imageViewTeamB);
        Glide.with(context).load(R.drawable.ic_error).into(imageViewTeamA);
        Glide.with(context).load(R.drawable.ic_error).into(imageViewTeamB);
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
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));

    }

    private void setupPurlpleTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));

    }

    private void setupGreenTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

    }

    private void setupMarronTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentMarron));

    }

    private void setupLightBlueTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));

    }

    private void setupDefaultTheme() {
        textViewLoserMessage.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        textViewMSI.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewBetamount.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        textViewPlayer.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewResult.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewTeam.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        textViewScore.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
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

                textViewMatchCountdown.setText("" + getFormatedTime(millisUntilFinished));
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                textViewMatchCountdown.setText("Live!");
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

}
