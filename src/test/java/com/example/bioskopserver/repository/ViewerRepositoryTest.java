package com.example.bioskopserver.repository;

import com.example.bioskopserver.BioskopServerApplication;
import com.example.bioskopserver.model.Viewer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BioskopServerApplication.class)
public class ViewerRepositoryTest {

    @Autowired
    private ViewerRepository viewerRepository;

    private Viewer viewer;

    @BeforeEach
    void setUp() {
        viewer = new Viewer();
        viewer.setFirstName("John");
        viewer.setLastName("Doe");

        viewer = viewerRepository.save(viewer);
    }

    @AfterEach
    void tearDown() {
        viewerRepository.deleteAll();
    }

    @Test
    void testSaveViewer() {
        assertNotNull(viewer.getId());
        assertEquals("John", viewer.getFirstName());
        assertEquals("Doe", viewer.getLastName());
    }

    @Test
    void testFindById() {
        Optional<Viewer> found = viewerRepository.findById(viewer.getId());

        assertTrue(found.isPresent());
        assertEquals("John", found.get().getFirstName());
        assertEquals("Doe", found.get().getLastName());
    }

    @Test
    void testFindAll() {
        List<Viewer> viewers = viewerRepository.findAll();

        assertFalse(viewers.isEmpty());
        assertTrue(viewers.stream().anyMatch(v -> v.getFirstName().equals("John")));
    }

    @Test
    void testDeleteViewer() {
        Viewer temp = new Viewer();
        temp.setFirstName("Temp");
        temp.setLastName("User");

        temp = viewerRepository.save(temp);
        Long id = temp.getId();

        viewerRepository.deleteById(id);

        Optional<Viewer> deleted = viewerRepository.findById(id);
        assertFalse(deleted.isPresent());
    }
}