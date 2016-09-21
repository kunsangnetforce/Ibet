package com.netforceinfotech.ibet.dashboard.home.startnewbet.currentgame;


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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.WhoWillWinActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;

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
    public static String home_id,away_id;
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

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                createMyReqSuccessListener(),
                createMyReqErrorListener());

        queue.add(myReq);
    }

    private Response.Listener<JSONObject> createMyReqSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                linearLayout.setVisibility(View.GONE);
                try {
                    Log.i("kunsang_result", response.toString());
                    try {
                        JSONArray data = response.getJSONArray("data");
                        if (data.length() == 0) {
                            showMessage("No match available");
                        } else {
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject jsonObject = data.getJSONObject(i);
                                String matchid, home_team_id = "", away_team_id = "", home_team_logo = "", away_team_logo = "", home_team_name = "", away_team_name = "", competition_id = "", competition_name = "";
                                if (!(jsonObject.get("id") == null || jsonObject.get("home_team_id") == null || jsonObject.get("away_team_id") == null)) {
                                    matchid = jsonObject.getString("id");
                                    home_team_id = jsonObject.getString("home_team_id");
                                    away_team_id = jsonObject.getString("away_team_id");
                                    JSONObject homeTeam, awayTeam, competition;
                                    if (!(jsonObject.get("homeTeam") == null || jsonObject.get("awayTeam") == null)) {
                                        homeTeam = jsonObject.getJSONObject("homeTeam");
                                        awayTeam = jsonObject.getJSONObject("awayTeam");
                                        competition = jsonObject.getJSONObject("competition");
                                        home_team_name = homeTeam.getString("name");
                                        away_team_name = awayTeam.getString("name");
                                        competition_id = competition.getString("id");
                                        competition_name = competition.getString("name");

                                        if (!(homeTeam.get("logo") == null)) {
                                            home_team_logo = homeTeam.getString("logo");
                                        }
                                        if (!(awayTeam.get("logo") == null)) {
                                            away_team_logo = awayTeam.getString("logo");
                                        }

                                    }
                                    //luug
                                    currentGameDatas.add(new CurrentGameData(matchid, home_team_name, away_team_name, home_team_logo, away_team_logo, home_team_id, away_team_id, competition_id, competition_name));
                                }

                            }


                            adapter.notifyDataSetChanged();

                        }
                    } catch (Exception ex) {
                        showMessage("Error occur while fetching data");
                        ex.printStackTrace();
                    }

                } catch (Exception e) {

                }
            }
        };
    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                linearLayout.setVisibility(View.GONE);
                error.printStackTrace();
            }
        };
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
                bundle.putString("away_id", home_id);
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
