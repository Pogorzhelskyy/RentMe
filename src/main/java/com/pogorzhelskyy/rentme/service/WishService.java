package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.Wish;
import com.pogorzhelskyy.rentme.repo.WishRepo;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishRepo wishRepo;

    public WishService(WishRepo wishRepo) {
        this.wishRepo = wishRepo;
    }
    public void save (Wish wish){
        wishRepo.save(wish);
    }
    public void deleteById (Long id){
        wishRepo.deleteById(id);
    }
}
