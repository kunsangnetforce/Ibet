package com.netforceinfotech.ibet.dashboard.setting.sounds;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.kyleduo.switchbutton.SwitchButton;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

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
    UserSessionManager userSessionManager;
    int theme;


    public SoundAdapter(Context context, List<SoundData> itemList) {
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

        View view = inflater.inflate(R.layout.row_sound_activity, parent, false);
        viewHolder = new SettingHolder(view);
        setlist_border();
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        SettingHolder settingHolder = (SettingHolder) holder;
        settingHolder.switchButton.setChecked(itemList.get(position).status);
        settingHolder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (position) {
                    case 0:
                        userSessionManager.setSoundOnOff(b);
                        break;
                    case 1:
                        userSessionManager.setWinBet(b);
                        break;
                    case 2:
                        userSessionManager.setLoseBet(b);
                        break;
                }
            }
        });
        viewHolder.textViewTitle.setText(itemList.get(position).title);


    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }


    public class SettingHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewCategory, textViewPros;
        SwitchButton switchButton;
        MaterialRippleLayout materialRippleLayout;
        View view, layout_view;


        public SettingHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

            textViewTitle = (TextView) itemView.findViewById(R.id.textViewName);
            switchButton = (SwitchButton) view.findViewById(R.id.switchbutton);
            layout_view = (View) itemView.findViewById(R.id.view);

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

