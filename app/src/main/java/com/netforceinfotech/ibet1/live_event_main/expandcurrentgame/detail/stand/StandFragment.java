package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame.detail.stand;


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

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StandFragment extends Fragment implements View.OnClickListener {

    CircleImageView circleImageViewTeamA, circleImageViewTeamB;
    Button buttonNeutral;
    Context context;
    CircleImageView imageViewTeamA, imageViewTeamB;
    private String home_id, away_id, home_name, away_name, match_id, home_logo, away_logo;
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
            home_id = this.getArguments().getString("away_id");
            away_id = this.getArguments().getString("away_id");
            match_id = this.getArguments().getString("match_id");
            home_name = this.getArguments().getString("home_name");
            away_name = this.getArguments().getString("away_name");
            home_logo = this.getArguments().getString("home_logo");
            away_logo = this.getArguments().getString("away_logo");

        } catch (Exception ex) {
            Log.i("kunsang_exception", "paramenter not set");
        }
        bundle = new Bundle();
        imageViewTeamA = (CircleImageView) view.findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (CircleImageView) view.findViewById(R.id.imageViewTeamB);
        Glide.with(context).load(home_logo).error(R.drawable.ic_error).into(imageViewTeamA);
        Glide.with(context).load(away_logo).error(R.drawable.ic_error).into(imageViewTeamB);
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
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("away_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("match_id", match_id);
                bundle.putString("team", "nuetral");
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imageViewTeamA:
                intent = new Intent(context, StandActivity.class);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("away_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("match_id", match_id);
                bundle.putString("team", "home");
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imageViewTeamB:
                intent = new Intent(context, StandActivity.class);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("away_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("match_id", match_id);
                bundle.putString("team", "away");
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
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


}