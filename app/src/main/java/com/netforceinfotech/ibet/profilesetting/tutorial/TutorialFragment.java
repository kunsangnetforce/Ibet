package com.netforceinfotech.ibet.profilesetting.tutorial;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.netforceinfotech.ibet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialFragment extends Fragment {

    int screenNo;
    ImageView imageView;
    Context context;

    public TutorialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);
        screenNo = this.getArguments().getInt("screenNo");
        imageView = (ImageView) view.findViewById(R.id.imageView);
        context = getActivity();
        switch (screenNo) {

            case 1:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t1_event).into(imageView);
                break;
            case 2:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t2_stat).into(imageView);
                break;
            case 3:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t3_stand).into(imageView);
                break;
            case 4:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t4_vote).into(imageView);
                break;
            case 5:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t5_profile).into(imageView);
                break;

            case 6:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t6_bet).into(imageView);
                break;
            case 7:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t7_team).into(imageView);
                break;
            case 8:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t8_score).into(imageView);
                break;
            case 9:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t9_betamount).into(imageView);
                break;
            case 10:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t10_next).into(imageView);
                break;
            case 11:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t11_theme).into(imageView);
                break;
            case 12:
                Glide.with(context).fromResource()
                        .asBitmap()
                        .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100)).load(R.drawable.t12_scratch).into(imageView);
                break;
        }
        return view;
    }

}
