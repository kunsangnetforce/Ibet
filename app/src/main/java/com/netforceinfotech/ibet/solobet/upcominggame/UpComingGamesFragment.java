package com.netforceinfotech.ibet.solobet.upcominggame;


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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.solobet.recycler.SoloBetActivity;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.net.ssl.X509TrustManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingGamesFragment extends Fragment implements View.OnClickListener {


    private UserSessionManager userSessionManager;
    int theme;
    FrameLayout upcomming_games_layout;
    ArrayList<UpcomingGameData> upcomingGameDatas = new ArrayList<>();
    Button buttonNext;
    private UpcomingGameAdapter upcomingGameAdapter;
    Context context;
    LinearLayout linearLayout;
    public static String home_name, away_name, home_logo, away_logo;
    public static String match_id = "";
    public static String home_id, away_id;

    public UpComingGamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming_games, container, false);
        context = getActivity();
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutInput);
        buttonNext = (Button) view.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);
        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        setupRecycler(view);
        String date1 = getDate(0);
        String date2 = getDate(4);
        if (!userSessionManager.getLoginMode().equalsIgnoreCase("0")) {
            getLiveMatch1(date1, date2);
        }
        return view;
    }

    private String getDate(int i) {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        c.add(Calendar.DATE, i);
        String formattedDate = df.format(c.getTime());
        return formattedDate;

    }


    private void setupRecycler(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        upcomming_games_layout = (FrameLayout) view.findViewById(R.id.upcomming_games_layout);

        if (theme == 0) {
            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme1));

        } else if (theme == 1) {

            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme2));

        } else if (theme == 2) {
            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme3));

        } else if (theme == 3) {
            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme4));

        } else if (theme == 4) {
            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme5));
        }


        upcomingGameAdapter = new UpcomingGameAdapter(getActivity(), upcomingGameDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(upcomingGameAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNext:
                Intent intent = new Intent(getActivity(), SoloBetActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("match_id", match_id);
                bundle.putString("home_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                Log.i("kunsangparamenter", home_id + "" + away_id);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    private void getLiveMatch1(String date1, String date2) {
        UserSessionManager userSessionManager = new UserSessionManager(context);
        String token = userSessionManager.getApitoken();
        String url = "https://api.soccerama.pro/v1.1/matches/" + date1 + "/" + date2 + "?api_token=" + token + "&include=homeTeam,awayTeam,competition";
        Log.i("kunsang_url", url);
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        if(isAdded()){
                            if (result == null) {

                                showMessage(getString(R.string.something_went_wrong));
                            } else {
                                setUpData(result);
                            }
                        }

                    }
                });
    }

    private void setUpData(JsonObject result) {
        linearLayout.setVisibility(View.GONE);
        try {
            JsonArray data = result.getAsJsonArray("data");
            if (data.size() == 0) {
                showMessage("No match available");
            } else {
                for (int i = 0; i < data.size(); i++) {
                    JsonObject jsonObject = data.get(i).getAsJsonObject();
                    String matchid, home_team_id = "", away_team_id = "", home_team_logo = "", away_team_logo = "", home_team_name = "", away_team_name = "", competition_id = "", competition_name = "";
                    JsonObject homeTeam = jsonObject.getAsJsonObject("homeTeam");
                    JsonObject awayTeam = jsonObject.getAsJsonObject("awayTeam");
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
                        upcomingGameDatas.add(new UpcomingGameData(matchid, home_team_name, away_team_name, home_team_logo, away_team_logo, home_team_id, away_team_id, competition_id, competition_name));
                    }
                }
                upcomingGameAdapter.notifyDataSetChanged();
            }
        } catch (Exception ex) {
            showMessage("json parsing exception");
            ex.printStackTrace();
        }


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

    private static class Trust implements X509TrustManager {

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

    }
}
