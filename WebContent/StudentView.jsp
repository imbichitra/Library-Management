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
		input[type="text"],input[type="mail"],input[type="date"]{
			width: 160px;
			height: 25px;
			font-size: 18px;
			background-color: #fff;
		}
		td{
			padding: 5px;
		}
		article{
			background: rgb(98, 157, 118);

		}
		h2{
			text-align: center;
			color: rgb(123,12,123);
			background: rgb(100,203,234);
			padding: 10px;
		}
		.main{
			width: 850px;
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
		tr:nth-child(even){background-color:rgb(102, 102, 102)}
		tr:nth-child(odd){background-color:rgb(153, 153, 255)}
		th{background-color: rgb(255, 0, 102)}
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
				<h2>STUDENT DETAIL</h2>
			</header>
			<form>
		
		<table style="border: 1px solid black;border-collapse: collapse; " width="830" border="1" cellpadding="4" >
		<tr style="border: 1px solid black">
			<th style="border: 1px solid black">REGNO</th>
			<th style="border: 1px solid black">NAME</th>
			<th style="border: 1px solid black">BRANCH</th>
			<th style="border: 1px solid black">GENDER</th>
			<th style="border: 1px solid black">BIRTHDAY</th>
			<th style="border: 1px solid black">ADDRESS</th>
			<th style="border: 1px solid black">EMAIL</th>
		</tr>
		<%
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			PreparedStatement pre=con.prepareStatement("select * from libaddstudent");
			
			ResultSet res=pre.executeQuery();
			 while(res.next()){
		%>
				 <tr>
					<td><%=res.getString(1) %></td>
					<td><%=res.getString(2) %></td>
					<td><%=res.getString(3) %></td>
					<td><%=res.getString(4) %></td>
					<td><%=res.getDate(5) %></td>
					<td><%=res.getString(6) %></td>
					<td><%=res.getString(7) %></td>
				</tr>
		 <%}
			
		}
		catch(Exception e){
			out.print("exception :"+e);
		}
		%>
	</table>
			</form>
		</article>
		</div>
	</div>
</body>
</html>