/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.controller;

import com.example.bioskopserver.DTOs.BuyTicketsRequest;
import com.example.bioskopserver.DTOs.HistoryPurchaseDTO;
import com.example.bioskopserver.model.Hall;
import com.example.bioskopserver.model.Movie;
import com.example.bioskopserver.model.Viewer;
import com.example.bioskopserver.service.CinemaService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MNG
 */
public class CinemaControllerTest {

    @Mock
    private CinemaService cinemaService;

    @InjectMocks
    private CinemaController cinemaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMovies() {
        Movie movie1 = new Movie();
        movie1.setTitle("Film 1");
        when(cinemaService.getMovies()).thenReturn(Arrays.asList(movie1));

        List<Movie> result = cinemaController.getMovies();

        assertEquals(1, result.size());
        assertEquals("Film 1", result.get(0).getTitle());
    }

    @Test
    void testGetHalls() {
        Hall hall1 = new Hall();
        hall1.setName("Sala 1");
        when(cinemaService.getHalls()).thenReturn(Arrays.asList(hall1));

        List<Hall> result = cinemaController.getHalls();

        assertEquals(1, result.size());
        assertEquals("Sala 1", result.get(0).getName());
    }

    @Test
    void testGetViewers() {
        Viewer viewer = new Viewer();
        viewer.setFirstName("Pera");
        viewer.setLastName("Peric");
        when(cinemaService.getViewers()).thenReturn(Arrays.asList(viewer));

        List<Viewer> viewers = cinemaController.getViewers();

        assertEquals(1, viewers.size());
        assertEquals("Pera", viewers.get(0).getFirstName());
    }

    @Test
    void testBuyTickets() {
        BuyTicketsRequest request = new BuyTicketsRequest();
        request.setMovieId(1L);
        request.setHallId(1L);
        request.setProjectionType("2D");
        request.setPrice(BigDecimal.valueOf(500));
        request.setDateTime(LocalDateTime.now());
        request.setAdminId(1L);
        request.setTickets(Collections.emptyList());

        doNothing().when(cinemaService).buyTickets(request);

        ResponseEntity<String> response = cinemaController.buyTickets(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tickets successfully purchased", response.getBody());
        verify(cinemaService, times(1)).buyTickets(request);
    }

    @Test
    void testGetHistory() {
        HistoryPurchaseDTO history = new HistoryPurchaseDTO(
                "Avengers",
                "Sala 1",
                LocalDateTime.now(),
                "2D",
                BigDecimal.valueOf(500),
                LocalDateTime.now(),
                Collections.emptyList()
        );
        when(cinemaService.getHistory(1L)).thenReturn(Arrays.asList(history));

        List<HistoryPurchaseDTO> result = cinemaController.getHistory(1L);

        assertEquals(1, result.size());
        assertEquals("Avengers", result.get(0).getMovieTitle());
    }
}
