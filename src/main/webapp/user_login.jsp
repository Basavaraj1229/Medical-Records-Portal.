<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- for jstl tag -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- end of jstl tag -->

<%@page isELIgnored="false"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
<%@include file="component/allcss.jsp"%>

<!-- customs css for this page -->
<style type="text/css">
.my-card {
	box-shadow: 0px 0px 10px 1px maroon;
	/*box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.3);*/
}
</style>
<!-- end of customs css for this page -->

</head>
<body>
	<!-- navbar -->
	<%@include file="component/navbar.jsp"%>
	<!-- end navbar -->



	<!-- <h1>User Login</h1> -->

	<!-- User Login -->
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card my-card">
					<div class="card-header text-center text-white my-bg-color">
						<!-- <p class="fs-4 text-center mt-1"><i class="fa-solid fa-users"></i> <br>User Login</p> -->
						<p class="fs-4 text-center text-white mt-2">
							<i class="fa fa-group"></i> User Login
						</p>
					</div>
					
					<div class="card-body">
						<!-- <p class="fs-4 text-center">User Login</p> -->

						<!-- for message -->
						<!-- success message -->
						<c:if test="${not empty successMsg }">
							<p class="text-center text-success fs-5">${successMsg}</p>
							<c:remove var="successMsg" scope="session" />
						</c:if>

						<!-- error message -->
						<c:if test="${not empty errorMsg }">
							<p class="text-center text-danger fs-5">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>
						<!-- end of message -->



						<!-- boostrap form -->
						<form action="userLogin" method="post" autocomplete="off">
							<!-- Hidden dummy fields to confuse autofill -->
							<input type="text" style="display:none" autocomplete="off">
							<input type="password" style="display:none" autocomplete="off">
							
							<div class="mb-3">
								<label class="form-label">Email address</label> <input
									id="userEmail" name="email" type="email" placeholder="Enter Email"
									class="form-control" autocomplete="new-email" value="">
								<div id="emailHelp" class="form-text">We'll never share
									your email with anyone else.</div>
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label> <input
									id="userPassword" name="password" type="password" placeholder="Enter password"
									class="form-control" autocomplete="new-password" value="">
							</div>

							<button type="submit"
								class="btn col-md-12 text-white my-bg-color">Submit</button>
						</form>
						<br>Don't have an account? <a href="signup.jsp"
							class="text-decoration-none">create one</a>
						<!-- end of boostrap form -->
					</div>
					<!-- </div> -->
				</div>
			</div>
		</div>
	</div>
	<!-- End of User Login -->


	<!-- JavaScript to clear form fields on page load -->
	<script type="text/javascript">
		function clearUserForm() {
			var emailField = document.getElementById('userEmail');
			var passwordField = document.getElementById('userPassword');
			
			if (emailField) {
				emailField.value = '';
			}
			if (passwordField) {
				passwordField.value = '';
			}
		}
		
		// Clear form fields when page loads
		window.addEventListener('load', function() {
			clearUserForm();
		});
		
		// Clear form fields when page becomes visible (handles back button)
		window.addEventListener('pageshow', function() {
			clearUserForm();
		});
		
		// Clear on DOM ready
		document.addEventListener('DOMContentLoaded', function() {
			clearUserForm();
		});
		
		// Clear fields with a slight delay to catch late autofill
		setTimeout(function() {
			clearUserForm();
		}, 500);
	</script>

</body>
</html>
