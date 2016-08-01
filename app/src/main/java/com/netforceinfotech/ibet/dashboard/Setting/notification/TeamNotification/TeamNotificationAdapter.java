package com.netforceinfotech.ibet.dashboard.Setting.notification.TeamNotification;

import android.content.Context;
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
import com.netforceinfotech.ibet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 7/26/2016.
 */




public class TeamNotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{



    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<String> itemList;
    private Context context;

    ArrayList<Integer> setting_icon = new ArrayList<>();


    public TeamNotificationAdapter(Context context, List<String> itemList,ArrayList<Integer> imagelist)
    {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.row_teamnotification, parent, false);

        viewHolder = new SettingHolder(view);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

        viewHolder.textViewTitle.setText(itemList.get(position));
        viewHolder.image_icon.setImageResource(setting_icon.get(position));

    }

    private void showMessage(String s)
    {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount()
    {
        if (itemList == null)
            return 0;
       return itemList.size();

    }


    public class SettingHolder  extends RecyclerView.ViewHolder
    {


        TextView textViewTitle, textViewCategory, textViewPros;
        ImageView image_icon,setting_image_icon;
        MaterialRippleLayout materialRippleLayout;
        View view;
       FrameLayout delete_layout ;

        public SettingHolder(View itemView)
        {
            super(itemView);
            //implementing onClickListener

            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

            image_icon = (ImageView)  itemView.findViewById(R.id.setting_list_icon);
            textViewTitle = (TextView) itemView.findViewById(R.id.setting_list_text);

            delete_layout = (FrameLayout) itemView.findViewById(R.id.delete_layout);

            setting_image_icon  = (ImageView)itemView.findViewById(R.id.setting_icon);


            delete_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                }
            });

            setting_image_icon.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                     Intent  intent =  new Intent(context, SoundlistActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                      context.startActivity(intent);


                }
            });


        }

    }

}

