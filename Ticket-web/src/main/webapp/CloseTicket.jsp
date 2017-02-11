<html>
<head>
<title>
Close ticket
</title>
<body>
<h3> Close Ticket </h3>
<form action="../tickets/closeTicket" method=get">
<input type="hidden" name="emailId" value="${emailId}">
Ticket Id:&nbsp&nbsp<input type="text" placeholder="Ticket Id"name="ticketId" required autofocus><br><br><br>
<input type="submit"  name="Close Ticket" value="Close Ticket"><br><br><br>
</form>
</body>
</html>
