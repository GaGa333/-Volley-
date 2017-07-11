package com.gcc.taotaopiao.entity;

public class Bus {
	public String getStartingStation() {
		return startingStation;
	}

	public void setStartingStation(String startingStation) {
		this.startingStation = startingStation;
	}

	public String getTerminus() {
		return terminus;
	}

	public void setTerminus(String terminus) {
		this.terminus = terminus;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public String getBusPrices() {
		return busPrices;
	}

	public void setBusPrices(String busPrices) {
		this.busPrices = busPrices;
	}

	public String getTicketsLeft() {
		return ticketsLeft;
	}

	public void setTicketsLeft(String ticketsLeft) {
		this.ticketsLeft = ticketsLeft;
	}

	private String startingStation;//起始站
	private String terminus;//终点站
	private String departureTime;//出发时间
	private String  busNumber;//车次
	
	private String  busPrices;//汽车票价
	
	private String ticketsLeft;//汽车余票数

	public Bus(String startingStation, String terminus, String departureTime,
			String busNumber, String busPrices, String ticketsLeft) {
		super();
		this.startingStation = startingStation;
		this.terminus = terminus;
		this.departureTime = departureTime;
		this.busNumber = busNumber;
		this.busPrices = busPrices;
		this.ticketsLeft = ticketsLeft;
	}

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


}
