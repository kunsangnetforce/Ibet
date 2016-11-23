package com.netforceinfotech.ibet1.dashboard.setting.notification.generalNotification.sound;

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
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.setting.notification.generalNotification.soundlist.SoundlistActivity;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 7/26/2016.
 */
public class SoundAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<SoundData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();
    ArrayList<Integer> setting_icon = new ArrayList<>();
    UserSessionManager userSessionManager;
    int theme;


    public SoundAdapter(Context context, List<SoundData> itemList, ArrayList<Integer> imagelist) {
        this.itemList = itemList;
        this.context = context;
        this.setting_icon = imagelist;
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

        View view = inflater.inflate(R.layout.row_sound, parent, false);
        viewHolder = new SettingHolder(view);

        setlist_border();

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        SettingHolder settingHolder = (SettingHolder) holder;
        settingHolder.textViewTitle.setText(itemList.get(position).eventname);
        settingHolder.image_icon.setImageResource(setting_icon.get(position));
        settingHolder.textViewSound.setText(userSessionManager.getGeneralNotificationSoundName(itemList.get(position).eventname + "soundname"));
        settingHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SoundlistActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", itemList.get(position).type);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return 7;
//        return itemList.size();
    }


    public class SettingHolder extends RecyclerView.ViewHolder {


        TextView textViewTitle, textViewSound, textViewPros;
        ImageView image_icon;
        MaterialRippleLayout materialRippleLayout;
        View view, layout_view;


        public SettingHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

            image_icon = (ImageView) itemView.findViewById(R.id.imageViewLogo);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewName);
            textViewSound = (TextView) itemView.findViewById(R.id.sound_text);

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

