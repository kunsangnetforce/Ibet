package com.netforceinfotech.ibet.GeneralNotification;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet.R;

import java.util.ArrayList;


public class SoundFragment  extends Fragment {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SoundAdapter adapter;
    ArrayList<String> notificationDatas = new ArrayList<String>();

    ArrayList<Integer> icon_list = new ArrayList<Integer>();

    ArrayList<String>  soundData = new ArrayList<String>();

    private Toolbar toolbar;

    Context  context;

    public SoundFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        context = getActivity();

        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        icon_list.add(R.drawable.match_reminder);
        icon_list.add(R.drawable.goal_icon);
        icon_list.add(R.drawable.red_cardicon);
        icon_list.add(R.drawable.yellow_card);
        icon_list.add(R.drawable.match_starticon);
        icon_list.add(R.drawable.half_timeicon);
        icon_list.add(R.drawable.final_icon);

        notificationDatas.add("Match Reminder");
        notificationDatas.add("Goal");
        notificationDatas.add("Red Card");
        notificationDatas.add("Yellow card");
        notificationDatas.add("Match Start");
        notificationDatas.add("Half Time");
        notificationDatas.add("Final");

        soundData.add("Sound 3 >");
        soundData.add("Sound 4 >");
        soundData.add("Tennis 2 >");
        soundData.add("Sound 3 >");
        soundData.add("Whistle >");
        soundData.add("Whistle >");
        soundData.add("Whistle >");



        adapter = new SoundAdapter(getActivity(), notificationDatas, icon_list,soundData);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        recyclerView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

            }
        });

    }

}