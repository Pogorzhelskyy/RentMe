package com.pogorzhelskyy.rentme.controllers;

import com.pogorzhelskyy.rentme.entity.Booking;
import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.service.BookingService;
import com.pogorzhelskyy.rentme.service.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@Validated
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
                               @RequestParam ("city") String city,
                               @RequestParam ("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkin,
                               @RequestParam ("until") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkout,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Map<String, String> errors = housingService.datesValidation(checkin,checkout);
        if(!errors.isEmpty()){
            model.mergeAttributes(errors);
            return "index";
        }
        Page<Housing> page = housingService.getAvailableByCity(city, checkin, checkout, pageable);
        model.addAttribute("page", page);
        model.addAttribute("url", "/find?city="+city+"&from="+checkin+"&until="+checkout);
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
        model.addAttribute("photos", housing.getPhotos());
    return "housingbyid";
    }
    @PostMapping("/book")
    public String bookHousing(@AuthenticationPrincipal User user,
                              Model model,
                              @RequestParam ("housingId") Long housingId,
                              @RequestParam ("checkin") @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate checkin,
                              @RequestParam ("checkout") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkout
                              ){
    Map<String, String> errors = housingService.datesValidation(checkin,checkout);
    if(!errors.isEmpty()){
        model.mergeAttributes(errors);
        return "housingById";
    }
    if(housingService.isAvailable(housingService.getById(housingId), checkin, checkout)){
        Booking booking = new Booking(housingService.getById(housingId),checkin, checkout,user);
        bookingService.save(booking);
        model.addAttribute("confirmationBooking", "Thank you. Your booking is confirmed.");
    }else model.addAttribute("errorBooking", "Is not available. Please try another dates.");

    model.addAttribute("onehousing", housingService.getById(housingId));
    return  "redirect:/housingById?housingId="+housingId;
    }
}
