/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.service;

import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.example.bioskopserver.DTOs.BuyTicketsRequest;
import com.example.bioskopserver.DTOs.HistoryPurchaseDTO;
import com.example.bioskopserver.DTOs.TicketRequest;
import com.example.bioskopserver.model.Administrator;
import com.example.bioskopserver.model.Hall;
import com.example.bioskopserver.model.Movie;
import com.example.bioskopserver.model.Screening;
import com.example.bioskopserver.model.Ticket;
import com.example.bioskopserver.model.Viewer;
import com.example.bioskopserver.repository.AdministratorRepository;
import com.example.bioskopserver.repository.HallRepository;
import com.example.bioskopserver.repository.MovieRepository;
import com.example.bioskopserver.repository.ScreeningRepository;
import com.example.bioskopserver.repository.TicketRepository;
import com.example.bioskopserver.repository.ViewerRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MNG
 */
@ExtendWith(MockitoExtension.class)
public class CinemaServiceTest {
    
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private HallRepository hallRepository;

    @Mock
    private ViewerRepository viewerRepository;

    @Mock
    private ScreeningRepository screeningRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private AdministratorRepository administratorRepository;

    @InjectMocks
    private CinemaService cinemaService;
    
    public CinemaServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMovies method, of class CinemaService.
     */
    @Test
    public void testGetMovies() {
        Movie movie = new Movie();
        movie.setTitle("Matrix");

        List<Movie> movies = List.of(movie);

        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = cinemaService.getMovies();

        assertEquals(1, result.size());
        assertEquals("Matrix", result.get(0).getTitle());
    }

    /**
     * Test of getHalls method, of class CinemaService.
     */
    @Test
    public void testGetHalls() {

        Hall hall = new Hall();
        hall.setName("Sala 1");

        List<Hall> halls = List.of(hall);

        when(hallRepository.findAll()).thenReturn(halls);

        List<Hall> result = cinemaService.getHalls();

        assertEquals(1, result.size());
        assertEquals("Sala 1", result.get(0).getName());
    }

    /**
     * Test of getViewers method, of class CinemaService.
     */
    @Test
    public void testGetViewers() {

        Viewer viewer = new Viewer();
        viewer.setFirstName("Marko");

        List<Viewer> viewers = List.of(viewer);

        when(viewerRepository.findAll()).thenReturn(viewers);

        List<Viewer> result = cinemaService.getViewers();

        assertEquals(1, result.size());
        assertEquals("Marko", result.get(0).getFirstName());
    }

    /**
     * Test of buyTickets method, of class CinemaService.
     */
    @Test
    public void testBuyTickets() {

        BuyTicketsRequest request = new BuyTicketsRequest();

        request.setMovieId(1L);
        request.setHallId(1L);
        request.setAdminId(1L);
        request.setPrice(new BigDecimal(589.99));

        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setViewerId(1L);
        ticketRequest.setSeat("A1");

        request.setTickets(List.of(ticketRequest));

        Movie movie = new Movie();
        Hall hall = new Hall();
        Administrator admin = new Administrator();
        Viewer viewer = new Viewer();

        when(movieRepository.findById(1L)).thenReturn(java.util.Optional.of(movie));
        when(hallRepository.findById(1L)).thenReturn(java.util.Optional.of(hall));
        when(administratorRepository.findById(1L)).thenReturn(java.util.Optional.of(admin));
        when(viewerRepository.findById(1L)).thenReturn(java.util.Optional.of(viewer));

        cinemaService.buyTickets(request);

        verify(screeningRepository, times(1)).save(any(Screening.class));
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    /**
     * Test of getHistory method, of class CinemaService.
     */
    @Test
    public void testGetHistory() {

        Long adminId = 1L;

        Movie movie = new Movie();
        movie.setTitle("Matrix");

        Hall hall = new Hall();
        hall.setName("Sala 1");

        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setHall(hall);

        Viewer viewer = new Viewer();
        viewer.setFirstName("Marko");
        viewer.setLastName("Markovic");
        viewer.setEmail("marko@test.com");

        Ticket ticket = new Ticket();
        ticket.setViewer(viewer);
        ticket.setSeat("A1");
        ticket.setScreening(screening);

        List<Screening> screenings = List.of(screening);
        List<Ticket> tickets = List.of(ticket);

        when(screeningRepository.findByAdministrator_AdministratorID(adminId))
                .thenReturn(screenings);

        when(ticketRepository.findByScreening(screening))
                .thenReturn(tickets);

        List<HistoryPurchaseDTO> result = cinemaService.getHistory(adminId);

        assertEquals(1, result.size());
        assertEquals("Matrix", result.get(0).getMovieTitle());
    }
}
