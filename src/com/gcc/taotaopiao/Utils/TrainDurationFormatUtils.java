package com.gcc.taotaopiao.Utils;

public class TrainDurationFormatUtils {
	private static String time = "";

	public static String trainDurationFormat(int minutes){
		if(minutes<60){
	        time=minutes+"����";
		}else if(minutes>=60){
			String hours=minutes/60+"Сʱ";
			String min=minutes%60+"����";
			time=hours+min;
		}
		return time;
	}
}
