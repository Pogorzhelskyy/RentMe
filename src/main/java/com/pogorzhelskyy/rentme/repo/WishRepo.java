package com.pogorzhelskyy.rentme.repo;

import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepo extends JpaRepository<Wish, Long> {
    List <Wish> findWishesByConsumer(User consumer);
}
