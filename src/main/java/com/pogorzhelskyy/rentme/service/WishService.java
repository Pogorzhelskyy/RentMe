package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.entity.Wish;
import com.pogorzhelskyy.rentme.repo.WishRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Wish> getByUser(User consumer){
        return wishRepo.findWishesByConsumer(consumer);
    }
}
