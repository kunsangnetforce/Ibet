package com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.CreateBet;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.friend.SearchFriendAdapter;
import com.netforceinfotech.ibet1.dashboard.home.startnewbet.create_bet.searchfriend.selectedfrind.SelectFriendAdapter;
import com.netforceinfotech.ibet1.general.UserSessionManager;

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
    CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    Button buttonDone;
    View view1;
    UserSessionManager userSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        context = this;
        userSessionManager = new UserSessionManager(context);
        setupStatusBar();
        initView();
        setupToolBar("Search Friends");
        setupRecycler();
        setupRecyclerSelected();
        setSearchView();
        setupTheme();
        setupBackbround();
    }

    private void initView() {
        view1 = findViewById(R.id.view);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        buttonDone = (Button) findViewById(R.id.buttonDone);
        linearLayoutMain = (LinearLayout) findViewById(R.id.linearLayoutMain);
        linearLayoutNoContent = (LinearLayout) findViewById(R.id.linearLayoutNoContent);
        linearLayoutMain.setVisibility(View.GONE);
        linearLayoutNoContent.setVisibility(View.GONE);
        buttonDone.setOnClickListener(this);
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

    private void setupBackbround() {

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
                break;
            case 5:
                setupLightBlueTheme();
                break;
        }
    }

    private void setupBrownTheme() {
        buttonDone.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));

    }

    private void setupPurlpleTheme() {
        buttonDone.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));

    }

    private void setupGreenTheme() {

        buttonDone.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));

    }

    private void setupMarronTheme() {
        buttonDone.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        buttonDone.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
    }

    private void setupDefaultTheme() {
        buttonDone.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
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
}
