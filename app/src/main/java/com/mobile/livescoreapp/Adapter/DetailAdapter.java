package com.mobile.livescoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.livescoreapp.LivescoreItem;
import com.mobile.livescoreapp.R;
import com.mobile.livescoreapp.eventsItem;

import java.util.List;

/**
 * Created by JohnDoe on 5/17/2017.
 */

public class DetailAdapter extends BaseAdapter{
    public Context mContext;
    private LayoutInflater inflater;
    private List<eventsItem> mItem;

    public DetailAdapter(Context mContext, List<eventsItem>  mItem) {
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = mContext;
        this.mItem = mItem;
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
        if (view ==null){
            view = inflater.inflate(R.layout.detail_item,null);
        }
        TextView type = (TextView)view.findViewById(R.id.textView1);
        TextView player = (TextView)view.findViewById(R.id.textView2);
        TextView assist = (TextView)view.findViewById(R.id.textView3);

        type.setText(mItem.get(position).type);
        player.setText(mItem.get(position).player);
        assist.setText(mItem.get(position).assist);


        return view;
    }
}
