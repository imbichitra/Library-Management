package com.Library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.jdbc.connection.JdbcConnectionFactory;

public class AddFacultyServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String fid = req.getParameter("fid");
		PrintWriter out=res.getWriter();
		Connection connection = JdbcConnectionFactory.getDBConnection();
		PreparedStatement preparedstatement=null;
		res.setContentType("text/html");
		String query = "select count(*) from libaddfaculty where facultyid=?";
		try {
			preparedstatement=connection.prepareStatement(query);
			preparedstatement.setString(1, fid);
			ResultSet resultSete=preparedstatement.executeQuery();
			if(resultSete.next()){
				int i=resultSete.getInt(1);
				if(i==1){
					req.setAttribute("errorMessage", "That Reg No is taken. Try another");
					req.getRequestDispatcher("AddFaculty.jsp").include(req, res); 
				}
				else{
					query = "insert into libaddfaculty values(?,?,?,?)";
					preparedstatement=connection.prepareStatement(query);
					preparedstatement.setString(1, req.getParameter("fid"));
					preparedstatement.setString(2, req.getParameter("name"));
					preparedstatement.setString(3,  req.getParameter("Designation"));
					preparedstatement.setString(4, req.getParameter("gender"));
					int ii=preparedstatement.executeUpdate();
					if(ii==1){
						req.setAttribute("Message", "Faculty registered Succfully");
						req.getRequestDispatcher("AddFaculty.jsp").include(req, res); 
					}
					else{
						
						req.setAttribute("errorMessage", "Data not inserted !");
						req.getRequestDispatcher("AddFaculty.jsp").include(req, res); 
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
