package com.mycompany.studenthousingmanagement.controller;

import com.mycompany.studenthousingmanagement.Model.Apartment;
import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.Model.*;
import com.mycompany.studenthousingmanagement.service.ApartmentService;
import com.mycompany.studenthousingmanagement.service.RentApartmentService;
import com.mycompany.studenthousingmanagement.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author manal
 */
@Controller
public class ApartmentController {

    @Autowired
    RentApartmentService rentApartmentService;

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    UserService userService;

    @PostMapping("/rent-request")
    public ResponseEntity<String> rentApartmentRequest(@RequestParam("apartmemtId") Long apartmemtId, HttpServletRequest request) {

        // String apartmentID = request.getParameter("apartmentId");
        System.out.println("i am here" + apartmemtId);

        Apartment apartment = apartmentService.findApartmentById(apartmemtId);

        HttpSession session = request.getSession();
        if (session == null) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired");
        }

        String username = (String) session.getAttribute("username");
        if (username != null) {

            ApartmentRent apartmentrentRequest = new ApartmentRent();

            apartmentrentRequest.setApartmentId(apartment.getApartmentId());
            apartmentrentRequest.setLandlord(apartment.getLandlord());

            apartmentrentRequest.setRentAmount(apartment.getTotalCost());
            apartmentrentRequest.setStudent(userService.findByUsername(username));
            apartmentrentRequest.setStatus("Requested");
            rentApartmentService.rentApartmentRequest(apartmentrentRequest);
            return ResponseEntity.ok("Rent request sent successfully");
        }else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
    }

       
    }

    @GetMapping("/check-rent-request")
    public String checkRentRequest(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        if (username != null) {
            List<ApartmentRent> list = rentApartmentService.getRentApartmentRequest(userService.findByUsername(username));
            model.addAttribute("rentRequestList", list);
        }
        return "checkRentRequest";
    }

    @PostMapping("/accept-rent-request")
    public String processRentRequest(HttpServletRequest request) {
        Long rentRequestId = Long.parseLong(request.getParameter("rentRequestId"));

        ApartmentRent apartmentRent = rentApartmentService.getApartmentRentRequest(rentRequestId);

        apartmentRent.setStatus("Approved");

        rentApartmentService.updateApartmentRentStatus(apartmentRent);
        return "checkRentRequest";
    }

    @PostMapping("/reject-rent-request")
    public String processRentRequestStatus(HttpServletRequest request) {
        Long rentRequestId = Long.parseLong(request.getParameter("rentRequestId"));

        ApartmentRent apartmentRent = rentApartmentService.getApartmentRentRequest(rentRequestId);
        rentApartmentService.deleteRentRequest(apartmentRent);
        return "checkRentRequest";
    }

    @PostMapping("/search-apartment")
    @ResponseBody
    public List<Apartment> searchApartments(@RequestBody SearchCriteria searchCriteria) {
        System.out.println("loctaion:" + searchCriteria.getFilterOptions());
        List<Apartment> apartments = apartmentService.searchApartments(searchCriteria);
        System.out.println("result :" + apartments);
        return apartments;
    }
    
    @GetMapping("/update-property")
    public String viewUpdatePage(Model model, HttpServletRequest request){
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
        User landlord = userService.findByUsername(username);
        List<Apartment> apartments = apartmentService.getApartmentByLandlord(landlord);
            System.out.println("list of aprt :" + apartments);
        model.addAttribute("apartments", apartments);
        }
        return "updateProperty";
    }
    
    @PostMapping("/updatePost")
    public ResponseEntity<String> updatePost(@RequestParam("id") int id, @RequestBody Map<String, Object> requestBody) {
        // Extract data from the request body
        String apartmentName = (String) requestBody.get("apartmentName");
        String description = (String) requestBody.get("description");
        double totalCost = Double.parseDouble((String) requestBody.get("totalCost"));

        // Update the apartment using the extracted data
        apartmentService.updatePost(id, apartmentName, description, totalCost);
        
        return ResponseEntity.ok("Post is updated successfully");
    }
    
    @PostMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam("id") int id) {
             
        apartmentService.deletePost(id);
        
        return ResponseEntity.ok("Post is deleted successfully");
            
    }
}
  