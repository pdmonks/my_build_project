// Author: Peter Monks
// Version 1.1

// utility functions to handle all requests.
// parameters used where appropriate, eg for root path suffixes,
// data, content, etc.
// calls corresponding success functions for each request type.

const rootURL = "/rest/";
const token = localStorage.getItem("token");

function ajaxGet(resourcePath, dataFormat) {
	$.ajax({
		url: rootURL + resourcePath,
		type: "GET",
		dataType: dataFormat,
		headers: {Authorization: token},		
		complete: function(response){getRequestComplete(response);}
	});
}

function ajaxPost(endpoint, postData, postContent) {
	$.ajax({
		url: rootURL + endpoint,
		type: "POST",
		data: postData,
		contentType: postContent,
		headers: {Authorization: token},
		complete: function(response){postRequestComplete(response);}
	});
}

function ajaxPut(id, putData, putContent) {
	$.ajax({
		url: rootURL + id,
		type: "PUT",
		data: putData,
		contentType: putContent,
		headers: {Authorization: token},
		complete: function(response){putRequestComplete(response);}
	});
}

function ajaxDelete(id) {
	$.ajax({
		url: rootURL + id,
		type: "DELETE",
		headers: {Authorization: token},
		complete: deleteRequestComplete
	});
}