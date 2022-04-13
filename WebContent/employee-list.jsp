<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management Application</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
        	 <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="#" class="navbar-brand"> Employee Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Employees</a></li>
                        <li><a href="<%=request.getContextPath()%>/new" class="nav-link">Add New Employee</a></li>
                    </ul>
                </nav>
            </header>
        	<div align="center" class="container">
		            <h4 class="text-center">List of Employees</h4>
		        <table border="1" class="table table-bordered">
		            <tr>
		                <th>ID</th>
		                <th>First Name</th>
		                <th>Last Name</th>
		                <th>Email</th>
		                <th>Actions</th>
		            </tr>
		            <c:forEach var="employee" items="${listEmployee}">
		                <tr>
		                    <td><c:out value="${employee.id}" /></td>
		                    <td><c:out value="${employee.firstName}" /></td>
		                    <td><c:out value="${employee.lastName}" /></td>
		                    <td><c:out value="${employee.email}" /></td>
		                    <td>
		                    	<a href="edit?id=<c:out value='${employee.id}' />">Edit</a>
		                    	&nbsp;&nbsp;&nbsp;&nbsp;
		                    	<a href="delete?id=<c:out value='${employee.id}' />">Delete</a>                    	
		                    </td>
		                </tr>
		            </c:forEach>
		        </table>
		    </div>	
</body>
</html>