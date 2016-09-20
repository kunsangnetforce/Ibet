package com.netforceinfotech.ibet.dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.netforceinfotech.ibet.MainActivity;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.purchase.PurchaseActivity;
import com.netforceinfotech.ibet.dashboard.setting.notification.teamNotification.TeamNotificationActivity;
import com.netforceinfotech.ibet.dashboard.chart.ChartFragment;
import com.netforceinfotech.ibet.dashboard.profile.ProfileFragment;
import com.netforceinfotech.ibet.dashboard.setting.SettingFragment;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.login.LoginActivity;
import com.netforceinfotech.ibet.scratchview.ImageOverlayDrawable;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class
Dashboard extends AppCompatActivity {

    private DashboardFragment dashboardFragment;
    private ProfileFragment profileFragment;
    private ChartFragment chartfragment;
    private SettingFragment settingfragment;
    private Toolbar toolbar;
    private UserSessionManager userSessionManager;
    private AccountHeader headerResult;
    private String imageURL, tagName;
    Intent intent;
    public static TextView title;
    int theme;
    int drawer_color;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Window window;
    RelativeLayout header_background;
    String loginmode;
    private Menu menu;
    CircleImageView imageViewProfilePic;
    TextView textViewName;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        context = this;
        userSessionManager = new UserSessionManager(getApplicationContext());


        loginmode = userSessionManager.getLoginMode();

        setupToolBar("Ibet");

        window = getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (theme == 0) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme1));
            }
        } else if (theme == 1) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme2));
            }

        } else if (theme == 2) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme3));
            }

        } else if (theme == 3) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme4));
            }
        } else if (theme == 4) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // only for gingerbread and newer versions
                window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_background_theme5));
            }
        }


        setupDashboardFragment();
        userSessionManager = new UserSessionManager(getApplicationContext());
        String id = userSessionManager.getFBID();
        imageURL = "https://graph.facebook.com/" + id + "/picture?type=large";
        // setupNavigation(imageURL);
        setupNavigationHeader();


    }

    private void setupNavigationHeader() {
        imageViewProfilePic = (CircleImageView)navigationView.getHeaderView(0).findViewById(R.id.imageViewProfilePic);
        textViewName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textViewName);
        if (!userSessionManager.getLoginMode().equals("0")) {
            Log.i("picturekunsang", userSessionManager.getProfilePic());
            Picasso.with(context).load(userSessionManager.getProfilePic()).error(R.drawable.ic_error).into(imageViewProfilePic);
            textViewName.setText(userSessionManager.getName());
        }

    }


    private void showMessage(String s) {
        Toast.makeText(Dashboard.this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupToolBar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        header_background = (RelativeLayout) findViewById(R.id.header_relative);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        setupNavigationView();
        if (theme == 0) {

            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme1));
            navigationView.setBackgroundColor(getResources().getColor(R.color.tab_seclector_highlitedcolor_theme1));
            // header_background.setBackgroundColor(Color.RED);

        } else if (theme == 1) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme2));
            navigationView.setBackgroundColor(getResources().getColor(R.color.navigation_background_theme2));

        } else if (theme == 2) {

            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme3));
            navigationView.setBackgroundColor(getResources().getColor(R.color.navigation_background_theme3));

        } else if (theme == 3) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme4));
            navigationView.setBackgroundColor(getResources().getColor(R.color.navigation_background_theme4));

        } else if (theme == 4) {
            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme5));
            navigationView.setBackgroundColor(getResources().getColor(R.color.navigation_background_theme5));

        }


        setSupportActionBar(toolbar);
        title = (TextView) toolbar.findViewById(R.id.textViewTitle);
        title.setText(s);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getTitle().toString()) {

                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case "Home":
                        setupDashboardFragment();
                        return true;

                    case "Profile":
                        setupProfileFragment();
                        return true;
                    case "Chart":
                        setupChartFragment();
                        return true;
                    case "Store":
                        Intent intent = new Intent(getApplicationContext(), PurchaseActivity.class);
                        startActivity(intent);
                        return true;
                    case "Setting":
                        setupSettingFragment();
                        return true;
                    case "Tutorial":
                        Toast.makeText(getApplicationContext(), "Tutotial screen to be shown... yet to implment", Toast.LENGTH_SHORT).show();
                        return true;
                    case "Share":
                        shareData();
                        return true;
                    case "Rate us":
                        Toast.makeText(getApplicationContext(), "App url... yet to implement", Toast.LENGTH_SHORT).show();
                        return true;
                    case "Log out":

                        LoginManager.getInstance().logOut();
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                        return true;

                    case "Scratch bonus":

                        Intent bonus = new Intent(Dashboard.this, ImageOverlayDrawable.class);
                        startActivity(bonus);

                        return true;
                    case "Login":

                        Intent login = new Intent(Dashboard.this, LoginActivity.class);
                        startActivity(login);
                        finish();

                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }

        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    private void setupNavigationView() {
        menu = navigationView.getMenu();
        if (loginmode.equalsIgnoreCase("0")) {
            menu.add("Home").setIcon(R.drawable.ic_home);
            menu.add("Setting").setIcon(R.drawable.ic_setting);
            menu.add("Tutorial").setIcon(R.drawable.ic_clipboard);
            menu.add("Share").setIcon(R.drawable.ic_share);
            menu.add("Rate us").setIcon(R.drawable.ic_rateus);
            menu.add("Login").setIcon(R.drawable.ic_logout);

        } else {
            menu.add("Home").setIcon(R.drawable.ic_home);
            menu.add("Profile").setIcon(R.drawable.ic_profile_setting);
            menu.add("Chart").setIcon(R.drawable.ic_chart);
            menu.add("Store").setIcon(R.drawable.ic_cart);
            menu.add("Setting").setIcon(R.drawable.ic_setting);
            menu.add("Tutorial").setIcon(R.drawable.ic_clipboard);
            menu.add("Share").setIcon(R.drawable.ic_share);
            menu.add("Rate us").setIcon(R.drawable.ic_rateus);
            menu.add("Logout").setIcon(R.drawable.ic_logout);
            menu.add("Scratch bonus").setIcon(R.drawable.ic_scratch);
        }
        menu.setGroupCheckable(0, true, false);
    }

    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, newFragment, tag);
        transaction.commit();
    }

    private void setupDashboardFragment() {
        title.setText("Ibet");
        dashboardFragment = new DashboardFragment();
        tagName = dashboardFragment.getClass().getName();
        replaceFragment(dashboardFragment, tagName);

    }

    private void setupProfileFragment() {

        profileFragment = new ProfileFragment();
        tagName = profileFragment.getClass().getName();
        replaceFragment(profileFragment, tagName);


    }

    private void shareData() {
        String shareBody = "Ibet... app for football lover";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.shareit)));
    }

    private void setupSettingFragment() {

        settingfragment = new SettingFragment();
        tagName = settingfragment.getClass().getName();
        replaceFragment(settingfragment, tagName);


    }


    private void setupChartFragment() {
        chartfragment = new ChartFragment();
        tagName = chartfragment.getClass().getName();
        replaceFragment(chartfragment, tagName);

    }

    //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu

}
