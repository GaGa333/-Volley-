package com.gcc.taotaopiao.adapter;

import java.util.List;

import com.gcc.taotaopiao.R;
import com.gcc.taotaopiao.dal.St_codeDBDao;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StationListAdapter extends MyBaseAdapter<String> {
	private Context context;

	public StationListAdapter(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	 ViewHolder vHolder=null;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*convertView=layoutInflater.inflate(R.layout.activity_station_list_item, null);*/
		St_codeDBDao st_codeDBDao=new St_codeDBDao(context);
		if(convertView==null){
			convertView=layoutInflater.inflate(R.layout.activity_station_list_item, null);
			vHolder=new ViewHolder();
			vHolder.textView_station_py=(TextView) convertView.findViewById(R.id.catalog);
			vHolder.textView_station=(TextView) convertView.findViewById(R.id.title);
			convertView.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) convertView.getTag();
		}
		String stations=getItem(position);	
		String st_py=st_codeDBDao.querySt_pyByStation(stations);
		String st_py_up=st_py.toUpperCase();
		vHolder.textView_station_py.setText(st_py_up);
		vHolder.textView_station.setText(stations);
		vHolder.textView_station_py.setVisibility(View.VISIBLE);
		if(position>0){
		String stations_last=getItem(position-1);	
		Log.i("TAG", stations);
		//判断是否第一次出现，是则显示第一个拼音的Item，否则不显示
		//显示当前车站名字的首拼音
		String st_py_last=st_codeDBDao.querySt_pyByStation(stations_last);	
		if(!st_py.equals(st_py_last)){
			vHolder.textView_station_py.setVisibility(View.VISIBLE);
		}else{
			vHolder.textView_station_py.setVisibility(View.GONE);
		}
		}
		return convertView;
	}
	
	
	class ViewHolder{ 
		TextView textView_station;
		TextView textView_station_py;
	}

}
