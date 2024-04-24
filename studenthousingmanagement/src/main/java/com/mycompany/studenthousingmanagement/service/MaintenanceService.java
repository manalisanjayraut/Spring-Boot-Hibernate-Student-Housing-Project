/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.service;

import com.mycompany.studenthousingmanagement.DAO.MaintenanceDAO;
import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.Model.Maintenance;
import com.mycompany.studenthousingmanagement.Model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author manal
 */

@Service
public class MaintenanceService {
    
    @Autowired
    MaintenanceDAO maintenanceDAO;
    public void maintenanceRequest(Maintenance maintenance){
        maintenanceDAO.maintenanceRequest(maintenance);
    }

    public List<Maintenance> getMaintenanceRequest(User findByUsername) {
        return maintenanceDAO.getMaintenanceRequest(findByUsername);
    }

    public void processRequest(int requestId) {
        maintenanceDAO.processRequest(requestId);
    }
    
}
