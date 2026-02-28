package com.example.bioskopserver.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "kupac")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "KupacID")
  private Long kupacID;

  @Column(name = "Username", unique = true)
  private String username;

  @Column(name = "Password")
  private String password;

  @Column(name = "FirstName")
  private String firstName;

  @Column(name = "LastName")
  private String lastName;

  @Column(name = "DateOfBirth")
  private LocalDate dateOfBirth;

  @Column(name = "Gender")
  private String gender;

  @Column(name = "MobileNumber")
  private String mobileNumber;

  public Long getKupacID() {
    return kupacID;
  }
  
  public void setKupacID(Long kupacID) {
    this.kupacID = kupacID;
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

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }
  
  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }
  
  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }
  
  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }
}