package com.pogorzhelskyy.rentme.controllers;

import com.pogorzhelskyy.rentme.entity.Role;
import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.service.BookingService;
import com.pogorzhelskyy.rentme.service.UserService;
import com.pogorzhelskyy.rentme.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;
    private final WishService wishService;
    private final BookingService bookingService;

    @Autowired
    public UserController(UserService userService, WishService wishService, BookingService bookingService) {
        this.userService = userService;
        this.wishService = wishService;
        this.bookingService = bookingService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/registration")
    public String registrationPost(Model model, @Valid User user,
                                   BindingResult bindingResult,
                                   @RequestParam("passwordConfirmation") String passwordConfirmation) {

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrors(bindingResult));
            return "registration";
        }
        if (!user.getPassword().equals(passwordConfirmation)) {
            model.addAttribute("errorPasswordConfirmation", "Passwords don't match");
            return "registration";
        }
        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("errorEmail", "Email is already exists");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        if (!userService.addUser(user)) {
            model.addAttribute("errorUsername", "User exists!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model,
                          @AuthenticationPrincipal User consumer) {
        model.addAttribute("bookings", bookingService.getByUser(consumer));
        model.addAttribute("wishlist", wishService.getByUser(consumer));
        return "profile";
    }
}
