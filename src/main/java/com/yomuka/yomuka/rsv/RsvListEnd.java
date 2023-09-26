package com.yomuka.yomuka.rsv;

import java.util.Date;

public class RsvListEnd {
	private Integer reserveid ;    	//예약id
	private String rsvdate;			//예약일자
	private String rsvtime;    		//예약시간
    private Integer hospitalid;		//병원id
    private String memberid;		//회원id
    private String content;			//예약내용
    private Date regdate;			//등록일자
    private Date resultdate;		//진료일자
    private String resultgubun;		//진료결과
    private String resultcontent;	//진료내용
    //예약현황 조회용
    private String hospitalname;		//병원명
    private String membername;		//멤버명
           
	
	public String getHospitalname() {
		return hospitalname;
	}
	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public Date getResultdate() {
		return resultdate;
	}
	public void setResultdate(Date resultdate) {
		this.resultdate = resultdate;
	}
	public String getResultgubun() {
		return resultgubun;
	}
	public void setResultgubun(String resultgubun) {
		this.resultgubun = resultgubun;
	}
	public String getResultcontent() {
		return resultcontent;
	}
	public void setResultcontent(String resultcontent) {
		this.resultcontent = resultcontent;
	}
	public Integer getReserveid() {
		return reserveid;
	}
	public void setReserveid(Integer reserveid) {
		this.reserveid = reserveid;
	}
	public String getRsvdate() {
		return rsvdate;
	}
	public void setRsvdate(String rsvdate) {
		this.rsvdate = rsvdate;
	}
	public String getRsvtime() {
		return rsvtime;
	}
	public void setRsvtime(String rsvtime) {
		this.rsvtime = rsvtime;
	}
	public Integer getHospitalid() {
		return hospitalid;
	}
	public void setHospitalid(Integer hospitalid) {
		this.hospitalid = hospitalid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
        	
    	
      
}
