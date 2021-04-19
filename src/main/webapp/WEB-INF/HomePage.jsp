<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome</title>
	  
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
 <p><form:errors path="user.*"/></p>
<h2 class="text-info mt-5">Welcome <c:out value="${user.firstName}" />!</h2>
<p class="text-success"><c:out value="${success}" /></p>
	
	<a class ="btn btn-danger float-right mt-3" href="/logout">Logout</a></div>
	
</body>
</html>
