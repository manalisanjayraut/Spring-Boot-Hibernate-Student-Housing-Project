package com.mycompany.studenthousingmanagement.controller;

import com.mycompany.studenthousingmanagement.Model.Apartment;
import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.service.ApartmentService;
import com.mycompany.studenthousingmanagement.service.RentApartmentService;
import com.mycompany.studenthousingmanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author manal
 */
@Controller
public class StudentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private RentApartmentService rentApartmentService;

    @Autowired
    private UserService userservice;

    @GetMapping("/student_dashboard")
    public String showDashboard(Model model) {

        List<Apartment> apartments = apartmentService.getAllApartments(1);
        System.out.println("apartmemt" + apartments.toString());
        model.addAttribute("apartments", apartments);
        return "home_student";
    }

    @GetMapping("/fetch-all-apartments")
    @ResponseBody
    public List<Apartment> showData(@RequestParam("pagenum")int pageNum,Model model) {

        List<Apartment> apartments = apartmentService.getAllApartments(pageNum);
        return apartments;
    }
    
        

    @GetMapping("/fetch-my-apartment")
    public String showMyApartment(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session != null) {

            String username = (String) session.getAttribute("username");
            if (username != null) {
                ApartmentRent apartmentRent = rentApartmentService.getRentApartmentRequestByStudent(userservice.findByUsername(username));

                model.addAttribute("apartmentRent", apartmentRent);
            }

        }
        
        return "myapartment";
    }

}
