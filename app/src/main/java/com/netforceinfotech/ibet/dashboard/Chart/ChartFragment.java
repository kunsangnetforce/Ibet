package com.netforceinfotech.ibet.dashboard.Chart;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.support.v4.app.Fragment;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Chart.RichestRank.RichestTabActivity;
import com.netforceinfotech.ibet.dashboard.Chart.highest.HighestTabActivity;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;

public class ChartFragment extends Fragment implements View.OnClickListener
{

    Button button_richest,button_highest;

    private UserSessionManager userSessionManager;
    LinearLayout chart_back_layout;
    Intent intent;
    int theme;




    public ChartFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();

        View view= inflater.inflate(R.layout.activity_chart, container, false);

        Dashboard.title.setText("Charts");
        setupLayout(view);

        return view;
    }

    private void setupLayout(View view)
    {

        chart_back_layout  = (LinearLayout) view.findViewById(R.id.chart_layoput);

        button_highest= (Button) view.findViewById(R.id.buttonHighest);
        button_richest = (Button) view.findViewById(R.id.buttonRichest);

        button_highest.setOnClickListener(this);
        button_richest.setOnClickListener(this);


        if(theme == 0)
        {

            chart_back_layout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme1));

        }
        else if (theme == 1)
        {

            chart_back_layout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme2));

        }
        else if (theme == 2)
        {

            chart_back_layout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme3));

        }
        else if (theme == 3)
        {

            chart_back_layout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme4));

        }
        else if (theme == 4)
        {

            chart_back_layout.setBackgroundColor(getResources().getColor(R.color.tab_background_theme5));


        }


    }


    @Override
    public void onClick(View view)
    {



        switch (view.getId())
        {

            case R.id.buttonHighest:

                intent = new Intent(getActivity(), HighestTabActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.buttonRichest:
                intent = new Intent(getActivity(), RichestTabActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);

                break;

        }

    }

}
