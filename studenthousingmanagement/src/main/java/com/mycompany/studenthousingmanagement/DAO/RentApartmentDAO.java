package com.mycompany.studenthousingmanagement.DAO;

import com.mycompany.studenthousingmanagement.Model.Apartment;
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
public class RentApartmentDAO extends HibernateConfig implements RentApartmentInterface {

    @Override
    public void rentApartmentRequest(ApartmentRent rentRequest) {
        beginTransaction();
        getSession().persist(rentRequest);
        commitTransaction();

    }

    @Override
    public List<ApartmentRent> getRentApartmentRequest(User landloard) {
        beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<ApartmentRent> criteriaQuery = criteriaBuilder.createQuery(ApartmentRent.class);
        Root<ApartmentRent> root = criteriaQuery.from(ApartmentRent.class);

        Predicate condition1 = criteriaBuilder.equal(root.get("landlord"), landloard);
        Predicate condition2 = criteriaBuilder.notEqual(root.get("status"), "Rejected");
        Predicate condition3 = criteriaBuilder.notEqual(root.get("status"), "Approved");

        Predicate finalCondition = criteriaBuilder.and(condition1, condition2, condition3);

        criteriaQuery.select(root)
                .where(finalCondition);

        Query<ApartmentRent> query = getSession().createQuery(criteriaQuery);
        List<ApartmentRent> apartmentList = query.list();
        commitTransaction();
        return apartmentList;
    }

    @Override
    public void rentApartmentResponseProcess(ApartmentRent rentRequest) {

        beginTransaction();

        getSession().merge(rentRequest);
        commitTransaction();

    }

    @Override
    public ApartmentRent getApartmentRentRequest(Long id) {
        beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<ApartmentRent> criteriaQuery = criteriaBuilder.createQuery(ApartmentRent.class);
        Root<ApartmentRent> root = criteriaQuery.from(ApartmentRent.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("id"), id)
                ));
        Query<ApartmentRent> query = getSession().createQuery(criteriaQuery);
        //  System.out.println("username " + A + query.uniqueResult());

        ApartmentRent apartment = query.uniqueResult();
        commitTransaction();
        return apartment;
    }

    @Override
    public ApartmentRent getRentApartmentRequestByStudent(User student) {
        beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<ApartmentRent> criteriaQuery = criteriaBuilder.createQuery(ApartmentRent.class);
        Root<ApartmentRent> root = criteriaQuery.from(ApartmentRent.class);
        Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), "approved");
        Predicate condition1 = criteriaBuilder.equal(root.get("student"), student);
        Predicate finalCondition = criteriaBuilder.and(condition1, statusPredicate);

        criteriaQuery.select(root).where(finalCondition);

        Query<ApartmentRent> query = getSession().createQuery(criteriaQuery);
        query.setMaxResults(1);
        ApartmentRent apartment = query.uniqueResult();
        
        commitTransaction();
        return apartment;
    }

    @Override
    public void deleteRentRequest(ApartmentRent apartmentRent) {
        
        beginTransaction();
        getSession().remove(apartmentRent);
        commitTransaction();
        
    }

}
