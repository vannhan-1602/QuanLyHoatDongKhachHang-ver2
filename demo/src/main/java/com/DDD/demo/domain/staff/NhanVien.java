package com.DDD.demo.domain.staff;

public class NhanVien {
    private Long id;
    private String hoTen;

    public NhanVien(Long id, String hoTen) {
        this.id=id;
        this.hoTen=hoTen;
    }

    public Long getId() { return id; }
    public String getHoTen() { return hoTen; }
}