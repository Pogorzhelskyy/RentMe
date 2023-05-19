package com.pogorzhelskyy.rentme.controllers;

import com.pogorzhelskyy.rentme.entity.Housing;
import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.service.HousingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class HousingController {
    private final HousingService housingService;
@Autowired
    public HousingController(HousingService housingService) {
        this.housingService = housingService;
    }
    @GetMapping("/")
    public String start (Model model,
                        @AuthenticationPrincipal User user) {
    return "index";
    }

    @GetMapping("/find")
    public String findHousing (Model model,
                               @AuthenticationPrincipal User user,
                               @RequestParam String city,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date until){
        List <Housing> availableHousings = housingService.findAvailable(city, from, until);
        model.addAttribute("entities", availableHousings);
    return "find";
    }

}
