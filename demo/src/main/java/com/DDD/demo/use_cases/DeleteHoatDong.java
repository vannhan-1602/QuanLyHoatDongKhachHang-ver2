package com.DDD.demo.use_cases;

import com.DDD.demo.domain.activity.HoatDongRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteHoatDong {
    private final HoatDongRepository hoatDongRepository;
    public DeleteHoatDong(HoatDongRepository hoatDongRepository){
        this.hoatDongRepository=hoatDongRepository;
    }
    public void execute(Long id){
        hoatDongRepository.deleteById(id);
    }
}
