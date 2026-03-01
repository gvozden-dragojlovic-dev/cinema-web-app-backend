package com.example.bioskopserver.controller;

import com.example.bioskopserver.model.Customer;
import com.example.bioskopserver.service.CustomerService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        Optional<Customer> customerOpt = customerService.authenticate(
                request.getUsername(),
                request.getPassword()
        );

        if (customerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

        Customer customer = customerOpt.get();

        AccountResponseDTO response = new AccountResponseDTO(
                customer.getCustomerID(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUsername()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequestDTO request) {

        Map<String, String> response = new HashMap<>();

        String result = customerService.register(
                request.getUsername(),
                request.getFirstName(),
                request.getLastName(),
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

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {

        Optional<Customer> customerOpt = customerService.getCustomerById(id);

        if (customerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Customer customer = customerOpt.get();

        AccountResponseDTO response = new AccountResponseDTO(
                customer.getCustomerID(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getUsername()
        );

        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestDTO request) {

        try {
            customerService.changePassword(
                    request.getAdminId(),
                    request.getCurrentPassword(),
                    request.getNewPassword()
            );

            return ResponseEntity.ok("Password changed successfully");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}