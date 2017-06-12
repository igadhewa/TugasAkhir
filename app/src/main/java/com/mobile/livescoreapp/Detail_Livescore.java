package com.mobile.livescoreapp;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.livescoreapp.Adapter.DetailAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  Detail_Livescore extends AppCompatActivity {

    TextView textViewHome,textViewAway,textViewScoreHome,textViewScoreAway,textViewStadium;
    JSONObject events;
    ListView listView;
    List<eventsItem> mItem;
    DetailAdapter mDetailAdapter;
    eventsItem eventsItem;
    private ArrayAdapter<String> listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__livescore);
        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        textViewAway = (TextView)findViewById(R.id.textAway);
        textViewHome = (TextView)findViewById(R.id.textHome);
        textViewScoreHome = (TextView)findViewById(R.id.textScoreHome);
        textViewScoreAway = (TextView)findViewById(R.id.textScoreAway);
        textViewStadium = (TextView)findViewById(R.id.textStadium);
        listView = (ListView)findViewById(R.id.listViewEvents);
        mItem = new ArrayList<eventsItem>();



        textViewAway.setText(intent.getStringExtra("away"));
        textViewHome.setText(intent.getStringExtra("home"));
        textViewStadium.setText(intent.getStringExtra("venue"));
        textViewScoreHome.setText(intent.getStringExtra("local_score"));
        textViewScoreAway.setText(intent.getStringExtra("visitor_score"));
        try {
            //events = new JSONObject(intent.getStringExtra("events"));


            JSONArray jsonArray = new JSONArray(intent.getStringExtra("events"));


            for(int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                String ids = jsonObject2.getString("id");
                String type = jsonObject2.getString("type");
                String minute = jsonObject2.getString("minute");
                String team = jsonObject2.getString("team");
                String player = jsonObject2.getString("player");
                String assist = jsonObject2.getString("assist");
                String result = jsonObject2.getString("result");
                eventsItem = new eventsItem(ids,type,minute,team,player,assist,result);
                Log.d(player,player);

                mItem.add(eventsItem);

            }
            String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                    "Jupiter", "Saturn", "Uranus", "Neptune"};
            ArrayList<String> planetList = new ArrayList<String>();
            planetList.addAll( Arrays.asList(planets) );
            //listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planetList);
            mDetailAdapter = new DetailAdapter(this,mItem);


            //listAdapter.addAll(mDetailAdapter);


            // Set the ArrayAdapter as the ListView's adapter.
            //mainListView.setAdapter( listAdapter );
            listView.setAdapter(mDetailAdapter);

            Toast.makeText(this, mItem.get(2).id.toString(), Toast.LENGTH_SHORT).show();



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
    */
}

