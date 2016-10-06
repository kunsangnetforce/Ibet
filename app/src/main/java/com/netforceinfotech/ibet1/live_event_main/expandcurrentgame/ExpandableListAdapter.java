package com.netforceinfotech.ibet1.live_event_main.expandcurrentgame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.netforceinfotech.ibet1.live_event_main.CurrentGameData;
import com.netforceinfotech.ibet1.live_event_main.LiveEventActivity;
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    UserSessionManager userSessionManager;
    private Context _context;
    private ArrayList<ExpandHeaderData> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<CurrentGameData>> _listDataChild;

    public ExpandableListAdapter(Context context, ArrayList<ExpandHeaderData> listDataHeader,
                                 HashMap<String, List<CurrentGameData>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        userSessionManager = new UserSessionManager(context);
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
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
        TextView textViewVs = (TextView) convertView.findViewById(R.id.textViewVs);
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
            Glide.with(_context)
                    .load(currentGameData.logoa)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(imageViewTeamA);
        } else {
            Glide.with(_context)
                    .load(R.drawable.ic_error)
                    .into(imageViewTeamA);
        }
        if (currentGameData.logob.length() > 1) {
            Glide.with(_context)
                    .load(currentGameData.logob)
                    .placeholder(R.drawable.ic_holder)
                    .error(R.drawable.ic_error)
                    .into(imageViewTeamB);
        } else {
            Glide.with(_context)
                    .load(R.drawable.ic_error)
                    .into(imageViewTeamB);
        }
        materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_context, LiveEventActivity.class);
                String home_id = currentGameData.teamaid;
                String away_id = currentGameData.teambid;
                String match_id = currentGameData.matchid;
                String home_name = currentGameData.teama;
                String away_name = currentGameData.teamb;
                String home_logo = currentGameData.logoa;
                String away_logo = currentGameData.logob;
                String season_id = currentGameData.season_id;
                Bundle bundle = new Bundle();
                Log.i("kunsangadapter", match_id + " " + home_id + " " + away_id);

                bundle.putString("home_id", home_id);
                bundle.putString("away_id", away_id);
                bundle.putString("home_name", home_name);
                bundle.putString("away_name", away_name);
                bundle.putString("match_id", match_id);
                bundle.putString("home_logo", home_logo);
                bundle.putString("away_logo", away_logo);
                bundle.putString("season_id", season_id);
                intent.putExtras(bundle);
                _context.startActivity(intent);
            }
        });
        switch (userSessionManager.getTheme()) {
            case 0:
              /*  linearLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimary));
                textViewTeamA.setTextColor(ContextCompat.getColor(_context, R.color.colorAccent));
                textViewTeamB.setTextColor(ContextCompat.getColor(_context, R.color.colorAccent));
                textViewVs.setTextColor(ContextCompat.getColor(_context, R.color.colorAccent));
              */
                break;
            case 1:
                linearLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryBrown));
                textViewTeamA.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentBrown));
                textViewTeamB.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentBrown));
                textViewVs.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentBrown));
                break;
            case 2:
                linearLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryPurple));
                textViewTeamA.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentPurple));
                textViewTeamB.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentPurple));
                textViewVs.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentPurple));
                break;
            case 3:
                linearLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryGreen));
                textViewTeamA.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentGreen));
                textViewTeamB.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentGreen));
                textViewVs.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentGreen));
                break;
            case 4:
                linearLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryMarron));
                textViewTeamA.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentMarron));
                textViewTeamB.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentMarron));
                textViewVs.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentMarron));
                break;
            case 5:
                linearLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryLightBlue));
                textViewTeamA.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentLightBlue));
                textViewTeamB.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentLightBlue));
                textViewVs.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentLightBlue));
                break;

        }
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
        RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout);
        TextView textview = (TextView) convertView.findViewById(R.id.textViewCompetitionName);
        textview.setText(header.name);
        switch (userSessionManager.getTheme()) {
            case 0:
              /*  relativeLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryDark));
                textview.setTextColor(ContextCompat.getColor(_context, R.color.colorAccent));
            */
                break;
            case 1:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryDarkBrown));
                textview.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentBrown));
                break;
            case 2:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryDarkPurple));
                textview.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentPurple));
                break;
            case 3:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryDarkGreen));
                textview.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentGreen));
                break;
            case 4:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryDarkMarron));
                textview.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentMarron));
                break;
            case 5:
                relativeLayout.setBackgroundColor(ContextCompat.getColor(_context, R.color.colorPrimaryDarkLightBlue));
                textview.setTextColor(ContextCompat.getColor(_context, R.color.colorAccentLightBlue));
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
}