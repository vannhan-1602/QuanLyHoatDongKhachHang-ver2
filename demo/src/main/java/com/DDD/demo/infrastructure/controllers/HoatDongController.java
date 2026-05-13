package com.DDD.demo.infrastructure.controllers;

import com.DDD.demo.domain.activity.HoatDong;
import com.DDD.demo.infrastructure.controllers.forms.TrackHoatDongForm;
import com.DDD.demo.infrastructure.controllers.forms.UpdateHoatDongForm;
import com.DDD.demo.use_cases.DeleteHoatDong;
import com.DDD.demo.use_cases.GetHoatDongHistory;
import com.DDD.demo.use_cases.TrackHoatDong;
import com.DDD.demo.use_cases.UpdateHoatDong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoat-dong")
public class HoatDongController {

    private final TrackHoatDong trackHoatDongUseCase;
    private final GetHoatDongHistory getHoatDongHistoryUseCase;
    private final UpdateHoatDong updateHoatDongUseCase;
    private final DeleteHoatDong deleteHoatDongUseCase;

    public HoatDongController(TrackHoatDong trackHoatDongUseCase,
                              GetHoatDongHistory getHoatDongHistoryUseCase,
                              UpdateHoatDong updateHoatDongUseCase,
                              DeleteHoatDong deleteHoatDongUseCase) {
        this.trackHoatDongUseCase = trackHoatDongUseCase;
        this.getHoatDongHistoryUseCase = getHoatDongHistoryUseCase;
        this.updateHoatDongUseCase = updateHoatDongUseCase;
        this.deleteHoatDongUseCase = deleteHoatDongUseCase;
    }
    @PostMapping
    public ResponseEntity<HoatDong> createActivity(@RequestBody TrackHoatDongForm form) {
        HoatDong hoatDong = trackHoatDongUseCase.execute(form.toDomain());
        return ResponseEntity.ok(hoatDong);
    }
    @GetMapping
    public ResponseEntity<List<HoatDong>> getAllActivities() {
        List<HoatDong> hoatDongs = getHoatDongHistoryUseCase.getAll();
        return ResponseEntity.ok(hoatDongs);
    }
    @GetMapping("/khach-hang/{khachHangId}")
    public ResponseEntity<List<HoatDong>> getByKhachHang(@PathVariable Long khachHangId) {
        List<HoatDong> hoatDongs = getHoatDongHistoryUseCase.getByKhachHangId(khachHangId);
        return ResponseEntity.ok(hoatDongs);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HoatDong> updateActivity(@PathVariable Long id, @RequestBody UpdateHoatDongForm form) {
        HoatDong updated = updateHoatDongUseCase.execute(id, form.getLoaiHoatDong(), form.getNoiDung(), form.getNhanVienId(),form.getThoiGianThucHien());
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        deleteHoatDongUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}