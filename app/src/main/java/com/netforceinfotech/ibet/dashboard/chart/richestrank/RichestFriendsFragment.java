package com.netforceinfotech.ibet.dashboard.chart.richestrank;

import android.content.Context;
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

public class RichestFriendsFragment extends Fragment {

    Context context;
    private LinearLayoutManager layoutManager;
    private RichestAdapter adapter;
    private UserSessionManager userSessionManager;
    private RecyclerView recyclerView;
    FrameLayout highestLayout;
    int theme;


    ArrayList<RichestData> richestDatas = new ArrayList<RichestData>();

    public RichestFriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthighest, container, false);
        context = getActivity();

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        return view;
    }



}
