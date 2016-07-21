package com.netforceinfotech.ibet.dashboard.Feedback;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Chart.ChartTabActivity;
import com.netforceinfotech.ibet.dashboard.Dashboard;



public class FeedbackFragment extends Fragment
{
    Button button_highest,button_richest;


    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view= inflater.inflate(R.layout.fragment_feedback, container, false);
      
        Dashboard.title.setText("Feedback");

        return view;
    }



}
