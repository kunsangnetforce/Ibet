package com.netforceinfotech.ibet.profilesetting.selectteam;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Cancellable;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClientMiddleware;
import com.koushikdutta.ion.Ion;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.profilesetting.selectteam.listofteam.TeamListAdapter;
import com.netforceinfotech.ibet.profilesetting.selectteam.listofteam.TeamListData;
import com.netforceinfotech.ibet.profilesetting.selectteam.selectedteam.SelectTeamAdapter;

import java.util.ArrayList;

public class SelectTeamActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    ArrayList<TeamListData> teamListDatas = new ArrayList<>();
    private Toolbar toolbar;
    public static TeamListAdapter upcomingGameAdapter;
    LinearLayout linearLayoutProgress;
    public static LinearLayout linearlayoutMain, linearLayoutSelectedTeams, linearLayoutTeams;
    EditText editTextSearch;
    public static ArrayList<TeamListData> selectTeamDatas = new ArrayList<>();
    public static SelectTeamAdapter selectTeamAdapter;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_team);
        findViewById(R.id.buttonDone).setOnClickListener(this);
        linearLayoutProgress = (LinearLayout) findViewById(R.id.linearLayoutProgres);
        linearlayoutMain = (LinearLayout) findViewById(R.id.linearLayoutMain);
        linearLayoutSelectedTeams = (LinearLayout) findViewById(R.id.linearLayoutSelectedTeams);
        linearLayoutTeams = (LinearLayout) findViewById(R.id.linearLayoutTeams);
        context = this;
        setSearchView();
        setupToolBar("Select Team");
        setupSelectedRecyler();
        setupRecycler();
        getTeams();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    private void setSearchView() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                linearLayoutTeams.setVisibility(View.GONE);
                linearLayoutProgress.setVisibility(View.VISIBLE);
                getTeam(query);
                searchView.setHint(query);
                View view = SelectTeamActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                if (newText.length() > 2) {
                    linearLayoutTeams.setVisibility(View.GONE);
                    linearLayoutProgress.setVisibility(View.VISIBLE);
                    getTeam(newText);
                }

                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                linearLayoutTeams.setVisibility(View.GONE);
                linearLayoutProgress.setVisibility(View.VISIBLE);
                getTeams();
                teamListDatas.clear();
                upcomingGameAdapter.notifyDataSetChanged();

            }
        });
    }

    private void getTeam(String string) {
        String url = getResources().getString(R.string.urlsearch);
        url = url + "?sstr=" + string;
        Log.i("result url", url);
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        linearLayoutProgress.setVisibility(View.GONE);
                        linearlayoutMain.setVisibility(View.VISIBLE);
                        linearLayoutTeams.setVisibility(View.VISIBLE);
                        teamListDatas.clear();
                        upcomingGameAdapter.notifyDataSetChanged();
                        if (result == null) {
                            showMessage("nothings is here");
                        } else {
                            Log.i("kunsang_test_login", result.toString());
                            String status = result.get("status").getAsString().toLowerCase();
                            if (status.equalsIgnoreCase("success")) {
                                JsonArray data = result.getAsJsonArray("data");
                                for (int i = 0; i < data.size(); i++) {
                                    JsonObject object = data.get(i).getAsJsonObject();
                                    String id = object.get("id").getAsString();
                                    String name = object.get("name").getAsString();
                                    String logo = object.get("logo").getAsString();
                                    teamListDatas.add(new TeamListData(id, name, logo));
                                }
                                upcomingGameAdapter.notifyDataSetChanged();
                            } else {
                                showMessage("Something's wrong");
                            }
                        }

                    }
                });
    }

    private void setupSelectedRecyler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerSelected);
        selectTeamAdapter = new SelectTeamAdapter(context, selectTeamDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(selectTeamAdapter);
    }

    private void getTeams() {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=team_list
        String url = getResources().getString(R.string.url);
        url = url + "?opt=team_list";
        Log.i("result url", url);
        setHeader();
        Ion.with(context)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        linearLayoutProgress.setVisibility(View.GONE);
                        linearlayoutMain.setVisibility(View.VISIBLE);
                        linearLayoutTeams.setVisibility(View.VISIBLE);
                        teamListDatas.clear();
                        upcomingGameAdapter.notifyDataSetChanged();
                        if (result == null) {
                            showMessage("nothings is here");
                        } else {
                            Log.i("kunsang_test_login", result.toString());
                            String status = result.get("status").getAsString().toLowerCase();
                            if (status.equalsIgnoreCase("success")) {
                                JsonArray data = result.getAsJsonArray("data");
                                for (int i = 0; i < data.size(); i++) {
                                    JsonObject object = data.get(i).getAsJsonObject();
                                    String id = object.get("id").getAsString();
                                    String name = object.get("name").getAsString();
                                    String logo = object.get("logo").getAsString();
                                    teamListDatas.add(new TeamListData(id, name, logo));
                                }
                                upcomingGameAdapter.notifyDataSetChanged();
                            } else {
                                showMessage("Something's wrong");
                            }
                        }

                    }
                });
    }

    private void showMessage(String s) {
        Toast.makeText(SelectTeamActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);

    }


    private void setupRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        upcomingGameAdapter = new TeamListAdapter(context, teamListDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(upcomingGameAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDone:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
        }
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
