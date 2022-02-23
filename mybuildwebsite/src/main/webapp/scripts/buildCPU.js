// Author: Peter Monks
// Version 1.1

// get usage selection and show next build option
// (budget if general or gaming build, CPU if workstation)
function buildUsage() {
	componentIndex = 0;
	usage = document.getElementById("selectUsage").value;
	$('#buildOptionsUsage').hide();
	if (usage === 'w') {
		budget = 'h';
    	getCPUOptions();
	} else {
		$('#buildOptionsBudget').show();
		if (usage === 'g') {
			$('#selectBudgetGaming').show();
		} else {
			$('#selectBudgetComputing').show();
		}
	}
}

// get budget selection and show CPU options
function buildBudget() {
	if (usage === 'g') {
		budget = document.getElementById('selectBudgetGaming').value;
	} else {
		budget = document.getElementById('selectBudgetComputing').value;
	}	
	$('#buildOptionsBudget').hide();
	getCPUOptions();
}

// request component options
function getCPUOptions() {
	$("#loadingIndicator").show();
	console.log("Selecting component index: " + componentIndex + " CPU");
	const resourcePath = "product/type/cpu/" + usage + "/" + budget;
	console.log(resourcePath);
	ajaxGet(resourcePath, "json");
}

// receive component results from response
function showCPUResults(response) {
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
		cpuData = response.responseJSON;
		$("#total").show();
		$("#cpuOptions").show();
		$("#cpuOptionsTable").show();
		let list = makeCPUList();
		$("#cpuOptionsTable").html(list);
	}
}

// display component results
function makeCPUList() {
	let result = listBuildsStartTags();
	if (cpuData === undefined) {
		result = "<h4>" + noComponentResultsMessage + "</h4>";
	} else {
		result += listCPUItemsJSON();
	}
	result += listBuildsEndTags();
	return result;
}

// table headers with usability information
function listBuildsStartTags() {
	return("<div>"
		//+ "<table>"
		+ "<table class=\"table\">"
		+ "<tr>"
		+ "<th title=\"Component description\">Description</th>"
		
		+ "<th title=\"The socket the CPU fits into on the motherboard\">Socket</th>"
		+ "<th title=\"The number of processing cores and threads the CPU has, the more the better!\">Cores / Threads</th>"
		+ "<th title=\"The number of operations the CPU can perform per second; the higher the speed the better!\">Clock Speed (GHz)</th>"
		+ "<th title=\"The size of the fast memory in the CPU, the more the better!\">Cache (MB)</th>"
		+ "<th title=\"If the CPU has integrated graphics, the processor also generates graphical outputs. "
			+ "If not, you will need to select a separate GPU\">Integrated Graphics?</th>"
		+ "<th title=\"Keeps the CPU cool while it is working hard! "
			+ "If the CPUs is supplied with a cooler, you do not need to select a separate one\">Cooler Included?</th>"
		
		+ "<th title=\"Price including VAT\">Price &pound;</th>"
		+ "<th title=\"Current stock of component\">Stock</th>"
		+ "<th>Select</th>"
		+ "</tr>");
}

// table end tags
function listBuildsEndTags() {
	return("</table></div>");
}

// generate table content for each component result line
function listCPUItemsJSON() {
	result = "";
	for(let index = 0; index < cpuData.length; index++) {
	
		// generate additional line information from each component result
		const cpuGhz = cpuData[index].cpu_base_clock / 1000;
		let cooler = 'yes';
		if (cpuData[index].cpu_cooler === 'n') {cooler = 'no';}
		const linePrice = valuePlusVAT(cpuData[index].product_price);
		
		result += "<tr>"
			+ "<td>" + cpuData[index].product_description + "</td>"
			+ "<td>" + cpuData[index].cpu_socket + "</td>"
			+ "<td>" + cpuData[index].cpu_cores + " / " + cpuData[index].cpu_threads + "</td>"
			+ "<td>" + Number(cpuGhz).toFixed(2) + "</td>"
			+ "<td>" + cpuData[index].cpu_cache + "</td>"
			+ "<td>" + cpuData[index].cpu_graphics + "</td>"
			+ "<td>" + cooler + "</td>"
			+ "<td>" + Number(linePrice).toFixed(2) + "</td>"
			+ "<td>" + cpuData[index].product_stock + "</td>";
		
		// show select button if in stock, or message if out of stock 
		if (parseInt(cpuData[index].product_stock) == 0) {
			result += "<td>" + noStockMessage + "</td>";
		} else {
			result += "<td><input type=\"radio\" id=\"cpu" + index +"\" name=\"cpu\" value=\"" + index + "\" onclick=\"selectCPU()\"></td>";
		}
		
		result += "</tr>";	
	}
	return result;	
}

// get selected component and update total build price
function selectCPU() {
	$('#cpuSelectButton').show();
	selectedCPU = document.querySelector('input[name="cpu"]:checked').value;
	console.log("Selected CPU: " + cpuData[selectedCPU].product_description);
	updateBuildHeaderTotal(cpuData[selectedCPU].product_price);
}

// request create new build with initial CPU component selection,
// store any values for next selections and update power requirement total
function createBuild() {
	console.log("Creating build with CPU index: " + selectedCPU);
	buildTotalExclVAT = cpuData[selectedCPU].product_price;
	if (cpuData[selectedCPU].cpu_cooler === "n") {coolerIncluded = false;}
	if (cpuData[selectedCPU].cpu_graphics === "no") {gpuIntegrated = false;}
	cpuSocket = cpuData[selectedCPU].cpu_socket;
	powerRequired += cpuData[selectedCPU].product_wattage;
	console.log("coolerIncluded = " + coolerIncluded + " gpuIntegrated = " + gpuIntegrated);
		
	var jsonString = '{"customer_id":"' + localStorage.getItem("id")
		+ '","build_status":"' + buildStatusOpen
		+ '","build_flag_usage":"' + usage
		+ '","build_flag_budget":"' + budget
		+ '","build_delivery_charge":"' + deliveryCharge
		+ '","build_total":"' + buildTotalExclVAT
		+ '","product_id":"' + cpuData[selectedCPU].product_id
		+ '","build_line_product_type":"' + cpuData[selectedCPU].product_type
		+ '","build_line_product_description":"' + cpuData[selectedCPU].product_description
		+ '","build_line_price":"' + cpuData[selectedCPU].product_price
		+ '","build_line_quantity":"' + defaultQuantity
		+ '"}';
		
	console.log(jsonString);
	$("#cpuOptions").hide();
	$("#savingIndicator").show();
	ajaxPost("build", jsonString, "application/json");	
}
