<html>
<head>
<title>
Reassign Ticket
</title>
<body>
<h3> Reassign Ticket </h3>
<form action="../tickets/reassigntickets" method=get">
<input type="hidden" name="emailId" value="${emailId}">
Ticket Id:&nbsp&nbsp<input type="text" placeholder="Ticket Id"name="ticketId" required autofocus><br><br><br>
Employee Id:&nbsp&nbsp<input type="text" placeholder="Employee Id"name="employeeId" required autofocus><br><br><br>
<input type="submit"  name="Reassign" value="Reassign"><br><br><br>
</form>
</body>
</html>

