package com.netforceinfotech.ibet.dashboard.Chart;

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

//1
public class HighestFragment extends Fragment {



    private RecyclerView recyclerView;
    Context context;
    private LinearLayoutManager layoutManager;
    private HighestAdapter1 adapter;


    ArrayList<HighestFragmentData1> highestDatas = new ArrayList<HighestFragmentData1>();

    public HighestFragment()
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
        adapter = new HighestAdapter1(context, highestDatas);
        recyclerView.setAdapter(adapter);
        setupFinsihedDatas();
        adapter.notifyDataSetChanged();
    }

    private void setupFinsihedDatas()
    {
        try
        {
            highestDatas.clear();
        } catch (Exception ex) {

        }
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData1("Tea", "imageurl"));
    }
}
