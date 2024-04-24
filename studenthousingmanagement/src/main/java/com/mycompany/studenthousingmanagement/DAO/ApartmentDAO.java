/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.DAO;

import com.mycompany.studenthousingmanagement.Model.Apartment;
import com.mycompany.studenthousingmanagement.Model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author manal
 */
@Repository
public class ApartmentDAO extends HibernateConfig implements ApartmentDAOInterface {

    /*
    saveApartment method persist apartment data
    
    */
    @Override
    public void saveApartment(Apartment apartment) {

        beginTransaction();
        getSession().persist(apartment);
        commitTransaction();

    }

    public List<Apartment> getAllApartments(int pageNum) {

        int howmany = 4;
        beginTransaction();
        Query<Apartment> query = getSession().createQuery("from Apartment where active != :status", Apartment.class);
        query.setParameter("status", "Inactive");
        query.setFirstResult((pageNum - 1) * howmany);
        query.setMaxResults(howmany);
        List<Apartment> apartmemtList = query.getResultList();
        commitTransaction();
        return apartmemtList;
    }

    @Override
    public Apartment findApartmentById(Long apartmentID) {

        beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);
        Root<Apartment> root = criteriaQuery.from(Apartment.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("apartmentId"), apartmentID)
                ));
        Query<Apartment> query = getSession().createQuery(criteriaQuery);
        //  System.out.println("username " + A + query.uniqueResult());

        Apartment apartment = query.uniqueResult();
        commitTransaction();
        return apartment;

    }

    public List<Apartment> searchApartments(String searchInput, String filterOptions) {

        beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);

        Root<Apartment> root = criteriaQuery.from(Apartment.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(filterOptions)), "%" + searchInput.toLowerCase() + "%")));
        Query<Apartment> query = getSession().createQuery(criteriaQuery);
        List<Apartment> apartmemtList = query.getResultList();

        commitTransaction();
        return apartmemtList;

    }

    @Override
    public void updatePost(int id, String apartmentName, String description, double totalCost) {

        beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);
        Root<Apartment> root = criteriaQuery.from(Apartment.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("apartmentId"), id)
                ));
        Query<Apartment> query = getSession().createQuery(criteriaQuery);
        Apartment apartment = query.uniqueResult();
        if (description != null) {
            apartment.setDescription(description);
        }
        if (apartmentName != null) {
            apartment.setApartmentName(apartmentName);
        }
        if (totalCost >= 0) {
            apartment.setTotalCost(totalCost);
        }
        getSession().merge(apartment);
        commitTransaction();

    }

    @Override
    public List<Apartment> getApartmentByLandlord(User landlord) {
        beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);
        Root<Apartment> root = criteriaQuery.from(Apartment.class);
        Predicate condition1 = criteriaBuilder.equal(root.get("landlord"), landlord);
        Predicate condition2 = criteriaBuilder.notEqual(root.get("active"), "Inactive");
        
        Predicate finalPredicate = criteriaBuilder.and(condition1,condition2);
        criteriaQuery.select(root).where(finalPredicate);

        Query<Apartment> query = getSession().createQuery(criteriaQuery);
        
        List<Apartment> apartment = query.list();

        commitTransaction();

        return apartment;

    }

    @Override
    public void deletePost(int id) {

        beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);
        Root<Apartment> root = criteriaQuery.from(Apartment.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("apartmentId"), id)
                ));
        Query<Apartment> query = getSession().createQuery(criteriaQuery);
        Apartment apartment = query.uniqueResult();
        apartment.setActive("Inactive");

        commitTransaction();

    }

}
