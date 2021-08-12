<%@page import="entity.Accounts"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
String dir = "images/";
if(acc_cur.getAvatar() == null){
	dir += "default_user.png";
}
else {
	dir += acc_cur.getAvatar();
}
%>

<jsp:include page="../component/common-js.jsp"></jsp:include>

<body>
	<div id="my_avatar" class="w3-modal">
		<div class="w3-modal-content w3-card-4 w3-animate-zoom"
			style="max-width: 600px">

			<div class="w3-center">
				<br> <span
					onclick="document.getElementById('my_avatar').style.display='none'"
					class="w3-button w3-xlarge w3-hover-red w3-display-topright"
					title="Close Modal">&times;</span> <img
					src="<%=dir %>" alt="Avatar" style="width: 30%"
					class="w3-circle w3-margin-top">
			</div>

			<form class="w3-container needs-validation" action="<%=request.getContextPath()%>/UploadImage" method="post" encType="multipart/form-data" novalidate>
				<div class="w3-section">
					<div class="form-group">
						<div class="input-group-prepend">
							<span class="input-group-text">Avatar</span>
						</div>
						<input type="file" name="avatar" required/>
						<div class="invalid-feedback">Please choose your image file.</div>
					</div>
					<button class="w3-button w3-block w3-green w3-section w3-padding"
						type="submit">Submit</button>
				</div>
			</form>
			<div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
				<button
					onclick="document.getElementById('my_avatar').style.display='none'"
					type="button" class="w3-button w3-red">Cancel</button>
			</div>
		</div>
	</div>
</body>
</html>