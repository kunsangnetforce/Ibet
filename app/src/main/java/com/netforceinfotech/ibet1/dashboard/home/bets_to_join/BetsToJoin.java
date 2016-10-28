package com.netforceinfotech.ibet1.dashboard.home.bets_to_join;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetsToJoin extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    ArrayList<BetsToJoinData> betsToJoinDatas = new ArrayList<>();
    private BetsToJoinAdapter adapter;
    UserSessionManager userSessionManager;
    LinearLayout linearLayoutNoBets;
    ImageView imageViewNoBets;

    public BetsToJoin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bets_to_join, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        initView(view);
        setupRecyclerView(view);
        getBetsToJoin();
        return view;
    }

    private void initView(View view) {
        linearLayoutNoBets = (LinearLayout) view.findViewById(R.id.linearLayoutNoBets);
        imageViewNoBets = (ImageView) view.findViewById(R.id.imageViewNoBets);
        Glide.with(context).load(R.drawable.gs_stadium).into(imageViewNoBets);
        linearLayoutNoBets.setVisibility(View.GONE);
    }

    private void getBetsToJoin() {
        //https://netforcesales.com/ibet_admin/api/bets_to_join.php?&user_id=163
        String baseUrl = getString(R.string.url);
        String url = baseUrl + "/bets_to_join.php?&user_id=" + userSessionManager.getCustomerId();
        Debugger.i("kurl", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (result == null) {
                    linearLayoutNoBets.setVisibility(View.VISIBLE);
                    showMessage("No bets to join");
                } else {
                    setupbetsToJoinDatas(result);
                }
            }
        });
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BetsToJoinAdapter(context, betsToJoinDatas);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setupbetsToJoinDatas(JsonObject result) {
        try {
            betsToJoinDatas.clear();
        } catch (Exception ex) {

        }

        try {
            if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                JsonArray data = result.getAsJsonArray("data");
                int size = data.size();
                if (size == 0) {
                    linearLayoutNoBets.setVisibility(View.VISIBLE);
                    showMessage(getString(R.string.no_bets_to_join));
                    return;
                }
                linearLayoutNoBets.setVisibility(View.GONE);
                for (int i = 0; i < size; i++) {
                    JsonObject jsonObject = data.get(i).getAsJsonObject();
                    String name = jsonObject.get("name").getAsString();
                    String creator_id = jsonObject.get("bet_creator").getAsString();
                    String userdp = jsonObject.get("profile_image").getAsString();
                    String participants = jsonObject.get("participants").getAsString();
                    String home_name = jsonObject.get("home_teamname").getAsString();
                    String away_name = jsonObject.get("away_teamname").getAsString();
                    String home_logo = jsonObject.get("team_home_flag").getAsString();
                    String away_logo = jsonObject.get("team_away_flag").getAsString();
                    String bet_match_date = jsonObject.get("bet_match_date").getAsString();
                    String bet_match_time = jsonObject.get("bet_match_time").getAsString();
                    String matchid = jsonObject.get("bet_match_id").getAsString();
                    String bet_option = jsonObject.get("bet_option").getAsString();
                    String bet_amount = jsonObject.get("bet_amount").getAsString();
                    String betid = jsonObject.get("betid").getAsString();
                    String time = bet_match_date + " " + bet_match_time;
//String userdp, String name, String numberparticipant, String time, String teamalogo, String teamblogo,
// String teamaname, String teambname, String betid) {
                    BetsToJoinData betsToJoin = new BetsToJoinData(userdp, name, participants, time, home_logo,
                            away_logo, home_name, away_name, betid, matchid, bet_option, bet_amount);
                    betsToJoinDatas.add(betsToJoin);

                }
                adapter.notifyDataSetChanged();
            } else {
                linearLayoutNoBets.setVisibility(View.VISIBLE);
                showMessage("something went wrong");
                return;
            }
        } catch (Exception ex) {
            showMessage("something went wrong");
            return;
        }
    }
}
