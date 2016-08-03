package com.netforceinfotech.ibet.dashboard.Chart.RichestRank;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;

public class RichestFriendsFragment extends Fragment
{

    Context context;
    private LinearLayoutManager layoutManager;
    private RichestAdapter adapter;
    private UserSessionManager userSessionManager;
    private RecyclerView recyclerView;
    FrameLayout highestLayout;
    int theme;


    ArrayList<RichestData> richestDatas = new ArrayList<RichestData>();

    public RichestFriendsFragment()
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

            highestLayout.setBackgroundResource(R.drawable.background_theme1);

        }
        else if (theme == 1)
        {

            highestLayout.setBackgroundResource(R.drawable.background_theme2);

        }
        else if (theme == 2)
        {

            highestLayout.setBackgroundResource(R.drawable.background_theme3);;

        }
        else if (theme == 3)
        {

            highestLayout.setBackgroundResource(R.drawable.background_theme4);

        }
        else if (theme == 4)
        {

            highestLayout.setBackgroundResource(R.drawable.background_theme5);

        }



        recyclerView.setLayoutManager(layoutManager);
        adapter = new RichestAdapter(context, richestDatas);
        recyclerView.setAdapter(adapter);
        setupFinsihedDatas();
        adapter.notifyDataSetChanged();
    }

    private void setupFinsihedDatas()
    {
        try
        {
            richestDatas.clear();
        }

        catch (Exception ex)
        {

        }
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
        richestDatas.add(new RichestData("Tea", "imageurl","coins 10"));
    }
}
