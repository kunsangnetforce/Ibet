package com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.searchfriend.friend;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchFriendHolder extends RecyclerView.ViewHolder {


    TextView textViewName;
    CircleImageView imageView;
    MaterialRippleLayout materialRippleLayout;
    LinearLayout linearLayoutMain;
    View view;
    Context context;
    UserSessionManager userSessionManager;


    public SearchFriendHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;
        context = view.getContext();
        userSessionManager = new UserSessionManager(context);
        linearLayoutMain = (LinearLayout) view.findViewById(R.id.linearLayoutMain);
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        imageView = (CircleImageView) view.findViewById(R.id.imageViewProfilePic);
        materialRippleLayout = (MaterialRippleLayout) view.findViewById(R.id.ripple);
        switch (userSessionManager.getTheme()) {
            case 0:
                setDefaultTheme();
                break;
            case 1:
                setBrownTheme();
                break;
            case 2:
                setPurpleTheme();
                break;
            case 3:
                setGreenTheme();
                break;
            case 4:
                setMarronTheme();
                break;
            case 5:
                setLightBlueTheme();
                break;

        }
    }

    private void setLightBlueTheme() {
        linearLayoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));
    }

    private void setMarronTheme() {
        linearLayoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));
    }

    private void setGreenTheme() {
        linearLayoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
    }

    private void setPurpleTheme() {
        linearLayoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));
    }

    private void setBrownTheme() {
        linearLayoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));
    }

    private void setDefaultTheme() {
        linearLayoutMain.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }
}