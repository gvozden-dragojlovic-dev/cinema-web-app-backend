package com.example.bioskopserver.repository;

import com.example.bioskopserver.BioskopServerApplication;
import com.example.bioskopserver.model.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BioskopServerApplication.class)
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    private CustomerRepository administratorRepository;

    private Movie movie;
    private Hall hall;
    private Screening screening;
    private Viewer viewer1;
    private Viewer viewer2;
    private Customer admin;
    private Ticket ticket1;
    private Ticket ticket2;

    @BeforeEach
    void setUp() {

        movie = new Movie();
        movie.setTitle("Inception");
        movie = movieRepository.save(movie);

        hall = new Hall();
        hall.setName("Main Hall");
        hall = hallRepository.save(hall);

        admin = new Customer();
        admin.setFirstName("Admin");
        admin.setLastName("Test");
        admin.setUsername("admin");
        admin.setPassword("123");
        admin = administratorRepository.save(admin);

        screening = new Screening();
        screening.setMovie(movie);
        screening.setHall(hall);
        screening.setCustomer(admin);
        screening.setTicketPrice(new BigDecimal("10.59"));
        screening.setDateTime(LocalDateTime.of(2026, 4, 19, 20, 0));
        screening = screeningRepository.save(screening);

        viewer1 = new Viewer();
        viewer1.setFirstName("Pera");
        viewer1.setLastName("Peric");
        viewer1 = viewerRepository.save(viewer1);

        viewer2 = new Viewer();
        viewer2.setFirstName("Mika");
        viewer2.setLastName("Mikic");
        viewer2 = viewerRepository.save(viewer2);

        ticket1 = new Ticket();
        ticket1.setScreening(screening);
        ticket1.setViewer(viewer1);
        ticket1.setSeat("A1");
        ticket1 = ticketRepository.save(ticket1);

        ticket2 = new Ticket();
        ticket2.setScreening(screening);
        ticket2.setViewer(viewer2);
        ticket2.setSeat("A2");
        ticket2 = ticketRepository.save(ticket2);
    }

    @AfterEach
    void tearDown() {
        ticketRepository.deleteAll();
        viewerRepository.deleteAll();
        screeningRepository.deleteAll();
        movieRepository.deleteAll();
        hallRepository.deleteAll();
        administratorRepository.deleteAll();
    }

    @Test
    void testFindByScreening() {

        List<Ticket> result = ticketRepository.findByScreening(screening);

        assertEquals(2, result.size());
        assertEquals(screening.getId(), result.get(0).getScreening().getId());
    }

    @Test
    void testFindByScreeningId() {

        List<Ticket> result =
                ticketRepository.findByScreening_Id(screening.getId());

        assertEquals(2, result.size());
    }

    @Test
    void testFindByScreeningAndViewerIds() {

        List<Long> viewerIds = List.of(
                viewer1.getId(),
                viewer2.getId()
        );

        List<Ticket> result =
                ticketRepository.findByScreeningAndViewer_IdIn(
                        screening,
                        viewerIds
                );

        assertEquals(2, result.size());
    }
}