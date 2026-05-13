package com.DDD.demo.infrastructure.database.repositories;

import com.DDD.demo.infrastructure.database.entities.KhachHangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangJpaRepository extends JpaRepository<KhachHangEntity, Long> {}