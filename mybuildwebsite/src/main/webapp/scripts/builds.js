// Author: Peter Monks
// Version 1.1

// run setup function when the page is loaded
document.onload = initPage();

$(document).ready(function(){    
    // setup default visible div areas once the page has loaded    
	$("#buildsErrorIndicator").hide();
});

let data = '';

if (!!window.performance && window.performance.navigation.type == 2) {
    window.location.reload();
}

function initPage() {
	if (userLoggedIn()) {
		loadBuildHistory();
	}
}

// load customer's build history details into page
function loadBuildHistory() {
	console.log("Loading build history details");
	const id = localStorage.getItem("id");
	const resourcePath = "build/customer/" + id;
	ajaxGet(resourcePath, "json");
}

function getRequestComplete(response) {
	console.log(response);
	
	if (response.status !== 200) {
		if (response.status === 401) {
			resetUser();
		}
		if (response.status === 400) {
			console.log("No results!");
			console.log(data.length);
			let list = makeBuildsList();
			$("#customerBuilds").html(list);
		} else {
			console.log("something went wrong!");
			resetUser();
		}
	} else {
		console.log(response.responseJSON);
		data = response.responseJSON;
		let list = makeBuildsList();
		$("#customerBuilds").html(list);
	}
	
}

function makeBuildsList() {
	$("#loadingIndicator").hide();
	let result = listBuildsStartTags();
	if (data.length == 0) {result = "<h1>You do not have any builds yet!</h1>";}
	else {
		result += listBuildsItemsJSON();
	}
	result += listBuildsEndTags();
	return result;
}

function listBuildsStartTags() {
	return("<div>"
		+ "<table>"
		//+ "<table class=\"table\">"
		+ "<tr>"
		+ "<th>ID</th>"
		+ "<th>Built</th>"
		+ "<th>Dispatched</th>"
		+ "<th>Usage</th>"
		+ "<th>Budget</th>"
		+ "<th>Status</th>"
		+ "<th>Options</th>"
		+ "</tr>");
}

function listBuildsEndTags() {
	return("</table></div>");
}

function listBuildsItemsJSON() {
	result = "";
	for(let index = 0; index < data.length; index++) {
		
		let usage = data[index].build_flag_usage;
		switch(usage) {
			case 'c':
				usage = 'Computing';
				break;
			case 'g':
				usage = 'Gaming';
				break;
			case 'w':
				usage = 'Workstation';
				break;
			default:
				usage = 'Unknown';
		}
		
		let budget = data[index].build_flag_budget;
		switch(budget) {
			case 'b':
				budget = 'Budget';
				break;
			case 'm':
				budget = 'Mid-range';
				break;
			case 'h':
				budget = 'High';
				break;
			default:
				budget = 'Unknown';
		}
		
		let status = data[index].build_status;
		switch(status) {
			case 'c':
				status = 'Complete';
				break;
			case 'd':
				status = 'Dispatched';
				break;
			case 'o':
				status = 'Open';
				break;
			case 'p':
				status = 'Paid';
				break;
			default:
				status = 'Unknown';
		}									
		
		let dispatchDate = data[index].build_dispatch_date;
		if (dispatchDate === undefined) {dispatchDate = 'Awaiting Dispatch';}
		
		result += "<tr>"
			+ "<td>" + data[index].build_id + "</td>"
			+ "<td>" + data[index].build_date + "</td>"
			+ "<td>" + dispatchDate + "</td>"
			+ "<td>" + usage + "</td>"
			+ "<td>" + budget + "</td>"
			+ "<td>" + status + "</td>"
			+ "<td><input type=\"button\" style=\" width:100%;\" id=\"button" + index + "\" value=\"Show Detail\" onclick=\"showBuildDetail(" + index + ")\"></td>"
			+ "</tr>"
			+ "<tr><td colspan=\"7\"><div id=\"detail" + index + "\"></div></td></tr>";
		
		$("#detail" + index).hide();
		
	}
	return(result);
}

function showBuildDetail(index) {
	console.log(document.getElementById("button" + index));
	buttonTxt = document.getElementById("button" + index).value;
	buttonText = document.getElementById("button" + index);
	if (buttonTxt === "Show Detail") {
		$("#detail" + index).show();
		buttonText.value = "Hide Detail";
		$("#detail" + index).html(makeBuildDetail(index));
	} else {
		$("#detail" + index).hide();
		buttonText.value = "Show Detail";
	}
}

function makeBuildDetail(index) {

	buildTotalData = data[index].build_total
	buildTotalDataInclVAT = valuePlusVAT(buildTotalData);
	buildTotalDataVAT = buildTotalDataInclVAT - buildTotalData;
	deliveryChargeData = data[index].build_delivery_charge;
	deliveryChargeDataInclVAT = valuePlusVAT(deliveryChargeData);
	deliveryChargeDataVAT = deliveryChargeDataInclVAT - deliveryChargeData;
	totalDataPaid = buildTotalDataInclVAT + deliveryChargeDataInclVAT;
	totalDataVAT = buildTotalDataVAT + deliveryChargeDataVAT;
	
	detail = "<div>"
		+ "<p>"
		+ "Build total: &pound; " + Number(buildTotalDataInclVAT).toFixed(2)
		+ " | Delivery: &pound; " + Number(deliveryChargeDataInclVAT).toFixed(2)
		+ " | Total paid: &pound; " + Number(totalDataPaid).toFixed(2)
		+ " | Includes VAT of: &pound; " + Number(totalDataVAT).toFixed(2)
		
		+ "</p>" 
		+ "<table id=\"tableDetail\">"
		+ "<tr>"
		+ "<th>Product ID</th>"
		+ "<th>Type</th>"
		+ "<th>Description</th>"
		+ "<th>Price</th>"
		+ "<th>Quantity</th>"
		+ "<th>Total</th>"
		+ "</tr>";
		
	console.log(data[index].build_Lines.length);
	
	for (let i = 0; i < data[index].build_Lines.length; i++) {
		let linePrice = valuePlusVAT(data[index].build_Lines[i].build_line_price);
		let lineTotal = linePrice * data[index].build_Lines[i].build_line_quantity;
		
		detail += "<tr>"
			+ "<td>" + data[index].build_Lines[i].product_id + "</td>"
			+ "<td>" + data[index].build_Lines[i].build_line_product_type + "</td>"
			+ "<td>" + data[index].build_Lines[i].build_line_product_description + "</td>"
			+ "<td>" + Number(linePrice).toFixed(2) + "</td>"
			+ "<td>" + data[index].build_Lines[i].build_line_quantity + "</td>"
			+ "<td>" + Number(lineTotal).toFixed(2) + "</td>"
			+ "</tr>";
	}
	
	detail += "</table>";
			
	return detail;
}


