package com.gcc.taotaopiao.entity;

public class TrainType_DEntity {
	@Override
	public String toString() {
		return "TrainType_DEntity [isChecked=" + isChecked + ", sw_checked="
				+ sw_checked + ", yd_checked=" + yd_checked + ", ed_checked="
				+ ed_checked + ", wz_checked=" + wz_checked + "]";
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public boolean isSw_checked() {
		return sw_checked;
	}
	public void setSw_checked(boolean sw_checked) {
		this.sw_checked = sw_checked;
	}
	public boolean isYd_checked() {
		return yd_checked;
	}
	public void setYd_checked(boolean yd_checked) {
		this.yd_checked = yd_checked;
	}
	public boolean isEd_checked() {
		return ed_checked;
	}
	public void setEd_checked(boolean ed_checked) {
		this.ed_checked = ed_checked;
	}
	public boolean isWz_checked() {
		return wz_checked;
	}
	public void setWz_checked(boolean wz_checked) {
		this.wz_checked = wz_checked;
	}
	public boolean isChecked=false;
	public boolean sw_checked=false;
	public boolean yd_checked=false;
	public boolean ed_checked=false;
	public boolean wz_checked=false;
	public TrainType_DEntity(boolean isChecked, boolean sw_checked,
			boolean yd_checked, boolean ed_checked, boolean wz_checked) {
		super();
		this.isChecked = isChecked;
		this.sw_checked = sw_checked;
		this.yd_checked = yd_checked;
		this.ed_checked = ed_checked;
		this.wz_checked = wz_checked;
	}
	public TrainType_DEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
