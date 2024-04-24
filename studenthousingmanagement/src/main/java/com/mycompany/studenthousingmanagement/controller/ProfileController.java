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
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author manal
 */
@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    User user = null;
    @GetMapping("/showMyProfile")
    public String showMyProfile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
            return "myprofile";
        } else {
            return "Login";
        }
    }

    @PostMapping("/updateMyProfile")
    public String updateMyProfilepublic(@ModelAttribute("user") User udpatedUser) {
      
        System.out.println("object" + user.getUserId()+ ":"+udpatedUser.getUserId());
        userService.updateUser(udpatedUser);
        return "myprofile";
    }

}
