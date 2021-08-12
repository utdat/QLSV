<%@page import="java.util.HashMap"%>
<%@page import="entity.Departments"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
HashMap<Integer,String> dep_map = (HashMap<Integer,String>) session.getAttribute("dep_map");
%>

<!DOCTYPE html>
<html>
<head>
<title>Register</title>
<link rel="stylesheet" type="text/css" href="resources/css/register.css">
<jsp:include page="../component/common-css.jsp"></jsp:include>
<jsp:include page="../component/common-js.jsp"></jsp:include>
</head>
<body>
<%
if (request.getAttribute("mess") != null && request.getAttribute("mess") != "") {
%>
<script>
	alert("<%=request.getAttribute("mess")%>");
</script>
<%
}%>
	<div class="container register">
		<div class="row">
			<div class="col-md-3 register-left">
				<img src="https://image.ibb.co/n7oTvU/logo_white.png" alt="" />
				<h3>Welcome</h3>
				<input type="submit" value="Login"
					onclick="window.location.href='<%=request.getContextPath()%>/Home'" /><br />
			</div>
			<div class="col-md-9 register-right">
				<h1 class="register-heading">Sign up</h1>
				<form action="<%=request.getContextPath()%>/Register" method="post"
					class="needs-validation" novalidate>
					<div class="row register-form">
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="First Name *" name="firstname" required />
								<div class="invalid-feedback">Please fill out this field.</div>
							</div>
							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="Last Name *" name="lastname" required />
								<div class="invalid-feedback">Please fill out this field.</div>
							</div>
							<div class="form-group">
								<input type="password" class="form-control"
									placeholder="Password *" name="password" required />
								<div class="invalid-feedback">Please fill out this field.</div>
							</div>
							<div class="form-group">
								<input type="password" class="form-control"
									placeholder="Confirm Password *" name="repassword" required />
								<div class="invalid-feedback">Please fill out this field.</div>
							</div>
							<div class="form-group">
								<div class="maxl">
									<label class="radio inline"> <input type="radio"
										name="gender" value="M" checked> <span> Male </span>
									</label> <label class="radio inline"> <input type="radio"
										name="gender" value="F"> <span>Female </span>
									</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Your ID *"
									name="id" required />
								<div class="invalid-feedback">Please fill out this field.</div>
							</div>
							<div class="form-group">
								<select class="form-control" name="depart">
									<option class="hidden" selected disabled>Please select
										your departments *</option>
									<%
									for (Integer key : dep_map.keySet()) {
									%>
									<option value="<%=key%>"><%=dep_map.get(key)%></option>
									<%
									}
									%>
								</select>
							</div>
							<div class="form-group">
								<select class="form-control" name="type">
									<option class="hidden" selected disabled>Please select
										your user type *</option>
									<option value="1">Student</option>
									<option value="2">Teacher</option>
								</select>
							</div>
							<div class="form-group">
								<input type="email" class="form-control"
									placeholder="Your Email *" value="" name="email" required />
								<div class="invalid-feedback">Email is not valid.</div>
							</div>
							<input type="submit" class="btnRegister" value="Register" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	</div>
</body>
</html>