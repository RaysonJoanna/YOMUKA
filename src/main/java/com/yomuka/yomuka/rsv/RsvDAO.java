package com.yomuka.yomuka.rsv;

import com.yomuka.yomuka.main.*;
import com.yomuka.yomuka.main.Member;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RsvDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/yomuka";
	
	// DB 연결을 가져오는 메서드
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "yomuka", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 예약내용 신규 등록 및 변경
	public int reserveSave(String reserveid, String hospitalid, String rsvdate, String rsvtime, String content, String memberid) throws Exception {
		
		System.out.println("### reserveSave dao : " + reserveid + " " + hospitalid + " " + rsvdate + " " + rsvtime + " " + content + " " + memberid );
		
		Connection conn = open();		
			
		int resultupdate = -1;
		int reserveid2 = Integer.parseInt(reserveid);
		
		if(!reserveid.equals("0")) {		//예약내용 변경인 경우
			String sql = "update rsvmgnt "; 
			sql += "set hospitalid = '" + hospitalid + "', ";
			sql += "rsvdate = '" + rsvdate + "', ";
			sql += "rsvtime = '" + rsvtime + "', ";
			sql += "content = '" + content + "' ";
			sql += "where reserveid =" + reserveid2;		
			
			PreparedStatement pstmt = conn.prepareStatement(sql);		
			try(conn; pstmt){
				resultupdate = pstmt.executeUpdate();
			}
		}else {			//예약내용 신규 등록인 경우

			String sql = "insert into rsvmgnt(rsvdate, rsvtime, hospitalid, memberid, content, regdate, resultdate)";
			sql += " values(?,?,?,?,?,CURRENT_TIMESTAMP(),?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn; pstmt){
				pstmt.setString(1, rsvdate);
				pstmt.setString(2, rsvtime);
				pstmt.setInt(3, Integer.parseInt(hospitalid));
				pstmt.setString(4, memberid);
				pstmt.setString(5, content);
				pstmt.setString(6, rsvdate);
				resultupdate = pstmt.executeUpdate();
			}			
		}
						
		return resultupdate;
	}

	
	// 예약현황조회 목록 전체를 가지고 오기 위한  메서드 (조회대상 기간의 전체건수 조회)
	public List<RsvListStart> getRsvListStart(String fromDate, String toDate, String hospitalid, String memberid) throws Exception {
				
		Connection conn = open();
		List<RsvListStart> rsvListStart = new ArrayList<>();
		
		logger.info("getRsvListStart start ###");
								
		String sql = "select a.rsvdate, a.rsvtime, a.reserveid ";
		sql += "from rsvmgnt a ";
		sql += "where a.rsvdate>='" + fromDate + "'" + " and a.rsvdate<='" + toDate + "' ";
		if(!hospitalid.equals("All")) {
			sql += "and a.hospitalid=" + Integer.parseInt(hospitalid) + " ";
		}
		if(!memberid.equals("All")) {
			sql += "and a.memberid='" + memberid + "' ";
		}	
		sql += "order by rsvdate, rsvtime asc ";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);	
				
		logger.info("dao parm 3 : " + fromDate + " " + toDate);
		logger.info("sql : " + sql);
		ResultSet rs = pstmt.executeQuery();
					
		try(conn; pstmt; rs) {
			while(rs.next()) {
				logger.info("rsvdate : " + rs.getString("rsvdate"));
				
				RsvListStart n = new RsvListStart();
				
				n.setRsvdate(rs.getString("rsvdate"));								
				rsvListStart.add(n);
			}
			return rsvListStart;
		}
	}
	
	// 예약현황조회 목록 전체를 가지고 오기 위한  메서드 (조회대상 페이지 명세 조회)
	public List<RsvListStart> getRsvListStart(String fromDate, String toDate, String hospitalid, String memberid, int offset, int limit) throws Exception {
				
		Connection conn = open();
		List<RsvListStart> rsvListStart = new ArrayList<>();
						
		logger.info("getRsvStartAll : " + fromDate + " " + toDate + " " + offset + " " + limit);
		
		String sql = "select b.hospitalname, a.hospitalid, a.rsvdate, a.rsvtime, ";
		sql += "a.reserveid,'홍길동' as membername, a.memberid, a.content ";
		sql += "from rsvmgnt a, hospital b ";
		sql += "where a.hospitalid = b.hospitalid ";
		sql += "and a.rsvdate>='" + fromDate + "'" + " and a.rsvdate<='" + toDate + "' ";
		if(!hospitalid.equals("All")) {
			sql += "and a.hospitalid=" + Integer.parseInt(hospitalid) + " ";
		}
		if(!memberid.equals("All")) {
			sql += "and a.memberid='" + memberid + "' ";
		}	
		sql += "order by rsvdate, rsvtime asc ";
		sql += "limit " + limit + " offset " + offset;
				
		PreparedStatement pstmt = conn.prepareStatement(sql);	
				
		logger.info("dao parm 3 : " + fromDate + " " + toDate);
		logger.info("sql : " + sql);
		ResultSet rs = pstmt.executeQuery();
					
		try(conn; pstmt; rs) {
			while(rs.next()) {					
				
				RsvListStart n = new RsvListStart();
				
				n.setHospitalname(rs.getString("hospitalname"));
				n.setHospitalid(rs.getInt("hospitalid"));
				n.setRsvdate(rs.getString("rsvdate"));
				n.setRsvtime(rs.getString("rsvtime"));
				n.setReserveid(rs.getInt("reserveid"));		
				n.setContent(rs.getString("content"));
				n.setMembername(rs.getString("membername"));
				n.setMemberid(rs.getString("memberid"));								
													
				rsvListStart.add(n);
			}
			return rsvListStart;
		}
	}
	
	
	// 진료결과 업데이트
	public int resultSave(String reserveid, String resultgubun, String resultcontent, String resultdate) throws Exception {
		
		System.out.println("resultSave dao : " + reserveid + " " + resultgubun + " " + resultcontent + " " + resultdate );
		
		Connection conn = open();
		int resultupdate = -1;
		int reserveid2 = Integer.parseInt(reserveid);
						
		String sql = "update rsvmgnt "; 
		sql += "set resultgubun = '" + resultgubun + "', ";
		sql += "resultcontent = '" + resultcontent + "', ";
		sql += "resultdate = '" + resultdate + "' ";
		sql += "where reserveid =" + reserveid2;		
		
		System.out.println("sql : " + sql);
		
		PreparedStatement pstmt = conn.prepareStatement(sql);		
		
		resultupdate = pstmt.executeUpdate();
				
		return resultupdate;
	}
	
	// 진료기록조회 목록 전체를 가지고 오기 위한  메서드 (조회대상 기간의 전체건수 조회)
	public List<RsvListEnd> getRsvListEnd(String fromDate, String toDate) throws Exception {
				
		Connection conn = open();
		List<RsvListEnd> rsvList = new ArrayList<>();
						
		String sql = "select reserveid, rsvdate, rsvtime, hospitalid, ";
		sql += "memberid, content, resultdate, resultgubun, resultcontent ";
		sql += "from rsvmgnt where resultdate>='" + fromDate + "'" + " and resultdate<='" + toDate + "' ";
		sql += "order by rsvdate, rsvtime asc ";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);	
				
		logger.info("dao parm 3 : " + fromDate + " " + toDate);
		logger.info("sql : " + sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				logger.info("reserveid : " + rs.getInt("reserveid"));
				
				RsvListEnd n = new RsvListEnd();
				n.setReserveid(rs.getInt("reserveid"));
				n.setRsvdate(rs.getString("rsvdate"));
				n.setRsvtime(rs.getString("rsvtime"));				
				n.setHospitalid(rs.getInt("hospitalid"));
				n.setMemberid(rs.getString("memberid"));
				n.setContent(rs.getString("content"));
				n.setResultdate(rs.getDate("resultdate"));				
				n.setResultgubun(rs.getString("resultgubun"));
				n.setResultcontent(rs.getString("resultcontent"));
				rsvList.add(n);
			}
			return rsvList;
		}
	}
	
	// 진료기록조회 목록 전체를 가지고 오기 위한  메서드 (페이지별로 구분하여 조회)
	public List<RsvListEnd> getRsvListEnd(String fromDate, String toDate, int offset, int limit) throws Exception {
					
		Connection conn = open();
		List<RsvListEnd> rsvList = new ArrayList<>();
						
		String sql = "select reserveid, rsvdate, rsvtime, hospitalid, ";
		sql += "memberid, content, resultdate, resultgubun, resultcontent ";
		sql += "from rsvmgnt where rsvdate>='" + fromDate + "'" + " and rsvdate<='" + toDate + "' ";
		sql += "order by rsvdate, rsvtime asc ";
		sql += " limit " + limit + " offset " + offset;
				
		PreparedStatement pstmt = conn.prepareStatement(sql);	
				
		logger.info("dao parm 3 : " + fromDate + " " + toDate + " " + offset + " " + limit);
		logger.info("sql : " + sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				logger.info("reserveid : " + rs.getInt("reserveid"));
				
				RsvListEnd n = new RsvListEnd();
				n.setReserveid(rs.getInt("reserveid"));
				n.setRsvdate(rs.getString("rsvdate"));
				n.setRsvtime(rs.getString("rsvtime"));				
				n.setHospitalid(rs.getInt("hospitalid"));
				n.setMemberid(rs.getString("memberid"));
				n.setContent(rs.getString("content"));
				n.setResultdate(rs.getDate("resultdate"));				
				n.setResultgubun(rs.getString("resultgubun"));
				n.setResultcontent(rs.getString("resultcontent"));
				rsvList.add(n);
			}
			return rsvList;
		}
	}
		
	// 병원명세조회 (조회조건 선택용)
	public List<Hospital> getHospitalListAll() throws Exception {
				
		Connection conn = open();
		List<Hospital> hospitalList = new ArrayList<>();
		
		logger.info("getHospitalListAll start ###");
								
		String sql = "select hospitalid, hospitalname, medtime, address, category, regdate ";
		sql += "from hospital ";
		sql += "order by hospitalid asc ";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);					
		
		logger.info("sql : " + sql);
		ResultSet rs = pstmt.executeQuery();
					
		try(conn; pstmt; rs) {
			while(rs.next()) {				
				Hospital n = new Hospital();
				
				n.setHospitalid(String.valueOf(rs.getInt("hospitalid")));
				n.setHospitalname(rs.getString("hospitalname"));
				n.setMedtime(rs.getString("medtime"));
				n.setAddress(rs.getString("address"));
				n.setCategory(rs.getString("category"));
				n.setRegdate(rs.getDate("regdate"));								
				
				hospitalList.add(n);
			}
			return hospitalList;
		}
	}
	
	// 회원 명세조회 : 조회조건용
	public List<Member> getMemberListAll() throws Exception {
				
		Connection conn = open();
		List<Member> memberList = new ArrayList<>();
		
		logger.info("getMemberListAll start ###");
								
		String sql = "select memberid, name ";
		sql += "from member ";		
		sql += "order by memberid asc ";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);	
						
		logger.info("sql : " + sql);
		ResultSet rs = pstmt.executeQuery();
					
		try(conn; pstmt; rs) {
			while(rs.next()) {				
				
				Member n = new Member();
				
				n.setMemberid(rs.getString("memberid"));								
				n.setName(rs.getString("name"));
				memberList.add(n);
			}
			return memberList;
		}
	}
	
}
