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
import java.util.Date;

@Entity
@Table(name = "maintenance_requests")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @Column(name = "apartment_id")
    private Long apartment_id;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private User landlord;
  
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @Column(name = "request_date")
    private Date requestDate;

    @Column(name = "description")
    private String description;
 
    @Column(name = "resolved")
    private boolean resolved;
    
    @Transient
    private String apartmentDescription;
    
    

    public Maintenance() {
    }

    public String getApartmentDescription() {
        return apartmentDescription;
    }

    public void setApartmentDescription(String apartmentDescription) {
        this.apartmentDescription = apartmentDescription;
    }
    
    
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Long getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(Long apartment_id) {
        this.apartment_id = apartment_id;
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

}
