package com.example.bioskopserver.service;

import com.example.bioskopserver.model.Customer;
import com.example.bioskopserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public boolean authenticate(String username, String password) {
    if (username == null || password == null) return false;

    return customerRepository.findByUsername(username)
      .map(customer -> customer.getPassword().equals(password))
      .orElse(false);
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
    customer.setPassword(finalPassword);
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
}