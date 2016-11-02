package com.netforceinfotech.ibet1.dashboard.home.finsihed_bet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class FinsihedBet extends Fragment {


    private RecyclerView recyclerView;
    private Context context;
    private FinishedBetAdapter adapter;
    private LinearLayoutManager layoutManager;
    static protected ArrayList<FinsihedData> finsihedDatas = new ArrayList<>();
    FrameLayout frameLayout;
    UserSessionManager userSessionManager;
    LinearLayout linearLayoutNoBets;
    ImageView imageViewNoBets;
    int theme;


    public FinsihedBet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finsihed_bet, container, false);

        context = getActivity();
        initView(view);
        setupRecyclerView(view);
        getFinishedData();
        return view;
    }

    private void initView(View view) {
        linearLayoutNoBets = (LinearLayout) view.findViewById(R.id.linearLayoutNoBets);
        imageViewNoBets = (ImageView) view.findViewById(R.id.imageViewNoBets);
        Glide.with(context).load(R.drawable.gs_stadium).into(imageViewNoBets);
        linearLayoutNoBets.setVisibility(View.GONE);
    }

    private void getFinishedData() {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=finished_bet&user_id=137
        String baseUrl = getString(R.string.url);
        String url = baseUrl + "/services.php?opt=finished_bet&user_id=" + userSessionManager.getCustomerId();
        Debugger.i("kunsang_url_finishbet", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (result == null) {
                    linearLayoutNoBets.setVisibility(View.VISIBLE);
                } else {
                    Debugger.i("kresult", result.toString());
                    if (result.get("status").getAsString().equalsIgnoreCase("success")) {

                        setupFinsihedDatas(result);
                    } else {
                        linearLayoutNoBets.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getFinishedData();
    }

    private void setupRecyclerView(View view) {
          /*
        *  recyclerView_Same = (RecyclerView) findViewById(R.id.recyclerFeatured);
        layoutManagerSame = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Same.setLayoutManager(layoutManagerSame);
        adapterSame = new RecyclerViewAdapterS(context, rowDataS, imagePath);
        recyclerView_Same.setAdapter(adapterSame);
        * */
        frameLayout = (FrameLayout) view.findViewById(R.id.frame_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FinishedBetAdapter(context, finsihedDatas);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void setupFinsihedDatas(JsonObject result) {
        try {
            finsihedDatas.clear();
        } catch (Exception ex) {

        }
       /* //String name, selectedteamlogo, selectedteamname, numberparticipant, numberpost, time, home_logo, away_logo, home_name, away_name, betstatus, betid;
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
   */
        JsonArray data = result.getAsJsonArray("data");
        int size = data.size();
        if (size == 0) {
            linearLayoutNoBets.setVisibility(View.VISIBLE);
            return;
        }
        for (int i = 0; i < size; i++) {
            JsonObject jsonObject = data.get(i).getAsJsonObject();
            String bet_id = jsonObject.get("bet_id").getAsString();
            String match_id = jsonObject.get("match_id").getAsString();
            JsonObject creator = jsonObject.getAsJsonObject("creator");
            String creator_id = creator.get("user_id").getAsString();
            String name = creator.get("name").getAsString();
            String creator_dp = creator.get("image").getAsString();
            try {
                JsonObject home = jsonObject.getAsJsonObject("home");
                JsonObject away = jsonObject.getAsJsonObject("away");
                String home_id = home.get("id").getAsString();
                String home_name = home.get("name").getAsString();

                String home_logo = "";
                if (!home.get("logo").isJsonNull()) {
                    home_logo = home.get("logo").getAsString();
                }

                String away_id = away.get("id").getAsString();
                String away_name = away.get("name").getAsString();
                String away_logo = "";
                if (!away.get("logo").isJsonNull()) {
                    away_logo = away.get("logo").getAsString();
                }
                String match_start_time = jsonObject.get("match_start_time").getAsString();
                String participants_count = jsonObject.get("participants_count").getAsString();
                String betresult = "NA";
                if (!jsonObject.get("bet_result").isJsonNull()) {
                    betresult = jsonObject.get("bet_result").getAsString();
                }
                //FinsihedData(String userdp, String name, String numberparticipant,
                // String numberpost, String time, String teamalogo, String teamblogo,
                // String teamaname, String teambname, String betstatus, String betid, String match_id) {
                FinsihedData finsihedData = new FinsihedData(creator_dp, name, participants_count, match_start_time, home_logo, away_logo
                        , home_name, away_name, betresult, bet_id, match_id);
                finsihedDatas.add(finsihedData);

            } catch (Exception ex) {

            }

        }
        adapter.notifyDataSetChanged();
    }


}
