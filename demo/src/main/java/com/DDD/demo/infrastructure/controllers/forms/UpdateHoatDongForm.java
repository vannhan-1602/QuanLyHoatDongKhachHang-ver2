package com.DDD.demo.infrastructure.controllers.forms;

import com.DDD.demo.domain.activity.LoaiHoatDong;
import lombok.Data;

@Data
public class UpdateHoatDongForm {
    private LoaiHoatDong loaiHoatDong;
    private String noiDung;
    private Long nhanVienId;
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private java.time.LocalDateTime thoiGianThucHien;
}
