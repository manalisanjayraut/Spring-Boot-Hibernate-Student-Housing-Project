<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Payment Details Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .col-md-3 {
                background-color: #6666ff;
                color: black;
                height:100vh;
            }

            .col-md-3 nav {
                background-color: #6666ff;
            }
            .nav-link{
                color:white;
            }
            .table-striped .active-link {
                background-color: #f5f5f5; /* Change to the color you want */
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <nav class="nav flex-column">
                        <a class="nav-link" href="update-property">Home</a>
                        <a class="nav-link" href="postProperty">Post Property</a>
                        <a class="nav-link" href="check-rent-request">Rent Request</a>
                        <a class="nav-link" href="process-maintenance">Maintenance Request</a>
                        <a class="nav-link" href="payment-details">Payment History</a>
                        <a class="nav-link" href="logout">Logout</a>
                    </nav>
                </div>
                <div class="col-md-9">
                    <div class="container">
                        <h1>Payment Details</h1>
                        <a class="btn btn-primary" href="/generatePDF">View PDF</a>
                        &nbsp;&nbsp;
                        <a class="btn btn-primary" href="/generateExcel">View Excel</a>
                        <br>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Date</th> 
                                    <th>Apartment ID </th>
                                    <th>Student</th>
                                    <th>Rent Month</th>
                                    <th>Amount Paid</th>
                                </tr>
                            </thead>

                            <tbody id="paymentHistory">
                                <!-- Payment details will be dynamically added here -->



                            </tbody>

                        </table>
                    </div>
                    </body>
                    </html>
                    <script>
                        window.onload = function () {
                            loadPaymentDetails();
                        };

                        function loadPaymentDetails() {
                            var xhr = new XMLHttpRequest();
                            xhr.onreadystatechange = function () {
                                if (this.readyState === 4 && this.status === 200) {
                                    var response = this.responseText;
                                    displaySearchResults(response);
                                }
                            };
                            xhr.open("GET", "/loadPaymentPerLandlord", true);
                            xhr.send();
                        }


                        function displaySearchResults(paymentData) {
                            console.log("payment details", paymentData);

                            var paymentHistory = document.getElementById('paymentHistory');
                            paymentHistory.innerHTML = ''; // Clear existing payment history

                            var payments = JSON.parse(paymentData);

                            if (Array.isArray(payments)) {
                                payments.forEach(function (payment) {
                                    var paymentRow = '<tr><td>' + payment.paymentDate + '</td><td>' + payment.apartment + '</td><td>' + payment.student.firstName + ' ' + payment.student.lastName + '</td><td>'+payment.paymentMonth+'</td><td>' + payment.amount + '</td></tr>';
                                    paymentHistory.insertAdjacentHTML('beforeend', paymentRow);
                                });
                            }
                        }


                    </script>                                
