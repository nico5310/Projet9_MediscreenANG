package com.nico5310.patientMicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "patient")
public class Patient {

    /**
     * ID patient auto-generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    /**
     * Firstname patient
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Lastname patient
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Birthdate patient
     */
    @DateTimeFormat ( pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dob;

    /**
     * Sex / genre patient
     */
    @Column(name = "genre")
    private char genre;

    /**
     * Address patient
     */
    @Column(name = "address")
    private String address;

    /**
     * phone number patient
     */
    @Column(name = "phone")
    private String phone;


    /**
     * patient public constructor
     * @param id of patient
     * @param firstName of patient
     * @param lastName of patient
     * @param dob of patient
     * @param genre of patient
     * @param address of patient
     * @param phone of patient
     */
    public Patient(Integer id,String firstName, String lastName, LocalDate dob, char genre, String address, String phone) {
        this.id        = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.dob       = dob;
        this.genre     = genre;
        this.address   = address;
        this.phone     = phone;
    }

    /**
     * patient public empty constructor
     */
    public Patient() {

    }


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

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

    public LocalDate getDob() {

        return dob;
    }

    public void setDob(LocalDate dob) {

        this.dob = dob;
    }

    public char getGenre() {

        return genre;
    }

    public void setGenre(char sex) {

        this.genre = sex;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }

    @Override
    public String toString() {

        return "patient{" + "id=" + id + ", family='" + firstName + '\'' + ", given='" + lastName + '\'' + ", dob=" + dob + ", sex='" + genre + '\'' + ", address='" + address + '\'' + ", phone='" + phone + '\'' + '}';
    }
}
