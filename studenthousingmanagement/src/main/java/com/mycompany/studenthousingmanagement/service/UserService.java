/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.service;

/**
 *
 * @author manal
 */

import com.mycompany.studenthousingmanagement.DAO.UserDao;
import com.mycompany.studenthousingmanagement.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    @Autowired
    private UserDao userDAO;

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Transactional
    public User findByUsername(String username) {
        return (User) userDAO.findByUsername(username);
    }
    
    @Transactional
     public User login(String username, String password){
         
         return (User) userDAO.login(username, password);
         
     }

    public boolean isUserPresent(User user) {
      
        return userDAO.isUserPresent(user);
    }

    public void updateUser(User user) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        userDAO.updateUser(user);
    }

   
}
