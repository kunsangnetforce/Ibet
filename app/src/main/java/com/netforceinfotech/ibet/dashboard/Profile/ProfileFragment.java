package com.netforceinfotech.ibet.dashboard.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;

public class ProfileFragment extends Fragment
{


    LinearLayout profile_layout;
    int theme;

    private UserSessionManager userSessionManager;


    public ProfileFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();

        View view= inflater.inflate(R.layout.activity_profile, container, false);

        Dashboard.title.setText("Profile");

        setupLayout(view);


        return view;
    }


    private void setupLayout(View view)
    {

        profile_layout = (LinearLayout) view.findViewById(R.id.profile_layout);

        if(theme == 0)
        {

            profile_layout.setBackgroundResource(R.drawable.background_theme1);

        }
        else if (theme == 1)
        {

            profile_layout.setBackgroundResource(R.drawable.background_theme2);

        }
        else if (theme == 2)
        {

            profile_layout.setBackgroundResource(R.drawable.background_theme3);

        }
        else if (theme == 3)
        {

            profile_layout.setBackgroundResource(R.drawable.background_theme4);

        }
        else if (theme == 4)
        {

            profile_layout.setBackgroundResource(R.drawable.background_theme5);


        }


    }

}
