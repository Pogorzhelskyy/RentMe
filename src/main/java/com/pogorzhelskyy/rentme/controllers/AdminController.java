package com.pogorzhelskyy.rentme.controllers;

import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.entity.Photo;
import com.pogorzhelskyy.rentme.service.BookingService;
import com.pogorzhelskyy.rentme.service.HousingService;
import com.pogorzhelskyy.rentme.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final HousingService housingService;
  //  private final BookingService bookingService;
    private final PhotoService photoService;

    @Autowired
    public AdminController(HousingService housingService, BookingService bookingService, PhotoService photoService) {
        this.housingService = housingService;
   //     this.bookingService = bookingService;
        this.photoService = photoService;
    }

    @GetMapping("/addHousing")
    public String addHousin() {
        return "addHousing";
    }

    @PostMapping("/addHousing")
    public String addHousingPost(Model model,
                                 @RequestParam String city,
                                 @RequestParam String address,
                                 @RequestParam int rooms,
                                 @RequestParam int square,
                                 @RequestParam int price,
                                 @RequestParam String description) {
        Housing housing = new Housing();
        housing.setCity(city);
        housing.setAddress(address);
        housing.setRooms(rooms);
        housing.setSquare(square);
        housing.setPrice(price);
        housing.setDescription(description);
        housingService.save(housing);
        return "redirect:/addHousing";
    }
    @PostMapping("/addPhoto")
    public String addPhotoPost(Model model,
                           @RequestParam ("photoLink") String photoLink,
                           @RequestParam ("housingId") Long housingId){
        Photo photo = new Photo();
        photo.setLink(photoLink);
        photo.setHousing(housingService.getById(housingId));
        return "redirect:/oneHousingDetails?housingId="+housingId;
    }
    @PostMapping("/housingDel")
    public String deleteHousing(@RequestParam ("housingId") Long id){
        housingService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/photoDel")
    public String deletePhoto(@RequestParam ("photoId") Long id){
        Long housingId = photoService.getById(id).getHousing().getId();
        photoService.deleteById(id);
        return "redirect:/housingById?housingId="+housingId;
    }

    @PostMapping("/setPrice")
    public  String setPrice(@RequestParam("housingId") Long housingId,
                            @RequestParam("price") int price){
        Housing housing = housingService.getById(housingId);
        housing.setPrice(price);
        housingService.save(housing);
        return "redirect:/housingById?housingId="+housingId;
    }
    @PostMapping("/setDescription")
    public String setDescription(@RequestParam("housingId") Long housingId,
                                 @RequestParam("description") String description){
        Housing housing = housingService.getById(housingId);
        housing.setDescription(description);
        housingService.save(housing);
        return "redirect:/housingById?housingId="+housingId;
    }
}