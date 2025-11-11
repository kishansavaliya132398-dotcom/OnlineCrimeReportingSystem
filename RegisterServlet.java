package com.ocr.controller;
import com.ocr.util.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// NOTE: For password hashing, add jBCrypt jar and uncomment BCrypt usage below.
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        // TODO: Hash password (use BCrypt). This stores plain text for demo only.
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (name,email,phone,password) VALUES (?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, password);
            ps.executeUpdate();
            resp.sendRedirect("login.jsp?msg=registered");
        } catch(Exception e) {
            e.printStackTrace();
            resp.sendRedirect("register.jsp?error=true");
        }
    }
}
