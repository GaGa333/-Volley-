package com.gcc.taotaopiao.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.CallLog;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.entity.RecentTrip;


public class RecentTripAdapter extends MyBaseAdapter<RecentTrip>{
	

 public RecentTripAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
ViewHolder viewHolder=null;

@Override
public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView==null) {
        convertView = layoutInflater.inflate(R.layout.recenttrip_item_layout, null);
        viewHolder = new ViewHolder();
        viewHolder.textView_start = (TextView) convertView.findViewById(R.id.textview_start);
        viewHolder.textView_end = (TextView) convertView.findViewById(R.id.textView_end);
        convertView.setTag(viewHolder);
    }else  {
        viewHolder= (ViewHolder) convertView.getTag();
    }
    RecentTrip recentTrip=getItem(position);
        viewHolder.textView_start.setText(recentTrip.getStart());
        viewHolder.textView_end.setText(recentTrip.getEnd());
    return convertView;
}

class ViewHolder{ 
	TextView textView_start;
	TextView textView_end;
}
}

