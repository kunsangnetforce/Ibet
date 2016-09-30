package com.netforceinfotech.ibet1.currentbet.upcoming;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */


public class UpcomingBetFragment extends Fragment
{

    ArrayList<UpcomingBetData> upcomingBetDatas=new ArrayList<>();
    Context context;
    UserSessionManager userSessionManager;
    int theme;
    FrameLayout upcomminhg_bet_latout;

    public UpcomingBetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_upcoming_bet, container, false);
        Log.i("testingkunsang","reahced upcoming");
        context=getActivity();

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();

        setupRecyclerView(view);
        return view;
    }
    private void setupRecyclerView(View view)
    {

        upcomminhg_bet_latout = (FrameLayout) view.findViewById(R.id.upcomminhg_bet_latout);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        UpcomingBetAdapter adapter = new UpcomingBetAdapter(context, upcomingBetDatas);
        recyclerView.setAdapter(adapter);
        setupupcomingBetDatas();
        adapter.notifyDataSetChanged();


    }

    private void setupupcomingBetDatas()
    {
        try
        {
            upcomingBetDatas.clear();
        }
        catch (Exception ex)
        {

        }
        upcomingBetDatas.add(new UpcomingBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        upcomingBetDatas.add(new UpcomingBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        upcomingBetDatas.add(new UpcomingBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        upcomingBetDatas.add(new UpcomingBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        upcomingBetDatas.add(new UpcomingBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        upcomingBetDatas.add(new UpcomingBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        upcomingBetDatas.add(new UpcomingBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        upcomingBetDatas.add(new UpcomingBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        upcomingBetDatas.add(new UpcomingBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));

    }
}
