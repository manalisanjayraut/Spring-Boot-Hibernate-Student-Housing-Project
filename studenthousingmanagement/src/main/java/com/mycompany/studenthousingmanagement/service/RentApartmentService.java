/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.service;

import com.mycompany.studenthousingmanagement.DAO.RentApartmentDAO;
import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.Model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author manal
 */
@Service
public class RentApartmentService {
    
    @Autowired
    RentApartmentDAO rentApartmentDAO;
    
    public void rentApartmentRequest(ApartmentRent rentRequest) {
        
        rentApartmentDAO.rentApartmentRequest(rentRequest);
    }
    
     public List<ApartmentRent> getRentApartmentRequest(User landloard){
         
         return rentApartmentDAO.getRentApartmentRequest(landloard);
     }
     
      public ApartmentRent getRentApartmentRequestByStudent(User student){
         
         return rentApartmentDAO.getRentApartmentRequestByStudent(student);
     }
     
     
      public void rentApartmentResponseProcess(ApartmentRent rentRequest) {
          
           rentApartmentDAO.rentApartmentResponseProcess(rentRequest);
      }
      
      public ApartmentRent getApartmentRentRequest(Long id){
          return rentApartmentDAO.getApartmentRentRequest(id);
      }

    public void updateApartmentRentStatus(ApartmentRent apartmentRent) {
      rentApartmentDAO.rentApartmentResponseProcess(apartmentRent);
    }

    public void deleteRentRequest(ApartmentRent apartmentRent) {
       rentApartmentDAO.deleteRentRequest(apartmentRent);
    }
    
}
