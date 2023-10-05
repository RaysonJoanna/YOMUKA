package com.yomuka.yomuka.ord.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yomuka.yomuka.ord.entity.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findByPetType(String petType);
	List<ProductEntity> getProductDetailByProductNum(String productNum);
	List<ProductEntity> findByProductType(String productType);
	
}
