<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link rel="icon" type="image/jpg" href="library.jpg" />
</head>
<body background="medical-group.jpg">
	<div class="container">
	<img src="user-icon-6.png">
		<form action="loginservlet" method="post">
		<% 
			if (null != request.getAttribute("errorMessage")) {
				out.print("<label style='color:red'><h4>"+request.getAttribute("errorMessage")+"</h4></label>");
			}
		%>
			<div class="form-input">
			<input type="text" name="uname" placeholder="Enter Username" required/>
			</div>
			<div class="form-input">
			<input type="password" name="password" placeholder="Enter Password" required/>
			</div>
			<input type="submit" value="Login" class="s"/></br>
			
			
			<h2><a href="SinUp.jsp">Sign Up</a></h2>
		</form>

	</div>
</body>
</html>