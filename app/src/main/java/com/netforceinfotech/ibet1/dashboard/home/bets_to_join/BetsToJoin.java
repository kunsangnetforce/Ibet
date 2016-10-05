package com.netforceinfotech.ibet1.dashboard.home.bets_to_join;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet1.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetsToJoin extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    ArrayList<BetsToJoinData> betsToJoinDatas = new ArrayList<>();
    private BetsToJoinAdapter adapter;

    public BetsToJoin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bets_to_join, container, false);
        context = getActivity();
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view)
    {
        setupbetsToJoinDatas();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BetsToJoinAdapter(context, betsToJoinDatas);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setupbetsToJoinDatas() {
        try {
            betsToJoinDatas.clear();
        } catch (Exception ex) {

        }
        betsToJoinDatas.add(new BetsToJoinData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        betsToJoinDatas.add(new BetsToJoinData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        betsToJoinDatas.add(new BetsToJoinData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        betsToJoinDatas.add(new BetsToJoinData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        betsToJoinDatas.add(new BetsToJoinData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        betsToJoinDatas.add(new BetsToJoinData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        betsToJoinDatas.add(new BetsToJoinData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        betsToJoinDatas.add(new BetsToJoinData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        betsToJoinDatas.add(new BetsToJoinData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));

    }
}
