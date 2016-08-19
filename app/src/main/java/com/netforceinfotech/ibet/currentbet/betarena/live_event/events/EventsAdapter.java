package com.netforceinfotech.ibet.currentbet.betarena.live_event.events;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL = 0;
    private static final int START = 1;
    private final LayoutInflater inflater;
    private Context context;
    ArrayList<EventsData> eventsDatas;

    public EventsAdapter(Context context, ArrayList<EventsData> eventsDatas) {
        this.eventsDatas = eventsDatas;
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
    public int getItemViewType(int position) {
        if (eventsDatas.get(position).nameb.trim().length() < 1 && eventsDatas.get(position).namea.trim().length() < 1) {
            return START;
        } else {
            return NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == NORMAL) {
            View view = inflater.inflate(R.layout.row_event, parent, false);
            EventsHolder viewHolder = new EventsHolder(view);
            return viewHolder;
        } else {
            View view = inflater.inflate(R.layout.row_event_start, parent, false);
            EventsHolder viewHolder = new EventsHolder(view);
            return viewHolder;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (position != eventsDatas.size() - 1) {
            EventsHolder holder1 = (EventsHolder) holder;
            if (eventsDatas.get(position).namea.trim().length() < 1) {
                holder1.linearLayouta.setVisibility(View.INVISIBLE);
                holder1.textViewTime.setText(eventsDatas.get(position).time+"'");
                switch (eventsDatas.get(position).event) {
                    case "red":
                        holder1.imageViewTypeb.setImageResource(R.drawable.ic_card_red);
                        break;
                    case "yellow":
                        holder1.imageViewTypeb.setImageResource(R.drawable.ic_card_yellow);
                        break;
                    case "goal":
                        holder1.imageViewTypeb.setImageResource(R.drawable.ball);
                        break;
                }
                holder1.textViewNameb.setText(eventsDatas.get(position).nameb);

            } else {
                holder1.linearLayoutb.setVisibility(View.INVISIBLE);
                holder1.textViewTime.setText(eventsDatas.get(position).time);
                switch (eventsDatas.get(position).event) {
                    case "red":
                        holder1.imageViewTypea.setImageResource(R.drawable.ic_card_red);
                        break;
                    case "yellow":
                        holder1.imageViewTypea.setImageResource(R.drawable.ic_card_yellow);
                        break;
                    case "goal":
                        holder1.imageViewTypea.setImageResource(R.drawable.ball);
                        break;
                }
                holder1.textViewNamea.setText(eventsDatas.get(position).namea);
            }
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return eventsDatas.size();
//        return itemList.size();
    }
}
