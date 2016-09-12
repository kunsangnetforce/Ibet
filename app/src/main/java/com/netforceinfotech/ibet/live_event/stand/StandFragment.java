package com.netforceinfotech.ibet.live_event.stand;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.netforceinfotech.ibet.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StandFragment extends Fragment implements View.OnClickListener {

    CircleImageView circleImageViewTeamA, circleImageViewTeamB;
    Button buttonNeutral;
    Context context;
    private TheArenaFragment theArenaFragment;
    CircleImageView imageViewTeamA, imageViewTeamB;
    private String tagName;
    private String teamaid, teambid, teama, teamb, matchid, logoa, logob;
    private Intent intent;
    private Bundle bundle;

    public StandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stand, container, false);
        context = getActivity();
        try {
            teamaid = this.getArguments().getString("teamaid");
            teambid = this.getArguments().getString("teambid");
            matchid = this.getArguments().getString("matchid");
            teama = this.getArguments().getString("teama");
            teamb = this.getArguments().getString("teamb");
            logoa = this.getArguments().getString("logoa");
            logob = this.getArguments().getString("logob");

        } catch (Exception ex) {
            Log.i("kunsang_exception", "paramenter not set");
        }
        bundle = new Bundle();
        imageViewTeamA = (CircleImageView) view.findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (CircleImageView) view.findViewById(R.id.imageViewTeamB);
        Picasso.with(context).load(logoa).error(R.drawable.ic_error).into(imageViewTeamA);
        Picasso.with(context).load(logob).error(R.drawable.ic_error).into(imageViewTeamB);
        initView(view);
        return view;
    }

    private void initView(View view) {
        circleImageViewTeamA = (CircleImageView) view.findViewById(R.id.imageViewTeamA);
        circleImageViewTeamB = (CircleImageView) view.findViewById(R.id.imageViewTeamB);
        buttonNeutral = (Button) view.findViewById(R.id.buttonNeutral);
        circleImageViewTeamA.setOnClickListener(this);
        circleImageViewTeamB.setOnClickListener(this);
        buttonNeutral.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNeutral:
                intent = new Intent(context, StandActivity.class);
                bundle.putString("teama", teama);
                bundle.putString("teamb", teamb);
                bundle.putString("teamaid", teamaid);
                bundle.putString("teambid", teambid);
                bundle.putString("matchid", matchid);
                bundle.putString("team", "nuetral");
                bundle.putString("logoa",logoa);
                bundle.putString("logob",logob);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imageViewTeamA:
                intent = new Intent(context, StandActivity.class);
                bundle.putString("teama", teama);
                bundle.putString("teamb", teamb);
                bundle.putString("teamaid", teamaid);
                bundle.putString("teambid", teambid);
                bundle.putString("matchid", matchid);
                bundle.putString("team", "home");
                bundle.putString("logoa",logoa);
                bundle.putString("logob",logob);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imageViewTeamB:
                intent = new Intent(context, StandActivity.class);
                bundle.putString("teama", teama);
                bundle.putString("teamb", teamb);
                bundle.putString("teamaid", teamaid);
                bundle.putString("teambid", teambid);
                bundle.putString("matchid", matchid);
                bundle.putString("team", "away");
                bundle.putString("logoa",logoa);
                bundle.putString("logob",logob);
                intent.putExtras(bundle);
                startActivity(intent);
                //   setupArenaFragment();
                break;
        }
    }

    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment, tag);
        transaction.commit();
    }

    private void setupArenaFragment() {
        theArenaFragment = new TheArenaFragment();
        tagName = theArenaFragment.getClass().getName();
        replaceFragment(theArenaFragment, tagName);

    }
}