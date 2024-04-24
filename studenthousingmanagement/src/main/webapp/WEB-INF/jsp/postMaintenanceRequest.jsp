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
        <title>Post Rental Listing</title>
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
            .btn btn-primary{
                background-color: blue;
            }
        </style>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <nav class="nav flex-column">
                        <a class="nav-link" href="/student_dashboard">Home</a>
                        <a class="nav-link" href="/showMyProfile">My Profile</a>
                        <a class="nav-link active" href="/showMyPaytment">My Apartment</a>
                        <a class="nav-link" href="/maintenance-request">Maintenance Request</a>
                        <a class="nav-link" href="/logout">Logout</a>
                    </nav>
                </div>
                <div class="col-md-9">
                    <h1>Post Maintenance Request</h1>
                    
                    <hr style="border-color: blue;"/>
                    <form:form action="/maintenance/post" method="post"  modelAttribute="maintenace">
                        <div class="form-group">
                            <label for="apartmentId">Apartment ID:</label>
                            <input name="apartment_id"  type="text" class="form-control" id="apartmentId" value="${apartmentRent.apartmentId}" readonly="true"/>
                        </div>
                        
                         <div class="form-group">
                            <label for="apartmentId">Apartment Description : </label>
                            <input type="text" class="form-control"  value="${apartmentRent.apartmentDescription}" readonly="true"/>
                        </div>

                        <div class="form-group">
                            <label for="landlordId">Landlord Name:</label>
                            <input type="text" class="form-control" id="landlordId" value="${apartmentRent.landlord.firstName}  ${apartmentRent.landlord.lastName}" readonly="true"/>
                        </div>
                        <div class="form-group">
                            <label for="studentId">Student Name:</label>
                            <input  type="text"  class="form-control" id="studentId" value="${apartmentRent.student.firstName}  ${apartmentRent.student.lastName}" readonly="true"/>
                        </div>
                        <div class="form-group">
                            <label for="description">Please write request:</label>
                            <input name="description" class="form-control" id="description" type="textarea"/>
                        </div>
                        <input name="landlordusername" type="hidden"  class="form-control"  value="${apartmentRent.landlord.username}"/>

                        <button type="submit" class="btn btn-primary">Submit Request</button>
                    </form:form>
                </div>
                </body>
                </html>
