package com.example.bioskopserver.repository;

import com.example.bioskopserver.BioskopServerApplication;
import com.example.bioskopserver.model.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BioskopServerApplication.class)
public class ScreeningRepositoryTest {

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Movie movie;
    private Hall hall;
    private Customer customer;
    private Screening screening;

    @BeforeEach
    void setUp() {

        movie = new Movie();
        movie.setTitle("Inception");
        movie = movieRepository.save(movie);

        hall = new Hall();
        hall.setName("Main Hall");
        hall = hallRepository.save(hall);

        customer = new Customer();
        customer.setUsername("admin");
        customer.setPassword("pass");
        customer = customerRepository.save(customer);

        screening = new Screening();
        screening.setMovie(movie);
        screening.setHall(hall);
        screening.setCustomer(customer);

        screening.setDateTime(LocalDateTime.of(2026, 4, 19, 20, 0));
        screening.setTicketPrice(new BigDecimal("10.50"));

        screening = screeningRepository.save(screening);
    }

    @AfterEach
    void tearDown() {
        screeningRepository.deleteAll();
        customerRepository.deleteAll();
        movieRepository.deleteAll();
        hallRepository.deleteAll();
    }

    @Test
    void testFindByMovieHallAndCustomer() {

        List<Screening> result =
                screeningRepository.findByMovie_IdAndHall_IdAndCustomer_CustomerID(
                        movie.getId(),
                        hall.getId(),
                        customer.getCustomerID()
                );

        assertFalse(result.isEmpty());
        assertEquals(movie.getId(), result.get(0).getMovie().getId());
        assertEquals(hall.getId(), result.get(0).getHall().getId());
        assertEquals(customer.getCustomerID(),
                result.get(0).getCustomer().getCustomerID());
    }

    @Test
    void testFindByCustomerId() {

        List<Screening> result =
                screeningRepository.findByCustomer_CustomerID(
                        customer.getCustomerID()
                );

        assertFalse(result.isEmpty());
        assertEquals(customer.getCustomerID(),
                result.get(0).getCustomer().getCustomerID());
    }
}