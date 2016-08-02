package com.netforceinfotech.ibet.dashboard.Chart.highest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

//1
public class HighestFragment extends Fragment
{


    private UserSessionManager userSessionManager;
    private RecyclerView recyclerView;
    Context context;
    private LinearLayoutManager layoutManager;
    private HighestAdapter adapter;
    FrameLayout highestLayout;
    int theme;



    ArrayList<HighestFragmentData> highestDatas = new ArrayList<HighestFragmentData>();

    public HighestFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragmenthighest, container, false);
        context = getActivity();

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view)
    {
        highestLayout = (FrameLayout) view.findViewById(R.id.highestlayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);


        if(theme == 0)
        {

            highestLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme1));

        }
        else if (theme == 1)
        {

            highestLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme2));

        }
        else if (theme == 2)
        {

            highestLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme3));

        }
        else if (theme == 3)
        {

            highestLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme4));

        }
        else if (theme == 4)
        {

            highestLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.tab_background_theme5));

        }


        recyclerView.setLayoutManager(layoutManager);
        adapter = new HighestAdapter(context, highestDatas);
        recyclerView.setAdapter(adapter);
        setupFinsihedDatas();
        adapter.notifyDataSetChanged();
    }

    private void setupFinsihedDatas()
    {
        try
        {
            highestDatas.clear();
        } catch (Exception ex) {

        }
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
        highestDatas.add(new HighestFragmentData("Tea", "imageurl"));
    }
}
