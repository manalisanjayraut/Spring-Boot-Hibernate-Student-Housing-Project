package com.mycompany.studenthousingmanagement.controller;

import com.mycompany.studenthousingmanagement.Model.User;
import com.mycompany.studenthousingmanagement.service.UserService;
import com.mycompany.studenthousingmanagement.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author manal
 */
@Controller
public class LoginController {

    @Autowired
    UserService userservice;

    @Autowired
    UserValidator userValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
               return "Login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = userservice.login(username, password);

        if (loginUser != null) {
            session.setAttribute("username", username);
            if (loginUser.getRole() == User.Role.Student) {
                System.out.println("I am here 53");
                return "redirect:/student_dashboard";
            }

            return "home";
        }
        return "error";
    }

    @GetMapping("/register")
    public String register(Model model, User user) {

        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/createUser")
    public String save(@ModelAttribute("user") User user, Model model, BindingResult result, SessionStatus status) {
        userValidator.validate(user, result);
        // check for any error based on form input and validator
        if (result.hasErrors()) {
            return "register";
        }
        status.setComplete();
        
        //check for duplicater user with username and email

        if (user!=null && !userservice.isUserPresent(user)) {
            userservice.saveUser(user);
        } else {
            StringBuilder message = new StringBuilder();
           
            message.append("Error : Please use different username and email");
            model.addAttribute("message",message);
            return "error";
        }
        return "Login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("username");
        }
        return "Login";
    }

}
