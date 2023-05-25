package com.pogorzhelskyy.rentme.controllers;

import com.pogorzhelskyy.rentme.entity.User;
import com.pogorzhelskyy.rentme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
@RestController
public class RegistrationController {
    private final UserService userService;
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registrationPost(Model model,
                                   BindingResult bindingResult,
                                   @Valid User user
                                  /* @RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String email,
                                   @RequestParam String phone*/) {
        Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }
       /* final User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRoles(Collections.singleton(Role.USER));
        userService.addUser(user);*/
        return "redirect:/login";
    }
}
