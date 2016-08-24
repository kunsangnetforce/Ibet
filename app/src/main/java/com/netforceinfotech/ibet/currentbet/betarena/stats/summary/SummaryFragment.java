package com.netforceinfotech.ibet.currentbet.betarena.stats.summary;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {
    Context context;
    TextView textViewTSH, textViewSoTH, textViewFoulH, textViewOSH, textViewCornorH, textViewPossesionH, textViewYCH, textViewRCH, textViewSaveH;
    TextView textViewTSA, textViewSoTA, textViewFoulA, textViewOSA, textViewCornorA, textViewPossesionA, textViewYCA, textViewRCA, textViewSaveA;
    RoundCornerProgressBar progressbarTSH, progressbarSoTH, progressbarFoulH, progressbarOSH, progressbarCornorH, progressbarPossesionH, progressbarYCH, progressbarRCH, progressbarSaveH;
    RoundCornerProgressBar progressbarTSA, progressbarSoTA, progressbarFoulA, progressbarOSA, progressbarCornorA, progressbarPossesionA, progressbarYCA, progressbarRCA, progressbarSaveA;
    LinearLayout linearLayoutStatistic, linearLayoutError;
    TextView textViewError;

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        context = getActivity();
        initview(view);
        String matchid = this.getArguments().getString("matchid");
        getStatistic(matchid);
        return view;
    }

    private void initview(View view) {
        linearLayoutError = (LinearLayout) view.findViewById(R.id.linearLayoutError);
        linearLayoutStatistic = (LinearLayout) view.findViewById(R.id.linearLayoutStatistic);
        textViewError = (TextView) view.findViewById(R.id.textViewError);
        initHome(view);
        initAway(view);
    }

    private void initAway(View view) {
        textViewTSA = (TextView) view.findViewById(R.id.textViewTSAway);
        textViewSoTA = (TextView) view.findViewById(R.id.textViewSoTAway);
        textViewFoulA = (TextView) view.findViewById(R.id.textViewFoulsAway);
        textViewOSA = (TextView) view.findViewById(R.id.textViewOSAway);
        textViewCornorA = (TextView) view.findViewById(R.id.textViewCornorsAway);
        textViewPossesionA = (TextView) view.findViewById(R.id.textViewPossesionAway);
        textViewYCA = (TextView) view.findViewById(R.id.textViewYellowcardAway);
        textViewRCA = (TextView) view.findViewById(R.id.textViewRedcardAway);
        textViewSaveA = (TextView) view.findViewById(R.id.textViewSavesAway);

        progressbarTSA = (RoundCornerProgressBar) view.findViewById(R.id.progressTSAway);
        progressbarSoTA = (RoundCornerProgressBar) view.findViewById(R.id.progressSoTAway);
        progressbarFoulA = (RoundCornerProgressBar) view.findViewById(R.id.progressFoulsAway);
        progressbarOSA = (RoundCornerProgressBar) view.findViewById(R.id.progressOSAway);
        progressbarCornorA = (RoundCornerProgressBar) view.findViewById(R.id.progressCornorsAway);
        progressbarPossesionA = (RoundCornerProgressBar) view.findViewById(R.id.progressPossesionAway);
        progressbarYCA = (RoundCornerProgressBar) view.findViewById(R.id.progressYellowcardAway);
        progressbarRCA = (RoundCornerProgressBar) view.findViewById(R.id.progressRedcardAway);
        progressbarSaveA = (RoundCornerProgressBar) view.findViewById(R.id.progressSavesAway);
    }

    private void initHome(View view) {
        //textViewTSH, textViewSoTH, textViewFoulH, textViewOSH, textViewCornorH, textViewPossesionH, textViewYCH, textViewRCH, textViewSaveH;
        textViewTSH = (TextView) view.findViewById(R.id.textViewTSHome);
        textViewSoTH = (TextView) view.findViewById(R.id.textViewSoTHome);
        textViewFoulH = (TextView) view.findViewById(R.id.textViewFoulsHome);
        textViewOSH = (TextView) view.findViewById(R.id.textViewOSHome);
        textViewCornorH = (TextView) view.findViewById(R.id.textViewCornorsHome);
        textViewPossesionH = (TextView) view.findViewById(R.id.textViewPossesionHome);
        textViewYCH = (TextView) view.findViewById(R.id.textViewYellowcardHome);
        textViewRCH = (TextView) view.findViewById(R.id.textViewRedcardHome);
        textViewSaveH = (TextView) view.findViewById(R.id.textViewSavesHome);

        progressbarTSH = (RoundCornerProgressBar) view.findViewById(R.id.progressTSHome);
        progressbarSoTH = (RoundCornerProgressBar) view.findViewById(R.id.progressSoTHome);
        progressbarFoulH = (RoundCornerProgressBar) view.findViewById(R.id.progressFoulsHome);
        progressbarOSH = (RoundCornerProgressBar) view.findViewById(R.id.progressOSHome);
        progressbarCornorH = (RoundCornerProgressBar) view.findViewById(R.id.progressCornorsHome);
        progressbarPossesionH = (RoundCornerProgressBar) view.findViewById(R.id.progressPossesionHome);
        progressbarYCH = (RoundCornerProgressBar) view.findViewById(R.id.progressYellowcardHome);
        progressbarRCH = (RoundCornerProgressBar) view.findViewById(R.id.progressRedcardHome);
        progressbarSaveH = (RoundCornerProgressBar) view.findViewById(R.id.progressSavesHome);
    }

    private void getStatistic(String matchid) {
        //https://api.soccerama.pro/v1.1/statistics/match/690006?api_token=DLhRgpl372eKkR1o7WzSDn3SlGntcDVQMTWn9HkrTaRwdFWVhveFfaH7K4QP
        String token = "DLhRgpl372eKkR1o7WzSDn3SlGntcDVQMTWn9HkrTaRwdFWVhveFfaH7K4QP";
        String url = "https://api.soccerama.pro/v1.1/statistics/match/" + matchid + "?api_token=" + token;
        Log.i("result url", url);
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result == null) {
                            linearLayoutError.setVisibility(View.VISIBLE);
                            linearLayoutStatistic.setVisibility(View.GONE);
                            textViewError.setText("Connection error");
                        } else {
                            if (result.get("error").isJsonNull()) {
                                linearLayoutError.setVisibility(View.GONE);
                                linearLayoutStatistic.setVisibility(View.VISIBLE);
                                JsonObject home = result.getAsJsonObject("home");
                                JsonObject away = result.getAsJsonObject("away");

                                String shots_on_goalh = home.get("shots_on_goal").getAsString();
                                String shots_totalh = home.get("shots_total").getAsString();
                                String fouls_totalh = home.get("fouls_total").getAsString();
                                String corners_totalh = home.get("corners_total").getAsString();
                                String offsides_totalh = home.get("offsides_total").getAsString();
                                String possesionh = home.get("possesion").getAsString();
                                String yellowcardsh = home.get("yellowcards").getAsString();
                                String redcardsh = home.get("redcards").getAsString();
                                String savesh = home.get("saves").getAsString();

                                textViewTSH.setText(shots_totalh);
                                textViewSoTH.setText(shots_on_goalh);
                                textViewFoulH.setText(fouls_totalh);
                                textViewOSH.setText(offsides_totalh);
                                textViewCornorH.setText(corners_totalh);
                                textViewPossesionH.setText(possesionh);
                                textViewYCH.setText(yellowcardsh);
                                textViewRCH.setText(redcardsh);
                                textViewSaveH.setText(savesh);

                                progressbarTSH.setProgress(Float.parseFloat(shots_totalh));
                                progressbarSoTH.setProgress(Float.parseFloat(shots_on_goalh));
                                progressbarFoulH.setProgress(Float.parseFloat(fouls_totalh));
                                progressbarOSH.setProgress(Float.parseFloat(offsides_totalh));
                                progressbarCornorH.setProgress(Float.parseFloat(corners_totalh));
                                progressbarPossesionH.setProgress(Float.parseFloat(possesionh));
                                progressbarYCH.setProgress(Float.parseFloat(yellowcardsh));
                                progressbarRCH.setProgress(Float.parseFloat(redcardsh));
                                progressbarSaveH.setProgress(Float.parseFloat(savesh));

                                String shots_on_goala = away.get("shots_on_goal").getAsString();
                                String shots_totala = away.get("shots_total").getAsString();
                                String fouls_totala = away.get("fouls_total").getAsString();
                                String corners_totala = away.get("corners_total").getAsString();
                                String offsides_totala = away.get("offsides_total").getAsString();
                                String possesiona = away.get("possesion").getAsString();
                                String yellowcardsa = away.get("yellowcards").getAsString();
                                String redcardsa = away.get("redcards").getAsString();
                                String savesa = away.get("saves").getAsString();
                                textViewTSA.setText(shots_totala);
                                textViewSoTA.setText(shots_on_goala);
                                textViewFoulA.setText(fouls_totalh);
                                textViewOSA.setText(offsides_totala);
                                textViewCornorA.setText(corners_totalh);
                                textViewPossesionA.setText(possesiona);
                                textViewYCA.setText(yellowcardsh);
                                textViewRCA.setText(redcardsa);
                                textViewSaveA.setText(savesa);

                                progressbarTSA.setProgress(Float.parseFloat(shots_totala));
                                progressbarSoTA.setProgress(Float.parseFloat(shots_on_goala));
                                progressbarFoulA.setProgress(Float.parseFloat(fouls_totala));
                                progressbarOSA.setProgress(Float.parseFloat(offsides_totala));
                                progressbarCornorA.setProgress(Float.parseFloat(corners_totala));
                                progressbarPossesionA.setProgress(Float.parseFloat(possesiona));
                                progressbarYCA.setProgress(Float.parseFloat(yellowcardsa));
                                progressbarRCA.setProgress(Float.parseFloat(redcardsa));
                                progressbarSaveA.setProgress(Float.parseFloat(savesa));


                            } else {
                                linearLayoutError.setVisibility(View.VISIBLE);
                                linearLayoutStatistic.setVisibility(View.GONE);
                                textViewError.setText(result.get("error").getAsString());
                                //showMessage(result.get("error").getAsString());
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
