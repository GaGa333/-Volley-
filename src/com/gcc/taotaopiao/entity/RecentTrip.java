package com.gcc.taotaopiao.entity;

public class RecentTrip {
	@Override
	public String toString() {
		return "RecentTrip [start=" + start + ", end=" + end + "]";
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	private String start;
	private String end;
	public RecentTrip(String start, String end) {
		super();
		this.start = start;
		this.end = end;
	}
	public RecentTrip() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
