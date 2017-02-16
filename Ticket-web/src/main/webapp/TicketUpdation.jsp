<html>
<head>
<title>
Update ticket
</title>
<body>
<h3> Update Ticket </h3>
<form action="../tickets/updateticket" method=get">
<input type="hidden" name="ticketId" value="${ticketId}">
Description:&nbsp&nbsp<input type="text" placeholder="Description"name="description" required autofocus><br><br><br>
<input type="submit"  name="Update" value="Update"><br><br><br>
</form>
</body>
</html>

