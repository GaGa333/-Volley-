package com.gcc.taotaopiao.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class St_codeDBOpenHelper extends SQLiteOpenHelper{
	

	public St_codeDBOpenHelper(Context context){		
		super(context, "station_16.sqlite", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		/*String sql = "CREATE TABLE station_16 ("
				+ "st_code TEXT, "
				+ "st_name TEXT, " 
				+ "st_py_s1 TEXT, " 
				+ "st_py_s2 TEXT, " 
				+ "st_py_full TEXT, " 
				+ "st_seq Integer, "
				+ "grade TEXT, "
				+ "ind TEXT, "+ ")";
		db.execSQL(sql); */
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}


}
