package com.gcc.taotaopiao.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper{

	/*public DBOpenHelper(Context context){
		super(context,"trips.db",null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
				
				String sql = "CREATE TABLE trips ("
						+ "_id integer primary key autoincrement, "
						+ "recentTrip TEXT, " + ")";
				db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
*/
	public DBOpenHelper(Context context){
		super(context,"trips.db",null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
				
				String sql = "CREATE TABLE trips ("
						+ "_id integer primary key autoincrement, "
						+ "start TEXT, "
						+ "end TEXT" + ")";
				db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
