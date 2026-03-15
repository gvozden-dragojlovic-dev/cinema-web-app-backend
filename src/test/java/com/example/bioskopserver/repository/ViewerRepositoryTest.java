/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Viewer;
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
public class ViewerRepositoryTest {
    
    @Mock
    private ViewerRepository viewerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveViewer() {
        Viewer viewer = new Viewer();
        viewer.setFirstName("John");
        viewer.setLastName("Doe");

        when(viewerRepository.save(viewer)).thenReturn(viewer);

        Viewer saved = viewerRepository.save(viewer);

        assertNotNull(saved);
        assertEquals("John", saved.getFirstName());
        assertEquals("Doe", saved.getLastName());
        verify(viewerRepository, times(1)).save(viewer);
    }

    @Test
    public void testFindById() {
        Viewer viewer = new Viewer();
        viewer.setId(1L);
        viewer.setFirstName("Jane");
        viewer.setLastName("Doe");

        when(viewerRepository.findById(1L)).thenReturn(Optional.of(viewer));

        Optional<Viewer> found = viewerRepository.findById(1L);

        assertTrue(found.isPresent());
        assertEquals("Jane", found.get().getFirstName());
        assertEquals("Doe", found.get().getLastName());
        verify(viewerRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        Viewer v1 = new Viewer();
        v1.setFirstName("Viewer 1");
        Viewer v2 = new Viewer();
        v2.setLastName("Viewer 2");

        when(viewerRepository.findAll()).thenReturn(Arrays.asList(v1, v2));

        List<Viewer> viewers = viewerRepository.findAll();

        assertEquals(2, viewers.size());
        verify(viewerRepository, times(1)).findAll();
    }

    @Test
    public void testDelete() {
        doNothing().when(viewerRepository).deleteById(anyLong());

        viewerRepository.deleteById(1L);

        verify(viewerRepository, times(1)).deleteById(1L);
    }
}
