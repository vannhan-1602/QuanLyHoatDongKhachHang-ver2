package com.DDD.demo.domain.customer;

public class KhachHang {
    private Long id;
    private String tenKhachHang;

    public KhachHang(Long id, String tenKhachHang) {
        this.id = id;
        this.tenKhachHang = tenKhachHang;
    }

    public Long getId() { return id; }
    public String getTenKhachHang() { return tenKhachHang; }
}