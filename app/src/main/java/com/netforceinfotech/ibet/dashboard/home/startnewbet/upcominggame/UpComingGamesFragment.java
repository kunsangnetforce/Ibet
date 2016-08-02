package com.netforceinfotech.ibet.dashboard.home.startnewbet.upcominggame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingGamesFragment extends Fragment {


    private UserSessionManager userSessionManager;
    int theme;
    FrameLayout upcomming_games_layout ;
    ArrayList<UpcomingGameData> upcomingGameDatas = new ArrayList<>();

    public UpComingGamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming_games, container, false);

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();


        setupData();


        setupRecycler(view);
        return view;
    }

    private void setupData() {
        try {
            upcomingGameDatas.clear();
        } catch (Exception ex) {

        }
        for (int i = 0; i < 20; i++) {
            upcomingGameDatas.add(new UpcomingGameData("", ""));
        }
    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        upcomming_games_layout = (FrameLayout)view.findViewById(R.id.upcomming_games_layout);

        if(theme == 0)
        {
            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme1));

        }
        else if (theme == 1)
        {

            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme2));

        }
        else if (theme == 2)
        {
            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme3));

        }
        else if (theme == 3)
        {
            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme4));

        }
        else if (theme == 4)
        {
            upcomming_games_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme5));
        }



        UpcomingGameAdapter upcomingGameAdapter = new UpcomingGameAdapter(getActivity(), upcomingGameDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(upcomingGameAdapter);
    }

}
