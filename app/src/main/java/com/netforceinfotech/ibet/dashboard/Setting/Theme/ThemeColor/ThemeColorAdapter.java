package com.netforceinfotech.ibet.dashboard.setting.theme.themeColor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification.sound.SoundAdapter;
import com.netforceinfotech.ibet.general.UserSessionManager;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    UserSessionManager userSessionManager;


    public ThemeColorAdapter(Activity context, ArrayList<Integer> imagelist) {
        this.context = context;
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
        SettingHolder settingHolder= (SettingHolder) holder;
        try{
            Picasso.with(context).load(setting_icon.get(position)).into(settingHolder.image_icon);
        }catch (Exception ex){
            Picasso.with(context).load(setting_icon.get(position)).resize(300,300).into(settingHolder.image_icon);
        }

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

