package com.netforceinfotech.ibet1.currentbet.betarena.stats.table;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class TableFragment extends Fragment {


    private RecyclerView recyclerView;
    private Context context;
    UserSessionManager userSessionManager;
    ArrayList<TableData> tableDatas = new ArrayList<>();
    private TableAdapter adapter;
    LinearLayout linearLayoutError;
    ImageView imageViewError;

    public TableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        linearLayoutError = (LinearLayout) view.findViewById(R.id.linearLayoutError);
        imageViewError = (ImageView) view.findViewById(R.id.imageViewError);
        Glide.with(context).load(R.drawable.gs_stadium).into(imageViewError);
        linearLayoutError.setVisibility(View.GONE);
        String seasonId = "", home_id = null, away_id = null;
        try {
            seasonId = this.getArguments().getString("season_id");
            home_id = this.getArguments().getString("home_id");
            away_id = this.getArguments().getString("away_id");
        } catch (Exception ex) {
            Log.i("kunsang_exception", "paramenter not set");
            showMessage("bundl error table");
        }
        setupRecyclerView(view, home_id, away_id);
        getTable(seasonId);
        return view;
    }

    private void getTable(String seasonId) {
//https://api.soccerama.pro/v1.2/standings/season/{id}?api_token=__YOURTOKEN__
        String url = "https://api.soccerama.pro/v1.2/standings/season/" + seasonId + "?api_token=" + userSessionManager.getApitoken();
        setupSelfSSLCert();
        Debugger.i("kunsang_url_table", url);

        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result == null) {
                            showMessage("Something is wrong");
                        } else {
                            setTableData(result);
                        }
                    }
                });
    }

    private void setTableData(JsonObject result) {
        JsonArray dataOuter = result.getAsJsonArray("data");
        if (dataOuter.size() == 0) {
            linearLayoutError.setVisibility(View.VISIBLE);
            return;
        }
        Log.i("kunsangresult", result.toString());
        JsonObject jsonObjectOuter = dataOuter.get(0).getAsJsonObject();
        JsonObject standings = jsonObjectOuter.get("standings").getAsJsonObject();
        JsonArray dataInner = standings.get("data").getAsJsonArray();
        int size = dataInner.size();
        for (int i = 0; i < size; i++) {
            JsonObject jsonObject = dataInner.get(i).getAsJsonObject();
            int position = Integer.parseInt(jsonObject.get("position").getAsString());
            String points = jsonObject.get("points").getAsString();
            String goalDiff = jsonObject.get("goal_difference").getAsString();
            String wins = jsonObject.get("overall_win").getAsString();
            String overall_draw = jsonObject.get("overall_draw").getAsString();
            String overall_loose = jsonObject.get("overall_loose").getAsString();
            String overall_played = jsonObject.get("overall_played").getAsString();
            JsonObject team = jsonObject.getAsJsonObject("team");
            String name = team.get("name").getAsString();
            String logo = team.get("logo").getAsString();
            String id = team.get("id").getAsString();

            TableData data = new TableData(name, logo, id, points, goalDiff, wins, overall_draw, overall_loose, overall_played, position);
            if (!tableDatas.contains(data)) {
                tableDatas.add(data);
            }

        }
        Collections.sort(tableDatas);
        adapter.notifyDataSetChanged();
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setupRecyclerView(View view, String home_id, String away_id) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TableAdapter(context, tableDatas, home_id, away_id);
        recyclerView.setAdapter(adapter);

    }

    public void setupSelfSSLCert() {
        final Trust trust = new Trust();
        final TrustManager[] trustmanagers = new TrustManager[]{trust};
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustmanagers, new SecureRandom());
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setTrustManagers(trustmanagers);
            Ion.getInstance(context, "rest").getHttpClient().getSSLSocketMiddleware().setSSLContext(sslContext);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (final KeyManagementException e) {
            e.printStackTrace();
        }
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
