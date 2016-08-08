package com.netforceinfotech.ibet.dashboard.Setting.notification.TeamNotification.Soundlist;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.List;

/**
 * Created by John on 7/25/2016.
 */
public class SoundlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{


    MyCount counter;
    MediaPlayer m;
    SoundlistHolder viewHolder;
    private final LayoutInflater inflater;
    private List<SoundListData> itemList;
    private Context context;
    Long s1;
    UserSessionManager userSessionManager;
    int theme;
    AssetFileDescriptor descriptor;


    public SoundlistAdapter(Context context, List<SoundListData> itemList)
    {

        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
        userSessionManager = new UserSessionManager(context);
        theme = userSessionManager.getTheme();
        m = new MediaPlayer();

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
        setlist_border();
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

        viewHolder.textViewTitle.setText(itemList.get(position).title);

    }

    private void showMessage(String s)
    {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount()
    {
        return 10;
//        return itemList.size();
    }


    public class SoundlistHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener
    {

        TextView textViewTitle, textViewCategory, textViewPros;

        MaterialRippleLayout materialRippleLayout;
        View view,layout_view;

        public SoundlistHolder(View itemView)
        {
            super(itemView);
            //implementing onClickListener
            itemView.setOnClickListener(this);
            view = itemView;

            textViewTitle = (TextView) itemView.findViewById(R.id.setting_list_text);

            layout_view = (View) itemView.findViewById(R.id.view);

        }
        @Override
        public void onClick(View v)
        {
            int position  =   getAdapterPosition();
            final Intent intent;
            if (position == 0)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                A_tone_sound();
            }
            else if (position == 1)
            {


                counter= new MyCount(3000,1000);
                counter.start();
                Air_Horn_sound();
            }
            else if (position == 2)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                cheering_sound();
            }
            else if (position == 3)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                croedBoo_sound();
            }
            else if (position == 4)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                cheering_sound();
            }

            else if (position == 5)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                Doorbell_sound();
            }
            else if (position == 6)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                Japanese_Temple_sound();
            }
            else if (position == 7)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                Sad_Trombone_sound();
            }
            else if (position == 8)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                Store_Door_Chime();
            }
            else if (position == 9)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                Ta_Da_sound();
            }
            else if (position == 10)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                Temple_sound();
            }
            else if (position == 11)
            {
                counter= new MyCount(3000,1000);
                counter.start();
                Two_Tone_sound();
            }

        }


    }



    public  void Two_Tone_sound()
    {

        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }

        try
        {

            AssetFileDescriptor descriptor = context.getAssets().openFd("Temple_Bell.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public  void Temple_sound()
    {

        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }


        try
        {

            AssetFileDescriptor descriptor = context.getAssets().openFd("Temple_Bell.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public  void Ta_Da_sound()
    {

        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }

        try
        {


            AssetFileDescriptor descriptor = context.getAssets().openFd("Ta_Da.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public  void Store_Door_Chime()
    {
        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }



        try
        {
            AssetFileDescriptor descriptor = context.getAssets().openFd("Store_Door_Chime.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }




    public  void Sad_Trombone_sound()
    {
        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }


        try
        {
            AssetFileDescriptor descriptor = context.getAssets().openFd("Japanese_Temple_Bell_Small.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public  void Japanese_Temple_sound()
    {
        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }


        try
        {


            AssetFileDescriptor descriptor = context.getAssets().openFd("Japanese_Temple_Bell_Small.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public  void Doorbell_sound()
    {
        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }


        try
        {
            AssetFileDescriptor descriptor = context.getAssets().openFd("Doorbell.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public  void croedHole_sound(){

        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }


        try
        {

            AssetFileDescriptor descriptor = context.getAssets().openFd("CrowedHole.wav");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public  void croedBoo_sound()
    {

        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }


        try
        {

            AssetFileDescriptor descriptor = context.getAssets().openFd("Crowed_Boo.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public  void cheering_sound()
    {

        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }


        try
        {


            AssetFileDescriptor descriptor = context.getAssets().openFd("Cheering.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }




    public void Air_Horn_sound()
    {

        m = new MediaPlayer();

        if(m.isPlaying())
        {
            m.stop();
            m .release();

        }

        try
        {
            AssetFileDescriptor descriptor = context.getAssets().openFd("Air_Horn.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void A_tone_sound()
    {
        m = new MediaPlayer();
        if(m.isPlaying())
        {
            m.stop();
            m .release();
        }

        try
        {
            AssetFileDescriptor descriptor = context.getAssets().openFd("A_tone.mp3");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public void playBeep()
    {
        m = new MediaPlayer();
        try
        {


            AssetFileDescriptor descriptor = context.getAssets().openFd("CrowedHole.wav");
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(true);
            m.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    public class MyCount extends CountDownTimer
    {

        public MyCount(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish()
        {
            m .stop();
            m .release();
        }
        @Override
        public void onTick(long millisUntilFinished)
        {
            s1 = millisUntilFinished;

        }


    }

    private void setlist_border()
    {

        if(theme == 0)
        {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background1));
        }
        else if (theme == 1)
        {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background2));
        }
        else if (theme == 2)
        {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background3));
        }
        else if (theme == 3)
        {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background4));
        }
        else if (theme == 4)
        {
            viewHolder.layout_view.setBackgroundColor(ContextCompat.getColor(context, R.color.view_background5));
        }
    }



}

