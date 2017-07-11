package com.gcc.taotaopiao.entity;

public class Air {
	
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
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getTimeConsuming() {
		return timeConsuming;
	}
	public void setTimeConsuming(String timeConsuming) {
		this.timeConsuming = timeConsuming;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getTd_ticketPrices() {
		return td_ticketPrices;
	}
	public void setTd_ticketPrices(String td_ticketPrices) {
		this.td_ticketPrices = td_ticketPrices;
	}
	public String getPt_ticketPrices() {
		return pt_ticketPrices;
	}
	public void setPt_ticketPrices(String pt_ticketPrices) {
		this.pt_ticketPrices = pt_ticketPrices;
	}
	public String getSw_ticketPrices() {
		return sw_ticketPrices;
	}
	public void setSw_ticketPrices(String sw_ticketPrices) {
		this.sw_ticketPrices = sw_ticketPrices;
	}
	public String getTd_ticketStatus() {
		return td_ticketStatus;
	}
	public void setTd_ticketStatus(String td_ticketStatus) {
		this.td_ticketStatus = td_ticketStatus;
	}
	public String getPt_ticketStatus() {
		return pt_ticketStatus;
	}
	public void setPt_ticketStatus(String pt_ticketStatus) {
		this.pt_ticketStatus = pt_ticketStatus;
	}
	public String getSw_ticketStatus() {
		return sw_ticketStatus;
	}
	public void setSw_ticketStatus(String sw_ticketStatus) {
		this.sw_ticketStatus = sw_ticketStatus;
	}
	private String startingStation;//��ʼվ
	private String terminus;//�յ�վ
	private String departureTime;//����ʱ��
	private String arriveTime;//����ʱ��
	private String timeConsuming;//����ʱ��
	private String  flightNumber;//�����
	
	private String  td_ticketPrices;//ͷ�Ȼ�Ʊ��
	private String  pt_ticketPrices;//��ͨ��Ʊ��
	private String  sw_ticketPrices;//�����Ʊ��
	
	private String td_ticketStatus;//ͷ�Ȼ�Ʊ��Ʊ״̬
	private String pt_ticketStatus;//��ͨ��Ʊ��Ʊ״̬
	private String sw_ticketStatus;//�����Ʊ��Ʊ״̬
	public Air(String startingStation, String terminus, String departureTime,
			String arriveTime, String timeConsuming, String flightNumber,
			String td_ticketPrices, String pt_ticketPrices,
			String sw_ticketPrices, String td_ticketStatus,
			String pt_ticketStatus, String sw_ticketStatus) {
		super();
		this.startingStation = startingStation;
		this.terminus = terminus;
		this.departureTime = departureTime;
		this.arriveTime = arriveTime;
		this.timeConsuming = timeConsuming;
		this.flightNumber = flightNumber;
		this.td_ticketPrices = td_ticketPrices;
		this.pt_ticketPrices = pt_ticketPrices;
		this.sw_ticketPrices = sw_ticketPrices;
		this.td_ticketStatus = td_ticketStatus;
		this.pt_ticketStatus = pt_ticketStatus;
		this.sw_ticketStatus = sw_ticketStatus;
	}
	public Air() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
