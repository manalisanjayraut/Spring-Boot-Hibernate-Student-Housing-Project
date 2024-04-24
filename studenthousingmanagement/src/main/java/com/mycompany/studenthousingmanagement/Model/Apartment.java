/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.Model;

/**
 *
 * @author manal
 */
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "apartments")
public class Apartment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long apartmentId;

    @Column(name = "apartment_name")
    private String apartmentName;

    @Column(name = "available_quantity")
    private String availableQuantity;

    @Column(name = "description", length =400)
    private String description;

    @Column(name = "cost")
    private double cost;

    @Column(name = "total_cost")
    private double totalCost;

    @Transient
    private MultipartFile file;

    @Column(name = "file_name") 
    private String fileName; 

    @Column(name = "location")
    private String location;
    
    @Column(name = "roomType")
    private String roomType;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @Basic
    @JoinColumn(name = "landlord_id")
    private User landlord;
    
    @Column(name ="staus")
    private String active;
    
    
    
    public Apartment() {
        
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
     public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

   

    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Apartment(Long apartmentId, String apartmentName, String availableQuantity, String description, double cost, double totalCost, MultipartFile file, String fileName, User landlord) {
        this.apartmentId = apartmentId;
        this.apartmentName = apartmentName;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.cost = cost;
        this.totalCost = totalCost;
        this.file = file;
        this.fileName = fileName;
        this.landlord = landlord;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
    
    

    public Apartment(Long apartmentId, String apartmentName, String availableQuantity, String description, double cost, double totalCost, MultipartFile file, String fileName, String location, String roomType, User landlord) {
        this.apartmentId = apartmentId;
        this.apartmentName = apartmentName;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.cost = cost;
        this.totalCost = totalCost;
        this.file = file;
        this.fileName = fileName;
        this.location = location;
        this.roomType = roomType;
        this.landlord = landlord;
    }
  }

