<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*,java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./home.css">
	<link rel="icon" type="image/jpg" href="./library.jpg" />
	<style type="text/css">
		input[type="text"]{
			width: 160px;
			height: 25px;
			font-size: 18px;
			background-color: #fff;
			margin-left:300px;
			margin-top:20px;
		}
		td{
			padding: 5px;
		}
		article{
			background: rgb(98, 157, 118);
			height:150px;
			
		}
		h2{
			text-align: center;
			color: rgb(123,12,123);
			background: rgb(100,203,234);
			padding: 10px;
		}
		.main{
			width: 750px;
			height: 600px;
			margin: 10px auto;
			margin-top: 30px;
		}
		table{
			margin-left: 10px;
		}
		#main{
			
			background-image: url("group-children-book.jpg");
			
		}
		#sub{
			height:30px;
			width:50px;
		}
	</style>
</head>
<body>
	<div id="main">
		<header id="top_header"><h1><marquee behavior="alternate"> WELCOME TO LIBRARY MANAGEMENT SYSTEM</marquee></h1></header>
		<nav id="top_manu">
			<ul>
				<li><a href="Home.jsp">HOME</a></li>
				<li><a href="#">ABOUT</a></li>
				<li class="dropdown">
					<a href="#" class="dropbtn">ADD MEMBER</a>
					<div class="dropdown-content">
						<a href="AddStudent.jsp">STUDENT</a>
						<a href="AddFaculty.jsp">FACULTY</a>
					</div>
				</li>
				<li><a href="BookEntry.jsp">ADD BOOK</a></li>
				<li><a href="BookIssue.jsp">ISSUE BOOK</a></li>
				<li><a href="BookReturn.jsp">RETURN BOOK</a></li>
				<li class="dropdown">
					<a href="#" class="dropbtn">VIEW</a>
					<div class="dropdown-content">
						<a href="StudentView.jsp">STUDENT</a>
						<a href="BookView.jsp">BOOK</a>
						<a href="FacultyView.jsp">FACULTY</a>
						<a href="IssueView.jsp">ISSUE DETAIL</a>
					</div>
				</li>
				
				<li class="dropdown">
					<a href="#" class="dropbtn">REMOVE</a>
					<div class="dropdown-content">
						<a href="RemoveStudent.jsp">STUDENT</a>
						<a href="RemoveBook.jsp">BOOK</a>
						<a href="RemoveFaculty.jsp">FACULTY</a>
					</div>
				</li>
			</ul>
		</nav>
		<div class="main">
		<article>
			<header>
				<h2>BOOK</h2>
				<%
		if(request.getParameter("remove")!=null)
		{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			PreparedStatement pre=con.prepareStatement("select count(*) from  libaddbook where bookid=?");
			pre.setString(1, request.getParameter("id"));
			ResultSet res=pre.executeQuery();
			if(res.next()){
				int i=res.getInt(1);
				if(i==1){
					pre=con.prepareStatement("delete from libaddbook where bookid=?");
					pre.setString(1, request.getParameter("id"));
					i=pre.executeUpdate();
					if(i==1){
						con.commit();
						out.print("<label style='color:green'><h4>"+"delete"+"</h4></label>");
					}
				}
				else{
					out.print("<label style='color:red' 'text-align:center'><h4>"+"Invalid ID"+"</h4></label>");
				
				}
			}
		}
		catch(Exception e){
			out.print("<label style='color:red'><h4>Invalid ID</h4></label>");
		}
		}
		%>
			</header>
			<form action="RemoveBook.jsp">
		<input type="text" name="id" placeholder="Enter Id" required>
		<input type="submit" value="remove" name="remove" id="sub">
		
			</form>
		</article>
		</div>
	</div>
</body>
</html>