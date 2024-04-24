/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.validator;

/**
 *
 * @author manal
 */
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.mycompany.studenthousingmanagement.Model.Apartment;

@Component
public class ApartmentValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Apartment.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object command, Errors errors) {
        Apartment apartment = (Apartment) command;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apartmentName", "empty-apartmentName", "Apartment Name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "empty-description", "Description cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "availableQuantity", "empty-availableQuantity", "Available Quantity cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost", "empty-cost", "Cost cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalCost", "empty-totalCost", "Total Cost cannot be empty");

        // Add more validation rules as needed

        // For example, you can check if available quantity and cost are numeric
        if (!isNumeric(apartment.getAvailableQuantity())) {
            errors.rejectValue("availableQuantity", "invalid-availableQuantity", "Available Quantity must be a number");
        }
        if (!isNumeric(Double.toString(apartment.getCost()))) {
            errors.rejectValue("cost", "invalid-cost", "Cost must be a number");
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}

