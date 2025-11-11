<%@ page import="java.sql.*,com.ocr.util.DBConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head><title>Track Status</title></head><body>
<h2>Your Complaints</h2>
<%
  Object idObj = session.getAttribute("userId");
  if(idObj==null){
%>
  <p>Please <a href="login.jsp">login</a> to view your complaints.</p>
<% } else {
    int userId = (Integer)idObj;
    try(Connection con = DBConnection.getConnection()){
      PreparedStatement ps = con.prepareStatement("SELECT id,title,status,created_at,evidence_path FROM complaints WHERE user_id=? ORDER BY created_at DESC");
      ps.setInt(1, userId);
      ResultSet rs = ps.executeQuery();
%>
  <table border="1">
    <tr><th>ID</th><th>Title</th><th>Status</th><th>Created</th><th>Evidence</th></tr>
    <% while(rs.next()){ %>
      <tr>
        <td><%=rs.getInt("id")%></td>
        <td><%=rs.getString("title")%></td>
        <td><%=rs.getString("status")%></td>
        <td><%=rs.getTimestamp("created_at")%></td>
        <td>
          <% String p = rs.getString("evidence_path"); if(p!=null){ %>
            <a href="<%=request.getContextPath()+"/"+p%>" target="_blank">View</a>
          <% } else { %> - <% } %>
        </td>
      </tr>
    <% } %>
  </table>
<% } catch(Exception e){ out.println(e);} } %>
</body></html>
