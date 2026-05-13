package com.DDD.demo.infrastructure.database.entities;

import com.DDD.demo.domain.activity.LoaiHoatDong;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "KH_HoatDong")
@Getter @Setter
public class HoatDongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KhachHang_Id", insertable = false, updatable = false)
    private KhachHangEntity khachHang;

    @Column(name = "KhachHang_Id")
    private Long khachHangId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Lead_Id", insertable = false, updatable = false)
    private LeadEntity lead;

    @Column(name = "Lead_Id")
    private Long leadId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NhanVien_Id", insertable = false, updatable = false)
    private UserEntity nhanVien;

    @Column(name = "NhanVien_Id")
    private Long nhanVienId;

    @Enumerated(EnumType.STRING)
    private LoaiHoatDong loaiHoatDong;

    private String noiDung;
    private LocalDateTime thoiGianThucHien;
}