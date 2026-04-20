package com.example.bioskopserver.service;

import com.example.bioskopserver.DTOs.BuyTicketsRequest;
import com.example.bioskopserver.DTOs.TicketRequest;
import com.example.bioskopserver.DTOs.HistoryPurchaseDTO;
import com.example.bioskopserver.DTOs.HistoryTicketDTO;
import com.example.bioskopserver.model.Hall;
import com.example.bioskopserver.model.Movie;
import com.example.bioskopserver.model.Screening;
import com.example.bioskopserver.model.Ticket;
import com.example.bioskopserver.model.Viewer;
import com.example.bioskopserver.repository.HallRepository;
import com.example.bioskopserver.repository.MovieRepository;
import com.example.bioskopserver.repository.ScreeningRepository;
import com.example.bioskopserver.repository.TicketRepository;
import com.example.bioskopserver.repository.ViewerRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private ViewerRepository viewerRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private TicketRepository ticketRepository;
    
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public List<Hall> getHalls() {
        return hallRepository.findAll();
    }

    public List<Viewer> getViewers() {
        return viewerRepository.findAll();
    }

    public List<Screening> getScreenings(Long movieId, Long hallId, Long adminId) {
        return screeningRepository.
        findByMovie_IdAndHall_IdAndCustomer_CustomerID(movieId, hallId, adminId);
    }
    
    public List<String> getOccupiedSeats(Long screeningId) {

        List<Ticket> tickets = ticketRepository.findByScreening_Id(screeningId);

        return tickets.stream()
                .map(Ticket::getSeat)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public void buyTickets(BuyTicketsRequest request) {

        Screening screening = screeningRepository
                .findById(request.getScreeningId())
                .orElseThrow();

        for (TicketRequest t : request.getTickets()) {

            Viewer viewer = viewerRepository
                    .findById(t.getViewerId())
                    .orElseThrow();

            Ticket ticket = new Ticket();
            ticket.setScreening(screening);
            ticket.setViewer(viewer);
            ticket.setSeat(t.getSeat());

            ticketRepository.save(ticket);
        }
    }
    
    public List<HistoryPurchaseDTO> getHistory(Long adminId) {

        List<Screening> screenings =
                screeningRepository.findByCustomer_CustomerID(adminId);

        return screenings.stream().map(screening -> {

            List<Ticket> tickets = ticketRepository.findByScreening(screening);

            List<Ticket> filtered = tickets.stream()
                    .filter(t -> t.getViewer() != null)
                    .collect(Collectors.toList());

            List<HistoryTicketDTO> ticketDTOs = filtered.stream()
                    .map(t -> new HistoryTicketDTO(
                            t.getViewer().getFirstName(),
                            t.getViewer().getLastName(),
                            t.getViewer().getEmail(),
                            t.getSeat()
                    ))
                    .collect(Collectors.toList());

            return new HistoryPurchaseDTO(
                    screening.getMovie().getTitle(),
                    screening.getHall().getName(),
                    screening.getDateTime(),
                    screening.getProjectionType(),
                    screening.getTicketPrice(),
                    screening.getDateTime(),
                    ticketDTOs
            );

        }).collect(Collectors.toList());
    }
}
