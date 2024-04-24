/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studenthousingmanagement.util;

import com.mycompany.studenthousingmanagement.controller.*;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.studenthousingmanagement.Model.Payment;
import com.mycompany.studenthousingmanagement.Model.User;
import com.mycompany.studenthousingmanagement.service.PaymentService;
import com.mycompany.studenthousingmanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author manal
 */
@Component
public class MyPDFView extends AbstractPdfView {

    List<Payment> payments = null;

    public MyPDFView() {

    }

    public MyPDFView(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (payments == null || payments.isEmpty()) {
            System.out.println("No payments found for student");
            return;
        }
        // Create a table with appropriate number of columns
        Table table = new Table(5);
        table.addCell("Date");
        table.addCell("Apartment ID");
        table.addCell("Student");
        table.addCell("Rent month");
        table.addCell("Amount Paid");

        // Add payment details to the table
        for (Payment payment : payments) {
            table.addCell(payment.getPaymentDate().toString());
            table.addCell(String.valueOf(payment.getApartment()));
            table.addCell(payment.getStudent().getFirstName() + " " + payment.getStudent().getLastName());
            table.addCell(String.valueOf(payment.getPaymentMonth()));
            table.addCell(String.valueOf(payment.getAmount()));
        }

        document.add(table);

    }
}
