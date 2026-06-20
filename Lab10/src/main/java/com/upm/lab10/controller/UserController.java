package com.upm.lab10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PING WENCHAO 226969
 * UserController - Secured routing controller managing the authenticated user dashboard.
 * Endpoints within this mapping are protected by role-based Security filters.
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    /**
     * Routes authenticated identities to their personal dashboard interface.
     */
    @GetMapping("/")
    public String userDetails() {
        return "user";
    }
}