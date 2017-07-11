package com.gcc.taotaopiao.entity;

public class TrainType_KTZEntity {
	@Override
	public String toString() {
		return "TrainType_KTZEntity [isChecked=" + isChecked + ", rw_checked="
				+ rw_checked + ", yw_checked=" + yw_checked + ", yz_checked="
				+ yz_checked + ", wz_checked=" + wz_checked + "]";
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public boolean isRw_checked() {
		return rw_checked;
	}
	public void setRw_checked(boolean rw_checked) {
		this.rw_checked = rw_checked;
	}
	public boolean isYw_checked() {
		return yw_checked;
	}
	public void setYw_checked(boolean yw_checked) {
		this.yw_checked = yw_checked;
	}
	public boolean isYz_checked() {
		return yz_checked;
	}
	public void setYz_checked(boolean yz_checked) {
		this.yz_checked = yz_checked;
	}
	public boolean isWz_checked() {
		return wz_checked;
	}
	public void setWz_checked(boolean wz_checked) {
		this.wz_checked = wz_checked;
	}
	public boolean isChecked=false;
	public boolean rw_checked=false;
	public boolean yw_checked=false;
	public boolean yz_checked=false;
	public boolean wz_checked=false;
	public TrainType_KTZEntity(boolean isChecked, boolean rw_checked,
			boolean yw_checked, boolean yz_checked, boolean wz_checked) {
		super();
		this.isChecked = isChecked;
		this.rw_checked = rw_checked;
		this.yw_checked = yw_checked;
		this.yz_checked = yz_checked;
		this.wz_checked = wz_checked;
	}
	public TrainType_KTZEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
