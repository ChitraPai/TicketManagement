<html>
<title>
login
</title>
<body>
<h3>Login</h3>
<form action="home/employeelogin" method="POST">
Email:&nbsp&nbsp<input type="text" placeholder="Email" name="emailId" required autofocus><br><br><br>
Password:&nbsp&nbsp<input type="password" placeholder="Password"name="password" required autofocus><br><br><br>
${ERROR}<br><br>
<input type="submit" name="login" value="LOGIN"><br><br>
</form>
</body>
</html>
