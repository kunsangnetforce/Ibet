package com.netforceinfotech.ibet.dashboard;

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
import com.netforceinfotech.ibet.scratchview.ImageOverlayDrawable;
import com.squareup.picasso.Picasso;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        userSessionManager = new UserSessionManager(getApplicationContext());

        theme = userSessionManager.getTheme();

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


    }

    private void setupNavigation(String imageURL) {

        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.home).withIcon(R.drawable.ic_home).withSelectedIcon(R.drawable.ic_home_white);
        PrimaryDrawerItem profile = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.profile).withIcon(R.drawable.ic_account).withSelectedIcon(R.drawable.ic_account_white);
        PrimaryDrawerItem chart = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.chart).withIcon(R.drawable.ic_chart).withSelectedIcon(R.drawable.ic_chart_white);
        PrimaryDrawerItem setting = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.setting).withIcon(R.drawable.ic_setting).withSelectedIcon(R.drawable.ic_settings_white);
        PrimaryDrawerItem store = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.store).withIcon(R.drawable.ic_cart).withSelectedIcon(R.drawable.ic_cart_white);
        PrimaryDrawerItem tutorial = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.tutorial).withIcon(R.drawable.ic_clipboard).withSelectedIcon(R.drawable.ic_clipboard_white);
        PrimaryDrawerItem share = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.share).withIcon(R.drawable.ic_share).withSelectedIcon(R.drawable.ic_share_white);
        PrimaryDrawerItem logout = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.logout).withIcon(R.drawable.ic_logout).withSelectedIcon(R.drawable.ic_logout_white);
        PrimaryDrawerItem rateus = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.rateus).withIcon(R.drawable.ic_rateus).withSelectedIcon(R.drawable.ic_rateus_white);

        PrimaryDrawerItem bonus = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.bonus).withIcon(R.drawable.ic_rateus).withSelectedIcon(R.drawable.ic_rateus_white);

        //create the drawer and remember the `Drawer` result object


        AccountHeader accountHeader = getAccountHeader(imageURL);


        Drawer result = new DrawerBuilder()
                .withAccountHeader(accountHeader)
                .withActivity(this)
                .withToolbar(toolbar)

                .addDrawerItems(
                        home,
                        profile,
                        chart,
                        store,
                        setting,
                        tutorial,
                        share,
                        rateus,
                        logout,
                        bonus
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {


                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        switch (position) {
                            case 17:
                                LoginManager.getInstance().logOut();
                                intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case 1:
                                setupDashboardFragment();
                                break;
                            case 2:
                                Intent feedback = new Intent(Dashboard.this, ProfileFragment.class);
                                startActivity(feedback);
                                break;
                            case 3:
                                setupDashboardFragment();
                                break;
                            case 5:
                                Intent setting = new Intent(Dashboard.this, SettingFragment.class);
                                startActivity(setting);
                                break;
                            case 6:
                                Intent team = new Intent(Dashboard.this, TeamNotificationActivity.class);
                                startActivity(team);
                                break;

                            default:
                                showMessage("Yet to implement" + position);
                                break;
                        }
                        return false;
                    }
                })
                .build();

    }

    private AccountHeader getAccountHeader(String imageURL) {
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }

    /*
    @Override
    public Drawable placeholder(Context ctx) {
        return super.placeholder(ctx);
    }

    @Override
    public Drawable placeholder(Context ctx, String tag) {
        return super.placeholder(ctx, tag);
    }
    */
        });
        UserSessionManager userSessionManager = new UserSessionManager(getApplicationContext());
        String name = userSessionManager.getName();
        String email = userSessionManager.getEmail();
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSelectionListEnabledForSingleProfile(false)
                .withHeaderBackground(R.drawable.background)
                .addProfiles(new ProfileDrawerItem().withName(name).withEmail(email).withIcon(imageURL))
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        return headerResult;
    }


    private void showMessage(String s) {
        Toast.makeText(Dashboard.this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupToolBar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        header_background = (RelativeLayout) findViewById(R.id.header_relative);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

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
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        setupDashboardFragment();
                        return true;

                    case R.id.profile:
                        setupProfileFragment();
                        return true;
                    case R.id.chart:
                        setupChartFragment();
                        return true;
                    case R.id.store:
                        Intent intent = new Intent(getApplicationContext(), PurchaseActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.setting:
                        setupSettingFragment();
                        return true;
                    case R.id.tutorial:
                        Toast.makeText(getApplicationContext(), "Tutotial screen to be shown... yet to implment", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.share:
                        shareData();
                        return true;
                    case R.id.rateus:
                        Toast.makeText(getApplicationContext(), "App url... yet to implement", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.logout:

                        LoginManager.getInstance().logOut();
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                        return true;

                    case R.id.bonus:

                        Intent bonus = new Intent(Dashboard.this, ImageOverlayDrawable.class);
                        startActivity(bonus);

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
