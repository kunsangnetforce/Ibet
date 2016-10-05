package com.netforceinfotech.ibet1.dashboard.setting.notification.generalNotification.notification;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.kyleduo.switchbutton.SwitchButton;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 7/25/2016.
 */
public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<NotificationData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();
    ArrayList<Integer> setting_icon = new ArrayList<>();
    UserSessionManager userSessionManager;
    int theme;
    public static ArrayList<Boolean> arrayListBoolean = new ArrayList<>();

    public NotificationAdapter(Context context, List<NotificationData> itemList, ArrayList<Integer> imagelist) {
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

        View view = inflater.inflate(R.layout.row_notification, parent, false);
        viewHolder = new SettingHolder(view);
        try {
            arrayListBoolean.clear();
        } catch (Exception ex) {

        }
        for (int i = 0; i < itemList.size(); i++) {
            arrayListBoolean.add(true);
        }
        setlist_border();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        SettingHolder settingHolder = (SettingHolder) holder;
        settingHolder.switchButton.setChecked(userSessionManager.getGeneralNotification(itemList.get(position).name + "general"));
        settingHolder.textViewTitle.setText(itemList.get(position).name);
        settingHolder.image_icon.setImageResource(setting_icon.get(position));
        settingHolder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                arrayListBoolean.set(position, b);
                userSessionManager.setGeneralNotification(itemList.get(position).name + "general", b);
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


    public class SettingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textViewTitle, textViewCategory, textViewPros;
        ImageView image_icon;
        MaterialRippleLayout materialRippleLayout;
        View view, layout_view;
        SwitchButton switchButton;

        public SettingHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            itemView.setOnClickListener(this);
            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
            switchButton = (SwitchButton) view.findViewById(R.id.switchbutton);
            image_icon = (ImageView) itemView.findViewById(R.id.setting_list_icon);
            textViewTitle = (TextView) itemView.findViewById(R.id.setting_list_text);
            layout_view = (View) itemView.findViewById(R.id.view);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            if (position == 0) {

            }

        }
    }


    private void setlist_border() {
        if (theme == 0) {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background1));
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
