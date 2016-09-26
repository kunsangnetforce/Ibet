package com.netforceinfotech.ibet.currentbet.livebet;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.general.UserSessionManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveBetFragment extends Fragment {
    Context context;
    private RecyclerView recyclerView;
    ArrayList<LiveBetData> liveBetDatas = new ArrayList<>();
    private LiveBetAdapter adapter;
    UserSessionManager userSessionManager;
    int theme;
    FrameLayout livebet;
    private ExpandableListView expListView;
    HashMap<String, List<LiveBetData>> listDataChild = new HashMap<String, List<LiveBetData>>();

    public LiveBetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_bet, container, false);
        Log.i("testingkunsang", "reahced livebet");
        context = getActivity();

        userSessionManager = new UserSessionManager(getActivity());
        theme = userSessionManager.getTheme();
        setupRecyclerView(view);
        setupRecyclerView(view);
        setupliveBetDatas();
        return view;


    }


    private void setupRecyclerView(View view) {

        livebet = (FrameLayout) view.findViewById(R.id.livebet);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        setupliveBetDatas();
        adapter = new LiveBetAdapter(context, liveBetDatas);
        recyclerView.setAdapter(adapter);


    }

    private void setupliveBetDatas() {
        try {
            liveBetDatas.clear();
        } catch (Exception ex) {

        }
        liveBetDatas.add(new LiveBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        liveBetDatas.add(new LiveBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        liveBetDatas.add(new LiveBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        liveBetDatas.add(new LiveBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        liveBetDatas.add(new LiveBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        liveBetDatas.add(new LiveBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        liveBetDatas.add(new LiveBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        liveBetDatas.add(new LiveBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));
        liveBetDatas.add(new LiveBetData("", "Roney Singh", "", "Barcelona", "20", "44", "12:30", "", "", "Barcelona", "Realmadreid", "You Win", "23"));

    }
}
