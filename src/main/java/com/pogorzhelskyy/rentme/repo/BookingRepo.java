package com.pogorzhelskyy.rentme.repo;

import com.pogorzhelskyy.rentme.entity.Booking;
import com.pogorzhelskyy.rentme.entity.Housing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo  extends JpaRepository<Booking, Long> {
     List <Booking> findByHousing (Housing housing);
}
