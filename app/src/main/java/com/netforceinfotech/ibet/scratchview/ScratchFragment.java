package com.netforceinfotech.ibet.scratchview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.netforceinfotech.ibet.R;
import com.winsontan520.WScratchView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScratchFragment extends Fragment {

    RelativeLayout relativeLayoutCounter;
    WScratchView scratchView0, scratchView1, scratchView2, scratchView3, scratchView4, scratchView5, scratchView6, scratchView7, scratchView8;
    ImageView imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8;
    ArrayList<ScratchData> scratchDatas = new ArrayList<>();
    ArrayList<Type> types = new ArrayList<>();
    ArrayList<Boolean> revealedList = new ArrayList<>();

    public ScratchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\
        View view = inflater.inflate(R.layout.fragment_scratch, container, false);
        setDummyScratchData();
        initView(view);
        return view;
    }

    private void setDummyScratchData() {

        try {
            revealedList.clear();
            scratchDatas.clear();
            types.clear();
        } catch (Exception ex) {

        }
        for (int i = 0; i < 9; i++) {
            revealedList.add(false);
        }
        //ScratchData(int position, String value, String imgUrl, String type) {
        scratchDatas.add(new ScratchData(0, 20, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(1, 1000, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(2, 10, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(3, 1000, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(4, 500, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(5, 10, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(6, 20, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(7, 50, R.drawable.coin, ""));
        scratchDatas.add(new ScratchData(8, 20, R.drawable.coin, ""));
        for (int i = 0; i < 9; i++) {
            int value = scratchDatas.get(i).value;
            Type type = new Type(value, 0);
            if (!types.contains(type)) {
                types.add(type);
            }
        }

    }

    private void initView(View view) {
        relativeLayoutCounter = (RelativeLayout) view.findViewById(R.id.relativeLayoutCounter);
        imageView0 = (ImageView) view.findViewById(R.id.imageView0);
        imageView1 = (ImageView) view.findViewById(R.id.imageView1);
        imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        imageView4 = (ImageView) view.findViewById(R.id.imageView4);
        imageView5 = (ImageView) view.findViewById(R.id.imageView5);
        imageView6 = (ImageView) view.findViewById(R.id.imageView6);
        imageView7 = (ImageView) view.findViewById(R.id.imageView7);
        imageView8 = (ImageView) view.findViewById(R.id.imageView8);
        scratchView0 = (WScratchView) view.findViewById(R.id.scratchView0);
        scratchView1 = (WScratchView) view.findViewById(R.id.scratchView1);
        scratchView2 = (WScratchView) view.findViewById(R.id.scratchView2);
        scratchView3 = (WScratchView) view.findViewById(R.id.scratchView3);
        scratchView4 = (WScratchView) view.findViewById(R.id.scratchView4);
        scratchView5 = (WScratchView) view.findViewById(R.id.scratchView5);
        scratchView6 = (WScratchView) view.findViewById(R.id.scratchView6);
        scratchView7 = (WScratchView) view.findViewById(R.id.scratchView7);
        scratchView8 = (WScratchView) view.findViewById(R.id.scratchView8);

        scratchView0.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(0)) {
                        revealedList.set(0, true);
                        checkGiftStatus(0);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView1.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(1)) {
                        revealedList.set(1, true);
                        checkGiftStatus(1);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView2.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(2)) {
                        revealedList.set(2, true);
                        checkGiftStatus(2);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView3.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(3)) {
                        revealedList.set(3, true);
                        checkGiftStatus(3);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView4.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(4)) {
                        revealedList.set(4, true);
                        checkGiftStatus(4);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView5.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(5)) {
                        revealedList.set(5, true);
                        checkGiftStatus(5);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView6.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(6)) {
                        revealedList.set(6, true);
                        checkGiftStatus(6);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView7.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(7)) {
                        revealedList.set(7, true);
                        checkGiftStatus(7);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
        scratchView8.setOnScratchCallback(new WScratchView.OnScratchCallback() {
            @Override
            public void onScratch(float v) {
                if (v > 85) {
                    if (!revealedList.get(8)) {
                        revealedList.set(8, true);
                        checkGiftStatus(8);
                    }
                }
            }

            @Override
            public void onDetach(boolean b) {

            }
        });
    }

    private void checkGiftStatus(int position) {
        int value = scratchDatas.get(position).value;
        for (int i = 0; i < types.size(); i++) {
            if (value == types.get(i).value) {
                types.get(i).count++;
                if (types.get(i).count >= 3) {
                    showMessage("Congrats");
                }
                break;
            }
        }
    }

    private void showMessage(String congrats) {
        Toast.makeText(getActivity(), congrats, Toast.LENGTH_SHORT).show();
    }

}
