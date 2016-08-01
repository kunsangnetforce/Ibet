package com.netforceinfotech.ibet.currentbet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.ibet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentBet extends Fragment {


    public CurrentBet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_bet, container, false);
        return view;
    }

}
