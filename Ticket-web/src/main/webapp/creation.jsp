<html>
<head>
<title>
ticket creation
</title>
<body>
<h3> Ticket Options </h3>
<form  action="../tickets/creationg" method="GET">
<input type="hidden" name="emailId" value="${emailId}">
Subject:&nbsp&nbsp<input type="text" placeholder="Subject" name="subject" required autofocus><br><br><br>
Description:&nbsp&nbsp<input type="text" placeholder="Description"name="description" required autofocus><br><br><br>
Department:&nbsp&nbsp<input type="text" placeholder="Department" name="departmentName" required autofocus><br><br><br>
Priority:&nbsp&nbsp<input type="text" placeholder="Priority" name="priority" required autofocus><br><br><br>
<input type="submit"  name="submit" value="Submit" onclick="window.location.href='../index.jsp'">
</form>
</html>