/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.studenthousingmanagement.DAO;

import com.mycompany.studenthousingmanagement.Model.Maintenance;

/**
 *
 * @author manal
 */
public interface MaintenanceInterface {
    
     public void maintenanceRequest(Maintenance maintenance) ;
      public void processRequest(int requestId);
}
