package com.mycompany.studenthousingmanagement.util;

import com.mycompany.studenthousingmanagement.Model.Payment;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsView;

/**
 *
 * @author manal
 */
public class MyExcelView extends AbstractXlsView {

    private List<Payment> payments;

    public MyExcelView() {
    }

    public MyExcelView(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        if (payments == null || payments.isEmpty()) {
            System.out.println("No payments found for student");
            return;
        }

        // Create Excel sheet
        Sheet sheet = workbook.createSheet("Payments");

        // Create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Date");
        header.createCell(1).setCellValue("Apartment ID");
        header.createCell(2).setCellValue("Student");
        header.createCell(3).setCellValue("Rent month");
        header.createCell(4).setCellValue("Amount Paid");

        // Add payment details to the sheet
        int rowNum = 1;
        for (Payment payment : payments) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(payment.getPaymentDate().toString());
            row.createCell(1).setCellValue(String.valueOf(payment.getApartment()));
            row.createCell(2).setCellValue(payment.getStudent().getFirstName() + " " + payment.getStudent().getLastName());
            row.createCell(3).setCellValue(String.valueOf(payment.getPaymentMonth()));
            row.createCell(4).setCellValue(String.valueOf(payment.getAmount()));
        }

        // Set response headers
        response.setHeader("Content-Disposition", "attachment; filename=\"payments.xls\"");
    }
}