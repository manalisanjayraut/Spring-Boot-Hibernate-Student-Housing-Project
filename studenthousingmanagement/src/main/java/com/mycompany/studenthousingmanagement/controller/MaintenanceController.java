package com.mycompany.studenthousingmanagement.controller;

import com.mycompany.studenthousingmanagement.Model.Apartment;
import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.Model.Maintenance;
import com.mycompany.studenthousingmanagement.Model.Payment;
import com.mycompany.studenthousingmanagement.service.ApartmentService;
import com.mycompany.studenthousingmanagement.service.MaintenanceService;
import com.mycompany.studenthousingmanagement.service.RentApartmentService;
import com.mycompany.studenthousingmanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author manal
 */
@Controller
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    @Autowired
    RentApartmentService rentApartmentService;

    @Autowired
    UserService userService;
    
    @Autowired
    ApartmentService apartmentService;

    @RequestMapping(value = "/maintenance-request", method = RequestMethod.GET)
    public String postProperty(Model model, Maintenance maintenace, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {

            //get apartment
            ApartmentRent apartmentRent = rentApartmentService.getRentApartmentRequestByStudent(userService.findByUsername(username));
            
            apartmentRent.setApartmentDescription(apartmentService.findApartmentById(apartmentRent.getApartmentId()).getApartmentName());
            model.addAttribute("apartmentRent", apartmentRent);
            model.addAttribute("maintenace", maintenace);

            return "postMaintenanceRequest";
        }
        return "Login";

    }

    @PostMapping("/maintenance/post")
    public String postRequest(Maintenance maintenance,
            @RequestParam("apartment_id") Long apartmentId,
            @RequestParam("landlordusername") String landlordusername,
            @RequestParam("description") String description, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {

            maintenance.setApartment_id(apartmentId);
            maintenance.setDescription(description);
            maintenance.setLandlord(userService.findByUsername(landlordusername));
            maintenance.setStudent(userService.findByUsername(username));
            maintenance.setResolved(false);
            maintenance.setRequestDate(new Date());
            maintenanceService.maintenanceRequest(maintenance);
        }

        return "maintenance-success";

    }

    @GetMapping("/process-maintenance")
    public String processMaintenance() {
        return "processmaintenance";
    }

    @PostMapping("/process-maintenance-request")
    public ResponseEntity<String> processMaintenanceRequest(@RequestParam("maintenanceRequestId") int requestId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            maintenanceService.processRequest(requestId);
            return ResponseEntity.ok("Maintenance request processed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
    }

    @GetMapping("/load-maintenance-data")
    @ResponseBody
    public List<Maintenance> processmaintenance(Model model, HttpServletRequest request) {
        List<Maintenance> list = null;
        HttpSession session = request.getSession();
       
        String username = (String) session.getAttribute("username");
        if (username != null) {

            list = maintenanceService.getMaintenanceRequest(userService.findByUsername(username));
            System.out.println("list :" + list);
            for(Maintenance maintenance: list){
                maintenance.setApartmentDescription(apartmentService.findApartmentById(maintenance.getApartment_id()).getApartmentName());
            }
            model.addAttribute("maintenanceRequestList", list);
        }

        return list;
    }

}
