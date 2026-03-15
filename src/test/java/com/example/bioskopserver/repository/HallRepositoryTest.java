/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Hall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author MNG
 */
public class HallRepositoryTest {
    
    @Mock
    private HallRepository hallRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveHall() {
        Hall hall = new Hall();
        hall.setName("Main Hall");

        when(hallRepository.save(hall)).thenReturn(hall);

        Hall savedHall = hallRepository.save(hall);

        assertNotNull(savedHall);
        assertEquals("Main Hall", savedHall.getName());
        verify(hallRepository, times(1)).save(hall);
    }

    @Test
    public void testFindById() {
        Hall hall = new Hall();
        hall.setId(1L);
        hall.setName("Main Hall");

        when(hallRepository.findById(1L)).thenReturn(Optional.of(hall));

        Optional<Hall> foundHall = hallRepository.findById(1L);
        assertTrue(foundHall.isPresent());
        assertEquals("Main Hall", foundHall.get().getName());
        verify(hallRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        Hall hall1 = new Hall();
        hall1.setName("Hall 1");
        Hall hall2 = new Hall();
        hall2.setName("Hall 2");

        when(hallRepository.findAll()).thenReturn(Arrays.asList(hall1, hall2));

        List<Hall> halls = hallRepository.findAll();
        assertEquals(2, halls.size());
        verify(hallRepository, times(1)).findAll();
    }

    @Test
    public void testDelete() {
        Hall hall = new Hall();
        hall.setId(1L);

        doNothing().when(hallRepository).deleteById(1L);

        hallRepository.deleteById(1L);

        verify(hallRepository, times(1)).deleteById(1L);
    }
    
}
