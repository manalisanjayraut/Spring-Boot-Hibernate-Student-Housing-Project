package com.mycompany.studenthousingmanagement.DAO;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.hibernate.cfg.Configuration;





/**
 *
 * @author manal
 */


public class HibernateConfig {

    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void beginTransaction() {
        getSession().beginTransaction();
    }

    public void commitTransaction() {
        getSession().getTransaction().commit();
    }

    public void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    public void closeSession() {
        getSession().close();
    }

   
}
