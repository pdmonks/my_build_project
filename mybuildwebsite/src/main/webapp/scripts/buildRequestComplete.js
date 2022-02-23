// Author: Peter Monks
// Version 1.1

// call appropriate GET response method based on component index
// 0 = CPU, 1 = motherboard, and so on...
function getRequestComplete(response) {
	switch(componentIndex) {
		case 0:
			showCPUResults(response);
			break;
		case 1:
			showMotherboardResults(response);
			break;
		case 2:
			showCoolerResults(response);
			break;
		case 3:
			showRAMResults(response);
			break;
		case 4:
			showGPUResults(response);
			break;
		case 5:
			showStorageResults(response);
			break;
		case 6:
			showCaseResults(response);
			break;
		case 7:
			showPowerSupplyResults(response);
			break;
		default:
			console.log("error");
			resetUser();
	}
}

// call appropriate POST response method based on component index
function postRequestComplete(response) {
	switch(componentIndex) {
	case 0:
		showMotherboardOptions(response);
		break;
	default:
		console.log("error");
		resetUser();
	}
}

// call appropriate PUT response method based on component index
function putRequestComplete(response) {
	switch(componentIndex) {
		case 1:
			{if (coolerIncluded) {
				showRAMOptions(response);
				break;
			} else {
				showCoolerOptions(response);
				break;
			}}
			break;
		case 2:
			showRAMOptions(response);
			break;
		case 3:
			{if (gpuIntegrated && usage !== 'g') {
				showStorageOptions(response);
				break;
			} else {
				showGPUOptions(response);
				break;
			}}
			break;
		case 4:
			showStorageOptions(response);
			break;
		case 5:
			showCaseOptions(response);
			break;
		case 6:
			showPowerSupplyOptions(response);
			break;
		case 7:
			showBuildSummary(response);
			break;
		case 8:
			showBuildPaidConfirmation(response);
			break;
		default:
			console.log("error");
			resetUser();
	}
}