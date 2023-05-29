package com.pogorzhelskyy.rentme.service;

import com.pogorzhelskyy.rentme.entity.Booking;
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

    public List<Booking> getByUser(User consumer) {
        return bookingRepo.findBookingsByConsumer(consumer);
    }

    public void save(Booking booking) {
        bookingRepo.save(booking);
    }

    public void delete(Booking booking) {
        bookingRepo.delete(booking);
    }

}
