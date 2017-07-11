package com.gcc.taotaopiao.adapter;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.entity.StationCodeInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StationCodeListAdapter extends MyBaseAdapter<StationCodeInfo>{

	public StationCodeListAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
ViewHolder holder=null;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = layoutInflater.inflate(R.layout.activity_buy_train_station_listview_item_layout, null);
			holder=new ViewHolder();
			holder.arrive_time=(TextView) convertView.findViewById(R.id.tv_stationCode_listView_arrivalTime);
			holder.start_time=(TextView) convertView.findViewById(R.id.tv_stationCode_listView_startTime);
			holder.station_name=(TextView) convertView.findViewById(R.id.tv_stationCode_listView_station);
			holder.stop_time=(TextView) convertView.findViewById(R.id.tv_stationCode_listView_stopTime);
			convertView.setTag(holder);
		}
		else{
			holder=(ViewHolder) convertView.getTag();
		}
		    StationCodeInfo station=getItem(position);
		    holder.arrive_time.setText(station.getStart_time());
		    holder.start_time.setText(station.getArrive_time());
		    holder.station_name.setText(station.getStation_name());
		    holder.stop_time.setText(station.getStop_time()+"∑÷÷”");
		return convertView;
	}
	
	
	class ViewHolder{
		TextView arrive_time;
		TextView start_time;
		TextView station_name;
		TextView station_no;
		TextView stop_time;
	}
	
	

}
