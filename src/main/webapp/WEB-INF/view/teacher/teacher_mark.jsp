<%@page import="java.util.Date"%>
<%@page import="entity.Subjects"%>
<%@page import="entity.StuSubject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.Accounts"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<%
Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
ArrayList<StuSubject> mark_list = (ArrayList<StuSubject>) session.getAttribute("mark_list");
HashMap<String, String> stu_map = (HashMap<String, String>) session.getAttribute("stu_map");
HashMap<Integer, Subjects> sub_map = (HashMap<Integer, Subjects>) session.getAttribute("sub_map");

String dir = "";
if (acc_cur.getAvatar() == null) {
	dir = "images/default_user.png";
} else {
	dir = "images/" + acc_cur.getAvatar();
}
%>


<html>
<head>
<title>Teacher</title>
<jsp:include page="../../component/common-css.jsp"></jsp:include>
<jsp:include page="../../component/common-js.jsp"></jsp:include>
<jsp:include page="../../component/change_avatar.jsp"></jsp:include>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
html, body, h1, h2, h3, h4, h5 {
	font-family: "Raleway", sans-serif
}
</style>
<body class="w3-light-grey">
<%
if (request.getAttribute("mess") != null && request.getAttribute("mess") != "") {
%>
<script>
	alert("<%=request.getAttribute("mess")%>");
</script>
<%
}%>
	<!-- Top container -->
	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey"
			onclick="w3_open()">
			<i class="fa fa-bars"></i> Menu
		</button>
		<span class="w3-bar-item w3-right"> Teacher </span>
	</div>

	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-collapse w3-white "
		style="z-index: 3; width: 300px;" id="mySidebar">
		<br>
		<div class="w3-container w3-row">
			<div class="w3-col s4">
				<img src="<%=dir%>" class="w3-circle w3-margin-right"
					style="width: 46px"
					onclick="document.getElementById('my_avatar').style.display='block'">
			</div>
			<div class="w3-col s8 w3-bar">
				<span>Welcome, <strong><%=acc_cur.getFirstname()%></strong></span><br>
				<a  class="w3-bar-item w3-button"><i
					class="fa fa-envelope"></i></a> <a
					class="w3-bar-item w3-button"><i class="fa fa-user"></i></a> <a
					class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
			</div>
		</div>
		<hr>
		<div class="w3-container">
			<h5>My Dashboard</h5>
		</div>
		<div class="w3-bar-block">
			<a href="<c:url value="TeacherProfile"/>"
				class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
				onclick="w3_close()" title="close menu"><i
				class="fa fa-remove fa-fw"></i> Close Menu</a> <a
				href="<c:url value="TeacherProfile"/>"
				class="w3-bar-item w3-button w3-padding"> <i
				class="fas fa-user-edit fa-fw"></i> Profile
			</a> <a href="<c:url value="TeacherSchedule"/>"
				class="w3-bar-item w3-button w3-padding"> <i
				class="fas fa-book-reader fa-fw"></i> Schedule
			</a> <a href="<c:url value="TeacherMark"/>"
				class="w3-bar-item w3-button w3-padding w3-blue"> <i
				class="fas fa-user-graduate fa-fw"></i> Mark
			</a> <a href="<c:url value="Logout"/>"
				class="w3-bar-item w3-button w3-padding"> <i
				class="fas fa-sign-out-alt fa-fw"></i> Logout
			</a><br> <br>
		</div>
	</nav>


	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px; margin-top: 43px;">
		<!-- Header -->
		<header class="w3-container" style="padding-top: 22px">
			<h2>
				<b><i class="fas fa-user-graduate fa-fw"></i> My Student Score</b>
			</h2>
		</header>
		<div class="panel-body">
			<div class="input-group">
				<input class="form-control" type="text"
					placeholder="Search for subject names..." id="myInput"
					onkeyup="myFunction()"> <span class="input-group-btn">
					<button class="btn btn-default" type="button">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</span>
			</div>
			<br>
			<table id="table" class="w3-table-all w3-hoverable" cellpadding="0"
				width="100%">
				<thead>
					<tr class="w3-green">
						<th class="w3-center">Subject ID</th>
						<th>Subject Name</th>
						<th class="w3-center">Student ID</th>
						<th>Student Name</th>
						<th class="w3-center">Midterm Score</th>
						<th class="w3-center">Endterm Score</th>
						<th class="w3-center">Update</th>
					</tr>
					<%
					for (int i = 0; i < mark_list.size(); i++) {
					%>
					<tr>
						<td class="w3-center"><%=mark_list.get(i).getSub_id()%></td>
						<td><%=sub_map.get(mark_list.get(i).getSub_id()).getName()%></td>
						<td class="w3-center"><%=mark_list.get(i).getStu_id()%></td>
						<td><%=stu_map.get(mark_list.get(i).getStu_id())%></td>
						<%
						if (sub_map.get(mark_list.get(i).getSub_id()).getStartdate().before(new Date())
								&& sub_map.get(mark_list.get(i).getSub_id()).getEnddate().after(new Date())) {
						%>
						<td class="w3-center"><input size="5" min="0" max="10" step="0.1" id="<%=mark_list.get(i).getSub_id()%><%=mark_list.get(i).getStu_id()%>_mid" class="w3-center" type="number"
							name="midterm" <%if(mark_list.get(i).getMidterm() >= 0) {%> value="<%=mark_list.get(i).getMidterm()%>" <%}%>/></td>
						<td class="w3-center"><input size="5" min="0" max="10" step="0.1" id="<%=mark_list.get(i).getSub_id()%><%=mark_list.get(i).getStu_id()%>_end" class="w3-center" type="number"
							name="endterm" <%if(mark_list.get(i).getEndterm() >= 0) {%> value="<%=mark_list.get(i).getEndterm()%>" <%}%>/></td>
						<td class="w3-center">
							<a id="<%=mark_list.get(i).getSub_id()%><%=mark_list.get(i).getStu_id()%>_a" 
								onclick="update_score(<%=mark_list.get(i).getSub_id()%><%=mark_list.get(i).getStu_id()%>)" 
								href="TeacherMark?action=update&stu_id=<%=mark_list.get(i).getStu_id()%>&sub_id=<%=mark_list.get(i).getSub_id()%>"> 
									<i class="fas fa-paper-plane"></i>
							</a>
						</td>
						<%
						} else {
						%>
						<td class="w3-center">
							<%
							if (mark_list.get(i).getMidterm() >= 0) {
							%> <%=mark_list.get(i).getMidterm()%> <%
 }
 %>
						</td>
						<td class="w3-center">
							<%
							if (mark_list.get(i).getEndterm() >= 0) {
							%> <%=mark_list.get(i).getEndterm()%> <%
 }
 %>
						</td>
						<td class="w3-center"></td>
						<%
						}
						%>
					</tr>
					<%
					}
					%>
				
			</table>
		</div>
		<hr>


		<hr>
		<div class="w3-container w3-dark-grey w3-padding-32">
			<div class="w3-row">
				<div class="w3-container w3-third">
					<h5 class="w3-bottombar w3-border-green">User</h5>
					<p>Student</p>
					<p>Teacher</p>
					<p>Manager</p>
				</div>
				<div class="w3-container w3-third">
					<h5 class="w3-bottombar w3-border-red">System</h5>
					<p>Browser</p>
					<p>OS</p>
					<p>More</p>
				</div>
				<div class="w3-container w3-third">
					<h5 class="w3-bottombar w3-border-orange">Target</h5>
					<p>School</p>
					<p>Teaching Center</p>
				</div>
			</div>
		</div>

		<!-- Footer -->
		<footer class="w3-container w3-padding-16 w3-light-grey">
			<h4>FOOTER</h4>
			<p>
				Powered by <a href="https://www.w3schools.com/w3css/default.asp"
					target="_blank">w3.css</a>
			</p>
		</footer>

	</div>
	<script>
		var mySidebar = document.getElementById("mySidebar");
		var overlayBg = document.getElementById("myOverlay");
	</script>
</body>
</html>