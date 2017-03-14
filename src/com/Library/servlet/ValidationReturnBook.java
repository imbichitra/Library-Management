package com.Library.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Library.jdbc.connection.JdbcConnectionFactory;

public class ValidationReturnBook extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Connection connection = JdbcConnectionFactory.getDBConnection();
		PreparedStatement preparedstatement=null;
		HttpSession session=req.getSession(false);  
		try{
			//check book id available
			String query = "select count(*) from  libaddbook where bookid=?";
			preparedstatement=connection.prepareStatement(query);
			preparedstatement.setString(1, (String)session.getAttribute("bookid"));
			ResultSet resultSete=preparedstatement.executeQuery();
			
			if(resultSete.next()){
				int i=resultSete.getInt(1);
				if(i==1){
					query = "delete from libbookissue where id=? and bookid=?";
					preparedstatement=connection.prepareStatement(query);
					preparedstatement.setString(1, (String)session.getAttribute("id"));
					preparedstatement.setString(2, (String)session.getAttribute("bookid"));
					i=preparedstatement.executeUpdate();
					if(i==1){
						connection.commit();
						
						
						query = "update libaddbook set no=1 where bookid=?";
						preparedstatement=connection.prepareStatement(query);
						preparedstatement.setString(1, (String)session.getAttribute("bookid"));
						int ii1=preparedstatement.executeUpdate();
						if(ii1==1){
							req.setAttribute("Message", "Book Return succfully !");
							req.getRequestDispatcher("BookReturn.jsp").include(req, res); 
						}
						
						
					}
					else{
						req.setAttribute("errorMessage", "Book not Returned !");
						req.getRequestDispatcher("BookReturn.jsp").include(req, res);
					}
				}
				else{
					req.setAttribute("errorMessage", "Book ID not available !");
					req.getRequestDispatcher("BookReturn.jsp").include(req, res);
				}
			}
			
		}
		catch(Exception e){
			//e.printStackTrace();
			req.setAttribute("errorMessage", "Book ID not available !");
			req.getRequestDispatcher("BookReturn.jsp").include(req, res);
		}
	}

}
