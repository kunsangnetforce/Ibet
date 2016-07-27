package com.netforceinfotech.ibet.dashboard.Setting;

import android.content.Context;
import android.content.Intent;
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
import com.netforceinfotech.ibet.dashboard.Feedback.FeedbackActivity;
import com.netforceinfotech.ibet.dashboard.Odds.OddsActivity;
import com.netforceinfotech.ibet.dashboard.SoundlistActivity;
import com.netforceinfotech.ibet.dashboard.Sounds.SoundActivity;
import com.netforceinfotech.ibet.dashboard.Theme.ThemeActivity;
import com.netforceinfotech.ibet.dashboard.language.LanguageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asdf on 7/22/2016.
 */
public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{



    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<String> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();
    ArrayList<Integer> setting_icon = new ArrayList<>();

    public SettingAdapter(Context context, List<String> itemList,ArrayList<Integer> imagelist)
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

        View view = inflater.inflate(R.layout.row_setting, parent, false);
         viewHolder = new SettingHolder(view);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

        viewHolder.textViewTitle.setText(itemList.get(position));
        viewHolder.image_icon.setImageResource(setting_icon.get(position));;

    }

    private void showMessage(String s)
    {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return 9;
//        return itemList.size();
    }


    public class SettingHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener
    {


        TextView textViewTitle, textViewCategory, textViewPros;
        ImageView image_icon;
        MaterialRippleLayout materialRippleLayout;
        View view;


        public SettingHolder(View itemView)
        {
            super(itemView);
            //implementing onClickListener
            itemView.setOnClickListener(this);
            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

            image_icon = (ImageView)  itemView.findViewById(R.id.setting_list_icon);
            textViewTitle = (TextView) itemView.findViewById(R.id.setting_list_text);

        }
        @Override
        public void onClick(View v)
        {

            int position  =   getAdapterPosition();
            final Intent intent;

            if (position == 0)
            {
                intent =  new Intent(context, LanguageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else if (position == 1)
            {
                intent =  new Intent(context, TeamNotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else if (position == 2)
            {
                intent =  new Intent(context, GeneralNotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else if (position == 3)
            {
                intent =  new Intent(context, SoundActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else if (position == 4)
            {
                intent =  new Intent(context, ThemeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else if (position == 5)
            {
                intent =  new Intent(context, OddsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else if (position == 6)
            {
                intent =  new Intent(context, OddsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else if (position == 7)
            {
                intent =  new Intent(context, FeedbackActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else if (position == 8)
            {
                intent =  new Intent(context, FeedbackActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            else
            {
                intent =  new Intent(context, TeamNotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);


        }




    }

}
