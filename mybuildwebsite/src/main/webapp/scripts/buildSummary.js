// Author: Peter Monks
// Version 1.1

// handle build update response and show build summary
function showBuildSummary(response) {
	console.log(response);
	$("#savingIndicator").hide();
	if (response.status !== 200) {
		if (response.status === 400) {
			$('#buildErrorIndicator').show();
		} else {
			$('#buildErrorIndicator').show();
		}
	} else {
		console.log("build summary");
		showBuildResults();
	}
}

// show build summary area
function showBuildResults() {
	$('#total').hide();
	$("#buildSummary").show();
	$("#buildSummaryTable").show();
	$("#buildPaymentForm").show();
	let list = makeBuildSummaryList();
	$("#buildSummaryTable").html(list);
}

// display build summary information
function makeBuildSummaryList() {
	let result = listBuildSummaryStartTags();
	result += listBuildSummaryItemsJSON();
	result += listBuildSummaryEndTags();
	return result;
}

// table headers
function listBuildSummaryStartTags() {
	return("<div>"
		+ "<table>"
		//+ "<table class=\"table\">"
		+ "<tr>"
		+ "<th>Product ID</th>"
		+ "<th>Type</th>"
		+ "<th>Description</th>"
		+ "<th>Price &pound;</th>"
		+ "<th>Quantity</th>"
		+ "<th>Total &pound;</th>"
		+ "</tr>");
}

// table end tags
function listBuildSummaryEndTags() {
	deliveryInclVAT = valuePlusVAT(deliveryCharge);
	deliveryVAT = deliveryInclVAT - deliveryCharge;
	buildVAT = buildTotalInclVAT - buildTotalExclVAT;
	totalCharge = deliveryInclVAT + buildTotalInclVAT;
	
	return("</table>"
	+ "<br>"
	+ "<h4 style=\"text-align:right;\">Build total &pound; " + Number(buildTotalInclVAT).toFixed(2) + "</h4>"
	+ "<h4 style=\"text-align:right;\">Delivery &pound; " + Number(deliveryInclVAT).toFixed(2) + "</h4>"
	+ "<h4 style=\"text-align:right;\">Total to pay &pound; " + Number(totalCharge).toFixed(2) + "</h4>"
	+ "<br>"
	+ "<p style=\"text-align:right;\">Includes VAT of &pound; " + Number((buildVAT + deliveryVAT)).toFixed(2) + "</p>"
	+ "</div>");
}

// generate table content for each build line
function listBuildSummaryItemsJSON() {
	result = "";
	
	// get selected component of each type 
	
	let dataSources = [cpuData[selectedCPU], motherboardData[selectedMotherboard],
		coolerData[selectedCooler], ramData[selectedRAM], gpuData[selectedGPU],
		storageData[selectedStorage], caseData[selectedCase], powerSupplyData[selectedPowerSupply]];
	
	for(let index = 0; index < dataSources.length; index++) {
	
		if (dataSources[index] != null) {
			console.log(dataSources[index].product_description);
			
			const linePrice = valuePlusVAT(dataSources[index].product_price);
			
			result += "<tr>"
				+ "<td>" + dataSources[index].product_id + "</td>"
				+ "<td>" + dataSources[index].product_type + "</td>"
				+ "<td>" + dataSources[index].product_description + "</td>"
				+ "<td>" + Number(linePrice).toFixed(2) + "</td>"
				+ "<td>" + defaultQuantity + "</td>"
				+ "<td>" + Number(linePrice * defaultQuantity).toFixed(2) + "</td>";
		}
		
		result += "</tr>";
	}
	return result;	
}
