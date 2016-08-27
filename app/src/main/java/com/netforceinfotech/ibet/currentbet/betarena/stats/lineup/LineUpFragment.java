package com.netforceinfotech.ibet.currentbet.betarena.stats.lineup;


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

import com.amulyakhare.textdrawable.TextDrawable;
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
import com.netforceinfotech.ibet.live_event.CurrentGameData;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineUpFragment extends Fragment {
    Context context;
    ArrayList<LineUPData> lineUPDatasHome = new ArrayList<>();
    ArrayList<LineUPData> lineUPDatasAway = new ArrayList<>();
    private TextDrawable drawable;
    LinearLayout linearLayoutGKH, linearLayoutDH3, linearLayoutDH4, linearLayoutDH5, linearLayoutMH1, linearLayoutMH2, linearLayoutMH3, linearLayoutMH4, linearLayoutMH5, linearLayoutFH1, linearLayoutFH2, linearLayoutFH3, linearLayoutFH4;
    LinearLayout linearLayoutGKA, linearLayoutDA3, linearLayoutDA4, linearLayoutDA5, linearLayoutMA1, linearLayoutMA2, linearLayoutMA3, linearLayoutMA4, linearLayoutMA5, linearLayoutFA1, linearLayoutFA2, linearLayoutFA3, linearLayoutFA4;
    ImageView imageViewGKH;
    ImageView imageViewGKA;
    ImageView imageViewDH31, imageViewDH32, imageViewDH33, imageViewDH41, imageViewDH42, imageViewDH43, imageViewDH44, imageViewDH51, imageViewDH52, imageViewDH53, imageViewDH54, imageViewDH55;
    ImageView imageViewDA31, imageViewDA32, imageViewDA33, imageViewDA41, imageViewDA42, imageViewDA43, imageViewDA44, imageViewDA51, imageViewDA52, imageViewDA53, imageViewDA54, imageViewDA55;
    ImageView imageViewMH11, imageViewMH21, imageViewMH22, imageViewMH31, imageViewMH32, imageViewMH33, imageViewMH41, imageViewMH42, imageViewMH43, imageViewMH44, imageViewMH51, imageViewMH52, imageViewMH53, imageViewMH54, imageViewMH55;
    ImageView imageViewMA11, imageViewMA21, imageViewMA22, imageViewMA31, imageViewMA32, imageViewMA33, imageViewMA41, imageViewMA42, imageViewMA43, imageViewMA44, imageViewMA51, imageViewMA52, imageViewMA53, imageViewMA54, imageViewMA55;
    ImageView imageViewFH11, imageViewFH21, imageViewFH22, imageViewFH31, imageViewFH32, imageViewFH33, imageViewFH41, imageViewFH42, imageViewFH43, imageViewFH44;
    ImageView imageViewFA11, imageViewFA21, imageViewFA22, imageViewFA31, imageViewFA32, imageViewFA33, imageViewFA41, imageViewFA42, imageViewFA43, imageViewFA44;

    TextView textViewGKH;
    TextView textViewGKA;
    TextView textViewDH31, textViewDH32, textViewDH33, textViewDH41, textViewDH42, textViewDH43, textViewDH44, textViewDH51, textViewDH52, textViewDH53, textViewDH54, textViewDH55;
    TextView textViewDA31, textViewDA32, textViewDA33, textViewDA41, textViewDA42, textViewDA43, textViewDA44, textViewDA51, textViewDA52, textViewDA53, textViewDA54, textViewDA55;
    TextView textViewMH11, textViewMH21, textViewMH22, textViewMH31, textViewMH32, textViewMH33, textViewMH41, textViewMH42, textViewMH43, textViewMH44, textViewMH51, textViewMH52, textViewMH53, textViewMH54, textViewMH55;
    TextView textViewMA11, textViewMA21, textViewMA22, textViewMA31, textViewMA32, textViewMA33, textViewMA41, textViewMA42, textViewMA43, textViewMA44, textViewMA51, textViewMA52, textViewMA53, textViewMA54, textViewMA55;
    TextView textViewFH11, textViewFH21, textViewFH22, textViewFH31, textViewFH32, textViewFH33, textViewFH41, textViewFH42, textViewFH43, textViewFH44;
    TextView textViewFA11, textViewFA21, textViewFA22, textViewFA31, textViewFA32, textViewFA33, textViewFA41, textViewFA42, textViewFA43, textViewFA44;
    private String homeTeamLogo = "",awayTeamLogo="";

    ArrayList<LineUPData> arrayListHomeDefender = new ArrayList<>();
    ArrayList<LineUPData> arrayListAwayDefender = new ArrayList<>();
    ArrayList<LineUPData> arrayListHomeMid = new ArrayList<>();
    ArrayList<LineUPData> arrayListAwayMid = new ArrayList<>();
    ArrayList<LineUPData> arrayListHomeForward = new ArrayList<>();
    ArrayList<LineUPData> arrayListAwayForward = new ArrayList<>();

    LinearLayout linearLayoutError;
    ScrollView scrollView;
    TextView textViewError;
    private String matchid;

    public LineUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_line_up3, container, false);
        context = getActivity();
        try {
            matchid = this.getArguments().getString("matchid");
        } catch (Exception ex) {
        }
        initview(view);
        getLineUp1(matchid);
        //    setDummyLineUp();
        return view;
    }

    private void getLineUp1(String matchid) {
        UserSessionManager userSessionManager = new UserSessionManager(context);
        String token = userSessionManager.getApitoken();
        String url = "https://api.soccerama.pro/v1.1/matches/" + matchid + "?api_token=" + token + "&include=lineup,homeTeam,awayTeam";
        RequestQueue queue = Volley.newRequestQueue(context);
        Log.i("url", url);
        JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                createMyReqSuccessListener(),
                createMyReqErrorListener());

        queue.add(myReq);
    }

    private void getLineUp(String matchid) {
        //https://api.soccerama.pro/v1.1/matches/690006?api_token=DLhRgpl372eKkR1o7WzSDn3SlGntcDVQMTWn9HkrTaRwdFWVhveFfaH7K4QP&include=lineup,homeTeam,awayTeam
        String query = "$";
        UserSessionManager userSessionManager = new UserSessionManager(context);
        String token = userSessionManager.getApitoken();

        try {
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://api.soccerama.pro/v1.1/matches/" + matchid + "?api_token=" + token + "&include=lineup,homeTeam,awayTeam";
        // url = url + "/events_by_match_id.php?matchid=" + matchid + "&home_team_id=" + teamaid + "&away_team_id=" + teambid;
        // url = url + "/events_by_match_id.php?matchid=" + "736799" + "&home_team_id=" + "6722" + "&away_team_id=" + "6724";
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result == null) {
                            showMessage("something is wrong");
                            e.printStackTrace();
                        } else {
                            try {

                                JsonObject homeTeam = result.getAsJsonObject("homeTeam");
                                JsonObject awayTeam = result.getAsJsonObject("awayTeam");
                                JsonObject lineup = result.getAsJsonObject("lineup");
                                if (!homeTeam.get("logo").isJsonNull()) {
                                    homeTeamLogo = homeTeam.get("logo").getAsString();
                                } else {
                                    homeTeamLogo = "";
                                }
                                if (!awayTeam.get("logo").isJsonNull()) {
                                    homeTeamLogo = awayTeam.get("logo").getAsString();
                                } else {
                                    homeTeamLogo = "";
                                }

                                String hometeamid = homeTeam.get("id").getAsString();
                                String awayteamid = awayTeam.get("id").getAsString();
                                JsonArray data = lineup.getAsJsonArray("data");
                                if (data.size() == 0) {
                                    textViewError.setText("line up not available");
                                    linearLayoutError.setVisibility(View.VISIBLE);
                                    scrollView.setVisibility(View.GONE);
                                } else {

                                    linearLayoutError.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                    for (int i = 0; i < data.size(); i++) {
                                        JsonObject jsonObject = data.get(i).getAsJsonObject();
                                        String team_id = jsonObject.get("team_id").getAsString();
                                        String player_name = "";
                                        if (jsonObject.get("player_name").isJsonNull()) {
                                            player_name = "";
                                        } else {
                                            player_name = jsonObject.get("player_name").getAsString();
                                        }
                                        String position = "";
                                        if (jsonObject.get("position").isJsonNull()) {
                                            position = "SUB";
                                        } else {
                                            position = jsonObject.get("position").getAsString();
                                        }
                                        String type = jsonObject.get("type").getAsString();
                                        String shirt_number = jsonObject.get("shirt_number").getAsString();
                                        if (type.equalsIgnoreCase("selection") && !position.equalsIgnoreCase("SUB")) {
                                            if (team_id.equalsIgnoreCase(hometeamid)) {
                                                lineUPDatasHome.add(new LineUPData(position, shirt_number, player_name));
                                            } else {
                                                lineUPDatasAway.add(new LineUPData(position, shirt_number, player_name));
                                            }
                                        }
                                    }
                                    initLineUp(lineUPDatasHome, lineUPDatasAway);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void initLineUp(ArrayList<LineUPData> lineUPDatasHome, ArrayList<LineUPData> lineUPDatasAway) {
        int countHD = 0, countHM = 0, countHF = 0, countAD = 0, countAM = 0, countAF = 0;
        for (int i = 0; i < lineUPDatasHome.size(); i++) {
            switch (lineUPDatasHome.get(i).position) {
                case "Goalkeeper":
                    textViewGKH.setText(lineUPDatasHome.get(i).name);
                    if (homeTeamLogo.length() > 0) {
                        Picasso.with(context)
                                .load(homeTeamLogo)
                                .placeholder(R.drawable.ic_holder)
                                .error(R.drawable.ic_error)
                                .into(imageViewGKH);
                    } else {
                        imageViewGKH.setImageResource(R.drawable.ic_error);
                    }
                    break;
                case "Defender":
                case "CD-L":
                case "CD-R":
                case "LB":
                case "RB":
                case "CD":
                    arrayListHomeDefender.add(lineUPDatasHome.get(i));
                    countHD++;
                    break;
                case "Midfielder":
                case "CM":
                case "CM-L":
                case "CM-R":
                case "LM":
                case "AM":
                case "RM":
                case "AM-L":
                case "AM-R":

                    arrayListHomeMid.add(lineUPDatasHome.get(i));
                    countHM++;
                    break;
                case "Forward":
                case "LF":
                case "RF":
                case "CF-L":
                case "CF-R":

                    arrayListHomeForward.add(lineUPDatasHome.get(i));
                    countHF++;
                    break;
            }
        }
        for (int i = 0; i < lineUPDatasAway.size(); i++) {
            switch (lineUPDatasAway.get(i).position) {
                case "Goalkeeper":
                    textViewGKA.setText(lineUPDatasAway.get(i).name);
                    if (homeTeamLogo.length() > 0) {
                        Picasso.with(context)
                                .load(awayTeamLogo)
                                .placeholder(R.drawable.ic_holder)
                                .error(R.drawable.ic_error)
                                .into(imageViewGKA);
                    } else {
                        imageViewGKA.setImageResource(R.drawable.ic_error);
                    }
                    break;
                case "Defender":
                case "CD-L":
                case "CD-R":
                case "LB":
                case "RB":
                case "CD":
                    arrayListAwayDefender.add(lineUPDatasAway.get(i));
                    countAD++;
                    break;
                case "Midfielder":
                case "CM":
                case "CM-L":
                case "CM-R":
                case "LM":
                case "AM":
                case "RM":
                case "AM-L":
                case "AM-R":
                    arrayListAwayMid.add(lineUPDatasAway.get(i));
                    countAM++;
                    break;
                case "Forward":
                case "LF":
                case "RF":
                case "CF-L":
                case "CF-R":
                    arrayListAwayForward.add(lineUPDatasAway.get(i));
                    countAF++;
                    break;
            }
        }
//****************************************************************************
        switch (countHD)

        {
            case 3:
                linearLayoutDH3.setVisibility(View.VISIBLE);
                textViewDH31.setText(arrayListHomeDefender.get(0).name);
                textViewDH32.setText(arrayListHomeDefender.get(1).name);
                textViewDH33.setText(arrayListHomeDefender.get(2).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH31);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH32);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH33);
                } else {
                    imageViewDH31.setImageResource(R.drawable.ic_error);
                    imageViewDH32.setImageResource(R.drawable.ic_error);
                    imageViewDH33.setImageResource(R.drawable.ic_error);
                }

                break;
            case 4:
                linearLayoutDH4.setVisibility(View.VISIBLE);
                textViewDH41.setText(arrayListHomeDefender.get(0).name);
                textViewDH42.setText(arrayListHomeDefender.get(1).name);
                textViewDH43.setText(arrayListHomeDefender.get(2).name);
                textViewDH44.setText(arrayListHomeDefender.get(3).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH41);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH42);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH43);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH44);
                } else {
                    imageViewDH41.setImageResource(R.drawable.ic_error);
                    imageViewDH42.setImageResource(R.drawable.ic_error);
                    imageViewDH43.setImageResource(R.drawable.ic_error);
                    imageViewDH44.setImageResource(R.drawable.ic_error);
                }
                break;
            case 5:
                linearLayoutDH5.setVisibility(View.VISIBLE);
                textViewDH51.setText(arrayListHomeDefender.get(0).name);
                textViewDH52.setText(arrayListHomeDefender.get(1).name);
                textViewDH53.setText(arrayListHomeDefender.get(2).name);
                textViewDH54.setText(arrayListHomeDefender.get(3).name);
                textViewDH55.setText(arrayListHomeDefender.get(4).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH51);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH52);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH53);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH54);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDH55);
                } else {
                    imageViewDH51.setImageResource(R.drawable.ic_error);
                    imageViewDH52.setImageResource(R.drawable.ic_error);
                    imageViewDH53.setImageResource(R.drawable.ic_error);
                    imageViewDH54.setImageResource(R.drawable.ic_error);
                    imageViewDH55.setImageResource(R.drawable.ic_error);

                }
                break;

        }

        switch (countHM)

        {
            case 1:
                linearLayoutMH1.setVisibility(View.VISIBLE);
                textViewMH11.setText(arrayListHomeMid.get(0).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH11);
                } else {
                    imageViewMH11.setImageResource(R.drawable.ic_error);
                }

                break;
            case 2:
                linearLayoutMH2.setVisibility(View.VISIBLE);
                textViewMH21.setText(arrayListHomeMid.get(0).name);
                textViewMH22.setText(arrayListHomeMid.get(1).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH21);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH22);
                } else {
                    imageViewMH21.setImageResource(R.drawable.ic_error);
                    imageViewMH22.setImageResource(R.drawable.ic_error);
                }
                break;
            case 3:
                linearLayoutMH3.setVisibility(View.VISIBLE);
                textViewMH31.setText(arrayListHomeMid.get(0).name);
                textViewMH32.setText(arrayListHomeMid.get(1).name);
                textViewMH33.setText(arrayListHomeMid.get(2).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH31);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH32);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH33);
                } else {
                    imageViewMH31.setImageResource(R.drawable.ic_error);
                    imageViewMH32.setImageResource(R.drawable.ic_error);
                    imageViewMH33.setImageResource(R.drawable.ic_error);
                }
                break;
            case 4:
                linearLayoutMH4.setVisibility(View.VISIBLE);
                textViewMH41.setText(arrayListHomeMid.get(0).name);
                textViewMH42.setText(arrayListHomeMid.get(1).name);
                textViewMH43.setText(arrayListHomeMid.get(2).name);
                textViewMH44.setText(arrayListHomeMid.get(3).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH41);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH42);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH43);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH44);
                } else {
                    imageViewMH41.setImageResource(R.drawable.ic_error);
                    imageViewMH42.setImageResource(R.drawable.ic_error);
                    imageViewMH43.setImageResource(R.drawable.ic_error);
                    imageViewMH44.setImageResource(R.drawable.ic_error);
                }
                break;
            case 5:
                linearLayoutMH5.setVisibility(View.VISIBLE);
                textViewMH51.setText(arrayListHomeMid.get(0).name);
                textViewMH52.setText(arrayListHomeMid.get(1).name);
                textViewMH53.setText(arrayListHomeMid.get(2).name);
                textViewMH54.setText(arrayListHomeMid.get(3).name);
                textViewMH55.setText(arrayListHomeMid.get(4).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH51);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH52);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH53);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH54);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMH55);

                } else {
                    imageViewMH51.setImageResource(R.drawable.ic_error);
                    imageViewMH52.setImageResource(R.drawable.ic_error);
                    imageViewMH53.setImageResource(R.drawable.ic_error);
                    imageViewMH54.setImageResource(R.drawable.ic_error);
                    imageViewMH55.setImageResource(R.drawable.ic_error);
                }
                break;
        }

        switch (countHF)

        {
            case 1:
                linearLayoutFH1.setVisibility(View.VISIBLE);
                textViewFH11.setText(arrayListHomeForward.get(0).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH11);
                } else {
                    imageViewFH11.setImageResource(R.drawable.ic_error);
                }

                break;
            case 2:
                linearLayoutFH2.setVisibility(View.VISIBLE);
                textViewFH21.setText(arrayListHomeForward.get(0).name);
                textViewFH22.setText(arrayListHomeForward.get(1).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH21);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH22);
                } else {
                    imageViewFH21.setImageResource(R.drawable.ic_error);
                    imageViewFH22.setImageResource(R.drawable.ic_error);
                }

                break;
            case 3:
                linearLayoutFH3.setVisibility(View.VISIBLE);
                textViewFH31.setText(arrayListHomeForward.get(0).name);
                textViewFH32.setText(arrayListHomeForward.get(1).name);
                textViewFH33.setText(arrayListHomeForward.get(2).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH31);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH32);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH33);
                } else {
                    imageViewFH31.setImageResource(R.drawable.ic_error);
                    imageViewFH32.setImageResource(R.drawable.ic_error);
                    imageViewFH33.setImageResource(R.drawable.ic_error);
                }

                break;
            case 4:
                linearLayoutFH4.setVisibility(View.VISIBLE);
                textViewFH41.setText(arrayListHomeForward.get(0).name);
                textViewFH42.setText(arrayListHomeForward.get(1).name);
                textViewFH43.setText(arrayListHomeForward.get(2).name);
                textViewFH44.setText(arrayListHomeForward.get(3).name);
                if (homeTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH41);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH42);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH43);
                    Picasso.with(context)
                            .load(homeTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFH44);
                } else {
                    imageViewFH41.setImageResource(R.drawable.ic_error);
                    imageViewFH42.setImageResource(R.drawable.ic_error);
                    imageViewFH43.setImageResource(R.drawable.ic_error);
                    imageViewFH44.setImageResource(R.drawable.ic_error);
                }

                break;

        }

        //******************************************************************************//

        switch (countAD)

        {
            case 3:
                linearLayoutDA3.setVisibility(View.VISIBLE);
                textViewDA31.setText(arrayListAwayDefender.get(0).name);
                textViewDA32.setText(arrayListAwayDefender.get(1).name);
                textViewDA33.setText(arrayListAwayDefender.get(2).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA31);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA32);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA33);
                } else {
                    imageViewDA31.setImageResource(R.drawable.ic_error);
                    imageViewDA32.setImageResource(R.drawable.ic_error);
                    imageViewDA33.setImageResource(R.drawable.ic_error);
                }

                break;
            case 4:
                linearLayoutDA4.setVisibility(View.VISIBLE);
                textViewDA41.setText(arrayListAwayDefender.get(0).name);
                textViewDA42.setText(arrayListAwayDefender.get(1).name);
                textViewDA43.setText(arrayListAwayDefender.get(2).name);
                textViewDA44.setText(arrayListAwayDefender.get(3).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA41);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA42);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA43);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA44);
                } else {
                    imageViewDA41.setImageResource(R.drawable.ic_error);
                    imageViewDA42.setImageResource(R.drawable.ic_error);
                    imageViewDA43.setImageResource(R.drawable.ic_error);
                    imageViewDA44.setImageResource(R.drawable.ic_error);
                }
                break;
            case 5:
                linearLayoutDA5.setVisibility(View.VISIBLE);
                textViewDA51.setText(arrayListAwayDefender.get(0).name);
                textViewDA52.setText(arrayListAwayDefender.get(1).name);
                textViewDA53.setText(arrayListAwayDefender.get(2).name);
                textViewDA54.setText(arrayListAwayDefender.get(3).name);
                textViewDA55.setText(arrayListAwayDefender.get(4).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA51);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA52);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA53);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA54);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewDA55);
                } else {
                    imageViewDA51.setImageResource(R.drawable.ic_error);
                    imageViewDA52.setImageResource(R.drawable.ic_error);
                    imageViewDA53.setImageResource(R.drawable.ic_error);
                    imageViewDA54.setImageResource(R.drawable.ic_error);
                    imageViewDA55.setImageResource(R.drawable.ic_error);

                }
                break;

        }

        switch (countAM)

        {
            case 1:
                linearLayoutMA1.setVisibility(View.VISIBLE);
                textViewMA11.setText(arrayListAwayMid.get(0).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA11);
                } else {
                    imageViewMA11.setImageResource(R.drawable.ic_error);
                }

                break;
            case 2:
                linearLayoutMA2.setVisibility(View.VISIBLE);
                textViewMA21.setText(arrayListAwayMid.get(0).name);
                textViewMA22.setText(arrayListAwayMid.get(1).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA21);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA22);
                } else {
                    imageViewMA21.setImageResource(R.drawable.ic_error);
                    imageViewMA22.setImageResource(R.drawable.ic_error);
                }
                break;
            case 3:
                linearLayoutMA3.setVisibility(View.VISIBLE);
                textViewMA31.setText(arrayListAwayMid.get(0).name);
                textViewMA32.setText(arrayListAwayMid.get(1).name);
                textViewMA33.setText(arrayListAwayMid.get(2).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA31);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA32);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA33);
                } else {
                    imageViewMA31.setImageResource(R.drawable.ic_error);
                    imageViewMA32.setImageResource(R.drawable.ic_error);
                    imageViewMA33.setImageResource(R.drawable.ic_error);
                }
                break;
            case 4:
                linearLayoutMA4.setVisibility(View.VISIBLE);
                textViewMA41.setText(arrayListAwayMid.get(0).name);
                textViewMA42.setText(arrayListAwayMid.get(1).name);
                textViewMA43.setText(arrayListAwayMid.get(2).name);
                textViewMA44.setText(arrayListAwayMid.get(3).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA41);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA42);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA43);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA44);
                } else {
                    imageViewMA41.setImageResource(R.drawable.ic_error);
                    imageViewMA42.setImageResource(R.drawable.ic_error);
                    imageViewMA43.setImageResource(R.drawable.ic_error);
                    imageViewMA44.setImageResource(R.drawable.ic_error);
                }
                break;
            case 5:
                linearLayoutMA5.setVisibility(View.VISIBLE);
                textViewMA51.setText(arrayListAwayMid.get(0).name);
                textViewMA52.setText(arrayListAwayMid.get(1).name);
                textViewMA53.setText(arrayListAwayMid.get(2).name);
                textViewMA54.setText(arrayListAwayMid.get(3).name);
                textViewMA55.setText(arrayListAwayMid.get(4).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA51);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA52);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA53);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA54);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewMA55);

                } else {
                    imageViewMA51.setImageResource(R.drawable.ic_error);
                    imageViewMA52.setImageResource(R.drawable.ic_error);
                    imageViewMA53.setImageResource(R.drawable.ic_error);
                    imageViewMA54.setImageResource(R.drawable.ic_error);
                    imageViewMA55.setImageResource(R.drawable.ic_error);
                }
                break;
        }

        switch (countAF)

        {
            case 1:
                linearLayoutFA1.setVisibility(View.VISIBLE);
                textViewFA11.setText(arrayListAwayForward.get(0).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA11);
                } else {
                    imageViewFA11.setImageResource(R.drawable.ic_error);
                }

                break;
            case 2:
                linearLayoutFA2.setVisibility(View.VISIBLE);
                textViewFA21.setText(arrayListAwayForward.get(0).name);
                textViewFA22.setText(arrayListAwayForward.get(1).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA21);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA22);
                } else {
                    imageViewFA21.setImageResource(R.drawable.ic_error);
                    imageViewFA22.setImageResource(R.drawable.ic_error);
                }

                break;
            case 3:
                linearLayoutFA3.setVisibility(View.VISIBLE);
                textViewFA31.setText(arrayListAwayForward.get(0).name);
                textViewFA32.setText(arrayListAwayForward.get(1).name);
                textViewFA33.setText(arrayListAwayForward.get(3).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA31);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA32);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA33);
                } else {
                    imageViewFA31.setImageResource(R.drawable.ic_error);
                    imageViewFA32.setImageResource(R.drawable.ic_error);
                    imageViewFA33.setImageResource(R.drawable.ic_error);
                }

                break;
            case 4:
                linearLayoutFA4.setVisibility(View.VISIBLE);
                textViewFA41.setText(arrayListAwayForward.get(0).name);
                textViewFA42.setText(arrayListAwayForward.get(1).name);
                textViewFA43.setText(arrayListAwayForward.get(2).name);
                textViewFA44.setText(arrayListAwayForward.get(3).name);
                if (awayTeamLogo.length() > 0) {
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA41);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA42);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA43);
                    Picasso.with(context)
                            .load(awayTeamLogo)
                            .placeholder(R.drawable.ic_holder)
                            .error(R.drawable.ic_error)
                            .into(imageViewFA44);
                } else {
                    imageViewFA41.setImageResource(R.drawable.ic_error);
                    imageViewFA42.setImageResource(R.drawable.ic_error);
                    imageViewFA43.setImageResource(R.drawable.ic_error);
                    imageViewFA44.setImageResource(R.drawable.ic_error);
                }

                break;

        }

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void setDummyLineUp() {
        //case "Goalkeeper":"Midfielder": "Defender":"Forward"
        scrollView.setVisibility(View.VISIBLE);
        lineUPDatasAway.add(new LineUPData("Goalkeeper", "", ""));
        lineUPDatasAway.add(new LineUPData("Defender", "", ""));
        lineUPDatasAway.add(new LineUPData("Defender", "", ""));
        lineUPDatasAway.add(new LineUPData("Defender", "", ""));
        lineUPDatasAway.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasAway.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasAway.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasAway.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasAway.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasAway.add(new LineUPData("Defender", "", ""));
        lineUPDatasAway.add(new LineUPData("Forward", "", ""));

        lineUPDatasHome.add(new LineUPData("Goalkeeper", "", ""));
        lineUPDatasHome.add(new LineUPData("Defender", "", ""));
        lineUPDatasHome.add(new LineUPData("Defender", "", ""));
        lineUPDatasHome.add(new LineUPData("Defender", "", ""));
        lineUPDatasHome.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasHome.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasHome.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasHome.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasHome.add(new LineUPData("Midfielder", "", ""));
        lineUPDatasHome.add(new LineUPData("Defender", "", ""));
        lineUPDatasHome.add(new LineUPData("Forward", "", ""));
        initLineUp(lineUPDatasHome, lineUPDatasAway);

    }


    private void initview(View view) {
        linearLayoutError = (LinearLayout) view.findViewById(R.id.linearLayoutError);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        textViewError = (TextView) view.findViewById(R.id.textViewError);

        linearLayoutGKH = (LinearLayout) view.findViewById(R.id.linearLayoutGKH);
        linearLayoutDH3 = (LinearLayout) view.findViewById(R.id.linearLayoutDH3);
        linearLayoutDH4 = (LinearLayout) view.findViewById(R.id.linearLayoutDH4);
        linearLayoutDH5 = (LinearLayout) view.findViewById(R.id.linearLayoutDH5);
        linearLayoutMH1 = (LinearLayout) view.findViewById(R.id.linearLayoutMH1);
        linearLayoutMH2 = (LinearLayout) view.findViewById(R.id.linearLayoutMH2);
        linearLayoutMH3 = (LinearLayout) view.findViewById(R.id.linearLayoutMH3);
        linearLayoutMH4 = (LinearLayout) view.findViewById(R.id.linearLayoutMH4);
        linearLayoutMH5 = (LinearLayout) view.findViewById(R.id.linearLayoutMH5);
        linearLayoutFH1 = (LinearLayout) view.findViewById(R.id.linearLayoutFH1);
        linearLayoutFH1 = (LinearLayout) view.findViewById(R.id.linearLayoutFH1);
        linearLayoutFH2 = (LinearLayout) view.findViewById(R.id.linearLayoutFH2);
        linearLayoutFH3 = (LinearLayout) view.findViewById(R.id.linearLayoutFH3);

        linearLayoutGKA = (LinearLayout) view.findViewById(R.id.linearLayoutGKA);
        linearLayoutDA3 = (LinearLayout) view.findViewById(R.id.linearLayoutDA3);
        linearLayoutDA4 = (LinearLayout) view.findViewById(R.id.linearLayoutDA4);
        linearLayoutDA5 = (LinearLayout) view.findViewById(R.id.linearLayoutDA5);
        linearLayoutMA1 = (LinearLayout) view.findViewById(R.id.linearLayoutMA1);
        linearLayoutMA2 = (LinearLayout) view.findViewById(R.id.linearLayoutMA2);
        linearLayoutMA3 = (LinearLayout) view.findViewById(R.id.linearLayoutMA3);
        linearLayoutMA4 = (LinearLayout) view.findViewById(R.id.linearLayoutMA4);
        linearLayoutMA5 = (LinearLayout) view.findViewById(R.id.linearLayoutMA5);
        linearLayoutFA1 = (LinearLayout) view.findViewById(R.id.linearLayoutFA1);
        linearLayoutFA1 = (LinearLayout) view.findViewById(R.id.linearLayoutFA1);
        linearLayoutFA2 = (LinearLayout) view.findViewById(R.id.linearLayoutFA2);
        linearLayoutFA3 = (LinearLayout) view.findViewById(R.id.linearLayoutFA3);
        initHomeDefender(view);
        initAwayDefender(view);
        initHomeMid(view);
        initAwayMid(view);
        initHomeForword(view);
        initAwayForword(view);
        initHomeGoalKeeper(view);
        initAwayGoalKeeper(view);
    }

    private void initAwayGoalKeeper(View view) {
        imageViewGKA = (ImageView) view.findViewById(R.id.imageViewGKa);
        textViewGKA = (TextView) view.findViewById(R.id.textViewGKa);

    }

    private void initHomeGoalKeeper(View view) {
        imageViewGKH = (ImageView) view.findViewById(R.id.imageViewGKH);
        textViewGKH = (TextView) view.findViewById(R.id.textViewGKH);
    }

    private void initAwayForword(View view) {
        imageViewFA11 = (ImageView) view.findViewById(R.id.imageViewFA11);
        imageViewFA21 = (ImageView) view.findViewById(R.id.imageViewFA21);
        imageViewFA22 = (ImageView) view.findViewById(R.id.imageViewFA22);
        imageViewFA31 = (ImageView) view.findViewById(R.id.imageViewFA31);
        imageViewFA32 = (ImageView) view.findViewById(R.id.imageViewFA32);
        imageViewFA33 = (ImageView) view.findViewById(R.id.imageViewFA33);
        imageViewFA41 = (ImageView) view.findViewById(R.id.imageViewFA41);
        imageViewFA42 = (ImageView) view.findViewById(R.id.imageViewFA42);
        imageViewFA43 = (ImageView) view.findViewById(R.id.imageViewFA43);
        imageViewFA44 = (ImageView) view.findViewById(R.id.imageViewFA44);

        textViewFA11 = (TextView) view.findViewById(R.id.textViewFA11);
        textViewFA21 = (TextView) view.findViewById(R.id.textViewFA21);
        textViewFA22 = (TextView) view.findViewById(R.id.textViewFA22);
        textViewFA31 = (TextView) view.findViewById(R.id.textViewFA31);
        textViewFA32 = (TextView) view.findViewById(R.id.textViewFA32);
        textViewFA33 = (TextView) view.findViewById(R.id.textViewFA33);
        textViewFA41 = (TextView) view.findViewById(R.id.textViewFA41);
        textViewFA42 = (TextView) view.findViewById(R.id.textViewFA42);
        textViewFA43 = (TextView) view.findViewById(R.id.textViewFA43);
        textViewFA44 = (TextView) view.findViewById(R.id.textViewFA44);
    }

    private void initHomeForword(View view) {
        imageViewFH11 = (ImageView) view.findViewById(R.id.imageViewFH11);
        imageViewFH21 = (ImageView) view.findViewById(R.id.imageViewFH21);
        imageViewFH22 = (ImageView) view.findViewById(R.id.imageViewFH22);
        imageViewFH31 = (ImageView) view.findViewById(R.id.imageViewFH31);
        imageViewFH32 = (ImageView) view.findViewById(R.id.imageViewFH32);
        imageViewFH33 = (ImageView) view.findViewById(R.id.imageViewFH33);
        imageViewFH41 = (ImageView) view.findViewById(R.id.imageViewFH41);
        imageViewFH42 = (ImageView) view.findViewById(R.id.imageViewFH42);
        imageViewFH43 = (ImageView) view.findViewById(R.id.imageViewFH43);
        imageViewFH44 = (ImageView) view.findViewById(R.id.imageViewFH44);

        textViewFH11 = (TextView) view.findViewById(R.id.textViewFH11);
        textViewFH21 = (TextView) view.findViewById(R.id.textViewFH21);
        textViewFH22 = (TextView) view.findViewById(R.id.textViewFH22);
        textViewFH31 = (TextView) view.findViewById(R.id.textViewFH31);
        textViewFH32 = (TextView) view.findViewById(R.id.textViewFH32);
        textViewFH33 = (TextView) view.findViewById(R.id.textViewFH33);
        textViewFH41 = (TextView) view.findViewById(R.id.textViewFH41);
        textViewFH42 = (TextView) view.findViewById(R.id.textViewFH42);
        textViewFH43 = (TextView) view.findViewById(R.id.textViewFH43);
        textViewFH44 = (TextView) view.findViewById(R.id.textViewFH44);
    }

    private void initAwayMid(View view) {
        imageViewMA11 = (ImageView) view.findViewById(R.id.imageViewMA11);
        imageViewMA21 = (ImageView) view.findViewById(R.id.imageViewMA21);
        imageViewMA22 = (ImageView) view.findViewById(R.id.imageViewMA22);
        imageViewMA31 = (ImageView) view.findViewById(R.id.imageViewMA31);
        imageViewMA32 = (ImageView) view.findViewById(R.id.imageViewMA32);
        imageViewMA33 = (ImageView) view.findViewById(R.id.imageViewMA33);
        imageViewMA41 = (ImageView) view.findViewById(R.id.imageViewMA41);
        imageViewMA42 = (ImageView) view.findViewById(R.id.imageViewMA42);
        imageViewMA43 = (ImageView) view.findViewById(R.id.imageViewMA43);
        imageViewMA44 = (ImageView) view.findViewById(R.id.imageViewMA44);
        imageViewMA51 = (ImageView) view.findViewById(R.id.imageViewMA51);
        imageViewMA52 = (ImageView) view.findViewById(R.id.imageViewMA52);
        imageViewMA53 = (ImageView) view.findViewById(R.id.imageViewMA53);
        imageViewMA54 = (ImageView) view.findViewById(R.id.imageViewMA54);
        imageViewMA55 = (ImageView) view.findViewById(R.id.imageViewMA55);

        textViewMA11 = (TextView) view.findViewById(R.id.textViewMA11);
        textViewMA21 = (TextView) view.findViewById(R.id.textViewMA21);
        textViewMA22 = (TextView) view.findViewById(R.id.textViewMA22);
        textViewMA31 = (TextView) view.findViewById(R.id.textViewMA31);
        textViewMA32 = (TextView) view.findViewById(R.id.textViewMA32);
        textViewMA33 = (TextView) view.findViewById(R.id.textViewMA33);
        textViewMA41 = (TextView) view.findViewById(R.id.textViewMA41);
        textViewMA42 = (TextView) view.findViewById(R.id.textViewMA42);
        textViewMA43 = (TextView) view.findViewById(R.id.textViewMA43);
        textViewMA44 = (TextView) view.findViewById(R.id.textViewMA44);
        textViewMA51 = (TextView) view.findViewById(R.id.textViewMA51);
        textViewMA52 = (TextView) view.findViewById(R.id.textViewMA52);
        textViewMA53 = (TextView) view.findViewById(R.id.textViewMA53);
        textViewMA54 = (TextView) view.findViewById(R.id.textViewMA54);
        textViewMA55 = (TextView) view.findViewById(R.id.textViewMA55);
    }

    private void initHomeMid(View view) {
        imageViewMH11 = (ImageView) view.findViewById(R.id.imageViewMH11);
        imageViewMH21 = (ImageView) view.findViewById(R.id.imageViewMH21);
        imageViewMH22 = (ImageView) view.findViewById(R.id.imageViewMH22);
        imageViewMH31 = (ImageView) view.findViewById(R.id.imageViewMH31);
        imageViewMH32 = (ImageView) view.findViewById(R.id.imageViewMH32);
        imageViewMH33 = (ImageView) view.findViewById(R.id.imageViewMH33);
        imageViewMH41 = (ImageView) view.findViewById(R.id.imageViewMH41);
        imageViewMH42 = (ImageView) view.findViewById(R.id.imageViewMH42);
        imageViewMH43 = (ImageView) view.findViewById(R.id.imageViewMH43);
        imageViewMH44 = (ImageView) view.findViewById(R.id.imageViewMH44);
        imageViewMH51 = (ImageView) view.findViewById(R.id.imageViewMH51);
        imageViewMH52 = (ImageView) view.findViewById(R.id.imageViewMH52);
        imageViewMH53 = (ImageView) view.findViewById(R.id.imageViewMH53);
        imageViewMH54 = (ImageView) view.findViewById(R.id.imageViewMH54);
        imageViewMH55 = (ImageView) view.findViewById(R.id.imageViewMH55);

        textViewMH11 = (TextView) view.findViewById(R.id.textViewMH11);
        textViewMH21 = (TextView) view.findViewById(R.id.textViewMH21);
        textViewMH22 = (TextView) view.findViewById(R.id.textViewMH22);
        textViewMH31 = (TextView) view.findViewById(R.id.textViewMH31);
        textViewMH32 = (TextView) view.findViewById(R.id.textViewMH32);
        textViewMH33 = (TextView) view.findViewById(R.id.textViewMH33);
        textViewMH41 = (TextView) view.findViewById(R.id.textViewMH41);
        textViewMH42 = (TextView) view.findViewById(R.id.textViewMH42);
        textViewMH43 = (TextView) view.findViewById(R.id.textViewMH43);
        textViewMH44 = (TextView) view.findViewById(R.id.textViewMH44);
        textViewMH51 = (TextView) view.findViewById(R.id.textViewMH51);
        textViewMH52 = (TextView) view.findViewById(R.id.textViewMH52);
        textViewMH53 = (TextView) view.findViewById(R.id.textViewMH53);
        textViewMH54 = (TextView) view.findViewById(R.id.textViewMH54);
        textViewMH55 = (TextView) view.findViewById(R.id.textViewMH55);
    }

    private void initAwayDefender(View view) {
        imageViewDA31 = (ImageView) view.findViewById(R.id.imageViewDA31);
        imageViewDA32 = (ImageView) view.findViewById(R.id.imageViewDA32);
        imageViewDA33 = (ImageView) view.findViewById(R.id.imageViewDA33);

        imageViewDA41 = (ImageView) view.findViewById(R.id.imageViewDA41);
        imageViewDA42 = (ImageView) view.findViewById(R.id.imageViewDA42);
        imageViewDA43 = (ImageView) view.findViewById(R.id.imageViewDA43);
        imageViewDA44 = (ImageView) view.findViewById(R.id.imageViewDA44);

        imageViewDA51 = (ImageView) view.findViewById(R.id.imageViewDA51);
        imageViewDA52 = (ImageView) view.findViewById(R.id.imageViewDA52);
        imageViewDA53 = (ImageView) view.findViewById(R.id.imageViewDA53);
        imageViewDA54 = (ImageView) view.findViewById(R.id.imageViewDA54);
        imageViewDA55 = (ImageView) view.findViewById(R.id.imageViewDA55);

        textViewDA31 = (TextView) view.findViewById(R.id.textViewDA31);
        textViewDA32 = (TextView) view.findViewById(R.id.textViewDA32);
        textViewDA33 = (TextView) view.findViewById(R.id.textViewDA33);

        textViewDA41 = (TextView) view.findViewById(R.id.textViewDA41);
        textViewDA42 = (TextView) view.findViewById(R.id.textViewDA42);
        textViewDA43 = (TextView) view.findViewById(R.id.textViewDA43);
        textViewDA44 = (TextView) view.findViewById(R.id.textViewDA44);

        textViewDA51 = (TextView) view.findViewById(R.id.textViewDA51);
        textViewDA52 = (TextView) view.findViewById(R.id.textViewDA52);
        textViewDA53 = (TextView) view.findViewById(R.id.textViewDA53);
        textViewDA54 = (TextView) view.findViewById(R.id.textViewDA54);
        textViewDA55 = (TextView) view.findViewById(R.id.textViewDA55);
    }

    private void initHomeDefender(View view) {
        imageViewDH31 = (ImageView) view.findViewById(R.id.imageViewDH31);
        imageViewDH32 = (ImageView) view.findViewById(R.id.imageViewDH32);
        imageViewDH33 = (ImageView) view.findViewById(R.id.imageViewDH33);

        imageViewDH41 = (ImageView) view.findViewById(R.id.imageViewDH41);
        imageViewDH42 = (ImageView) view.findViewById(R.id.imageViewDH42);
        imageViewDH43 = (ImageView) view.findViewById(R.id.imageViewDH43);
        imageViewDH44 = (ImageView) view.findViewById(R.id.imageViewDH44);

        imageViewDH51 = (ImageView) view.findViewById(R.id.imageViewDH51);
        imageViewDH52 = (ImageView) view.findViewById(R.id.imageViewDH52);
        imageViewDH53 = (ImageView) view.findViewById(R.id.imageViewDH53);
        imageViewDH54 = (ImageView) view.findViewById(R.id.imageViewDH54);
        imageViewDH55 = (ImageView) view.findViewById(R.id.imageViewDH55);

        textViewDH31 = (TextView) view.findViewById(R.id.textViewDH31);
        textViewDH32 = (TextView) view.findViewById(R.id.textViewDH32);
        textViewDH33 = (TextView) view.findViewById(R.id.textViewDH33);

        textViewDH41 = (TextView) view.findViewById(R.id.textViewDH41);
        textViewDH42 = (TextView) view.findViewById(R.id.textViewDH42);
        textViewDH43 = (TextView) view.findViewById(R.id.textViewDH43);
        textViewDH44 = (TextView) view.findViewById(R.id.textViewDH44);

        textViewDH51 = (TextView) view.findViewById(R.id.textViewDH51);
        textViewDH52 = (TextView) view.findViewById(R.id.textViewDH52);
        textViewDH53 = (TextView) view.findViewById(R.id.textViewDH53);
        textViewDH54 = (TextView) view.findViewById(R.id.textViewDH54);
        textViewDH55 = (TextView) view.findViewById(R.id.textViewDH55);

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

    private Response.Listener<JSONObject> createMyReqSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JsonParser jsonParser = new JsonParser();
                JsonObject result = (JsonObject) jsonParser.parse(response.toString());
                try {
                    JsonObject homeTeam = result.getAsJsonObject("homeTeam");
                    JsonObject awayTeam = result.getAsJsonObject("awayTeam");
                    JsonObject lineup = result.getAsJsonObject("lineup");
                    if (!homeTeam.get("logo").isJsonNull()) {

                        homeTeamLogo = homeTeam.get("logo").getAsString();
                    } else {
                        homeTeamLogo = "";
                    }
                    if (!awayTeam.get("logo").isJsonNull()) {
                        awayTeamLogo = awayTeam.get("logo").getAsString();
                    } else {
                        awayTeamLogo = "";
                    }

                    String hometeamid = homeTeam.get("id").getAsString();
                    String awayteamid = awayTeam.get("id").getAsString();
                    JsonArray data = lineup.getAsJsonArray("data");
                    if (data.size() == 0) {
                        textViewError.setText("line up not available");
                        linearLayoutError.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.GONE);
                    } else {
                        linearLayoutError.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);
                        for (int i = 0; i < data.size(); i++) {

                            JsonObject jsonObject = data.get(i).getAsJsonObject();
                            String team_id = jsonObject.get("team_id").getAsString();
                            String player_name = "";
                            if (jsonObject.get("player_name").isJsonNull()) {
                                player_name = "";
                            } else {
                                player_name = jsonObject.get("player_name").getAsString();
                            }
                            String position = "";
                            if (jsonObject.get("position").isJsonNull()) {
                                position = "SUB";
                            } else {
                                position = jsonObject.get("position").getAsString();
                                Log.i("kunsangposition", team_id + "" + i + position);
                            }
                            String type = jsonObject.get("type").getAsString();
                            String shirt_number = jsonObject.get("shirt_number").getAsString();
                            if (type.equalsIgnoreCase("selection")) {
                                if (team_id.equalsIgnoreCase(hometeamid)) {
                                    lineUPDatasHome.add(new LineUPData(position, shirt_number, player_name));
                                } else {
                                    lineUPDatasAway.add(new LineUPData(position, shirt_number, player_name));
                                }
                            }
                        }
                        initLineUp(lineUPDatasHome, lineUPDatasAway);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        };
    }

}
