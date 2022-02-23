// Author: Peter Monks
// Version 1.1

// check is there is a login token in local storage
function userLoggedIn() {
	let loggedIn = false;
	const token = localStorage.getItem("token");
	console.log("Checking if user is logged in");
	
	if (token === "" || token === null) {
		resetUser();
	} else {
		loggedIn = true;
		console.log("User is logged in");
	}
	return loggedIn;
}

// clear locally stored id and token to reset user
function resetUser() {
	console.log("User not logged in");
	localStorage.setItem("id", "");
	localStorage.setItem("token", "");
	window.location.href='login.jsp';
}

// add VAT to value parameter and return result
function valuePlusVAT(value) {
	const vatRate = 20;
	return value * ((100 + vatRate) / 100);
}

// update the build total in the build page header
function updateBuildHeaderTotal(priceParam) {
	const price = valuePlusVAT(priceParam);
	buildTotalInclVAT += price - previousPrice;
	previousPrice = price;
	$("#total").html("<h2>Order total &pound; " + Number(buildTotalInclVAT).toFixed(2) + " </h2>");
}

// remove unsecure characters from text to prevent data security problems
function filterString(stringParam) {
	let filteredString = stringParam;
	filteredString = filteredString.replace(/["'<>/\\]/g, "");
	console.log(filteredString);
	return filteredString;
}

// check new / updated password inputs are in correct format
function validPassword(passwordParam) {
	let valid = false;
	const passwordFormat = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
	if (passwordFormat.test(passwordParam)) {
		valid = true;
	}
	return valid;
}

// check email inputs are in correct format
function validEmail(emailParam) {
	let valid = false;
	const emailFormat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if (emailFormat.test(emailParam)) {
		valid = true;
	}
	return valid;
}



