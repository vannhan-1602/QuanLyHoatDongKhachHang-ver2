package com.DDD.demo.use_cases;

import com.DDD.demo.domain.activity.HoatDong;
import com.DDD.demo.domain.activity.HoatDongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GetHoatDongHistory {

    private final HoatDongRepository hoatDongRepository;

    public GetHoatDongHistory(HoatDongRepository hoatDongRepository) {
        this.hoatDongRepository = hoatDongRepository;
    }

    public List<HoatDong> getByKhachHangId(Long khachHangId) {
        return hoatDongRepository.findByKhachHangId(khachHangId);
    }
    public List<HoatDong> getAll() {
        return hoatDongRepository.findAll();
    }
}