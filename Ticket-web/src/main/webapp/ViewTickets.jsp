<%@page import="java.time.LocalDate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>
View tickets
</title>
<body>
<h3> View Tickets </h3>
	<table border="1">
		<thead>
			<tr>
				<th>Ticket Id</th>
				<th>Subject</th>
				<th>Description</th>
				<th>Created Date</th>
				<th>Resolved Date</th>
				<th>Status</th>
				<th>Update Ticket</th>
				<th>Close Ticket</th>
				</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${list}">
				<tr>
					<td>${c.id}</td>
					<td>${c.subject}</td>
					<td>${c.description}</td>
					<td>${c.createdDate.toLocalDate()}</td>
					<td>${c.resolvedDate.toLocalDate()}</td>
					<td>${c.status}</td>
					<td><a href="../tickets/update?ticketId=${c.id}">Update</a></td>
					<td><a href="../tickets/closeticket?ticketId=${c.id}">Close</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</body>
</html>