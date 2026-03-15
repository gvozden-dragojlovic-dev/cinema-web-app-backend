/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.controller;

import com.example.bioskopserver.DTOs.*;
import com.example.bioskopserver.model.Customer;
import com.example.bioskopserver.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author MNG
 */
public class CustomerControllerTest {
    
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assertNotNull(customerController);
        assertNotNull(customerService);
    }

    @Test
    void testLoginSuccess() {
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsername("pera");
        request.setPassword("123");

        Customer customer = new Customer();
        customer.setCustomerID(1L);
        customer.setFirstName("Pera");
        customer.setLastName("Peric");
        customer.setUsername("pera");

        when(customerService.authenticate("pera", "123")).thenReturn(Optional.of(customer));

        ResponseEntity<?> response = customerController.login(request);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void testLoginFail() {
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsername("pera");
        request.setPassword("wrong");

        when(customerService.authenticate("pera", "wrong")).thenReturn(Optional.empty());

        ResponseEntity<?> response = customerController.login(request);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Invalid username or password", response.getBody());
    }

    @Test
    void testRegisterSuccess() {
        RegisterRequestDTO request = new RegisterRequestDTO();
        request.setUsername("pera");
        request.setFirstName("Pera");
        request.setLastName("Peric");
        request.setPassword("123");

        when(customerService.register(anyString(), anyString(), anyString(), anyString()))
                .thenReturn("123");

        ResponseEntity<Map<String, String>> response = customerController.register(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Registration successful", response.getBody().get("message"));
        assertEquals("123", response.getBody().get("password"));
    }

    @Test
    void testRegisterUsernameExists() {
        assertNotNull(customerController);
        assertNotNull(customerService);
        
        RegisterRequestDTO request = new RegisterRequestDTO();
        request.setUsername("pera");
        request.setFirstName("Pera");
        request.setLastName("Peric");
        request.setPassword("123");

        when(customerService.register(any(), any(), any(), any()))
            .thenReturn("ERROR: Username already exists");

        ResponseEntity<Map<String, String>> response = customerController.register(request);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Username already exists", response.getBody().get("error"));
    }

    @Test
    void testGetAccountSuccess() {
        Customer customer = new Customer();
        customer.setCustomerID(1L);
        customer.setFirstName("Pera");
        customer.setLastName("Peric");
        customer.setUsername("pera");

        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));

        ResponseEntity<?> response = customerController.getAccount(1L);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetAccountFail() {
        when(customerService.getCustomerById(2L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = customerController.getAccount(2L);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("User not found", response.getBody());
    }

    @Test
    void testChangePasswordSuccess() {
        ChangePasswordRequestDTO request = new ChangePasswordRequestDTO();
        request.setAdminId(1L);
        request.setCurrentPassword("123");
        request.setNewPassword("456");

        doNothing().when(customerService).changePassword(1L, "123", "456");

        ResponseEntity<?> response = customerController.changePassword(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Password changed successfully", response.getBody());
    }

    @Test
    void testChangePasswordFail() {
        ChangePasswordRequestDTO request = new ChangePasswordRequestDTO();
        request.setAdminId(1L);
        request.setCurrentPassword("wrong");
        request.setNewPassword("456");

        doThrow(new RuntimeException("Current password is incorrect"))
                .when(customerService).changePassword(1L, "wrong", "456");

        ResponseEntity<?> response = customerController.changePassword(request);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Current password is incorrect", response.getBody());
    }
}
