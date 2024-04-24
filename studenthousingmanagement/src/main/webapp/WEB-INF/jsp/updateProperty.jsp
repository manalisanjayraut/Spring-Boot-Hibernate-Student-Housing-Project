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
                        <h2>Your Posts</h2>

                        <table class="table table-striped">
                            <table id="apartmentTable" class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Apartment Name</th>
                                        <th>Description</th>
                                        <th>Total Cost</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="apartment" items="${apartments}">
                                        <tr>
                                            <td id="apartmentName_${apartment.apartmentId}" class="editable" onclick="makeEditable(this)">${apartment.apartmentName}</td>
                                            <td id="description_${apartment.apartmentId}" class="editable" onclick="makeEditable(this)">${apartment.description}</td>
                                            <td id="totalCost_${apartment.apartmentId}" class="editable" onclick="makeEditable(this)">${apartment.totalCost}</td>
                                            <td>
                                                <button class="btn btn-primary" onclick="updateApartment(${apartment.apartmentId})">Update</button>
                                                <button class="btn btn-danger" onclick="deleteApartment(${apartment.apartmentId})">Delete</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <script>
                                function makeEditable(cell) {
                                    var value = cell.textContent.trim();
                                    var input = document.createElement('input');
                                    input.type = 'text';
                                    input.value = value;
                                    cell.textContent = '';
                                    cell.appendChild(input);
                                    input.focus();
                                    input.addEventListener('blur', function () {
                                        cell.textContent = this.value;
                                    });
                                }

                                function deleteApartment(id) {
                                    var xhr = new XMLHttpRequest();
                                    xhr.onreadystatechange = function () {
                                        if (this.readyState === 4 && this.status === 200) {
                                            location.reload();
                                        }
                                    };

                                    xhr.open("POST", "/deletePost?id=" + id, true);
                                    xhr.send();
                                }

                                function updateApartment(id) {
                                    var apartmentName = document.getElementById('apartmentName_' + id).textContent.trim();
                                    var description = document.getElementById('description_' + id).textContent.trim();
                                    var totalCost = document.getElementById('totalCost_' + id).textContent.trim();

                                    var xhr = new XMLHttpRequest();
                                    xhr.onreadystatechange = function () {
                                        if (this.readyState === 4 && this.status === 200) {
                                            location.reload();
                                        }
                                    };

                                    xhr.open("POST", "/updatePost?id=" + id, true);
                                    xhr.setRequestHeader("Content-Type", "application/json");
                                    xhr.send(JSON.stringify({apartmentName: apartmentName, description: description, totalCost: totalCost}));
                                }
                            </script>

                            </body>
                            </html>
