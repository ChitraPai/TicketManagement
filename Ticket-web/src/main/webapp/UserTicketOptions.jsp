<html>
<head>
<title>
ticket options
</title>
<body>
<h3> Ticket Options </h3>
<form  action="../tickets/creation" method="GET">
<input type="hidden" name="emailId" value="${emailId }">
<input type="submit" name="ticketCreation" value="Create Ticket"><br><br><br>
</form>
<%-- <form action="../tickets/updation" method="GET">
<input type="hidden" name="emailId" value="${emailId}">
<input type="submit"  name="ticketUpdation" value="Update Ticket"><br><br><br>
</form>
<form action="../tickets/close" method="GET" >
<input type="hidden" name="emailId" value="${emailId}">
<input type="submit"  name="closeTicket" value="Close Ticket"><br><br><br>
</form> --%>
<form action="../tickets/view" method="GET">
<input type="hidden" name="emailId" value="${emailId}">
<input type="submit"  name="viewTicket" value="View Ticket"><br><br><br>
</form>
</body>
</html>