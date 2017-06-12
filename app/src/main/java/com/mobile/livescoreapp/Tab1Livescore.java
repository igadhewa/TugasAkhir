package com.mobile.livescoreapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by JohnDoe on 5/6/2017.
 */

public class Tab1Livescore extends Fragment implements AdapterView.OnItemClickListener {

    ListView mListView;
    LivescoreAdapter mLivescoreAdapter;
    HttpURLConnection connection;
    URL url = null;
    BufferedReader reader = null;

    TextView mTextView;
    RequestQueue requestQueue;
    List<LivescoreItem> mListItem;
    LivescoreItem livescoreItem;
    eventsItem mItem;
    Runnable mHandlerTask;
    private final static int INTERVAL = 1000 * 60 * 1;
    int liveCount = 2;
    final Handler handler = new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_livescore, container, false);




        mListView = (ListView)view.findViewById(R.id.listItem);
        requestQueue = Volley.newRequestQueue(getContext());

        mListItem = new ArrayList<LivescoreItem>();


        mListView.setOnItemClickListener(this);


        downloadData();
        //final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                downloadData();
                handler.postDelayed(this, 5000); //now is every 2 minutes
            }
        }, 5000);




        return view;
    }



    void startRepeatingTask()
    {
        mHandlerTask.run();
    }

    void stopRepeatingTask()
    {
        handler.removeCallbacks(mHandlerTask);
    }

    private void downloadData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://192.168.8.101/ls"+liveCount+".json",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("livematch");
                            mListItem.clear();
                            for(int i = 0; i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String localteam_name = jsonObject1.getString("localteam_name");
                                String visitorteam_name = jsonObject1.getString("visitorteam_name");
                                String status = jsonObject1.getString("status");
                                String id = jsonObject1.getString("id");
                                String venue = jsonObject1.getString("venue");
                                String localScore = jsonObject1.getString("localteam_score");
                                String visitorScore = jsonObject1.getString("visitorteam_score");

                                JSONArray jsonArray2 = jsonObject1.getJSONArray("events");
                                String json = jsonObject1.getJSONArray("events").toString();

                                for(int j = 0; j <jsonArray2.length();j++){

                                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                                    String ids = jsonObject2.getString("id");
                                    String type = jsonObject2.getString("type");
                                    String minute = jsonObject2.getString("minute");
                                    String team = jsonObject2.getString("team");
                                    String player = jsonObject2.getString("player");
                                    String assist = jsonObject2.getString("assist");
                                    String result = jsonObject2.getString("result");
                                    mItem = new eventsItem(ids,type,minute,team,player,assist,result);
                                }


                                livescoreItem = new LivescoreItem(id,localteam_name,visitorteam_name
                                                                ,status,venue,visitorScore,localScore,json);
                                mListItem.add(livescoreItem);
                            }

                            mLivescoreAdapter = new LivescoreAdapter(getContext(),mListItem);

                            mListView.setAdapter(mLivescoreAdapter);
                            mLivescoreAdapter.notifyDataSetChanged();
                            liveCount++;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Error",error.toString());
                    }
                }

        );
        requestQueue.add(jsonObjectRequest);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //List<LivescoreItem> data = parent.getItemAtPosition(position);
        Toast.makeText(getContext(), mListItem.get(position).awayTeam, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(),Detail_Livescore.class);
        intent.putExtra("home",mListItem.get(position).homeTeam);
        intent.putExtra("away",mListItem.get(position).awayTeam);
        intent.putExtra("venue",mListItem.get(position).venue);
        intent.putExtra("local_score",mListItem.get(position).localTeamScore);
        intent.putExtra("visitor_score",mListItem.get(position).visitorTeamScore);
        intent.putExtra("events",mListItem.get(position).events);
        startActivity(intent);
    }
}


