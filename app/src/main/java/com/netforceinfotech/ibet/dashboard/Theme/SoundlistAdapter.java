package com.netforceinfotech.ibet.dashboard.Theme;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.GeneralNotification.GeneralNotificationActivity;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.TeamNotification.TeamNotificationActivity;
import com.netforceinfotech.ibet.dashboard.language.LanguageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 7/25/2016.
 */
public class SoundlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    MediaPlayer m;
    SoundlistHolder viewHolder;

    private final LayoutInflater inflater;
    private List<String> itemList;
    private Context context;



    public SoundlistAdapter(Context context, List<String> itemList)
    {
        this.itemList = itemList;
        this.context = context;

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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.row_soundlist, parent, false);
        viewHolder = new SoundlistHolder(view);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

        viewHolder.textViewTitle.setText(itemList.get(position));

    }

    private void showMessage(String s)
    {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return 3;
//        return itemList.size();
    }


    public class SoundlistHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener
    {


        TextView textViewTitle, textViewCategory, textViewPros;

        MaterialRippleLayout materialRippleLayout;
        View view;


        public SoundlistHolder(View itemView)
        {
            super(itemView);
            //implementing onClickListener
            itemView.setOnClickListener(this);
            view = itemView;

            textViewTitle = (TextView) itemView.findViewById(R.id.setting_list_text);

        }
        @Override
        public void onClick(View v)
        {

            int position  =   getAdapterPosition();
            final Intent intent;

            if (position == 0)
            {
                playBeep();

            }
            else if (position == 1)
            {
                playBeep();
            }
            else if (position == 2)
            {
                playBeep();
            }

        }
    }



    public void playBeep()
    {
        try {

            m = new MediaPlayer();

            AssetFileDescriptor descriptor = context.getAssets().openFd("CrowedHole.wav");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

