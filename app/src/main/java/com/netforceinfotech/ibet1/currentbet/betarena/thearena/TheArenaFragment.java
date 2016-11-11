package com.netforceinfotech.ibet1.currentbet.betarena.thearena;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.netforceinfotech.ibet1.Debugger.Debugger;
import com.netforceinfotech.ibet1.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheArenaFragment extends Fragment implements View.OnClickListener {

    CircleImageView circleImageViewTeamA, circleImageViewTeamB;
    Button buttonNeutral;
    Context context;
    String season_id, home_id, away_id, match_id, home_name, away_name, away_logo, home_logo;
    private String tagName;
    private String bet_id;
    private Intent intent;
    Bundle bundle;

    public TheArenaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stand, container, false);
        context = getActivity();
        try {
            season_id = this.getArguments().getString("season_id");
            home_id = this.getArguments().getString("home_id");
            away_id = this.getArguments().getString("away_id");
            match_id = this.getArguments().getString("match_id");
            home_name = this.getArguments().getString("home_name");
            away_name = this.getArguments().getString("away_name");
            home_logo = this.getArguments().getString("home_logo");
            away_logo = this.getArguments().getString("away_logo");
            bet_id = this.getArguments().getString("bet_id");
            Debugger.i("klogo", home_logo + " " + away_logo);
        } catch (Exception ex) {
            Log.i("kunsang_exception", "paramenter not set");
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        circleImageViewTeamA = (CircleImageView) view.findViewById(R.id.imageViewTeamA);
        circleImageViewTeamB = (CircleImageView) view.findViewById(R.id.imageViewTeamB);
        Debugger.i("kimage", home_logo + "   " + away_logo);
        Glide.with(context) .fromResource()
                .asBitmap()
                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .load(R.drawable.home_logo).error(R.drawable.ic_error).into(circleImageViewTeamA);
        Glide.with(context)
                .fromResource()
                .asBitmap()
                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .load(R.drawable.away_logo).error(R.drawable.ic_error).into(circleImageViewTeamB);
        buttonNeutral = (Button) view.findViewById(R.id.buttonNeutral);
        circleImageViewTeamA.setOnClickListener(this);
        circleImageViewTeamB.setOnClickListener(this);
        buttonNeutral.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNeutral:
                intent = new Intent(context, TheArenaActivity.class);
                bundle = new Bundle();
                  /* match_id = bundle.getString("match_id");
            bet_id = bundle.getString("bet_id");
            team = bundle.getString("team");*/
                bundle.putString("match_id", match_id);
                bundle.putString("bet_id", bet_id);
                bundle.putString("team", "draw");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imageViewTeamA:
                intent = new Intent(context, TheArenaActivity.class);
                bundle = new Bundle();
                  /* match_id = bundle.getString("match_id");
            bet_id = bundle.getString("bet_id");
            team = bundle.getString("team");*/
                bundle.putString("match_id", match_id);
                bundle.putString("bet_id", bet_id);
                bundle.putString("team", "home");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.imageViewTeamB:
                intent = new Intent(context, TheArenaActivity.class);
                bundle = new Bundle();
                bundle.putString("match_id", match_id);
                bundle.putString("bet_id", bet_id);
                bundle.putString("team", "away");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment, tag);
        transaction.commit();
    }
}
