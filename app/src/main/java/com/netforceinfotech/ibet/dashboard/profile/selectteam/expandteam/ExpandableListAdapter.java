package com.netforceinfotech.ibet.dashboard.profile.selectteam.expandteam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.profile.selectteam.SelectTeamActivity;
import com.netforceinfotech.ibet.dashboard.profile.selectteam.listofteam.TeamListData;
import com.netforceinfotech.ibet.dashboard.profile.selectteam.selectedteam.SelectedTeamData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Netforce on 7/12/2016.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    ArrayList<ExpandHeaderData> expandHeaderDatas = new ArrayList<>();
    HashMap<ExpandHeaderData, List<TeamListData>> expandChildDatas = new HashMap<>();
    private clickListner click;

    public ExpandableListAdapter(Context context, ArrayList<ExpandHeaderData> expandHeaderDatas,
                                 HashMap<ExpandHeaderData, List<TeamListData>> expandChildDatas) {
        this._context = context;
        this.expandChildDatas = expandChildDatas;
        this.expandHeaderDatas = expandHeaderDatas;
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
        final ImageView imageViewChecked = (ImageView) convertView.findViewById(R.id.imageViewChecked);
        MaterialRippleLayout materialRippleLayout = (MaterialRippleLayout) convertView.findViewById(R.id.ripple);
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.teamName);
        //final String childText = (String) getChild(groupPosition, childPosition);
        String childText = expandChildDatas.get(expandHeaderDatas.get(groupPosition)).get(childPosition).name;
        txtListChild.setText(childText);
        if (expandChildDatas.get(expandHeaderDatas.get(groupPosition)).get(childPosition).logo.length() > 1) {
            Picasso.with(_context)
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
                SelectedTeamData selectedTeamData = new SelectedTeamData(teamListData.id, teamListData.name, teamListData.logo, teamListData.compid, teamListData.compName);

                if (!SelectTeamActivity.selectTeamDatas.contains(selectedTeamData)) {

                    SelectTeamActivity.selectTeamDatas.add(selectedTeamData);
                }
                imageViewChecked.setImageResource(R.drawable.ic_circle_filled);
                SelectTeamActivity.selectTeamAdapter.notifyDataSetChanged();
                if (expandChildDatas.size() > 0) {
                    SelectTeamActivity.linearLayoutSelectedTeams.setVisibility(View.VISIBLE);
                }

            }
        });
        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.itemClicked(finalConvertView, groupPosition, childPosition);
            }
        });
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