package com.yomuka.yomuka.petc.service;

import org.springframework.stereotype.Component;

import com.yomuka.yomuka.petc.DTO.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PetcService {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/yomuka";

	// DB연결
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

	// 모든 반려동물 정보 가져오기
	public List<Pet> getAllPet(String memberid) throws Exception {
		Connection conn = open();
		List<Pet> petList = new ArrayList<>();
		String sql = "";
		sql = "select petProfile, petName, petAge, petAgeUnit, petWeight, petNeu, diesease, breed, kind, petNum";
		sql += " from pet where memberid=? order by petNum desc";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberid);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				Pet pet = new Pet();
				pet.setPetProfile(rs.getString("petProfile"));
				pet.setPetName(rs.getString("petName"));
				pet.setPetAge(rs.getInt("petAge"));
				pet.setPetAgeUnit(rs.getString("petAgeUnit"));
				pet.setPetWeight(rs.getDouble("petWeight"));
				pet.setPetNeu(rs.getString("petNeu"));
				pet.setDiesease(rs.getString("diesease"));
				pet.setBreed(rs.getString("breed"));
				pet.setKind(rs.getString("kind"));
				pet.setPetNum(rs.getInt("petNum"));

				petList.add(pet);
			}
			return petList;
		}
	}
	
	public String getAllPetName(String memberid) throws Exception {
		Connection conn = open();
		
		String allPetName = "";

		
		String sql = "select petName from pet where memberid=? order by petNum desc";
		
		try(conn;){
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberid);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				if(!rs.isLast()) {
					allPetName += rs.getString("petName") + ",";
				} else {
					allPetName += rs.getString("petName");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allPetName;
	}

	// 반려동물 품종 목록 불러오는 메서드
	public ArrayList<String> getList(String kind) throws Exception {
		Connection conn = open();
		int kindNum = 0;
		String breed = "";

		ArrayList<String> breedList = new ArrayList<>();

		switch (kind) {
		case "dog":
			kindNum = 1;
			break;
		case "cat":
			kindNum = 2;
			break;
		case "other":
			kindNum = 3;
			break;
		}
		String sql = "select breed from breed where kind=? order by breed";

		try (conn;) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, kindNum);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				breed = rs.getString("breed");
				breedList.add(breed);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return breedList;
	}

	// 반려동물 질병,품종 목록 불러오는 메서드
	public ArrayList<String> getdList(String kind) throws Exception {
		Connection conn = open();
		int kindNum = 0;
		String diesease = "";

		ArrayList<String> dieseaseList = new ArrayList<>();

		switch (kind) {
		case "dog":
			kindNum = 1;
			break;
		case "cat":
			kindNum = 2;
			break;
		case "other":
			kindNum = 3;
			break;
		}

		String sql = "select diesease from diesease where kind=? order by diesease";

		try (conn;) {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, kindNum);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				diesease = rs.getString("diesease");
				dieseaseList.add(diesease);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dieseaseList;
	}

	// 반려동물 정보 등록 메서드
	public void addPetInfo(Pet pet, String kind) throws Exception {
		Connection conn = open();
		String sql = "insert into pet(memberid, kind, petProfile, petName, petAge, petAgeUnit, petWeight, petNeu, diesease, breed)";
		sql += "values(?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setString(1, pet.getmemberid());
			pstmt.setString(2, kind);
			pstmt.setString(3, pet.getPetProfile());
			pstmt.setString(4, pet.getPetName());
			pstmt.setInt(5, pet.getPetAge());
			pstmt.setString(6, pet.getPetAgeUnit());
			pstmt.setDouble(7, pet.getPetWeight());
			pstmt.setString(8, pet.getPetNeu());
			pstmt.setString(9, pet.getDiesease());
			pstmt.setString(10, pet.getBreed());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 반려동물 정보 수정 메서드
	public void updatePetInfo(Pet pet) throws Exception {
		Connection conn = open();

		String sql = "update pet set petProfile=?, petName=?, petAge=?, petAgeUnit=?, petWeight=?, petNeu=?, diesease=?, breed=?";
		sql += " where petNum=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setString(1, pet.getPetProfile());
			pstmt.setString(2, pet.getPetName());
			pstmt.setInt(3, pet.getPetAge());
			pstmt.setString(4, pet.getPetAgeUnit());
			pstmt.setDouble(5, pet.getPetWeight());
			pstmt.setString(6, pet.getPetNeu());
			pstmt.setString(7, pet.getDiesease());
			pstmt.setString(8, pet.getBreed());
			pstmt.setInt(9, pet.getPetNum());
			pstmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePetInfo(Pet pet) throws SQLException {
		Connection conn = open();

		String sql = "delete from pet where petNum=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setInt(1, pet.getPetNum());
			if (pstmt.executeUpdate() == 0) {
				throw new SQLException("반려동물 삭제 에러");
			}
		}
	}

	public String getKind(int petNum) throws SQLException {
		Connection conn = open();

		String kind = "";
		String sql = "select kind from pet where petNum=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setInt(1, petNum);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				kind = rs.getString("kind");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kind;
	}

	public Pet petByNum(int petNum) throws SQLException {
		Connection conn = open();
		Pet pet = new Pet();
		
		String sql = "select * from pet where petNum=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		

		try (conn; pstmt;) {
			pstmt.setInt(1, petNum);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				pet.setmemberid(rs.getString("memberid"));
				pet.setPetNum(petNum);
				pet.setKind(rs.getString("kind"));
				pet.setPetProfile(rs.getString("petProfile"));
				pet.setPetName(rs.getString("petName"));
				pet.setPetAge(rs.getInt("petAge"));
				pet.setPetAgeUnit(rs.getString("petAgeUnit"));
				pet.setPetWeight(rs.getDouble("petWeight"));
				pet.setPetNeu(rs.getString("petNeu"));
				pet.setDiesease(rs.getString("diesease"));
				pet.setBreed(rs.getString("breed"));
				pet.setKind(rs.getString("kind"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pet;
	}

}
