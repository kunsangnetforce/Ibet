package com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.searchfriend;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.CreateBet;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.searchfriend.friend.SearchFriendAdapter;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.searchfriend.selectedfrind.SelectFriendAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SearchFriendActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialSearchView searchView;
    ArrayList<SearchFriendData> searchFriendDatas = new ArrayList<>();
    public static SearchFriendAdapter searchFriendAdapter;
    public static SelectFriendAdapter selectFriendAdapter;
    RecyclerView recyclerView, recyclerViewSelected;
    LinearLayout linearLayoutMain, linearLayoutNoContent;
    public static ArrayList<SearchFriendData> selectedDatas = new ArrayList<>();
    Context context;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        context = this;
        linearLayoutMain = (LinearLayout) findViewById(R.id.linearLayoutMain);
        linearLayoutNoContent = (LinearLayout) findViewById(R.id.linearLayoutNoContent);
        linearLayoutMain.setVisibility(View.GONE);
        linearLayoutNoContent.setVisibility(View.GONE);
        findViewById(R.id.buttonDone).setOnClickListener(this);
        setupToolBar("Search Friends");
        setupRecycler();
        setupRecyclerSelected();
        setSearchView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
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

    private void setupToolBar(String app_name) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(app_name);

    }


    private void setupRecyclerSelected() {
        recyclerViewSelected = (RecyclerView) findViewById(R.id.recyclerSelected);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSelected.setLayoutManager(linearLayoutManager);
        selectFriendAdapter = new SelectFriendAdapter(context, selectedDatas);
        recyclerViewSelected.setAdapter(selectFriendAdapter);
    }

    private void setupRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        searchFriendAdapter = new SearchFriendAdapter(context, searchFriendDatas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(searchFriendAdapter);
    }

    private void setSearchView() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                searchFriendDatas.clear();
                searchFriendAdapter.notifyDataSetChanged();
                getFriend(query);
                searchView.setHint(query);
                View view = SearchFriendActivity.this.getCurrentFocus();
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
                    searchFriendDatas.clear();
                    searchFriendAdapter.notifyDataSetChanged();
                    getFriend(newText);
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

            }
        });
    }

    private void getFriend(String query) {
        //https://netforcesales.com/ibet_admin/api/search_friend_by_name.php?sstr=XYZ
        String url = getResources().getString(R.string.url);
        url = url + "/search_friend_by_name.php?sstr=";
        String searchurl = query;
        try {
            searchurl = URLEncoder.encode(searchurl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            showMessage("url error");
        }
        Log.i("kunsangurl", url + searchurl);
        Ion.with(context).load(url + searchurl)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        searchFriendDatas.clear();
                        searchFriendAdapter.notifyDataSetChanged();
                        if (result == null) {
                            showMessage("nothings is here");
                        } else {
                            Log.i("kunsang_test_login", result.toString());
                            String status = result.get("status").getAsString().toLowerCase();
                            if (status.equalsIgnoreCase("success")) {
                                JsonArray data = result.getAsJsonArray("data");
                                if (data.size() == 0) {
                                    //show no result
                                    linearLayoutNoContent.setVisibility(View.VISIBLE);
                                    linearLayoutMain.setVisibility(View.GONE);
                                } else {
                                    linearLayoutMain.setVisibility(View.VISIBLE);
                                    linearLayoutNoContent.setVisibility(View.GONE);
                                }
                                for (int i = 0; i < data.size(); i++) {
                                    JsonObject object = data.get(i).getAsJsonObject();
                                    String id = object.get("id").getAsString();
                                    String name = object.get("name").getAsString();
                                    String profile_image = object.get("profile_image").getAsString();
                                    searchFriendDatas.add(new SearchFriendData(id, name, profile_image));

                                }
                                searchFriendAdapter.notifyDataSetChanged();
                            } else {
                                showMessage("Something's wrong");
                            }
                        }

                    }
                });
    }

    private void showMessage(String s) {
        Toast.makeText(SearchFriendActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonDone:
                CreateBet.frindids.clear();
                for (int i = 0; i < selectedDatas.size(); i++) {

                    if (!CreateBet.frindids.contains(selectedDatas.get(i))) {
                        CreateBet.frindids.add(selectedDatas.get(i));
                    }
                }
                if (selectedDatas.size() == 0) {
                    showMessage("No friend selected");
                }
                finish();
                break;
        }
    }
}
