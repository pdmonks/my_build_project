// Author: Peter Monks
// Version 1.1

// handle build update response and get next component
function showStorageOptions(response) {
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
		componentIndex = 5;
		previousPrice = 0;
		getStorageOptions();
	}
}

// request component options
function getStorageOptions() {
	console.log("Selecting component index: " + componentIndex + " Storage");
	console.log("Getting Storage");
	let storageType = 'm.2';
	if (m2Slots === 0) {storageType = "sata";}
	const resourcePath = "product/type/storage/" + usage + "/" + budget + "/" + storageType;
	console.log(resourcePath);
	ajaxGet(resourcePath, "json");
}

// receive component results from response
function showStorageResults(response) {
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
		storageData = response.responseJSON;
		$("#storageOptions").show();
		$("#storageOptionsTable").show();
		let list = makeStorageList();
		$("#storageOptionsTable").html(list);
	}
}

// display component results
function makeStorageList() {
	let result = listStorageStartTags();
	if (storageData === undefined) {
		result = "<h4>" + noComponentResultsMessage + "</h4>";
	} else {
		result += listStorageItemsJSON();
	}
	result += listStorageEndTags();
	return result;
}

// table headers with usability information
function listStorageStartTags() {
	return("<div>"
		// + "<table>"
		+ "<table class=\"table\">"
		+ "<tr>"
		+ "<th title=\"Component description\">Description</th>"
		
		// + "<th>Type</th>"
		+ "<th title=\"The amount of space (measured in gigabytes) available for storing files; the more the better!\">Capacity (GB)</th>"
		+ "<th title=\"The rate (measured in megabytes per second) at which data can be read from the device; the higher the better!\">Read Speed (MBs)</th>"
		+ "<th title=\"The rate (measured in megabytes per second) at which data can be written to the device; the higher the better!\">Write Speed (MBs)</th>"
		
		+ "<th title=\"Price including VAT\">Price &pound;</th>"
		+ "<th title=\"Current stock of component\">Stock</th>"
		+ "<th>Select</th>"
		+ "</tr>");
}

// table end tags
function listStorageEndTags() {
	return("</table></div>");
}

// generate table content for each component result line
function listStorageItemsJSON() {
	result = "";
	for(let index = 0; index < storageData.length; index++) {
	
		// generate additional line information from each component result
		const linePrice = valuePlusVAT(storageData[index].product_price);
		
		result += "<tr>"
			+ "<td>" + storageData[index].product_description + "</td>"
			// + "<td>" + storageData[index].storage_type + "</td>"
			+ "<td>" + storageData[index].storage_capacity + "</td>"
			+ "<td>" + storageData[index].storage_read_speed + "</td>"
			+ "<td>" + storageData[index].storage_write_speed + "</td>"
			+ "<td>" + Number(linePrice).toFixed(2) + "</td>"
			+ "<td>" + storageData[index].product_stock + "</td>";
		
		// show select button if in stock, or message if out of stock
		if (parseInt(storageData[index].product_stock) == 0) {
			result += "<td>" + noStockMessage + "</td>";
		} else {
			result += "<td><input type=\"radio\" id=\"storage" + index +"\" name=\"storage\" value=\"" + index + "\" onclick=\"selectStorage()\"></td>";
		}
		
		result += "</tr>";	
	}
	return result;	
}

// get selected component and update total build price
function selectStorage() {
	$('#storageSelectButton').show();
	selectedStorage = document.querySelector('input[name="storage"]:checked').value;
	console.log("Selected Storage: " + storageData[selectedStorage].product_description);
	updateBuildHeaderTotal(storageData[selectedStorage].product_price);
}

// request update build with component selection,
// store any values for next selections and update power requirement total
function addStorageToBuild() {
	console.log("Updating build with Storage index: " + selectedStorage);
	buildTotalExclVAT += storageData[selectedStorage].product_price;
	powerRequired += storageData[selectedStorage].product_wattage;
	const id = localStorage.getItem("build_id");
	const path = "build/" + id;

	var jsonString = '{"build_id":"' + id
		+ '","customer_id":"' + localStorage.getItem("id")
		+ '","build_status":"' + buildStatusOpen
		+ '","build_flag_usage":"' + usage
		+ '","build_flag_budget":"' + budget
		+ '","build_delivery_charge":"' + deliveryCharge
		+ '","build_total":"' + Number(buildTotalExclVAT).toFixed(2)
		+ '","product_id":"' + storageData[selectedStorage].product_id
		+ '","build_line_product_type":"' + storageData[selectedStorage].product_type
		+ '","build_line_product_description":"' + storageData[selectedStorage].product_description
		+ '","build_line_price":"' + storageData[selectedStorage].product_price
		+ '","build_line_quantity":"' + defaultQuantity
		+ '"}';
	console.log(jsonString);
	$("#storageOptions").hide();
	$("#savingIndicator").show();
	ajaxPut(path, jsonString, "application/json");
}
