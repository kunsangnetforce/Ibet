package com.netforceinfotech.ibet1.currentbet.livebet;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveBetFragment extends Fragment {
    Context context;
    private RecyclerView recyclerView;
    ArrayList<LiveBetData> liveBetDatas = new ArrayList<>();
    private LiveBetAdapter adapter;
    UserSessionManager userSessionManager;
    int theme;
    FrameLayout livebet;
    private ExpandableListView expListView;
    HashMap<String, List<LiveBetData>> listDataChild = new HashMap<String, List<LiveBetData>>();
    LinearLayout linearLayoutNoBets;
    ImageView imageViewNoBets;
    SwipyRefreshLayout swipyRefreshLayout;

    public LiveBetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_bet, container, false);
        Log.i("testingkunsang", "reahced livebet");
        context = getActivity();

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        initView(view);
        setupRecyclerView(view);
        //   setupRecyclerView(view);
        if (!userSessionManager.getLoginMode().equalsIgnoreCase("0")) {
            getrLiveBets();
        }
        return view;


    }

    private void initView(View view) {
        swipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                getrLiveBets();
            }
        });
        linearLayoutNoBets = (LinearLayout) view.findViewById(R.id.linearLayoutNoBets);
        imageViewNoBets = (ImageView) view.findViewById(R.id.imageViewNoBets);
        Glide.with(context).load(R.drawable.gs_stadium).into(imageViewNoBets);
        linearLayoutNoBets.setVisibility(View.GONE);
    }


    private void setupRecyclerView(View view) {

        livebet = (FrameLayout) view.findViewById(R.id.livebet);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new LiveBetAdapter(context, liveBetDatas);
        recyclerView.setAdapter(adapter);


    }


    private void getrLiveBets() {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=live_bets&user_id=136
        String baseUrl=getString(R.string.url);
        String url=baseUrl+"/services.php?opt=live_bets&user_id="+ userSessionManager.getCustomerId();
        //String url = "https://netforcesales.com/ibet_admin/api/services.php?opt=live_bets&user_id="
        Debugger.i("kunsang_url_LiveBets", url);
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
            liveBetDatas.clear();
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
            JsonObject creator=jsonObject.getAsJsonObject("creator");
            String creatorDp = creator.get("image").getAsString();
            String creatorName = creator.get("name").getAsString();
            String participantsCount = jsonObject.get("participants_count").getAsString();
            String date_time = jsonObject.get("match_start_time").getAsString();
            String homeLogo = "";
            String awayLogo = "";
            JsonObject home=jsonObject.getAsJsonObject("home");
            JsonObject away=jsonObject.getAsJsonObject("away");
            String homeName = "";
            if (!home.get("name").isJsonNull()) {
                homeName = home.get("name").getAsString();
                homeLogo=home.get("logo").getAsString();
            }
            String awayName = "";
            if (!away.get("name").isJsonNull()) {
                awayName = away.get("name").getAsString();
                awayLogo=away.get("logo").getAsString();
            }
            String betId = jsonObject.get("bet_id").getAsString();
            String matchid = jsonObject.get("match_id").getAsString();
            String home_id = "", away_id = "", seasonid = "";
            seasonid = jsonObject.get("season_id").getAsString();
            if (participantsCount.equalsIgnoreCase("") || participantsCount.trim().length() <= 0) {
                participantsCount = "0";
            }
            liveBetDatas.add(new LiveBetData(creatorDp, creatorName, "", "", participantsCount, "", date_time, homeLogo, awayLogo, homeName, awayName, "", betId, home_id, away_id, matchid, seasonid));
        }
        adapter.notifyDataSetChanged();
    }

}
