package com.DDD.demo.use_cases;

import com.DDD.demo.domain.activity.HoatDong;
import com.DDD.demo.domain.activity.HoatDongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrackHoatDong {

    private final HoatDongRepository hoatDongRepository;
    public TrackHoatDong(HoatDongRepository hoatDongRepository){
        this.hoatDongRepository=hoatDongRepository;
    }
    public HoatDong execute(HoatDong hoatDong){
        return hoatDongRepository.save(hoatDong);
    }
}