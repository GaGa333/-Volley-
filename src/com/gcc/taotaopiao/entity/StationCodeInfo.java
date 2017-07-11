package com.gcc.taotaopiao.entity;

public class StationCodeInfo {

	public String getArrive_time() {
		return arrive_time;
	}
	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	public String getStation_no() {
		return station_no;
	}
	public void setStation_no(String station_no) {
		this.station_no = station_no;
	}
	public String getStop_time() {
		return stop_time;
	}
	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}
	public String getSub_train_code() {
		return sub_train_code;
	}
	public void setSub_train_code(String sub_train_code) {
		this.sub_train_code = sub_train_code;
	}
	@Override
	public String toString() {
		return "StationCodeInfo [arrive_time=" + arrive_time + ", start_time="
				+ start_time + ", station_name=" + station_name
				+ ", station_no=" + station_no + ", stop_time=" + stop_time
				+ ", sub_train_code=" + sub_train_code + "]";
	}
	private String arrive_time;
	private String start_time;
	private String station_name;
	private String station_no;
	private String stop_time;
	private String sub_train_code;
	public StationCodeInfo(String arrive_time, String start_time,
			String station_name, String station_no, String stop_time,
			String sub_train_code) {
		super();
		this.arrive_time = arrive_time;
		this.start_time = start_time;
		this.station_name = station_name;
		this.station_no = station_no;
		this.stop_time = stop_time;
		this.sub_train_code = sub_train_code;
	}
	public StationCodeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
