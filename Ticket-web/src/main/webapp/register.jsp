<html>
<title>
register
</title>
<body>
<h3>Registration</h3>
<form action="home/register" method="GET">
Name:&nbsp&nbsp<input type="text" placeholder="Name" name="name" required autofocus><br><br>
Email:&nbsp&nbsp<input type="text" placeholder="Email" name="emailId" required autofocus><br><br><br>
Password:&nbsp&nbsp<input type="password" placeholder="Password"name="password" required autofocus><br><br><br>
${ERROR}<br><br>
<input type="submit" name="register" value="Register"><br><br>
</form>
</body>
</html>