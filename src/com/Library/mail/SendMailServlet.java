package com.Library.mail;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
  
public class SendMailServlet extends HttpServlet {  
public void doPost(HttpServletRequest request,  
 HttpServletResponse response)  
    throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
      
    String to=request.getParameter("to");  
    String subject="Return your book"; 
    String msg="You have so late to return library book";  
          System.out.println(to);
    Mailer.send(to, subject, msg);  
    out.print("message has been sent successfully");  
    out.close();  
    }  
  
}  