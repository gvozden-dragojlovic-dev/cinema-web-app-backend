package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Screening;
import com.example.bioskopserver.model.Ticket;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    List<Ticket> findByScreening(Screening screening);
    
    List<Ticket> findByScreeningAndViewer_IdIn(Screening screening, List<Long> viewerIds);
    
    List<Ticket> findByScreening_Id(Long screeningId);
}
