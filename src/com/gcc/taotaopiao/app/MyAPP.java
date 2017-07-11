package com.gcc.taotaopiao.app;

import cn.bmob.v3.Bmob;

import com.baidu.mapapi.SDKInitializer;
import com.gcc.taotaopiao.entity.User;

import android.app.Application;
import android.util.Log;

public class MyAPP extends Application{
	public static User user;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		user=new User();
		SDKInitializer.initialize(this);
		Bmob.initialize(this,"88114eab0b9a432750428e5c8196666f");
	}

}
