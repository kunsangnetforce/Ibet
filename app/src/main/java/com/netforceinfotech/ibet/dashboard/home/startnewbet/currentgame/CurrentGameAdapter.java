package com.netforceinfotech.ibet.dashboard.home.startnewbet.currentgame;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class CurrentGameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<CurrentGameData> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames=new ArrayList<>();

    public CurrentGameAdapter(Context context, List<CurrentGameData> itemList)
    {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.row_live_event, parent, false);
        CurrentGameHolder viewHolder = new CurrentGameHolder(view);
        for (int i = 0; i < itemList.size(); i++)
        {
            if (i == 0)
            {
                booleanGames.add(true);
            }
            else
            {
                booleanGames.add(false);
            }
            Log.i("looppp",""+i);



        }

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {


        if(booleanGames.get(position))
        {
            ((CurrentGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
        }
        else
        {
            ((CurrentGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
        }
        Log.i("ibet_position", "" + position);
        CurrentGameHolder currentGameHolder = (CurrentGameHolder) holder;
        currentGameHolder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                for (int i = 0; i < itemList.size(); i++)
                {
                    if (i == position)
                    {
                        ((CurrentGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
                        booleanGames.set(position, true);
                    }
                    else
                    {
                        ((CurrentGameHolder) holder).imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
                        booleanGames.set(i, false);
                    }
                }
                notifyDataSetChanged();

            }
        });

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount()
    {
        return 5;
//        return itemList.size();
    }
}
