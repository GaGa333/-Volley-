package com.gcc.taotaopiao.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TrainQueryDataUtils {
	private static final SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
	private static final Date date=new Date();
	
	public static String getDataFormat(long data){
		date.setTime(data);
		return simpleDateFormat.format(date);
	}
	

}
