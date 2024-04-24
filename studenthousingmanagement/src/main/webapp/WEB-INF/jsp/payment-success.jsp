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
        <title>Payment Processing</title>
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
                        <a class="nav-link" href="showMyProfile">My Profile</a>
                        <a class="nav-link active" href="showMyPaytment">My Apartment</a>
                        <a class="nav-link" href="maintenance-request">Maintenance Request</a>
                        <a class="nav-link" href="logout">Logout</a>
                    </nav>
                </div>
                
                <h3> Payment is done successfully </h3><!-- comment -->
                <br/><br/>
                <a href="showMyPaytment"> Go Back </a>
            </div>
        </div>
    </body>
</html>
