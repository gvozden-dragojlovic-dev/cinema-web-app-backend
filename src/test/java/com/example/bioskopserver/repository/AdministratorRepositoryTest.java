/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Administrator;
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
@SpringBootTest
public class AdministratorRepositoryTest {

    @Autowired
    private AdministratorRepository administratorRepository;

    private Administrator admin;

    @BeforeEach
    public void setUp() {
        admin = new Administrator();
        admin.setUsername("admin");
        admin.setPassword("password123");
        admin = administratorRepository.save(admin);
    }

    @AfterEach
    public void tearDown() {
        try {
            administratorRepository.delete(admin);
        } catch (Exception e) {
        }
    }

    @Test
    public void testSaveAdministrator() {
        assertNotNull(admin.getAdministratorID(), "Administrator ID should not be null after save");
        assertEquals("admin", admin.getUsername(), "Username should match saved value");
    }

    @Test
    public void testFindById() {
        Optional<Administrator> found = administratorRepository.findById(admin.getAdministratorID());
        assertTrue(found.isPresent(), "Administrator should be found by ID");
        assertEquals(admin.getUsername(), found.get().getUsername(), "Username should match");
    }

    @Test
    public void testDeleteAdministrator() {
        Administrator toDelete = new Administrator();
        toDelete.setUsername("todelete");
        toDelete.setPassword("pass");
        toDelete = administratorRepository.save(toDelete);

        administratorRepository.delete(toDelete);

        Optional<Administrator> deleted = administratorRepository.findById(toDelete.getAdministratorID());
        assertFalse(deleted.isPresent(), "Administrator should be deleted");
    }

    @Test
    public void testUpdateAdministrator() {
        admin.setPassword("newPassword");
        Administrator updated = administratorRepository.save(admin);
        assertEquals("newPassword", updated.getPassword(), "Password should be updated");
    }
}