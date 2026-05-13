package com.DDD.demo.domain.activity;

import java.time.LocalDateTime;

public class HoatDong {
    private Long id;
    private Long khachHangId;
    private String tenKhachHang;
    private Long leadId;
    private String tenLead;
    private LoaiHoatDong loaiHoatDong;
    private String noiDung;
    private LocalDateTime thoiGianThucHien;
    private Long nhanVienId;
    private String tenNhanVien;


    public HoatDong(Long khachHangId, String tenKhachHang, Long leadId, String tenLead,
                    LoaiHoatDong loaiHoatDong, String noiDung,
                    LocalDateTime thoiGianThucHien, Long nhanVienId, String tenNhanVien) {
        this.khachHangId = khachHangId;
        this.tenKhachHang = tenKhachHang;
        this.leadId = leadId;
        this.tenLead = tenLead;
        this.loaiHoatDong = loaiHoatDong;
        this.noiDung = noiDung;
        this.thoiGianThucHien = thoiGianThucHien;
        this.nhanVienId = nhanVienId;
        this.tenNhanVien = tenNhanVien;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getKhachHangId() { return khachHangId; }
    public Long getLeadId() { return leadId; }
    public LoaiHoatDong getLoaiHoatDong() { return loaiHoatDong; }
    public String getNoiDung() { return noiDung; }
    public String getTenKhachHang(){return tenKhachHang;}
    public String getTenLead(){return tenLead;}
    public String getTenNhanVien(){return tenNhanVien;}
    public LocalDateTime getThoiGianThucHien() { return thoiGianThucHien; }
    public Long getNhanVienId() { return nhanVienId; }

    public void capNhapThongTin(LoaiHoatDong loaiHoatDong, String noiDung, Long nhanVienId, LocalDateTime thoiGianThucHien){
        if (loaiHoatDong == null) {
            throw new InvalidHoatDongException("Loại hoạt động Không được để trống,hãy điền vào đi.");
        }
        this.loaiHoatDong=loaiHoatDong;
        this.noiDung=noiDung;
        this.nhanVienId=nhanVienId;
        this.thoiGianThucHien = thoiGianThucHien;

    }
}