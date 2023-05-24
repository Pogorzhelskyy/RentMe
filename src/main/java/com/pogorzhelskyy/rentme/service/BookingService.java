package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.Booking;
import com.pogorzhelskyy.rentme.entity.DateFrame;
import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;
@Autowired
    public BookingService(BookingRepo bookingRepo) {
        this.bookingRepo = bookingRepo;

}
    public List<Booking> getByHousing (Housing housing){
    try {
       return bookingRepo.findByHousing(housing);
   }catch (Exception e){
       return new ArrayList<>();
   }
    }
    public List<Booking> getByUser (User consumer){
    return bookingRepo.findBookingsByConsumer(consumer);
    }

    public void save (Booking booking){
    bookingRepo.save(booking);
    }
    public void delete (Booking booking){
    bookingRepo.delete(booking);
    }
    public Boolean isAvailable(Housing housing, DateFrame dateFrame){
        List<Booking> bookings = getByHousing(housing);
        if (bookings.isEmpty()) return true;
        for (Booking b : bookings) {
            if ((dateFrame.from.isAfter(b.getCheckin()) && dateFrame.from.isBefore(b.getCheckout()))
            ||(dateFrame.until.isAfter(b.getCheckin()) && dateFrame.until.isBefore(b.getCheckout()))) return false;
        }
        return true;
    }

}
