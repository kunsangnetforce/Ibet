package com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.netforceinfotech.ibet.R;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.searchfriend.SearchFriendActivity;
import com.netforceinfotech.ibet.dashboard.home.startnewbet.create_bet.searchfriend.SearchFriendData;

import java.util.ArrayList;

public class CreateBet extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    ImageView setting_list1, setting_list2, setting_list3;
    private MaterialDialog dailog;
    TextView textViewRemaining;
    EditText editText;
    TextView friendslist;
    public static ArrayList<SearchFriendData> frindids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bet);
        setupToolBar("Create Bet");
        setuplayout();
        findViewById(R.id.buttoncreatebet).setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String frindstring = "";
        for (int i = 0; i < frindids.size(); i++) {
            if (i == 0) {
                frindstring = frindids.get(i).name;
            } else {
                frindstring += "," + frindids.get(i).name;
            }
        }
        friendslist.setText(frindstring);
    }

    private void setuplayout() {
        editText = (EditText) findViewById(R.id.editText);
        textViewRemaining = (TextView) findViewById(R.id.textViewRemaing);
        friendslist = (TextView) findViewById(R.id.textViewListofFriends);
        setting_list1 = (ImageView) findViewById(R.id.canViewJoin);
        setting_list2 = (ImageView) findViewById(R.id.canView);
        setting_list3 = (ImageView) findViewById(R.id.cantView);
        findViewById(R.id.buttonInviteFriend).setOnClickListener(this);
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
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                int remaining = 200 - editText.length();
                textViewRemaining.setText(remaining + " characters remaining");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

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
    public boolean onOptionsItemSelected(MenuItem item) {
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonInviteFriend:
                startActivity(new Intent(CreateBet.this, SearchFriendActivity.class));
                break;
            case R.id.buttoncreatebet:
                createbet();
                break;
        }
    }

    private void createbet() {
        //https://netforcesales.com/ibet_admin/api/create_bet.php?match_id=647654&participants=4&comments=5&home_team_id=1150&away_team_id=1232&bet_amount=5&bet_status=win&&bet_match_date=2016-09-15&bet_option=0&bet_options_to_user=0&user_id=136&bet_remarks=test
    }
}
