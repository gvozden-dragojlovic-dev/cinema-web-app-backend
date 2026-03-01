package com.example.bioskopserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrator")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdministratorID")
    private Long customerID;

    @Column(name = "Ime")
    private String firstName;

    @Column(name = "Prezime")
    private String lastName;

    @Column(name = "Username", unique = true)
    private String username;

    @Column(name = "Password")
    private String password;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}