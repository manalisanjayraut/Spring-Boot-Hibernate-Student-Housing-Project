<%-- 
    Document   : myapartment
    Created on : Mar 22, 2024, 7:23:41 PM
    Author     : manal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>>Show my apartment</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
              .col-md-3 {
                background-color: #6666ff;
                color: black;
                overflow-y: auto;
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
                        <a class="nav-link" href="student_dashboard">Home</a>
                        <a class="nav-link" href="showMyProfile">My Profile</a>
                        <a class="nav-link active" href="showMyPaytment">My Apartment</a>
                        <a class="nav-link" href="maintenance-request">Maintenance Request</a>
                        <a class="nav-link" href="logout">Logout</a>
                    </nav>
                </div>
                <div class="col-md-9">
                    <h1>My apartment</h1>

                    <form:form action="/pay-rent" method="post" modelAttribute="payment">
                        <div class="form-group">
                            <label for="apartmentId">Apartment ID:</label>
                            <input type="text" name="apartmentId" class="form-control" value="${apartmentRent.apartmentId}" readonly/>
                        </div>

                        <div class="form-group">
                            <label for="apartmentId">Apartment Name:</label>
                            <input type="text" class="form-control" value="${apartmentRent.apartmentDescription}" readonly/>
                        </div>

                        <div class="form-group">
                            <label for="landlordId">Landlord name:</label>
                            <input type="text" class="form-control" value="${apartmentRent.landlord.firstName} ${apartmentRent.landlord.lastName}" readonly/>
                        </div>
                        <input type="hidden"  name ="username" class="form-control" value="${apartmentRent.landlord.username}"/>

                        <div class="form-group">
                            <label for="rentAmount">Rent Amount:</label>    
                            <input type="text" name="rentAmount" class="form-control" value="${apartmentRent.rentAmount}" readonly/>
                        </div>

                        <div class="form-group">
                            <label for="paymentAmount">Payment Amount:</label>
                            <input type="number" name="paymentAmount" class="form-control" name="amount" required="true"/>
                        </div>

                        <div class="form-group">
                            <label for="paymentMonth">Select Month:</label>
                            <select name="paymentMonth" class="form-control">
                                <option value="January">January</option>
                                <option value="February">February</option>
                                <option value="March">March</option>
                                <option value="April">April</option>
                                <option value="May">May</option>
                                <option value="June">June</option>
                                <option value="July">July</option>
                                <option value="August">August</option>
                                <option value="September">September</option>
                                <option value="October">October</option>
                                <option value="November">November</option>
                                <option value="December">December</option>  
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Pay Rent</button>

                    </form:form>


                    <h2>Payment History</h2>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Payment Date</th>
                                <th>Rent Month</th>
                                <th>Amount Paid</th>
                            </tr>
                        </thead>
                        <tbody id="paymentHistory">
                            <!-- Payment details will be dynamically added here -->
                        </tbody>
                    </table>    

                </div>
            </div>
        </div>
    </body>
    <script>

        window.onload = function () {
            console.log("i am here 22221111");
            loadPaymentDeatils();
        };

        function loadPaymentDeatils() {

            console.log("i am here 1111");

            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (this.readyState === 4) {
                    if (this.status === 200) {
                        // Process the response and update the UI if needed
                        var paymentData = JSON.parse(this.responseText);
                        displayPaymentData(paymentData);
                    } else {
                        // Handle errors
                        console.error('Error loading payment data:', this.statusText);
                    }
                }
            };

            xhr.open('GET', '/load-payment-data', true);
            xhr.send();
        }


        function displayPaymentData(paymentData) {
            var paymentHistory = document.getElementById('paymentHistory');
            paymentHistory.innerHTML = ''; // Clear existing payment history

            paymentData.forEach(function (payment) {
                var paymentRow = '<tr><td>' + payment.paymentDate + '</td><td>' + payment.paymentMonth + '</td><td>' + payment.amount + '</td></tr>';
                paymentHistory.insertAdjacentHTML('beforeend', paymentRow);
            });
        }

    </script>
</html>
