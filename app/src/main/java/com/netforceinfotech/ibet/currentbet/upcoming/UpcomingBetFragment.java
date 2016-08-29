package com.netforceinfotech.ibet.currentbet.upcoming;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

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

        if (theme == 0)
        {

            upcomminhg_bet_latout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme1));

        }
        else if (theme == 1)
        {

            upcomminhg_bet_latout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme2));

        }
        else if (theme == 2)
        {

            upcomminhg_bet_latout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme3));
        }
        else if (theme == 3)
        {

            upcomminhg_bet_latout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme4));

        }
        else if (theme == 4)
        {

            upcomminhg_bet_latout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme5));
        }

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
