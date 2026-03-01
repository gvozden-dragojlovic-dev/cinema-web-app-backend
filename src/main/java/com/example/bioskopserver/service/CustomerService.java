package com.example.bioskopserver.service;

import com.example.bioskopserver.model.Customer;
import com.example.bioskopserver.repository.CustomerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Customer> authenticate(String username, String password) {
        if (username == null || password == null) {
            return Optional.empty();
        }

        return customerRepository.findByUsername(username)
                .filter(customer -> passwordEncoder.matches(password, customer.getPassword()));
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public String register(String username, String firstName, String lastName, String password) {

        if (username == null || username.isBlank()) {
            return "ERROR: Username cannot be empty";
        }

        if (customerRepository.findByUsername(username).isPresent()) {
            return "ERROR: Username already exists";
        }

        String finalPassword = (password == null || password.isBlank())
                ? generatePassword()
                : password;

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(passwordEncoder.encode(finalPassword));
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        customerRepository.save(customer);

        return finalPassword;
    }

    private String generatePassword() {
        java.security.SecureRandom random = new java.security.SecureRandom();
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
    
    public void changePassword(Long adminId, String currentPassword, String newPassword) {

        Customer customer = customerRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(currentPassword, customer.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        customer.setPassword(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
    }
}