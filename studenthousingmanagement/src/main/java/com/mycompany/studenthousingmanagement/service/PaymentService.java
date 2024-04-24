/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.service;

import com.mycompany.studenthousingmanagement.DAO.PaymentDAO;
import com.mycompany.studenthousingmanagement.Model.Payment;
import com.mycompany.studenthousingmanagement.Model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;



/**
 *
 * @author manal
 */
@Service
public class PaymentService {
    
    @Autowired
    PaymentDAO paymentDao;
    
     public List<Payment> getPaymentDetails(User student) {
         return paymentDao.getPaymentDetails(student);
     }
     
     public void savePayment(Payment payment){
         paymentDao.savePayment(payment);
     }
     
    
}
