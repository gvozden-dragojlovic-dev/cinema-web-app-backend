package com.example.bioskopserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gledalac")
public class Viewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GledalacID")
    private Long id;

    @Column(name = "Ime")
    private String firstName;

    @Column(name = "Prezime")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Telefon")
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
