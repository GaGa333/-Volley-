package com.gcc.taotaopiao.dal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.gcc.taotaopiao.DB.St_codeDBOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class St_codeDBDao {
	private static Context context;
	public St_codeDBDao(Context context){
		this.context=context;
	}
	public static String querySt_codeByStation(String station){
		String st_code=null;
		St_codeDBOpenHelper helper=new St_codeDBOpenHelper(context);
		AssetsDatabaseManager.initManager(context); 
		// 获取管理对象，因为数据库需要通过管理对象才能够获取  
		AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
		// 通过管理对象获取数据库  
		SQLiteDatabase db = mg.getDatabase("station_16.sqlite");  
		 //SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
		//SQLiteDatabase sql=OpenOrCreateDatabase（DATABASE_NAME, Context.MODE_PRIVATE, null）；
		//File file=new File("");
		//    String path = "file:///android_asset/文件名";
		//SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase("file:///android_asset/Database/station_16.sqlite", null);
		String columns[]={
				"st_code"
		};
		String selection="st_name"+"=?";
		String Args[]=new String[]{station}; 
		Cursor cursor=db.query("st12306", columns, selection, Args, null, null, null);
		if(cursor.moveToFirst()){
			st_code=cursor.getString(0);
		}
		cursor.close();
		Log.i("TAG", "st_code="+st_code);
		return st_code;
	}
	
	public String querySt_pyByStation(String station){
		String st_py=null;
		St_codeDBOpenHelper helper=new St_codeDBOpenHelper(context);
		AssetsDatabaseManager.initManager(context); 
		// 获取管理对象，因为数据库需要通过管理对象才能够获取  
		AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
		// 通过管理对象获取数据库  
		SQLiteDatabase db = mg.getDatabase("station_16.sqlite");  
		//SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase("file:///android_asset/Database/station_16.sqlite", null);
		String columns[]={
				"ind"
		};
		String selection="st_name"+"=?";
		String Args[]=new String[]{station};
		Cursor cursor=db.query("st12306", columns, selection, Args, null, null, null);
		if(cursor.moveToFirst()){
			st_py=cursor.getString(0);
		}
		cursor.close();
		Log.i("TAG", "st_ind="+st_py);
		return st_py;
	}
	//通过输入拼音a,b,c~z获得对应的所有火车站的站点集合，按拼音的顺序
	public List<String> getAllStationByPy(String str){
		List<String> stations=new ArrayList<String>();
		St_codeDBOpenHelper helper=new St_codeDBOpenHelper(context);
		AssetsDatabaseManager.initManager(context); 
		// 获取管理对象，因为数据库需要通过管理对象才能够获取  
		AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
		// 通过管理对象获取数据库  
		SQLiteDatabase db = mg.getDatabase("station_16.sqlite");  
		String columns[]={
				"st_name"
		};
		String selection="ind"+"=?";
		String Args[]=new String[]{str};
		String order="st_py_full";
		Cursor cursor=db.query("st12306", columns, selection, Args, null, null, order);
		while(cursor.moveToNext()){
			String station=cursor.getString(0);
			stations.add(station);
		}
		cursor.close();
		return stations;
	}
	
	//通过输入拼音a,b,c~z获得对应的所有火车站的站点集合，按拼音的顺序
		public List<String> getStationByPy(String str){
			List<String> stations=new ArrayList<String>();
			St_codeDBOpenHelper helper=new St_codeDBOpenHelper(context);
			AssetsDatabaseManager.initManager(context); 
			// 获取管理对象，因为数据库需要通过管理对象才能够获取  
			AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
			// 通过管理对象获取数据库  
			SQLiteDatabase db = mg.getDatabase("station_16.sqlite");  
			String columns[]={
					"st_name"
			};
			String selection="st_py_s2"+"=?";
			String Args[]=new String[]{str};
			String order="st_py_full";
			Cursor cursor=db.query("st12306", columns, selection, Args, null, null, order);
			while(cursor.moveToNext()){
				String station=cursor.getString(0);
				stations.add(station);
			}
			cursor.close();
			return stations;
		}
	
	
	//按照A~Z获得所有火车站的车站
	public List<String> getALLStationByPy(){
		List<String> stations=new ArrayList<String>();
		St_codeDBOpenHelper helper=new St_codeDBOpenHelper(context);
		//SQLiteDatabase db=helper.getReadableDatabase();
			// 初始化，只需要调用一次  
			AssetsDatabaseManager.initManager(context); 
			// 获取管理对象，因为数据库需要通过管理对象才能够获取  
			AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
			// 通过管理对象获取数据库  
			SQLiteDatabase db = mg.getDatabase("station_16.sqlite");  
		String columns[]={
				"st_name"
		};
		String order="st_py_full";
		Cursor cursor=db.query("st12306", columns, null, null, null, null, order);
		while(cursor.moveToNext()){
			String station=cursor.getString(0);
			stations.add(station);
		}
		cursor.close();
		return stations;
	}
	//通过输入汉字来获取所有车站的站名
			public List<String> getStationByHz(String str){
				List<String> stations=new ArrayList<String>();
				St_codeDBOpenHelper helper=new St_codeDBOpenHelper(context);
				AssetsDatabaseManager.initManager(context); 
				// 获取管理对象，因为数据库需要通过管理对象才能够获取  
				AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
				// 通过管理对象获取数据库  
				SQLiteDatabase db = mg.getDatabase("station_16.sqlite");  
				String columns[]={
						"st_name"
				};
				String selection="st_name"+" like?";
				String Args[]=new String[]{str};
				String order="st_py_full";
				Cursor cursor=db.query("st12306", columns, selection, Args, null, null, order);
				while(cursor.moveToNext()){
					String station=cursor.getString(0);
					stations.add(station);
				}
				cursor.close();
				return stations;
			}
			public List<String> getStationByHzFirst(String str){
				List<String> stations=new ArrayList<String>();
				St_codeDBOpenHelper helper=new St_codeDBOpenHelper(context);
				AssetsDatabaseManager.initManager(context); 
				// 获取管理对象，因为数据库需要通过管理对象才能够获取  
				AssetsDatabaseManager mg = AssetsDatabaseManager.getManager();  
				// 通过管理对象获取数据库  
				SQLiteDatabase db = mg.getDatabase("station_16.sqlite");  
				String columns[]={
						"st_name"
				};
				String selection="st_hz_1"+"=?";
				String Args[]=new String[]{str};
				String order="st_py_full";
				Cursor cursor=db.query("st12306", columns, selection, Args, null, null, order);
				while(cursor.moveToNext()){
					String station=cursor.getString(0);
					stations.add(station);
				}
				cursor.close();
				return stations;
			}
}
