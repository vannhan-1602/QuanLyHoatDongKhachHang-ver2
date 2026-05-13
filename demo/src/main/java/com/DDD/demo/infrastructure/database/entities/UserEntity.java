package com.DDD.demo.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "HT_User")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NhanSu_Id")
    private Long nhanSuId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NhanSu_Id", insertable = false, updatable = false)
    private ThongTinNhanSuEntity nhanSu;
}