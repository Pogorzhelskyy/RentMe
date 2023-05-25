package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.Booking;
import com.pogorzhelskyy.rentme.entity.DateFrame;
import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.repo.HousingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class HousingService {
    private final HousingRepo housingRepo;
  //  private final BookingService bookingService;
@Autowired
    public HousingService(HousingRepo housingRepo) {
        this.housingRepo = housingRepo;
 //   this.bookingService = bookingService;
}
    public List <Housing> getAll(){
    return housingRepo.findAll();
    }
    public Housing getById (Long id) {
        return housingRepo.getById(id);
    }
    public  List <Housing> getByCity (String city){return housingRepo.findHousingByCity(city);}
    public Boolean isAvailable(Housing housing, LocalDate checkin, LocalDate checkout){
        Set<Booking> bookings = housing.getBookings();
        if (bookings.isEmpty()) return true;
        for (Booking b : bookings) {
            if ((checkin.isAfter(b.getCheckin()) && checkin.isBefore(b.getCheckout()))
                    ||(checkout.isAfter(b.getCheckin()) && checkout.isBefore(b.getCheckout()))) return false;
        }
        return true;
    }
    public Page<Housing> getAvailableByCity(String city, LocalDate checkin, LocalDate checkout, Pageable pageable) {
        List<Housing> housingsByCity = getByCity(city);
        List<Housing> housingsAvailable = new ArrayList<>();

        for (Housing h : housingsByCity) {
            if (isAvailable(h, checkin, checkout)) {
                housingsAvailable.add(h);
            }
        }

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Housing> sublist;

        if (housingsAvailable.size() < startItem) {
            sublist = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, housingsAvailable.size());
            sublist = housingsAvailable.subList(startItem, toIndex);
        }

        return new PageImpl<>(sublist, pageable, housingsAvailable.size());
    }

    public void save (Housing housing){
    housingRepo.save(housing);
    }
    public void deleteById (Long id){
    housingRepo.deleteById(id);}

}
