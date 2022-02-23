<!-- Author: Peter Monks -->
<!-- Version 1.1 -->
<!DOCTYPE html>
<html>
<head><title>MyBuild Homepage</title>
<script src="scripts/jquery.js"></script>
<script src="scripts/index.js"></script>
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
			<h1>Welcome to MyBuild!</h1>
		</div>
	</div>

	<!-- main page content -->

	<div class="container">
		<div class="text-justify">
			<p><b>Do you want to build your own PC?</b></p>
			<p><b>Are you confused by the vast number of components? Which ones work together? What all that jargon means?</b></p>
			<p><b>Don't want to spend hours of your time researching hundreds of websites and books?</b></p>
			<p><b>Then you're in the right place!</b></p>
			<p>MyBuild was created to help you easily select and order the components you need to build your own PC without the hours of research!</p>
			<p>When you start your build, we explain what all the components are, what they do and the most important features you need to know about.</p>
			<p><b>We don't build your PC for you, we help you to build it yourself as you...</b></p>
			<ul>
				<li>Choose all the components you need to build your PC.</li>
				<li>Learning about your components as you choose them!</li>
				<li>Creating builds to suit your needs, such as for gaming, productivity or general use.</li>
				<li>Within your choice of budget.</li>
			</ul>
			<p>Best of all, we suggest components based on your choices as you progress through your build, meaning...</p>
			<p><b>All of your components will be compatible with each other!</b></p>
			<p>We even give you advice on how to install each component, helping you to build your PC when your components arrive.</p>
			<p>Even if you have built PCs before, MyBuild provides a quick and easy method of selecting and ordering components for your next build.</p>
			<p>More instructions on how to use MyBuild can be found <a href="help.jsp">HERE</a>. </p>
			<p>Create your new MyBuild account or log into your existing account to get started!</p>
			<p><b>Happy Building!</b></p>
		</div>
		<br>
		<input type="button" value="Create a MyBuild Account" style="float:left; width:40%" onclick="window.location.href='/signup.jsp';">
	    <input type="button" value="Login to MyBuild" style="float:right; width:40%" onclick="window.location.href='/login.jsp';">
    </div>
    
    <br><br><br>
    
    <!-- footer -->
    <jsp:include page = "pagefooter.jsp" flush = "true" />
    
  </body>
</html>