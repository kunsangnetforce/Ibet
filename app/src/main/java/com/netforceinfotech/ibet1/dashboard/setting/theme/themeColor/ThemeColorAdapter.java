package com.netforceinfotech.ibet1.dashboard.setting.theme.themeColor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.dashboard.Dashboard;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;


/**
 * Created by John on 7/29/2016.
 */
public class ThemeColorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    Activity context;
    ArrayList<Integer> setting_icon = new ArrayList<>();
    ArrayList<String> theme_names;
    UserSessionManager userSessionManager;


    public ThemeColorAdapter(Activity context, ArrayList<Integer> imagelist, ArrayList<String> theme_names) {
        this.context = context;
        this.theme_names = theme_names;
        this.setting_icon = imagelist;
        inflater = LayoutInflater.from(context);
        userSessionManager = new UserSessionManager(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.row_theme, parent, false);

        viewHolder = new SettingHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        SettingHolder settingHolder = (SettingHolder) holder;
        try {
            Glide.with(context).load(setting_icon.get(position)).into(settingHolder.image_icon);
        } catch (Exception ex) {
            Glide.with(context).load(setting_icon.get(position)).into(settingHolder.image_icon);
        }
        settingHolder.textViewThemenName.setText(theme_names.get(position));

        // new_decode();

    }

    private void showMessage(String s) {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();


    }


    @Override
    public int getItemCount() {
        if (setting_icon == null)
            return 0;

        return setting_icon.size();

    }


    public class SettingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image_icon;
        TextView textViewThemenName;
        MaterialRippleLayout materialRippleLayout;
        View view;
        FrameLayout delete_layout;

        public SettingHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            view = itemView;
            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
            image_icon = (ImageView) itemView.findViewById(R.id.setting_list_icon);
            image_icon.setOnClickListener(this);
            textViewThemenName = (TextView) view.findViewById(R.id.textViewThemeName);

        }

        @Override
        public void onClick(View view) {
            userSessionManager.setTheme(getAdapterPosition());
            context.finish();
            Intent intent = new Intent(context, Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }


}

