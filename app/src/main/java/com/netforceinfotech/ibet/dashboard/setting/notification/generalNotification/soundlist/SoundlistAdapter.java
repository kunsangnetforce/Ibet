package com.netforceinfotech.ibet.dashboard.setting.notification.generalNotification.soundlist;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.Debugger.Debugger;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.List;

/**
 * Created by John on 7/25/2016.
 */
public class SoundlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    SoundlistHolder viewHolder;
    private final LayoutInflater inflater;
    private List<SoundListData> itemList;
    private Context context;
    Long s1;
    UserSessionManager userSessionManager;
    int theme;
    AssetFileDescriptor descriptor;
    int lastClicked = 0;
    String event_name;
    private MediaPlayer mediaPlayer;

    public SoundlistAdapter(Context context, List<SoundListData> itemList, String event_name, MediaPlayer mediaPlayer) {

        this.mediaPlayer = mediaPlayer;
        this.itemList = itemList;
        this.context = context;
        this.event_name = event_name;
        inflater = LayoutInflater.from(context);
        userSessionManager = new UserSessionManager(context);
        theme = userSessionManager.getTheme();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_soundlist, parent, false);
        viewHolder = new SoundlistHolder(view);
        setlist_border();
        /*try {
            arrayListBoolean.clear();
        } catch (Exception ex) {

        }
        for (int i = 0; i < itemList.size(); i++) {

            if (i == 0) {
                arrayListBoolean.add(true);
            } else {
                arrayListBoolean.add(false);
            }

        }
*/
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final SoundlistHolder soundlistHolder = (SoundlistHolder) holder;
        soundlistHolder.textViewTitle.setText(itemList.get(position).title);
        if (lastClicked == position) {
            soundlistHolder.imageView.setImageResource(R.drawable.ic_check_30);
        } else {
            soundlistHolder.imageView.setImageResource(R.drawable.ic_uncheck_28);
        }
        soundlistHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(position);
            }
        });
        soundlistHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastClicked = position;
                //setGeneralNotificationFileName
                userSessionManager.setGeneralNotificationFileName(event_name + "filename", itemList.get(position).filename);
                Debugger.i("filenotification", event_name + "filename  :" + itemList.get(position).filename);
                userSessionManager.setGeneralNotificationSoundName(event_name + "soundname", itemList.get(position).title);
                notifyDataSetChanged();


            }

        });

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }


    public class SoundlistHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewCategory, textViewPros;

        MaterialRippleLayout materialRippleLayout;
        View view, layout_view;
        ImageView imageView;


        public SoundlistHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            view = itemView;
            imageView = (ImageView) view.findViewById(R.id.imageViewCheck);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewName);
            layout_view = (View) itemView.findViewById(R.id.view);

        }


    }

    private void playSound(int position) {

        try {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
        } catch (Exception ex) {

        }
        try {
            int resID = context.getResources().getIdentifier(itemList.get(position).filename, "raw", context.getPackageName());
            mediaPlayer=MediaPlayer.create(context, resID);
            mediaPlayer.start();
        } catch (Exception ex) {
            ex.printStackTrace();
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

