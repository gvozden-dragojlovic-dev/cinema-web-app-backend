package com.example.bioskopserver.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class HistoryPurchaseDTO {

    private String movieTitle;
    private String hallName;
    private LocalDateTime dateTime;
    private String projectionType;
    private BigDecimal price;
    private LocalDateTime purchaseDate;

    private List<HistoryTicketDTO> tickets;

    public HistoryPurchaseDTO(String movieTitle, String hallName, LocalDateTime dateTime,
                              String projectionType, BigDecimal price, LocalDateTime purchaseDate,
                              List<HistoryTicketDTO> tickets) {

        this.movieTitle = movieTitle;
        this.hallName = hallName;
        this.dateTime = dateTime;
        this.projectionType = projectionType;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.tickets = tickets;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getProjectionType() {
        return projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<HistoryTicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<HistoryTicketDTO> tickets) {
        this.tickets = tickets;
    }
}