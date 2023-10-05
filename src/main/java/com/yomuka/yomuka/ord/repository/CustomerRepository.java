package com.yomuka.yomuka.ord.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yomuka.yomuka.ord.entity.GudokEntity;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<GudokEntity, Long> {
	List<GudokEntity> findByMemberId(String memberId);
	List<GudokEntity> deleteByProductName(String productName);
}
