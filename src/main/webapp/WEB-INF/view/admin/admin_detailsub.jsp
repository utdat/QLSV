<%@page import="entity.Subjects"%>
<%@page import="entity.SubjectGroup"%>
<%@page import="entity.Departments"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="entity.Accounts"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
Subjects sub_cur = (Subjects) session.getAttribute("sub_cur");
HashMap<Integer, String> sg_map = (HashMap<Integer, String>) session.getAttribute("sg_map");
HashMap<String, String> acc_map = (HashMap<String, String>) session.getAttribute("acc_map");

String dir = "";
if (acc_cur.getAvatar() == null) {
dir = "images/default_user.png";
} else {
dir = "images/" + acc_cur.getAvatar();
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Admin</title>
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
			onclick="w3_open();">
			<i class="fa fa-bars"></i> Menu
		</button>
		<span class="w3-bar-item w3-right"> Admin </span>
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
			<a href="<c:url value="AdminProfile"/>"
				class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black"
				onclick="w3_close()" title="close menu"><i
				class="fa fa-remove fa-fw"></i> Close Menu</a> <a
				href="<c:url value="AdminProfile"/>"
				class="w3-bar-item w3-button w3-padding"> <i
				class="fas fa-user-edit fa-fw"></i> Profile </a> 
			<a href="<c:url value="AdminAccount"/>"
				class="w3-bar-item w3-button w3-padding"> <i
				class="fas fa-address-book"></i> Account </a> 
			<a href="<c:url value="AdminDepartment"/>"
				class="w3-bar-item w3-button w3-padding"> <i
				class="fas fa-laptop-code"></i> Department </a> 
			<a href="<c:url value="AdminSubGroup"/>"
				class="w3-bar-item w3-button w3-padding"> <i
				class="fas fa-shapes"></i> Subject Group </a>
			<a onclick="myAccFunc()" href="javascript:void(0)" id="myBtn"
				class="w3-bar-item w3-button w3-padding w3-blue"> <i
				class="fas fa-book-open"></i> Subject </a>
			<div id="adminsubject" class="w3-bar-block w3-hide w3-padding">
			      <a href="<c:url value="AdminSubject"/>"
					class="w3-bar-item w3-button w3-padding"> Subject List</a>
			      <a href="<c:url value="AdminSubject?action=detail"/>"
					class="w3-bar-item w3-button w3-padding w3-blue"><i
					class="fa fa-caret-right w3-margin-right"></i> Subject Detail</a>
    		</div> 
			<a href="<c:url value="AdminStuSubject"/>"
				class="w3-bar-item w3-button w3-padding"> <i
				class="fas fa-chalkboard-teacher"></i> Student's Subject </a> 
			<a href="<c:url value="Logout"/>"
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
				<b><i class="fas fa-book-open"></i> Subject Detail</b>
			</h2>
		</header>
		<div class="panel-body">
			<div class="card-body">
				<form action="<%=request.getContextPath()%>/AdminSubject"
					method="post">
					<%if(sub_cur != null){ %>
						<div class="row register-form">
							<div class="col-md-6">
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject ID</span>
									</div>
									<input type="number" class="form-control" min="1"
										placeholder="Subject ID *"
										value="<%=sub_cur.getId()%>" name="id" required/>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject Name</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Subject Name *"
										value="<%=sub_cur.getName()%>" name="name" required/>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject Group</span>
									</div>
									<select class="form-control" name="sg_id">
										<option value="<%=sub_cur.getSg_id()%>" selected><%=sg_map.get(sub_cur.getSg_id())%></option>
										<%
										for (Integer key : sg_map.keySet()) {
										%>
										<option value="<%=key%>"><%=sg_map.get(key)%></option>
										<%
										}
										%>
									</select>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Startdate</span>
									</div>
									<input class="form-control" name="startdate"
										value="<%=sub_cur.getStartdate()%>" type="date" required/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject Schedule</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Subject Schedule *"
										value="<%=sub_cur.getSchedule()%>" name="schedule" required/>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject Room</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Subject Room *"
										value="<%=sub_cur.getRoom()%>" name="room" required/>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Teacher Name</span>
									</div>
									<select class="form-control" name="tea_id">
										<option value="<%=sub_cur.getTea_id()%>" selected><%=acc_map.get(sub_cur.getTea_id())%></option>
										<%
										for (String key : acc_map.keySet()) {
										%>
										<option value="<%=key%>"><%=acc_map.get(key)%></option>
										<%
										}
										%>
									</select>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Enddate</span>
									</div>
									<input class="form-control" name="enddate"
										value="<%=sub_cur.getEnddate()%>" type="date" required/>
								</div>
							</div>
						</div>
					<%}else{ %>
						<div class="row register-form">
							<div class="col-md-6">
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject ID</span>
									</div>
									<input type="number" class="form-control" min="1"
										placeholder="Subject ID *" name="id" />
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject Name</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Subject Name *" name="name" required/>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject Group</span>
									</div>
									<select class="form-control" name="sg_id">
										<option class="hidden" selected disabled>Please select Subject Group *</option>
										<%
										for (Integer key : sg_map.keySet()) {
										%>
										<option value="<%=key%>"><%=sg_map.get(key)%></option>
										<%
										}
										%>
									</select>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Startdate</span>
									</div>
									<input class="form-control" name="startdate" type="date" required/>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject Schedule</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Subject Schedule *" name="schedule" required/>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Subject Room</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Subject Room *" name="room" required/>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Teacher Name</span>
									</div>
									<select class="form-control" name="tea_id">
										<option class="hidden" selected disabled>Please select Teacher *</option>
										<%
										for (String key : acc_map.keySet()) {
										%>
										<option value="<%=key%>"><%=acc_map.get(key)%></option>
										<%
										}
										%>
									</select>
								</div>
								<div class="form-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Enddate</span>
									</div>
									<input class="form-control" name="enddate" type="date" required/>
								</div>
							</div>
						</div>
					<%} %>
					<button class="w3-btn w3-green">Submit</button>
				</form>
			</div>
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
		document.getElementById("myBtn").click();
	</script>
</body>
</html>
