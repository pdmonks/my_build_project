// Author: Peter Monks
// Version 1.1

// handle build update response and get next component
function showPowerSupplyOptions(response) {
	console.log(response);
	$("#savingIndicator").hide();
	if (response.status !== 200) {
		if (response.status === 400) {
			$('#buildErrorIndicator').show();
		} else {
			$('#buildErrorIndicator').show();
		}
	} else {
		$("#loadingIndicator").show();
		componentIndex = 7;
		previousPrice = 0;
		getPowerSupplyOptions();
	}
}

// request component options
function getPowerSupplyOptions() {
	console.log("Selecting component index: " + componentIndex + " Power Supply");
	const resourcePath = "product/type/powersupply/" + usage + "/" + budget + "/" + powerRequired;
	console.log(resourcePath);
	ajaxGet(resourcePath, "json");
}

// receive component results from response
function showPowerSupplyResults(response) {
	console.log(response);
	$("#loadingIndicator").hide();
	
	if (response.status !== 200 && response.status !== 400) {
		if (response.status === 401) {
			resetUser();
		} else {
			$('#buildErrorIndicator').show();
		}
	} else {
		console.log(response.responseJSON);
		powerSupplyData = response.responseJSON;
		$("#powerSupplyOptions").show();
		$("#powerSupplyOptionsTable").show();
		let list = makePowerSupplyList();
		$("#powerSupplyOptionsTable").html(list);
	}
}

// display component results
function makePowerSupplyList() {
	let result = listPowerSupplyStartTags();
	if (powerSupplyData === undefined) {
		result = "<h4>" + noComponentResultsMessage + "</h4>";
	} else {
		result += listPowerSupplyItemsJSON();
	}
	result += listPowerSupplyEndTags();
	return result;
}

// table headers with usability information
function listPowerSupplyStartTags() {
	return("<div>"
		//+ "<table>"
		+ "<table class=\"table\">"
		+ "<tr>"
		+ "<th title=\"Component description\">Description</th>"
		
		+ "<th title=\"The amount of power supplied (measured in watts) to your system to keep it running\">Wattage</th>"
		+ "<th title=\"The modularity determines how many power cables are permanently connected to the supply; full modular means none are connected\">Type</th>"
		
		+ "<th title=\"Price including VAT\">Price &pound;</th>"
		+ "<th title=\"Current stock of component\">Stock</th>"
		+ "<th>Select</th>"
		+ "</tr>");
}

// table end tags
function listPowerSupplyEndTags() {
	return("</table></div>");
}

// generate table content for each component result line
function listPowerSupplyItemsJSON() {
	result = "";
	for(let index = 0; index < powerSupplyData.length; index++) {
	
		// generate additional line information from each component result
		const linePrice = valuePlusVAT(powerSupplyData[index].product_price);
		
		result += "<tr>"
			+ "<td>" + powerSupplyData[index].product_description + "</td>"
			+ "<td>" + powerSupplyData[index].power_supply_wattage + "</td>"
			+ "<td>" + powerSupplyData[index].power_supply_type + "</td>"
			+ "<td>" + Number(linePrice).toFixed(2) + "</td>"
			+ "<td>" + powerSupplyData[index].product_stock + "</td>";
		
		// show select button if in stock, or message if out of stock
		if (parseInt(powerSupplyData[index].product_stock) == 0) {
			result += "<td>" + noStockMessage + "</td>";
		} else {
			result += "<td><input type=\"radio\" id=\"powerSupply" + index +"\" name=\"powerSupply\" value=\"" + index + "\" onclick=\"selectPowerSupply()\"></td>";
		}
		
		result += "</tr>";	
	}
	return result;	
}

// get selected component and update total build price
function selectPowerSupply() {
	$('#powerSupplySelectButton').show();
	selectedPowerSupply = document.querySelector('input[name="powerSupply"]:checked').value;
	console.log("Selected Power Supply: " + powerSupplyData[selectedPowerSupply].product_description);
	updateBuildHeaderTotal(powerSupplyData[selectedPowerSupply].product_price);
}

// request update build with component selection,
// store any values for next selections and update power requirement total
function addPowerSupplyToBuild() {
	console.log("Updating build with Power Supply index: " + selectedPowerSupply);
	buildTotalExclVAT += powerSupplyData[selectedPowerSupply].product_price;
	const id = localStorage.getItem("build_id");
	const path = "build/" + id;

	var jsonString = '{"build_id":"' + id
		+ '","customer_id":"' + localStorage.getItem("id")
		+ '","build_status":"' + buildStatusComplete
		+ '","build_flag_usage":"' + usage
		+ '","build_flag_budget":"' + budget
		+ '","build_delivery_charge":"' + deliveryCharge
		+ '","build_total":"' + Number(buildTotalExclVAT).toFixed(2)
		+ '","product_id":"' + powerSupplyData[selectedPowerSupply].product_id
		+ '","build_line_product_type":"' + powerSupplyData[selectedPowerSupply].product_type
		+ '","build_line_product_description":"' + powerSupplyData[selectedPowerSupply].product_description
		+ '","build_line_price":"' + powerSupplyData[selectedPowerSupply].product_price
		+ '","build_line_quantity":"' + defaultQuantity
		+ '"}';
	console.log(jsonString);
	$("#powerSupplyOptions").hide();
	$("#savingIndicator").show();
	ajaxPut(path, jsonString, "application/json");
}


