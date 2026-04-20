/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.repository;

import com.example.bioskopserver.BioskopServerApplication;
import com.example.bioskopserver.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MNG
 */
@SpringBootTest(classes = BioskopServerApplication.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setUsername("john_doe");
        customer.setPassword("pass123");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer = customerRepository.save(customer);
    }

    @AfterEach
    public void tearDown() {
        try {
            customerRepository.delete(customer);
        } catch (Exception e) {
        }
    }

    @Test
    public void testSaveCustomer() {
        assertNotNull(customer.getCustomerID(), "Customer ID should not be null after save");
        assertEquals("john_doe", customer.getUsername(), "Username should match saved value");
    }

    @Test
    public void testFindById() {
        Optional<Customer> found = customerRepository.findById(customer.getCustomerID());
        assertTrue(found.isPresent(), "Customer should be found by ID");
        assertEquals(customer.getUsername(), found.get().getUsername(), "Username should match");
    }

    @Test
    public void testFindByUsername() {
        Optional<Customer> found = customerRepository.findByUsername("john_doe");
        assertTrue(found.isPresent(), "Customer should be found by username");
        assertEquals(customer.getCustomerID(), found.get().getCustomerID(), "ID should match");
    }

    @Test
    public void testFindByUsernameAndPassword() {
        Optional<Customer> found = customerRepository.findByUsernameAndPassword("john_doe", "pass123");
        assertTrue(found.isPresent(), "Customer should be found by username and password");
        assertEquals(customer.getCustomerID(), found.get().getCustomerID(), "ID should match");
    }

    @Test
    public void testUpdateCustomer() {
        customer.setPassword("newPass456");
        Customer updated = customerRepository.save(customer);
        assertEquals("newPass456", updated.getPassword(), "Password should be updated");
    }

    @Test
    public void testDeleteCustomer() {
        Customer toDelete = new Customer();
        toDelete.setUsername("delete_me");
        toDelete.setPassword("pass");
        toDelete.setFirstName("Del");
        toDelete.setLastName("Ete");
        toDelete = customerRepository.save(toDelete);

        customerRepository.delete(toDelete);
        Optional<Customer> deleted = customerRepository.findById(toDelete.getCustomerID());
        assertFalse(deleted.isPresent(), "Customer should be deleted");
    }
}
