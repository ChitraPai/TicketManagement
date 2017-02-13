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
				</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${list}">
				<tr>
					<td>${c.id}</td>
					<td>${c.subject}</td>
					<td>${c.description}</td>
					<td>${c.createdDate }</td>
					<td>${c.resolvedDate}</td>
					<td>${c.status}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</body>
</html>