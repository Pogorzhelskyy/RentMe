package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.Booking;
import com.pogorzhelskyy.rentme.entity.DateFrame;
import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
            if ((dateFrame.from.after(b.getFrom()) && dateFrame.from.before(b.getUntil()))
            ||(dateFrame.until.after(b.getFrom()) && dateFrame.until.before(b.getUntil()))) return false;
        }
        return true;
    }

    public List<Booking> getActualBookingByHousing (Housing housing){
        return getByHousing(housing)
                .stream()
                .filter(booking -> booking.getUntil().after(new Date()))
                .sorted(Comparator.comparing(Booking::getUntil))
                .toList();
    }
}
