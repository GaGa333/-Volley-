package com.gcc.taotaopiao.entity;

import java.util.List;

public class StationsResult {
	public MyStation getData() {
		return data;
	}


	public StationsResult(MyStation data) {
		super();
		this.data = data;
	}


	public StationsResult() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setData(MyStation data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "StationsResult [data=" + data + "]";
	}


	private MyStation data;
	
	
	public static class MyStation{
		public List<StationCodeInfo> getList() {
			return stations;
		}

		public void setList(List<StationCodeInfo> list) {
			this.stations = list;
		}

		@Override
		public String toString() {
			return "MyStation [list=" + stations + "]";
		}

		public MyStation(List<StationCodeInfo> list) {
			super();
			this.stations = list;
		}

		private List<StationCodeInfo> stations;

		public MyStation() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
	}

}
