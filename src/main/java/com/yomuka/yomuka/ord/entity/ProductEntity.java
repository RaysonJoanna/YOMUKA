package com.yomuka.yomuka.ord.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // 엔티티 선언
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "productnum")
	private Long productNum;

	@Column(name = "pettype")
	private String petType;

	@Column(name = "producttype")
	private String productType;

	@Column(name = "productname")
	private String productName;

	@Column(name = "productprice")
	private String productPrice;

	@Column(name = "productimg")
	private String productImg;

	@Column(name = "productdetail")
	private String productDetail;

	// 기본 생성자
	public ProductEntity() {
	}

	// 빌더 메서드
	public static ProductEntityBuilder builder() {
		return new ProductEntityBuilder();
	}

	// 빌더 클래스
	public static class ProductEntityBuilder {
		private Long productNum;
		private String petType;
		private String productType;
		private String productName;
		private String productPrice;
		private String productImg;
		private String productDetail;

		public ProductEntityBuilder productNum(Long productNum) {
			this.productNum = productNum;
			return this;
		}

		public ProductEntityBuilder petType(String petType) {
			this.petType = petType;
			return this;
		}

		public ProductEntityBuilder productType(String productType) {
			this.productType = productType;
			return this;
		}

		public ProductEntityBuilder productName(String productName) {
			this.productName = productName;
			return this;
		}

		public ProductEntityBuilder productPrice(String productPrice) {
			this.productPrice = productPrice;
			return this;
		}

		public ProductEntityBuilder productImg(String productImg) {
			this.productImg = productImg;
			return this;
		}

		public ProductEntityBuilder productDetail(String productDetail) {
			this.productDetail = productDetail;
			return this;
		}

		public ProductEntity build() {
			ProductEntity Entity = new ProductEntity();
			Entity.setProductNum(this.productNum);
			Entity.setPetType(this.petType);
			Entity.setProductType(this.productType);
			Entity.setProductName(this.productName);
			Entity.setProductPrice(this.productPrice);
			Entity.setProductImg(this.productImg);
			Entity.setProductDetail(this.productDetail);
			return Entity;
		}

	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
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

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

}
