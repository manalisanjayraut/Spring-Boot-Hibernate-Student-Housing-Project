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
        <title>My Profile</title>
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
                background-color: #f5f5f5; 
            }
        </style>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                     <nav class="nav flex-column">
                        <a class="nav-link" href="student_dashboard">Home</a>
                        <a class="nav-link active" href="showMyProfile">My Profile</a>
                        <a class="nav-link" href="showMyPaytment">My Apartment</a>
                        <a class="nav-link" href="maintenance-request">Maintenance Request</a>
                        <a class="nav-link" href="logout">Logout</a>
                    </nav>
                </div>
                <div class="col-md-9">
                  
                    <form:form action="updateMyProfile" method="post" modelAttribute="user">
                        <form:hidden path ="userId" value="${user.userId}"/>
                        <form:hidden path ="role" value="${user.role}"/>
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <form:input path="username" class="form-control" id="username"  value="${user.username}" required="optional"/>
                            <form:errors path="username" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <form:password path="password" class="form-control" id="password" value="${user.password}" required="optional"/>
                            <form:errors path="password" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <form:input path="email" class="form-control" id="email"  value="${user.email}"  required="optional" type="email"/>
                            <form:errors path="email" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber">Phone Number:</label>
                            <form:input path="phoneNumber" class="form-control"   value="${user.phoneNumber}" id="phoneNumber"/>
                            <form:errors path="phoneNumber" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="firstName">First Name:</label>
                            <form:input path="firstName" class="form-control" id="firstName"  value="${user.firstName}"/>
                            <form:errors path="firstName" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name:</label>
                            <form:input path="lastName" class="form-control" id="lastName"  value="${user.lastName}"/>
                            <form:errors path="lastName" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="dob">Date of Birth:</label>
                            <form:input path="dob" class="form-control" id="dob" type="date"  value="${user.dob}"/>
                            <form:errors path="dob" class="text-danger" />
                        </div>

                        <button type="submit" class="btn btn-primary">Update</button>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
