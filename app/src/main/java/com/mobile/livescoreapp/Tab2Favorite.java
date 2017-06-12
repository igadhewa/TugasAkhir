package com.mobile.livescoreapp;

import android.app.VoiceInteractor;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mobile.livescoreapp.Adapter.FavoiteItemAdapter;
import com.mobile.livescoreapp.DB.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by JohnDoe on 5/6/2017.
 */

public class Tab2Favorite extends Fragment  {

    private static final String tag = "ListDataActivity";
    ListView mListView;
    static DatabaseHelper databaseHelper;
    Button mButton;
    static List<LivescoreItem> mItem;
    static LivescoreItem livescore;
    static FavoiteItemAdapter mAdapter;
    static Cursor data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_favorite, container, false);
        mListView = (ListView) view.findViewById(R.id.liveViewFavorite);
        databaseHelper = new DatabaseHelper(getContext());
        //mButton = (Button)view.findViewById(R.id.button2);
        //mButton.setOnClickListener(this);
        mItem = new ArrayList<LivescoreItem>();
        populateListView();

        return view;
    }

    private void populateListView() {
        data = databaseHelper.getData();

        while(data.moveToNext()){
            livescore = new LivescoreItem(data.getString(0)
                    ,data.getString(1)
                    ,data.getString(2)
                    ,data.getString(3));

            mItem.add(livescore);
        }

        mAdapter = new FavoiteItemAdapter(getContext(),mItem);
        //ListAdapter adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,listData);

        mListView.setAdapter(mAdapter);


    }
    public void refreshData(){
        data = databaseHelper.getData();
        mItem.clear();
        while(data.moveToNext()){
            livescore = new LivescoreItem(data.getString(0)
                    ,data.getString(1)
                    ,data.getString(2)
                    ,data.getString(3));

            mItem.add(livescore);
        }
        mAdapter.notifyDataSetChanged();
    }



}
