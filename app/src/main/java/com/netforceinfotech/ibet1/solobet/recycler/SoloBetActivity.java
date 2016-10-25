package com.netforceinfotech.ibet1.solobet.recycler;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;

public class SoloBetActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Context context;
    ArrayList<SoloBetData> soloBetDatas = new ArrayList<>();
    private SoloBetAdapter adapter;
    String match_id;
    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_bet1);
        context = this;
        userSessionManager = new UserSessionManager(context);
        Bundle bundle = getIntent().getExtras();
        match_id = bundle.getString("match_id");
        setupRecyclerView();
        getOdds(match_id);
        initView();

    }

    private void getOdds(String match_id) {
        //https://api.soccerama.pro/v1.2/odds/match/{id}?api_token=__YOURTOKEN__
        String url = "https://api.soccerama.pro/v1.2/odds/match/" + match_id + "?api_token=" + userSessionManager.getApitoken();
        Debugger.i("kresult", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (result == null) {
                    showMessage("No result found");
                } else {
                    Debugger.i("kresult", result.toString());
                    setupOdds(result);
                }
            }
        });
    }

    private void setupOdds(JsonObject result) {
        try {
            soloBetDatas.clear();
        } catch (Exception ex) {

        }
        JsonArray data = result.getAsJsonArray("data");
        int size = data.size();
        if (size == 0) {
            showMessage("No odds for this match");
            return;
        }
        for (int i = 0; i < size; i++) {
            JsonObject jsonObject = data.get(i).getAsJsonObject();
            String bookmaker_id = jsonObject.get("bookmaker_id").getAsString();
            String name = jsonObject.get("name").getAsString();
            JsonObject types = jsonObject.get("types").getAsJsonObject();
            JsonArray data1 = types.getAsJsonArray("data");
            JsonObject jsonObject1 = data1.get(0).getAsJsonObject();
            String type = jsonObject1.get("type").getAsString();
            if (type.equalsIgnoreCase("1x2")) {
                JsonObject odds = jsonObject1.getAsJsonObject("odds");
                JsonArray data2 = odds.getAsJsonArray("data");
                JsonObject home = data2.get(0).getAsJsonObject();
                JsonObject draw = data2.get(1).getAsJsonObject();
                JsonObject away = data2.get(2).getAsJsonObject();
                String home_odds = home.get("value").getAsString();
                String away_odds = away.get("value").getAsString();
                String draw_odds = draw.get("value").getAsString();
                SoloBetData soloBetData = new SoloBetData(bookmaker_id, name, home_odds, away_odds, draw_odds);
                soloBetDatas.add(soloBetData);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void initView() {

    }

    private void setupRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new SoloBetAdapter(context, soloBetDatas);
        recyclerView.setAdapter(adapter);
    }
}
