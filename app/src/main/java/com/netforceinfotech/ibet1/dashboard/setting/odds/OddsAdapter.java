package com.netforceinfotech.ibet1.dashboard.setting.odds;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 7/25/2016.
 */
public class OddsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{


    OddsHolder viewHolder;
    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<String> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();
    ArrayList<Integer> setting_icon = new ArrayList<>();
    int theme;
    UserSessionManager userSessionManager;



    public OddsAdapter(Context context, List<String> itemList,ArrayList<Integer> imagelist)
    {
        this.itemList = itemList;
        this.context = context;
        this.setting_icon = imagelist;
        inflater = LayoutInflater.from(context);

        userSessionManager = new UserSessionManager(context);
        theme = userSessionManager.getTheme();


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.row_odds, parent, false);
        viewHolder = new OddsHolder(view);

        setlist_border();

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


    public class OddsHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener
    {
        TextView textViewTitle, textViewCategory, textViewPros;
        MaterialRippleLayout materialRippleLayout;
        View view,layout_view;


        public OddsHolder(View itemView)
        {
            super(itemView);
            //implementing onClickListener
            itemView.setOnClickListener(this);
            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);


            textViewTitle = (TextView) itemView.findViewById(R.id.setting_list_text);

            layout_view = (View)  itemView.findViewById(R.id.view);

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
