/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Customer;
import com.example.bioskopserver.model.Screening;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 *
 * @author MNG
 */
public class ScreeningRepositoryTest {
    
    @Mock
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByCustomer_CustomerID() {
        Customer admin = new Customer();
        admin.setCustomerID(1L);

        Screening s1 = new Screening();
        s1.setCustomer(admin);
        Screening s2 = new Screening();
        s2.setCustomer(admin);

        when(screeningRepository.findByCustomer_CustomerID(1L))
                .thenReturn(Arrays.asList(s1, s2));

        List<Screening> screenings = screeningRepository.findByCustomer_CustomerID(1L);

        assertNotNull(screenings);
        assertEquals(2, screenings.size());
        assertEquals(admin, screenings.get(0).getCustomer());
        assertEquals(admin, screenings.get(1).getCustomer());

        verify(screeningRepository, times(1)).findByCustomer_CustomerID(1L);
    }

    @Test
    public void testSaveAndDeleteScreening() {
        Screening screening = new Screening();
        screening.setCustomer(new Customer());
        
        when(screeningRepository.save(screening)).thenReturn(screening);
        Screening saved = screeningRepository.save(screening);
        assertNotNull(saved);
        verify(screeningRepository, times(1)).save(screening);

        doNothing().when(screeningRepository).deleteById(anyLong());
        screeningRepository.deleteById(1L);
        verify(screeningRepository, times(1)).deleteById(1L);
    }
}
