package com.mobile.livescoreapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.livescoreapp.DB.DatabaseHelper;

import java.util.List;

/**
 * Created by JohnDoe on 5/6/2017.
 */

public class LivescoreAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<LivescoreItem> mItem;
    private DatabaseHelper mDatabaseHelper;
    private Tab2Favorite tab2Favorite;
    int i;
    public LivescoreAdapter(Context context,List<LivescoreItem> mItem){
        mContext = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mItem = mItem;
        mDatabaseHelper = new DatabaseHelper(context);
        tab2Favorite = new Tab2Favorite();
        i=0;

    }
    @Override
    public int getCount() {
        return mItem.size();
    }

    @Override
    public Object getItem(int position) {
        return mItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View view = convertView;
        View view = convertView;
        if (view ==null){
            view = inflater.inflate(R.layout.livescore_item,null);
        }

        final TextView home_Team = (TextView)view.findViewById(R.id.favorite_home_textview);
        TextView status = (TextView)view.findViewById(R.id.favorite_status_textview);
        TextView away_Team = (TextView)view.findViewById(R.id.txtView_Away);
        ImageButton addToFavorite = (ImageButton) view.findViewById(R.id.btn_remove_from_favorite);
        addToFavorite.setTag(position);
        status.setText(mItem.get(position).status);
        home_Team.setText(mItem.get(position).homeTeam);

        away_Team.setText(mItem.get(position).awayTeam);
        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer)v.getTag();
                //Toast.makeText(mContext, "posisi "+mItem.get(position).homeTeam, Toast.LENGTH_SHORT).show();
                addData(mItem.get(position).id,
                        mItem.get(position).homeTeam,
                        mItem.get(position).awayTeam,
                        mItem.get(position).status);
                tab2Favorite.refreshData();

            }
        });


        return view;
    }
    public void addData(String id,String home,String away,String status){
        boolean insertData = mDatabaseHelper.addData(id,home,away,status);
        if (insertData){
            Toast.makeText(mContext, "Ditambahkan Ke Favorite", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext, "Gagal Ditambahkan Ke Favorite", Toast.LENGTH_SHORT).show();
        }
    }




}
