package com.yomuka.yomuka.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class BoardDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	@Value("${yomuka.imgdir}")
    private String fdir;
	 
	public Connection open() {
		Connection conn = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"jwbook", "1234");
		}catch (Exception e) {
			e.printStackTrace();
		}return conn;
	}
	
	public List<Board> getAllNotices(int startIndex, int pageSize) throws Exception {
	    Connection conn = open();
	    List<Board> noticeList = new ArrayList<>();

	    String sql = "SELECT boardaid, notice, memberid, title, img, content, TO_CHAR(date, 'yyyy-MM-dd HH24:MI:SS') as cdate FROM board WHERE notice = 'Y' LIMIT ? OFFSET ?";

	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, pageSize);
	    pstmt.setInt(2, startIndex);
	    ResultSet rs = pstmt.executeQuery();

	    try(conn; pstmt; rs) {
	        while(rs.next()) {
	            Board b = new Board();
	            b.setBoardaid(rs.getInt("boardaid"));
	            b.setNotice(rs.getString("notice"));
	            b.setMemberid(rs.getString("memberid"));
	            b.setTitle(rs.getString("title"));
	            b.setImg(rs.getString("img"));
	            b.setContent(rs.getString("content"));
	            b.setDate(rs.getString("cdate"));
	            noticeList.add(b);
	        }
	    }
	    
	    return noticeList;
	}

	public List<Board> getAllFreeBoards(String title, String memberid , String notice) throws Exception {
	    Connection conn = open();
	    List<Board> freeBoardList = new ArrayList<>();

	    String sql = "SELECT boardaid, notice, memberid, title, img, content, TO_CHAR(date, 'yyyy-MM-dd HH24:MI:SS') as cdate ";
	    		sql += "FROM board ";
	    		sql	+= "WHERE notice = '" + notice + "' " ;
	    		
	    		if(title != null) {
	    			sql += "and title like '%" + title + "%' ";
	    		}
	    		if(memberid != null) {
	    			sql += "and memberid like '%" + memberid + "%'; ";
	    		}
	    
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();

	    try(conn; pstmt; rs) {
	        while(rs.next()) {
	            Board b = new Board();
	            b.setBoardaid(rs.getInt("boardaid"));
	            b.setNotice(rs.getString("notice"));
	            b.setMemberid(rs.getString("memberid"));
	            b.setTitle(rs.getString("title"));
	            b.setImg(rs.getString("img"));
	            b.setContent(rs.getString("content"));
	            b.setDate(rs.getString("cdate"));
	            freeBoardList.add(b);
	        }
	    }
	    
	    return freeBoardList;
	}
	
	public List<Board> getAllFreeBoards(String title, String memberid, String notice, int offset, int limit) throws Exception {
	    Connection conn = open();
	    List<Board> freeBoardList = new ArrayList<>();

	    String sql = "SELECT boardaid, notice, memberid, title, img, content, TO_CHAR(date, 'yyyy-MM-dd HH24:MI:SS') as cdate ";
	    		sql += "FROM board ";
	    		sql	+= "WHERE notice = '" + notice + "' " ;
	    		
	    		if(title != null) {
	    			sql += "and title like '%" + title + "%' ";
	    		}
	    		if(memberid != null) {
	    			sql += "and memberid like '%" + memberid + "%' ";
	    		}
	    		sql += "limit " + limit + " offset " + offset;
	    		
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();

	    try(conn; pstmt; rs) {
	        while(rs.next()) {
	            Board b = new Board();
	            b.setBoardaid(rs.getInt("boardaid"));
	            b.setNotice(rs.getString("notice"));
	            b.setMemberid(rs.getString("memberid"));
	            b.setTitle(rs.getString("title"));
	            b.setImg(rs.getString("img"));
	            b.setContent(rs.getString("content"));
	            b.setDate(rs.getString("cdate"));
	            freeBoardList.add(b);
	        }
	    }
	    
	    return freeBoardList;
	}
