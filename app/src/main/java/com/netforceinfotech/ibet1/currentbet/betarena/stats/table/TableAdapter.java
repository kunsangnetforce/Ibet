package com.netforceinfotech.ibet1.currentbet.betarena.stats.table;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<TableData> itemList;
    private Context context;
    String home_id, away_id;
    UserSessionManager userSessionManager;

    public TableAdapter(Context context, List<TableData> itemList, String home_id, String away_id) {
        this.itemList = itemList;
        this.context = context;
        this.home_id = home_id;
        this.away_id = away_id;
        userSessionManager = new UserSessionManager(context);
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_table, parent, false);
        TableHolder viewHolder = new TableHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TableHolder tableHolder = (TableHolder) holder;
        try {
            Glide.with(context).load(itemList.get(position).logo).error(R.drawable.ic_error).into(tableHolder.imageView);
        } catch (Exception ex) {
            Glide.with(context).load(R.drawable.ic_error).into(tableHolder.imageView);
        }
        tableHolder.textViewPoints.setText(itemList.get(position).points);
        tableHolder.textViewPosition.setText(itemList.get(position).position + "");
        tableHolder.textViewGD.setText(itemList.get(position).goalDiff);
        tableHolder.textViewGP.setText(itemList.get(position).overall_played);
        tableHolder.textViewName.setText(itemList.get(position).name);
        if (itemList.get(position).id.equalsIgnoreCase(home_id) || itemList.get(position).id.equalsIgnoreCase(away_id)) {
            tableHolder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
        } else {
            switch (userSessionManager.getTheme()) {
                case 0:
                    tableHolder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                    break;
                case 1:
                    tableHolder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryBrown));

                    break;
                case 2:
                    tableHolder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryPurple));

                    break;
                case 3:
                    tableHolder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryGreen));
                    break;
                case 4:
                    tableHolder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryMarron));

                    break;
                case 5:
                    tableHolder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLightBlue));

                    break;
            }
        }

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }
}
