package com.netforceinfotech.ibet.solobet.recycler;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet.Debugger.Debugger;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

public class SoloBetActivity extends AppCompatActivity {


    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Context context;
    ArrayList<SoloBetData> soloBetDatas = new ArrayList<>();
    private SoloBetAdapter adapter;
    String match_id, home_name, away_name, home_logo, away_logo;
    UserSessionManager userSessionManager;
    CoordinatorLayout coordinatorLayout;
    View view1;
    private MaterialDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_bet1);
        context = this;
        userSessionManager = new UserSessionManager(context);
        Bundle bundle = getIntent().getExtras();
        match_id = bundle.getString("match_id");

        home_name = bundle.getString("home_name");
        away_name = bundle.getString("away_name");
        home_logo = bundle.getString("home_logo");
        away_logo = bundle.getString("away_logo");
        setupRecyclerView();
        initView();
        getOdds(match_id);

        setupToolBar(home_name + " vs " + away_name);
        setupStatusBar();
        setupTheme();
        setupBackground();

    }

    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);

    }

    private void getOdds(String match_id) {
        progressDialog.show();
        //https://api.soccerama.pro/v1.2/odds/match/{id}?api_token=__YOURTOKEN__
        String url = "https://api.soccerama.pro/v1.2/odds/match/" + match_id + "?api_token=" + userSessionManager.getApitoken();
        Debugger.i("kunsang_url_getOdds", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                progressDialog.dismiss();
                if (result == null) {
                    showMessage("No result found");
                } else {
                    Debugger.i("kresult", result.toString());
                    setupOdds(result);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupOdds(JsonObject result) {
        try {
            soloBetDatas.clear();
        } catch (Exception ex) {

        }
        try {
            JsonArray data = result.getAsJsonArray("data");
            int size = data.size();
            if (size == 0) {
                showMessage(getString(R.string.no_odds));
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
        } catch (Exception ex) {
            showMessage(getString(R.string.no_odds));
        }


    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        progressDialog = new MaterialDialog.Builder(this)
                .title(R.string.progress_dialog)
                .content(R.string.please_wait)
                .progress(true, 0).build();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        view1 = findViewById(R.id.view);
    }

    private void setupRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new SoloBetAdapter(context, soloBetDatas, home_logo, away_logo, home_name, away_name, match_id);
        recyclerView.setAdapter(adapter);
    }

    private void setupStatusBar() {
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        switch (userSessionManager.getTheme()) {
            case 0:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkBrown));
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkPurple));
                }
                break;
            case 3:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkGreen));
                }
                break;
            case 4:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkMarron));
                }
                break;
            case 5:
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                    window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDarkLightBlue));
                }
                break;
        }

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
            case 5:
                setupLightBlueTheme();
                break;
        }
    }

    private void setupBrownTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));

    }

    private void setupPurlpleTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setupGreenTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setupMarronTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));


    }

    private void setupLightBlueTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));


    }

    private void setupDefaultTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

    }
}
