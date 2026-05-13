package com.DDD.demo.infrastructure.database.repositories;

import com.DDD.demo.infrastructure.database.entities.HoatDongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoatDongJpaRepository extends JpaRepository<HoatDongEntity, Long> {
    List<HoatDongEntity> findAllByKhachHangId(Long khachHangId);
    List<HoatDongEntity> findAllByLeadId(Long leadId);
}