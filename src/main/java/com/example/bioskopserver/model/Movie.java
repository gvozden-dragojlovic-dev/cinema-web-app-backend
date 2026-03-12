package com.example.bioskopserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "film")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FilmID")
    private Long id;

    @Column(name = "Naziv")
    private String title;

    @Column(name = "Zanr")
    private String genre;

    @Column(name = "Opis")
    private String description;

    @Column(name = "Reziser")
    private String director;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
