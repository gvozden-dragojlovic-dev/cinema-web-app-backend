package com.example.bioskopserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sala")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SalaID")
    private Long id;

    @Column(name = "Naziv")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
