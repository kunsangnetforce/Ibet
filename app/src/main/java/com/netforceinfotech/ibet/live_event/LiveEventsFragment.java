package com.netforceinfotech.ibet.live_event;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.profilesetting.selectteam.listofteam.TeamListData;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveEventsFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Context context;
    ArrayList<CurrentGameData> currentGameDatas = new ArrayList<>();
    private CurrentGameAdapter currentGameAdapter;
    LinearLayout linearLayout;
    private SwipyRefreshLayout mSwipyRefreshLayout;
    Button buttonDate;

    public LiveEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_events, container, false);
        context = getActivity();
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        buttonDate = (Button) view.findViewById(R.id.buttondate);
        buttonDate.setOnClickListener(this);
        mSwipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                getLiveMatch1();
            }
        });
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
                mSwipyRefreshLayout.setRefreshing(false);
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
                            currentGameAdapter.notifyDataSetChanged();

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
                mSwipyRefreshLayout.setRefreshing(false);
                linearLayout.setVisibility(View.GONE);
                error.printStackTrace();
            }
        };
    }

    private void getLiveMatch() {
        //https://api.soccerama.pro/v1.1/livescore?api_token=__YOURTOKEN__
        //String url = getResources().getString(R.string.url);
        //url = url + "/current_matches.php";
        String token = "DLhRgpl372eKkR1o7WzSDn3SlGntcDVQMTWn9HkrTaRwdFWVhveFfaH7K4QP";
        String url = "https://api.soccerama.pro/v1.1/livescore?api_token=" + token + "&include=homeTeam,awayTeam";

        Log.i("result url", url);
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        mSwipyRefreshLayout.setRefreshing(false);
                        linearLayout.setVisibility(View.GONE);
                        if (result == null) {
                            showMessage("Somethings wrong");
                        } else {
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

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView(View view) {
        currentGameAdapter = new CurrentGameAdapter(context, currentGameDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(currentGameAdapter);
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

    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String secondString = second < 10 ? "0" + second : "" + second;
        String time = "You picked the following time: " + hourString + "h" + minuteString + "m" + secondString + "s";
        buttonDate.setText(time);
    }


    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
        Date date2 = new Date();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date2 = date_format.parse(year + "-" + monthOfYear + "-" + dayOfMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("date======" + date2.toString());

        String day_txt = date2.toString().substring(0, 3);

        String month_txt = date2.toString().substring(4, 7);
        String formattedMonth = "";
        if (monthOfYear <= 9) {
            formattedMonth = "0" + monthOfYear;
        } else {
            formattedMonth = "" + monthOfYear;
        }
        buttonDate.setText(day_txt + " " + dayOfMonth + " " + month_txt);
        getMatchBydate(year + "-" + formattedMonth + "-" + dayOfMonth);
        Log.i("kunsang_date", year + "-" + monthOfYear + "-" + dayOfMonth);

    }

    private void getMatchBydate(String date) {
        try{
            currentGameDatas.clear();
            currentGameAdapter.notifyDataSetChanged();
        }catch (Exception ex){

        }
        UserSessionManager userSessionManager = new UserSessionManager(context);
        //https://api.soccerama.pro/v1.1/livescore/date/{date}?api_token=__YOURTOKEN__
        String token = userSessionManager.getApitoken();
        //String url = "https://api.soccerama.pro/v1.1/livescore?api_token=" + token + "&include=homeTeam,awayTeam";
        String url = "https://api.soccerama.pro/v1.1/livescore/date/" + date + "?api_token=" + token + "&include=homeTeam,awayTeam";
        Log.i("result_kunsang", url);
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                createMyReqSuccessListener(),
                createMyReqErrorListener());

        queue.add(myReq);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttondate:

                Calendar now = Calendar.getInstance();
                DatePickerDialog.newInstance(this, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).show(getActivity().getFragmentManager(), "datePicker");


                break;

        }
    }
}
