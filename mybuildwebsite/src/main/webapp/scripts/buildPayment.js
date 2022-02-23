// Author: Peter Monks
// Version 1.1

// check values entered into payment form
function processBuildPayment() {
	componentIndex = 8;

	const cardNumber = document.getElementById("cardNumber").value;
	const expiryMonth = document.getElementById("expiryMonth").value;
	const expiryYear = document.getElementById("expiryYear").value;
	const cardName = filterString(document.getElementById("cardName").value);
	const cardCCV = document.getElementById("cardCCV").value;
	
	const cardNumberFormat = /^(?:[0-9]{16})$/;
	const fullDate = new Date();
	const currentYear = fullDate.getFullYear();
	const currentMonth = fullDate.getMonth() + 1;
	const cardCCVFormat = /^(?:[0-9]{3})$/;
	
	let errorMessage = '';
	
	if (!cardCCVFormat.test(cardCCV)) {
		errorMessage = 'Invalid CCV';
	}
	
	if (cardName === '') {
		errorMessage = 'Please enter card name';
	}
	
	if ((expiryYear - currentYear) === 0) {
		if (expiryMonth < currentMonth) {
			errorMessage = 'Card Expired';
		}
	}
	
	if (!cardNumberFormat.test(cardNumber)) {
		errorMessage = 'Invalid Card Number';
	}
	
	if (errorMessage != '') {
		$("#messageArea").html(errorMessage);
	} else {
		sendPayment();
	}
}

// simulated payment request method
function sendPayment() {
	console.log("Simulated payment to bank process");
	finaliseBuild();
}

// request update build to paid status and update stock of each component
function finaliseBuild() {
	console.log("Updating build with paid status");
	const id = localStorage.getItem("build_id");
	const path = "build/" + id;

	var jsonString = '{"build_id":"' + id
		//+ '","customer_id":"' + localStorage.getItem("id")
		+ '","build_status":"' + buildStatusPaid
		//+ '","build_flag_usage":"' + usage
		//+ '","build_flag_budget":"' + budget
		//+ '","build_delivery_charge":"' + deliveryCharge
		//+ '","build_total":"' + Number(buildTotalExclVAT).toFixed(2)
		//+ '","product_id":"' + gpuData[selectedGPU].product_id
		//+ '","build_line_product_type":"' + gpuData[selectedGPU].product_type
		//+ '","build_line_product_description":"' + gpuData[selectedGPU].product_description
		//+ '","build_line_price":"' + gpuData[selectedGPU].product_price
		//+ '","build_line_quantity":"' + defaultQuantity
		+ '"}';
	console.log(jsonString);
	$("#buildSummary").hide();
	$("#paymentProcessingIndicator").show();
	ajaxPut(path, jsonString, "application/json");
}

// confirm completion of build process
function showBuildPaidConfirmation(response) {
	console.log(response);
	$("#paymentProcessingIndicator").hide();
	if (response.status !== 200) {
		if (response.status === 400) {
			//alert("Incorrect build details!");
			$('#buildErrorIndicator').show();
		} else {
			//console.log("something went wrong!");
			$('#buildErrorIndicator').show();
		}
	} else {
		//console.log("build paid");
		$("#paymentProcessedIndicator").show();
	}
}


