package com.netforceinfotech.ibet1.live_event_main;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.ExpandHeaderData;
import com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.ExpandableListAdapter;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    private ExpandableListAdapter listAdapter;
    ArrayList<ExpandHeaderData> expandHeaderDatas = new ArrayList<>();
    HashMap<String, List<CurrentGameData>> listDataChild = new HashMap<String, List<CurrentGameData>>();
    LinearLayout linearLayoutNoMatch;
    private ExpandableListView expListView;
    ImageView imageViewNoMatch;
    UserSessionManager userSessionManager;
    private Button buttonLive;
    CoordinatorLayout coordinatorLayout;
    TextView textViewNoData;
    View view1;

    public LiveEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_events, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(getActivity());
        initView(view);
        setupTheme();
        setupBackground();
        // getLiveMatch1();
        return view;
    }

    private void setupBackground() {
        int background = userSessionManager.getBackground();
        switch (background) {
            case 0:
                coordinatorLayout.setBackgroundResource(R.drawable.blue240);
                break;
            case 1:
                coordinatorLayout.setBackgroundResource(R.drawable.france240);
                break;
            case 2:
                coordinatorLayout.setBackgroundResource(R.drawable.soccer240);
                break;
            case 3:
                coordinatorLayout.setBackgroundResource(R.drawable.spain240);
                break;
            case 4:
                coordinatorLayout.setBackgroundResource(R.drawable.uk240);
                break;
            case 5:
                view1.setVisibility(View.GONE);
                break;
        }
    }

    private void setupTheme() {
        switch (userSessionManager.getTheme()) {
            case 0:
                setupDefaultTheme();
                break;
            case 1:
                setupBrownTheme();
                break;
            case 2:
                setupPurlpleTheme();
                break;
            case 3:
                setupGreenTheme();
                break;
            case 4:
                setupMarronTheme();
                break;
            case 5:
                setupLightBlueTheme();
                break;
        }

    }

    private void setupPurlpleTheme() {
        buttonDate.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        buttonLive.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        textViewNoData.setTextColor(ContextCompat.getColor(context, R.color.colorAccentPurple));

    }

    private void setupGreenTheme() {
        buttonDate.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        buttonLive.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        textViewNoData.setTextColor(ContextCompat.getColor(context, R.color.colorAccentGreen));

    }

    private void setupMarronTheme() {
        buttonDate.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        buttonLive.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        buttonDate.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        buttonLive.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        textViewNoData.setTextColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
    }

    private void setupBrownTheme() {
        buttonDate.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        buttonLive.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        textViewNoData.setTextColor(ContextCompat.getColor(context, R.color.colorAccentBrown));

    }

    private void setupDefaultTheme() {
        buttonDate.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        buttonLive.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        textViewNoData.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    private void initView(View view) {
        view1 = view.findViewById(R.id.view);
        textViewNoData = (TextView) view.findViewById(R.id.textViewNoData);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutProgress);
        linearLayoutNoMatch = (LinearLayout) view.findViewById(R.id.linearLayoutNoLiveMatches);
        imageViewNoMatch = (ImageView) view.findViewById(R.id.imageViewNoLiveMatch);
        Picasso.with(context).load(R.drawable.gs_stadium).into(imageViewNoMatch);
        buttonDate = (Button) view.findViewById(R.id.buttondate);
        buttonDate.setOnClickListener(this);
        buttonLive = (Button) view.findViewById(R.id.buttonLive);
        mSwipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                currentGameDatas.clear();
                expandHeaderDatas.clear();
                listDataChild.clear();
                buttonDate.setText("Select Date");
                getLiveMatch1();
            }
        });
        setupExpandableView(view);
        buttonLive.setOnClickListener(this);
    }

    private void getLiveMatch1() {
        try {
            expandHeaderDatas.clear();
            listDataChild.clear();
            currentGameDatas.clear();
            listAdapter.notifyDataSetChanged();
            //  currentGameAdapter.notifyDataSetChanged();
        } catch (Exception ex) {

        }
        linearLayout.setVisibility(View.VISIBLE);
        mSwipyRefreshLayout.setVisibility(View.GONE);
        UserSessionManager userSessionManager = new UserSessionManager(context);
        String token = userSessionManager.getApitoken();
        String url = "https://api.soccerama.pro/v1.1/livescore/now?api_token=" + token + "&include=homeTeam,awayTeam,competition";

        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("result_url", url);
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
                mSwipyRefreshLayout.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);

                try {
                    Log.i("kunsang_result", response.toString());
                    try {
                        JSONArray data = response.getJSONArray("data");
                        if (data.length() == 0) {
                            linearLayoutNoMatch.setVisibility(View.VISIBLE);
                            showMessage("No match available");
                        } else {
                            linearLayoutNoMatch.setVisibility(View.GONE);
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
                            setupExpandableData(currentGameDatas);

                            //    currentGameAdapter.notifyDataSetChanged();

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

    private void setupExpandableData(ArrayList<CurrentGameData> currentGameDatas) {
        for (CurrentGameData currentGameData : currentGameDatas) {
            String key = currentGameData.compition_id;
            if (!listDataChild.containsKey(key)) {
                List<CurrentGameData> list = new ArrayList<>();
                list.add(currentGameData);
                expandHeaderDatas.add(new ExpandHeaderData(currentGameData.compition_id, currentGameData.competition_name));
                listDataChild.put(currentGameData.compition_id, list);
            } else {
                listDataChild.get(currentGameData.compition_id).add(currentGameData);
            }
        }
        for (String id : listDataChild.keySet()) {
            String value = listDataChild.get(id).size() + "";
            Log.i("kunsang_count", id + " : " + value);


        }
        listAdapter.notifyDataSetChanged();

    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showMessage("Check internet connection");
                mSwipyRefreshLayout.setRefreshing(false);
                mSwipyRefreshLayout.setVisibility(View.VISIBLE);
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
        String url = "https://api.soccerama.pro/v1.1/livescore/url?api_token=" + token + "&include=homeTeam,awayTeam";

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
                                    String match_id = object.get("match_id").getAsString();
                                    String home_name = null;
                                    if (!object.get("home_teamname").isJsonNull()) {
                                        home_name = object.get("home_teamname").getAsString();
                                    }
                                    String away_name = null;
                                    if (!object.get("away_teamname").isJsonNull()) {
                                        away_name = object.get("away_teamname").getAsString();
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
                                    if (!(match_id == null || home_name == null || away_name == null)) {
                                        currentGameDatas.add(new CurrentGameData(match_id, home_name, away_name, logohome_team, awayteam_team, hometeamid, away_team_id));
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
                                        String matchid, home_team_id = "", away_team_id = "", home_team_logo = "", away_team_logo = "", home_team_name = "", away_team_name = "", competition_id = "", competition_name = "";
                                        if (!(jsonObject.get("id").isJsonNull() || jsonObject.get("home_team_id").isJsonNull() || jsonObject.get("away_team_id").isJsonNull())) {
                                            matchid = jsonObject.get("id").getAsString();
                                            home_team_id = jsonObject.get("home_team_id").getAsString();
                                            away_team_id = jsonObject.get("away_team_id").getAsString();
                                            JsonObject homeTeam, awayTeam, competition;
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
                                            currentGameDatas.add(new CurrentGameData(matchid, home_team_name, away_team_name, home_team_logo, away_team_logo, home_team_id, away_team_id, "", ""));
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

    private void setupExpandableView(View view) {
       /* currentGameAdapter = new CurrentGameAdapter(context, currentGameDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(currentGameAdapter);*/
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        // preparing list data
        getLiveMatch1();
        listAdapter = new ExpandableListAdapter(context, expandHeaderDatas, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
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
        try {
            expandHeaderDatas.clear();
            listDataChild.clear();
            currentGameDatas.clear();
            listAdapter.notifyDataSetChanged();
            //  currentGameAdapter.notifyDataSetChanged();
        } catch (Exception ex) {

        }
        UserSessionManager userSessionManager = new UserSessionManager(context);
        //https://api.soccerama.pro/v1.1/livescore/date/{date}?api_token=__YOURTOKEN__
        String token = userSessionManager.getApitoken();
        //String url = "https://api.soccerama.pro/v1.1/livescore?api_token=" + token + "&include=homeTeam,awayTeam";
        String url = "https://api.soccerama.pro/v1.1/livescore/date/" + date + "?api_token=" + token + "&include=homeTeam,awayTeam,competition";
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

            case R.id.buttonLive:
                getLiveMatch1();
                break;

        }
    }
}
