package com.netforceinfotech.ibet.currentbet.betarena.stats.lineup;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.Debugger.Debugger;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    int height, width;

    public LineupFragmentNew() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.lineup_fragment_new, container, false);
        context = getActivity();
        height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());
        width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());
        //setupDummyContent();
        initview(view);
        String match_id;
        try {
            match_id = this.getArguments().getString("match_id");
            //  match_id = "691136";
            getLineUp(match_id);
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
        // url = url + "/events_by_match_id.php?match_id=" + match_id + "&home_team_id=" + home_id + "&away_team_id=" + away_id;
        // url = url + "/events_by_match_id.php?match_id=" + "736799" + "&home_team_id=" + "6722" + "&away_team_id=" + "6724";
        Debugger.i("kunsang_url_lineup", url);

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
                                linearLayoutError.setVisibility(View.VISIBLE);
                                scrollView.setVisibility(View.GONE);
                                ex.printStackTrace();
                            }
                        }
                    }
                });
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
                        Glide.with(context)
                                .fromResource()
                                .asBitmap()
                                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                                .load(R.drawable.home_logo)
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
                case "A":

                    arrayListHomeForward.add(lineUPDatasHome.get(i));
                    break;
            }
        }
        for (int i = 0; i < lineUPDatasAway.size(); i++) {
            switch (lineUPDatasAway.get(i).position) {
                case "Goalkeeper":
                    textViewAGK.setText(lineUPDatasAway.get(i).name);
                    if (homeTeamLogo.length() > 0) {
                        Glide.with(context)
                                .fromResource()
                                .asBitmap()
                                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                                .load(R.drawable.away_logo)
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
                case "A":
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
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setText(arrayListHomeMid.get(i).name);
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);
            tv.setTextSize(12);
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            LayoutParams paramsTv = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(width, height);
            imageView.setLayoutParams(paramsImageView);
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.home_logo).error(R.drawable.ic_error).into(imageView);
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
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setText(arrayListHomeForward.get(i).name);
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);

            tv.setTextSize(12);
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            LayoutParams paramsTv = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(width, height);
            imageView.setLayoutParams(paramsImageView);
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.home_logo).error(R.drawable.ic_error).into(imageView);
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
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);

            tv.setTextSize(12);
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            tv.setText(arrayListAwayDefender.get(i).name);
            LayoutParams paramsTv = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(width, height);
            imageView.setLayoutParams(paramsImageView);
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo).error(R.drawable.ic_error).into(imageView);
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
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);

            tv.setTextSize(12);
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            tv.setText(arrayListAwayMid.get(i).name);
            LayoutParams paramsTv = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(width, height);
            imageView.setLayoutParams(paramsImageView);
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo).error(R.drawable.ic_error).into(imageView);
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
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);

            tv.setTextSize(12);
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            tv.setText(arrayListAwayForward.get(i).name);
            LayoutParams paramsTv = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(width, height);
            imageView.setLayoutParams(paramsImageView);
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.away_logo).error(R.drawable.ic_error).into(imageView);
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
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 120, 1f);
            linearLayout.setLayoutParams(params);
            linearLayout.setGravity(Gravity.CENTER);

            TextView tv = new TextView(getActivity());
            tv.setTextColor(ContextCompat.getColor(context, R.color.white));
            tv.setMaxLines(1);

            tv.setTextSize(12);
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setEllipsize(TextUtils.TruncateAt.END);

            tv.setText(arrayListHomeDefender.get(i).name);
            LayoutParams paramsTv = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(paramsTv);

            ImageView imageView = new ImageView(getActivity());
            LayoutParams paramsImageView = new LayoutParams(width, height);
            imageView.setLayoutParams(paramsImageView);
            Glide.with(context)
                    .fromResource()
                    .asBitmap()
                    .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                    .load(R.drawable.home_logo).error(R.drawable.ic_error).into(imageView);
            linearLayout.addView(tv);
            linearLayout.addView(imageView);
            linearLayoutHD.addView(linearLayout);

        }

    }
}
