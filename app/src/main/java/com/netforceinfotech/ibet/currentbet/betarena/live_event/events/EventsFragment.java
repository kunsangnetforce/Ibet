package com.netforceinfotech.ibet.currentbet.betarena.live_event.events;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    private Context context;
    ArrayList<EventsData> eventsDatas = new ArrayList<>();

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        context = getActivity();
        setupData();
        setupRecycler(view);
        return view;
    }

    private void setupData() {
        eventsDatas.add(new EventsData("neymar", "", "goal", "76"));
        eventsDatas.add(new EventsData("", "bale", "goal", "55"));
        eventsDatas.add(new EventsData("masherano", "", "yellow", "34"));
        eventsDatas.add(new EventsData("Pique", "", "red", "12"));
        eventsDatas.add(new EventsData("", "Ronaldo", "goal", "6"));
        eventsDatas.add(new EventsData("", "", "", "0"));

    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        EventsAdapter adapter = new EventsAdapter(context, eventsDatas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}
