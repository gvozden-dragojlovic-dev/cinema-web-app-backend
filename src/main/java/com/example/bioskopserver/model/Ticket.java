package com.example.bioskopserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "karta")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KartaID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TerminID")
    private Screening screening;

    @ManyToOne
    @JoinColumn(name = "GledalacID")
    private Viewer viewer;

    @Column(name = "Sediste")
    private String seat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
