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
import javax.servlet.http.HttpSession;

import com.Library.jdbc.connection.JdbcConnectionFactory;

public class BookIssueServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String id =	req.getParameter("id");
		String bookid = req.getParameter("bookid");
		
		HttpSession session=req.getSession();  
		session.setAttribute("id",id);  
		session.setAttribute("bookid",bookid);
		
		session.setAttribute("issuedate",req.getParameter("issuedate"));
		session.setAttribute("returndate",req.getParameter("returndate"));

		System.out.println("bookid="+bookid);
		PrintWriter out=res.getWriter();
		Connection connection = JdbcConnectionFactory.getDBConnection();
		PreparedStatement preparedstatement=null;
		res.setContentType("text/html");
		//check for student availability
		String query = "select count(*) from  libaddstudent where regno=?";
		try {
			preparedstatement=connection.prepareStatement(query);
			preparedstatement.setString(1, id);
			ResultSet resultSete=preparedstatement.executeQuery();
			if(resultSete.next()){
				int i=resultSete.getInt(1);
				if(i==1){
					//call method
					req.getRequestDispatcher("validation").include(req, res); 
				}
				else{
					query = "select count(*) from  libaddfaculty where facultyid=?";
					preparedstatement=connection.prepareStatement(query);
					preparedstatement.setString(1, id);
					resultSete=preparedstatement.executeQuery();
					if(resultSete.next()){
						i=resultSete.getInt(1);
						if(i==1){
							//call method
							req.getRequestDispatcher("validation").include(req, res); 
						}
						else{
							req.setAttribute("errorMessage", "Not a valid ID !");
							req.getRequestDispatcher("BookIssue.jsp").include(req, res); 
						}
					}
				}
			}
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
}
