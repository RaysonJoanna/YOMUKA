package com.yomuka.rsv;

import java.util.Date;

public class Hospital {
	private String hospitalid;	
	private String hospitalname;
	private String medtime;
	private String address;
	private String category;
	private Date regdate;
	
	public String getHospitalid() {
		return hospitalid;
	}
	public void setHospitalid(String hospitalid) {
		this.hospitalid = hospitalid;
	}
	public String getHospitalname() {
		return hospitalname;
	}
	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	public String getMedtime() {
		return medtime;
	}
	public void setMedtime(String medtime) {
		this.medtime = medtime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
	
}
