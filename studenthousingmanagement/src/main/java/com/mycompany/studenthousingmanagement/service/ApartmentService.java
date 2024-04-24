/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.service;

import com.mycompany.studenthousingmanagement.DAO.ApartmentDAO;
import com.mycompany.studenthousingmanagement.DAO.ApartmentDAOInterface;
import com.mycompany.studenthousingmanagement.Model.Apartment;
import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.Model.SearchCriteria;
import com.mycompany.studenthousingmanagement.Model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author manal
 */
@Service
public class ApartmentService {
    
   @Autowired
   ApartmentDAO dao;
    
    public void saveApartment(Apartment apartment){
        dao.saveApartment(apartment);
    }

    public List<Apartment> getAllApartments(int pagenum) {
      
      List<Apartment> apartments =  dao.getAllApartments(pagenum);

      return apartments;
    }
    
     public Apartment findApartmentById (Long apartment_id) {
      
      return dao.findApartmentById(apartment_id);
    }

    public List<Apartment> searchApartments(SearchCriteria searchCriteria) {
        System.out.print("serach input:"+ searchCriteria.getSearchInput() + ": " + searchCriteria.getFilterOptions() );
       return dao.searchApartments(searchCriteria.getSearchInput(), searchCriteria.getFilterOptions());
    }

   
    public void updatePost(int id, String apartmentName, String description, double totalCost) {
             dao.updatePost(id, apartmentName, description, totalCost);
    }
    
    public List<Apartment> getApartmentByLandlord(User landlord){
        
         return dao.getApartmentByLandlord(landlord);
    }

    public void deletePost(int id) {
             dao.deletePost(id);
    }

    
}
