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
import android.widget.FrameLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

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
    FrameLayout frameLayout;
    UserSessionManager userSessionManager;
    int theme;


    public FinsihedBet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finsihed_bet, container, false);
        context = getActivity();
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
        frameLayout = (FrameLayout) view.findViewById(R.id.frame_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
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
        //String name, selectedteamlogo, selectedteamname, numberparticipant, numberpost, time, home_logo, away_logo, home_name, away_name, betstatus, betid;
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        FinsihedDatas.add(new FinsihedData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
    }


}
