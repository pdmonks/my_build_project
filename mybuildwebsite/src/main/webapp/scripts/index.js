// Author: Peter Monks
// Version 1.1

// run setup function when the page is loaded
document.onload = initPage();

function initPage() {
	console.log("ID = " + localStorage.getItem("id") + " Token = " + localStorage.getItem("token"));
}