package com.yomuka.yomuka.ord.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GudokEntity {
	@Id
	@Column(name = "gudoknum")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long gudokNum;

	@Column(name = "memberid")
	private String memberId;

	@Column(name = "productimg")
	private String productImg;

	@Column(name = "productname")
	private String productName;

	@Column(name = "productprice")
	private String productPrice;

	@Column(name = "productquantity")
	private String productQuantity;

	@Column(name = "gudokstartdate")
	private LocalDate gudokstartdate;
	
	@Column(name = "totalPrice")
	private String totalPrice;


	// 기본 생성자
	public GudokEntity() {

	}
	//빌더 메서드
	public static GudokEntityBuilder builder() {
		return new GudokEntityBuilder();
	}

	//빌더 클래스
	public static class GudokEntityBuilder {
		private Long gudokNum;
		private String memberId;
		private String productImg;
		private String productName;
		private String productPrice;
		private String productQuantity;
		private LocalDate gudokstartdate;
		private String totalPrice;

		public GudokEntityBuilder gudokNum(Long gudokNum) {
			this.gudokNum = gudokNum;
			return this;
		}

		public GudokEntityBuilder memberId(String memberId) {
			this.memberId = memberId;
			return this;
		}

		public GudokEntityBuilder productImg(String productImg) {
			this.productImg = productImg;
			return this;
		}

		public GudokEntityBuilder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public GudokEntityBuilder productPrice(String productPrice) {
			this.productPrice = productPrice;
			return this;
		}

		public GudokEntityBuilder productQuantity(String productQuantity) {
			this.productQuantity = productQuantity;
			return this;
		}

		public GudokEntityBuilder gudokStartDate(LocalDate gudokstartdate) {
			this.gudokstartdate = gudokstartdate;
			return this;
		}
		
		public GudokEntityBuilder totalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}

		public GudokEntity build() {
			GudokEntity gudokEntity = new GudokEntity();
			gudokEntity.setGudokNum(this.gudokNum);
			gudokEntity.setMemberId(this.memberId);
			gudokEntity.setProductImg(this.productImg);
			gudokEntity.setProductName(this.productName);
			gudokEntity.setProductPrice(this.productPrice);
			gudokEntity.setProductQuantity(this.productQuantity);
			gudokEntity.setGudokstartdate(this.gudokstartdate);
			gudokEntity.setTotalPrice(this.totalPrice);
			return gudokEntity;
		}
	}

	public Long getGudokNum() {
		return gudokNum;
	}
	public void setGudokNum(Long gudokNum) {
		this.gudokNum = gudokNum;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}
	public LocalDate getGudokstartdate() {
		return gudokstartdate;
	}
	public void setGudokstartdate(LocalDate gudokstartdate) {
		this.gudokstartdate = gudokstartdate;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	
}
