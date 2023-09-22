package com.yomuka.yomuka.main.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yomuka.yomuka.main.DTO.Member;

@Component
public class MemberDAO {
	@Value("${yomuka.imgdir}")
    private String fdir;
	
	Connection conn = null;
	PreparedStatement pstmt;
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/yomuka";
	
	// DB 연결 메서드
	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			this.conn = DriverManager.getConnection(JDBC_URL,"yomuka","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
	    try {
	        if (pstmt != null) {
	            pstmt.close();
	            pstmt = null;
	        }
	        if (conn != null) {
	            conn.close();
	            conn = null;
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// 회원가입
	public boolean Signup(Member m) {
	      open();
	      
	      String checkSql = "SELECT COUNT(*) AS cnt FROM member WHERE memberid = ?";
	      int cnt = 0;
	      
	      try {
	          pstmt = conn.prepareStatement(checkSql);
	          pstmt.setString(1, m.getMemberid());
	          ResultSet rs = pstmt.executeQuery();

	          if (rs.next()) {
	              cnt = rs.getInt("cnt");
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      
	      if (cnt > 0) {
	          close();
	          return false;
	      } else {
	      String sql = "INSERT INTO member(memberid, password, name, birth, gender, email, address, phone) values(?,?,?,?,?,?,?,?)";
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, m.getMemberid());
	         pstmt.setString(2, m.getPassword());
	         pstmt.setString(3, m.getName());
	         pstmt.setString(4, m.getBirth());
	         pstmt.setString(5, m.getGender());
	         pstmt.setString(6, m.getEmail());
	         pstmt.setString(7, m.getAddress());
	         pstmt.setString(8, m.getPhone());
	         pstmt.executeUpdate();
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } 
      }	close();
      return true;
	   }
	
	//로그인
	public Member login(String memberid, String password) {
        open();
        Member member = null;
        String sql = "SELECT * FROM member WHERE memberid = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { // 결과가 있으면
                String dbPassword = rs.getString("password"); // 데이터베이스에서 비밀번호 가져오기
                if (dbPassword.equals(password)) { // 비밀번호 일치하면 여기로! 
                //여기에 섹션저장
                	member = new Member();
                	member.setAid(rs.getInt("aid"));
                    member.setMemberid(rs.getString("memberid"));
                    member.setPassword(rs.getString("password"));
                    member.setName(rs.getString("name"));
                    member.setBirth(rs.getString("birth"));
                    member.setGender(rs.getString("gender"));
                    member.setEmail(rs.getString("email"));
                    member.setAddress(rs.getString("address"));
                    member.setProfile(rs.getString("profile"));
                    member.setPoint(rs.getInt("point"));
                    member.setAdmin(rs.getString("admin"));
                    member.setPhone(rs.getString("phone"));
//                    close();
//                    return true;  // 로그인 성공
                }
            } // 틀리면 넘어가~
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        
        return member;  // 로그인 실패
    }
	
	//비밀번호 찾기
	public String findUserPassword(String memberid, String name) {
        open();
        
        String sql = "SELECT password, name FROM member WHERE memberid = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { // 결과가 있으면
                String dbName = rs.getString("name"); // 데이터베이스에서 이름 가져오기
                String dbPassword = rs.getString("password");
                if (dbName.equals(name)) { // 이름 일치하면 여기로! 
                    close();
                    return dbPassword;  // 로그인 성공
                }
            } // 틀리면 넘어가~
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        
        return null;  // 아이디 비밀번호 실패
    }
	
	public boolean changeMyPage(String memberid, String password, String name, String phone, String email, String address) {
		open();
		boolean isUpdated = false;
		String sql = "UPDATE member SET password = ?, name = ?, phone = ? , email = ? , address = ? WHERE memberid = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,password);
			pstmt.setString(2,name);
			pstmt.setString(3,phone);
			pstmt.setString(4,email);
			pstmt.setString(5,address);
			pstmt.setString(6,memberid);
			
			int updatedRows = pstmt.executeUpdate();
			
			if(updatedRows > 0) {
				isUpdated = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return isUpdated;
	}
	
	public boolean profileChange(String memberid,MultipartFile imgFile, String profile){
		open();
		boolean profileC = false;
		String sql = "UPDATE member SET profile = ? WHERE memberid = ?";
		
		try {
			if (imgFile != null && !imgFile.isEmpty()) {
	            File dest = new File(fdir + "/" + imgFile.getOriginalFilename());
	            imgFile.transferTo(dest);
	            profile = "http://localhost:8085/main/img/" + dest.getName();
	        }
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, profile);
			pstmt.setString(2, memberid);
			
			int updatedRows = pstmt.executeUpdate();
			
			if(updatedRows > 0) {
				profileC = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		} return profileC;
	}
}


