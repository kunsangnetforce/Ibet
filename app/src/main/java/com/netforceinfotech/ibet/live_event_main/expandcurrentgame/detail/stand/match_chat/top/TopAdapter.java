package com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.top;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.live_event_main.expandcurrentgame.detail.stand.match_chat.comments_comment.CommentComments;
import com.netforceinfotech.ibet.util.Util;
import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class TopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<TopData> itemList;
    private Context context;
    String matchid, team;
    RequestManager glide;

    public TopAdapter(Context context, List<TopData> itemList, String matchid, String team, RequestManager with) {
        this.itemList = itemList;
        this.context = context;
        this.team=team;
        glide=with;
        this.matchid=matchid;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_chat, parent, false);
        TopHolder viewHolder = new TopHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TopHolder topHolder = (TopHolder) holder;
        TopData topData = itemList.get(position);
        try {
            glide.load(topData.imageurl).error(R.drawable.ic_error).into(topHolder.circleImageView);
        } catch (Exception ex) {
            glide.load(R.drawable.ic_error).into(topHolder.circleImageView);
        }
        topHolder.textViewCC.setText(topData.message);
        topHolder.textViewSC.setText(topData.share);
        topHolder.textViewDC.setText(topData.dislike);
        topHolder.textViewLC.setText(topData.like);
        topHolder.textViewComment.setText(topData.comment);
        topHolder.textViewDate.setText(Util.getDateCurrentTimeZone(topData.timestamp));
        topHolder.textViewTime.setText(Util.getTimeCurrentTimeZone(topData.timestamp));
        topHolder.imageViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("kunsangvalue", itemList.get(position).like + ":" + itemList.get(position).dislike);
                Intent intent = new Intent(context, CommentComments.class);
                Bundle bundle = new Bundle();
                bundle.putString("match_id", matchid);
                bundle.putString("team", team);
                bundle.putString("commentkey", itemList.get(position).key);
                bundle.putString("from", "all");
                bundle.putString("dp", itemList.get(position).imageurl);
                bundle.putString("name", itemList.get(position).name);
                bundle.putString("comment", itemList.get(position).comment);
                bundle.putString("dislikecount", itemList.get(position).dislike);
                bundle.putString("likecount", itemList.get(position).like);
                bundle.putString("sharecount", itemList.get(position).share);
                bundle.putLong("timestamp", itemList.get(position).timestamp);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }
}
