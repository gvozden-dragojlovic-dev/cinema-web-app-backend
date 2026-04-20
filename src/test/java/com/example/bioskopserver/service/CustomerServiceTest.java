/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.service;

import com.example.bioskopserver.model.Customer;
import com.example.bioskopserver.repository.CustomerRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 *
 * @author MNG
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testAuthenticateSuccess() {

        Customer customer = new Customer();
        customer.setUsername("pera");
        customer.setPassword("encoded");

        when(customerRepository.findByUsername("pera"))
                .thenReturn(Optional.of(customer));

        when(passwordEncoder.matches("123", "encoded"))
                .thenReturn(true);

        Optional<Customer> result = customerService.authenticate("pera", "123");

        assertTrue(result.isPresent());
        assertEquals("pera", result.get().getUsername());
    }

    @Test
    void testAuthenticateFail() {

        when(customerRepository.findByUsername("pera"))
                .thenReturn(Optional.empty());

        Optional<Customer> result = customerService.authenticate("pera", "123");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCustomerById() {

        Customer customer = new Customer();
        customer.setCustomerID(1L);

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getCustomerID());
    }

    @Test
    void testRegisterSuccess() {

        when(customerRepository.findByUsername("pera"))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        String password = customerService.register("pera", "Pera", "Peric", "123");

        assertEquals("123", password);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testRegisterUsernameExists() {

        Customer customer = new Customer();

        when(customerRepository.findByUsername("pera"))
                .thenReturn(Optional.of(customer));

        String result = customerService.register("pera", "Pera", "Peric", "123");

        assertEquals("ERROR: Username already exists", result);
    }
}
