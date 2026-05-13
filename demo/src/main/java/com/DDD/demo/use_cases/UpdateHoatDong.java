package com.DDD.demo.use_cases;

import com.DDD.demo.domain.activity.HoatDong;
import com.DDD.demo.domain.activity.HoatDongRepository;
import com.DDD.demo.domain.activity.InvalidHoatDongException;
import com.DDD.demo.domain.activity.LoaiHoatDong;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class UpdateHoatDong {
    private final HoatDongRepository hoatDongRepository;
    public UpdateHoatDong(HoatDongRepository hoatDongRepository){
        this.hoatDongRepository=hoatDongRepository;
    }
    public HoatDong execute(Long id, LoaiHoatDong loaiHoatDong, String noiDung, Long nhanVienId, LocalDateTime thoiGianThucHien){
        HoatDong hoatDong=hoatDongRepository.findById(id).
                orElseThrow(()->new InvalidHoatDongException("Không tìm thấy"));
        hoatDong.capNhapThongTin(loaiHoatDong,noiDung,nhanVienId,thoiGianThucHien);
        return hoatDongRepository.save(hoatDong);
    }
}
