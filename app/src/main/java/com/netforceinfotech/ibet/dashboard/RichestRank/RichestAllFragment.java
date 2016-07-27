package com.netforceinfotech.ibet.dashboard.RichestRank;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet.R;

import java.util.ArrayList;


public class RichestAllFragment  extends Fragment {



    private RecyclerView recyclerView;
    Context context;
    private LinearLayoutManager layoutManager;
    private RichestAdapter adapter;


    ArrayList<RichestData> richestDatas = new ArrayList<RichestData>();

    public RichestAllFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragmenthighest, container, false);
        context = getActivity();

        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RichestAdapter(context, richestDatas);
        recyclerView.setAdapter(adapter);
        setupFinsihedDatas();
        adapter.notifyDataSetChanged();
    }

    private void setupFinsihedDatas()
    {
        try
        {
            richestDatas.clear();
        } catch (Exception ex) {

        }
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
    }
}
