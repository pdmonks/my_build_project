// Author: Peter Monks
// Version 1.1

// handle build update response and get next component
function showRAMOptions(response) {
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
		componentIndex = 3;
		previousPrice = 0;
		getRAMOptions();
	}
}

// request component options
function getRAMOptions() {
	console.log("Selecting component index: " + componentIndex + " RAM");
	const resourcePath = "product/type/ram/" + usage + "/" + budget + "/" + ramCapacity + "/" + ramSpeed;
	console.log(resourcePath);
	ajaxGet(resourcePath, "json");
}

// receive component results from response
function showRAMResults(response) {
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
		ramData = response.responseJSON;
		$("#ramOptions").show();
		$("#ramOptionsTable").show();
		let list = makeRAMList();
		$("#ramOptionsTable").html(list);
	}
}

// display component results
function makeRAMList() {
	let result = listRAMStartTags();
	if (ramData === undefined) {
		result = "<h4>" + noComponentResultsMessage + "</h4>";
	} else {
		result += listRAMItemsJSON();
	}
	result += listRAMEndTags();
	return result;
}

// table headers with usability information
function listRAMStartTags() {
	return("<div>"
		//+ "<table>"
		+ "<table class=\"table\">"
		+ "<tr>"
		+ "<th title=\"Component description\">Description</th>"
		
		// + "<th>DDR</th>"
		+ "<th title=\"The amount of space (measured in gigabytes) available for programs and data being worked on; the more the better!\">Capacity (GB)</th>"
		+ "<th title=\"The speed of the RAM (measured in megahertz), determines how quickly data can be processed; the higher the better!\">Base Clock (MHz)</th>"
		+ "<th title=\"The number of RAM modules supplied, which are fitted to your motherboard\">Modules</th>"
		
		+ "<th title=\"Price including VAT\">Price &pound;</th>"
		+ "<th title=\"Current stock of component\">Stock</th>"
		+ "<th>Select</th>"
		+ "</tr>");
}

// table end tags
function listRAMEndTags() {
	return("</table></div>");
}

// generate table content for each component result line
function listRAMItemsJSON() {
	result = "";
	for(let index = 0; index < ramData.length; index++) {
	
		// generate additional line information from each component result
		const linePrice = valuePlusVAT(ramData[index].product_price);
		
		result += "<tr>"
			+ "<td>" + ramData[index].product_description + "</td>"
			// + "<td>" + ramData[index].ram_ddr + "</td>"
			+ "<td>" + ramData[index].ram_capacity + "</td>"
			+ "<td>" + ramData[index].ram_base_clock + "</td>"
			+ "<td>" + ramData[index].ram_modules + "</td>"
			+ "<td>" + Number(linePrice).toFixed(2) + "</td>"
			+ "<td>" + ramData[index].product_stock + "</td>";
		
		// show select button if in stock, or message if out of stock
		if (parseInt(ramData[index].product_stock) == 0) {
			result += "<td>" + noStockMessage + "</td>";
		} else {
			result += "<td><input type=\"radio\" id=\"ram" + index +"\" name=\"ram\" value=\"" + index + "\" onclick=\"selectRAM()\"></td>";
		}
		
		result += "</tr>";	
	}
	return result;	
}

// get selected component and update total build price
function selectRAM() {
	$('#ramSelectButton').show();
	selectedRAM = document.querySelector('input[name="ram"]:checked').value;
	console.log("Selected RAM: " + ramData[selectedRAM].product_description);
	updateBuildHeaderTotal(ramData[selectedRAM].product_price);
}

// request update build with component selection,
// store any values for next selections and update power requirement total
function addRAMToBuild() {
	console.log("Updating build with RAM index: " + selectedRAM);
	buildTotalExclVAT += ramData[selectedRAM].product_price;
	powerRequired += ramData[selectedRAM].product_wattage;
	const id = localStorage.getItem("build_id");
	const path = "build/" + id;

	var jsonString = '{"build_id":"' + id
		+ '","customer_id":"' + localStorage.getItem("id")
		+ '","build_status":"' + buildStatusOpen
		+ '","build_flag_usage":"' + usage
		+ '","build_flag_budget":"' + budget
		+ '","build_delivery_charge":"' + deliveryCharge
		+ '","build_total":"' + Number(buildTotalExclVAT).toFixed(2)
		+ '","product_id":"' + ramData[selectedRAM].product_id
		+ '","build_line_product_type":"' + ramData[selectedRAM].product_type
		+ '","build_line_product_description":"' + ramData[selectedRAM].product_description
		+ '","build_line_price":"' + ramData[selectedRAM].product_price
		+ '","build_line_quantity":"' + defaultQuantity
		+ '"}';
	console.log(jsonString);
	$("#ramOptions").hide();
	$("#savingIndicator").show();
	ajaxPut(path, jsonString, "application/json");
}