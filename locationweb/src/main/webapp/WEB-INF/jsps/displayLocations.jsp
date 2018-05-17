<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Locations</title>
</head>
<body>
	<b>Locations</b>
			<table>
				<tr>
					<th>ID</th>
					<th>Code</th>
					<th>Name</th>
					<th>Type</th>
				</tr>
				<c:forEach items="${locations}" var="location">
					<tr>
						<td>${location.id}</td>
						<td>${location.code}</td>
						<td>${location.name}</td>
						<td>${location.type}</td>
						<td><a href="updateLocation?id=${location.id}">edit</a>
						<td><a href="deleteLocation?id=${location.id}">delete</a>
					</tr>
				</c:forEach>
			</table>
			<a href="showCreate">Add location</a>
</body>
</html>