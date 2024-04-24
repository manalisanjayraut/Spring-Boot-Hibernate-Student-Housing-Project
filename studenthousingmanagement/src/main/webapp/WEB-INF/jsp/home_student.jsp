<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student Dashboard</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .apartment-image {
                max-width: 200px;
                height: auto;
            }
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
                background-color: white;
            }
        </style>
    </head>
    <body>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <nav class="nav flex-column">
                        <a class="nav-link active" href="/student_dashboard">Home</a>
                        <a class="nav-link" href="/showMyProfile">My Profile</a>
                        <a class="nav-link" href="/showMyPaytment">My Apartment</a>
                        <a class="nav-link" href="/maintenance-request">Maintenance Request</a>
                        <a class="nav-link" href="/logout">Logout</a>
                    </nav>
                </div>

                <div class="col-md-9">
                    <h1>Welcome to Student Dashboard</h1>
                    <br>
                    <hr/>


                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <input type="text" class="form-control" id="searchInput" placeholder="Search...">
                        </div>
                        <div class="col-md-6">
                            <select class="form-control" id="filterOptions">
                                <option value="location">Location</option>
                                <option value="description">Description</option>
                                <option value="roomType">Room Type</option>
                                <option value="price">Price</option>
                            </select>
                        </div>
                        <div class="col-md-12 mt-3">
                            <button class="btn btn-primary" id="searchBtn" onclick="load()">Search</button>
                        </div>
                    </div>
                    <h2>Apartments Available:</h2>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Apartment Name</th>
                                <th>Location</th>
                                <th>Room Type</th>
                                <th>Description</th>
                                <th>Cost</th>
                                <th>Image</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody id="apartmentTableBody">
                            <!-- Table rows will be dynamically added here -->
                        </tbody>
                    </table>
                    <div id="paginationLinks" class="mt-3" style="text-align: center;">
                        <c:forEach var="i" begin="1" end="4">
                            <a href="javascript:void(0);" onclick="loadAllApartments(${i});" style="align-content: center"> ${i} </a>
                            &nbsp;&nbsp;
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>

                                window.onload = function () {
                                    loadAllApartments(1);
                                };

                                function loadAllApartments(pageNum) {
                                    var xhr = new XMLHttpRequest();
                                    xhr.onreadystatechange = function () {
                                        if (this.readyState === 4 && this.status === 200) {
                                            var response = this.responseText;
                                            displaySearchResults(response);
                                        }
                                    };
                                    console.log("pagenum" + pageNum);
                                    xhr.open("GET", "/fetch-all-apartments?pagenum=" + pageNum, true);
                                    xhr.send();
                                }
                                function load() {


                                    var searchInput = document.getElementById("searchInput").value;
                                    var filterOption = document.getElementById("filterOptions").value;

                                    var xhr = new XMLHttpRequest();


                                    xhr.onreadystatechange = function () {
                                        if (this.readyState === 4 && this.status === 200) {
                                            var response = this.responseText;
                                            console.log("response :", response);
                                            displaySearchResults(response);
                                        }
                                    };

                                    xhr.open("POST", "/search-apartment", true);
                                    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
                                    var payload = JSON.stringify({searchInput: searchInput, filterOptions: filterOption});
                                    console.log("payload :" + payload);
                                    xhr.send(payload);
                                }

                                function displaySearchResults(apartments) {
                                    var tableBody = document.getElementById("apartmentTableBody");
                                    tableBody.innerHTML = ""; // Clear existing table rows
                                    console.log("apartments" + apartments);
                                    var apartments = JSON.parse(apartments);

                                    if (Array.isArray(apartments)) {
                                        console.log("1 am here apartments" + apartments);
                                        apartments.forEach(function (apartment) {
                                            var row = "<tr>" +
                                                    "<td>" + apartment.apartmentName + "</td>" +
                                                    "<td>" + apartment.location + "</td>" +
                                                    "<td>" + apartment.roomType + "</td>" +
                                                    "<td>" + apartment.description + "</td>" +
                                                    "<td>" + apartment.totalCost + "</td>" +
                                                    "<td><img src='${pageContext.request.contextPath}/static/" + apartment.fileName + "' class='apartment-image'/></td>" +
                                                    "<td>" +
                                                    "<form id='requestForm" + apartment.apartmentId + "' onsubmit='sendRequest(" + apartment.apartmentId + "); return false;'>" +
                                                    "<input type='hidden' name='apartmentId' value='" + apartment.apartmentId + "'>" +
                                                    "<button type='submit' class='btn btn-primary'>Send Request</button>" +
                                                    "</form>" +
                                                    "</td>" +
                                                    "</tr>";
                                            tableBody.insertAdjacentHTML("beforeend", row);
                                        });
                                    } else
                                    {
                                        var row = "<tr>" +
                                                "<td>" + apartments[0].apartmentName + "</td>" +
                                                "<td>" + apartments[0].description + "</td>" +
                                                "<td>" + apartments.totalCost + "</td>" +
                                                "<td><img src='${pageContext.request.contextPath}/static/" + apartment.fileName + "' class='apartment-image'/></td>" +
                                                "<td>" +
                                                "<form id='requestForm" + apartments.apartmentId + "' onsubmit='sendRequest(" + apartments.apartmentId + "); return false;'>" +
                                                "<input type='hidden' name='apartmentId' value='" + apartments.apartmentId + "'>" +
                                                "<button type='submit' class='btn btn-primary'>Send Request</button>" +
                                                "</form>" +
                                                "</td>" +
                                                "</tr>";
                                        tableBody.insertAdjacentHTML("beforeend", row);
                                    }
                                }


                                function sendRequest(apartmentId) {
                                    var xhr = new XMLHttpRequest();
                                    xhr.onreadystatechange = function () {
                                        if (this.readyState === 4) {
                                            if (this.status === 200) {
                                                var message = this.responseText;
                                                alert(message);
                                            } else {
                                                // Request failed
                                                console.error("Error sending rent request");
                                                // You can handle any further actions here, such as displaying an error message
                                            }
                                        }
                                    }
                                    ;
                                    xhr.open("POST", "/rent-request", true);
                                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                    var params = "apartmemtId=" + encodeURIComponent(apartmentId);
                                    xhr.send(params);
                                }
        </script>
    </body>
</html>
