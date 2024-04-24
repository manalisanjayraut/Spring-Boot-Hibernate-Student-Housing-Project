<%-- 
    Document   : Login
    Created on : Mar 22, 2024, 7:23:41 PM
    Author     : manal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Page</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    </head>

    <body>
        
        <c:out value="${message}" />
        <br/>
        <br/>
        <a href="login">Please Login/Register </a>
    </body>
</html>

