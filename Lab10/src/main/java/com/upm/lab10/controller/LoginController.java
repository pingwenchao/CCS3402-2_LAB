package com.upm.lab10.controller;

import com.upm.lab10.dto.UserDto;
import com.upm.lab10.model.User;
import com.upm.lab10.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * PING WENCHAO 226969
 * LoginController - Routing engine managing authentication and registration workflows.
 * Validates incoming Data Transfer Objects (DTO) and delegates persistence to the Service layer.
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Routes incoming requests to the custom Bootstrap login interface.
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /**
     * Initializes an empty DTO payload and routes to the registration interface.
     */
    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    /**
     * Intercepts POST payloads from the registration form, executes structural validation,
     * checks for email duplication, and delegates verified payloads to the persistence layer.
     */
    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {

        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null) {
            result.rejectValue("email", null, "User already registered !!!");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "registration";
        }

        userService.saveUser(userDto);
        return "redirect:/registration?success";
    }
}