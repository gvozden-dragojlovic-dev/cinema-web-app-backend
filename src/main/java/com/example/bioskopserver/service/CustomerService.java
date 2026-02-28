package com.example.bioskopserver.service;

import com.example.bioskopserver.model.Customer;
import com.example.bioskopserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

  public String register(String username, String firstName, String lastName,
    String dateOfBirthStr, String gender, String mobileNumber, String password) {

    if (username == null || username.isBlank()) {
        return "ERROR: Username cannot be empty";
    }

    if (customerRepository.findByUsername(username).isPresent()) {
        return "ERROR: Username already exists";
    }

    LocalDate dob;
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
      dob = LocalDate.parse(dateOfBirthStr, formatter);
    } catch (Exception e) {
      throw new RuntimeException("ERROR: Invalid date format. Use yyyy-MM-dd");
    }

    String finalPassword = (password == null || password.isBlank()) ?
      generatePassword() : password;

    Customer customer = new Customer();
    customer.setUsername(username);
    customer.setPassword(finalPassword);
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setDateOfBirth(dob);
    customer.setGender(gender);
    customer.setMobileNumber(mobileNumber);

    System.out.println("Saving customer: " + username);
    customerRepository.save(customer);
    System.out.println("Saved customer ID: " + customer.getKupacID());

    return finalPassword;
  }

  private String generatePassword() {
    java.security.SecureRandom random = new java.security.SecureRandom();
    byte[] bytes = new byte[6];
    random.nextBytes(bytes);
    return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
  }
}