<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%
    ResultSet resultset = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
create ticket
</title>
<body>
<%
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/ticket_db?user=root&password=chitra16");
        
            Statement statement = connection.createStatement();

            resultset = statement.executeQuery("SELECT * FROM DEPARTMENTS");
    %>
<h3> Create Tickets </h3>
<form action="../tickets/createtickets" method="GET">
<input type="hidden" name="emailId" value="${emailId}">
Subject:&nbsp&nbsp<input type="text" placeholder="Subject" name="subject" required autofocus><br><br><br>
Description:&nbsp&nbsp<input type="text" placeholder="Description"name="description" required autofocus><br><br><br>
 Department:
        <select name="departmentName">
        <%  while(resultset.next()){ %>
            <option value="<%= resultset.getString(2)%>"> <%= resultset.getString(2)%></option>
        <% } %>
        </select> 
            <br><br><br>
            Priority:<input type="radio" name="priority" value="Low">Low&nbsp<input type="radio" name="priority" value="Medium">Medium<input type="radio" name="priority" value="High">High<br><br><br>
            
<input type="submit"  name="submit" value="Submit"><br><br><br>
</form>
<%
        } catch (Exception e) {
            out.println("wrong entry" + e);
        }
    %>
    </body>
</html>