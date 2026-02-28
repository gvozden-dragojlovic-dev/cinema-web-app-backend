package com.example.bioskopserver.controller;

import com.example.bioskopserver.service.CustomerService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {

        if (request.getUsername() == null || request.getPassword() == null) {
            return "Invalid username or password";
        }

        boolean success = customerService.authenticate(
                request.getUsername(),
                request.getPassword()
        );

        if (success) {
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequestDTO request) {

        Map<String, String> response = new HashMap<>();

        String result = customerService.register(
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
                request.getDateOfBirth(),
                request.getGender(),
                request.getMobileNumber(),
                request.getPassword()
        );

        if (result.startsWith("ERROR")) {
            response.put("error", result.replace("ERROR: ", ""));
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", "Registration successful");
        response.put("password", result);

        return ResponseEntity.ok(response);
    }
}