//	public List<BoardList> getRsvListStart(String fromDate, String toDate, String hospitalid, String memberid) throws Exception {
//		
//		Connection conn = open();
//		List<RsvListStart> rsvListStart = new ArrayList<>();
//		
//		logger.info("getRsvListStart start ###");
//								
//		String sql = "select a.rsvdate, a.rsvtime, a.reserveid ";
//		sql += "from rsvmgnt a ";
//		sql += "where a.rsvdate>='" + fromDate + "'" + " and a.rsvdate<='" + toDate + "' ";
//		if(!hospitalid.equals("All")) {
//			sql += "and a.hospitalid=" + Integer.parseInt(hospitalid) + " ";
//		}
//		if(!memberid.equals("All")) {
//			sql += "and a.memberid='" + memberid + "' ";
//		}	
//		sql += "order by rsvdate, rsvtime asc ";
//				
//		PreparedStatement pstmt = conn.prepareStatement(sql);	
//				
//		logger.info("dao parm 3 : " + fromDate + " " + toDate);
//		logger.info("sql : " + sql);
//		ResultSet rs = pstmt.executeQuery();
//					
//		try(conn; pstmt; rs) {
//			while(rs.next()) {
//				logger.info("rsvdate : " + rs.getString("rsvdate"));
//				
//				RsvListStart n = new RsvListStart();
//				
//				n.setRsvdate(rs.getString("rsvdate"));								
//				rsvListStart.add(n);
//			}
//			return rsvListStart;
//		}
//	}
	public Board getBoard(int boardaid) throws SQLException{
		Connection conn = open();
		Board b = new Board();
		String sql = "select boardaid, notice, memberid, title, img, content, TO_CHAR(date, 'yyyy-MM-dd HH24:MI:SS') as cdate from board where boardaid = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,boardaid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		try(conn; pstmt; rs){
			b.setBoardaid(rs.getInt("boardaid"));
			b.setNotice(rs.getString("notice"));
			b.setMemberid(rs.getString("memberid"));
			b.setTitle(rs.getString("title"));
			b.setImg(rs.getString("img"));
			b.setContent(rs.getString("content"));
			b.setDate(rs.getString("cdate"));
			
			return b;
		}
	}
	
	public void newBoard(Board b) throws Exception{
		Connection conn = open();
		
		String sql = "insert into board(memberid, notice,title, img, content, date)";
		sql += "values(?,?,?,?,?, CURRENT_TIMESTAMP())";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		String profile = null; // 이미지 URL을 저장할 변수
        MultipartFile imgFile = b.getImgFile();
        
        if (imgFile != null && !imgFile.isEmpty()) {
            File dest = new File(fdir + "/" + imgFile.getOriginalFilename());
            imgFile.transferTo(dest);
            profile = "http://localhost:8090/main/img/" + dest.getName();
        }else {
        	profile ="/main/img/board1.jpg";
        }
        
		try(conn; pstmt){
			pstmt.setString(1, b.getMemberid());
			pstmt.setString(2, b.getNotice());
			pstmt.setString(3, b.getTitle());
			pstmt.setString(4, profile);
			pstmt.setString(5, b.getContent());
			pstmt.executeUpdate();
		}
	}
	public void updateBoard(Board board, MultipartFile imgFile) throws Exception {
	    Connection conn = open();
	    String profile = null;
	    
	    // 이미지 파일 처리
	    if (imgFile != null && !imgFile.isEmpty()) {
	        File dest = new File(fdir + "/" + imgFile.getOriginalFilename());
	        imgFile.transferTo(dest);
	        profile = "http://localhost:8090/main/img/" + dest.getName();
	    } else {
	    	if (board.getImg() == null || board.getImg().trim().isEmpty()) {
	            profile = "/main/img/board1.jpg";  // 이미지가 null일 경우 기본 이미지로 설정
	        } else {
	            profile = board.getImg(); // 이미지를 변경하지 않았다면 기존의 이미지 URL을 그대로 사용
	        }
	    }

	    String sql = "UPDATE board SET title = ?, img = ?, content = ? WHERE boardaid = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, board.getTitle());
	        pstmt.setString(2, profile);
	        pstmt.setString(3, board.getContent());
	        pstmt.setInt(4, board.getBoardaid());
	        pstmt.executeUpdate();
	    } finally {
	        if (conn != null) conn.close();
	    }
	}

	
	public String delBoard(int boardaid) throws Exception{
		Connection conn = open();
		String sql = "delete from board where boardaid = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try(conn; pstmt){
			pstmt.setInt(1, boardaid);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}return null;
	}
	
	public List<Comments> getCommentsByBoardAid(int boardaid) throws Exception{
	    Connection conn = open();
	    List<Comments> commentsList = new ArrayList<>();
	    String sql = "SELECT commentsaid , boardaid, memberid, content, TO_CHAR(date, 'yyyy-MM-dd HH24:MI:SS')as cdate FROM Comments WHERE boardaid = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, boardaid);
	    ResultSet rs = pstmt.executeQuery();
	    
	    while(rs.next()) {
	        Comments comment = new Comments();
	        comment.setCommentsaid(rs.getInt("commentsaid"));
	        comment.setBoardaid(rs.getInt("boardaid"));
	        comment.setMemberid(rs.getString("memberid"));
	        comment.setContent(rs.getString("content"));
	        comment.setDate(rs.getString("cdate"));
	        commentsList.add(comment);
	    }
	    return commentsList;
	}

	public void deleteCommentsByBoardAid(int boardaid) throws Exception {
	    Connection conn = open();
	    String sql = "DELETE FROM Comments WHERE boardaid = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    try(conn; pstmt){
	        pstmt.setInt(1, boardaid);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
