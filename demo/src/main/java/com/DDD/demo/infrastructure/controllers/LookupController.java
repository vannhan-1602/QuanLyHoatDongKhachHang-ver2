package com.DDD.demo.infrastructure.controllers;

import com.DDD.demo.infrastructure.database.entities.*;
import com.DDD.demo.infrastructure.database.repositories.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/lookup")
public class LookupController {
    private final KhachHangJpaRepository khRepo;
    private final LeadJpaRepository leadRepo;
    private final UserJpaRepository userRepo;

    public LookupController(KhachHangJpaRepository khRepo, LeadJpaRepository leadRepo, UserJpaRepository userRepo) {
        this.khRepo = khRepo;
        this.leadRepo = leadRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/khach-hang")
    public List<KhachHangEntity> getKhachHangs() { return khRepo.findAll(); }

    @GetMapping("/lead")
    public List<LeadEntity> getLeads() { return leadRepo.findAll(); }

    @GetMapping("/nhan-vien")
    public List<Map<String, Object>> getNhanViens() {
        return userRepo.findAll().stream().map(u -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", u.getId());
            map.put("name", u.getNhanSu() != null ? u.getNhanSu().getHoTen() : "Nhân viên " + u.getId());
            return map;
        }).toList();
    }
}