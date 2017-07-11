package com.gcc.taotaopiao.entity;

public class Train{
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

	public String getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(String timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getPt_rwTicketsLeft() {
		return pt_rwTicketsLeft;
	}

	public void setPt_rwTicketsLeft(String pt_rwTicketsLeft) {
		this.pt_rwTicketsLeft = pt_rwTicketsLeft;
	}

	public String getPt_ywTicketsLeft() {
		return pt_ywTicketsLeft;
	}

	public void setPt_ywTicketsLeft(String pt_ywTicketsLeft) {
		this.pt_ywTicketsLeft = pt_ywTicketsLeft;
	}

	public String getPt_yzTicketsLeft() {
		return pt_yzTicketsLeft;
	}

	public void setPt_yzTicketsLeft(String pt_yzTicketsLeft) {
		this.pt_yzTicketsLeft = pt_yzTicketsLeft;
	}

	public String getPt_wzTicketsLeft() {
		return pt_wzTicketsLeft;
	}

	public void setPt_wzTicketsLeft(String pt_wzTicketsLeft) {
		this.pt_wzTicketsLeft = pt_wzTicketsLeft;
	}

	public String getPt_rwticketPrice() {
		return pt_rwticketPrice;
	}

	public void setPt_rwticketPrice(String pt_rwticketPrice) {
		this.pt_rwticketPrice = pt_rwticketPrice;
	}

	public String getPt_ywticketPrice() {
		return pt_ywticketPrice;
	}

	public void setPt_ywticketPrice(String pt_ywticketPrice) {
		this.pt_ywticketPrice = pt_ywticketPrice;
	}

	public String getPt_yzticketPrice() {
		return pt_yzticketPrice;
	}

	public void setPt_yzticketPrice(String pt_yzticketPrice) {
		this.pt_yzticketPrice = pt_yzticketPrice;
	}

	public String getPt_wzticketPrice() {
		return pt_wzticketPrice;
	}

	public void setPt_wzticketPrice(String pt_wzticketPrice) {
		this.pt_wzticketPrice = pt_wzticketPrice;
	}

	public String getDc_ydzTicketsLeft() {
		return dc_ydzTicketsLeft;
	}

	public void setDc_ydzTicketsLeft(String dc_ydzTicketsLeft) {
		this.dc_ydzTicketsLeft = dc_ydzTicketsLeft;
	}

	public String getDc_edzTicketsLeft() {
		return dc_edzTicketsLeft;
	}

	public void setDc_edzTicketsLeft(String dc_edzTicketsLeft) {
		this.dc_edzTicketsLeft = dc_edzTicketsLeft;
	}

	public String getDc_swzTicketsLeft() {
		return dc_swzTicketsLeft;
	}

	public void setDc_swzTicketsLeft(String dc_swzTicketsLeft) {
		this.dc_swzTicketsLeft = dc_swzTicketsLeft;
	}

	public String getDc_wzTicketsLeft() {
		return dc_wzTicketsLeft;
	}

	public void setDc_wzTicketsLeft(String dc_wzTicketsLeft) {
		this.dc_wzTicketsLeft = dc_wzTicketsLeft;
	}

	public String getDc_ydzticketPrice() {
		return dc_ydzticketPrice;
	}

	public void setDc_ydzticketPrice(String dc_ydzticketPrice) {
		this.dc_ydzticketPrice = dc_ydzticketPrice;
	}

	public String getDc_edzticketPrice() {
		return dc_edzticketPrice;
	}

	public void setDc_edzticketPrice(String dc_edzticketPrice) {
		this.dc_edzticketPrice = dc_edzticketPrice;
	}

	public String getDc_swzticketPrice() {
		return dc_swzticketPrice;
	}

	public void setDc_swzticketPrice(String dc_swzticketPrice) {
		this.dc_swzticketPrice = dc_swzticketPrice;
	}

	public String getDc_wzticketPrice() {
		return dc_wzticketPrice;
	}

	public void setDc_wzticketPrice(String dc_wzticketPrice) {
		this.dc_wzticketPrice = dc_wzticketPrice;
	}

	private String startingStation;//起始站
	private String terminus;//终点站
	private String startingDate;//出发时间
	private String arrivalDate;//到达时间
	private String timeConsuming;//车程时间
	private String trainNumber;//车次
	
	//普通列车组
	private String  pt_rwTicketsLeft;//软卧余票数
	private  String  pt_ywTicketsLeft;//硬卧余票数
	private  String  pt_yzTicketsLeft;//硬座余票数
	private  String  pt_wzTicketsLeft;//无票余票数
	//普通列车票价
	private String  pt_rwticketPrice;//软卧票价
	private String  pt_ywticketPrice;//硬卧票价
	private String  pt_yzticketPrice;//硬座票价
	private String  pt_wzticketPrice;//无票票价
	
	//动车 高铁组
	private  String  dc_ydzTicketsLeft;//一等余票数
	private  String  dc_edzTicketsLeft;//二等余票数
	private  String  dc_swzTicketsLeft;//商务余票数
	private  String  dc_wzTicketsLeft;//商务余票数
	
	//动车票价
	private String  dc_ydzticketPrice;//一等座票价
	private String  dc_edzticketPrice;//二等座票价
	private String  dc_swzticketPrice;//商务座票价
	private String  dc_wzticketPrice;//无座票票价
	
	public Train(String startingStation, String terminus,
			String startingDate, String arrivalDate, String timeConsuming,
			String trainNumber, String pt_rwTicketsLeft,
			String pt_ywTicketsLeft, String pt_yzTicketsLeft,
			String pt_wzTicketsLeft, String pt_rwticketPrice,
			String pt_ywticketPrice, String pt_yzticketPrice,
			String pt_wzticketPrice, String dc_ydzTicketsLeft,
			String dc_edzTicketsLeft, String dc_swzTicketsLeft,
			String dc_wzTicketsLeft, String dc_ydzticketPrice,
			String dc_edzticketPrice, String dc_swzticketPrice,
			String dc_wzticketPrice) {
		super();
		this.startingStation = startingStation;
		this.terminus = terminus;
		this.startingDate = startingDate;
		this.arrivalDate = arrivalDate;
		this.timeConsuming = timeConsuming;
		this.trainNumber = trainNumber;
		this.pt_rwTicketsLeft = pt_rwTicketsLeft;
		this.pt_ywTicketsLeft = pt_ywTicketsLeft;
		this.pt_yzTicketsLeft = pt_yzTicketsLeft;
		this.pt_wzTicketsLeft = pt_wzTicketsLeft;
		this.pt_rwticketPrice = pt_rwticketPrice;
		this.pt_ywticketPrice = pt_ywticketPrice;
		this.pt_yzticketPrice = pt_yzticketPrice;
		this.pt_wzticketPrice = pt_wzticketPrice;
		this.dc_ydzTicketsLeft = dc_ydzTicketsLeft;
		this.dc_edzTicketsLeft = dc_edzTicketsLeft;
		this.dc_swzTicketsLeft = dc_swzTicketsLeft;
		this.dc_wzTicketsLeft = dc_wzTicketsLeft;
		this.dc_ydzticketPrice = dc_ydzticketPrice;
		this.dc_edzticketPrice = dc_edzticketPrice;
		this.dc_swzticketPrice = dc_swzticketPrice;
		this.dc_wzticketPrice = dc_wzticketPrice;
	}

	public Train() {
		super();
		// TODO Auto-generated constructor stub
	}
	
 

}
