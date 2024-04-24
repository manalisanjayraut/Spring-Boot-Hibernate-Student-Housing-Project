package com.mycompany.studenthousingmanagement.controller;

import com.mycompany.studenthousingmanagement.Model.Apartment;
import com.mycompany.studenthousingmanagement.service.ApartmentService;
import com.mycompany.studenthousingmanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author manal
 */
@Controller
public class LandlordController {

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    UserService userService;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    @RequestMapping(value = "/postProperty", method = RequestMethod.GET)
    public String postProperty(Model model, Apartment apartment) {

        model.addAttribute("apartment", apartment);
        return "postProperty";
    }

    @PostMapping(value = "/landlord/post")
    public String postProperty(@ModelAttribute("Apartment") Apartment apartment, HttpServletRequest request) throws IOException {

        MultipartFile file = apartment.getFile();
        if (file != null && !file.isEmpty()) {
            // Generate a unique filename
            String originalFileName = file.getOriginalFilename();
            String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
            String filename = UUID.randomUUID().toString() + extension;

            Files.createDirectories(Paths.get(fileUploadPath));

            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileUploadPath + filename);
            Files.write(path, bytes);

            apartment.setFileName(filename);

            HttpSession session = request.getSession();

            String username = (String) session.getAttribute("username");
            if (username != null) {
                apartment.setLandlord(userService.findByUsername(username));
                apartment.setActive("Active");
            } 
        }
        apartmentService.saveApartment(apartment);

        return "redirect:/postProperty";
    }
}
