package com.pogorzhelskyy.rentme.repo;

import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo  extends JpaRepository<Review, Long> {
    List<Review> findByHousing (Housing housing);
}
