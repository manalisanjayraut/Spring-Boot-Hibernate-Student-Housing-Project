/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.Model;

/**
 *
 * @author manal
 */
import jakarta.persistence.*;

@Entity
public class ApartmentRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long apartmentId;
    @Column
    private double rentAmount;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private User landlord;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
    
    @Transient
    private String apartmentDescription;
    

    // Constructors
    public ApartmentRent() {
    }
 
    public ApartmentRent(Long apartmentId, double rentAmount, User landlord, User student, String status) {
        this.apartmentId = apartmentId;
        this.rentAmount = rentAmount;
        this.landlord = landlord;
        this.student = student;
        this.status = status;
    }

    public String getApartmentDescription() {
        return apartmentDescription;
    }

    public void setApartmentDescription(String apartmentDescription) {
        this.apartmentDescription = apartmentDescription;
    }

    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }
}
