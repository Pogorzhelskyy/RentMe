package com.pogorzhelskyy.rentme.repo;

import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepo  extends JpaRepository<Photo, Long> {
    List<Photo> findByHousing (Housing housing);
}
