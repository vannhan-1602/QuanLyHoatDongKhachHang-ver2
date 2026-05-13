package com.DDD.demo.infrastructure.database.repositories;

import com.DDD.demo.infrastructure.database.entities.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadJpaRepository extends JpaRepository<LeadEntity, Long> {}