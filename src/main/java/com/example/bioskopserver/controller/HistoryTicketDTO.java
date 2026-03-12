package com.example.bioskopserver.controller;

public class HistoryTicketDTO {

    private String viewerFirstName;
    private String viewerLastName;
    private String viewerEmail;
    private String seat;

    public HistoryTicketDTO(String viewerFirstName, String viewerLastName, String viewerEmail, String seat) {
        this.viewerFirstName = viewerFirstName;
        this.viewerLastName = viewerLastName;
        this.viewerEmail = viewerEmail;
        this.seat = seat;
    }

    public String getViewerFirstName() {
        return viewerFirstName;
    }
    
    public void setViewerFirstName(String viewerFirstName) {
        this.viewerFirstName = viewerFirstName;
    }

    public String getViewerLastName() {
        return viewerLastName;
    }

    public void setViewerLastName(String viewerLastName) {
        this.viewerLastName = viewerLastName;
    }
    
    public String getViewerEmail() {
        return viewerEmail;
    }
    
    public void setViewerEmail(String viewerEmail) {
        this.viewerEmail = viewerEmail;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}