/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.DAO;

import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.Model.Payment;
import com.mycompany.studenthousingmanagement.Model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author manal
 */
@Repository
public class PaymentDAO extends HibernateConfig implements PaymentDAOInterface {

    @Override
    public List<Payment> getPaymentDetails(User user) {
        beginTransaction();
          
        System.out.println("i am user :" + user.getUserId());
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Payment> criteriaQuery = criteriaBuilder.createQuery(Payment.class);
        Root<Payment> root = criteriaQuery.from(Payment.class);
      
        if (user.getRole() == User.Role.Student) {
           // System.out.println("*********com.mycompany.studenthousingmanagement.DAO.PaymentDAO.getPaymentDetails()");
            criteriaQuery.select(root)
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("student"), user)
                    ));
        } else {
         //   System.out.println("_____________com.mycompany.studenthousingmanagement.DAO.PaymentDAO.getPaymentDetails()");
            criteriaQuery.select(root)
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("landlord"), user)
                    ));
        }
        Query<Payment> query = getSession().createQuery(criteriaQuery);
        List<Payment> payments = query.list();

        commitTransaction();
        return payments;
    }

    @Override
    public void savePayment(Payment payment) {
        beginTransaction();
        getSession().persist(payment);
        commitTransaction();
    }

}
