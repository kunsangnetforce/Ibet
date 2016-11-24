package com.netforceinfotech.ibet1.dashboard.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet1.dashboard.setting.notification.generalNotification.GeneralNotificationActivity;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.setting.notification.teamNotification.TeamNotificationActivity;
import com.netforceinfotech.ibet1.dashboard.setting.feedback.FeedbackActivity;
import com.netforceinfotech.ibet1.dashboard.setting.odds.OddsActivity;
import com.netforceinfotech.ibet1.dashboard.setting.sounds.SoundActivity;
import com.netforceinfotech.ibet1.dashboard.setting.theme.ThemeActivity;
import com.netforceinfotech.ibet1.dashboard.setting.language.LanguageActivity;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asdf on 7/22/2016.
 */
public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<String> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();
    ArrayList<Integer> setting_icon = new ArrayList<>();

    public SettingAdapter(Context context, List<String> itemList, ArrayList<Integer> imagelist) {
        this.itemList = itemList;
        this.context = context;
        this.setting_icon = imagelist;
        inflater = LayoutInflater.from(context);

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

        View view = inflater.inflate(R.layout.row_setting, parent, false);
        viewHolder = new SettingHolder(view);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        viewHolder.textViewTitle.setText(itemList.get(position));
        viewHolder.image_icon.setImageResource(setting_icon.get(position));
        ;

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return 9;
//        return itemList.size();
    }


    public class SettingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textViewTitle, textViewCategory, textViewPros;
        ImageView image_icon;
        MaterialRippleLayout materialRippleLayout;
        View view;


        public SettingHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            itemView.setOnClickListener(this);
            view = itemView;
            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
            image_icon = (ImageView) itemView.findViewById(R.id.imageViewLogo);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewName);
            UserSessionManager userSessionManager = new UserSessionManager(context);
            switch (userSessionManager.getBackground()) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    textViewTitle.setTextColor(ContextCompat.getColor(context, R.color.white));
                    break;
                case 5:
                    break;
            }

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Intent intent = null;

            if (position == 0) {
                intent = new Intent(context, LanguageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else if (position == 1) {
                intent = new Intent(context, TeamNotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else if (position == 2) {
                intent = new Intent(context, GeneralNotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else if (position == 3) {
                intent = new Intent(context, SoundActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else if (position == 4) {
                intent = new Intent(context, ThemeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else if (position == 5) {
                intent = new Intent(context, OddsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else if (position == 6) {
                intent = new Intent(context, InfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else if (position == 7) {
                showMessage("Remove ads function will be called");
            } else if (position == 8) {
                intent = new Intent(context, FeedbackActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                intent = new Intent(context, TeamNotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }


        }


    }


}
