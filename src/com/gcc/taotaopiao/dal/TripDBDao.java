package com.gcc.taotaopiao.dal;

import java.util.ArrayList;
import java.util.List;

import com.gcc.taotaopiao.DB.DBOpenHelper;
import com.gcc.taotaopiao.entity.RecentTrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;

public class TripDBDao {
/*private static Context context;
	
	public  TripDBDao(Context context) {
		this.context = context;
	}


	public static long addnew(RecentTrip trip) {
		// 1. 获取DBOpenHelper对象
		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
		// 2. 获取SQLiteDatabase对象
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		// 3. 执行业务：增加
		String table = "trips";
		String nullColumnHack = null;
		ContentValues values = new ContentValues();
		values.put("recentTrip", trip.getRecentTrip());
		long id = db.insert(table, nullColumnHack , values);
		// 4. 释放资源
		db.close();
		// 5. 返回
		return id;
	}

	public static List<RecentTrip> queryAll() {
		// 0. 准备返回值
		List<RecentTrip> trips = new ArrayList<RecentTrip>();
		// 1. 获取DBOpenHelper对象
		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
		// 2. 获取SQLiteDatabase对象
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		// 3. 执行业务：查询
		String[] columns = {"_id","recentTrip"};
		String order="_id"+" desc";
		Cursor c = db.query("trips", columns, null, null, null, null,order);
		if (c.moveToFirst()) {
			for (; !c.isAfterLast(); c.moveToNext()) {
				long id = c.getLong(0);
				String recentTrip = c.getString(1);
				RecentTrip trip = new RecentTrip(recentTrip);
				trips.add(trip);
			}
		}
		// 4. 释放资源
		db.close();
		// 5. 返回
		return trips;
	}
*/
private static Context context;


	public  TripDBDao(Context context) {
		this.context = context;
	}


	public long addnew(RecentTrip rTrip) {
		// 1. 获取DBOpenHelper对象
		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
		// 2. 获取SQLiteDatabase对象
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		// 3. 执行业务：增加
		String table = "trips";
		String nullColumnHack = null;
		ContentValues values = new ContentValues();
		values.put("start", rTrip.getStart());
		values.put("end", rTrip.getEnd());
		long id = db.insert(table, nullColumnHack , values);
		// 4. 释放资源
		db.close();
		// 5. 返回
		return id;
	}

	public List<RecentTrip> queryAll() {
		// 0. 准备返回值
		List<RecentTrip> rTrips = new ArrayList<RecentTrip>();
		// 1. 获取DBOpenHelper对象
		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
		// 2. 获取SQLiteDatabase对象
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		// 3. 执行业务：查询
		String[] columns = {"_id","start", "end"};
		String order="_id"+" desc";
		Cursor c = db.query("trips", columns, null, null, null, null, order);
		if (c.moveToFirst()) {
			for (; !c.isAfterLast(); c.moveToNext()) {
				long id = c.getLong(0);
				String start = c.getString(1);
				String end = c.getString(2);
				RecentTrip rTrip = new RecentTrip(start,end);
			    rTrips.add(rTrip);
			}
		}
		// 4. 释放资源
		db.close();
		// 5. 返回
		return rTrips;
	}
	public void deleteData(){
		// 1. 获取DBOpenHelper对象
		DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
		// 2. 获取SQLiteDatabase对象
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		/*db.execSQL("delete from table");*/
		db.delete("trips", null, null);
	}

}
