package com.pogorzhelskyy.rentme.controllers;

import com.pogorzhelskyy.rentme.entity.Role;
import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public String registrationPost(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String email,
                                   @RequestParam String phone) {
        final User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRoles(Collections.singleton(Role.USER));
        userService.addUser(user);
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
    public String profile (Model model,
                           @AuthenticationPrincipal User user){
        model.addAttribute("bookings", user.getBookings());
        return "profile";
    }
}
