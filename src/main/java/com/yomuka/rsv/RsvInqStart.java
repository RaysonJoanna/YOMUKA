package com.yomuka.rsv;

//진료기록조회 조회조건
public class RsvInqStart {	
	private String fromDate;
	private String toDate;
	private String nhospitalid;
	private String nmemberid;
			
	public String getNhospitalid() {
		return nhospitalid;
	}
	public void setNhospitalid(String nhospitalid) {
		this.nhospitalid = nhospitalid;
	}
	public String getNmemberid() {
		return nmemberid;
	}
	public void setNmemberid(String nmemberid) {
		this.nmemberid = nmemberid;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}	
	
}
