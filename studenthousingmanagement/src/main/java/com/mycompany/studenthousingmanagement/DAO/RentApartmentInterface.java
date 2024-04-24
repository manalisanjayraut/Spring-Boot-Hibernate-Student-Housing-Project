/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.studenthousingmanagement.DAO;

import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.Model.User;
import java.util.List;

/**
 *
 * @author manal
 */
public interface RentApartmentInterface {

    public void rentApartmentRequest(ApartmentRent rentRequest);

    public List<ApartmentRent> getRentApartmentRequest(User landloard);

    public void rentApartmentResponseProcess(ApartmentRent rentRequest);

    public ApartmentRent getApartmentRentRequest(Long id);
    
    public ApartmentRent getRentApartmentRequestByStudent(User studnet);
    
    public void deleteRentRequest(ApartmentRent apartmentRent);
}
