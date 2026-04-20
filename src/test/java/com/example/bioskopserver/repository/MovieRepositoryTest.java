package com.example.bioskopserver.repository;

import com.example.bioskopserver.BioskopServerApplication;
import com.example.bioskopserver.model.Movie;
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
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setTitle("Inception");

        movie = movieRepository.save(movie);
    }

    @AfterEach
    void tearDown() {
        try {
            movieRepository.delete(movie);
        } catch (Exception ignored) {
        }
    }

    @Test
    void testSaveMovie() {
        assertNotNull(movie.getId(), "Movie ID should not be null after save");
        assertEquals("Inception", movie.getTitle());
    }

    @Test
    void testFindById() {
        Optional<Movie> found = movieRepository.findById(movie.getId());

        assertTrue(found.isPresent());
        assertEquals("Inception", found.get().getTitle());
    }

    @Test
    void testFindAll() {
        List<Movie> movies = movieRepository.findAll();

        assertFalse(movies.isEmpty());
        assertTrue(movies.stream()
                .anyMatch(m -> m.getTitle().equals("Inception")));
    }

    @Test
    void testDeleteMovie() {
        Movie temp = new Movie();
        temp.setTitle("Temp Movie");
        temp = movieRepository.save(temp);

        Long id = temp.getId();

        movieRepository.deleteById(id);

        Optional<Movie> deleted = movieRepository.findById(id);
        assertFalse(deleted.isPresent());
    }
}