<%@page import="java.time.LocalDate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>
View Assigned Tickets
</title>
<body>
<h3> View Assigned Tickets </h3>
	<table border="1">
		<thead>
			<tr>
				<th>Ticket Id</th>
				<th>Subject</th>
				<th>Description</th>
				<th>Created Date</th>
				<th>Resolved Date</th>
				<th>Status</th>
				<th>Reassign</th>
				<th>Resolve</th>
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
					<td><a href="../tickets/reassign?ticketId=${c.id}">Reassign</a></td>
					<td><a href="../tickets/resolve?ticketId=${c.id}">Resolve</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</body>
</html>