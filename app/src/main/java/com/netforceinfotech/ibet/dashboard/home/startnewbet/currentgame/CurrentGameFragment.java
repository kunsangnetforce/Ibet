package com.netforceinfotech.ibet.dashboard.home.startnewbet.currentgame;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.detail_bet_to_join.WhoWillWinActivity;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentGameFragment extends Fragment implements View.OnClickListener {


    private RecyclerView recyclerView;
    Context context;
    private LinearLayoutManager layoutManager;
    private CurrentGameAdapter adapter;
    ArrayList<CurrentGameData> currentGameDatas = new ArrayList<>();
    FrameLayout currentgame_layout;
    private UserSessionManager userSessionManager;
    Button buttonNext;
    int theme;

    public CurrentGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_game, container, false);
        context = getActivity();
        buttonNext = (Button) view.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(this);
        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();

        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {

        currentgame_layout = (FrameLayout) view.findViewById(R.id.currentgame_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        if (theme == 0) {
            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme1));

        } else if (theme == 1) {

            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme2));

        } else if (theme == 2) {
            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme3));

        } else if (theme == 3) {
            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme4));

        } else if (theme == 4) {
            currentgame_layout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme5));
        }


        recyclerView.setLayoutManager(layoutManager);
        adapter = new CurrentGameAdapter(context, currentGameDatas);
        recyclerView.setAdapter(adapter);
        setupFinsihedDatas();
        adapter.notifyDataSetChanged();
    }

    private void setupFinsihedDatas() {
        try {
            currentGameDatas.clear();
        } catch (Exception ex) {

        }
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
        currentGameDatas.add(new CurrentGameData("Tea", "imageurl"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNext:
                Intent intent = new Intent(context, WhoWillWinActivity.class);
                startActivity(intent);
                break;
        }
    }
}
