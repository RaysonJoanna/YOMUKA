package com.yomuka.yomuka.ord.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yomuka.yomuka.ord.entity.GudokEntity;

public interface test extends JpaRepository<GudokEntity, Long> {
}
