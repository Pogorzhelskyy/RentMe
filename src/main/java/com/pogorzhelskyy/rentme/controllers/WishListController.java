package com.pogorzhelskyy.rentme.controllers;

import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.entity.Wish;
import com.pogorzhelskyy.rentme.service.HousingService;
import com.pogorzhelskyy.rentme.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String addWish (Model model,
                           @AuthenticationPrincipal User user,
                           @RequestParam("housingId") Long housingId){
        if (!wishService.save(new Wish(housingService.getById(housingId), user))) {
            model.addAttribute("errorWish", "Already added to wishlist!");
            return "redirect:/housing-by-id?housingId=%d".formatted(housingId);
        };
        return "redirect:/housing-by-id?housingId=%d".formatted(housingId);
    }
    @PostMapping("/wishDel")
    public String delWish (@RequestParam ("wishId") Long wishId){
        wishService.deleteById(wishId);
        return "redirect:/profile";
    }
}
