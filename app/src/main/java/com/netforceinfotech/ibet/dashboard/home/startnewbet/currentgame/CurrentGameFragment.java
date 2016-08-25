package com.netforceinfotech.ibet.dashboard.home.startnewbet.currentgame;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
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

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestTickle;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.VolleyTickle;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.detail_bet_to_join.WhoWillWinActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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

    public CurrentGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_game, container, false);
        context = getActivity();
        Ion.getDefault(getActivity()).getConscryptMiddleware().enable(false);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        buttonNext = (Button) view.findViewById(R.id.buttonNext);
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
        String url = "https://api.soccerama.pro/v1.1/livescore?api_token=" + token + "&include=homeTeam,awayTeam";

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
                JsonParser jsonParser = new JsonParser();
                JsonObject result = (JsonObject) jsonParser.parse(response.toString());
                try {
                    Log.i("kunsang_result", result.toString());
                    try {
                        JsonArray data = result.getAsJsonArray("data");
                        if (data.size() == 0) {
                            showMessage("No match available");
                        } else {
                            for (int i = 0; i < data.size(); i++) {
                                JsonObject jsonObject = data.get(i).getAsJsonObject();
                                String matchid, home_team_id = "", away_team_id = "", home_team_logo = "", away_team_logo = "", home_team_name = "", away_team_name = "";
                                if (!(jsonObject.get("id").isJsonNull() || jsonObject.get("home_team_id").isJsonNull() || jsonObject.get("away_team_id").isJsonNull())) {
                                    matchid = jsonObject.get("id").getAsString();
                                    home_team_id = jsonObject.get("home_team_id").getAsString();
                                    away_team_id = jsonObject.get("away_team_id").getAsString();
                                    JsonObject homeTeam, awayTeam;
                                    if (!(jsonObject.get("homeTeam").isJsonNull() || jsonObject.get("awayTeam").isJsonNull())) {
                                        homeTeam = jsonObject.getAsJsonObject("homeTeam");
                                        awayTeam = jsonObject.getAsJsonObject("awayTeam");
                                        home_team_name = homeTeam.get("name").getAsString();
                                        away_team_name = awayTeam.get("name").getAsString();
                                        if (!(homeTeam.get("logo").isJsonNull())) {
                                            home_team_logo = homeTeam.get("logo").getAsString();
                                        }
                                        if (!(awayTeam.get("logo").isJsonNull())) {
                                            away_team_logo = awayTeam.get("logo").getAsString();
                                        }

                                    }
                                    //luug
                                    currentGameDatas.add(new CurrentGameData(matchid, home_team_name, away_team_name, home_team_logo, away_team_logo, home_team_id, away_team_id));
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
                startActivity(intent);
                break;
        }
    }

    private void getLiveMatch() {
        //https://api.soccerama.pro/v1.1/livescore?api_token=__YOURTOKEN__
        //String url = getResources().getString(R.string.url);
        //url = url + "/current_matches.php";
        String token = "DLhRgpl372eKkR1o7WzSDn3SlGntcDVQMTWn9HkrTaRwdFWVhveFfaH7K4QP";
        String url = "https://api.soccerama.pro/v1.1/livescore?api_token=" + token + "&include=homeTeam,awayTeam";

        Log.i("result url", url);
        setHeader();
        setSSLContext();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        linearLayout.setVisibility(View.GONE);
                        if (result == null) {
                            showMessage("Somethings wrong");
                        } else {
                            Log.i("kunsangtest", result.toString());
                          /*  String status = result.get("status").getAsString();
                            if (status.equalsIgnoreCase("success")) {
                                JsonArray data = result.getAsJsonArray("data");
                                for (int i = 0; i < data.size(); i++) {

                                    JsonObject object = data.get(i).getAsJsonObject();
                                    String matchid = object.get("matchid").getAsString();
                                    String teama = null;
                                    if (!object.get("home_teamname").isJsonNull()) {
                                        teama = object.get("home_teamname").getAsString();
                                    }
                                    String teamb = null;
                                    if (!object.get("away_teamname").isJsonNull()) {
                                        teamb = object.get("away_teamname").getAsString();
                                    }
                                    String logohome_team = "";
                                    if (!object.get("home_teamlogo").isJsonNull()) {
                                        logohome_team = object.get("home_teamlogo").getAsString();
                                    }

                                    String awayteam_team = "";
                                    if (!object.get("away_teamlogo").isJsonNull()) {
                                        awayteam_team = object.get("away_teamlogo").getAsString();//dummy
                                    }
                                    String hometeamid = "";
                                    if (!object.get("home_team_id").isJsonNull()) {
                                        hometeamid = object.get("home_team_id").getAsString();//dummy
                                    }
                                    //home_team_id  away_team_id
                                    String away_team_id = "";
                                    if (!object.get("away_team_id").isJsonNull()) {
                                        away_team_id = object.get("away_team_id").getAsString();//dummy
                                    }
                                    if (!(matchid == null || teama == null || teamb == null)) {
                                        currentGameDatas.add(new CurrentGameData(matchid, teama, teamb, logohome_team, awayteam_team, hometeamid, away_team_id));
                                    }
                                }
                                currentGameAdapter.notifyDataSetChanged();
                            } else {
                                showMessage("json error");
                            }*/
                            try {
                                JsonArray data = result.getAsJsonArray("data");
                                if (data.size() == 0) {
                                    showMessage("No match available");
                                } else {
                                    for (int i = 0; i < data.size(); i++) {
                                        JsonObject jsonObject = data.get(i).getAsJsonObject();
                                        String matchid, home_team_id = "", away_team_id = "", home_team_logo = "", away_team_logo = "", home_team_name = "", away_team_name = "";
                                        if (!(jsonObject.get("id").isJsonNull() || jsonObject.get("home_team_id").isJsonNull() || jsonObject.get("away_team_id").isJsonNull())) {
                                            matchid = jsonObject.get("id").getAsString();
                                            home_team_id = jsonObject.get("home_team_id").getAsString();
                                            away_team_id = jsonObject.get("away_team_id").getAsString();
                                            JsonObject homeTeam, awayTeam;
                                            if (!(jsonObject.get("homeTeam").isJsonNull() || jsonObject.get("awayTeam").isJsonNull())) {
                                                homeTeam = jsonObject.getAsJsonObject("homeTeam");
                                                awayTeam = jsonObject.getAsJsonObject("awayTeam");
                                                home_team_name = homeTeam.get("name").getAsString();
                                                away_team_name = awayTeam.get("name").getAsString();
                                                if (!(homeTeam.get("logo").isJsonNull())) {
                                                    home_team_logo = homeTeam.get("logo").getAsString();
                                                }
                                                if (!(awayTeam.get("logo").isJsonNull())) {
                                                    away_team_logo = awayTeam.get("logo").getAsString();
                                                }

                                            }
                                            //luug
                                            currentGameDatas.add(new CurrentGameData(matchid, home_team_name, away_team_name, home_team_logo, away_team_logo, home_team_id, away_team_id));
                                        }

                                    }
                                }
                            } catch (Exception ex) {
                                showMessage("Error occur while fetching data");
                                ex.printStackTrace();
                            }
                        }

                    }
                });
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        try {
            chain[0].checkValidity();
        } catch (Exception e) {
            throw new CertificateException("Certificate not valid or trusted.");
        }
    }
   /**/

    private void setSSLContext() {
        // following is the "trust the system" certs setup
        try {
            // critical extension 2.5.29.15 is implemented improperly prior to 4.0.3.
            // https://code.google.com/p/android/issues/detail?id=9307
            // https://groups.google.com/forum/?fromgroups=#!topic/netty/UCfqPPk5O4s
            // certs that use this extension will throw in Cipher.java.
            // fallback is to use a custom SSLContext, and hack around the x509 extension.
            if (Build.VERSION.SDK_INT <= 15)
                throw new Exception();
            sslContext = SSLContext.getInstance("Default");
        } catch (Exception ex) {
            try {
                sslContext = SSLContext.getInstance("TLS");
                TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }

                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        for (X509Certificate cert : certs) {
                            cert.getCriticalExtensionOIDs().remove("2.5.29.15");
                        }
                    }
                }};
                sslContext.init(null, trustAllCerts, null);
            } catch (Exception ex2) {
                ex.printStackTrace();
                ex2.printStackTrace();
            }
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
}
