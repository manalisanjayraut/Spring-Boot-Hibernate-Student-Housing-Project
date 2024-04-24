/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.validator;

/**
 *
 * @author manal
 */
import com.mycompany.studenthousingmanagement.Model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private static final int MAX_NAME_LENGTH = 25;
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object command, Errors errors) {
        User user = (User) command;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty-username", "Username cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "Password cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty-email", "Email cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "empty-firstName", "First Name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "empty-lastName", "Last Name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "empty-dob", "Date of Birth cannot be empty");

        // Validate Date of Birth (Age should be more than 15)
        LocalDate today = LocalDate.now();
        LocalDate dob = user.getDob().toLocalDate();
        Period period = Period.between(dob, today);
        if (period.getYears() < 15) {
            errors.rejectValue("dob", "invalid-age", "Age should be at least 15 years old");
        }

        // Validate First Name and Last Name (Alphabetic characters and max length)
        if (!isValidName(user.getFirstName())) {
            errors.rejectValue("firstName", "invalid-firstName", "First Name should contain only alphabetic characters and be less than 25 characters long");
        }
        if (!isValidName(user.getLastName())) {
            errors.rejectValue("lastName", "invalid-lastName", "Last Name should contain only alphabetic characters and be less than 25 characters long");
        }
    }

    private boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches() && name.length() <= MAX_NAME_LENGTH;
    }
}
