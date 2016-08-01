package com.netforceinfotech.ibet.dashboard.Setting.Sounds;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 7/26/2016.
 */
public class SoundAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    SettingHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<String> itemList;
    private Context context;

    ArrayList<Integer> setting_icon = new ArrayList<>();

    public SoundAdapter(Context context, List<String> itemList,ArrayList<Integer> imagelist)
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

        View view = inflater.inflate(R.layout.row_sound_activity, parent, false);
        viewHolder = new SettingHolder(view);

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
    public int getItemCount()
    {
        return 4;
//        return itemList.size();
    }


    public class SettingHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener
    {


        TextView textViewTitle, textViewCategory, textViewPros;

        MaterialRippleLayout materialRippleLayout;
        View view;


        public SettingHolder(View itemView)
        {
            super(itemView);
            //implementing onClickListener
            itemView.setOnClickListener(this);
            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);


            textViewTitle = (TextView) itemView.findViewById(R.id.setting_list_text);

        }
        @Override
        public void onClick(View v)
        {

            int position  =   getAdapterPosition();

            if(position==0)
            {



            }

        }
    }

}

