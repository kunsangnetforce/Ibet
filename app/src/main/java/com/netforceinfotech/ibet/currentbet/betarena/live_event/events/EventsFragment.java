package com.netforceinfotech.ibet.currentbet.betarena.live_event.events;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.live_event.CurrentGameData;
import com.netforceinfotech.ibet.live_event.LiveEventActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    private Context context;
    ArrayList<EventsData> eventsDatas = new ArrayList<>();
    private EventsAdapter adapter;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        context = getActivity();
        setupData();
        getEvents(LiveEventActivity.matchid, LiveEventActivity.teamaid, LiveEventActivity.teambid);
        setupRecycler(view);
        return view;
    }

    private void setupData() {
        eventsDatas.add(new EventsData("neymar", "", "goal", "76"));
        eventsDatas.add(new EventsData("", "bale", "goal", "55"));
        eventsDatas.add(new EventsData("masherano", "", "yellow", "34"));
        eventsDatas.add(new EventsData("Pique", "", "red", "12"));
        eventsDatas.add(new EventsData("", "Ronaldo", "goal", "6"));
        eventsDatas.add(new EventsData("", "", "", "0"));

    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapter = new EventsAdapter(context, eventsDatas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getEvents(String matchid, String teamaid, String teambid) {
        //https://netforcesales.com/ibet_admin/api/events_by_match_id.php?matchid=614704&home_team_id=1370&away_team_id=1377
        String url = getResources().getString(R.string.url);
        url = url + "/events_by_match_id.php?matchid=" + matchid + "&home_team_id=" + teamaid + "&away_team_id=" + teambid;
        Log.i("result url", url);
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result == null) {
                            showMessage("Somethings wrong");
                        } else {
                            String status = result.get("status").getAsString();
                            if (status.equalsIgnoreCase("success")) {
                                JsonObject dataObject = result.getAsJsonObject("data");
                                JsonObject teamObject = dataObject.getAsJsonObject("team");
                                String teama = teamObject.get("hm_teamname").getAsString();
                                String teamb = teamObject.get("aw_teamname").getAsString();
                                String teamalogo = teamObject.get("hm_teamlogo").getAsString();
                                String teamblogo = teamObject.get("aw_teamlogo").getAsString();
                                JsonArray data_event=dataObject.get("data_event").getAsJsonArray();
                                for(int i=0;i<data_event.size();i++){
                                    
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                showMessage("json error");
                            }
                        }

                    }
                });
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
