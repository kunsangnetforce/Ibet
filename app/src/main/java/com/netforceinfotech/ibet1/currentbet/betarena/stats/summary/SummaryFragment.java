package com.netforceinfotech.ibet1.currentbet.betarena.stats.summary;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bcgdv.asia.lib.ticktock.TickTockView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import at.grabner.circleprogress.CircleProgressView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {
    final Handler handler = new Handler();
    Context context;
    TextView textViewTSH, textViewSoTH, textViewFoulH, textViewOSH, textViewCornorH, textViewPossesionH, textViewYCH, textViewRCH, textViewSaveH;
    TextView textViewTSA, textViewSoTA, textViewFoulA, textViewOSA, textViewCornorA, textViewPossesionA, textViewYCA, textViewRCA, textViewSaveA;
    RoundCornerProgressBar progressbarTSH, progressbarSoTH, progressbarFoulH, progressbarOSH, progressbarCornorH, progressbarPossesionH, progressbarYCH, progressbarRCH, progressbarSaveH;
    RoundCornerProgressBar progressbarTSA, progressbarSoTA, progressbarFoulA, progressbarOSA, progressbarCornorA, progressbarPossesionA, progressbarYCA, progressbarRCA, progressbarSaveA;
    LinearLayout linearLayout;
    ScrollView scrollView;
    TextView textViewGoal, textViewMatchStatus;
    TickTockView tickTockView;
    ImageView imageViewHome, imageViewAway;
    TextView textViewTeamA, textViewTeamB, textViewTime, textViewHomeGoal, textViewAwayGoal;
    UserSessionManager userSessionManager;
    View view1;
    ImageView imageViewError;
    CircleProgressView cpvLevel;
    CoordinatorLayout coordinatorLayout;
    private String ftstatus = "";
    private boolean firstTime = true;
    String match_id;

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        initview(view);
        String matchid = this.getArguments().getString("match_id");
        // String matchid = "691136";
        getStatistic(matchid);
        setupTheme();
        setupBackground();
        return view;
    }

    private void initview(View view) {
        view1 = view.findViewById(R.id.view);
        tickTockView = (TickTockView) view.findViewById(R.id.tickTockView);
        cpvLevel = (CircleProgressView) view.findViewById(R.id.cpvLevel);

        textViewMatchStatus = (TextView) view.findViewById(R.id.textViewMatchStatus);
        imageViewError = (ImageView) view.findViewById(R.id.imageViewError);
        Glide.with(context).load(R.drawable.gs_stadium).into(imageViewError);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        textViewGoal = (TextView) view.findViewById(R.id.textViewGoal);
        imageViewAway = (ImageView) view.findViewById(R.id.imageViewTeamB);
        imageViewHome = (ImageView) view.findViewById(R.id.imageViewTeamA);
        textViewTeamA = (TextView) view.findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) view.findViewById(R.id.textViewTeamB);
        textViewTime = (TextView) view.findViewById(R.id.textViewLevelNumber);
        textViewHomeGoal = (TextView) view.findViewById(R.id.textViewHomeGoal);
        textViewAwayGoal = (TextView) view.findViewById(R.id.textViewAwayGoal);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutError);
        linearLayout.setVisibility(View.GONE);

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
        Debugger.i("kunsang_url_getstatistic", url);
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
                                        ftstatus = match.get("status").getAsString();
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
                                    String starting_date = match.get("starting_date").getAsString();
                                    String starting_time = match.get("starting_time").getAsString();
                                    setupProgress(ftstatus, minute, extra_minute, starting_date, starting_time);
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
                                        Glide.with(context)
                                                .fromResource()
                                                .asBitmap()
                                                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                                                .load(R.drawable.home_logo)
                                                .placeholder(R.drawable.ic_holder)
                                                .error(R.drawable.ic_error)
                                                .into(imageViewHome);
                                    } else {
                                        imageViewHome.setImageResource(R.drawable.ic_error);
                                    }
                                    if (awayteamlogo.length() > 0) {
                                        Glide.with(context)
                                                .fromResource()
                                                .asBitmap()
                                                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))

                                                .load(R.drawable.away_logo)
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

    private void setupProgress(String ftstatus, String minute, String extra_minute, String starting_date, String starting_time) {
        switch (ftstatus) {
            case "NS":
                setupTimeThread(starting_date, starting_time);
                break;
            case "LIVE":
                if (firstTime) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.MINUTE, 100);
                    tickTockView.start(calendar);
                    firstTime = !firstTime;

                }
                setupProgressThread(minute, extra_minute, 5000);
                textViewMatchStatus.setText("LIVE");
                break;
            case "HT":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                setupProgressThread(minute, extra_minute, 60000);
                textViewMatchStatus.setText("HALF TIME");
                break;
            case "FT":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }

                //  setupProgressThread(minute, extra_minute, 5000);
                textViewMatchStatus.setText("FULL TIME");
                break;
            case "ET":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                setupProgressThread(minute, extra_minute, 60000);
                cpvLevel.setMaxValue(120);
                textViewMatchStatus.setText("EXTRA TIME");
                break;
            case "PEN_LIVE":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                setupProgressThread(minute, extra_minute, 5000);
                textViewMatchStatus.setText("PENALTY SHOOTOUT");
                break;
            case "AET":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                cpvLevel.setMaxValue(120);
                textViewMatchStatus.setText("FISHED AFTER EXTRA TIME");
                break;
            case "BREAK":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                setupProgressThread(minute, extra_minute, 60000);
                cpvLevel.setMaxValue(120);
                textViewMatchStatus.setText("Match finished, waiting for extra time to start");
                break;
            case "FT_PEN":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                textViewMatchStatus.setText("Full-Time after penalties");
                break;
            case "CANCL":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                textViewMatchStatus.setText("Cancelled");
                break;
            case "POSTP":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                textViewMatchStatus.setText("PostPhoned");
                break;
            case "INT":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                textViewMatchStatus.setText("Interrupted");
                break;
            case "ABAN":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                textViewMatchStatus.setText("Abandoned");
                break;
            case "SUSP":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                textViewMatchStatus.setText("Suspended");
                break;
            case "AWARDED":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                textViewMatchStatus.setText("Awarded");
                break;
            case "DELAY":
                try {
                    tickTockView.stop();
                } catch (Exception ex) {

                }
                textViewMatchStatus.setText("Delayed");
                break;
        }
    }

    private void setupProgressThread(String minute, String extra_minute, int i) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                try {
                    getStatistic(match_id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, i);
    }

    private void setupTimeThread(String starting_date, String starting_time) {
        String datetime = starting_date + " " + starting_time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNow = Calendar.getInstance().getTime();
        Date myDate = null;
        try {
            myDate = simpleDateFormat.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long milliseconds = (myDate.getTime() - dateNow.getTime());
        new CountDownTimer(milliseconds, 1000) {

            public void onTick(long millisUntilFinished) {

                textViewMatchStatus.setText("" + getFormatedTime(millisUntilFinished));
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                textViewMatchStatus.setText("Live!");
                getStatistic(match_id);
            }

        }.start();
    }

    private String getFormatedTime(long millisUntilFinished) {
        long seconds = millisUntilFinished / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = days + "D : " + hours % 24 + " : " + minutes % 60 + " : " + seconds % 60;
        return time;
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

    private void setupTheme() {
        int theme = userSessionManager.getTheme();
        switch (theme) {
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

    private void setupBrownTheme() {
        progressbarTSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarSoTH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarFoulH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarOSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarCornorH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarPossesionH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarYCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarRCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarSaveH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));

        progressbarTSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarSoTA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarFoulA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarOSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarCornorA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarPossesionA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarYCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarRCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
        progressbarSaveA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
    }

    private void setupPurlpleTheme() {
        progressbarTSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarSoTH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarFoulH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarOSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarCornorH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarPossesionH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarYCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarRCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarSaveH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));

        progressbarTSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarSoTA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarFoulA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarOSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarCornorA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarPossesionA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarYCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarRCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
        progressbarSaveA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
    }

    private void setupGreenTheme() {
        progressbarTSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarSoTH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarFoulH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarOSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarCornorH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarPossesionH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarYCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarRCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarSaveH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));

        progressbarTSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarSoTA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarFoulA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarOSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarCornorA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarPossesionA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarYCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarRCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
        progressbarSaveA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
    }

    private void setupMarronTheme() {
        progressbarTSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarSoTH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarFoulH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarOSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarCornorH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarPossesionH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarYCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarRCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarSaveH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));

        progressbarTSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarSoTA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarFoulA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarOSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarCornorA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarPossesionA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarYCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarRCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
        progressbarSaveA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));

    }

    private void setupLightBlueTheme() {
        progressbarTSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarSoTH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarFoulH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarOSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarCornorH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarPossesionH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarYCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarRCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarSaveH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));

        progressbarTSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarSoTA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarFoulA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarOSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarCornorA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarPossesionA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarYCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarRCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
        progressbarSaveA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));

    }

    private void setupDefaultTheme() {
       /*
        RoundCornerProgressBar progressbarTSH, progressbarSoTH, progressbarFoulH, progressbarOSH, progressbarCornorH,
         progressbarPossesionH, progressbarYCH, progressbarRCH, progressbarSaveH;
    RoundCornerProgressBar progressbarTSA, progressbarSoTA, progressbarFoulA, progressbarOSA, progressbarCornorA, progressbarPossesionA, progressbarYCA, progressbarRCA, progressbarSaveA;
       */
        progressbarTSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarSoTH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarFoulH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarOSH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarCornorH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarPossesionH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarYCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarRCH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarSaveH.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

        progressbarTSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarSoTA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarFoulA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarOSA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarCornorA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarPossesionA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarYCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarRCA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        progressbarSaveA.setProgressBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

    }

    private void setupBackground() {

        switch (userSessionManager.getBackground()) {
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
}
