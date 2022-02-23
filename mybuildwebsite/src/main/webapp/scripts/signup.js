// Author: Peter Monks
// Version 1.1

// customer request functions

$(document).ready(function(){ 
	$('#savingIndicator').hide();
	$('#successIndicator').hide();
	$('#errorIndicator').hide();
});

$(function() {
	$("#createCustomerButton").click(createCustomerJSON);
});

function createCustomerJSON() {
	// get the required elements from the create form
	const forename = filterString(document.getElementById("forename").value);
	const surname = filterString(document.getElementById("surname").value);
	const address = filterString(document.getElementById("address").value);
	const town = filterString(document.getElementById("town").value);
	const postcode = filterString(document.getElementById("postcode").value);
	const telephone = filterString(document.getElementById("telephone").value);
	const email = filterString(document.getElementById("email").value);
	const password1 = filterString(document.getElementById("password1").value);
	const password2 = document.getElementById("password2").value;
	
	let message = '';
	
	// check that all fields have been completed correctly before executing update
	
	if (password1 !== password2) {
		message = 'Passwords do not match';
	}
	
	if (!validPassword(password1)) {
		message = 'Password must be 8-15 characters, containing 8 to 15 characters with at least 1 lowercase letter,'
			+ ' 1 uppercase letter, 1 number and 1 special character ?!.*';
	}
	
	if (!validEmail(email)) {
		message = 'Not a valid email address';
	}
	
	if (forename === '' || surname === '' || address === '' || town === '' || postcode === '' || telephone === '' || email === '' || password1 === '') {
		message = 'Please complete all fields';
	}
	
	if (message === '') {
		createNewCustomerJSON(surname, forename, address, town, postcode, telephone, email, password1);
	} else {
		$("#messageArea").html(message);
	}
}

// build JSON string and call create function
function createNewCustomerJSON(surname, forename, address, town, postcode, telephone, email, password1) {	
	var jsonString = '{"customer_surname":"' + surname
		+ '","customer_forename":"' + forename
		+ '","customer_address":"' + address
		+ '","customer_town":"' + town
		+ '","customer_postcode":"' + postcode
		+ '","customer_telephone":"' + telephone
		+ '","customer_email":"' + email
		+ '","customer_password":"' + password1
		+ '"}';
	console.log(jsonString);
	$('#customerSignup').hide();
	$('#savingIndicator').show();
	ajaxPost("customer", jsonString, "application/json");
}

// close create form on completion
function postRequestComplete(response) {
	if (response.status !== 201) {
		if (response.status === 400) {
			$('#savingIndicator').hide();
			$('#errorIndicator').show();
		} else {
			console.log("something went wrong!");
		}
	} else {
		$('#savingIndicator').hide();
		$('#successIndicator').show();
	}	
}

