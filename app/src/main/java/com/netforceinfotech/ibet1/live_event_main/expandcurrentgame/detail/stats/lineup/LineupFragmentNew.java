package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.detail.stats.lineup;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineupFragmentNew extends Fragment {

    ArrayList<LineUPData> lineUPDatasHome = new ArrayList<>();
    ArrayList<LineUPData> lineUPDatasAway = new ArrayList<>();
    Context context;
    LinearLayout linearLayoutHD, linearLayoutAD, linearLayoutHM, linearLayoutAM, linearLayoutAF, linearLayoutHF;
    LinearLayout linearLayoutError;
    ScrollView scrollView;
    private String homeTeamLogo, awayTeamLogo;
    TextView textViewError, textViewHGK, textViewAGK;
    ImageView imageViewHGK, imageViewAGK;

    public LineupFragmentNew() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.lineup_fragment_new, container, false);
        context = getActivity();
        //setupDummyContent();
        initview(view);
        String match_id;
        try {
            match_id = this.getArguments().getString("match_id");
            getLineUp1(match_id);
        } catch (Exception ex) {
            showMessage("bundleError");
        }


        //

        return view;
    }

    private void showMessage(String bundleError) {
        Toast.makeText(context, bundleError, Toast.LENGTH_SHORT).show();
    }

    private void initview(View view) {
        linearLayoutAD = (LinearLayout) view.findViewById(R.id.linearLayoutAD);
        linearLayoutHD = (LinearLayout) view.findViewById(R.id.linearLayoutHD);
        linearLayoutHM = (LinearLayout) view.findViewById(R.id.linearLayoutHM);
        linearLayoutAM = (LinearLayout) view.findViewById(R.id.linearLayoutAM);
        linearLayoutAF = (LinearLayout) view.findViewById(R.id.linearLayoutAF);
        linearLayoutHF = (LinearLayout) view.findViewById(R.id.linearLayoutHF);
        linearLayoutError = (LinearLayout) view.findViewById(R.id.linearLayoutError);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        textViewError = (TextView) view.findViewById(R.id.textViewError);
        textViewAGK = (TextView) view.findViewById(R.id.textViewGKA);
        textViewHGK = (TextView) view.findViewById(R.id.textViewGKH);
        imageViewHGK = (ImageView) view.findViewById(R.id.imageViewGKH);
        imageViewAGK = (ImageView) view.findViewById(R.id.imageViewGKA);

    }

    private void setupDummyContent() {
//        LinearLayout.LayoutParams paramHD = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (int i = 0; i < 5; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setText("");
            LayoutParams paramsTv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(paramsImageView);
            Picasso.with(context).load(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutHD.addView(linearLayout);

        }

        for (int i = 0; i < 3; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setText("mid" + i);
            LayoutParams paramsTv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(paramsImageView);
            Picasso.with(context).load(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutHM.addView(linearLayout);

        }
        for (int i = 0; i < 2; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setText("forword" + i);
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            LayoutParams paramsTv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(paramsImageView);
            Picasso.with(context).load(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutHF.addView(linearLayout);

        }


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
                    linearLayoutError.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                    ex.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                linearLayoutError.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                error.printStackTrace();
            }
        };
    }

    private void initLineUp(ArrayList<LineUPData> lineUPDatasHome, ArrayList<LineUPData> lineUPDatasAway) {
        ArrayList<LineUPData> arrayListHomeDefender = new ArrayList<>();
        ArrayList<LineUPData> arrayListAwayDefender = new ArrayList<>();
        ArrayList<LineUPData> arrayListHomeMid = new ArrayList<>();
        ArrayList<LineUPData> arrayListAwayMid = new ArrayList<>();
        ArrayList<LineUPData> arrayListHomeForward = new ArrayList<>();
        ArrayList<LineUPData> arrayListAwayForward = new ArrayList<>();

        for (int i = 0; i < lineUPDatasHome.size(); i++) {
            switch (lineUPDatasHome.get(i).position) {
                case "Goalkeeper":
                    textViewHGK.setText(lineUPDatasHome.get(i).name);
                    if (homeTeamLogo.length() > 0) {
                        Picasso.with(context)
                                .load(homeTeamLogo)
                                .placeholder(R.drawable.ic_holder)
                                .error(R.drawable.ic_error)
                                .into(imageViewHGK);
                    } else {
                        imageViewHGK.setImageResource(R.drawable.ic_error);
                    }
                    break;
                case "Defender":
                case "CD-L":
                case "CD-R":
                case "LB":
                case "RB":
                case "CD":
                    arrayListHomeDefender.add(lineUPDatasHome.get(i));
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
                    break;
                case "Forward":
                case "LF":
                case "RF":
                case "CF-L":
                case "CF-R":

                    arrayListHomeForward.add(lineUPDatasHome.get(i));
                    break;
            }
        }
        for (int i = 0; i < lineUPDatasAway.size(); i++) {
            switch (lineUPDatasAway.get(i).position) {
                case "Goalkeeper":
                    textViewAGK.setText(lineUPDatasAway.get(i).name);
                    if (homeTeamLogo.length() > 0) {
                        Picasso.with(context)
                                .load(awayTeamLogo)
                                .placeholder(R.drawable.ic_holder)
                                .error(R.drawable.ic_error)
                                .into(imageViewAGK);
                    } else {
                        imageViewAGK.setImageResource(R.drawable.ic_error);
                    }
                    break;
                case "Defender":
                case "CD-L":
                case "CD-R":
                case "LB":
                case "RB":
                case "CD":
                    arrayListAwayDefender.add(lineUPDatasAway.get(i));
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
                    break;
                case "Forward":
                case "LF":
                case "RF":
                case "CF-L":
                case "CF-R":
                    arrayListAwayForward.add(lineUPDatasAway.get(i));
                    break;
            }
        }

        setupHomeDefender(arrayListHomeDefender);
        setupHomeMid(arrayListHomeMid);
        setupHomeForward(arrayListHomeForward);
        setupAwayDefender(arrayListAwayDefender);
        setupAwayMid(arrayListAwayMid);
        setupAwayForward(arrayListAwayForward);
    }

    private void setupHomeMid(ArrayList<LineUPData> arrayListHomeMid) {
        int size = arrayListHomeMid.size();
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setText(arrayListHomeMid.get(i).name);
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);
            tv.setTextSize(14);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            LayoutParams paramsTv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(paramsImageView);
            Picasso.with(context).load(homeTeamLogo).error(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutHM.addView(linearLayout);
        }
    }

    private void setupHomeForward(ArrayList<LineUPData> arrayListHomeForward) {
        int size = arrayListHomeForward.size();
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setText(arrayListHomeForward.get(i).name);
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);
            tv.setTextSize(14);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            LayoutParams paramsTv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(paramsImageView);
            Picasso.with(context).load(homeTeamLogo).error(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutHF.addView(linearLayout);
        }
    }

    private void setupAwayDefender(ArrayList<LineUPData> arrayListAwayDefender) {
        int size = arrayListAwayDefender.size();
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);
            tv.setTextSize(14);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            tv.setText(arrayListAwayDefender.get(i).name);
            LayoutParams paramsTv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(paramsImageView);
            Picasso.with(context).load(awayTeamLogo).error(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutAD.addView(linearLayout);

        }
    }

    private void setupAwayMid(ArrayList<LineUPData> arrayListAwayMid) {
        int size = arrayListAwayMid.size();
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);
            tv.setTextSize(14);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            tv.setText(arrayListAwayMid.get(i).name);
            LayoutParams paramsTv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(paramsImageView);
            Picasso.with(context).load(awayTeamLogo).error(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutAM.addView(linearLayout);

        }

    }

    private void setupAwayForward(ArrayList<LineUPData> arrayListAwayForward) {
        int size = arrayListAwayForward.size();
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);
            tv.setTextSize(14);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            tv.setText(arrayListAwayForward.get(i).name);
            LayoutParams paramsTv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(paramsImageView);
            Picasso.with(context).load(awayTeamLogo).error(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutAF.addView(linearLayout);

        }

    }

    private void setupHomeDefender(ArrayList<LineUPData> arrayListHomeDefender) {
        int size = arrayListHomeDefender.size();
        for (int i = 0; i < size; i++) {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);
            tv.setTextSize(14);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            tv.setText(arrayListHomeDefender.get(i).name);
            LayoutParams paramsTv = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(paramsImageView);
            Picasso.with(context).load(homeTeamLogo).error(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutHD.addView(linearLayout);

        }

    }
}
