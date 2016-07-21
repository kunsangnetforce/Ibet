package com.netforceinfotech.ibet.dashboard.ODDS;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.language.LanguageHolder;

import java.util.ArrayList;

/**
 * Created by asdf on 7/21/2016.
 */
public class OddsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private ArrayList<String> itemList;
    OddsHolder viewHolder;

    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();




    public OddsAdapter(Context context, ArrayList<String> itemList)
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
        viewHolder = new OddsHolder(view);

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

    public class OddsHolder  extends RecyclerView.ViewHolder {


        TextView textViewTitle;

        MaterialRippleLayout materialRippleLayout;
        View view;


        public OddsHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            view = itemView;

            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

            textViewTitle = (TextView) itemView.findViewById(R.id.txt_language);
        }
    }


}
