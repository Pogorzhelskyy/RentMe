package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.Booking;
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

    @Autowired
    public HousingService(HousingRepo housingRepo) {
        this.housingRepo = housingRepo;
    }

    public List<Housing> getAll() {
        return housingRepo.findAll();
    }

    public Housing getById(Long id) {
        return housingRepo.getById(id);
    }

    public List<Housing> getByCity(String city) {
        if (city.isBlank()) return getAll();
        return housingRepo.findHousingByCity(city);
    }

    public boolean isAvailable(Housing housing, LocalDate checkin, LocalDate checkout) {
        Set<Booking> bookings = housing.getBookings();
        if (bookings == null || bookings.isEmpty()) return true;
        for (Booking b : bookings) {
            if (
                    ((checkin.isAfter(b.getCheckin()) || (checkin.isEqual(b.getCheckin()))) && checkin.isBefore(b.getCheckout()))
                            || (checkout.isAfter(b.getCheckin()) && (checkout.isBefore(b.getCheckout()) || (checkout.isEqual(b.getCheckout()))))
                            || ((checkin.isBefore(b.getCheckin())) && (checkout.isAfter(b.getCheckout())))
            ) return false;

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

    public void save(Housing housing) {
        housingRepo.save(housing);
    }

    public void deleteById(Long id) {
        housingRepo.deleteById(id);
    }

    public Map<String, String> datesValidation(LocalDate checkin, LocalDate checkout) {
        Map<String, String> validationErrors = new HashMap<>();
        if (checkin == null) validationErrors.put("errorCheckin", "Please fill the checkin date");
        else if (checkin.isBefore(LocalDate.now()))
            validationErrors.put("checkinError", "Checkin date can't be in the past");
        if (checkout == null) validationErrors.put("errorCheckout", "Please fill the checkin date");
        if (checkin != null && checkout != null) {
            if (!checkout.isAfter(checkin))
                validationErrors.put("errorCheckout", "Checkout date must be after checkin");
        }
        return validationErrors;
    }

}
