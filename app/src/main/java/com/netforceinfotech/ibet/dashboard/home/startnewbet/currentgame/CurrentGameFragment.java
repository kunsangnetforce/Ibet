package com.netforceinfotech.ibet.dashboard.home.startnewbet.currentgame;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentGameFragment extends Fragment {


    private RecyclerView recyclerView;
    Context context;
    private LinearLayoutManager layoutManager;
    private CurrentGameAdapter adapter;
    ArrayList<CurrentGameData> currentGameDatas = new ArrayList<>();

    public CurrentGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_game, container, false);
        context = getActivity();
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
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
}
