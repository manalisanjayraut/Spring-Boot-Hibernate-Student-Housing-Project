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
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "apartment_id")
    private Long apartment;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private User landlord;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @Column(name = "amount")
    private Double amount;
    
    @Column(name = "paymentMonth")
    private String paymentMonth;
    

   
    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "status")
    private String status;

    public Long getApartment() {
        return apartment;
    }

    public void setApartment(Long apartment) {
        this.apartment = apartment;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth;
    }
    
    

    // Constructors, getters, and setters
    public Payment() {
    }

    public Payment(long apartment, User landlord, User student, Double amount, Date paymentDate, String status) {
        this.apartment = apartment;
        this.landlord = landlord;
        this.student = student;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
