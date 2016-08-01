package com.netforceinfotech.ibet.dashboard;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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
import com.netforceinfotech.ibet.currentbet.DashboardFragment;
import com.netforceinfotech.ibet.dashboard.Setting.notification.TeamNotification.TeamNotificationActivity;
import com.netforceinfotech.ibet.dashboard.Chart.ChartActivity;
import com.netforceinfotech.ibet.dashboard.Setting.Feedback.FeedbackActivity;
import com.netforceinfotech.ibet.dashboard.Profile.ProfileActivity;
import com.netforceinfotech.ibet.dashboard.Setting.SettingActivity;
import com.netforceinfotech.ibet.dashboard.Setting.language.LanguageActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.squareup.picasso.Picasso;

public class Dashboard extends AppCompatActivity
{

    private DashboardFragment dashboardFragment;
    private Toolbar toolbar;
    private UserSessionManager userSessionManager;
    private AccountHeader headerResult;
    private String imageURL, tagName;
    Intent intent;
    public static TextView title;
    int theme;
    int drawer_color;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userSessionManager = new UserSessionManager(getApplicationContext());
        theme = userSessionManager.getTheme();

        setupToolBar("Ibet");
        setupDashboardFragment();
        userSessionManager = new UserSessionManager(getApplicationContext());
        String id = userSessionManager.getFBID();
        imageURL = "https://graph.facebook.com/" + id + "/picture?type=large";
        setupNavigation(imageURL);


    }

    private void setupNavigation(String imageURL)
    {

        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.home).withIcon(R.drawable.ic_home).withSelectedIcon(R.drawable.ic_home_white);
        PrimaryDrawerItem profile = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.profile).withIcon(R.drawable.ic_account).withSelectedIcon(R.drawable.ic_account_white);
        PrimaryDrawerItem chart = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.chart).withIcon(R.drawable.ic_chart).withSelectedIcon(R.drawable.ic_chart_white);
        PrimaryDrawerItem setting = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.setting).withIcon(R.drawable.ic_setting).withSelectedIcon(R.drawable.ic_settings_white);
        PrimaryDrawerItem store = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.store).withIcon(R.drawable.ic_cart).withSelectedIcon(R.drawable.ic_cart_white);
        PrimaryDrawerItem tutorial = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.tutorial).withIcon(R.drawable.ic_clipboard).withSelectedIcon(R.drawable.ic_clipboard_white);
        PrimaryDrawerItem share = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.share).withIcon(R.drawable.ic_share).withSelectedIcon(R.drawable.ic_share_white);
        PrimaryDrawerItem logout = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.logout).withIcon(R.drawable.ic_logout).withSelectedIcon(R.drawable.ic_logout_white);
        PrimaryDrawerItem rateus = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.rateus).withIcon(R.drawable.ic_rateus).withSelectedIcon(R.drawable.ic_rateus_white);

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
                        logout
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
                                Intent feedback = new Intent(Dashboard.this, ProfileActivity.class);
                                startActivity(feedback);
                                break;
                            case 3:
                                Intent chart = new Intent(Dashboard.this, ChartActivity.class);
                                startActivity(chart);
                                break;
                            case 5:
                                Intent setting = new Intent(Dashboard.this, SettingActivity.class);
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

    private AccountHeader getAccountHeader(String imageURL)
    {
        DrawerImageLoader.init(new AbstractDrawerImageLoader()
        {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder)
            {
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView)
            {
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
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener()
                {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile)
                    {
                        return false;
                    }
                })
                .build();
        return headerResult;
    }


    private void showMessage(String s)
    {
        Toast.makeText(Dashboard.this, s, Toast.LENGTH_SHORT).show();
    }

    private void setupToolBar(String s)
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(theme == 0)
        {
            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme1));
        }
        else if (theme == 1)
        {
            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme2));
        }
        else if (theme == 2)
        {
            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme3));

        }
        else if (theme == 3)
        {
            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme4));
        }
        else if (theme == 4)
        {
            toolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_background_theme5));
        }

        setSupportActionBar(toolbar);
        title = (TextView) toolbar.findViewById(R.id.textViewTitle);
        title.setText(s);
    }

    private void replaceFragment(Fragment newFragment, String tag)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, newFragment, tag);
        transaction.commit();
    }

    private void setupDashboardFragment()
    {

        dashboardFragment = new DashboardFragment();
        tagName = dashboardFragment.getClass().getName();
        replaceFragment(dashboardFragment, tagName);


    }

}
