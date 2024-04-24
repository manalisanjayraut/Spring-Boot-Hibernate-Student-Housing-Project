<%-- 
    Document   : register
    Created on : Mar 22, 2024, 9:09:45 PM
    Author     : manal
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center">Registration Form</h3>
                </div>
                <div class="card-body">
                    <form:form action="createUser" method="post" modelAttribute="user">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <form:input path="username" class="form-control" id="username" required="true"/>
                            <form:errors path="username" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <form:password path="password" class="form-control" id="password" required="true"/>
                            <form:errors path="password" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <form:input path="email" class="form-control" id="email" required="true" type="email"/>
                            <form:errors path="email" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber">Phone Number:</label>
                            <form:input path="phoneNumber" class="form-control" id="phoneNumber"/>
                            <form:errors path="phoneNumber" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="firstName">First Name:</label>
                            <form:input path="firstName" class="form-control" id="firstName"/>
                            <form:errors path="firstName" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name:</label>
                            <form:input path="lastName" class="form-control" id="lastName"/>
                            <form:errors path="lastName" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="dob">Date of Birth:</label>
                            <form:input path="dob" class="form-control" id="dob" type="date"/>
                            <form:errors path="dob" class="text-danger" />
                        </div>
                        <div class="form-group">
                            <label>Are you a student or a landlord?</label><br>
                            <form:radiobutton path="role" id="student" value="Student"/> Student
                            <form:radiobutton path="role" id="landlord" value="Landlord"/> Landlord
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Register</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
