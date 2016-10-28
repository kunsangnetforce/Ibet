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
import com.bumptech.glide.Glide;
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
    LinearLayout linearLayoutProgress;
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
    boolean clickedLivematch = true;
    private String stringDate = "";

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
        getLiveMatch();
        linearLayoutProgress.setVisibility(View.VISIBLE);
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
                //setupDefaultTheme();
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
        linearLayoutProgress = (LinearLayout) view.findViewById(R.id.linearLayoutProgress);
        linearLayoutNoMatch = (LinearLayout) view.findViewById(R.id.linearLayoutNoLiveMatches);
        imageViewNoMatch = (ImageView) view.findViewById(R.id.imageViewNoLiveMatch);
        Glide.with(context).load(R.drawable.gs_stadium).into(imageViewNoMatch);
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
                if (clickedLivematch) {
                    getLiveMatch();
                } else {
                    getMatchBydate(stringDate);
                }
            }
        });
        setupExpandableView(view);
        buttonLive.setOnClickListener(this);
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
        listAdapter = new ExpandableListAdapter(context, expandHeaderDatas, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void getLiveMatch() {
        //https://api.soccerama.pro/v1.2/livescore/now?api_token=__YOURTOKEN__
        String url = "https://api.soccerama.pro/v1.2/livescore/now?api_token=" + userSessionManager.getApitoken() + "&include=homeTeam,awayTeam,competition";
        Log.i("kunsangurl", url);
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        linearLayoutProgress.setVisibility(View.GONE);
                        if (result == null) {
                            showMessage("null result");
                        } else {
                            setupData(result);
                            Log.i("kunsangresult", result.toString());
                        }


                    }
                });
    }

    private void setupData(JsonObject result) {
        try {
            expandHeaderDatas.clear();
            listDataChild.clear();
            currentGameDatas.clear();
            listAdapter.notifyDataSetChanged();
            currentGameDatas.clear();
            currentGameAdapter.notifyDataSetChanged();
        } catch (Exception ex) {

        }
        mSwipyRefreshLayout.setRefreshing(false);
        mSwipyRefreshLayout.setVisibility(View.VISIBLE);
        linearLayoutProgress.setVisibility(View.GONE);
        try {
            JsonArray data = result.getAsJsonArray("data");
            if (data.size() == 0) {
                linearLayoutNoMatch.setVisibility(View.VISIBLE);
                showMessage("No match available");
            } else {
                linearLayoutNoMatch.setVisibility(View.GONE);
                for (int i = 0; i < data.size(); i++) {
                    JsonObject jsonObject = data.get(i).getAsJsonObject();
                    String matchid, home_team_id = "", away_team_id = "", home_team_logo = "", away_team_logo = "", home_team_name = "", away_team_name = "", competition_id = "", competition_name = "", season_id = "";
                    if (!(jsonObject.get("id").isJsonNull() || jsonObject.get("home_team_id").isJsonNull() || jsonObject.get("away_team_id").isJsonNull())) {
                        matchid = jsonObject.get("id").getAsString();
                        home_team_id = jsonObject.get("home_team_id").getAsString();
                        away_team_id = jsonObject.get("away_team_id").getAsString();
                        season_id = jsonObject.get("season_id").getAsString();
                        JsonObject homeTeam, awayTeam, competition;
                        if (!(jsonObject.get("homeTeam").isJsonNull() || jsonObject.get("awayTeam").isJsonNull())) {
                            homeTeam = jsonObject.getAsJsonObject("homeTeam");
                            awayTeam = jsonObject.getAsJsonObject("awayTeam");
                            competition = jsonObject.getAsJsonObject("competition");
                            home_team_name = homeTeam.get("name").getAsString();
                            away_team_name = awayTeam.get("name").getAsString();
                            competition_id = competition.get("id").getAsString();
                            competition_name = competition.get("name").getAsString();
                            if (!(homeTeam.get("logo").isJsonNull())) {
                                home_team_logo = homeTeam.get("logo").getAsString();
                            }
                            if (!(awayTeam.get("logo").isJsonNull())) {
                                away_team_logo = awayTeam.get("logo").getAsString();
                            }

                        }
                        //luug
                        currentGameDatas.add(new CurrentGameData(matchid, home_team_name, away_team_name, home_team_logo, away_team_logo, home_team_id, away_team_id, competition_id, competition_name, season_id));
                    }

                }
                setupExpandableData(currentGameDatas);

                //    currentGameAdapter.notifyDataSetChanged();

            }
        } catch (Exception ex) {
            showMessage("Error occur while fetching data");
            ex.printStackTrace();
        }

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
        linearLayoutProgress.setVisibility(View.VISIBLE);
        clickedLivematch = false;
        stringDate = year + "-" + formattedMonth + "-" + dayOfMonth;
        getMatchBydate(stringDate);
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
        Log.i("kunsangresult", url);
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        linearLayoutProgress.setVisibility(View.GONE);
                        if (result == null) {
                            showMessage("null result");
                        } else {
                            setupData(result);
                            showMessage("got result");
                            Log.i("kunsangresult", result.toString());
                        }


                    }
                });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttondate:

                Calendar now = Calendar.getInstance();
                DatePickerDialog.newInstance(this, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).show(getActivity().getFragmentManager(), "datePicker");

            case R.id.buttonLive:
                getLiveMatch();
                break;

        }
    }
}
