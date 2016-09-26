package com.netforceinfotech.ibet.currentbet.betarena.stats.summary;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {
    Context context;
    TextView textViewTSH, textViewSoTH, textViewFoulH, textViewOSH, textViewCornorH, textViewPossesionH, textViewYCH, textViewRCH, textViewSaveH;
    TextView textViewTSA, textViewSoTA, textViewFoulA, textViewOSA, textViewCornorA, textViewPossesionA, textViewYCA, textViewRCA, textViewSaveA;
    RoundCornerProgressBar progressbarTSH, progressbarSoTH, progressbarFoulH, progressbarOSH, progressbarCornorH, progressbarPossesionH, progressbarYCH, progressbarRCH, progressbarSaveH;
    RoundCornerProgressBar progressbarTSA, progressbarSoTA, progressbarFoulA, progressbarOSA, progressbarCornorA, progressbarPossesionA, progressbarYCA, progressbarRCA, progressbarSaveA;
    LinearLayout linearLayout;
    ScrollView scrollView;
    TextView textViewGoal;

    ImageView imageViewHome, imageViewAway;
    TextView textViewTeamA, textViewTeamB, textViewTime, textViewHomeGoal, textViewAwayGoal;


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
        String matchid = this.getArguments().getString("match_id");
        getStatistic(matchid);
        return view;
    }

    private void initview(View view) {
        textViewGoal = (TextView) view.findViewById(R.id.textViewGoal);
        imageViewAway = (ImageView) view.findViewById(R.id.imageViewTeamB);
        imageViewHome = (ImageView) view.findViewById(R.id.imageViewTeamA);
        textViewTeamA = (TextView) view.findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) view.findViewById(R.id.textViewTeamB);
        textViewTime = (TextView) view.findViewById(R.id.textViewLevelNumber);
        textViewHomeGoal = (TextView) view.findViewById(R.id.textViewHomeGoal);
        textViewAwayGoal = (TextView) view.findViewById(R.id.textViewAwayGoal);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutInput);

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

    private void getStatistic(final String matchid) {
        //https://api.soccerama.pro/v1.1/statistics/match/698884?api_token=DLhRgpl372eKkR1o7WzSDn3SlGntcDVQMTWn9HkrTaRwdFWVhveFfaH7K4QP&include=match,team
        //https://api.soccerama.pro/v1.1/statistics/match/690006?api_token=DLhRgpl372eKkR1o7WzSDn3SlGntcDVQMTWn9HkrTaRwdFWVhveFfaH7K4QP
        String token = "DLhRgpl372eKkR1o7WzSDn3SlGntcDVQMTWn9HkrTaRwdFWVhveFfaH7K4QP";
        String url = "https://api.soccerama.pro/v1.1/statistics/match/" + matchid + "?api_token=" + token + "&include=match,team";
        Log.i("result url", url);
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result == null) {
                            linearLayout.setVisibility(View.VISIBLE);
                            scrollView.setVisibility(View.GONE);

                        } else {
                            try {
                                try {
                                    if (!result.get("error").isJsonNull()) {
                                        linearLayout.setVisibility(View.VISIBLE);
                                        scrollView.setVisibility(View.GONE);
                                    }
                                } catch (Exception ex) {

                                }
                                try {
                                    linearLayout.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                    JsonObject home = result.getAsJsonObject("home");
                                    JsonObject away = result.getAsJsonObject("away");
                                    JsonObject hometeam = home.getAsJsonObject("team");
                                    JsonObject awayteam = away.getAsJsonObject("team");
                                    JsonObject match = home.getAsJsonObject("match");
                                    String goalhome = match.get("home_score").getAsString();
                                    String away_score = match.get("away_score").getAsString();
                                    String minute = "", goal = "";
                                    try {
                                        String ftstatus = match.get("status").getAsString();
                                        if (ftstatus.equalsIgnoreCase("ft")) {
                                            goal = match.get("ft_score").getAsString();
                                        } else {
                                            goal = goalhome + "-" + away_score;
                                        }

                                    } catch (Exception ex) {

                                    }
                                    textViewGoal.setText(goal);

                                    if (!match.get("minute").isJsonNull()) {
                                        minute = match.get("minute").getAsString();
                                    }
                                    String extra_minute = "";
                                    if (!match.get("extra_minute").isJsonNull()) {
                                        extra_minute = match.get("extra_minute").getAsString();
                                    }
                                    String hometeamlogo = "";
                                    String awayteamlogo = "";
                                    String hometeamname = "", awayteamname = "";
                                    if (!hometeam.get("logo").isJsonNull()) {
                                        hometeamlogo = hometeam.get("logo").getAsString();
                                    }
                                    if (!awayteam.get("logo").isJsonNull()) {
                                        awayteamlogo = awayteam.get("logo").getAsString();
                                    }
                                    if (!hometeam.get("name").isJsonNull()) {
                                        hometeamname = hometeam.get("name").getAsString();
                                    }
                                    if (!awayteam.get("name").isJsonNull()) {
                                        awayteamname = awayteam.get("name").getAsString();
                                    }
                                    textViewTeamA.setText(hometeamname);
                                    textViewTeamB.setText(awayteamname);


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
                                    textViewFoulA.setText(fouls_totala);
                                    textViewOSA.setText(offsides_totala);
                                    textViewCornorA.setText(corners_totala);
                                    textViewPossesionA.setText(possesiona);
                                    textViewYCA.setText(yellowcardsa);
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
                                    if (extra_minute.equalsIgnoreCase("0")) {
                                        textViewTime.setText(minute + "'");
                                    } else {
                                        textViewTime.setText(minute + "'+" + extra_minute);
                                    }
                                    if (hometeamlogo.length() > 0) {
                                        Picasso.with(context)
                                                .load(hometeamlogo)
                                                .placeholder(R.drawable.ic_holder)
                                                .error(R.drawable.ic_error)
                                                .into(imageViewHome);
                                    } else {
                                        imageViewHome.setImageResource(R.drawable.ic_error);
                                    }
                                    if (awayteamlogo.length() > 0) {
                                        Picasso.with(context)
                                                .load(awayteamlogo)
                                                .placeholder(R.drawable.ic_holder)
                                                .error(R.drawable.ic_error)
                                                .into(imageViewAway);
                                    } else {
                                        imageViewAway.setImageResource(R.drawable.ic_error);
                                    }

                                } catch (Exception ex) {
                                    linearLayout.setVisibility(View.VISIBLE);
                                    scrollView.setVisibility(View.GONE);
                                    //showMessage(result.get("error").getAsString());
                                }
                            } catch (Exception ex) {
                                linearLayout.setVisibility(View.VISIBLE);
                                scrollView.setVisibility(View.GONE);
                                ex.printStackTrace();
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
