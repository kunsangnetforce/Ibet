package com.netforceinfotech.ibet.dashboard.language;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.Chart.HighestFragmentData;
import com.netforceinfotech.ibet.dashboard.Chart.HighestHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asdf on 7/21/2016.
 */


public class languageAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private ArrayList<String> itemList;
    LanguageHolder viewHolder;

    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();




    public languageAdapter(Context context, ArrayList<String> itemList)
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

        View view = inflater.inflate(R.layout.row_language, parent, false);
         viewHolder = new LanguageHolder(view);

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




}
