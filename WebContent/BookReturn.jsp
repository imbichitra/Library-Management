<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="home.css">
	<link rel="icon" type="image/jpg" href="library.jpg" />
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
			width: 600px;
			height: 600px;
			margin: 10px auto;
			margin-top: 30px;
		}
		table{
			margin-left: 150px;
		}
		#main{
			
			background-image: url("group-children-book.jpg");

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
				<h2>Return Book</h2>
		<% 
			if (null != request.getAttribute("errorMessage")) {
				out.print("<label style='color:red'><h4>"+request.getAttribute("errorMessage")+"</h4></label>");
			}
		%>
		<% 
			if (null != request.getAttribute("Message")) {
				out.print("<label style='color:green';'text-align:center'><h4>"+request.getAttribute("Message")+"</h4></label>");
			}
		%>
			</header>
			<form action="returnbookservlet" method="post">

				<table>
					<tr>
						<td>ID:</td>
						<td><input type="text" name="id" required>
					</tr>
					<tr>
						<td>Book Id:</td>
						<td><input type="text" name="bookid" required>
					</tr>
					<tr>
						<td colspan="2" style="text-align:center"><input type="submit" value="Issue" style="height: 2em; width: 5em">
					</tr>
				</table>
			</form>

		</article>
		</div>
	</div>
</body>
</html>