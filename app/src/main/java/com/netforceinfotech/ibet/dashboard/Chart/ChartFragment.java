package com.netforceinfotech.ibet.dashboard.Chart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.netforceinfotech.ibet.R;


public class ChartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    Button button_highest,button_richest;


    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chart, container, false);
        //setupTab(view);


        button_highest= (Button) view.findViewById(R.id.buttonHighest);
        button_richest = (Button) view.findViewById(R.id.buttonRichest);



        return view;
    }

}
