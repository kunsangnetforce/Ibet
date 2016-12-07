package com.netforceinfotech.ibet.dashboard.purchase;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseFragment extends Fragment implements View.OnClickListener {


    private View view;
    ImageView imageViewSmallPack, imageViewLargePack, imageViewMediumPack, imageViewSuperPack;
    UserSessionManager userSessionManager;
    Context context;
    CoordinatorLayout coordinatorLayout;
    View view1;
    Button buttonPurchase;

    public PurchaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_purchase, container, false);
        context = getActivity();
        userSessionManager = new UserSessionManager(context);
        initView(view);
        Dashboard.title.setText(getString(R.string.store));

        setupTheme();
        setupBackground();
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.relativeLayoutSmallPack).setOnClickListener(this);
        view.findViewById(R.id.relativeLayoutMediumPack).setOnClickListener(this);
        view.findViewById(R.id.relativeLayoutLargePack).setOnClickListener(this);
        view.findViewById(R.id.relativeLayoutSuperPack).setOnClickListener(this);
        view1 = view.findViewById(R.id.view);
        buttonPurchase = (Button) view.findViewById(R.id.buttonPurchase);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayout);
        imageViewLargePack = (ImageView) view.findViewById(R.id.imageViewLargePack);
        imageViewSmallPack = (ImageView) view.findViewById(R.id.imageViewSmallPack);
        imageViewMediumPack = (ImageView) view.findViewById(R.id.imageViewMediumPack);
        imageViewSuperPack = (ImageView) view.findViewById(R.id.imageViewSuperPack);
        Glide.with(context)
                .fromResource()
                .asBitmap()
                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .load(R.drawable.smallpack).into(imageViewSmallPack);
        Glide.with(context)
                .fromResource()
                .asBitmap()
                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .load(R.drawable.mediumpack).into(imageViewMediumPack);

        Glide.with(context)
                .fromResource()
                .asBitmap()
                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .load(R.drawable.largepack).into(imageViewLargePack);

        Glide.with(context)
                .fromResource()
                .asBitmap()
                .encoder(new BitmapEncoder(Bitmap.CompressFormat.PNG, 100))
                .load(R.drawable.superpack).into(imageViewSuperPack);

    }

    private void setupBackground() {

        switch (userSessionManager.getBackground()) {
            case 0:
                coordinatorLayout.setBackgroundResource(R.drawable.blue240);
                break;
            case 1:
                coordinatorLayout.setBackgroundResource(R.drawable.france240);
                break;
            case 2:
                coordinatorLayout.setBackgroundResource(R.drawable.soccer240);
                break;
            case 3:
                coordinatorLayout.setBackgroundResource(R.drawable.spain240);
                break;
            case 4:
                coordinatorLayout.setBackgroundResource(R.drawable.uk240);
                break;
            case 5:
                view1.setVisibility(View.GONE);
                break;
        }
    }

    private void setupTheme() {
        int theme = userSessionManager.getTheme();
        switch (theme) {
            case 0:
                // setupDefaultTheme();
                break;
            case 1:
                setupBrownTheme();
                break;
            case 2:
                setupPurlpleTheme();
                break;
            case 3:
                setupGreenTheme();
                break;
            case 4:
                setupMarronTheme();
                break;
            case 5:
                setupLightBlueTheme();
                break;
        }
    }

    private void setupBrownTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentBrown));
    }

    private void setupPurlpleTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentPurple));
    }

    private void setupGreenTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentGreen));
    }

    private void setupMarronTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentMarron));
    }

    private void setupLightBlueTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccentLightBlue));
    }

    private void setupDefaultTheme() {
        buttonPurchase.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.relativeLayoutSmallPack:
                showMessage("function call for small pack");
                break;
            case R.id.relativeLayoutMediumPack:
                showMessage("function call for medium pack");
                break;
            case R.id.relativeLayoutLargePack:
                showMessage("function call for large pack");
                break;
            case R.id.relativeLayoutSuperPack:
                showMessage("function call for super pack");
                break;
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
