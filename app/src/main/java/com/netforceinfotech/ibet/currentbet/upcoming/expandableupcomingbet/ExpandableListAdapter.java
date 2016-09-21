package com.netforceinfotech.ibet.currentbet.upcoming.expandableupcomingbet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.live_event_main.CurrentGameData;
import com.netforceinfotech.ibet.live_event_main.LiveEventActivity;
import com.squareup.picasso.Picasso;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private ArrayList<ExpandHeaderData> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<CurrentGameData>> _listDataChild;

    public ExpandableListAdapter(Context context, ArrayList<ExpandHeaderData> listDataHeader,
                                 HashMap<String, List<CurrentGameData>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        ExpandHeaderData header = (ExpandHeaderData) getGroup(groupPosition);
        return this._listDataChild.get(header.id)
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final CurrentGameData currentGameData = (CurrentGameData) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_live_event, null);
        }
        ImageView imageViewTeamA, imageViewTeamB;
        TextView textViewTeamA, textViewTeamB;
        MaterialRippleLayout materialRippleLayout;
        materialRippleLayout = (MaterialRippleLayout) convertView.findViewById(R.id.ripple);
        textViewTeamA = (TextView) convertView.findViewById(R.id.textViewTeamA);
        textViewTeamB = (TextView) convertView.findViewById(R.id.textViewTeamB);
        imageViewTeamA = (ImageView) convertView.findViewById(R.id.imageViewTeamA);
        imageViewTeamB = (ImageView) convertView.findViewById(R.id.imageViewTeamB);

        textViewTeamA.setText(currentGameData.teama);
        textViewTeamB.setText(currentGameData.teamb);
        if (currentGameData.logoa.length() > 1) {
            Picasso.with(_context)
                    .load(currentGameData.logoa)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(imageViewTeamA);
        } else {
            Picasso.with(_context)
                    .load(R.drawable.ic_error)
                    .into(imageViewTeamA);
        }
        if (currentGameData.logob.length() > 1) {
            Picasso.with(_context)
                    .load(currentGameData.logob)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(imageViewTeamB);
        } else {
            Picasso.with(_context)
                    .load(R.drawable.ic_error)
                    .into(imageViewTeamB);
        }
        materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_context, LiveEventActivity.class);
                String teamaid = currentGameData.teamaid;
                String teambid = currentGameData.teambid;
                String matchid = currentGameData.matchid;
                String teama = currentGameData.teama;
                String teamb = currentGameData.teamb;
                Bundle bundle = new Bundle();
                Log.i("kunsangadapter", matchid + " " + teamaid + " " + teambid);

                bundle.putString("home_id", teamaid);
                bundle.putString("away_id", teambid);
                bundle.putString("home_name", teama);
                bundle.putString("away_name", teamb);
                bundle.putString("match_id", matchid);
                intent.putExtras(bundle);
                _context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ExpandHeaderData header = (ExpandHeaderData) getGroup(groupPosition);
        return this._listDataChild.get(header.id)
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ExpandHeaderData header = (ExpandHeaderData) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        TextView textview = (TextView) convertView.findViewById(R.id.textViewCompetitionName);
        textview.setText(header.name);


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
}