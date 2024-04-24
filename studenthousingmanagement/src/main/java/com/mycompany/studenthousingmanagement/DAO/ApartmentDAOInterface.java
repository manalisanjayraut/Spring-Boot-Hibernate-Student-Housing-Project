package com.mycompany.studenthousingmanagement.DAO;

import com.mycompany.studenthousingmanagement.Model.Apartment;
import com.mycompany.studenthousingmanagement.Model.SearchCriteria;
import com.mycompany.studenthousingmanagement.Model.User;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author manal
 */
public interface ApartmentDAOInterface {

    public void saveApartment(Apartment apartment);

    public List<Apartment> getAllApartments(int pageNum);

    public Apartment findApartmentById(Long apartmentID);

    public List<Apartment> searchApartments(String search, String filter);
    
    public void updatePost(int id, String apartmentName, String description, double totalCost) ;
    
    public List<Apartment> getApartmentByLandlord(User landlord);
    
    public void deletePost(int id);

}
