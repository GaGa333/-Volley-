package com.gcc.taotaopiao.Utils;

public class TrainDurationFormatUtils {
	private static String time = "";

	public static String trainDurationFormat(int minutes){
		if(minutes<60){
	        time=minutes+"分钟";
		}else if(minutes>=60){
			String hours=minutes/60+"小时";
			String min=minutes%60+"分钟";
			time=hours+min;
		}
		return time;
	}
}
