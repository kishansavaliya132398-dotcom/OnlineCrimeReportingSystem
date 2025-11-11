package com.ocr.controller;
import com.ocr.util.DBConnection;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT id,password,name FROM users WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String stored = rs.getString("password");
                // If you use BCrypt, verify here. This demo compares plain text.
                if(password.equals(stored)){
                    HttpSession session = req.getSession();
                    session.setAttribute("userId", rs.getInt("id"));
                    session.setAttribute("userName", rs.getString("name"));
                    resp.sendRedirect("index.jsp");
                    return;
                }
            }
        } catch(Exception e){ e.printStackTrace(); }
        resp.sendRedirect("login.jsp?error=1");
    }
}
