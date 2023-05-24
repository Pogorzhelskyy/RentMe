package com.pogorzhelskyy.rentme.repo;

import com.pogorzhelskyy.rentme.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepo extends JpaRepository<Wish, Long> {
}
