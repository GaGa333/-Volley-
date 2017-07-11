package com.gcc.taotaopiao.Manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetWorkManager{

             public static boolean isNetWorkConnected(Context context){
			 ConnectivityManager  manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			 
			 
			 
			 if(manager==null){
			 return false;
			 }
			 NetworkInfo  netWorkInfo=manager.getActiveNetworkInfo();
			 if(netWorkInfo==null||!netWorkInfo.isAvailable()){
			 return false;
			 }
			 return true;
             }
}
