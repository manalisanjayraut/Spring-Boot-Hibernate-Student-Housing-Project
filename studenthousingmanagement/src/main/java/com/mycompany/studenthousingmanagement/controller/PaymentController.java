package com.mycompany.studenthousingmanagement.controller;

import ch.qos.logback.core.CoreConstants;
import com.mycompany.studenthousingmanagement.Model.Apartment;
import com.mycompany.studenthousingmanagement.Model.ApartmentRent;
import com.mycompany.studenthousingmanagement.Model.Payment;
import com.mycompany.studenthousingmanagement.Model.User;
import com.mycompany.studenthousingmanagement.service.ApartmentService;
import com.mycompany.studenthousingmanagement.service.PaymentService;
import com.mycompany.studenthousingmanagement.service.RentApartmentService;
import com.mycompany.studenthousingmanagement.service.UserService;
import com.mycompany.studenthousingmanagement.util.MyExcelView;
import com.mycompany.studenthousingmanagement.util.MyPDFView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

/**
 *
 * @author manal
 */
@Controller
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    RentApartmentService rentApartmentService;

    @Autowired
    UserService userService;
    
    @Autowired
    ApartmentService apartmentService;

    @GetMapping("/load-payment-data")
    @ResponseBody
    public List<Payment> getPaymentDetails(Model model, HttpServletRequest request) {
        List<Payment> payments = null;
        HttpSession session = request.getSession();
        System.out.println("com.mycompany.studenthousingmanagement.controller.PaymentController.getPaymentDetails()");
        if (session == null) {
            System.out.println("session issues");

        }

        String username = (String) session.getAttribute("username");
        if (username != null) {
            User student = userService.findByUsername(username);
            payments = paymentService.getPaymentDetails(student);
        }
        return payments;
    }

    @PostMapping("/pay-rent")
    public String doPayment(Payment payment,
            @RequestParam("apartmentId") Long apartmentId,
            @RequestParam("paymentAmount") Double amount,@RequestParam("paymentMonth") String month, HttpServletRequest request) {

        String landlordname = request.getParameter("username");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {

            payment.setAmount(amount);
            payment.setApartment(apartmentId);
            payment.setStatus("PAID");
            payment.setLandlord(userService.findByUsername(landlordname));
            payment.setPaymentDate(new Date());
            payment.setPaymentMonth(month);
            payment.setStudent(userService.findByUsername(username));
            paymentService.savePayment(payment);

        }

        return "payment-success";
    }

    @GetMapping("/showMyPaytment")
    public String showMyPayemt(Model model, Payment payment, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println(username+"  userbamek98989898");
        if (username != null) {

            //get apartment
            User student = userService.findByUsername(username);
            
            ApartmentRent apartmentRent = rentApartmentService.getRentApartmentRequestByStudent(student);
            apartmentRent.setApartmentDescription(apartmentService.findApartmentById(apartmentRent.getApartmentId()).getApartmentName());
       
            model.addAttribute("apartmentRent", apartmentRent);
        }

        return "myapartment";
    }

    @GetMapping("/payment-details")
    public String loadPaymentDetails() {
        return "paymentdetails";
    }

    @GetMapping("/loadPaymentPerLandlord")
    @ResponseBody
    public List<Payment> loadPaymentForLandlord(HttpServletRequest request) {
        List<Payment> payments = null;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User landlord = userService.findByUsername(username);
            payments = paymentService.getPaymentDetails(landlord);
        }
        System.out.println("paymentdetails:" + payments);
        return payments;
    }

    @GetMapping("/generatePDF")
    public View generatePDF(HttpServletRequest request) {
        List<Payment> payments = null;
        View view = null;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User student = userService.findByUsername(username);
            payments = paymentService.getPaymentDetails(student);        
        try {
             view = new MyPDFView(payments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        return view;
    }
    
    
    @GetMapping("/generateExcel")
    public View generateExcel(HttpServletRequest request) {
        List<Payment> payments = null;
        View view = null;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            User student = userService.findByUsername(username);
            payments = paymentService.getPaymentDetails(student);        
        try {
             view = new MyExcelView(payments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        return view;
    }

}
