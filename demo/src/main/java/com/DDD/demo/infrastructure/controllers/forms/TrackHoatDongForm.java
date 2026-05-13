package com.DDD.demo.infrastructure.controllers.forms;

import com.DDD.demo.domain.activity.HoatDong;
import com.DDD.demo.domain.activity.LoaiHoatDong;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrackHoatDongForm {
    private Long khachHangId;
    private Long leadId;
    private LoaiHoatDong loaiHoatDong;
    private String noiDung;
    private LocalDateTime thoiGianThucHien;
    private Long nhanVienId;

    public HoatDong toDomain() {
        return new HoatDong(
                khachHangId,
                null,
                leadId,
                null,
                loaiHoatDong,
                noiDung,
                thoiGianThucHien,
                nhanVienId,
                null
        );
    }
}