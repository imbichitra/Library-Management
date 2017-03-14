package com.Library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Library.jdbc.connection.JdbcConnectionFactory;

public class AddStudentServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String regno = req.getParameter("regno");
		PrintWriter out=res.getWriter();
		Connection connection = JdbcConnectionFactory.getDBConnection();
		PreparedStatement preparedstatement=null;
		res.setContentType("text/html");
		String query = "select count(*) from libaddstudent where regno=?";
		try {
			preparedstatement=connection.prepareStatement(query);
			preparedstatement.setString(1, regno);
			ResultSet resultSete=preparedstatement.executeQuery();
			if(resultSete.next()){
				int i=resultSete.getInt(1);
				if(i==1){
					req.setAttribute("errorMessage", "That Reg No is taken. Try another");
					req.getRequestDispatcher("AddStudent.jsp").include(req, res); 
				}
				else{
					query = "insert into libaddstudent values(?,?,?,?,?,?,?)";
					preparedstatement=connection.prepareStatement(query);
					preparedstatement.setString(1, req.getParameter("regno"));
					preparedstatement.setString(2, req.getParameter("name"));
					preparedstatement.setString(3,  req.getParameter("branch"));
					preparedstatement.setString(4, req.getParameter("gender"));
					Date date1= null;
					try{
						date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthday"));
					}
					catch( Exception e){
						e.printStackTrace();
					}
					
					preparedstatement.setString(5, new SimpleDateFormat("dd/MMM/yyyy").format(date1));
					String s=req.getParameter("birthday");
					System.out.println(s);
					
					
					preparedstatement.setString(6, req.getParameter("address"));
					preparedstatement.setString(7, req.getParameter("mail"));
			
					int ii=preparedstatement.executeUpdate();
					if(ii==1){
						req.setAttribute("Message", "Student registered Succfully");
						req.getRequestDispatcher("AddStudent.jsp").include(req, res); 
					}
					else{
						
						req.setAttribute("errorMessage", "Data not inserted !");
						req.getRequestDispatcher("AddStudent.jsp").include(req, res); 
					}					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
