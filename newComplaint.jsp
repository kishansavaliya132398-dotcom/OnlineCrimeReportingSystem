<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head><title>File Complaint</title></head><body>
<h2>File Complaint</h2>
<form action="ComplaintServlet" method="post" enctype="multipart/form-data">
  Title: <input name="title" required><br>
  Description:<br><textarea name="description" rows="6" cols="50"></textarea><br>
  Evidence (image/pdf): <input type="file" name="evidence"><br>
  <button type="submit">Submit</button>
</form>
</body></html>
