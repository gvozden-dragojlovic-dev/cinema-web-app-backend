/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.repository;

import com.example.bioskopserver.BioskopServerApplication;
import com.example.bioskopserver.model.Hall;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MNG
 */
@SpringBootTest(classes = BioskopServerApplication.class)
public class HallRepositoryTest {

    @Autowired
    private HallRepository hallRepository;

    private Hall hall;

    @BeforeEach
    void setUp() {
        hall = new Hall();
        hall.setName("Main Hall");

        hall = hallRepository.save(hall);
    }

    @AfterEach
    void tearDown() {
        try {
            hallRepository.delete(hall);
        } catch (Exception ignored) {
        }
    }

    @Test
    void testSaveHall() {
        assertNotNull(hall.getId(), "Hall ID should not be null after save");
        assertEquals("Main Hall", hall.getName());
    }

    @Test
    void testFindById() {
        Optional<Hall> found = hallRepository.findById(hall.getId());

        assertTrue(found.isPresent());
        assertEquals("Main Hall", found.get().getName());
    }

    @Test
    void testFindAll() {
        List<Hall> halls = hallRepository.findAll();

        assertFalse(halls.isEmpty());
        assertTrue(halls.stream().anyMatch(h -> h.getName().equals("Main Hall")));
    }

    @Test
    void testDeleteHall() {
        Hall temp = new Hall();
        temp.setName("Temp Hall");
        temp = hallRepository.save(temp);

        Long id = temp.getId();

        hallRepository.deleteById(id);

        Optional<Hall> deleted = hallRepository.findById(id);
        assertFalse(deleted.isPresent());
    }
}
