<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="home.css">
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
				<li><a href="SendMailJsp.jsp">Mail</a></li>
			</ul>
		</nav>
		<article>
			<img src="group-children-book.jpg" width="1200px" height="530px">
		</article>
	</div>
</body>
</html>