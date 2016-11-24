package com.netforceinfotech.ibet1.currentbet.upcoming;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */


public class UpcomingBetFragment extends Fragment {

    ArrayList<UpcomingBetData> upcomingBetDatas = new ArrayList<>();
    Context context;
    UserSessionManager userSessionManager;
    int theme;
    FrameLayout upcomminhg_bet_latout;
    private UpcomingBetAdapter adapter;
    LinearLayout linearLayoutNoBets;
    ImageView imageViewNoBets;
    private SwipyRefreshLayout swipyRefreshLayout;

    public UpcomingBetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_bet, container, false);
        Log.i("testingkunsang", "reahced upcoming");
        context = getActivity();

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        initView(view);
        setupRecyclerView(view);
        if (!userSessionManager.getLoginMode().equalsIgnoreCase("0")) {
            getUpcomingBets();
        }
        return view;
    }

    private void initView(View view) {
        swipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                getUpcomingBets();
            }
        });
        linearLayoutNoBets = (LinearLayout) view.findViewById(R.id.linearLayoutNoBets);
        imageViewNoBets = (ImageView) view.findViewById(R.id.imageViewNoBets);
        Glide.with(context).load(R.drawable.gs_stadium).into(imageViewNoBets);
        linearLayoutNoBets.setVisibility(View.GONE);
    }

    private void getUpcomingBets() {
        String url = "https://netforcesales.com/ibet_admin/api/upcoming_bets.php?&user_id=" + userSessionManager.getCustomerId();
        Debugger.i("kunsang_url_upcomingbets", url);

        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                try {
                    swipyRefreshLayout.setRefreshing(false);
                } catch (Exception ex) {

                }
                if (result == null) {
                    linearLayoutNoBets.setVisibility(View.VISIBLE);
                } else {
                    setupupcomingBetDatas(result);
                }
            }
        });
    }

    private void setupupcomingBetDatas(JsonObject result) {
        try {
            upcomingBetDatas.clear();
        } catch (Exception ex) {

        }
        JsonArray data = null;
        try {
            if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                data = result.getAsJsonArray("data");
            } else {
                linearLayoutNoBets.setVisibility(View.VISIBLE);
                return;
            }
        } catch (Exception ex) {
            linearLayoutNoBets.setVisibility(View.VISIBLE);
            return;
        }
        int size = data.size();
        linearLayoutNoBets.setVisibility(View.GONE);

        for (int i = 0; i < size; i++) {
            JsonObject jsonObject = data.get(i).getAsJsonObject();
            String creatorDp = "", creatorName = "", participantsCount = "", date_time = "", homeLogo = "", homeName = "", home_id = "", matchid = "", awayLogo = "", awayName = "", away_id = "", seasonid = "", betId = "";
            try {
                creatorDp = jsonObject.get("profile_image").getAsString();
                creatorName = jsonObject.get("name").getAsString();
                participantsCount = jsonObject.get("participants").getAsString();
                date_time = jsonObject.get("bet_match_date").getAsString() + " " + jsonObject.get("bet_match_time").getAsString();
                homeLogo = jsonObject.get("team_home_flag").getAsString();
                awayLogo = jsonObject.get("team_away_flag").getAsString();
                if (!jsonObject.get("home_teamname").isJsonNull()) {
                    homeName = jsonObject.get("home_teamname").getAsString();
                }
                if (!jsonObject.get("away_teamname").isJsonNull()) {
                    awayName = jsonObject.get("away_teamname").getAsString();
                }
                betId = jsonObject.get("betid").getAsString();
                matchid = jsonObject.get("bet_match_id").getAsString();

                home_id = jsonObject.get("team_home").getAsString();
                away_id = jsonObject.get("team_away").getAsString();
                seasonid = jsonObject.get("season_id").getAsString();
            } catch (Exception ex) {

            }
            if (participantsCount.equalsIgnoreCase("") || participantsCount.trim().length() <= 0) {
                participantsCount = "0";
            }
            //String userdp, String name, String selectedteamlogo, String selectedteamname,
            // String numberparticipant, String numberpost, String time, String teamalogo, String teamblogo,
            // String teamaname, String teambname, String betstatus, String betid, String home_id, String away_id,String matchid,String seasonid) {
            upcomingBetDatas.add(new UpcomingBetData(creatorDp, creatorName, "", "", participantsCount, "", date_time, homeLogo,
                    awayLogo, homeName, awayName, "", betId, home_id, away_id, matchid, seasonid));
        }
        adapter.notifyDataSetChanged();
    }


    private void setupRecyclerView(View view) {

        upcomminhg_bet_latout = (FrameLayout) view.findViewById(R.id.upcomminhg_bet_latout);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UpcomingBetAdapter(context, upcomingBetDatas);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


}
