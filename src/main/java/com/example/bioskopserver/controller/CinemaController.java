package com.example.bioskopserver.controller;

import com.example.bioskopserver.DTOs.HistoryPurchaseDTO;
import com.example.bioskopserver.DTOs.BuyTicketsRequest;
import com.example.bioskopserver.model.Hall;
import com.example.bioskopserver.model.Movie;
import com.example.bioskopserver.model.Screening;
import com.example.bioskopserver.model.Viewer;
import com.example.bioskopserver.service.CinemaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cinema")
@CrossOrigin(origins = "http://localhost:5173")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;


    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return cinemaService.getMovies();
    }


    @GetMapping("/halls")
    public List<Hall> getHalls() {
        return cinemaService.getHalls();
    }


    @GetMapping("/viewers")
    public List<Viewer> getViewers() {
        return cinemaService.getViewers();
    }


    @GetMapping("/screenings")
    public List<Screening> getScreenings(Long movieId, Long hallId, Long adminId) {
        
        return cinemaService.getScreenings(movieId, hallId, adminId);
    }
    
    @GetMapping("/occupied-seats/{screeningId}")
    public List<String> getOccupiedSeats(@PathVariable Long screeningId) {
        
        return cinemaService.getOccupiedSeats(screeningId);
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyTickets(@RequestBody BuyTicketsRequest request) {

        cinemaService.buyTickets(request);

        return ResponseEntity.ok("Tickets successfully purchased");
    }
    
    @GetMapping("/history/{adminId}")
    public List<HistoryPurchaseDTO> getHistory(@PathVariable Long adminId) {

        return cinemaService.getHistory(adminId);
    }
}
