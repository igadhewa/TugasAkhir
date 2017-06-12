package com.mobile.livescoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.livescoreapp.DB.DatabaseHelper;
import com.mobile.livescoreapp.LivescoreItem;
import com.mobile.livescoreapp.R;
import com.mobile.livescoreapp.Tab2Favorite;

import java.util.List;

/**
 * Created by JohnDoe on 5/11/2017.
 */

public class FavoiteItemAdapter extends BaseAdapter  {

    Context mContext;
    List<LivescoreItem> mItem;
    LayoutInflater inflater;
    DatabaseHelper dbHelper;
    Tab2Favorite tab2;
    public FavoiteItemAdapter(Context mContext, List<LivescoreItem> mItem) {

        this.mContext = mContext;
        this.mItem = mItem;
        inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        dbHelper = new DatabaseHelper(mContext);
        tab2 = new Tab2Favorite();
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
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.favorite_item,null);
        }
        TextView homeText = (TextView)view.findViewById(R.id.favorite_home_textview);
        TextView awayText = (TextView)view.findViewById(R.id.favorite_away_textview);
        TextView status = (TextView)view.findViewById(R.id.favorite_status_textview);
        ImageButton removeButton = (ImageButton)view.findViewById(R.id.btn_remove_from_favorite);
        removeButton.setTag(mItem.get(position).id);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteData(v.getTag().toString());
                tab2.refreshData();
                //Toast.makeText(mContext, v.getTag().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        homeText.setText(mItem.get(position).homeTeam);
        awayText.setText(mItem.get(position).awayTeam);
        status.setText(mItem.get(position).status);

        return view;
    }
}
