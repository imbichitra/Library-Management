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

public class LogInServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String uname = req.getParameter("uname");
		String password = req.getParameter("password");
		PrintWriter out=res.getWriter();
		Connection connection = JdbcConnectionFactory.getDBConnection();
		PreparedStatement preparedstatement=null;
		String query = "select count(*) from liblogin where username=? and password=?";
			try {
				preparedstatement=connection.prepareStatement(query);
				preparedstatement.setString(1, uname);
				preparedstatement.setString(2, password);
				ResultSet resultSete=preparedstatement.executeQuery();
				if(resultSete.next()){
					int i=resultSete.getInt(1);
					if(i==1){
						res.sendRedirect("Home.jsp");
					}
					else{
						res.setContentType("text/html");  
						req.setAttribute("errorMessage", "Invalid user name and password");
						req.getRequestDispatcher("LogIn.jsp").include(req, res); 
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
