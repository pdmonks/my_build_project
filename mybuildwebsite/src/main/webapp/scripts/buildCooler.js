// Author: Peter Monks
// Version 1.1

// handle build update response and get next component
function showCoolerOptions(response) {
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
		componentIndex = 2;
		previousPrice = 0;
		getCoolerOptions();
	}
}

// request component options
function getCoolerOptions() {
	console.log("Selecting component index: " + componentIndex + " Cooler");
	const resourcePath = "product/type/cooler/" + usage + "/" + budget + "/" + cpuSocket;
	console.log(resourcePath);
	ajaxGet(resourcePath, "json");
}

// receive component results from response
function showCoolerResults(response) {
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
		coolerData = response.responseJSON;
		$("#coolerOptions").show();
		$("#coolerOptionsTable").show();
		let list = makeCoolerList();
		$("#coolerOptionsTable").html(list);
	}
}

// display component results
function makeCoolerList() {
	let result = listCoolerStartTags();
	if (coolerData === undefined) {
		result = "<h4>" + noComponentResultsMessage + "</h4>";
	} else {
		result += listCoolerItemsJSON();
	}
	result += listCoolerEndTags();
	return result;
}

// table headers with usability information
function listCoolerStartTags() {
	return("<div>"
		//+ "<table>"
		+ "<table class=\"table\">"
		+ "<tr>"
		+ "<th title=\"Component description\">Description</th>"
		
		// + "<th>FCLGA1200</th>"
		// + "<th>FCLGA1151</th>"
		// + "<th>FCLGA2066</th>"
		// + "<th>AM4</th>"
		// + "<th>sTRX4</th>"
		+ "<th title=\"The rate at which the fan rotates each minute, the faster the better!\">Fan Speed (RPM)</th>"
		+ "<th title=\"The material the cooler's heatsink is made from.\">Heatsink Material</th>"
		
		+ "<th title=\"Price including VAT\">Price &pound;</th>"
		+ "<th title=\"Current stock of component\">Stock</th>"
		+ "<th>Select</th>"
		+ "</tr>");
}

// table end tags
function listCoolerEndTags() {
	return("</table></div>");
}

// generate table content for each component result line
function listCoolerItemsJSON() {
	result = "";
	for(let index = 0; index < coolerData.length; index++) {
	
		// generate additional line information from each component result
		const linePrice = valuePlusVAT(coolerData[index].product_price);
		
		result += "<tr>"
			+ "<td>" + coolerData[index].product_description + "</td>"
			// + "<td>" + coolerData[index].cooler_fclga1200 + "</td>"
			// + "<td>" + coolerData[index].cooler_fclga1151 + "</td>"
			// + "<td>" + coolerData[index].cooler_fclga2066 + "</td>"
			// + "<td>" + coolerData[index].cooler_am4 + "</td>"
			// + "<td>" + coolerData[index].cooler_strx4 + "</td>"
			+ "<td>" + coolerData[index].cooler_fan_speed + "</td>"
			+ "<td>" + coolerData[index].cooler_material + "</td>"
			+ "<td>" + Number(linePrice).toFixed(2) + "</td>"
			+ "<td>" + coolerData[index].product_stock + "</td>";
		
		// show select button if in stock, or message if out of stock
		if (parseInt(coolerData[index].product_stock) == 0) {
			result += "<td>" + noStockMessage + "</td>";
		} else {
			result += "<td><input type=\"radio\" id=\"cooler" + index +"\" name=\"cooler\" value=\"" + index + "\" onclick=\"selectCooler()\"></td>";
		}
		
		result += "</tr>";	
	}
	return result;	
}

// get selected component and update total build price
function selectCooler() {
	$('#coolerSelectButton').show();
	selectedCooler = document.querySelector('input[name="cooler"]:checked').value;
	console.log("Selected Cooler: " + coolerData[selectedCooler].product_description);
	updateBuildHeaderTotal(coolerData[selectedCooler].product_price);
}

// request update build with component selection,
// store any values for next selections and update power requirement total
function addCoolerToBuild() {
	console.log("Updating build with Cooler index: " + selectedCooler);
	buildTotalExclVAT += coolerData[selectedCooler].product_price;
	powerRequired += coolerData[selectedCooler].product_wattage;
	const id = localStorage.getItem("build_id");
	const path = "build/" + id;

	var jsonString = '{"build_id":"' + id
		+ '","customer_id":"' + localStorage.getItem("id")
		+ '","build_status":"' + buildStatusOpen
		+ '","build_flag_usage":"' + usage
		+ '","build_flag_budget":"' + budget
		+ '","build_delivery_charge":"' + deliveryCharge
		+ '","build_total":"' + Number(buildTotalExclVAT).toFixed(2)
		+ '","product_id":"' + coolerData[selectedCooler].product_id
		+ '","build_line_product_type":"' + coolerData[selectedCooler].product_type
		+ '","build_line_product_description":"' + coolerData[selectedCooler].product_description
		+ '","build_line_price":"' + coolerData[selectedCooler].product_price
		+ '","build_line_quantity":"' + defaultQuantity
		+ '"}';
	console.log(jsonString);
	$("#coolerOptions").hide();
	$("#savingIndicator").show();
	ajaxPut(path, jsonString, "application/json");
}
