package com.netforceinfotech.ibet.currentbet.betarena.stats.table;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.currentbet.livebet.LiveBetAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TableFragment extends Fragment {


    private RecyclerView recyclerView;
    private Context context;
    private TableAdapter adapter;

    public TableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_table, container, false);
        context=getActivity();
        setupRecyclerView(view);
        return view;
    }
    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TableAdapter(context, null);
        recyclerView.setAdapter(adapter);

    }
}
