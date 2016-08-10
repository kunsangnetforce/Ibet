package com.netforceinfotech.ibet.live_event.stand;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.netforceinfotech.ibet.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StandFragment extends Fragment implements View.OnClickListener {

    CircleImageView circleImageViewTeamA, circleImageViewTeamB;
    Button buttonNeutral;

    public StandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stand, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        circleImageViewTeamA= (CircleImageView) view.findViewById(R.id.imageViewTeamA);
        circleImageViewTeamB= (CircleImageView) view.findViewById(R.id.imageViewTeamB);
        buttonNeutral= (Button) view.findViewById(R.id.buttonNeutral);
        circleImageViewTeamA.setOnClickListener(this);
        circleImageViewTeamB.setOnClickListener(this);
        buttonNeutral.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
