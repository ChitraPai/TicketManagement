<html>
<head>
<title>
Update ticket
</title>
<body>
<h3> Update Ticket </h3>
<form action="../tickets/ticketupdation" method=get">
<input type="hidden" name="emailId" value="${emailId}">
Ticket Id:&nbsp&nbsp<input type="text" placeholder="Ticket Id"name="ticketId" required autofocus><br><br><br>
Description:&nbsp&nbsp<input type="text" placeholder="Description"name="description" required autofocus><br><br><br>
<input type="submit"  name="Update" value="Update"><br><br><br>
</form>
</body>
</html>

