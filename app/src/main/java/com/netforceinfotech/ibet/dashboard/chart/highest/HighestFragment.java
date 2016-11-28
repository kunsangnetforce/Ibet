package com.netforceinfotech.ibet.dashboard.chart.highest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.Debugger.Debugger;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//1
public class HighestFragment extends Fragment {


    private UserSessionManager userSessionManager;
    private RecyclerView recyclerView;
    Context context;
    private LinearLayoutManager layoutManager;
    private HighestAdapter adapter;
    FrameLayout highestLayout;
    int theme;


    ArrayList<HighestFragmentData> highestDatas = new ArrayList<HighestFragmentData>();

    public HighestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthighest, container, false);
        context = getActivity();

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        setupRecyclerView(view);
        getHighestRank();
        return view;
    }

    private void getHighestRank() {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=get_rishest_Ranking
        String baseUrl = getString(R.string.url);
        String richestListUrl = "/services.php?opt=get_highest_Ranking";
        String url = baseUrl + richestListUrl;
        Debugger.i("kunsang_url_highestrank", url);

        setupSelfSSLCert();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result == null) {
                            showMessage("Something is wrong");
                        } else {
                            Log.i("kunsangresult", result.toString());
                            if (result.get("status").getAsString().equalsIgnoreCase("success")) {
                                setupRichestListDatas(result);
                            } else {
                                showMessage("Could not set team. Try again");
                            }
                        }
                    }
                });


    }

    private void setupRecyclerView(View view) {
        highestLayout = (FrameLayout) view.findViewById(R.id.highestlayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);


        recyclerView.setLayoutManager(layoutManager);
        adapter = new HighestAdapter(context, highestDatas);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void setupRichestListDatas(JsonObject result) {
        try {
            highestDatas.clear();
        } catch (Exception ex) {

        }
        JsonArray data = result.getAsJsonArray("data");
        for (int i = 0; i < data.size(); i++) {
            JsonObject jsonObject = data.get(i).getAsJsonObject();
            String name = jsonObject.get("name").getAsString();
            String profile_image = jsonObject.get("profile_image").getAsString();
            String total_amt = jsonObject.get("cust_level").getAsString();
            highestDatas.add(new HighestFragmentData(name, profile_image, total_amt));
        }
        adapter.notifyDataSetChanged();

    }
    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
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
