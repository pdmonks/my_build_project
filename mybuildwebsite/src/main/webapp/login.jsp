<!-- Author: Peter Monks -->
<!-- Version 1.1 -->
<!DOCTYPE html>
<html>
<head>
<title>MyBuild Login</title>
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ajax-requests.js"></script>
<script src="scripts/utilities.js"></script>
<script src="scripts/login.js"></script>
<!-- <script src="scripts/jquery-ui.js"></script> -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<!--
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
-->

<link href="https://code.jquery.com/ui/1.12.1/themes/vader/jquery-ui.css" rel="stylesheet">
<!-- <script src="https://code.jquery.com/jquery-1.10.2.js"></script> -->
<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

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
			<h1>Log into your MyBuild Account</h1>
		</div>
	</div>

    <!-- main page content -->
        
    <!-- update indicators -->    
        
    <div class="container" id="loginIndicator">
    	<h2>Please wait</h2>
    	<img src="images/processing.gif" alt="processing">
    	<br><br>
		<p>Logging into your account...</p>
    </div>
    
	<div class="container" id="successIndicator">
    	<h2>Log in successful</h2>
		<p>You can now...</p>
		<p><a href="build.jsp">Start a new Build!</a></p>
		<p><a href="builds.jsp">View your previous Builds</a></p>
		<p><a href="account.jsp">Manage your account</a></p>
    </div>
        
    <div class="container" id="errorIndicator">
    	<h2>Invalid log in information submitted</h2>
		<p>Please check your log in details and click <a href="login.jsp">HERE</a> to try again</p>
		<p>New to MyBuild? Click <a href="signup.jsp">HERE</a> to create an account</p>
    </div>
        
    <!-- form for inputting login credentials -->
    <div class="container" id="customerLogin">
	    <div class="container">  
	    	<div class="row justify-content-center">
		        <form name="loginform" action="#">    
		            <div class="form-group">
		                <label></label>
		                <input id="email" type="text" class="form-control" placeholder="Email address" maxlength="50" required>
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="password" type="password" class="form-control" placeholder="Password" maxlength="30" required>
		            </div>
		            <br>
		            <input type="button" id="loginCustomerButton" value="Login">
		        </form>
			</div>
		</div>
	        
	    <br>
		
		<div class="container" id="messageArea">
	    </div>
	        
	    <br><br>
	        
		<div class="container text-center">   
	        <div id="resultRegion"> 
	            <p>New to MyBuild? Click <a href="signup.jsp">HERE</a> to create an account</p>
	        </div>
	    </div>
    </div>
    
    <br><br><br>
    
    <!-- footer -->
    <jsp:include page = "pagefooter.jsp" flush = "true" />
    
  </body>
</html>