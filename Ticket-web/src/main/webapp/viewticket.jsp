%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>
View tickets
</title>
<body>
<h3> View Tickets </h3>
<form action="../tickets/viewtickets" method="GET">
Ticket Id:&nbsp&nbsp<input type="text" placeholder="Ticket Id"name="ticketId" required autofocus><br><br><br>
	<button type="submit"><h4>Find My Tickets</h4></button>
	</form>
	<h3>User Details</h3>
	<%-- 	To Test Values : ${User_Details} --%>
	<table border="1">
		<thead>
			<tr>
				<th>id</th>
				<th>User_id</th>
				<th>subject</th>
				<th>description</th>
				<th>status</th>
				<th>priority</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${list}" varStatus="i">
				<tr>
					<td>${c.id}</td>
					<td>${c.userId.getId()}</td>
					<td>${c.subject}</td>
					<td>${c.description}</td>
					<td>${c.status}</td>
					<td>${c.priority}</td>
					<td>Edit</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</body>
</html>