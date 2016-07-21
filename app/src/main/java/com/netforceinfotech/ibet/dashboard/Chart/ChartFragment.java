package com.netforceinfotech.ibet.dashboard.Chart;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.netforceinfotech.ibet.MainActivity;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;


public class ChartFragment extends Fragment
{
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
        Dashboard.title.setText("Charts");

        /*view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if (keyCode == KeyEvent.KEYCODE_BACK)
                    {


                        return true;
                    }
                }
                return false;
            }
        });
*/

        button_highest= (Button) view.findViewById(R.id.buttonHighest);

        button_highest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),ChartTabActivity.class);
                startActivity(intent);
            }
        });

        button_richest = (Button) view.findViewById(R.id.buttonRichest);


        button_richest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(),ChartTabActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setupToolBar(String s) {

    }


}
