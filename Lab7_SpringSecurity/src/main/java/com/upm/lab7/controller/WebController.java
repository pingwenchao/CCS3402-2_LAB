package com.upm.lab7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * PING WENCHAO 226969
 * WebController - Primary controller handling routing logic for the application.
 * Utilizes @Controller to support HTML view rendering alongside JSON data endpoints.
 */
@Controller
public class WebController {

    /**
     * Basic endpoint mapping the root URL to verify operational status.
     * @return Plain text response body directly to the client view.
     */
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome to Spring Boot! Authentication Successful.";
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
        info.put("lab_topic", "Spring Security Implementation");
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