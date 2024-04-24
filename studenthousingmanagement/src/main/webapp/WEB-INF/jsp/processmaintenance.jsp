<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Process Maintenance</title>
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
                background-color: #f5f5f5;
            }
            .h2{
                background-color: orange;
            }
            
            .table-striped tbody tr:nth-child(even) {
                background-color: #f2f2f2; 
            }

            .table-striped tbody tr:nth-child(odd) {
                background-color: #ccccff; 
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
                        <h2>Maintenance Requests Details</h2>

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Requested Date</th> 
                                    <th>Requested Apartment ID </th>
                                    <th>Requested Student</th>
                                    <th>Requested Apartment Description</th>
                                    <th>Requested Maintenance</th>
                                </tr>
                            </thead>

                            <tbody id="maintenanceTableBody">



                            </tbody>

                        </table>
                    </div>
                    </body>
                    </html>
                    <script>
                        function processMaintenanceRequest(maintenanceRequestId) {
                            // Make an AJAX request to process the maintenance request
                            var xhr = new XMLHttpRequest();
                            xhr.onreadystatechange = function () {
                                if (this.readyState === 4) {
                                    if (this.status === 200) {
                                        // Alert the user that the request is processed
                                        alert('Maintenance request processed successfully!');
                                        // Reload maintenance data after processing
                                        loadMaintenanceData();
                                    } else {
                                        // Handle errors
                                        console.error('Error processing maintenance request:', this.statusText);
                                    }
                                }
                            };

                            xhr.open('POST', '/process-maintenance-request', true);
                            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                            xhr.send('maintenanceRequestId=' + encodeURIComponent(maintenanceRequestId));
                        }
                        window.onload = function () {
                            //console.log("i am here 22221111");
                            loadMaintenanceData();
                        };

                        function loadMaintenanceData() {
                            var xhr = new XMLHttpRequest();
                            xhr.onreadystatechange = function () {
                                if (this.readyState === 4) {
                                    if (this.status === 200) {
                                        // Process the response and update the UI if needed
                                        var maintenanceData = JSON.parse(this.responseText);
                                        displayMaintenanceData(maintenanceData);
                                    } else {
                                        // Handle errors
                                        console.error('Error loading maintenance data:', this.statusText);
                                    }
                                }
                            };

                            xhr.open('GET', '/load-maintenance-data', true);
                            xhr.send();
                        }

                        function displayMaintenanceData(maintenanceData) {
                            var maintenanceTableBody = document.getElementById('maintenanceTableBody');
                            maintenanceTableBody.innerHTML = ''; // Clear existing maintenance table body

                            // Loop through the maintenance data and create table rows
                            maintenanceData.forEach(function (maintenanceRequest) {
                                var row = "<tr>" +
                                        "<td>" + maintenanceRequest.requestDate + "</td>" +
                                        "<td>" + maintenanceRequest.apartment_id + "</td>" +
                                        "<td>" + maintenanceRequest.student.firstName + "</td>" +
                                        "<td>" + maintenanceRequest.apartmentDescription + "</td>" +
                                        "<td>" + maintenanceRequest.description + "</td>" +
                                        "<td><button onclick='processMaintenanceRequest(" + maintenanceRequest.requestId + ")' style='color: #fff;background-color: #007bff;border-color: #007bff;'>Process</button></td>" +
                                        "</tr>";
                                maintenanceTableBody.insertAdjacentHTML('beforeend', row);
                            });
                        }
                    </script>                                
