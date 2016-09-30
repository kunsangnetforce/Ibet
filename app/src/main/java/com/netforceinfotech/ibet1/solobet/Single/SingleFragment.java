package com.netforceinfotech.ibet1.solobet.single;

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


public class SingleFragment extends Fragment
{

    Context context;
    private RecyclerView recyclerView;
    ArrayList<SingleBetData> liveBetDatas = new ArrayList<>();
    private SingleAdapter adapter;

    public SingleFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_bet, container, false);
        context = getActivity();
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view)
    {

       recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        setupliveBetDatas();
        adapter = new SingleAdapter(context, liveBetDatas);
        recyclerView.setAdapter(adapter);
    }

    private void setupliveBetDatas()
    {
        try
        {
            liveBetDatas.clear();
        }
        catch (Exception ex)
        {

        }
        liveBetDatas.add(new SingleBetData("Tea", "imageurl"));
        liveBetDatas.add(new SingleBetData("Dried Fruit", "imageurl"));
        liveBetDatas.add(new SingleBetData("Nut Mixed", "imageurl"));
        liveBetDatas.add(new SingleBetData("Tea", "imageurl"));
        liveBetDatas.add(new SingleBetData("Dried Fruit", "imageurl"));
        liveBetDatas.add(new SingleBetData("Nut Mixed", "imageurl"));
        liveBetDatas.add(new SingleBetData("Tea", "imageurl"));
        liveBetDatas.add(new SingleBetData("Dried Fruit", "imageurl"));
        liveBetDatas.add(new SingleBetData("Nut Mixed", "imageurl"));

    }
}