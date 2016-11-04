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

    @Override
    public void onResume() {
        super.onResume();
        getBetsToJoin();
    }

    private void getBetsToJoin() {
        //https://netforcesales.com/ibet_admin/api/bets_to_join.php?&user_id=163
        String baseUrl = getString(R.string.url);
        String url = baseUrl + "/bets_to_join.php?&user_id=" + userSessionManager.getCustomerId();
        Debugger.i("kunsang_url_betstoJoin", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (result == null) {
                    linearLayoutNoBets.setVisibility(View.VISIBLE);
                } else {
                    setupbetsToJoinDatas(result);
                }
            }
        });
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
                    return;
                }
                linearLayoutNoBets.setVisibility(View.GONE);
                for (int i = 0; i < size; i++) {
                    JsonObject jsonObject = data.get(i).getAsJsonObject();
                    String name,creator_id,userdp,participants,home_name,away_name,home_logo,away_logo,bet_match_date,bet_match_time,matchid,bet_option,bet_amount,betid;
                    if(jsonObject.get("name").isJsonNull()){
                        name="";
                    }
                    else {
                        name = jsonObject.get("name").getAsString();
                    }

                    if(jsonObject.get("bet_creator").isJsonNull()){
                        creator_id="";
                    }
                    else {
                        creator_id = jsonObject.get("bet_creator").getAsString();
                    }
                    if(jsonObject.get("profile_image").isJsonNull()){
                        userdp="";
                    }
                    else {
                        userdp = jsonObject.get("profile_image").getAsString();
                    }

                    if(jsonObject.get("participants").isJsonNull()){
                        participants="";
                    }
                    else {
                        participants = jsonObject.get("participants").getAsString();
                    }

                    if(jsonObject.get("home_teamname").isJsonNull()){
                        home_name  ="";
                    }
                    else {
                        home_name = jsonObject.get("home_teamname").getAsString();
                    }
                    if(jsonObject.get("away_teamname").isJsonNull()){
                        away_name="";
                    }
                    else {
                        away_name = jsonObject.get("away_teamname").getAsString();
                    }
                    if(jsonObject.get("team_home_flag").isJsonNull()){
                        home_logo="";
                    }
                    else {
                        home_logo = jsonObject.get("team_home_flag").getAsString();
                    }

                    if(jsonObject.get("team_away_flag").isJsonNull()){
                        away_logo="";
                    }
                    else {
                        away_logo = jsonObject.get("team_away_flag").getAsString();
                    }
                    if(jsonObject.get("bet_match_date").isJsonNull()){
                        bet_match_date="";
                    }
                    else {
                        bet_match_date = jsonObject.get("bet_match_date").getAsString();
                    }

                    if(jsonObject.get("name").isJsonNull()){
                        bet_match_time="";
                    }
                    else {
                        bet_match_time = jsonObject.get("bet_match_time").getAsString();
                    }
                    if(jsonObject.get("bet_match_id").isJsonNull()){
                        matchid="";
                    }
                    else {
                        matchid = jsonObject.get("bet_match_id").getAsString();
                    }

                    if(jsonObject.get("bet_option").isJsonNull()){
                        bet_option="";
                    }
                    else {
                        bet_option = jsonObject.get("bet_option").getAsString();
                    }
                    if(jsonObject.get("bet_amount").isJsonNull()){
                        bet_amount="";
                    }
                    else {
                        bet_amount = jsonObject.get("bet_amount").getAsString();
                    }
                    if(jsonObject.get("betid").isJsonNull()){
                        betid="";
                    }
                    else {
                        betid = jsonObject.get("betid").getAsString();
                    }
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
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }
}
