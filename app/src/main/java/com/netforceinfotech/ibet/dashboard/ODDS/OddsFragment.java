package com.netforceinfotech.ibet.dashboard.ODDS;

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
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.dashboard.language.languageAdapter;

import java.util.ArrayList;


public class OddsFragment extends  Fragment
{

    private RecyclerView recyclerView;
    Context context;
    private LinearLayoutManager layoutManager;
    private languageAdapter adapter;

    ArrayList<String> oddsdatas = new ArrayList<String>();

    public OddsFragment()
    {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_odds, container, false);
        context = getActivity();

        Dashboard.title.setText("Language");
        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        oddsdatas.add("Decimal");
        oddsdatas.add("American");
        oddsdatas.add("Fractional");


        adapter = new languageAdapter(context, oddsdatas);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }


}