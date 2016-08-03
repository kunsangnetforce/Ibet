package com.netforceinfotech.ibet.currentbet.betarena.betdetail;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.bets_to_join.BetsToJoinAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetDetailFragment extends Fragment {
Context context;

    public BetDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragmentet_bet_detail, container, false);
        context=getActivity();
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        DetailBetAdapter detailBetAdapter=new DetailBetAdapter(context,null);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(detailBetAdapter);
    }

}
