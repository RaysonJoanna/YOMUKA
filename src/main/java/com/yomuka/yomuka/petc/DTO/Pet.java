package com.yomuka.yomuka.petc.DTO;

import org.springframework.web.multipart.MultipartFile;

public class Pet {
	private String memberid; // 반려동물 주인의 계정정보
	private int petNum; // 반려동물 번호
	private String kind; // 반려동물 대분류
	private String petProfile; // 반려동물 사진
	private MultipartFile petProfileFile; // 반려동물 사진(프론트에서 파일 전송받기)
	private String petName; // 반려동물 이름
	private int petAge; // 반려동물 나이
	private String petAgeUnit; // 반려동물 나이 단위
	private double petWeight; // 반려동물 몸무게
	private String petNeu; // 반려동물 중성화 여부
	private String diesease; // 반려동물 주요 질병
	private String breed; // 반려동물 품종

	public String getmemberid() {
		return memberid;
	}

	public void setmemberid(String memberid) {
		this.memberid = memberid;
	}

	public int getPetNum() {
		return petNum;
	}

	public void setPetNum(int petNum) {
		this.petNum = petNum;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getPetProfile() {
		return petProfile;
	}

	public void setPetProfile(String petProfile) {
		this.petProfile = petProfile;
	}

	public MultipartFile getPetProfileFile() {
		return petProfileFile;
	}

	public void setPetProfileFile(MultipartFile petProfileFile) {
		this.petProfileFile = petProfileFile;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getPetAge() {
		return petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}

	public String getPetAgeUnit() {
		return petAgeUnit;
	}

	public void setPetAgeUnit(String petAgeUnit) {
		this.petAgeUnit = petAgeUnit;
	}

	public double getPetWeight() {
		return petWeight;
	}

	public void setPetWeight(double petWeight) {
		this.petWeight = petWeight;
	}

	public String getPetNeu() {
		return petNeu;
	}

	public void setPetNeu(String petNeu) {
		this.petNeu = petNeu;
	}

	public String getDiesease() {
		return diesease;
	}

	public void setDiesease(String diesease) {
		this.diesease = diesease;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
}
