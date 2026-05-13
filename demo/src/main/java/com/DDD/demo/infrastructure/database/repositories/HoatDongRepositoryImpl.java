package com.DDD.demo.infrastructure.database.repositories;

import com.DDD.demo.domain.activity.HoatDong;
import com.DDD.demo.domain.activity.HoatDongRepository;
import com.DDD.demo.infrastructure.database.entities.HoatDongEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HoatDongRepositoryImpl implements HoatDongRepository {

    private final HoatDongJpaRepository jpaRepository;

    public HoatDongRepositoryImpl(HoatDongJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public HoatDong save(HoatDong hoatDong) {
        HoatDongEntity entity = toEntity(hoatDong);
        HoatDongEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }
    @Override
    public List<HoatDong> findAll() {

        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<HoatDong> findByKhachHangId(Long khachHangId) {
        return jpaRepository.findAllByKhachHangId(khachHangId)
                .stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<HoatDong> findByLeadId(Long leadId) {
        return jpaRepository.findAllByLeadId(leadId)
                .stream().map(this::toDomain).collect(Collectors.toList());
    }
    @Override
    public Optional<HoatDong> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }


    private HoatDongEntity toEntity(HoatDong domain) {
        HoatDongEntity entity = new HoatDongEntity();
        entity.setId(domain.getId());
        entity.setKhachHangId(domain.getKhachHangId());
        entity.setLeadId(domain.getLeadId());
        entity.setLoaiHoatDong(domain.getLoaiHoatDong());
        entity.setNoiDung(domain.getNoiDung());
        entity.setThoiGianThucHien(domain.getThoiGianThucHien());
        entity.setNhanVienId(domain.getNhanVienId());
        return entity;
    }

    private HoatDong toDomain(HoatDongEntity entity) {
        HoatDong domain = new HoatDong(
                entity.getKhachHangId(),
                entity.getKhachHang() != null ? entity.getKhachHang().getTenKhachHang() : null,
                entity.getLeadId(),
                entity.getLead() != null ? entity.getLead().getTenLead() : null,
                entity.getLoaiHoatDong(),
                entity.getNoiDung(),
                entity.getThoiGianThucHien(),
                entity.getNhanVienId(),
                entity.getNhanVien() != null ? entity.getNhanVien().getNhanSu().getHoTen() : "N/A"
        );
        domain.setId(entity.getId());
        return domain;
    }
}