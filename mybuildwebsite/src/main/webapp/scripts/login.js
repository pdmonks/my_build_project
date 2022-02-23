// Author: Peter Monks
// Version 1.1

// customer request functions

$(document).ready(function(){ 
	$('#loginIndicator').hide();
	$('#successIndicator').hide();
	$('#errorIndicator').hide();
});

$(function() {
	$("#loginCustomerButton").click(checkLoginCustomerJSON);
});

function checkLoginCustomerJSON() {
	// get the required elements from the login form
	const email = filterString(document.getElementById("email").value);
	const password = filterString(document.getElementById("password").value);
	console.log(email, password);
	
	// check that all fields have been completed before executing update
	if (email !== "" && password !== "") {
		console.log(email, password);
		loginCustomerJSON(email, password);
	} else {
		$("#messageArea").html('Please complete all required fields');
	}
}

// build JSON string and call create function
function loginCustomerJSON(email, password) {	
	var jsonString = '{"customer_email":"' + email
		+ '","customer_password":"' + password
		+ '"}';
	console.log(jsonString);
	$('#customerLogin').hide();
	$('#loginIndicator').show();
	ajaxPost("customer/login", jsonString, "application/json");
}

// close create form on completion
function postRequestComplete(response) {
	console.log(response);
	$('#loginIndicator').hide();
	if (response.status !== 200) {
		if (response.status === 400) {
			$('#errorIndicator').show();
		} else {
			console.log("something went wrong!");
		}
	} else {
		localStorage.setItem("id", response.responseJSON.id);
		localStorage.setItem("token", response.responseJSON.token);
		$('#successIndicator').show();
	}
}
