<!-- Author: Peter Monks -->
<!-- Version 1.1 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyBuild Account</title>
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ajax-requests.js"></script>
<script src="scripts/utilities.js"></script>
<script src="scripts/account.js"></script>
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
			<h1>Your Account Details</h1>
		</div>
	</div>

    <!-- main page content -->
    
    <!-- update indicators -->
    
    <div class="container" id="loadingIndicator">
    	<h2>Please wait</h2>
    	<img src="images/processing.gif" alt="processing">
    	<br><br>
		<p>Loading your account details...</p>
    </div>
    
    <div class="container" id="savingIndicator">
    	<h2>Please wait</h2>
    	<img src="images/processing.gif" alt="processing">
    	<br><br>
		<p>Updating your account details...</p>
    </div>

    <div class="container" id="logoutIndicator">
    	<h2>Please wait</h2>
    	<img src="images/processing.gif" alt="processing">
    	<br><br>
		<p>Logging you out of your account...</p>
    </div>
    
    <div class="container" id="successIndicator">
    	<h2>You have successfully logged out of your account</h2>
		<p>Please visit MyBuild again soon</p>
		<p>Click <a href="login.jsp">HERE</a> to log in again</p>
		<p>Click <a href="index.jsp">HERE</a> to return to the home page</p>
    </div>
    
    <div class="container" id="customerDetails">

	    <!-- form for inputting customer information -->
	    <div class="container">
	    	<div class="row justify-content-center">
	        	<h5>Please enter any amendments required and click the update button</h5>
	    	</div>
	    </div>
	    
	    <div class="container">   
	    	<div class="row justify-content-center">
		        <form name="customerform" action="#">    
		            <div class="form-group">
		                <label></label>
		                <input id="forename" type="text" class="form-control" placeholder="Forename" maxlength="30">
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="surname" type="text" class="form-control" placeholder="Surname" maxlength="30">
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="telephone" type="tel" class="form-control" placeholder="Mobile" maxlength="20">
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="email" type="email" class="form-control" placeholder="Email" maxlength="30">
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="address" type="text" class="form-control" placeholder="Address" maxlength="30">
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="town" type="text" class="form-control" placeholder="Town" maxlength="30">
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="postcode" type="text" class="form-control" placeholder="Postcode" maxlength="8">
		            </div>
	            	<br>
	            	<input id="updateCustomerButton" type="button" value="Update">
	        	</form>
	    	</div>
	    </div>                       
	     
	    <br>
	     
		<div class="container" id="messageArea">
	    </div>
	     
	    <br>
	     
	    <!-- button to change password -->
	    <div class="container">
	    	<div class="row justify-content-center">      
		        <form name="customerchangepassword" action="#"> 
		            <div class="form-group">
		                <label></label>
		                <input id="password1" type="password" class="form-control" placeholder="Password (minimum 8 characters)" maxlength="30">
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="password2" type="password" class="form-control" placeholder="Re-enter Password" maxlength="30">
		            </div>
		            <br>   
		            <input id="updatePasswordButton" type="button" value="Change Password">
		        </form>
		 	</div>
	    </div>
	     
	    <br><br><br>
	     
	    <!-- button to log out of account -->
	    <div class="container"> 
	    	<div class="row justify-content-center">         
	     		<input id="logoutCustomerButton" type="button" value="Logout">
	     	</div>
	    </div>
	
	</div>
	
	<br><br><br>

    <!-- footer -->
    <jsp:include page = "pagefooter.jsp" flush = "true" />

</body>
</html>