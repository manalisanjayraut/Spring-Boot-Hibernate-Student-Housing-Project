<%-- 
    Document   : Login
    Created on : Mar 22, 2024, 7:23:41 PM
    Author     : manal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Post Property</title>
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
                        <h1>Post Rental Listing</h1>
                        <form:form action="/landlord/post" method="post" enctype="multipart/form-data" modelAttribute="apartment">
                            <div class="form-group">
                                <label for="apartmentName">Apartment Name:</label>
                                <form:input path="apartmentName" class="form-control" id="apartmentName"/>
                            </div>
                            <div class="form-group">
                                <label for="location">Location Name:</label>
                                <form:input path="location" class="form-control" id="location"/>
                            </div>
                            <div class="form-group">
                                <label for="roomType">Room Type</label>
                                <form:input path="roomType" class="form-control" id="roomType"/>
                            </div>
                            <div class="form-group">
                                <label for="description">Description:</label>
                                <form:textarea path="description" class="form-control" id="description"/>
                            </div>
                            <div class="form-group">
                                <label for="availableQuantity">Available Quantity:</label>
                                <form:input path="availableQuantity" class="form-control" id="availableQuantity"/>
                            </div>
                            <div class="form-group">
                                <label for="cost">Cost:</label>
                                <form:input path="cost" class="form-control" id="cost" type="number"/>
                            </div>
                            <div class="form-group">
                                <label for="totalCost">Total Cost:</label>
                                <form:input path="totalCost" class="form-control" id="totalCost" type="number"/>
                            </div>
                            <div class="form-group">
                                <label for="file">Upload Image/PDF:</label>
                                <input type="file" class="form-control-file" id="file" name="file">
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
