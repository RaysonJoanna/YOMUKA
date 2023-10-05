package com.yomuka.yomuka.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Repository;

@Repository
public class CommentsDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	public Connection open() {
		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"jwbook", "1234");
		}catch (Exception e) {
			e.printStackTrace();
		}return conn;
	}	
		public void newComments(Comments c) throws Exception{
			Connection conn = open();
			
			String sql = "insert into comments(boardaid,memberid, content, date)";
			sql += "values(?,?,?,CURRENT_TIMESTAMP())";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn; pstmt){
				pstmt.setInt(1, c.getBoardaid());
				pstmt.setString(2, c.getMemberid());
				pstmt.setString(3, c.getContent());
				pstmt.executeUpdate();
							
			}
		}
		
		public void delcomment(int commentsaid) throws Exception {
		    Connection conn = open();
		    String sql = "DELETE FROM Comments WHERE commentsaid = ?";
		    PreparedStatement pstmt = conn.prepareStatement(sql);
		    try(conn; pstmt){
		        pstmt.setInt(1, commentsaid);
		        pstmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		} 
}
