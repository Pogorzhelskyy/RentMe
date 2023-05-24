package com.pogorzhelskyy.rentme.controllers;

import com.pogorzhelskyy.rentme.entity.Booking;
import com.pogorzhelskyy.rentme.entity.DateFrame;
import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.service.BookingService;
import com.pogorzhelskyy.rentme.service.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class HousingController {
    private final HousingService housingService;
    private final BookingService bookingService;
@Autowired
    public HousingController(HousingService housingService, BookingService bookingService) {
        this.housingService = housingService;
        this.bookingService = bookingService;
}
    @GetMapping("/")
    public String start () {
    return "index";
    }

    @GetMapping("/find")
    public String findHousing (Model model,
                               @AuthenticationPrincipal User user,
                               @RequestParam ("city") String city,
                               @RequestParam ("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                               @RequestParam ("until")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate until){
        List <Housing> housingsAvailable = new ArrayList<>();
        List <Housing> housingsByCity = housingService.getByCity(city);
        DateFrame dateFrame = new DateFrame(from, until);
        for (Housing h: housingsByCity) {
            if (bookingService.isAvailable(h, dateFrame)) housingsAvailable.add(h);
        }
        model.addAttribute("entities", housingsAvailable);
    return "find";
    }

    @GetMapping("/housingById")
    public String getHousingById (Model model,
                                  @AuthenticationPrincipal User user,
                                  @RequestParam("housingId") Long housingId){
        Housing housing =  housingService.getById(housingId);
        List<Booking> actualBookings = housing.getBookings()
                .stream()
                .filter(booking -> booking.getCheckout().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Booking::getCheckout))
                .toList();
        model.addAttribute("onehousing", housing);
        model.addAttribute("bookings", actualBookings);
    return "housingbyid";
    }
    @PostMapping("/book")
    public String bookHousing(Model model,
                              @AuthenticationPrincipal User user,
                              @RequestParam ("housingId") Long housingId,
                              @RequestParam ("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkin,
                              @RequestParam ("until")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkout){
        DateFrame dateFrame = new DateFrame(checkin, checkout);
    if(bookingService.isAvailable(housingService.getById(housingId), dateFrame)){
        Booking booking = new Booking(housingService.getById(housingId),checkin, checkout,user);
        bookingService.save(booking);
        model.addAttribute("bookingConfirmation", "Thank you. Your booking is confirmed.");
    }else model.addAttribute("bookingConfirmation", "Is not available. Please try another dates.");
        model.addAttribute("onehousing", housingService.getById(housingId));
    return  "redirect:/housingById?housingId="+housingId;
    }
}
