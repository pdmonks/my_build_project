// Author: Peter Monks
// Version 1.1

// handle build update response and get next component
function showMotherboardOptions(response) {
	console.log(response);
	$("#savingIndicator").hide();
	if (response.status !== 200) {
		if (response.status === 400) {
			$('#buildErrorIndicator').show();
		} else {
			$('#buildErrorIndicator').show();
		}
	} else {
		localStorage.setItem("build_id", response.responseJSON.build_id);
		console.log("Build created with ID:", localStorage.getItem("build_id"));
		$("#loadingIndicator").show();
		componentIndex = 1;
		previousPrice = 0;
		getMotherboardOptions();
	}
}

// request component options
function getMotherboardOptions() {
	console.log("Selecting component index: " + componentIndex + " Motherboard");
	const resourcePath = "product/type/motherboard/" + usage + "/" + budget + "/" + cpuSocket;
	console.log(resourcePath);
	ajaxGet(resourcePath, "json");
}

// receive component results from response
function showMotherboardResults(response) {
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
		motherboardData = response.responseJSON;
		$("#motherboardOptions").show();
		$("#motherboardOptionsTable").show();
		let list = makeMotherboardList();
		$("#motherboardOptionsTable").html(list);
	}
}

// display component results
function makeMotherboardList() {
	let result = listMotherboardStartTags();
	if (motherboardData === undefined) {
		result = "<h4>" + noComponentResultsMessage + "</h4>";
	} else {
		result += listMotherboardItemsJSON();
	}
	result += listMotherboardEndTags();
	return result;
}

// table headers with usability information
function listMotherboardStartTags() {
	return("<div>"
		//+ "<table>"
		+ "<table class=\"table\">"
		+ "<tr>"
		+ "<th title=\"Component description\">Description</th>"
		
		// + "<th>Socket</th>"
		+ "<th title=\"The chipset determines which advanced features the motherboard can perform\">Chipset</th>"
		+ "<th title=\"This is the physical size of the board\">Form factor</th>"
		+ "<th title=\"The number slots on the motherboard for connecting high-speed M.2 storage devices\">M.2 Slots</th>"
		+ "<th title=\"The number slots on the motherboard for connecting SATA storage devices\">SATA Slots</th>"
		+ "<th title=\"The number of slots for connecting PCI Express components, such as GPU expansion cards\">PCIe Slots</th>"
		// + "<th>RAM slots</th>"
		+ "<th title=\"The maximum RAM capacity supported by the motherboard\">Max RAM Capacity (GB)</th>"
		// + "<th>RAM Channels</th>"
		// + "<th>RAM DDR</th>"
		+ "<th title=\"The speed at which the motherboard can transfer data between RAM storage and the CPU\">RAM Speed (GHz)</th>"
		
		+ "<th title=\"Price including VAT\">Price &pound;</th>"
		+ "<th title=\"Current stock of component\">Stock</th>"
		+ "<th>Select</th>"
		+ "</tr>");
}

// table end tags
function listMotherboardEndTags() {
	return("</table></div>");
}

// generate table content for each component result line
function listMotherboardItemsJSON() {
	result = "";
	for(let index = 0; index < motherboardData.length; index++) {
	
		// generate additional line information from each component result
		const linePrice = valuePlusVAT(motherboardData[index].product_price);
		
		result += "<tr>"
			+ "<td>" + motherboardData[index].product_description + "</td>"
			// + "<td>" + motherboardData[index].motherboard_cpu_socket + "</td>"
			+ "<td>" + motherboardData[index].motherboard_chipset + "</td>"
			+ "<td>" + motherboardData[index].motherboard_form_factor + "</td>"
			+ "<td>" + motherboardData[index].motherboard_m2_slots + "</td>"
			+ "<td>" + motherboardData[index].motherboard_sata_slots + "</td>"
			+ "<td>" + motherboardData[index].motherboard_pcie_slots + "</td>"
			// + "<td>" + motherboardData[index].motherboard_ram_slots + "</td>"
			+ "<td>" + motherboardData[index].motherboard_ram_max + "</td>"
			// + "<td>" + motherboardData[index].motherboard_ram_channel + "</td>"
			// + "<td>" + motherboardData[index].motherboard_ram_ddr + "</td>"
			+ "<td>" + motherboardData[index].motherboard_ram_speed + "</td>"
			+ "<td>" + Number(linePrice).toFixed(2) + "</td>"
			+ "<td>" + motherboardData[index].product_stock + "</td>";
		
		// show select button if in stock, or message if out of stock
		if (parseInt(motherboardData[index].product_stock) == 0) {
			result += "<td>" + noStockMessage + "</td>";
		} else {
			result += "<td><input type=\"radio\" id=\"motherboard" + index +"\" name=\"motherboard\" value=\"" + index + "\" onclick=\"selectMotherboard()\"></td>";
		}
		
		result += "</tr>";	
	}
	return result;	
}

// get selected component and update total build price
function selectMotherboard() {
	$('#motherboardSelectButton').show();
	selectedMotherboard = document.querySelector('input[name="motherboard"]:checked').value;
	console.log("Selected Motherboard: " + motherboardData[selectedMotherboard].product_description);
	updateBuildHeaderTotal(motherboardData[selectedMotherboard].product_price);
}

// request update build with component selection,
// store any values for next selections and update power requirement total
function addMotherboardToBuild() {
	console.log("Updating build with Motherboard index: " + selectedMotherboard);
	buildTotalExclVAT += motherboardData[selectedMotherboard].product_price;
	ramCapacity = motherboardData[selectedMotherboard].motherboard_ram_max;
	ramSpeed = motherboardData[selectedMotherboard].motherboard_ram_speed;
	m2Slots = motherboardData[selectedMotherboard].motherboard_m2_slots;
	formFactor = motherboardData[selectedMotherboard].motherboard_form_factor;
	powerRequired += motherboardData[selectedMotherboard].product_wattage;
	const id = localStorage.getItem("build_id");
	const path = "build/" + id;

	var jsonString = '{"build_id":"' + id
		+ '","customer_id":"' + localStorage.getItem("id")
		+ '","build_status":"' + buildStatusOpen
		+ '","build_flag_usage":"' + usage
		+ '","build_flag_budget":"' + budget
		+ '","build_delivery_charge":"' + deliveryCharge
		+ '","build_total":"' + Number(buildTotalExclVAT).toFixed(2)
		+ '","product_id":"' + motherboardData[selectedMotherboard].product_id
		+ '","build_line_product_type":"' + motherboardData[selectedMotherboard].product_type
		+ '","build_line_product_description":"' + motherboardData[selectedMotherboard].product_description
		+ '","build_line_price":"' + motherboardData[selectedMotherboard].product_price
		+ '","build_line_quantity":"' + defaultQuantity
		+ '"}';
	console.log(jsonString);
	$("#motherboardOptions").hide();
	$("#savingIndicator").show();
	ajaxPut(path, jsonString, "application/json");
}
