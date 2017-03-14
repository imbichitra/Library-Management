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

public class BookEntryServlet extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String bookid = req.getParameter("bookid");
		PrintWriter out=res.getWriter();
		Connection connection = JdbcConnectionFactory.getDBConnection();
		PreparedStatement preparedstatement=null;
		res.setContentType("text/html");
		String query = "select count(*) from libaddbook where bookid=?";
		try {
			preparedstatement=connection.prepareStatement(query);
			preparedstatement.setString(1, bookid);
			ResultSet resultSete=preparedstatement.executeQuery();
			if(resultSete.next()){
				int i=resultSete.getInt(1);
				if(i==1){
					req.setAttribute("errorMessage", "Book is regestered");
					req.getRequestDispatcher("BookEntry.jsp").include(req, res); 
				}
				else{
					int a=1;
					query = "insert into libaddbook values(?,?,?,?,?)";
					preparedstatement=connection.prepareStatement(query);
					preparedstatement.setString(1, req.getParameter("bookid"));
					preparedstatement.setString(2, req.getParameter("booktitle"));
					preparedstatement.setString(3,  req.getParameter("authorname"));
					preparedstatement.setString(4, req.getParameter("publisher"));
					preparedstatement.setInt(5, a);
					int ii=preparedstatement.executeUpdate();
					if(ii==1){
						req.setAttribute("Message", "Book Add to library");
						req.getRequestDispatcher("BookEntry.jsp").include(req, res); 
					}
					else{
						
						req.setAttribute("errorMessage", "Book not inserted !");
						req.getRequestDispatcher("BookEntry.jsp").include(req, res); 
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
