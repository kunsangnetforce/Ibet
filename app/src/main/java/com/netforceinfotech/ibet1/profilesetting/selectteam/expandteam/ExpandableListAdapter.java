package com.netforceinfotech.ibet1.profilesetting.selectteam.expandteam;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.netforceinfotech.ibet1.R;
import com.netforceinfotech.ibet1.general.UserSessionManager;
import com.netforceinfotech.ibet1.profilesetting.selectteam.SelectTeamActivity;
import com.netforceinfotech.ibet1.profilesetting.selectteam.listofteam.TeamListData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Netforce on 7/12/2016.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    UserSessionManager userSessionManager;
    private Context _context;
    ArrayList<ExpandHeaderData> expandHeaderDatas = new ArrayList<>();
    HashMap<ExpandHeaderData, List<TeamListData>> expandChildDatas = new HashMap<>();
    private clickListner click;

    public ExpandableListAdapter(Context context, ArrayList<ExpandHeaderData> expandHeaderDatas,
                                 HashMap<ExpandHeaderData, List<TeamListData>> expandChildDatas) {
        this._context = context;
        this.expandChildDatas = expandChildDatas;
        this.expandHeaderDatas = expandHeaderDatas;
        userSessionManager = new UserSessionManager(context);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.expandChildDatas.get(this.expandHeaderDatas.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_team, parent, false);
        }
        ImageView imageViewicon = (ImageView) convertView.findViewById(R.id.logo);
        LinearLayout linearLayoutMain= (LinearLayout) convertView.findViewById(R.id.linearLayoutMain);
        final ImageView imageViewChecked = (ImageView) convertView.findViewById(R.id.imageViewChecked);
        MaterialRippleLayout materialRippleLayout = (MaterialRippleLayout) convertView.findViewById(R.id.ripple);
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.teamName);
        //final String childText = (String) getChild(groupPosition, childPosition);
        String childText = expandChildDatas.get(expandHeaderDatas.get(groupPosition)).get(childPosition).name;
        txtListChild.setText(childText);
        if (expandChildDatas.get(expandHeaderDatas.get(groupPosition)).get(childPosition).logo.length() > 1) {
            Glide.with(_context)
                    .load(expandChildDatas.get(expandHeaderDatas.get(groupPosition)).get(childPosition).logo)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(imageViewicon);
        } else {
            imageViewicon.setImageResource(R.drawable.ic_error);
        }
        imageViewChecked.setImageResource(R.drawable.ic_circle_outline);
        for (int i = 0; i < SelectTeamActivity.selectTeamDatas.size(); i++) {
            if (expandChildDatas.get(expandHeaderDatas.get(groupPosition)).get(childPosition).id.equalsIgnoreCase(SelectTeamActivity.selectTeamDatas.get(i).id)) {
                imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
            }
        }
        materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("kunsangmessage", "clicked");
                TeamListData teamListData = expandChildDatas.get(expandHeaderDatas.get(groupPosition)).get(childPosition);

                if (!SelectTeamActivity.selectTeamDatas.contains(teamListData)) {

                    SelectTeamActivity.selectTeamDatas.add(teamListData);
                }
                imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
                SelectTeamActivity.selectedTeamAdapter.notifyDataSetChanged();

            }
        });
        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.itemClicked(finalConvertView, groupPosition, childPosition);
            }
        });
        switch (userSessionManager.getTheme()){
            case 0:
            //    linearLayoutMain.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimary));
                break;
            case 1:
                linearLayoutMain.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryBrown));
                break;
            case 2:
                linearLayoutMain.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryPurple));
                break;
            case 3:
                linearLayoutMain.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryGreen));
                break;
            case 4:
                linearLayoutMain.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryMarron));
                break;
            case 5:
                linearLayoutMain.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryLightBlue));
                break;
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandChildDatas.get(this.expandHeaderDatas.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandHeaderDatas.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandHeaderDatas.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = expandHeaderDatas.get(groupPosition).name;
        LayoutInflater infalInflater = (LayoutInflater) this._context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.list_group, parent, false);
        RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        TextView textViewCompetitionName = (TextView) convertView.findViewById(R.id.textViewCompetitionName);
        textViewCompetitionName.setText(headerTitle);
        if (isExpanded) {
            imageView.setImageResource(R.drawable.ic_chevron_primary);
            notifyDataSetChanged();
        } else {
            imageView.setImageResource(R.drawable.ic_chevron_grey);
            notifyDataSetChanged();
        }
        switch (userSessionManager.getTheme()) {
            case 0:
              /*  relativeLayout.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryDark));
                textViewCompetitionName.setTextColor(ContextCompat.getColor(_context,R.color.colorAccent));
              */  break;
            case 1:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryDarkBrown));
                textViewCompetitionName.setTextColor(ContextCompat.getColor(_context,R.color.colorAccentBrown));
                break;
            case 2:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryDarkPurple));
                textViewCompetitionName.setTextColor(ContextCompat.getColor(_context,R.color.colorAccentPurple));
                break;
            case 3:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryDarkGreen));
                textViewCompetitionName.setTextColor(ContextCompat.getColor(_context,R.color.colorAccentGreen));
                break;
            case 4:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryDarkMarron));
                textViewCompetitionName.setTextColor(ContextCompat.getColor(_context,R.color.colorAccentMarron));
                break;
            case 5:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context,R.color.colorPrimaryDarkLightBlue));
                textViewCompetitionName.setTextColor(ContextCompat.getColor(_context,R.color.colorAccentLightBlue));
                break;

        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public interface clickListner {
        void itemClicked(View view, int groupview, int childview);
    }

    public void setClickListner(clickListner click) {
        this.click = click;
    }
}