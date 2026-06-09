package com.upm.lab9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * PING WENCHAO 226969
 * HomeController - Basic routing controller for public and foundational system pages.
 * Resolves the infinite redirect loop by explicitly mapping the login and dashboard views.
 */
@Controller
public class HomeController {

    /**
     * Routes to the main Enterprise Dashboard (index.html) after successful authentication.
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * Routes to the custom Bootstrap 5 authentication page (login.html).
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}