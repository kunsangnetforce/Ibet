package com.netforceinfotech.ibet.dashboard.ThemeColor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
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
import com.netforceinfotech.ibet.dashboard.Dashboard;
import com.netforceinfotech.ibet.general.UserSessionManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by John on 7/29/2016.
 */
public class ThemeColorAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{


    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    Activity context;
    ArrayList<Integer> setting_icon = new ArrayList<>();

    UserSessionManager userSessionManager;

    public ThemeColorAdapter(Activity context,ArrayList<Integer> imagelist)
    {

        this.context = context;
        this.setting_icon = imagelist;
        inflater = LayoutInflater.from(context);
        userSessionManager = new UserSessionManager(context);

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

        View view = inflater.inflate(R.layout.row_theme, parent, false);

        viewHolder = new SettingHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {
        viewHolder.image_icon.setImageResource(setting_icon.get(position));

    }

    private void showMessage(String s)
    {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();


    }


    @Override
    public int getItemCount()
    {
        if (setting_icon == null)
            return 0;

        return setting_icon.size();

    }


    public class SettingHolder  extends RecyclerView.ViewHolder implements  View.OnClickListener
    {

        ImageView image_icon;
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

            image_icon.setOnClickListener(this);


        }

        @Override
        public void onClick(View view)
        {

            if(getAdapterPosition() == 0)
            {

                userSessionManager.setTheme(0);
            }
            else  if(getAdapterPosition() == 1)
            {
                userSessionManager.setTheme(1);
            }
            else  if(getAdapterPosition() == 2)
            {
                userSessionManager.setTheme(2);
            }
            else  if(getAdapterPosition() == 3)
            {
                userSessionManager.setTheme(3);
            }

            context.finish();
            Intent intent  = new Intent(context, Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);


        }
    }

}

