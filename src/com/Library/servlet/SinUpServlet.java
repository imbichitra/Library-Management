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


public class SinUpServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String uname = req.getParameter("uname");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("cpassword");
		PrintWriter out=res.getWriter();
		Connection connection = JdbcConnectionFactory.getDBConnection();
		PreparedStatement preparedstatement=null;
		res.setContentType("text/html");
		if(password.equals(cpassword)){
			String query = "select count(*) from liblogin where username=?";
			try {
				preparedstatement=connection.prepareStatement(query);
				preparedstatement.setString(1, uname);
				
				ResultSet resultSete=preparedstatement.executeQuery();
				if(resultSete.next()){
					int i=resultSete.getInt(1);
					if(i==1){
						
						req.setAttribute("errorMessage", "Same user name already availabale !");
						req.getRequestDispatcher("SinUp.jsp").include(req, res); 
					}
					else{
						query = "insert into liblogin values(?,?)";
						preparedstatement=connection.prepareStatement(query);
						preparedstatement.setString(1, uname);
						preparedstatement.setString(2, password);
						int ii=preparedstatement.executeUpdate();
						System.out.println("i=="+ii);
						if(ii==1){
							req.getRequestDispatcher("LogIn.jsp").forward(req, res);
						}
						else{
							
							req.setAttribute("errorMessage", "Data not inserted !");
							req.getRequestDispatcher("SinUp.jsp").include(req, res); 
						}
					}
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			
			 req.setAttribute("errorMessage", "password and Conform Password not same ");
			 req.getRequestDispatcher("SinUp.jsp").include(req, res);
		}
	}
}
