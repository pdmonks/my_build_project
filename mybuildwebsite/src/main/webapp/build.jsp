<!-- Author: Peter Monks -->
<!-- Version 1.1 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyBuild New Build</title>
<script src="scripts/jquery.js"></script>
<script src="scripts/jquery-ajax-requests.js"></script>
<script src="scripts/utilities.js"></script>
<script src="scripts/buildParameters.js"></script>
<script src="scripts/buildRequestComplete.js"></script>
<script src="scripts/buildCPU.js"></script>
<script src="scripts/buildMotherboard.js"></script>
<script src="scripts/buildCooler.js"></script>
<script src="scripts/buildRAM.js"></script>
<script src="scripts/buildGPU.js"></script>
<script src="scripts/buildStorage.js"></script>
<script src="scripts/buildCase.js"></script>
<script src="scripts/buildPowerSupply.js"></script>
<script src="scripts/buildSummary.js"></script>
<script src="scripts/buildPayment.js"></script>
</head>
<body>

	<!-- navigation bar -->
    <jsp:include page = "pagenavbar.jsp" flush = "true" />

	<!-- jumbotron -->
	<div>
		<br><br>
		<div class="jumbotron text-center">
			<img id="mybuildlogo" src="images/mybuildlogo.jpg" alt="MyBuild Logo">
			<br><br>
			<h1>Your New Build</h1>
		</div>
	</div>

	<!-- main page content -->
	
	<!--  initial build option selections -->

	<div class="container" id="buildOptionsUsage">
		<h5>Let's get your build started!</h5>
		<br>
		<h5>Your build will need several components, which we will help you to select:</h5>
		<ul class="list-inline">
        	<li class="list-inline-item">CPU</li>
        	<li class="list-inline-item">Motherboard</li>
            <li class="list-inline-item">Cooler</li>
            <li class="list-inline-item">RAM</li>
            <li class="list-inline-item">GPU</li>
            <li class="list-inline-item">Storage</li>
            <li class="list-inline-item">Case</li>
            <li class="list-inline-item">Power Supply</li>
        </ul>
        <br>
		<h5>To help us select the most suitable components for your build, please tell us...</h5>
		<br>
   		<h5>What will be the main purpose of your new build?</h5>
   		<select class="form-select" id="selectUsage">
   			<option value="c">General computing - web browsing, email, media, word processing, etc</option>
			<option value="g">Gaming - for general computing, but also able to play video games </option>
			<option value="w">Workstation - for powerful productivity applications, eg 3D modelling</option>
		</select>
        <br><br><br>
        <input type="button" id="buildOptionsUsageButton" value="Next step"></input>
    </div>
	
	<div class="container" id="buildOptionsBudget">
     	<h5>Which price range are you aiming for?</h5>
     	<select class="form-select" id="selectBudgetComputing">
     		<option value="b">Budget range - up to £600 to get the job done</option>
			<option value="m">Mid-range - £600 - £1,000 to include more powerful components</option>
			<option value="h">High budget - £1,000+ to build your dream PC</option>
		</select>
		<select class="form-select" id="selectBudgetGaming">
     		<option value="b">Budget range - up to £750 to get the job done</option>
			<option value="m">Mid-range - £750 - £1,200 to include more powerful components</option>
			<option value="h">High budget - £1,200+ to build your dream PC</option>
		</select>
        <br><br><br>
        <input type="button" id="buildOptionsBudgetButton" value="Next step"></input>
    </div>
    
    <!-- build update indicators -->
    
    <div class="container" id="loadingIndicator">
    	<h2>Please wait</h2>
    	<img src="images/processing.gif" alt="processing">
    	<br><br>
    	<p>Loading components...</p>
    </div>
    
    <div class="container" id="savingIndicator">
    	<h2>Please wait</h2>
    	<img src="images/processing.gif" alt="processing">
    	<br><br>
    	<p>Updating build...</p>
    </div>
    
    <div class="container" id="buildErrorIndicator">
    	<h2>There was an error when updating your build</h2>
    	<p>Please refresh the page to try again</p>
    </div>
    
    <!--  component selections  -->
    
    <div class="container" id="cpuOptions">
    	<h2>Processor (CPU)</h2>
    	<img id="componentImage" src="images/cpu.jpg" alt="cpu">
    	<div class="text-justify">
	    	<p>
	    		The Processor or CPU (Central Processing Unit) is the component which processes data and program instructions to keep your system running,
	    		making your choice of CPU crucial to building a system which meets your needs.
	    		We have selected the most suitable CPU options, based on your usage and budget choices, but there are several more elements to consider
	    		before making your selection.
	    		These elements are shown in the table below, each with their own characteristics:
	    	</p>
	    	<dl class="row">
	    		<dt class="col-sm-3">Socket</dt>
	    		<dd class="col-sm-9">
	    			This is the type of socket the CPU fits into on the motherboard.
	    			The type can depend on the manufacturer or generation of the CPU.
	    			For example, Intel and AMD sockets are different, but Intel sockets for CPU generations 9 and 10 are also different.
	    			Your choice will determine which motherboard options you can choose in the next step.			 
	    		</dd>
	    		<dt class="col-sm-3">Cores / Threads</dt>
	    		<dd class="col-sm-9">
	    			The number of cores determines how many tasks the CPU can perform at any given time.
	    			A CPU with a single core can perform one task, dual core two tasks simultaneously and so on.
	    			CPUs with additional threads can perform tasks even more efficiently.
	    			The more cores and threads the CPU has, the more work it can do.
	    		</dd>
	    		<dt class="col-sm-3">Clock Speed (GHz)</dt>
	    		<dd class="col-sm-9">
	    			The number of operations each core of the CPU can perform per second, measured in gigahertz (GHz).
	    			A 1 GHz processor can perform 1,000,000,000 operations per second!
	    			The higher the clock speed, the faster your CPU can perform its tasks.
	    		</dd>
	    		<dt class="col-sm-3">Cache (MB)</dt>
	    		<dd class="col-sm-9">
	    			Cache memory is a small amount of RAM (measured in megabytes) which is stored within the CPU itself.
	    			This means that cache memory can be accessed very quickly, and is used to store data which is used most frequently
	    			to speed up tasks which are performed more often.
	    			The more cache memory your CPU has, the more efficiently it can perform these tasks.
	    		</dd>
	    		<dt class="col-sm-3">Integrated Graphics?</dt>
	    		<dd class="col-sm-9">
	    			If the CPU has integrated graphics, it can generate graphical outputs without needing a separate GPU (Graphics Card).
	    			This can lower the cost of your build significantly, although a GPU is recommended for tasks needing more graphics
	    			capabilities, such as playing the latest video games.
	    			If you are building a system for general computing and select a CPU with integrated graphics, you will not need a
	    			separate GPU and we will skip this component.
	    		</dd>
	    		<dt class="col-sm-3">Cooler Included?</dt>
	    		<dd class="col-sm-9">
	    			A cooler is a vital component, which keeps the CPU cool while it is working, preventing it from over-heating.
	    			Once the CPU has been fitted in its socket, the cooler is fitted over it, and fixed to the motherboard.
	    			If you select a CPU which is bundled with a cooler, you do not need to select a separate one and we will skip this component.
	    		</dd>
	    	</dl>
	    	<p>Please select your CPU, by clicking on the relevant Select button in the table below...</p>
    	</div>
    	<div class="container" id="cpuOptionsTable"></div>
    	<div class="text-justify">
    		<p>
	    		<b>Build Tip</b> - you should apply a layer of thermal paste to the top of the CPU before fitting the cooler over it, although
	    		some CPUs come with a thermal layer already applied.
	    		The thermal layer creates an air-free connection between the CPU and Cooler, allowing the cooler to draw heat away from the CPU
	    		efficiently to prevent it over-heating.
	    		After you fit the CPU to the motherboard (but before you fit the cooler), apply only a small (typically pea-sized) amount to prevent
	    		the paste spreading out from the CPU and onto other components.
				If you need to remove and reinstall your CPU, it is also advisable to clean off the paste with an isopropyl alcohol cleaner and then
				re-apply more paste before reinstalling.
	    	</p>
	    	<p><b>Tools Required</b> - Thermal paste, Isopropyl alcohol cleaner (if re-applying paste).</p>
    	</div>
    	<br>
    	<div class="row justify-content-center" id="cpuOptionsButton">
    		<input type="button" id="cpuSelectButton" value="Next component">
    	</div>
    </div>
    
    <div class="container" id="motherboardOptions">
    	<h2>Motherboard</h2>
    	<img id="componentImage" src="images/motherboard.jpg" alt="motherboard">
    	<div class="text-justify">
	    	<p>
	    		The Motherboard is the component the CPU and all other components are fitted to, making it the central area of the system.
	    		A vital task of the motherboard is to transmit data between the components via the system bus.
	    		This interaction allows the components to communicate with each other so that the whole system works together.
	    		Based on your choice of CPU, we have selected compatible motherboards within your usage and budget selections.
	    		Before making your selection, refer to the motherboard characteristics provided below:
	    	</p>
	    	<dl class="row">
	    		<dt class="col-sm-3">Chipset</dt>
	    		<dd class="col-sm-9">
	    			The chipset determines which advanced features the motherboard can accommodate.
	    			An example is the number of USB devices which the motherboard can support.
	    			For the most up to date information, please refer to the appropriate links for either 
					<a href="https://www.amd.com/en/products/chipsets-am4" target="_blank">AMD</a>
					or
					<a href="https://www.intel.co.uk/content/www/uk/en/products/chipsets/desktop-chipsets.html" target="_blank">Intel</a>
					products.
	    		</dd>
	    		<dt class="col-sm-3">Form Factor</dt>
	    		<dd class="col-sm-9">
	    			This is the physical size of the board; from smallest to largest:
					<ul>
						<li>Micro-ATX</li>
						<li>Mini-ITX</li>
						<li>ATX (this is the standard form factor)</li>
						<li>CEB</li>
						<li>E-ATX</li>
					</ul>	
	    			The form factor determines the types of case you can select to fit your build into.
	    			This allows you flexibility to build a variety of systems for specific purposes.
	    			For example, you may favour a smaller sized build for a home-theatre system, to fit in a small case within a media unit.
	    		</dd>
	    		<dt class="col-sm-3">M.2 Slots</dt>
	    		<dd class="col-sm-9">
	    			M.2 components are high speed devices which are fitted directly to a slot on the motherboard.
	    			If the motherboard you select has M.2 slots onboard, your storage options will be selected from our M.2 components.
	    			This will give you the fastest options for your main storage device.
	    		</dd>
	    		<dt class="col-sm-3">SATA Slots</dt>
	    		<dd class="col-sm-9">
	    			SATA components are lower speed devices which are connected to the motherboard via a cable.
	    			If the motherboard you select does not have M.2 slots onboard, your storage options will be selected from our SATA components.
	    			Although slower than M.2, SATA devices are still a relatively fast storage option for budget builds.
	    		</dd>
	    		<dt class="col-sm-3">PCIe Slots</dt>
	    		<dd class="col-sm-9">
	    			PCIe components provide expansion options to your build, most significantly GPU expansion cards for graphics.
	    			The more PCIe slots your motherboard has onboard, the more expansion options you have increasing the flexibility of your build.
	    		</dd>
	    		<dt class="col-sm-3">RAM Capacity (GB)</dt>
	    		<dd class="col-sm-9">
	    			This is the maximum amount of RAM, measured in gigabytes, supported by the motherboard.
	    			Your RAM component options will depend on this capacity.
	    		</dd>
	    		<dt class="col-sm-3">RAM Speed (GHz)</dt>
	    		<dd class="col-sm-9">
	    			This is the speed, measured in gigahertz, at which the motherboard can transfer data between RAM storage and the CPU.
	    			Your RAM component options will depend on this capability.
	    		</dd>
	    	</dl>
	    	<p>Please select your motherboard, by clicking on the relevant Select button in the table below...</p>
    	</div>
    	<div class="container" id="motherboardOptionsTable"></div>
    	<div class="text-justify">
    		<p>
	    		<b>Build Tip</b> - fitting the CPU, Cooler and RAM to the motherboard before fitting the motherboard into the case can be a lot easier
	    		than doing so after.  
	    	</p>
	    	<p><b>Tools Required</b> - Screwdriver, to secure the Motherboard into the case.</p>
    	</div>
    	<br>
    	<div class="row justify-content-center" id="motherboardOptionsButton">
    		<input type="button" id="motherboardSelectButton" value="Next component">
    	</div>
    </div>
    
    <div class="container" id="coolerOptions">
    	<h2>Cooler</h2>
    	<img id="componentImage" src="images/cooler.jpg" alt="cooler">
    	<div class="text-justify">
	    	<p>
	    		The Cooler is the component which keeps the CPU cool while it is operating.
	    		CPUs can generate a lot of heat, making the cooler a vital component in preventing the system from over-heating and failing.
	    		As the CPU you chose did not come bundled with a cooler, you need to select a cooler for your build.
	    		We have selected the cooler options, which are compatible with your chosen CPU.
	    		Your choice should depend on these requirements:
	    	</p>
	    	<dl class="row">
	    		<dt class="col-sm-3">Fan Speed (RPM)</dt>
	    		<dd class="col-sm-9">
	    			This is the rate at which the fan rotates, measured in revolutions per minute.
	    			The faster the rotation, the faster hot air can be drawn away from the CPU.			 
	    		</dd>
	    		<dt class="col-sm-3">Heatsink Material</dt>
	    		<dd class="col-sm-9">
	    			The heatsink is a metal component which draws heat away from the CPU.
	    			It is the part of the cooler which makes contact with the CPU (with a layer of thermal paste in between)
	    			allowing it to draw heat away from the CPU to prevent the CPU from overheating.
	    			Heatsinks can be be made from different materials, the choice of which determines the efficiency of heat dissipation.
	    			Copper heatsinks are more efficient than those made from aluminium.
	    		</dd>
	    	</dl>
	    	<p>Please select your cooler, by clicking on the relevant Select button in the table below...</p>
    	</div>
    	<div class="container" id="coolerOptionsTable"></div>
    	<div class="text-justify">
    		<p>
	    		<b>Build Tip</b> - when fitting the cooler, ensure that you position it so that its power cable can comfortably reach the
	    		power socket on the motherboard.  Also, place the cooler straight down onto the CPU, rather than from an angle, to allow
	    		the thermal paste to spread evenly over the CPU.
	    	</p>
	    	<p><b>Tools Required</b> - Screwdriver, to secure the Cooler to the Motherboard, over the CPU.</p>
    	</div>
    	<br>
    	<div class="row justify-content-center" id="coolerOptionsButton">
    		<input type="button" id="coolerSelectButton" value="Next component">
    	</div>
    </div>
    
    <div class="container" id="ramOptions">
    	<h2>RAM</h2>
    	<img id="componentImage" src="images/ram.jpg" alt="ram">
    	<div class="text-justify">
	    	<p>
	    		Random Access Memory (RAM) is the component which stores data while it the PC is operating.
	    		The amount of RAM is measured in gigabytes.
	    		Storing data in RAM allows it to be sent to and from the CPU far quicker than when data is retrieved from your main storage device.
	    		Therefore, you RAM choice is an important one which will determine how efficiently your build runs.
	    		We have selected the RAM options which allow for the specifications of your chosen motherboard, but you should consider the following
	    		characteristics:
	    	</p>
	    	<dl class="row">
	    		<dt class="col-sm-3">Capacity (GB)</dt>
	    		<dd class="col-sm-9">
	    			This is the capacity of the RAM modules, measured in gigabytes.
	    			The greater the amount of RAM there is in your build, the more data and programs can be sent to and from the CPU quickly,
	    			increasing its operating efficiency.
	    		</dd>
	    		<dt class="col-sm-3">Base Clock (MHz)</dt>
	    		<dd class="col-sm-9">
	    			Similar to CPU clock speed, this is the speed of the RAM (measured in megahertz), and determines how quickly data can be moved.
	    			It is limited by the RAM speed supported by the motherboard.
	    		</dd>
	    		<dt class="col-sm-3">Modules</dt>
	    		<dd class="col-sm-9">
	    			This is the number of RAM modules supplied.
	    			Generally, selecting two modules should be favoured over a single one, as this allows for both modules to be accessed simultaneously
	    			(known as dual channel memory access).
	    			For example, if 8GB is required, aim to select 2 X 4GB rather than 1 X 8GB.
	    		</dd>
	    	</dl>
	    	<p>Please select your RAM, by clicking on the relevant Select button in the table below...</p>
    	</div>
    	<div class="container" id="ramOptionsTable"></div>
    	<div class="text-justify">
    		<p>
	    		<b>Build Tip</b> - Check that you have pushed the RAM modules firmly into their slots in the motherboard to ensure that the RAM is usable.
				If you hear a continuous beep from your motherboard speaker when switching on your PC, this is usually a sign that the RAM modules have not
				been detected by the motherboard and need refitting.
	    	</p>
	    	<p><b>Tools Required</b> - None, the RAM modules just push into place in their Motherboard slots.</p>
    	</div>
    	<br>
    	<div class="row justify-content-center" id="ramOptionsButton">
    		<input type="button" id="ramSelectButton" value="Next component">
    	</div>
    </div>
    
    <div class="container" id="gpuOptions">
    	<h2>GPU</h2>
    	<img id="componentImage" src="images/gpu.jpg" alt="gpu">
    	<div class="text-justify">
	    	<p>
	    		The system needs to generate visual outputs to display on your monitor.
	    		The system you are building needs a GPU (Graphics Card) to perform this function.
	    		A GPU is recommended for graphically intensive tasks, such as playing video games or 3D design work.
	    		We have selected the most suitable GPU options, based on your usage and budget choices, but you may wish to consider these characteristics
	    		before making your selection:
	    	</p>
	    	<dl class="row">
	    		<dt class="col-sm-3">Base Clock (GHz)</dt>
	    		<dd class="col-sm-9">
	    			Similar to CPU clock speed, this is the processing speed of the GPU (measured in gigahertz), determining the number of operations
	    			the GPU can perform per second.			 
	    		</dd>
	    		<dt class="col-sm-3">RAM Capacity (GB)</dt>
	    		<dd class="col-sm-9">
	    			This is the capacity of graphics data (measured in gigabytes) which can be worked on by the GPU.
	    		</dd>
	    		<dt class="col-sm-3">RAM DDR</dt>
	    		<dd class="col-sm-9">
	    			This is the rate at which graphics data can be processed.
	    		</dd>
	    		<dt class="col-sm-3">Cores</dt>
	    		<dd class="col-sm-9">
	    			Similar to the CPU, the number of cores determines how many tasks the GPU can perform at any given time.
	    			The more cores the GPU has, the more graphics it can generate at any given time.
	    		</dd>
	    	</dl>
	    	<p>Please select your GPU, by clicking on the relevant Select button in the table below...</p>
    	</div>
    	<div class="container" id="gpuOptionsTable"></div>
    	<div class="text-justify">
    		<p>
	    		<b>Build Tip</b> - many GPUs need to draw power directly from the power supply.  If so, remember to connect a power cable to the GPU
	    		when you fit it to prevent display problems caused by an under-powered GPU.
	    	</p>
	    	<p><b>Tools Required</b> - Screwdriver, to secure the GPU into its case slot.</p>
    	</div>
    	<br>
    	<div class="row justify-content-center" id="gpuOptionsButton">
    		<input type="button" id="gpuSelectButton" value="Next component">
    	</div>
     </div>
    
    <div class="container" id="storageOptions">
    	<h2>Storage</h2>
    	<img id="componentImage" src="images/storage.jpg" alt="storage">
    	<div class="text-justify">
	    	<p>
	    		The Storage device contains the operating system, programs and your files.
	    		Data is read from storage into RAM when required to run programs or work on your files.
	    		We have selected the most suitable storage options, based on the capabilities of your motherboard, as well as your usage and budget selections.
	    		The characteristics below should be considered before making your choice:
	    	</p>
	    	<dl class="row">
	    		<dt class="col-sm-3">Capacity (GB)</dt>
	    		<dd class="col-sm-9">
	    			This is the amount of space (measured in gigabytes) available on the device for storing data.			 
	    		</dd>
	    		<dt class="col-sm-3">Read Speed (MBs)</dt>
	    		<dd class="col-sm-9">
	    			This is the rate (measured in megabytes per second) at which data can be read from the device.
	    		</dd>
	    		<dt class="col-sm-3">Write Speed (MBs)</dt>
	    		<dd class="col-sm-9">
	    			This is the rate (measured in megabytes per second) at which data can be written to the device.
	    		</dd>
	    	</dl>
	    	<p>Please select your storage device, by clicking on the relevant Select button in the table below...</p>
    	</div>
    	<div class="container" id="storageOptionsTable"></div>
    	<div class="text-justify">
    		<p>
	    		<b>Build Tip</b> - although many storage devices can be installed into a build, it is advisable to only have your main storage
	    		device connected if installing Windows 10 as the operating system.  This will ensure that all required system data is stored onto
	    		this device.  Once Windows 10 is installed, you can then connect more storage devices without problems.
	    	</p>
	    	<p><b>Tools Required</b> - Screwdriver, to secure the device into its case bay.</p>
    	</div>
    	<br>
    	<div class="row justify-content-center" id="storageOptionsButton">
    		<input type="button" id="storageSelectButton" value="Next component">
    	</div>
    </div>
    
    <div class="container" id="caseOptions">
    	<h2>Case</h2>
    	<img id="componentImage" src="images/pc-case.jpg" alt="pccase">
    	<div class="text-justify">
	    	<p>
	    		The Case houses all of the components of your build.
	    		Cases typically come fitted with a fan to regulate the temperature within the case, although more cooling devices can be fitted if needed.
	    		We have selected the most suitable Case options, based on your selected motherboard form factor.
	    		Before making your selection, you may also wish to consider the following characteristics:
	    	</p>
	    	<dl class="row">
	    		<dt class="col-sm-3">Cooling Slots</dt>
	    		<dd class="col-sm-9">
	    			The number of slots for connecting additional fans to increase the amount of cooling inside the case.			 
	    		</dd>
	    		<dt class="col-sm-3">Internal Bays</dt>
	    		<dd class="col-sm-9">
	    			This is the number of internal bays, for example to connect additional storage devices inside the case.
	    		</dd>
	    		<dt class="col-sm-3">External Bays</dt>
	    		<dd class="col-sm-9">
	    			This is the number of external bays, for example to connect optical drives which are accessible from outside the case.
	    		</dd>
	    	</dl>
	    	<p>Please select your case, by clicking on the relevant Select button in the table below...</p>
    	</div>
    	<div class="container" id="caseOptionsTable"></div>
    	<div class="text-justify">
    		<p>
	    		<b>Build Tip</b> - if you think you may add an optical drive to your build in the future, remember to choose a case which
	    		has at least one external bay.
	    	</p>
	    	<p><b>Tools Required</b> - Screwdriver, to open and close the case.</p>
    	</div>
    	<br>
    	<div class="row justify-content-center" id="caseOptionsButton">
    		<input type="button" id="caseSelectButton" value="Next component">
    	</div>
    </div>
    
    <div class="container" id="powerSupplyOptions">
    	<h2>Power Supply</h2>
    	<img id="componentImage" src="images/power-supply.jpg" alt="powersupply">
    	<div class="text-justify">
	    	<p>
	    		The Power Supply delivers power to all components enabling the system to run.
	    		We have selected the most suitable power supply options based on the power requirements of the components you have chosen,
	    		with an extra allowance for further components you may wish to add to your build in the future.
	    		Before making your selection, you may also wish to consider the following characteristics:
	    	</p>
	    	<dl class="row">
	    		<dt class="col-sm-3">Wattage</dt>
	    		<dd class="col-sm-9">
					This is the total amount of power, measured in watts that the power supply can deliver.
					Although we have selected components with enough power to comfortably run your build and future expansions, you may
					still wish to consider more powerful options.			 
	    		</dd>
	    		<dt class="col-sm-3">Type</dt>
	    		<dd class="col-sm-9">
	    			The power supply type refers to its level of modularity:
	    			<ul>
						<li>Non-modular</li>
						<li>Semi-modular</li>
						<li>Modular</li>
					</ul>
	    			The more modular, the fewer the number of cables which are permanently attached to the power supply.
	    			A fully modular power supply is the most flexible for your build as you only need to attach the cables you need for
	    			the components you have fitted.
	    		</dd>
	    	</dl>
	    	<p>Please select your power supply, by clicking on the relevant Select button in the table below...</p>
    	</div>
    	<div class="container" id="powerSupplyOptionsTable"></div>
    	<div class="text-justify">
    		<p>
	    		<b>Build Tip</b> - the power supply can be a large component, and is generally fitted into its own bay.  It is advisable
	    		to fit the power supply into the case before the motherboard, as its size can cause problems when fitting to a case with
	    		the motherboard already installed.
	    	</p>
	    	<p><b>Tools Required</b> - Screwdriver, to secure the power supply into its case housing.</p>
    	</div>
    	<br>
    	<div class="row justify-content-center" id="powerSupplyOptionsButton">
    		<input type="button" id="powerSupplySelectButton" value="Next component">
    	</div>
    </div> 
    
    <!--  build summary table and payment form -->
    
    <div class="container" id="buildSummary">
    	<h2>Your Build Summary</h2>
    	<p>You chose the following components...</p>
    	<div class="container" id="buildSummaryTable"></div>
    	<br>
    	
    	<div class="container" id="buildPaymentForm">
    		<div class="row justify-content-center">
    			<h4>Please enter your card details, then click the Pay button below to complete your build</h4>
    			<div class="container" id="messageArea"></div>
		    	<form name="paymentform" action="#"> 
		            <div class="form-group">
		                <label></label>
		                <input id="cardNumber" type="tel" class="form-control" pattern="[0-9]{16}" placeholder="Card Number" maxlength="16">
		            </div>
		            <div class="form-group">
		                <label>Expiry Month</label>
		                <select id="expiryMonth" name="expiryMonth" style="float:right">
	          				<option value="1">January</option>
	          				<option value="2">February</option>
	          				<option value="3">March</option>
	          				<option value="4">April</option>
	          				<option value="5">May</option>
	          				<option value="6">June</option>
	          				<option value="7">July</option>
	          				<option value="8">August</option>
	          				<option value="9">September</option>
	          				<option value="10">October</option>
	          				<option value="11">November</option>
	          				<option value="12">December</option>
	        			</select>
		            </div>
		            <br>
		            <div class="form-group">
		                <label>Expiry Year</label>
		                <select id="expiryYear" name="expiryYear" style="float:right">
	          				<option value="2021">2021</option>
	          				<option value="2022">2022</option>
	          				<option value="2023">2023</option>
	          				<option value="2024">2024</option>
	          				<option value="2025">2025</option>
	        			</select>
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="cardName" type="text" class="form-control" placeholder="Name on Card" maxlength="30">
		            </div>
		            <div class="form-group">
		                <label></label>
		                <input id="cardCCV" type="tel" class="form-control" pattern="[0-9]{3}" placeholder="CCV" maxlength="3">
		            </div>
		            <br>
	            	<input type="button" id="buildPaymentButton" value="Pay">
	        	</form>
	        </div>
    	</div>
    	
    </div>   

	<!-- payment update indicators -->

	<div class="container" id="paymentProcessingIndicator">
    	<h2>Please wait</h2>
    	<img src="images/processing.gif" alt="processing">
    	<br><br>
    	<p>Processing payment...</p>
    </div>
    
	<div class="container" id="paymentProcessedIndicator">
    	<h2>Thank you!</h2>
    	<p>Your payment has been processed and your build is now complete.</p>
    	<p>We will let you know when your components have been dispatched.</p>
    	<p>You can also review your order by clicking <a href="builds.jsp">MyBUILDS</a></p>
    	<p><b>Happy building!</b></p>
    </div>

	<br><br>

    <!-- footer -->
    <jsp:include page = "pagefooter.jsp" flush = "true" />

</body>
</html>