/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.example.bioskopserver.repository;

import com.example.bioskopserver.model.Screening;
import com.example.bioskopserver.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author MNG
 */
public class TicketRepositoryTest {
    
    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByScreening() {
        Screening screening = new Screening();
        Ticket ticket1 = new Ticket();
        ticket1.setScreening(screening);
        Ticket ticket2 = new Ticket();
        ticket2.setScreening(screening);

        when(ticketRepository.findByScreening(screening))
                .thenReturn(Arrays.asList(ticket1, ticket2));

        List<Ticket> tickets = ticketRepository.findByScreening(screening);

        assertNotNull(tickets);
        assertEquals(2, tickets.size());
        assertEquals(screening, tickets.get(0).getScreening());
        assertEquals(screening, tickets.get(1).getScreening());
        verify(ticketRepository, times(1)).findByScreening(screening);
    }

    @Test
    public void testSaveAndDeleteTicket() {
        Ticket ticket = new Ticket();
        ticket.setScreening(new Screening());

        when(ticketRepository.save(ticket)).thenReturn(ticket);
        Ticket saved = ticketRepository.save(ticket);
        assertNotNull(saved);
        verify(ticketRepository, times(1)).save(ticket);

        doNothing().when(ticketRepository).deleteById(anyLong());
        ticketRepository.deleteById(1L);
        verify(ticketRepository, times(1)).deleteById(1L);
    }
}
