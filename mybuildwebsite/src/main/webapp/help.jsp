<!-- Author: Peter Monks -->
<!-- Version 1.1 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyBuild Help</title>
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ajax-requests.js"></script>
<script src="scripts/utilities.js"></script>
<script src="scripts/help.js"></script>
</head>
<body>

	<!-- navigation bar -->
    <jsp:include page = "pagenavbar.jsp" flush = "true" />
    
	<div>
		<br><br>
		<div class="jumbotron text-center">
			<img id="mybuildlogo" src="images/mybuildlogo.jpg" alt="MyBuild Logo">
			<br><br>
			<h1>Help and Contact</h1>
		</div>
	</div>
    
    <!-- main page content-->
    <div class="container">           
        <p>
        	This page provides assistance on using MyBuild to make new builds.
        	You can also contact us to <a href="#feedbackSection">leave feedback or request help with your build</a>.
        </p>
        <hr><br>
    </div>
    
    <div class="container text-justify">
        <div class="row">
            <div class="col-md-3">
                <h2 style="clear: left">How to build!</h2>
            </div>
            <div class="col-md-9">
                <p>The <a href="build.jsp">BUILD!</a> page is where you select the components for your build.</p>
                <p><b>How do I get started?</b></p>
                <p>It's easy, just click the <a href="build.jsp">BUILD!</a> button at the top of the page.</p>
                <p><b>Will you help me?</b></p>
                <p>We guide you through each step, making each build easy to complete.</p>
                <p>To help us provide you with the most appropriate component choices, we will ask you to select options for:</p>
                <ul>
                    <li>The main purpose of your build, for example gaming.</li>
                    <li>Your budget range from a series of choices.</li>
                </ul>
                <p>Please note that if you are building a workstation, you will not be asked for a budget option as these builds
                	are higher value components to reflect their powerful capabilities.</p>
                <p>Once your have selected the initial options, your build will start and you can select the components you need.</p>
                <p><b>Which components do I need?</b></p>
                <p>It depends on the purpose of the build you choose, but we make sure you choose the right selection of:<p>
                <ul class="list-inline">
		        	<li class="list-inline-item">CPU</li>
		        	<li class="list-inline-item">Motherboard</li>
		            <li class="list-inline-item">Cooler</li>
		            <li class="list-inline-item">RAM</li>
		            <li class="list-inline-item">GPU</li>
		            <li class="list-inline-item">Storage</li>
		            <li class="list-inline-item">Case</li>
		            <li class="list-inline-item">Power Supply</li>
       			</ul>
                <p>For each component, we provide suitable choices based on your usage and budget options, as well
                	as the other components you choose as you progress through the build.</p>
                <p><b>Will my components work together?</b></p>
                <p>Yes!  As an example, once you select a CPU, we will only show you Motherboard options which are compatible with your CPU.</p>
                <p>Your RAM, Storage and Case options are based on your choice of Motherboard, and so on.</p>
                <p>This means all the components you choose will be compatible with each other.</p>
                <p><b>I'm not sure what all those numbers mean!</b></p>
                <p>Along with each component, we provide useful information on the most important specifications (for example, processing speeds)
                	to help you select the right components for your build.</p>
                <p>Once you have made your choice of components, you move onto the next one until you have selected all the
                	components listed above for your complete system.</p>
                <p><b>I want to keep an eye on my budget!</b></p>
                <p>You can view your build cost throughout the process, as it will be updated each time you select a component,
                	allowing you to keep track of your build cost at all times.</p>
                <p>When have selected all the components you need, you will be able to review them along with the total cost of your build.</p>
                <p>Once you are happy with your choices, you can enter your payment card details to complete your build.</p>
                <p><b>I want to make a change!</b></p>
                <p>Don't worry if you are not completely happy with your choices, you can click the Build! button at any time to start over.</p> 
                <p>That's it!  When you complete you build, we let you know once your components are on their way to you.</p>
                <p><b>What happens when I received my components?</b></p>
                <p>We provide you with useful tips as you select components for installing them once you receive your build order.</p>
                <p>If you need more help, you can contact us for more help, by using the feedback form below.</p>
                <p><b>Happy Building!</b></p>
            </div>
        </div>
    </div>
    
    <div class="container">
        <br><hr><br>
    </div>
    
    <div class="container">
    	<div id="feedbackSection"></div>
        <div class="row">
            <div class="col-md-3">
                <h2 style="clear: left">We want your feedback!</h2>
            </div>
            <div class="col-md-9">
                <p>We would love to hear from you with your thoughts on MyBuild.</p>
                <p><b>What do you like about MyBuild?</b></p>
                <p><b>How can we make MyBuild better?</b></p>
                <p><b>Do you need help installing your build?</b></p>
                <p>Use the form below to contact us and let us know.</p>
            </div>
        </div>
    </div>
   
   	<br>
   
    <!-- form for inputting feedback -->
    <div class="row justify-content-center">
        <form name="feedbackForm" action="#">
            <div class="form-group">
                <label>Your name (leave this blank if you prefer)</label>
                <input id="feedbackName" type="text" class="form-control" placeholder="name" maxlength="30">
            </div>
            <div class="form-group">
                <label>Your email address (leave this blank if you prefer)</label>
                <input id="feedbackEmail" type="email" class="form-control" placeholder="email" maxlength="30">
            </div>
            <div class="form-group">
                <label>Your feedback</label>
                <textarea id="feedbackText" class="form-control" placeholder="feedback" maxlength="200"></textarea>
            </div>
            <input type="button" id="feedbackSubmitButton" value="Submit Feedback">
        </form>
    </div>
    
    <br>
    
    <div class="row justify-content-center" id="feedbackMessage">
    </div>
    
    <div class="container">
        <br><br>
    </div>
    
    <!-- footer -->
    <jsp:include page = "pagefooter.jsp" flush = "true" />

</body>
</html>