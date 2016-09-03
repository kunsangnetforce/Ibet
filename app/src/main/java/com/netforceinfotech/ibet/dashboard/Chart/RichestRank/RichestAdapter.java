package com.netforceinfotech.ibet.dashboard.chart.richestrank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by John on 7/25/2016.
 */
public class RichestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<RichestData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();



    public RichestAdapter(Context context, List<RichestData> itemList)
    {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.row_richest_rank, parent, false);
        RichestHolder viewHolder = new RichestHolder(view);
        for (int i = 0; i < itemList.size(); i++) {
            if (i == 0) {
                booleanGames.add(true);
            } else {
                booleanGames.add(false);
            }
            Log.i("looppp", "" + i);
        }

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

    }

    private void showMessage(String s)
    {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return 12;
//        return itemList.size();
    }


    public class RichestHolder extends RecyclerView.ViewHolder {


        TextView textViewTitle, textViewCategory, textViewPros;

        MaterialRippleLayout materialRippleLayout;
        View view;


        public RichestHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

        }
    }
}