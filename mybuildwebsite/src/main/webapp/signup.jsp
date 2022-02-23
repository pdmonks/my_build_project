<!-- Author: Peter Monks -->
<!-- Version 1.1 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyBuild Signup</title>
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ajax-requests.js"></script>
<script src="scripts/utilities.js"></script>
<script src="scripts/signup.js"></script>
</head>
<body>

	<!-- navigation bar -->
    <jsp:include page = "pagenavbar.jsp" flush = "true" />
	
	<!-- jumbotron -->
	<div>
		<br><br>
		<div class="jumbotron text-center">
			<img id="mybuildlogo" src="images/mybuildlogo.jpg" alt="MyBuild Logo">
			<br><br>
			<h1>Create your MyBuild Account</h1>
		</div>
	</div>

	<!-- main page content -->
	
	<!-- update indicators -->

    <div class="container" id="savingIndicator">
    	<h2>Please wait</h2>
    	<img src="images/processing.gif" alt="processing">
    	<br><br>
		<p>Creating your account...</p>
    </div>
    
	<div class="container" id="successIndicator">
    	<h2>Your account has been created</h2>
		<p>Please click <a href="login.jsp">HERE</a> to log into your new account</p>
    </div>
        
    <div class="container" id="errorIndicator">
    	<h2>Invalid information submitted</h2>
		<p>You may already have an account with this email address</p>
		<p>Please check and refresh the page to try again</p>
    </div>

	<!-- customer signup form -->

	<div class="container" id="customerSignup">
	    <div class="container">
	    	<div class="row justify-content-center">
			    <h5>Please complete all fields, then click Submit to create your account</h5>
		    </div>
	    </div>
	        
		<div class="container">
			<div class="row justify-content-center">   
			    <form name="customerform" action="#">    
			        <div class="form-group">
			            <label></label>
			            <input id="forename" type="text" class="form-control" placeholder="Forename *" maxlength="30" required>
			        </div>
			        <div class="form-group">
			            <label></label>
			            <input id="surname" type="text" class="form-control" placeholder="Surname *" maxlength="30" required>
			        </div>
			        <div class="form-group">
			            <label></label>
			            <input id="telephone" type="tel" class="form-control" placeholder="Mobile *" maxlength="20" required>
			        </div>
			        <div class="form-group">
			            <label></label>
			            <input id="email" type="email" class="form-control" placeholder="Email *" maxlength="50" required>
			        </div>
			        <div class="form-group">
			            <label></label>
			            <input id="address" type="text" class="form-control" placeholder="Address *" maxlength="30" required>
			        </div>
			        <div class="form-group">
			            <label></label>
			            <input id="town" type="text" class="form-control" placeholder="Town *" maxlength="30" required>
			        </div>
			        <div class="form-group">
			            <label></label>
			            <input id="postcode" type="text" class="form-control" placeholder="Postcode *" maxlength="8" required>
			        </div>
			        <div class="form-group">
			            <label></label>
			            <input id="password1" type="password" class="form-control" placeholder="Password (minimum 8 characters) *" pattern=".{8,30}" maxlength="30" required>
			        </div>
			        <div class="form-group">
			            <label></label>
			            <input id="password2" type="password" class="form-control" placeholder="Re-enter Password *" pattern=".{8,30}" maxlength="30" required>
			        </div>        
			        <br>
			        <input type="button" id="createCustomerButton" value="Submit">
			    </form>
		    </div>
		</div>
		<br>
		<div class="container" id="messageArea">
    	</div>
	</div>

	<br><br><br>

    <!-- footer -->
    <jsp:include page = "pagefooter.jsp" flush = "true" />

</body>
</html>