<!-- Author: Peter Monks -->
<!-- Version 1.1 -->
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>MyBuild Navigation Bar</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="PC, builder, mybuild">
        <meta name="description" content="MyBuild Online web portal for customer builds">
        
        <!-- css link for site-wide styling -->
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <!-- Bootstrap
        <link href="CSS/bootstrap.min.css" rel="stylesheet">
        -->
        
        <script src="scripts/jquery.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        
    </head>
    
    <body>
        
        <!-- navigation bar -->
        <div class="container fixed-top">
            <nav class="navbar navbar-expand-lg navbar-light" style="background: #000000">
                <button class="navbar-toggler" style="background: #C0C0C0" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="container">
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a href="index.jsp">
                                    <img id="navbarimg" src="images/mybuildlogoweb.jpg" alt="MyBuild Logo Web">
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="build.jsp">
                                    <img id="navbarimg" src="images/mybuildbuild.jpg" alt="MyBuild Build!">
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="builds.jsp">
                                    <img id="navbarimg" src="images/mybuildmybuilds.jpg" alt="MyBuild MyBuilds">
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="account.jsp">
                                    <img id="navbarimg" src="images/mybuildaccount.jpg" alt="MyBuild Account">
                                </a>
                            </li>

                            <li class="nav-item">
                                <a href="help.jsp">
                                    <img id="navbarimg" src="images/mybuildhelp.jpg" alt="MyBuild Help">
                                </a>
                            </li>

                        </ul>
                    </div>
                </div>
            </nav>
            
            <div style="background: #FF0000; color: #FFFFFF">
            		Please be aware that this website was built for a Degree course project and is for demonstration purposes only.
					Orders created with this website are simulated only.
					Please do not enter any of your personal information into this website.
					Thank you.
			</div>
			<div id="total" align="right" style="background: #00B0F0; color: #FFFFFF"></div>
        </div>
                                     
        <!-- bootstrap script sources -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        
    </body>
</html>