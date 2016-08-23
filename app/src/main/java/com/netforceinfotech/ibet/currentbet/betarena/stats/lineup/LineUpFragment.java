package com.netforceinfotech.ibet.currentbet.betarena.stats.lineup;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.currentbet.betarena.live_event.events.EventsData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineUpFragment extends Fragment {
    ImageView imageViewGKa, imageViewSWa, imageViewRBa, imageViewRCBa, imageViewLCBa, imageViewLBa, imageViewLWBa, imageViewDMa, imageViewRWBa, imageViewRCMa, imageViewLCMa, imageViewAMa, imageViewLWa, imageViewRWa, imageViewRWFa, imageViewLWFa, imageViewCFa;
    ImageView imageViewGKh, imageViewSWh, imageViewRBh, imageViewRCBh, imageViewLCBh, imageViewLBh, imageViewLWBh, imageViewDMh, imageViewRWBh, imageViewRCMh, imageViewLCMh, imageViewAMh, imageViewLWh, imageViewRWh, imageViewRWFh, imageViewLWFh, imageViewCFh;
    TextView textViewGKa, textViewSWa, textViewRBa, textViewRCBa, textViewLCBa, textViewLBa, textViewLWBa, textViewDMa, textViewRWBa, textViewRCMa, textViewLCMa, textViewAMa, textViewLWa, textViewRWa, textViewRWFa, textViewLWFa, textViewCFa;
    TextView textViewGKh, textViewSWh, textViewRBh, textViewRCBh, textViewLCBh, textViewLBh, textViewLWBh, textViewDMh, textViewRWBh, textViewRCMh, textViewLCMh, textViewAMh, textViewLWh, textViewRWh, textViewRWFh, textViewLWFh, textViewCFh;

    Context context;
    ArrayList<LineUPData> lineUPDatasHome = new ArrayList<>();
    ArrayList<LineUPData> lineUPDatasAway = new ArrayList<>();
    private TextDrawable drawable;

    public LineUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_line_up3, container, false);
        context = getActivity();
        initview(view);
        getLineUp();
        setDummyLineUp();
        return view;
    }

    private void getLineUp() {
        //https://api.soccerama.pro/v1.1/matches/738592?api_token=$api_token&include=lineup,homeTeam,awayTeam
        String url = getResources().getString(R.string.url);
        // url = url + "/events_by_match_id.php?matchid=" + matchid + "&home_team_id=" + teamaid + "&away_team_id=" + teambid;
        // url = url + "/events_by_match_id.php?matchid=" + "736799" + "&home_team_id=" + "6722" + "&away_team_id=" + "6724";
        Log.i("result url", url);
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                    }
                });
    }

    private void setDummyLineUp() {
        lineUPDatasHome.add(new LineUPData("GK", "1", "Lopez"));
        lineUPDatasHome.add(new LineUPData("RB", "2", "Carvajal"));
        lineUPDatasHome.add(new LineUPData("RCB", "3", "Ramos"));
        lineUPDatasHome.add(new LineUPData("LCB", "4", "Pepe"));
        lineUPDatasHome.add(new LineUPData("LB", "5", "Marcelo"));
        lineUPDatasHome.add(new LineUPData("LCM", "6", "Modric"));
        lineUPDatasHome.add(new LineUPData("RCM", "8", "Kroos"));
        lineUPDatasHome.add(new LineUPData("AM", "40", "Rodriguez"));
        lineUPDatasHome.add(new LineUPData("LW", "7", "Ronaldo"));
        lineUPDatasHome.add(new LineUPData("RW", "11", "Bale"));
        lineUPDatasHome.add(new LineUPData("CF", "9", "Benzema"));

        lineUPDatasAway.add(new LineUPData("GK", "1", "V.Valdes"));
        lineUPDatasAway.add(new LineUPData("RB", "2", "Alves"));
        lineUPDatasAway.add(new LineUPData("RCB", "3", "Masherano"));
        lineUPDatasAway.add(new LineUPData("LCB", "4", "Pique"));
        lineUPDatasAway.add(new LineUPData("LB", "5", "Alba"));
        lineUPDatasAway.add(new LineUPData("LCM", "6", "Iniesta"));
        lineUPDatasAway.add(new LineUPData("RCM", "8", "Fabregas"));
        lineUPDatasAway.add(new LineUPData("AM", "40", "Busquests"));
        lineUPDatasAway.add(new LineUPData("LW", "7", "Sanches"));
        lineUPDatasAway.add(new LineUPData("RW", "11", "Neymar"));
        lineUPDatasAway.add(new LineUPData("CF", "9", "Messi"));

        initPlayer();
    }

    private void initPlayer() {
        initHome();
        initAway();
    }

    private void initAway() {
        for (int i = 0; i < lineUPDatasAway.size(); i++) {
            switch (lineUPDatasAway.get(i).position) {
                case "GK":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewGKa.setImageDrawable(drawable);
                    textViewGKa.setText(lineUPDatasAway.get(i).name);
                    textViewGKa.setVisibility(View.VISIBLE);
                    imageViewGKa.setVisibility(View.VISIBLE);
                    break;
                case "SW":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewSWa.setImageDrawable(drawable);
                    textViewSWa.setText(lineUPDatasAway.get(i).name);
                    textViewSWa.setVisibility(View.VISIBLE);
                    imageViewSWa.setVisibility(View.VISIBLE);
                    break;
                case "RB":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewRBa.setImageDrawable(drawable);
                    textViewRBa.setText(lineUPDatasAway.get(i).name);
                    textViewRBa.setVisibility(View.VISIBLE);
                    imageViewRBa.setVisibility(View.VISIBLE);
                    break;
                case "LCB":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewLCBa.setImageDrawable(drawable);
                    textViewLCBa.setText(lineUPDatasAway.get(i).name);
                    textViewLCBa.setVisibility(View.VISIBLE);
                    imageViewLCBa.setVisibility(View.VISIBLE);
                    break;
                case "RCB":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewRCBa.setImageDrawable(drawable);
                    textViewRCBa.setText(lineUPDatasAway.get(i).name);
                    textViewRCBa.setVisibility(View.VISIBLE);
                    imageViewRCBa.setVisibility(View.VISIBLE);
                    break;
                case "LB":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewLBa.setImageDrawable(drawable);
                    textViewLBa.setText(lineUPDatasAway.get(i).name);
                    textViewLBa.setVisibility(View.VISIBLE);
                    imageViewLBa.setVisibility(View.VISIBLE);
                    break;
                case "LWB":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewLWBa.setImageDrawable(drawable);
                    textViewLWBa.setText(lineUPDatasAway.get(i).name);
                    textViewLWBa.setVisibility(View.VISIBLE);
                    imageViewLWBa.setVisibility(View.VISIBLE);
                    break;
                case "DM":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewDMa.setImageDrawable(drawable);
                    textViewDMa.setText(lineUPDatasAway.get(i).name);
                    textViewDMa.setVisibility(View.VISIBLE);
                    imageViewDMa.setVisibility(View.VISIBLE);
                    break;
                case "RWB":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewRWBa.setImageDrawable(drawable);
                    textViewRWBa.setText(lineUPDatasAway.get(i).name);
                    textViewRWBa.setVisibility(View.VISIBLE);
                    imageViewRWBa.setVisibility(View.VISIBLE);
                    break;
                case "LCM":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewLCMa.setImageDrawable(drawable);
                    textViewLCMa.setText(lineUPDatasAway.get(i).name);
                    textViewLCMa.setVisibility(View.VISIBLE);
                    imageViewLCMa.setVisibility(View.VISIBLE);
                    break;
                case "RCM":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewRCMa.setImageDrawable(drawable);
                    textViewRCMa.setText(lineUPDatasAway.get(i).name);
                    textViewRCMa.setVisibility(View.VISIBLE);
                    imageViewRCMa.setVisibility(View.VISIBLE);
                    break;
                case "AM":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewAMa.setImageDrawable(drawable);
                    textViewAMa.setText(lineUPDatasAway.get(i).name);
                    textViewAMa.setVisibility(View.VISIBLE);
                    imageViewAMa.setVisibility(View.VISIBLE);
                    break;
                case "LW":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewLWa.setImageDrawable(drawable);
                    textViewLWa.setText(lineUPDatasAway.get(i).name);
                    textViewLWa.setVisibility(View.VISIBLE);
                    imageViewLWa.setVisibility(View.VISIBLE);
                    break;
                case "RW":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewRWa.setImageDrawable(drawable);
                    textViewRWa.setText(lineUPDatasAway.get(i).name);
                    textViewRWa.setVisibility(View.VISIBLE);
                    imageViewRWa.setVisibility(View.VISIBLE);
                    break;
                case "RWF":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewRWFa.setImageDrawable(drawable);
                    textViewGKa.setText(lineUPDatasAway.get(i).name);
                    textViewRWFa.setVisibility(View.VISIBLE);
                    imageViewRWFa.setVisibility(View.VISIBLE);
                    break;
                case "LWF":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewLWFa.setImageDrawable(drawable);
                    textViewLWFa.setText(lineUPDatasAway.get(i).name);
                    textViewLWFa.setVisibility(View.VISIBLE);
                    imageViewLWFa.setVisibility(View.VISIBLE);
                    break;
                case "CF":
                    drawable = TextDrawable.builder()
                            .buildRoundRect(lineUPDatasAway.get(i).number, Color.parseColor("#FF5A2E"), 40);
                    imageViewCFa.setImageDrawable(drawable);
                    textViewCFa.setText(lineUPDatasAway.get(i).name);
                    textViewCFa.setVisibility(View.VISIBLE);
                    imageViewCFa.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void initHome() {
        for (int i = 0; i < lineUPDatasHome.size(); i++) {
            switch (lineUPDatasHome.get(i).position) {
                case "GK":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewGKh.setImageDrawable(drawable);
                    textViewGKh.setText(lineUPDatasHome.get(i).name);
                    textViewGKh.setVisibility(View.VISIBLE);
                    imageViewGKh.setVisibility(View.VISIBLE);
                    break;
                case "SW":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewSWh.setImageDrawable(drawable);
                    textViewSWh.setText(lineUPDatasHome.get(i).name);
                    textViewSWh.setVisibility(View.VISIBLE);
                    imageViewSWh.setVisibility(View.VISIBLE);
                    break;
                case "RB":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewRBh.setImageDrawable(drawable);
                    textViewRBh.setText(lineUPDatasHome.get(i).name);
                    textViewRBh.setVisibility(View.VISIBLE);
                    imageViewRBh.setVisibility(View.VISIBLE);
                    break;
                case "LCB":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewLCBh.setImageDrawable(drawable);
                    textViewLCBh.setText(lineUPDatasHome.get(i).name);
                    textViewLCBh.setVisibility(View.VISIBLE);
                    imageViewLCBh.setVisibility(View.VISIBLE);
                    break;
                case "RCB":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewRCBh.setImageDrawable(drawable);
                    textViewRCBh.setText(lineUPDatasHome.get(i).name);
                    textViewRCBh.setVisibility(View.VISIBLE);
                    imageViewRCBh.setVisibility(View.VISIBLE);
                    break;
                case "LB":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewLBh.setImageDrawable(drawable);
                    textViewLBh.setText(lineUPDatasHome.get(i).name);
                    textViewLBh.setVisibility(View.VISIBLE);
                    imageViewLBh.setVisibility(View.VISIBLE);
                    break;
                case "LWB":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewLWBh.setImageDrawable(drawable);
                    textViewLWBh.setText(lineUPDatasHome.get(i).name);
                    textViewLWBh.setVisibility(View.VISIBLE);
                    imageViewLWBh.setVisibility(View.VISIBLE);
                    break;
                case "DM":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewDMh.setImageDrawable(drawable);
                    textViewDMh.setText(lineUPDatasHome.get(i).name);
                    textViewDMh.setVisibility(View.VISIBLE);
                    imageViewDMh.setVisibility(View.VISIBLE);
                    break;
                case "RWB":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewRWBh.setImageDrawable(drawable);
                    textViewRWBh.setText(lineUPDatasHome.get(i).name);
                    textViewRWBh.setVisibility(View.VISIBLE);
                    imageViewRWBh.setVisibility(View.VISIBLE);
                    break;
                case "LCM":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewLCMh.setImageDrawable(drawable);
                    textViewLCMh.setText(lineUPDatasHome.get(i).name);
                    textViewLCMh.setVisibility(View.VISIBLE);
                    imageViewLCMh.setVisibility(View.VISIBLE);
                    break;
                case "RCM":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewRCMh.setImageDrawable(drawable);
                    textViewRCMh.setText(lineUPDatasHome.get(i).name);
                    textViewRCMh.setVisibility(View.VISIBLE);
                    imageViewRCMh.setVisibility(View.VISIBLE);
                    break;
                case "AM":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewAMh.setImageDrawable(drawable);
                    textViewAMh.setText(lineUPDatasHome.get(i).name);
                    textViewAMh.setVisibility(View.VISIBLE);
                    imageViewAMh.setVisibility(View.VISIBLE);
                    break;
                case "LW":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewLWh.setImageDrawable(drawable);
                    textViewLWh.setText(lineUPDatasHome.get(i).name);
                    textViewLWh.setVisibility(View.VISIBLE);
                    imageViewLWh.setVisibility(View.VISIBLE);
                    break;
                case "RW":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewRWh.setImageDrawable(drawable);
                    textViewRWh.setText(lineUPDatasHome.get(i).name);
                    textViewRWh.setVisibility(View.VISIBLE);
                    imageViewRWh.setVisibility(View.VISIBLE);
                    break;
                case "RWF":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewRWFh.setImageDrawable(drawable);
                    textViewGKh.setText(lineUPDatasHome.get(i).name);
                    textViewRWFh.setVisibility(View.VISIBLE);
                    imageViewRWFh.setVisibility(View.VISIBLE);
                    break;
                case "LWF":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewLWFh.setImageDrawable(drawable);
                    textViewLWFh.setText(lineUPDatasHome.get(i).name);
                    textViewLWFh.setVisibility(View.VISIBLE);
                    imageViewLWFh.setVisibility(View.VISIBLE);
                    break;
                case "CF":
                    drawable = TextDrawable.builder().beginConfig().textColor(Color.BLACK).endConfig()
                            .buildRoundRect(lineUPDatasHome.get(i).number, Color.parseColor("#ffffff"), 40);
                    imageViewCFh.setImageDrawable(drawable);
                    textViewCFh.setText(lineUPDatasHome.get(i).name);
                    textViewCFh.setVisibility(View.VISIBLE);
                    imageViewCFh.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void initview(View view) {
        setupRoundImage(view);
        setupTextView(view);
    }

    private void setupTextView(View view) {
        textViewGKa = (TextView) view.findViewById(R.id.textViewGKa);
        textViewSWa = (TextView) view.findViewById(R.id.textViewSWa);
        textViewRBa = (TextView) view.findViewById(R.id.textViewRBa);
        textViewRCBa = (TextView) view.findViewById(R.id.textViewRCBa);
        textViewLCBa = (TextView) view.findViewById(R.id.textViewLCBa);
        textViewLBa = (TextView) view.findViewById(R.id.textViewLBa);
        textViewLWBa = (TextView) view.findViewById(R.id.textViewLWBa);
        textViewDMa = (TextView) view.findViewById(R.id.textViewDMa);
        textViewRWBa = (TextView) view.findViewById(R.id.textViewRWBa);
        textViewLCMa = (TextView) view.findViewById(R.id.textViewLCMa);
        textViewRCMa = (TextView) view.findViewById(R.id.textViewRCMa);
        textViewAMa = (TextView) view.findViewById(R.id.textViewAMa);
        textViewLWa = (TextView) view.findViewById(R.id.textViewLWa);
        textViewRWa = (TextView) view.findViewById(R.id.textViewRWa);
        textViewRWFa = (TextView) view.findViewById(R.id.textViewRWFa);
        textViewLWFa = (TextView) view.findViewById(R.id.textViewLWFa);
        textViewCFa = (TextView) view.findViewById(R.id.textViewCFa);


        textViewGKh = (TextView) view.findViewById(R.id.textViewGKh);
        textViewSWh = (TextView) view.findViewById(R.id.textViewSWh);
        textViewRBh = (TextView) view.findViewById(R.id.textViewRBh);
        textViewRCBh = (TextView) view.findViewById(R.id.textViewRCBh);
        textViewLCBh = (TextView) view.findViewById(R.id.textViewLCBh);
        textViewLBh = (TextView) view.findViewById(R.id.textViewLBh);
        textViewLWBh = (TextView) view.findViewById(R.id.textViewLWBh);
        textViewDMh = (TextView) view.findViewById(R.id.textViewDMh);
        textViewRWBh = (TextView) view.findViewById(R.id.textViewRWBh);
        textViewLCMh = (TextView) view.findViewById(R.id.textViewLCMh);
        textViewRCMh = (TextView) view.findViewById(R.id.textViewRCMh);
        textViewAMh = (TextView) view.findViewById(R.id.textViewAMh);
        textViewLWh = (TextView) view.findViewById(R.id.textViewLWh);
        textViewRWh = (TextView) view.findViewById(R.id.textViewRWh);
        textViewRWFh = (TextView) view.findViewById(R.id.textViewRWFh);
        textViewLWFh = (TextView) view.findViewById(R.id.textViewLWFh);
        textViewCFh = (TextView) view.findViewById(R.id.textViewCFh);
    }

    private void setupRoundImage(View view) {
        imageViewGKa = (ImageView) view.findViewById(R.id.imageViewGKa);
        imageViewSWa = (ImageView) view.findViewById(R.id.imageViewSWa);
        imageViewRBa = (ImageView) view.findViewById(R.id.imageViewRBa);
        imageViewRCBa = (ImageView) view.findViewById(R.id.imageViewRCBa);
        imageViewLCBa = (ImageView) view.findViewById(R.id.imageViewLCBa);
        imageViewLBa = (ImageView) view.findViewById(R.id.imageViewLBa);
        imageViewLWBa = (ImageView) view.findViewById(R.id.imageViewLWBa);
        imageViewDMa = (ImageView) view.findViewById(R.id.imageViewDMa);
        imageViewRWBa = (ImageView) view.findViewById(R.id.imageViewRWBa);
        imageViewLCMa = (ImageView) view.findViewById(R.id.imageViewLCMa);
        imageViewRCMa = (ImageView) view.findViewById(R.id.imageViewRCMa);
        imageViewAMa = (ImageView) view.findViewById(R.id.imageViewAMa);
        imageViewLWa = (ImageView) view.findViewById(R.id.imageViewLWa);
        imageViewRWa = (ImageView) view.findViewById(R.id.imageViewRWa);
        imageViewRWFa = (ImageView) view.findViewById(R.id.imageViewRWFa);
        imageViewLWFa = (ImageView) view.findViewById(R.id.imageViewLWFa);
        imageViewCFa = (ImageView) view.findViewById(R.id.imageViewCFa);


        imageViewGKh = (ImageView) view.findViewById(R.id.imageViewGKh);
        imageViewSWh = (ImageView) view.findViewById(R.id.imageViewSWh);
        imageViewRBh = (ImageView) view.findViewById(R.id.imageViewRBh);
        imageViewRCBh = (ImageView) view.findViewById(R.id.imageViewRCBh);
        imageViewLCBh = (ImageView) view.findViewById(R.id.imageViewLCBh);
        imageViewLBh = (ImageView) view.findViewById(R.id.imageViewLBh);
        imageViewLWBh = (ImageView) view.findViewById(R.id.imageViewLWBh);
        imageViewDMh = (ImageView) view.findViewById(R.id.imageViewDMh);
        imageViewRWBh = (ImageView) view.findViewById(R.id.imageViewRWBh);
        imageViewLCMh = (ImageView) view.findViewById(R.id.imageViewLCMh);
        imageViewRCMh = (ImageView) view.findViewById(R.id.imageViewRCMh);
        imageViewAMh = (ImageView) view.findViewById(R.id.imageViewAMh);
        imageViewLWh = (ImageView) view.findViewById(R.id.imageViewLWh);
        imageViewRWh = (ImageView) view.findViewById(R.id.imageViewRWh);
        imageViewRWFh = (ImageView) view.findViewById(R.id.imageViewRWFh);
        imageViewLWFh = (ImageView) view.findViewById(R.id.imageViewLWFh);
        imageViewCFh = (ImageView) view.findViewById(R.id.imageViewCFh);

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
