<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head><title>Register</title></head><body>
<h2>Register</h2>
<form action="RegisterServlet" method="post">
  Name: <input name="name" required><br>
  Email: <input type="email" name="email" required><br>
  Phone: <input name="phone"><br>
  Password: <input type="password" name="password" required><br>
  <button type="submit">Register</button>
</form>
</body></html>
