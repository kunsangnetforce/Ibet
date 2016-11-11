package com.netforceinfotech.ibet1.dashboard.setting.notification.teamNotification;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.setting.notification.teamNotification.teamlist.TeamData;
import com.netforceinfotech.ibet1.dashboard.setting.notification.teamNotification.teamlist.TeamlistActivity;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;

public class TeamNotificationActivity extends AppCompatActivity {

    Context context;
    CoordinatorLayout coordinatorLayout;
    View view1;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TeamNotificationAdapter adapter;
    public ArrayList<TeamData> teamDatas = new ArrayList<>();
    ArrayList<Integer> ic_sound_list = new ArrayList<Integer>();
    private Toolbar toolbar;
    Button buttonAddmore;
    private UserSessionManager userSessionManager;
    RelativeLayout relativeLayoutHeader;
    Window window;
    CheckBox radioButton;
    ImageView imageViewMute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_notification);
        context = this;
        userSessionManager = new UserSessionManager(context);
        setupStatusBar();
        initView();
        setupToolBar("Team Notification");
        setupTheme();
        setupBackground();
        setupRecyclerView();
        getFavTeam();
    }

    private void getFavTeam() {
        //https://netforcesales.com/ibet_admin/api/services.php?opt=get_fav_team_top&user_id=25
        String baseUrl = getString(R.string.url);
        String url = baseUrl + "/services.php?opt=get_fav_team_top&user_id" + userSessionManager.getCustomerId();
        Debugger.i("kunsang_fav_team", url);
        Ion.with(context).load(url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (result == null) {
                    showMessage("Something went wrong");
                } else {
                    setFavTeam(result);
                }
            }
        });
    }

    private void setFavTeam(JsonObject result) {
        Debugger.i("kresult", result.toString());
        /* teamDatas.add(new TeamData("1", "Man U", "http://man.png"));
        teamDatas.add(new TeamData("2", "Man City", "http://man.png"));
        teamDatas.add(new TeamData("3", "Barca", "http://man.png"));
        teamDatas.add(new TeamData("4", "Inter Minlan", "http://man.png"));

        * */
        try {
            teamDatas.clear();
        } catch (Exception ex) {

        }
        if (result.get("status").getAsString().equalsIgnoreCase("success")) {
            JsonArray data = result.getAsJsonArray("data");
            int size = data.size();
            if (size < -0) {
                showMessage("no favourite team selected");
                return;
            }
            for (int i = 0; i < size; i++) {
                JsonObject object = data.get(i).getAsJsonObject();
                String name = "", logo = "";
                String fav_team_id = object.get("fav_team_id").getAsString();
                if (!object.get("name").isJsonNull()) {
                    name = object.get("name").getAsString();
                } else {
                    name = "No Name";
                }
                if (!object.get("logo").isJsonNull()) {
                    logo = object.get("logo").getAsString();
                } else {
                    logo = "";
                }
                teamDatas.add(new TeamData(fav_team_id, name, logo));
            }
            adapter.notifyDataSetChanged();
        }

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        relativeLayoutHeader = (RelativeLayout) findViewById(R.id.header);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        view1 = findViewById(R.id.view);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);
        radioButton = (CheckBox) findViewById(R.id.radioButton);
        radioButton.setChecked(false);

        buttonAddmore = (Button) findViewById(R.id.button_add_notifiaction);

        imageViewMute = (ImageView) findViewById(R.id.imageViewMute);


        imageViewMute.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                ic_sound_list.clear();
                radioButton.toggle();
                userSessionManager.setMuteAllNotification(false);

            }
        });


        buttonAddmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TeamNotificationActivity.this, TeamlistActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    for (int i = 0; i < TeamNotificationAdapter.arrayListBoolean.size(); i++) {
                        TeamNotificationAdapter.arrayListBoolean.set(i, true);
                        //userSessionManager.getTeamNotification(itemList.get(position).name);
                        userSessionManager.setTeamNotification(teamDatas.get(i).name, true);
                    }
                    imageViewMute.setImageResource(R.drawable.ic_volume);

                } else {
                    imageViewMute.setImageResource(R.drawable.ic_volume_mute);
                    for (int i = 0; i < TeamNotificationAdapter.arrayListBoolean.size(); i++) {
                        TeamNotificationAdapter.arrayListBoolean.set(i, false);
                        //userSessionManager.getTeamNotification(itemList.get(position).name);
                        userSessionManager.setTeamNotification(teamDatas.get(i).name, false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void setupToolBar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);


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

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new TeamNotificationAdapter(getApplicationContext(), teamDatas);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


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
                // setupDefaultTheme();
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
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
        relativeLayoutHeader.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
        buttonAddmore.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
    }

    private void setupPurlpleTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
        relativeLayoutHeader.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
        buttonAddmore.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setupGreenTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
        relativeLayoutHeader.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
        buttonAddmore.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setupMarronTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
        relativeLayoutHeader.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
        buttonAddmore.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setupLightBlueTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
        relativeLayoutHeader.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
        buttonAddmore.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
    }

    private void setupDefaultTheme() {
        toolbar.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        coordinatorLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        relativeLayoutHeader.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        buttonAddmore.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

}
