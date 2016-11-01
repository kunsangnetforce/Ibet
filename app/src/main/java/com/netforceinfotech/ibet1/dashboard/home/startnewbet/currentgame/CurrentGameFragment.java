package com.netforceinfotech.ibet1.dashboard.home.startnewbet.currentgame;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.WhoWillWinActivity;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.net.ssl.SSLContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentGameFragment extends Fragment implements View.OnClickListener {

    static SSLContext sslContext;
    private RecyclerView recyclerView;
    Context context;
    private LinearLayoutManager layoutManager;
    private CurrentGameAdapter adapter;
    ArrayList<CurrentGameData> currentGameDatas = new ArrayList<>();
    FrameLayout currentgame_layout;
    private UserSessionManager userSessionManager;
    LinearLayout linearLayout;
    Button buttonNext;
    int theme;
    TextView textViewBetamount;
    private EditText editTextPopupBetAmount;
    private String betamountString;
    public static String home_name, away_name, home_logo, away_logo;
    public static String match_id = "";
    public static String home_id, away_id;

    public CurrentGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_game, container, false);
        context = getActivity();
        Ion.getDefault(getActivity()).getConscryptMiddleware().enable(false);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutInput);
        buttonNext = (Button) view.findViewById(R.id.buttonNext);
        textViewBetamount = (TextView) view.findViewById(R.id.textViewBetamount);
        buttonNext.setOnClickListener(this);
        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        setupRecyclerView(view);
        getLiveMatch1();
        return view;
    }


    private void getLiveMatch1() {
        UserSessionManager userSessionManager = new UserSessionManager(context);
        String token = userSessionManager.getApitoken();
        String url = "https://api.soccerama.pro/v1.1/livescore?api_token=" + token + "&include=homeTeam,awayTeam,competition";
        Debugger.i("kunsang_url_liveMatch", url);
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        if (result == null) {
                            showMessage("Something wrong");
                        } else {

                            setUpData(result);
                        }
                    }
                });
    }

    private void setUpData(JsonObject result) {
            linearLayout.setVisibility(View.GONE);
            try {
                Log.i("kunsang_result", result.toString());
                JsonArray data = result.getAsJsonArray("data");
                if (data.size() == 0) {
                    showMessage("No match available");
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        JsonObject jsonObject = data.get(i).getAsJsonObject();
                        String matchid, home_team_id = "", away_team_id = "", home_team_logo = "", away_team_logo = "", home_team_name = "", away_team_name = "", competition_id = "", competition_name = "";
                        JsonObject homeTeam=jsonObject.getAsJsonObject("homeTeam");
                        JsonObject awayTeam=jsonObject.getAsJsonObject("awayTeam");
                        if (!(jsonObject.get("id") == null || jsonObject.get("home_team_id") == null || jsonObject.get("away_team_id") == null)) {
                            matchid = jsonObject.get("id").getAsString();
                            home_team_id = jsonObject.get("home_team_id").getAsString();
                            away_team_id = jsonObject.get("away_team_id").getAsString();
                            try {
                                home_team_name = homeTeam.get("name").getAsString();
                            } catch (Exception ex) {
                                home_team_name = "";
                            }
                            try {
                                away_team_name = awayTeam.get("name").getAsString();
                            } catch (Exception ex) {
                                away_team_name = "";
                            }
                            try {
                                home_team_logo = homeTeam.get("logo").getAsString();
                            } catch (Exception ex) {
                                home_team_logo = "";
                            }
                            try {
                                away_team_logo = awayTeam.get("logo").getAsString();

                            } catch (Exception ex) {
                                away_team_logo = "";
                            }
                            currentGameDatas.add(new CurrentGameData(matchid, home_team_name, away_team_name, home_team_logo, away_team_logo, home_team_id, away_team_id, competition_id, competition_name));
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            } catch (Exception ex) {
                showMessage("json parsing exception");
                ex.printStackTrace();
            }


    }

    private void setupRecyclerView(View view) {

        currentgame_layout = (FrameLayout) view.findViewById(R.id.currentgame_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        if (theme == 0) {
            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme1));

        } else if (theme == 1) {

            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme2));

        } else if (theme == 2) {
            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme3));

        } else if (theme == 3) {
            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme4));

        } else if (theme == 4) {
            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme5));
        }


        recyclerView.setLayoutManager(layoutManager);
        adapter = new CurrentGameAdapter(context, currentGameDatas);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNext:
                Intent intent = new Intent(context, WhoWillWinActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("match_id", match_id);
                bundle.putString("home_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

        }
    }


    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();

    }


}
