<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Check Rent Request</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>

            .col-md-3{
                background-color: #6666ff;
                color:black;
                height:100vh;
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
                        <h1>Rent Requests</h1>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Student</th>
                                    <th>Apartment Name</th>
                                    <th>Description</th>
                                    <th>Cost</th>

                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${rentRequestList}" var="rentRequest">
                                    <tr>
                                        <td>${rentRequest.student.username}</td>
                                        <td>${rentRequest.apartmentId}</td>
                                        <td></td>
                                        <td>${rentRequest.rentAmount}</td>

                                        <td>${rentRequest.status}</td>
                                        <td>
                                            <form action="/accept-rent-request" method="post">
                                                <input type="hidden" name="rentRequestId" value="${rentRequest.id}">
                                                <button type="submit" class="btn btn-success">Accept</button>
                                            </form>
                                            <form action="/reject-rent-request" method="post">
                                                <input type="hidden" name="rentRequestId" value="${rentRequest.id}">
                                                <button type="submit" class="btn btn-danger">Reject</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
</div>
 </body>
 </html>
