package com.example.bioskopserver.DTOs;

public class TicketRequest {

    private Long viewerId;
    private String seat;

    public Long getViewerId() {
        return viewerId;
    }

    public void setViewerId(Long viewerId) {
        this.viewerId = viewerId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
