<html>
<head>
<title>
Resolve Ticket
</title>
<body>
<h3> Resolve Ticket </h3>
<form action="../tickets/resolveticket" method=get">
<input type="hidden" name="emailId" value="${emailId}">
Ticket Id:&nbsp&nbsp<input type="text" placeholder="Ticket Id"name="ticketId" required autofocus><br><br><br>
Solution:&nbsp&nbsp<input type="text" placeholder="Solution"name="solution" required autofocus><br><br><br>
<input type="submit"  name="Resolve" value="Resolve"><br><br><br>
</form>
</body>
</html>

