package com.ocr.controller;
import com.ocr.util.DBConnection;
import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Part;
@WebServlet("/ComplaintServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*1, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*15)
public class ComplaintServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session==null || session.getAttribute("userId")==null){
            resp.sendRedirect("login.jsp"); return;
        }
        int userId = (Integer)session.getAttribute("userId");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Part filePart = req.getPart("evidence");
        String fileName = null;
        if(filePart!=null && filePart.getSize()>0){
            String uploadsDir = getServletContext().getRealPath("") + File.separator + "evidence";
            new File(uploadsDir).mkdirs();
            fileName = System.currentTimeMillis() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            filePart.write(uploadsDir + File.separator + fileName);
        }
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO complaints (user_id,title,description,evidence_path) VALUES (?,?,?,?)");
            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, fileName==null?null:("evidence/"+fileName));
            ps.executeUpdate();
            resp.sendRedirect("trackStatus.jsp?msg=submitted");
        } catch(Exception e){ e.printStackTrace(); resp.sendRedirect("newComplaint.jsp?error=true"); }
    }
}
