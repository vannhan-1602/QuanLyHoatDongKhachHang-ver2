package com.DDD.demo.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "KH_KhachHang")
@Getter
@Setter
public class KhachHangEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TenKhachHang")
    private String tenKhachHang;


}