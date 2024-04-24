/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.studenthousingmanagement.DAO;

import com.mycompany.studenthousingmanagement.Model.Payment;
import com.mycompany.studenthousingmanagement.Model.User;
import java.util.List;

/**
 *
 * @author manal
 */
public interface PaymentDAOInterface {
    
    List<Payment> getPaymentDetails(User student) ;
    
    void savePayment(Payment payment);
    
}
