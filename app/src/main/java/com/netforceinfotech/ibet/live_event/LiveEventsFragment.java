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
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.profilesetting.selectteam.listofteam.TeamListData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveEventsFragment extends Fragment {

    Context context;
    ArrayList<CurrentGameData> currentGameDatas = new ArrayList<>();

    public LiveEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_events, container, false);
        context = getActivity();
        setupRecyclerView(view);
        getLiveMatch();
        return view;
    }

    private void getLiveMatch() {
        //https://netforcesales.com/ibet_admin/api/current_matches.php?todaydate=2016-08-20
        String url = getResources().getString(R.string.url);
        url = url + "/current_matches.php?todaydate=2016-08-20";
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
                        }
                        else {
                            String status=result.get("status").getAsString();
                            if(status.equalsIgnoreCase("success")){
                                JsonArray data=result.getAsJsonArray("data");
                                for(int i=0;i<data.size();i++){
                                    
                                }
                            }
                        }
                    }
                });
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView(View view) {
        CurrentGameAdapter currentGameAdapter = new CurrentGameAdapter(context, null);
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
}
