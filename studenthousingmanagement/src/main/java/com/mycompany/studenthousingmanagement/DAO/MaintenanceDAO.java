package com.mycompany.studenthousingmanagement.DAO;

import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.Model.Maintenance;
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
public class MaintenanceDAO extends HibernateConfig implements MaintenanceInterface {

    @Override
    public void maintenanceRequest(Maintenance maintenance) {

        beginTransaction();
        getSession().persist(maintenance);
        commitTransaction();

    }

    public List<Maintenance> getMaintenanceRequest(User findByUsername) {
        beginTransaction();

        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Maintenance> criteriaQuery = criteriaBuilder.createQuery(Maintenance.class);
        Root<Maintenance> root = criteriaQuery.from(Maintenance.class);

        Predicate condition1 = criteriaBuilder.equal(root.get("landlord"), findByUsername);
        Predicate condition2 = criteriaBuilder.equal(root.get("resolved"),false);
        Predicate finalCondition = criteriaBuilder.and(condition1, condition2 );

        criteriaQuery.select(root)
                .where(finalCondition);

        Query<Maintenance> query = getSession().createQuery(criteriaQuery);
        List<Maintenance> list = query.list();

        commitTransaction();
        return list;
    }

    @Override
    public void processRequest(int requestId) {

        beginTransaction();
        String sql = "UPDATE maintenance_requests SET resolved = :newStatus WHERE request_id = :requestId";
        Query query = getSession().createNativeQuery(sql);
        // Set parameters
        query.setParameter("newStatus", 1);
        query.setParameter("requestId", requestId); // Assuming the request ID is 1

        // Execute the update query
        int updatedRows = query.executeUpdate();
        commitTransaction();

    }
}
