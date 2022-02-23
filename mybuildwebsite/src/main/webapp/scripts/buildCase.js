// Author: Peter Monks
// Version 1.1

// handle build update response and get next component
function showCaseOptions(response) {
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
		componentIndex = 6;
		previousPrice = 0;
		getCaseOptions();
	}
}

// request component options
function getCaseOptions() {
	console.log("Selecting component index: " + componentIndex + " Case");
	const resourcePath = "product/type/pccase/" + usage + "/" + budget + "/" + formFactor;
	console.log(resourcePath);
	ajaxGet(resourcePath, "json");
}

// receive component results from response
function showCaseResults(response) {
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
		caseData = response.responseJSON;
		$("#caseOptions").show();
		$("#caseOptionsTable").show();
		let list = makeCaseList();
		$("#caseOptionsTable").html(list);
	}
}

// display component results
function makeCaseList() {
	let result = listCaseStartTags();
	if (caseData === undefined) {
		result = "<h4>" + noComponentResultsMessage + "</h4>";
	} else {
		result += listCaseItemsJSON();
	}
	result += listCaseEndTags();
	return result;
}

// table headers with usability information
function listCaseStartTags() {
	return("<div>"
		//+ "<table>"
		+ "<table class=\"table\">"
		+ "<tr>"
		+ "<th title=\"Component description\">Description</th>"
		
		// + "<th>Micro-ATX</th>"
		// + "<th>Mini-ITX</th>"
		// + "<th>ATX</th>"
		// + "<th>CEB</th>"
		// + "<th>E-ATX</th>"
		+ "<th title=\"The number of slots for connecting additional fans to increase the amount of cooling inside the case\">Cooling slots</th>"
		+ "<th title=\"The number of internal bays, for example to connect additional storage devices inside the case\">Internal bays</th>"
		+ "<th title=\"The number of external bays, for example to connect optical drives which are accessible from outside the case\">External bays</th>"
		
		+ "<th title=\"Price including VAT\">Price &pound;</th>"
		+ "<th title=\"Current stock of component\">Stock</th>"
		+ "<th>Select</th>"
		+ "</tr>");
}

// table end tags
function listCaseEndTags() {
	return("</table></div>");
}

// generate table content for each component result line
function listCaseItemsJSON() {
	result = "";
	for(let index = 0; index < caseData.length; index++) {
	
		// generate additional line information from each component result
		const linePrice = valuePlusVAT(caseData[index].product_price);
		
		result += "<tr>"
			+ "<td>" + caseData[index].product_description + "</td>"
			// + "<td>" + caseData[index].pc_case_micro_atx + "</td>"
			// + "<td>" + caseData[index].pc_case_mini_itx + "</td>"
			// + "<td>" + caseData[index].pc_case_atx + "</td>"
			// + "<td>" + caseData[index].pc_case_ceb + "</td>"
			// + "<td>" + caseData[index].pc_case_e_atx + "</td>"
			+ "<td>" + caseData[index].pc_case_cooling_slots + "</td>"
			+ "<td>" + caseData[index].pc_case_internal_bays + "</td>"
			+ "<td>" + caseData[index].pc_case_external_bays + "</td>"
			+ "<td>" + Number(linePrice).toFixed(2) + "</td>"
			+ "<td>" + caseData[index].product_stock + "</td>";
		
		// show select button if in stock, or message if out of stock
		if (parseInt(caseData[index].product_stock) == 0) {
			result += "<td>" + noStockMessage + "</td>";
		} else {
			result += "<td><input type=\"radio\" id=\"case" + index +"\" name=\"case\" value=\"" + index + "\" onclick=\"selectCase()\"></td>";
		}
		
		result += "</tr>";	
	}
	return result;	
}

// get selected component and update total build price
function selectCase() {
	$('#caseSelectButton').show();
	selectedCase = document.querySelector('input[name="case"]:checked').value;
	console.log("Selected Case: " + caseData[selectedCase].product_description);
	updateBuildHeaderTotal(caseData[selectedCase].product_price);
}

// request update build with component selection,
// store any values for next selections and update power requirement total
function addCaseToBuild() {
	console.log("Updating build with Case index: " + selectedCase);
	buildTotalExclVAT += caseData[selectedCase].product_price;
	const id = localStorage.getItem("build_id");
	const path = "build/" + id;

	var jsonString = '{"build_id":"' + id
		+ '","customer_id":"' + localStorage.getItem("id")
		+ '","build_status":"' + buildStatusOpen
		+ '","build_flag_usage":"' + usage
		+ '","build_flag_budget":"' + budget
		+ '","build_delivery_charge":"' + deliveryCharge
		+ '","build_total":"' + Number(buildTotalExclVAT).toFixed(2)
		+ '","product_id":"' + caseData[selectedCase].product_id
		+ '","build_line_product_type":"' + caseData[selectedCase].product_type
		+ '","build_line_product_description":"' + caseData[selectedCase].product_description
		+ '","build_line_price":"' + caseData[selectedCase].product_price
		+ '","build_line_quantity":"' + defaultQuantity
		+ '"}';
	console.log(jsonString);
	$("#caseOptions").hide();
	$("#savingIndicator").show();
	ajaxPut(path, jsonString, "application/json");
}

