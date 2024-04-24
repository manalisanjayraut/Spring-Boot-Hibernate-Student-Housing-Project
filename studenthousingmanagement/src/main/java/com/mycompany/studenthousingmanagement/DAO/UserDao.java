/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.DAO;

/**
 *
 * @author manal
 */
import com.mycompany.studenthousingmanagement.Model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends HibernateConfig implements UserDAOInterface {

    @Override
    public void saveUser(User user) {
        beginTransaction();
        getSession().persist(user);
        commitTransaction();
    }

    @Override
    public User findByUsername(String username) {
        System.out.println("i am here" + username);
        beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("username"), username)
                ));
        Query<User> query = getSession().createQuery(criteriaQuery);
        User user = query.uniqueResult();
        commitTransaction();
        return user;

    }

    @Override
    public User login(String username, String password) {
        try (Session session = getSession()) {
            beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root)
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("username"), username),
                            criteriaBuilder.equal(root.get("password"), password)
                    ));
            Query<User> query = session.createQuery(criteriaQuery);
            User user = query.uniqueResult();
            commitTransaction();
          
            return user;

        }

    }

    public boolean isUserPresent(User user) {

        String username = user.getUsername();
        String email = user.getEmail();
       beginTransaction();
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("username"), username),
                        criteriaBuilder.equal(root.get("email"), email)
                ));
        Query<User> query = getSession().createQuery(criteriaQuery);
        User duplicateUser = query.uniqueResult();
       commitTransaction();
        return duplicateUser != null;

    }

    public void updateUser(User user) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        beginTransaction();
        getSession().merge(user);
        commitTransaction();

    }
}
