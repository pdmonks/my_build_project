// Author: Peter Monks
// Version 1.1

// run setup function when the page loads
document.onload = initPage();

// reload page when back button clicked in browser
if (!!window.performance && window.performance.navigation.type == 2) {
    window.location.reload();
}

// functions to run when page loads
function initPage() {
	userLoggedIn();
}

// setup page elements when page has loaded
$(document).ready(function(){    
    $('#total').hide();
    $('#buildOptionsUsage').show();
    $('#buildOptionsBudget').hide();
    $('#selectBudgetComputing').hide();
    $('#selectBudgetGaming').hide();
    $('#loadingIndicator').hide();
    $('#savingIndicator').hide();
    $('#buildErrorIndicator').hide();
    $('#cpuOptions').hide();
    $('#cpuOptionsTable').hide();
    $('#cpuSelectButton').hide();
    $('#motherboardOptions').hide();
    $('#motherboardOptionsTable').hide();
    $('#motherboardSelectButton').hide();
    $('#coolerOptions').hide();
    $('#coolerOptionsTable').hide();
    $('#coolerSelectButton').hide();
    $('#ramOptions').hide();
    $('#ramOptionsTable').hide();
    $('#ramSelectButton').hide();
    $('#gpuOptions').hide();
    $('#gpuOptionsTable').hide();
    $('#gpuSelectButton').hide();
    $('#storageOptions').hide();
    $('#storageOptionsTable').hide();
    $('#storageSelectButton').hide();
    $('#caseOptions').hide();
    $('#caseOptionsTable').hide();
    $('#caseSelectButton').hide();
    $('#powerSupplyOptions').hide();
    $('#powerSupplyOptionsTable').hide();
    $('#powerSupplySelectButton').hide();
    $('#buildSummary').hide();
    $('#buildSummaryTable').hide();
	$('#buildPaymentForm').hide();
	$('#paymentProcessingIndicator').hide();
	$('#paymentProcessedIndicator').hide();
});

// component index (in order of selection, starting 0 for CPU)
let componentIndex = 0;

// datasets to store retrieved component data
let cpuData = '';
let motherboardData = '';
let coolerData = '';
let ramData = '';
let gpuData = '';
let storageData = '';
let caseData = '';
let powerSupplyData = '';

// selected components (by index in dataset)
let selectedCPU = '';
let selectedMotherboard = '';
let selectedCooler = '';
let selectedRAM = '';
let selectedGPU = '';
let selectedStorage = '';
let selectedCase = '';
let selectedPowerSupply = '';

// default staff ID for web sales
const staffID = 9999;

// price information
//const vatRate = 20; // moved to utilities.js
let buildTotalExclVAT = 0;
let buildTotalInclVAT = 0;
let previousPrice = 0;
let deliveryCharge = 10;

// component criteria for other selections
let usage = '';
let budget = '';
let cpuSocket = '';
let coolerIncluded = true;
let gpuIntegrated = true;
let ramCapacity = 0;
let ramSpeed = 0;
let m2Slots = 0;
let formFactor = '';
let powerRequired = 0;

// reusable messages
const noComponentResultsMessage = 'There are no components of this type available for this build. Please start your build again, choosing different components';
const noStockMessage = 'Awaiting stock';

// build criteria defaults
const defaultQuantity = 1;
const buildStatusOpen = 'o';
const buildStatusComplete = 'c';
const buildStatusPaid = 'p';

// functions to call when page buttons clicked
$(function() {
	$("#buildOptionsUsageButton").click(buildUsage);
	$("#buildOptionsBudgetButton").click(buildBudget);
	$("#cpuSelectButton").click(createBuild);
	$("#motherboardSelectButton").click(addMotherboardToBuild);
	$("#coolerSelectButton").click(addCoolerToBuild);
	$("#ramSelectButton").click(addRAMToBuild);
	$("#gpuSelectButton").click(addGPUToBuild);
	$("#storageSelectButton").click(addStorageToBuild);
	$("#caseSelectButton").click(addCaseToBuild);
	$("#powerSupplySelectButton").click(addPowerSupplyToBuild);
	$("#buildPaymentButton").click(processBuildPayment);
});
