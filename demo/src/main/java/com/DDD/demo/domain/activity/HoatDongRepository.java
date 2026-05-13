package com.DDD.demo.domain.activity;

import java.util.List;
import java.util.Optional;

public interface HoatDongRepository {
    HoatDong save(HoatDong hoatDong);
    Optional<HoatDong> findById(Long id);
    void deleteById(Long id);

    List<HoatDong> findByKhachHangId(Long khachHangId);
    List<HoatDong> findByLeadId(Long leadId);
    List<HoatDong> findAll();
}