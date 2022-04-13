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
		<c:if test="${employee != null}">
		<form action="update">
        </c:if>
        <c:if test="${employee == null}">
        <form action="insert">
        </c:if>
            <h4>
            		<c:if test="${employee != null}">
            			Edit Employee
            		</c:if>
            		<c:if test="${employee == null}">
            			Add New Employee
            		</c:if>
            </h4>
        		<c:if test="${employee != null}">
        			<input type="hidden" name="id" value="<c:out value='${employee.id}' />" />
        		</c:if>            
            <div class="mb-3">
            	<label for="exampleInputEmail1" class="form-label">First Name</label>
            	<input type="text" class="form-control" name="first_name" size="45" value="<c:out value='${employee.firstName}' />" />
            </div>
            <div class="mb-3">
            	<label for="exampleInputEmail1" class="form-label">First Name</label>
            	<input type="text" class="form-control" name="last_name" size="45" value="<c:out value='${employee.lastName}' />" />
            </div>
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Email</label>
            	<input type="email" class="form-control" name="email" size="45" value="<c:out value='${employee.email}' />" />
            </div>
            <button type="submit" class="btn btn-success">Save</button>
            </form>
   </div>	
</body>
</html>