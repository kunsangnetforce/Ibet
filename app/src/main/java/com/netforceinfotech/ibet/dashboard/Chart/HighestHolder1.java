package com.netforceinfotech.ibet.dashboard.Chart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;

/**1
 * Created by asdf on 7/21/2016.
 */
public class HighestHolder1 extends RecyclerView.ViewHolder {


    TextView textViewTitle, textViewCategory, textViewPros;

    MaterialRippleLayout materialRippleLayout;
    View view;


    public HighestHolder1(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;

        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

    }
}