package com.pogorzhelskyy.rentme.controllers;

import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.entity.Wish;
import com.pogorzhelskyy.rentme.service.HousingService;
import com.pogorzhelskyy.rentme.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishListController {
    private final WishService wishService;
    private final HousingService housingService;
    @Autowired
    public WishListController(WishService wishService, HousingService housingService) {
        this.wishService = wishService;
        this.housingService = housingService;
    }
    @PostMapping("/addWish")
    public String addWish (@AuthenticationPrincipal User user,
                           @RequestParam("housingId") Long housingId){
        wishService.save(new Wish(housingService.getById(housingId), user));
        return "redirect:/housingById?housingId="+housingId;
    }
    @PostMapping("/wishDel")
    public String delWish (@RequestParam ("wishId") Long wishId){
        wishService.deleteById(wishId);
        return "redirect:/profile";
    }
}
