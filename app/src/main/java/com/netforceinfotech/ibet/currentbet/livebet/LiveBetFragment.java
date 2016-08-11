package com.netforceinfotech.ibet.currentbet.livebet;


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
public class LiveBetFragment extends Fragment
{
    Context context;
    private RecyclerView recyclerView;
    ArrayList<LiveBetData> liveBetDatas = new ArrayList<>();
    private LiveBetAdapter adapter;
    UserSessionManager userSessionManager;
    int theme;
    FrameLayout livebet;


    public LiveBetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_bet, container, false);
        Log.i("testingkunsang","reahced livebet");
        context = getActivity();

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        setupRecyclerView(view);
        return view;


    }

    private void setupRecyclerView(View view)
    {

        livebet = (FrameLayout) view.findViewById(R.id.livebet);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        if (theme == 0)
        {

            livebet.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme1));

        }
        else if (theme == 1)
        {

            livebet.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme2));

        }
        else if (theme == 2)
        {

            livebet.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme3));
        }
        else if (theme == 3)
        {

            livebet.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme4));

        }
        else if (theme == 4)
        {

            livebet.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme5));
        }

        recyclerView.setLayoutManager(layoutManager);
        setupliveBetDatas();
        adapter = new LiveBetAdapter(context, liveBetDatas);
        recyclerView.setAdapter(adapter);


    }

    private void setupliveBetDatas()
    {
        try {
            liveBetDatas.clear();
        } catch (Exception ex) {

        }
        liveBetDatas.add(new LiveBetData("Tea", "imageurl"));
        liveBetDatas.add(new LiveBetData("Dried Fruit", "imageurl"));
        liveBetDatas.add(new LiveBetData("Nut Mixed", "imageurl"));
        liveBetDatas.add(new LiveBetData("Tea", "imageurl"));
        liveBetDatas.add(new LiveBetData("Dried Fruit", "imageurl"));
        liveBetDatas.add(new LiveBetData("Nut Mixed", "imageurl"));
        liveBetDatas.add(new LiveBetData("Tea", "imageurl"));
        liveBetDatas.add(new LiveBetData("Dried Fruit", "imageurl"));
        liveBetDatas.add(new LiveBetData("Nut Mixed", "imageurl"));

    }
}
