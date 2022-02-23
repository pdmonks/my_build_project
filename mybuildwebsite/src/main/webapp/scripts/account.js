// Author: Peter Monks
// Version 1.1

// customer request functions

// variables
let forename = "";
let surname = "";
let address = "";
let town = "";
let postcode = "";
let telephone = "";
let email = "";
let originalForename = "";
let originalSurname = "";
let originalAddress = "";
let originalTown = "";
let originalPostcode = "";
let originalTelephone = "";
let originalEmail = "";

// run setup function when the page loads
document.onload = initPage();

// functions to run when page loads
function initPage() {
	if (userLoggedIn()) {
		loadCustomerDetails();
	}
}

// setup page elements when page has loaded
$(document).ready(function(){ 
	$('#savingIndicator').hide();
	$('#successIndicator').hide();
	$('#logoutIndicator').hide(); 
	$('#customerDetails').hide();
});

// reload page when back button clicked in browser
if (!!window.performance && window.performance.navigation.type == 2) {
    window.location.reload();
}

// functions to call when page buttons clicked
$(function() {
	$("#updateCustomerButton").click(updateCustomerJSON);
	$("#updatePasswordButton").click(updatePasswordJSON);
	$("#logoutCustomerButton").click(logoutCustomerJSON);
});

// request customer details to load into page
function loadCustomerDetails() {
	console.log("Loading customer details");
	$("#messageArea").html('');
	const id = localStorage.getItem("id");
	const resourcePath = "customer/" + id;
	ajaxGet(resourcePath, "json");
}

// receive customer details from response
function getRequestComplete(response) {
	console.log(response);
	if (response.status !== 200) {
		if (response.status === 401) {
			resetUser();
		} else {
			console.log("something went wrong!");
			resetUser();
		}
	} else {
		displayCustomerDetails(response);
	}
}

// display customer details
function displayCustomerDetails(response) {
	console.log(response);
	
	forename = document.getElementById("forename");
	surname = document.getElementById("surname");
	address = document.getElementById("address");
	town = document.getElementById("town");
	postcode = document.getElementById("postcode");
	telephone = document.getElementById("telephone");
	email = document.getElementById("email");
	password1 = document.getElementById("password1");
	password2 = document.getElementById("password2");
	
	forename.value = response.responseJSON.customer_forename;
	surname.value = response.responseJSON.customer_surname;
	address.value = response.responseJSON.customer_address;
	town.value = response.responseJSON.customer_town;
	postcode.value = response.responseJSON.customer_postcode;
	telephone.value = response.responseJSON.customer_telephone;
	email.value = response.responseJSON.customer_email;
	
	password1.value = "";
	password2.value = "";
	
	// record retrieved values to compare to updated values
	originalForename = forename.value;
	originalSurname = surname.value;
	originalAddress = address.value;
	originalTown = town.value;
	originalPostcode = postcode.value;
	originalTelephone = telephone.value;
	originalEmail = email.value;
	
	$('#loadingIndicator').hide();
	$('#customerDetails').show();
}

// request to update customer details
function updateCustomerJSON() {
	const id = localStorage.getItem("id");
	const path = "customer/" + id;
	let updateRequired = false;
	let jsonString = '{"customer_id":"' + id + '",';

	forename = filterString(document.getElementById("forename").value);
	surname = filterString(document.getElementById("surname").value);
	address = filterString(document.getElementById("address").value);
	town = filterString(document.getElementById("town").value);
	postcode = filterString(document.getElementById("postcode").value);
	telephone = filterString(document.getElementById("telephone").value);
	email = filterString(document.getElementById("email").value);
	
	// items to update to JSON data
	if (forename.trim() !== originalForename
		&& forename.trim().length > 0) {
		jsonString += '"customer_forename":"' + forename + '",';
		updateRequired = true;
	}
	if (surname.trim() !== originalSurname
		&& surname.trim().length > 0) {
		jsonString += '"customer_surname":"' + surname + '",';
		updateRequired = true;
	}
	if (address.trim() !== originalAddress
		&& address.trim().length > 0) {
		jsonString += '"customer_address":"' + address + '",';
		updateRequired = true;
	}
	if (town.trim() !== originalTown
		&& town.trim().length > 0) {
		jsonString += '"customer_town":"' + town + '",';
		updateRequired = true;
	}
	if (postcode.trim() !== originalPostcode
		&& postcode.trim().length > 0) {
		jsonString += '"customer_postcode":"' + postcode + '",';
		updateRequired = true;
	}
	if (telephone.trim() !== originalTelephone
		&& telephone.trim().length > 0) {
		jsonString += '"customer_telephone":"' + telephone + '",';
		updateRequired = true;
	}
	if (email.trim() !== originalEmail
		&& email.trim().length > 0) {
		jsonString += '"customer_email":"' + email + '",';
		updateRequired = true;
	}
	
	// close JSON data and send request
	jsonString += '}';
	console.log(jsonString);
	if (updateRequired) {
		if (validEmail(email)) {
			$('#customerDetails').hide();
			$('#savingIndicator').show();
			ajaxPut(path, jsonString, "application/json");
		} else {
			$("#messageArea").html('Not a valid email address');
		}
	} else {
		$("#messageArea").html('Nothing to update!');
	}
}

// reload page with updated customer response data
function putRequestComplete(response) {
	console.log(response);
	
	if (response.status !== 200) {
		if (response.status === 401) {
			resetUser();
		}
		if (response.status === 404) {
			resetUser();
		} else {
			resetUser();
		}
	} else {
		$('#savingIndicator').hide();
		$('#loadingIndicator').show();
		loadCustomerDetails();
	}
}

// request to update customer password
function updatePasswordJSON() {
	const id = localStorage.getItem("id");
	const path = "customer/" + id;
	let jsonString = '{"customer_id":"' + id + '",';

	password1 = filterString(document.getElementById("password1").value);
	password2 = document.getElementById("password2").value;
	
	let message = '';
	
	if (password1 !== password2) {
		message = 'Passwords do not match';
	}
	
	if (!validPassword(password1)) {
		message = 'Password must be 8-15 characters, containing 8 to 15 characters with at least 1 lowercase letter,'
			+ ' 1 uppercase letter, 1 number and 1 special character ?!.*';
	}
	
	if (message === '') {
		jsonString += '"customer_password":"' + password1 + '",';
		jsonString += '}';
		console.log(jsonString);
		$('#customerDetails').hide();
		$('#savingIndicator').show();
		ajaxPut(path, jsonString, "application/json");
	} else {
		$("#messageArea").html(message);
	}
}

// customer log out request
function logoutCustomerJSON() {	
	let jsonString = '';
	console.log(jsonString);
	$('#customerDetails').hide();
	$('#logoutIndicator').show(); 
	ajaxPost("customer/logout", jsonString, "application/json");
}

// confirm customer log out complete and reset local storage
function postRequestComplete(response) {
	if (response.status !== 200) {
		if (response.status === 401) {
			resetUser();
		} else {
			resetUser();
		}
	} else {
		localStorage.setItem("id", "");
		localStorage.setItem("token", "");
		$('#logoutIndicator').hide();
		$('#successIndicator').show();
	}
}
