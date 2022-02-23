// Author: Peter Monks
// Version 1.1

let feedbackName = '';
let	feedbackEmail = '';
let	feedbackText = '';
let filteredText = '';

// run setup function when the page is loaded
document.onload = initPage();

$(document).ready(function(){ 
	//$('#feedbackMessage').hide();
});

if (!!window.performance && window.performance.navigation.type == 2) {
    window.location.reload();
}

function initPage() {
}

$(function() {
	$("#feedbackSubmitButton").click(checkFeedback);
});

function checkFeedback() {
	feedbackName = filterString(document.getElementById("feedbackName").value);
	feedbackEmail = filterString(document.getElementById("feedbackEmail").value);
	feedbackText = filterString(document.getElementById("feedbackText").value);
	
	let message = '';
	
	if (feedbackEmail !== '' && !validEmail(feedbackEmail)) {
		message = 'Not a valid email address';
	}
	
	if (feedbackText === '') {
		message = 'Please complete feedback field';
	}
	
	if (message === '') {
		submitFeedback();
	} else {
		$("#feedbackMessage").html("<h5>" + message + "</h5>");
	}
}

function submitFeedback() {
	const jsonString = '{"feedback_name":"' + feedbackName
		+ '","feedback_email":"' + feedbackEmail
		+ '","feedback_text":"' + feedbackText
		+ '"}';
		
	console.log(jsonString);
	ajaxPost("feedback", jsonString, "application/json");	
}

function postRequestComplete(response) {
	let message = '';
	if (response.status !== 201) {
		message = 'Error - please try again';
	} else {
		message = 'Thank you for your feedback!';
		clearForm();
	}
	$("#feedbackMessage").html("<h5>" + message + "</h5>");	
}

function clearForm() {
	feedbackName = document.getElementById("feedbackName");
	feedbackEmail = document.getElementById("feedbackEmail");
	feedbackText = document.getElementById("feedbackText");
	
	feedbackName.value = '';
	feedbackEmail.value = '';
	feedbackText.value = '';
}

