<!-- Author: Peter Monks -->
<!-- Version 1.1 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyBuild Builds</title>
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ajax-requests.js"></script>
<script src="scripts/utilities.js"></script>
<script src="scripts/builds.js"></script>
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
			<h1>Your Builds</h1>
		</div>
	</div>
	
	<!-- main page content -->
    
    <div class="container">
        <div class="row justify-content-center">
            <input type="button" value="Build Something New!" onclick="document.location = 'build.jsp'"></input>
        </div>
    </div>
    
    <br>
    
    <!-- update indicators -->
    
    <div class="container" id="loadingIndicator">
    	<h2>Please wait</h2>
    	<img src="images/processing.gif" alt="processing">
    	<br><br>
		<p>Loading your build history...</p>
    </div>
    
    <div class="container" id="buildsErrorIndicator">
    	<h2>There was an error when retrieving your builds</h2>
    	<p>Please refresh the page to try again</p>
    </div>
    
    <div class="container" id="customerBuilds"></div>
        
    <br><br><br>    
    
    <!-- footer -->
    <jsp:include page = "pagefooter.jsp" flush = "true" />

</body>
</html>