<%@page import="java.util.HashMap"%>
<%@page import="java.sql.SQLOutput"%>
<%@page import="entity.Accounts"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
Accounts acc_new = (Accounts) session.getAttribute("acc_new");
HashMap<Integer, String> dep_map = (HashMap<Integer, String>) session.getAttribute("dep_map");

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

.sel {
	height: 34px;
	padding: 6px 12px;
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
	}
	%>
	<div class="w3-bar w3-top w3-black w3-large" style="z-index: 4">
		<button
			class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey"
			onclick="w3_open();">
			<i class="fa fa-bars"></i> Menu
		</button>
		<span class="w3-bar-item w3-right"> Admin </span>
	</div>

	<nav class="w3-sidebar w3-collapse w3-white"
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
				class="w3-bar-item w3-button w3-padding w3-blue"> <i
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
				class="w3-bar-item w3-button w3-padding"> <i
				class="fas fa-book-open"></i> Subject </a>
			<div id="adminsubject" class="w3-bar-block w3-hide w3-padding">
			      <a href="<c:url value="AdminSubject"/>"
					class="w3-bar-item w3-button w3-padding"> Subject List</a>
			      <a href="<c:url value="AdminSubject?action=detail"/>"
					class="w3-bar-item w3-button w3-padding"> Subject Detail</a>
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
		<header class="w3-container" style="padding-top: 22px">
			<h2>
				<b><i class="fas fa-user-edit fa-fw"></i> Edit Profile</b>
			</h2>
		</header>
		
		<%
		if(acc_new != null){
			acc_cur = acc_new;
		}
		%>
		<!-- Header -->
		<div class="panel-body">
			<div class="card-body">
				<form action="<%=request.getContextPath()%>/AdminProfile"
					method="post">
					<div class="row register-form">
						<div class="col-md-6">
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">First Name</span>
								</div>
								<input type="text" class="form-control"
									placeholder="Your First Name *"
									value="<%=acc_cur.getFirstname()%>" name="firstname" />
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Last Name</span>
								</div>
								<input type="text" class="form-control"
									placeholder="Your Last Name *"
									value="<%=acc_cur.getLastname()%>" name="lastname" />
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Password</span>
								</div>
								<div class="input-group">
									<input type="password" class="form-control"
										placeholder="Password *" value="<%=acc_cur.getPassword()%>"
										name="password" id="pass" /> <span class="input-group-btn">
										<button class="btn btn-default form-control" type="button"
											onclick="showPass()">
											<span class="fas fa-eye"></span>
										</button>
									</span>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Confirm Password</span>
								</div>
								<input type="password" class="form-control"
									placeholder="Confirm Password *" value="" name="repassword" />
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Address</span>
								</div>
								<input type="text" class="form-control"
									placeholder="Your Address "
									<%if (acc_cur.getAddress() != null) {%>
									value="<%=acc_cur.getAddress()%>" <%}%> name="address" />
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Department</span>
								</div>
								<%if(acc_new != null){ %>
								<select class="form-control" name="depart">
									<option value="<%=acc_cur.getDep_id()%>" selected><%=dep_map.get(acc_cur.getDep_id())%></option>
									<%
									for (Integer key : dep_map.keySet()) {
									%>
									<option value="<%=key%>"><%=dep_map.get(key)%></option>
									<%
									}
									%>
								</select>			
								<%} else{%>
									<input type="text" class="form-control" value="" name="depart" disabled/>
								<%} %>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">ID</span>
								</div>
								<input type="text" class="form-control" placeholder="Your ID *"
									value="<%=acc_cur.getId()%>" name="id" />
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Phone Number</span>
								</div>
								<input type="text" minlength="10" maxlength="10" name="phone"
									class="form-control" placeholder="Your Phone *"
									<%if (acc_cur.getPhone() != null) {%>
									value="<%=acc_cur.getPhone()%>" <%}%> name="phone" />
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Email</span>
								</div>
								<input type="email" class="form-control"
									placeholder="Your Email *" value="<%=acc_cur.getEmail()%>"
									name="email" />
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">BirthDate</span>
								</div>
								<input class="form-control" name="birthdate"
									value="<%=acc_cur.getBirthdate()%>" type="date">
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Gender</span>
								</div>
								<select class="form-control" name="gender">
									<option value="M" <%if(acc_cur.getGender().equals("M")){%> selected <%} %>>Male</option>
									<option value="F" <%if(acc_cur.getGender().equals("F")){%> selected <%} %>>Female</option>
								</select>
							</div>
							<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">User Type</span>
								</div>
								<select class="form-control" name="type" disabled>
									<option value="1" <%if(acc_cur.getType() == 1){%> selected <%} %>>Student</option>
									<option value="2" <%if(acc_cur.getType() == 2){%> selected <%} %>>Teacher</option>
									<option value="3" <%if(acc_cur.getType() == 3){%> selected <%} %>>Admin</option>
								</select>		
							</div>
						</div>
					</div>
					<button class="w3-btn w3-green">Update</button>
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
	</script>
</body>
</html>
