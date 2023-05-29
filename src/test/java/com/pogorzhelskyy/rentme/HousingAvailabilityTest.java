package com.pogorzhelskyy.rentme;

import com.pogorzhelskyy.rentme.entity.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.repo.HousingRepo;
import com.pogorzhelskyy.rentme.service.HousingService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class HousingAvailabilityTest {
    private HousingService housingService;
    @Mock
    private HousingRepo housingRepo;
    private Housing housing;
    private LocalDate checkin;
    private LocalDate checkout;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        housingService = new HousingService(housingRepo);
        housing = new Housing();
        checkin = LocalDate.of(2023, 5, 1);
        checkout = LocalDate.of(2023, 5, 5);
    }

    @Test
    void testIsAvailable_NoBookings() {
        boolean available = housingService.isAvailable(housing, checkin, checkout);
        Assertions.assertTrue(available);
    }

    @Test
    void testIsAvailable_BookingsDoNotOverlap() {
        Booking booking1 = new Booking(LocalDate.of(2023, 4, 20), LocalDate.of(2023, 4, 25));
        Booking booking2 = new Booking(LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 15));
        Set<Booking> bookings = new HashSet<>();
        bookings.add(booking1);
        bookings.add(booking2);
        housing.setBookings(bookings);

        boolean available = housingService.isAvailable(housing, checkin, checkout);
        Assertions.assertTrue(available);
    }

    @Test
    void testIsAvailable_BookingOverlapsCheckin() {
        Booking booking = new Booking(LocalDate.of(2023, 4, 30), LocalDate.of(2023, 5, 3));
        Set<Booking> bookings = new HashSet<>();
        bookings.add(booking);
        housing.setBookings(bookings);

        boolean available = housingService.isAvailable(housing, checkin, checkout);
        Assertions.assertFalse(available);
    }

    @Test
    void testIsAvailable_BookingOverlapsCheckout() {
        Booking booking = new Booking(LocalDate.of(2023, 5, 3), LocalDate.of(2023, 5, 10));
        Set<Booking> bookings = new HashSet<>();
        bookings.add(booking);
        housing.setBookings(bookings);

        boolean available = housingService.isAvailable(housing, checkin, checkout);
        Assertions.assertFalse(available);
    }

    @Test
    void testIsAvailable_BookingOverlapsCheckinAndCheckout() {
        Booking booking = new Booking(LocalDate.of(2023, 4, 30), LocalDate.of(2023, 5, 3));
        Set<Booking> bookings = new HashSet<>();
        bookings.add(booking);
        housing.setBookings(bookings);

        boolean available = housingService.isAvailable(housing, checkin, checkout);
        Assertions.assertFalse(available);
    }
}
