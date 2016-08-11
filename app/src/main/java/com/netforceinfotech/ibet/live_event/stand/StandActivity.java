package com.netforceinfotech.ibet.live_event.stand;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.PagerAdapter;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.StartNewBetActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.netforceinfotech.ibet.general.WrapContentViewPager;
import com.squareup.picasso.Picasso;

import at.grabner.circleprogress.CircleProgressView;
import de.hdodenhof.circleimageview.CircleImageView;

public class StandActivity extends AppCompatActivity  implements View.OnClickListener{


    WrapContentViewPager viewPager;
    private Context context;

    CoordinatorLayout coordinatorLayout;
    UserSessionManager userSessionManager;
    int theme ;
    private TheArenaFragment theArenaFragment;
    private String tagName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stand);


        context = getApplicationContext();

        userSessionManager = new UserSessionManager(getApplicationContext());
        theme = userSessionManager.getTheme();


        setupTab();
    }



    private void setupTab()
    {

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorlayout);

        viewPager = (WrapContentViewPager) findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.all));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.top));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.friends));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        if(theme == 0)
        {
            //coordinatorLayout.setBackgroundResource(R.drawable.background_theme5);
            coordinatorLayout.setBackgroundResource(R.drawable.background_theme1);

        }
        else if(theme == 1)
        {

            coordinatorLayout.setBackgroundResource(R.drawable.background_theme2);

        }

        else if(theme == 2)
        {

            coordinatorLayout.setBackgroundResource(R.drawable.background_theme3);

        }
        else if(theme == 3)
        {


            coordinatorLayout.setBackgroundResource(R.drawable.background_theme4);


        }

        else if(theme == 4)
        {


            coordinatorLayout.setBackgroundResource(R.drawable.background_theme5);


        }


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
                viewPager.reMeasureCurrentPage(viewPager.getCurrentItem());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.buttonStartnewBet:
                //go to new bet
                Intent intent = new Intent(context, StartNewBetActivity.class);
                startActivity(intent);
                break;
        }
    }

}
