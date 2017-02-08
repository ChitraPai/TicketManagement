<html>
<head>
<title>
ticket options
</title>
<body>
<h3> Ticket Options </h3>
<form  action="tickets/creation" method="GET">
<input type="hidden" name="emailId" value={$emailId}>
<input type="button"  name="ticketCreation" value="Create Ticket" onclick="window.location.href='ticketcreation.jsp'"><br><br><br>
</form>
<form action="tickets/updation" method="GET">
<input type="hidden" name="emailId" value={$emailId}>
<input type="button"  name="ticketUpdation" value="Update Ticket" onclick="window.location.href='ticketupdation.jsp'"><br><br><br>
</form>
<form action="tickets/close" method="GET" >
<input type="hidden" name="emailId" value={$emailId}>
<input type="button"  name="closeTicket" value="Close Ticket" onclick="window.location.href='closeticket.jsp'"><br><br><br>
</form>
<form action="tickets/close" method="GET">
<input type="hidden" name="emailId" value={$emailId}>
<input type="button"  name="viewTicket" value="View Ticket" onclick="window.location.href='viewticket.jsp'"><br><br><br>
</form>
</body>
</html>