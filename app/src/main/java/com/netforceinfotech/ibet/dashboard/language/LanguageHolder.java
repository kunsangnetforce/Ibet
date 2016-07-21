package com.netforceinfotech.ibet.dashboard.language;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;

/**
 * Created by asdf on 7/21/2016.
 */
public class LanguageHolder  extends RecyclerView.ViewHolder {


    TextView textViewTitle, textViewCategory, textViewPros;

    MaterialRippleLayout materialRippleLayout;
    View view;


    public LanguageHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        view = itemView;

        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

        textViewTitle = (TextView) itemView.findViewById(R.id.txt_language);
    }
}