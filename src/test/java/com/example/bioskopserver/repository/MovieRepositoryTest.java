/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Movie;
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
public class MovieRepositoryTest {
    
     @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMovie() {
        Movie movie = new Movie();
        movie.setTitle("Inception");

        when(movieRepository.save(movie)).thenReturn(movie);

        Movie savedMovie = movieRepository.save(movie);

        assertNotNull(savedMovie);
        assertEquals("Inception", savedMovie.getTitle());
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    public void testFindById() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Interstellar");

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        Optional<Movie> foundMovie = movieRepository.findById(1L);
        assertTrue(foundMovie.isPresent());
        assertEquals("Interstellar", foundMovie.get().getTitle());
        verify(movieRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        Movie movie1 = new Movie();
        movie1.setTitle("Movie 1");
        Movie movie2 = new Movie();
        movie2.setTitle("Movie 2");

        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1, movie2));

        List<Movie> movies = movieRepository.findAll();
        assertEquals(2, movies.size());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void testDelete() {
        Movie movie = new Movie();
        movie.setId(1L);

        doNothing().when(movieRepository).deleteById(1L);

        movieRepository.deleteById(1L);

        verify(movieRepository, times(1)).deleteById(1L);
    }
    
}
