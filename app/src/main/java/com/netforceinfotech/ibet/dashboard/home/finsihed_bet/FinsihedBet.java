package com.netforceinfotech.ibet.dashboard.home.finsihed_bet;

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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class FinsihedBet extends Fragment {


    private RecyclerView recyclerView;
    private Context context;
    private FinishedBetAdapter adapter;
    private LinearLayoutManager layoutManager;
    static protected ArrayList<FinsihedData> FinsihedDatas = new ArrayList<>();
    public FinsihedBet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finsihed_bet, container, false);
        context=getActivity();
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
          /*
        *  recyclerView_Same = (RecyclerView) findViewById(R.id.recyclerFeatured);
        layoutManagerSame = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_Same.setLayoutManager(layoutManagerSame);
        adapterSame = new RecyclerViewAdapterS(context, rowDataS, imagePath);
        recyclerView_Same.setAdapter(adapterSame);
        * */

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FinishedBetAdapter(context, FinsihedDatas);
        recyclerView.setAdapter(adapter);
        setupFinsihedDatas();
        adapter.notifyDataSetChanged();
    }
    private void setupFinsihedDatas() {
        try {
            FinsihedDatas.clear();
        } catch (Exception ex) {

        }
        FinsihedDatas.add(new FinsihedData("Tea", "imageurl"));
        FinsihedDatas.add(new FinsihedData("Dried Fruit", "imageurl"));
        FinsihedDatas.add(new FinsihedData("Nut Mixed", "imageurl"));
        FinsihedDatas.add(new FinsihedData("Tea", "imageurl"));
        FinsihedDatas.add(new FinsihedData("Dried Fruit", "imageurl"));
        FinsihedDatas.add(new FinsihedData("Nut Mixed", "imageurl"));
        FinsihedDatas.add(new FinsihedData("Tea", "imageurl"));
        FinsihedDatas.add(new FinsihedData("Dried Fruit", "imageurl"));
        FinsihedDatas.add(new FinsihedData("Nut Mixed", "imageurl"));

    }


}
