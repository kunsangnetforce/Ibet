package com.netforceinfotech.ibet.dashboard.setting.notification.teamNotification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.setting.notification.teamNotification.soundlist.SoundlistActivity;
import com.netforceinfotech.ibet.dashboard.setting.notification.teamNotification.teamlist.TeamData;
import com.netforceinfotech.ibet.general.UserSessionManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 7/26/2016.
 */


public class TeamNotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    public List<TeamData> itemList;
    private Context context;
    UserSessionManager userSessionManager;
    int theme;
    public static ArrayList<Boolean> arrayListBoolean = new ArrayList<>();


    public TeamNotificationAdapter(Context context, List<TeamData> itemList) {

        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        userSessionManager = new UserSessionManager(context);
        theme = userSessionManager.getTheme();


    }

    /*  @Override
      public int getItemViewType(int position) {
          if (itemList.get(position).image.isEmpty()) {
              return SIMPLE_TYPE;
          } else {
              return IMAGE_TYPE;
          }
      }
  */


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_teamnotification, parent, false);
        viewHolder = new SettingHolder(view);
        try {
            arrayListBoolean.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < itemList.size(); i++) {
            arrayListBoolean.add(false);
        }
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final SettingHolder settingHolder = (SettingHolder) holder;
        settingHolder.textViewTitle.setText(itemList.get(position).name);


        setlist_border();

        boolean isOn = userSessionManager.getTeamNotification(itemList.get(position).name);
        if (isOn) {
            settingHolder.sound_icon.setImageResource(R.drawable.teammusic_icon);
        } else {
            settingHolder.sound_icon.setImageResource(R.drawable.teammute_icon);
        }
        settingHolder.sound_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userSessionManager.getTeamNotification(itemList.get(position).name)) {
                    userSessionManager.setTeamNotification(itemList.get(position).name, false);
                    settingHolder.sound_icon.setImageResource(R.drawable.teammute_icon);
                } else {
                    userSessionManager.setTeamNotification(itemList.get(position).name, true);
                    settingHolder.sound_icon.setImageResource(R.drawable.teammusic_icon);
                }
            }
        });
        settingHolder.setting_image_icon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SoundlistActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("teamname", itemList.get(position).name);
                bundle.putString("teamid", itemList.get(position).id);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });




    }



    @Override
    public int getItemCount() {
        if (itemList == null)
            return 0;
        return itemList.size();

    }


    public class SettingHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewCategory, textViewPros;
        ImageView image_icon, setting_image_icon, sound_icon;
        MaterialRippleLayout materialRippleLayout;
        View view, layout_view;


        public SettingHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            view = itemView;
            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
            image_icon = (ImageView) itemView.findViewById(R.id.imageViewLogo);
            sound_icon = (ImageView) itemView.findViewById(R.id.sound_icon);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewName);
            setting_image_icon = (ImageView) itemView.findViewById(R.id.setting_icon);
            layout_view = (View) itemView.findViewById(R.id.view);

        }

    }


    private void setlist_border() {

        if (theme == 0) {
          //  viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background1));
        } else if (theme == 1) {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background2));
        } else if (theme == 2) {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background3));
        } else if (theme == 3) {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background4));
        } else if (theme == 4) {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background5));
        }


    }


}

