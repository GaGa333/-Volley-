package com.gcc.taotaopiao.Manager;

import java.util.List;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gcc.taotaopiao.IURL.IURL;
import com.gcc.taotaopiao.dal.St_codeDBDao;
import com.gcc.taotaopiao.entity.A;
import com.gcc.taotaopiao.entity.StationCodeInfo;
import com.gcc.taotaopiao.entity.StationsResult;
import com.gcc.taotaopiao.entity.Trains5;
import com.google.gson.Gson;

public class HttpLoadTrainsManager {
	private static RequestQueue queue;
	private Context context;
	public HttpLoadTrainsManager(Context context) {
		super();
		this.context = context;
	}
	public static void getTrainsData(Context context,String sst_code,String tst_code,String date,final GetTrainListener getTrainListener){
		/*String sst_code="BJP";*/
		/*String sst_code=St_codeDBDao.querySt_codeByStation(sStation);
		String tst_code="SHH";
		String tst_code=St_codeDBDao.querySt_codeByStation(tStation);*/
		if(queue==null){
			queue=Volley.newRequestQueue(context);
		}
		String url=IURL.ROOT+IURL.DATE_ROOT+date+IURL.SSTATION_ROOT+sst_code+IURL.TSTATION_ROOT+tst_code+IURL.KEY_ROOT;
	    StringRequest request=new StringRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String s) {
				// TODO Auto-generated method stub
				try{
				Gson gson=new Gson();
				Trains5 trains5=gson.fromJson(s, Trains5.class);
				List<A> trains=trains5.getResult().getList();
				getTrainListener.getTrains(trains);}catch (Exception e){
					getTrainListener.getTrains(null);
				}
			}
	    	
		},new  ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.i("TAG", "error");
				
			}
			
		}) ;
	    queue.add(request);
		
	}
	public interface GetTrainListener{
		public void getTrains(List<A> trains5);
		
	}
	
////	异步获取车次列表的网络数据
	
	public static void getStationCodeData(Context context,String TrainCode,String sStation,String tStation,String date,final GetStationsCodeInfoListener getStationsCodeInfoListener){
		/*String sst_code="BJP";*/
		String sst_code=St_codeDBDao.querySt_codeByStation(sStation);
		//String tst_code="SHH";
		String tst_code=St_codeDBDao.querySt_codeByStation(tStation);
		if(queue==null){
			queue=Volley.newRequestQueue(context);
		}
		String url=IURL.ROOT1+IURL.TRAIN_CODE_ROOT+TrainCode+IURL.TRAIN_CODE_DATE_ROOT+date+IURL.TRAIN_CODE_SSTATION_ROOT+sst_code+IURL.TRAIN_CODE_TSTATION_ROOT+tst_code;
	    StringRequest request=new StringRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String s) {
				// TODO Auto-generated method stub
				try{
					Log.i("TAG", "json="+s);
				Gson gson=new Gson();
				StationsResult stationsResult=gson.fromJson(s, StationsResult.class);
				Log.i("TAG", "stationsResult"+stationsResult);
				List<StationCodeInfo> stationCodeInfos=stationsResult.getData().getList();
				Log.i("TAG", "stationCodeInfos.size="+stationCodeInfos.size());
				getStationsCodeInfoListener.getStations(stationCodeInfos);
				}catch (Exception e){
					Log.i("TAG", "Exception");
					getStationsCodeInfoListener.getStations(null);
				}
			}	
		},new  ErrorListener(){
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Log.i("TAG", "error");
			}
			
		}) ;
	    queue.add(request);
		
	}
	public interface GetStationsCodeInfoListener{
		public void getStations(List<StationCodeInfo> stationInfos);
		
	}
	

}
