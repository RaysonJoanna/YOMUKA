package com.yomuka.yomuka.care;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class CareDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	// DB 연결을 가져오는 메서드
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 포인트 목록 전체 가져오기!
		public List<CarePoint> getAll() throws Exception {
			Connection conn = open();
			List<CarePoint> pointList = new ArrayList<>();
			
			String sql = "select TO_CHAR(date, 'yyyy-MM-dd hh:mm:ss') as date";
			sql += ", img, content from carePoint";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs) {
				while(rs.next()) {
					CarePoint p = new CarePoint();
					p.setDate(rs.getString("date"));
					p.setImg(rs.getString("Img"));
					p.setContent(rs.getString("content"));
					
					pointList.add(p);
				}
				return pointList;
			}
		}
	
}
