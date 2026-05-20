package com.upm.lab8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * PING WENCHAO 226969
 * WebController - Primary controller handling routing logic for the application.
 * Updated for Lab 8 to integrate Bootstrap UI rendering via Thymeleaf templates.
 */
@Controller
public class WebController {

    /**
     * Primary dashboard endpoint routing to the Bootstrap-styled HTML view.
     * Lab 8 Update: Removed @ResponseBody to allow Thymeleaf template resolution.
     * @return The string filename mapping to index.html within template resources.
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * Processes requests with dynamic parameter handling via URL query strings.
     * @param name Input captured from the URL parameter, defaulting to 'Student'.
     * @return Formatted plain text string containing user input.
     */
    @GetMapping("/greet")
    @ResponseBody
    public String greetUser(@RequestParam(value = "name", defaultValue = "Student") String name) {
        return "Hello, " + name + "! Welcome to the secure Spring Boot application.";
    }

    /**
     * API endpoint simulating structured data layer retrieval.
     * @return A map object automatically serialized into JSON format.
     */
    @GetMapping("/api/info")
    @ResponseBody
    public Map<String, String> getLabInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("student_name", "PING WENCHAO");
        info.put("matric_no", "226969");
        info.put("lab_topic", "Bootstrap Integration");
        info.put("status", "Secure");
        return info;
    }

    /**
     * Maps the security login endpoint to serve the custom authentication interface.
     * @return The string filename mapping to login.html within template resources.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}