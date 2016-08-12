package com.netforceinfotech.ibet.dashboard.home.create_bet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.netforceinfotech.ibet.R;

public class CreateBet extends AppCompatActivity {

    Toolbar toolbar;
    ImageView setting_list1, setting_list2, setting_list3;
    private MaterialDialog dailog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bet);
        setupToolBar("Create Bet");
        setuplayout();

    }

    private void setuplayout() {

        setting_list1 = (ImageView) findViewById(R.id.setting_list_icon);

        setting_list2 = (ImageView) findViewById(R.id.setting_list_icon2);

        setting_list3 = (ImageView) findViewById(R.id.setting_list_icon3);

        boolean wrapInScrollView = true;


        setting_list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp("If all checkboxes are off. By default all users can view and join the bet 1");
            }
        });


        setting_list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp("If all checkboxes are off. By default all users can view and join the bet 2");
            }
        });


        setting_list3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp("If all checkboxes are off. By default all users can view and join the bet 3");
            }
        });

    }

    private void showPopUp(String s) {
        dailog = new MaterialDialog.Builder(CreateBet.this)
                .title("Info")
                .customView(R.layout.custom_view_text, true).build();

        Button b = (Button) dailog.findViewById(R.id.got_it_buttton);
        TextView textView = (TextView) dailog.findViewById(R.id.textView);
        textView.setText(s);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dailog.dismiss();
            }
        });
        dailog.show();

    }

    private void setupToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = title;
        getSupportActionBar().setTitle(teams);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


}
