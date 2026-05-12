package com.upm.lab6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

// PING WENCHAO 226969
// WebController - Handle web requests and return responses
@RestController
public class WebController {

    // Basic endpoint to handle the root URL and return a welcome message
    @GetMapping("/")
    public String home() {
        return "Welcome to Lab 6: My First Spring Boot Application!";
    }

    // Endpoint to handle greeting requests with a dynamic name parameter
    @GetMapping("/greet")
    public String greetUser(@RequestParam(value = "name", defaultValue = "Student") String name) {
        return "Hello, " + name + "! Welcome to the Spring Boot application.";
    }

    // API endpoint to return laboratory and student information as a JSON object
    @GetMapping("/api/info")
    public Map<String, String> getLabInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("student_name", "PING WENCHAO");
        info.put("matric_no", "226969");
        info.put("lab_topic", "Spring Boot Fundamentals");
        info.put("status", "Success");
        return info;
    }
}