package com.Library.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Library.jdbc.connection.JdbcConnectionFactory;

public class Validation extends HttpServlet{
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
					//book no available
					query = "select * from  libaddbook where bookid=?";
					preparedstatement=connection.prepareStatement(query);
					preparedstatement.setString(1, (String)session.getAttribute("bookid"));
					resultSete=preparedstatement.executeQuery();
					if(resultSete.next()){
						i=resultSete.getInt(5);
						if(i==1){
							//check id available in issue section
							query = "select count(*) from  libbookissue where id=?";
							preparedstatement=connection.prepareStatement(query);
							preparedstatement.setString(1, (String)session.getAttribute("id"));
							resultSete=preparedstatement.executeQuery();
							if(resultSete.next()){
								i=resultSete.getInt(1);
								if(i==0){
									int a=1;
									query = "insert into libbookissue values(?,?,?,?,?)";
									preparedstatement=connection.prepareStatement(query);
									preparedstatement.setString(1, (String)session.getAttribute("id"));
									preparedstatement.setString(2, (String)session.getAttribute("bookid"));
									
									Date date1= null;
									try{
										date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String)session.getAttribute("issuedate"));
										preparedstatement.setString(3, new SimpleDateFormat("dd/MMM/yyyy").format(date1));
										date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String)session.getAttribute("returndate"));
										preparedstatement.setString(4, new SimpleDateFormat("dd/MMM/yyyy").format(date1));
									}
									catch( Exception e){
										e.printStackTrace();
									}
									preparedstatement.setInt(5,a);
									int ii=preparedstatement.executeUpdate();
									if(ii==1){
										query = "update libaddbook set no=0 where bookid=?";
										preparedstatement=connection.prepareStatement(query);
										preparedstatement.setString(1, (String)session.getAttribute("bookid"));
										int ii1=preparedstatement.executeUpdate();
										if(ii1==1){
											req.setAttribute("Message", "Book issued");
											req.getRequestDispatcher("BookIssue.jsp").include(req, res); 
										}
									}
								}
								else if(i==1){
									int a=1;
									query = "insert into libbookissue values(?,?,?,?,?)";
									preparedstatement=connection.prepareStatement(query);
									preparedstatement.setString(1, (String)session.getAttribute("id"));
									preparedstatement.setString(2, (String)session.getAttribute("bookid"));
									Date date1= null;
									try{
										date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String)session.getAttribute("issuedate"));
										preparedstatement.setString(3, new SimpleDateFormat("dd/MMM/yyyy").format(date1));
										date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse((String)session.getAttribute("returndate"));
										preparedstatement.setString(4, new SimpleDateFormat("dd/MMM/yyyy").format(date1));
									}
									catch( Exception e){
										e.printStackTrace();
									}
									preparedstatement.setInt(5,a);
									int ii=preparedstatement.executeUpdate();
									if(ii==1){
										query = "update libaddbook set no=0 where bookid=?";
										preparedstatement=connection.prepareStatement(query);
										preparedstatement.setString(1, req.getParameter("bookid"));
										int ii1=preparedstatement.executeUpdate();
										if(ii1==1){
											req.setAttribute("Message", "Book issued");
											req.getRequestDispatcher("BookIssue.jsp").include(req, res); 
										}
									}
								}
								else if(i==2){
									req.setAttribute("errorMessage", "Already 2 Book taken !");
									req.getRequestDispatcher("BookIssue.jsp").include(req, res);
								}
							}
						}
						else{
							req.setAttribute("errorMessage", "Book not available !");
							req.getRequestDispatcher("BookIssue.jsp").include(req, res);
						}
					}
				}
				else{
					req.setAttribute("errorMessage", "Book ID not available !");
					req.getRequestDispatcher("BookIssue.jsp").include(req, res);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			req.setAttribute("errorMessage", "Book ID not available !");
			req.getRequestDispatcher("BookIssue.jsp").include(req, res);
		}
	}
	
}
