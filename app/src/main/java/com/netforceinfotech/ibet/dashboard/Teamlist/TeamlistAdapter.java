package com.netforceinfotech.ibet.dashboard.Teamlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;

import java.util.List;

/**
 * Created by John on 7/25/2016.
 */
public class TeamlistAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{


    TeamlistHolder viewHolder;

    private final LayoutInflater inflater;
    private List<String> itemList;
    private Context context;



    public TeamlistAdapter(Context context, List<String> itemList)
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
        viewHolder = new TeamlistHolder(view);

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
        return 3;
//        return itemList.size();
    }


    public class TeamlistHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener
    {


        TextView textViewTitle, textViewCategory, textViewPros;

        MaterialRippleLayout materialRippleLayout;
        View view;


        public TeamlistHolder(View itemView)
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

            if(position==0)
            {



            }

        }
    }

}

