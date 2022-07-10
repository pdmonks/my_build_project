# CREATE SCHEMA 'mybuild';

USE mybuild;

# *************************************************************
# Delete Existing Tables - deleted in reverse order of creation
# *************************************************************

# ****** Deleting existing sequences and tables ....

SELECT last_insert_id(null);

####DROP VIEW	view_all_products;

DROP TABLE IF EXISTS	build_line		CASCADE;
DROP TABLE IF EXISTS	build			CASCADE;
DROP TABLE IF EXISTS	power_supply	CASCADE;
DROP TABLE IF EXISTS	other			CASCADE;
DROP TABLE IF EXISTS	pc_case			CASCADE;
DROP TABLE IF EXISTS	storage			CASCADE;
DROP TABLE IF EXISTS	gpu				CASCADE;
DROP TABLE IF EXISTS	ram				CASCADE;
DROP TABLE IF EXISTS	cooler			CASCADE;
DROP TABLE IF EXISTS	motherboard		CASCADE;
DROP TABLE IF EXISTS	cpu				CASCADE;
DROP TABLE IF EXISTS	product			CASCADE;
DROP TABLE IF EXISTS	staff			CASCADE;
DROP TABLE IF EXISTS	feedback		CASCADE;
DROP TABLE IF EXISTS	customer		CASCADE;

# *******************************************************************************************
# Create the CUSTOMER table to hold customer account information
#
# customer_id used as foreign key in the BUILD table
# *******************************************************************************************

# ****** Creating CUSTOMER table ....

CREATE TABLE customer
(
customer_id		INT(4)		UNSIGNED 	PRIMARY KEY		AUTO_INCREMENT,
customer_surname	VARCHAR(30)	NOT NULL,
customer_forename	VARCHAR(30)	NOT NULL,
customer_address	VARCHAR(30)	NOT NULL,
customer_town		VARCHAR(30)	NOT NULL,
customer_postcode	VARCHAR(8)	NOT NULL,
customer_telephone	VARCHAR(20)	NOT NULL,
customer_email		VARCHAR(50)	UNIQUE,
customer_password	VARCHAR(32) NOT NULL,
customer_token	VARCHAR(16),
CONSTRAINT customer_password_chk CHECK(LENGTH(customer_password) >= 8)
);
ALTER TABLE customer AUTO_INCREMENT = 1011;

# *******************************************************************************************
# Create the FEEDBACK table to hold feedback information
#
# *******************************************************************************************

# ****** Creating FEEDBACK table ....

CREATE TABLE feedback
(
feedback_id		INT(4)		UNSIGNED 	PRIMARY KEY		AUTO_INCREMENT,
feedback_date DATE,
feedback_name	VARCHAR(30),
feedback_email	VARCHAR(30),
feedback_text	VARCHAR(200)	NOT NULL
);

# **************************************************************
# Create the STAFF table to hold staff login information
#
# staff_id used as foreign key in the BUILD table
# **************************************************************

# ****** Creating STAFF table ....

CREATE TABLE staff
(
staff_id		INT(4)		UNSIGNED	PRIMARY KEY		AUTO_INCREMENT,
staff_role		CHAR(1)		NOT NULL,	
staff_password		VARCHAR(32)	NOT NULL,
CONSTRAINT staff_role_chk CHECK(staff_role IN('A','W')),
CONSTRAINT staff_password_chk CHECK(LENGTH(staff_password) >= 8)
);
ALTER TABLE staff AUTO_INCREMENT = 1011;

# **********************************************************************************************************
# Create the PRODUCT table to hold generic product information
#
# product_id used as foreign key in the CPU, MOTHERBOARD, COOLER, RAM, GPU, STORAGE, CASE & OTHER tables
# product_id used as part of composite key in the BUILD_LINE table (with build_id from BUILD table)
# **********************************************************************************************************

# ****** Creating PRODUCT table ....

CREATE TABLE product
(
product_id		INT(4)			UNSIGNED	PRIMARY KEY		AUTO_INCREMENT,
product_type		VARCHAR(20)		NOT NULL,
product_supplier	VARCHAR(20)		NOT NULL,
product_description	VARCHAR(100)		NOT NULL,
product_wattage		INT(4)			UNSIGNED	NOT NULL,
product_price		DECIMAL(7,2)		UNSIGNED	NOT NULL,
product_stock		INT(3)			UNSIGNED	NOT NULL,
product_flag_general	CHAR(1)			NOT NULL,	
product_flag_gaming	CHAR(1)			NOT NULL,	
product_flag_workstation	CHAR(1)		NOT NULL,	
product_flag_budget	CHAR(1)			NOT NULL,
CONSTRAINT product_general_chk CHECK(product_flag_general IN('N','Y')),
CONSTRAINT product_gaming_chk CHECK(product_flag_gaming IN('N','Y')),
CONSTRAINT product_workstation_chk CHECK(product_flag_workstation IN('N','Y')),
CONSTRAINT product_budget_chk CHECK(product_flag_budget IN('B','M','H'))
);
ALTER TABLE product AUTO_INCREMENT = 1375;

# **********************************************************************************************************
# Create the CPU table to hold specific CPU product information
#
# **********************************************************************************************************

# ****** Creating CPU table ....

CREATE TABLE cpu
(
product_id		INT(4)		UNSIGNED	NOT NULL	PRIMARY KEY,
cpu_id			VARCHAR(30)	UNIQUE,
cpu_socket		VARCHAR(20)	NOT NULL,
cpu_category	VARCHAR(20)	NOT NULL,
cpu_cores		INT(2)		NOT NULL,
cpu_threads		INT(2)		NOT NULL,
cpu_base_clock		INT(4)	UNSIGNED	NOT NULL,
cpu_cache		INT(2)		UNSIGNED	NOT NULL,
cpu_graphics		VARCHAR(30)	NOT NULL,
cpu_cooler		CHAR(1)		NOT NULL,
CONSTRAINT cpu_cooler_chk CHECK(cpu_cooler IN('N','Y')),
CONSTRAINT cpu_product_id_fk FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

# **********************************************************************************************************
# Create the MOTHERBOARD table to hold specific motherboard product information
#
# **********************************************************************************************************

# ****** Creating MOTHERBOARD table ....

CREATE TABLE motherboard
(
product_id		INT(4)		UNSIGNED	NOT NULL	PRIMARY KEY,
motherboard_id		VARCHAR(30)	UNIQUE,
motherboard_cpu_socket	VARCHAR(20)	NOT NULL,
motherboard_chipset	VARCHAR(20)	NOT NULL,
motherboard_form_factor	VARCHAR(20)	NOT NULL,
motherboard_m2_slots	INT(2)		UNSIGNED	NOT NULL,
motherboard_sata_slots	INT(2)		UNSIGNED	NOT NULL,
motherboard_pcie_slots	INT(2)		UNSIGNED	NOT NULL,
motherboard_ram_slots	INT(2)		UNSIGNED	NOT NULL,
motherboard_ram_max	INT(3)		UNSIGNED	NOT NULL,
motherboard_ram_channel	INT(1)		UNSIGNED	NOT NULL,
motherboard_ram_ddr	INT(1)		UNSIGNED	NOT NULL,
motherboard_ram_speed	INT(4)	UNSIGNED	NOT NULL,
CONSTRAINT motherboard_product_id_fk FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

# **********************************************************************************************************
# Create the RAM table to hold specific RAM product information
#
# **********************************************************************************************************

# ****** Creating RAM table ....

CREATE TABLE ram
(
product_id		INT(4)		UNSIGNED	NOT NULL	PRIMARY KEY,
ram_id			VARCHAR(30)	UNIQUE,
ram_ddr			INT(1)	UNSIGNED	NOT NULL,
ram_capacity		INT(3)	UNSIGNED	NOT NULL,
ram_base_clock		INT(4)	UNSIGNED	NOT NULL,
ram_modules		INT(1)	UNSIGNED	NOT NULL,
CONSTRAINT ram_product_id_fk FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

# **********************************************************************************************************
# Create the STORAGE table to hold specific storage product information
#
# **********************************************************************************************************

# ****** Creating STORAGE table ....

CREATE TABLE storage
(
product_id		INT(4)			UNSIGNED	NOT NULL	PRIMARY KEY,
storage_id		VARCHAR(30)		UNIQUE,
storage_type		VARCHAR(20)		NOT NULL,
storage_capacity	INT(5)		UNSIGNED	NOT NULL,
storage_read_speed	INT(4)		UNSIGNED	NOT NULL,
storage_write_speed	INT(4)		UNSIGNED	NOT NULL,
CONSTRAINT storage_product_id_fk FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

# **********************************************************************************************************
# Create the GPU table to hold specific GPU product information
#
# **********************************************************************************************************

# ****** Creating GPU table ....

CREATE TABLE gpu
(
product_id		INT(4)			UNSIGNED	NOT NULL	PRIMARY KEY,
gpu_id			VARCHAR(30)		UNIQUE,
gpu_category	VARCHAR(20)	NOT NULL,	
gpu_base_clock		INT(4)		UNSIGNED	NOT NULL,
gpu_ram_capacity	INT(3)		UNSIGNED	NOT NULL,
gpu_ram_ddr		INT(1)		UNSIGNED	NOT NULL,
gpu_cores		INT(5)		UNSIGNED	NOT NULL,
CONSTRAINT gpu_category_chk CHECK(gpu_category IN('gaming','productivity')),
CONSTRAINT gpu_product_id_fk FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

# **********************************************************************************************************
# Create the CASE table to hold specific case product information
#
# **********************************************************************************************************

# ****** Creating PC_CASE table ....

CREATE TABLE pc_case
(
product_id		INT(4)			UNSIGNED	NOT NULL	PRIMARY KEY,
pc_case_id			VARCHAR(30)		UNIQUE,
pc_case_micro_atx	CHAR(1)		NOT NULL,	
pc_case_mini_itx	CHAR(1)		NOT NULL,	
pc_case_atx	CHAR(1)		NOT NULL,	
pc_case_ceb	CHAR(1)		NOT NULL,	
pc_case_e_atx	CHAR(1)		NOT NULL,	
pc_case_cooling_slots	INT(2)		UNSIGNED	NOT NULL,
pc_case_internal_bays	INT(2)		UNSIGNED	NOT NULL,
pc_case_external_bays	INT(2)		UNSIGNED	NOT NULL,
CONSTRAINT pc_case_micro_atx_chk CHECK(pc_case_micro_atx IN('Y','N')),
CONSTRAINT pc_case_mini_itx_chk CHECK(pc_case_mini_itx IN('Y','N')),
CONSTRAINT pc_case_atx_chk CHECK(pc_case_atx IN('Y','N')),
CONSTRAINT pc_case_ceb_chk CHECK(pc_case_ceb IN('Y','N')),
CONSTRAINT pc_case_e_atx_chk CHECK(pc_case_e_atx IN('Y','N')),
CONSTRAINT pc_case_product_id_fk FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

# **********************************************************************************************************
# Create the COOLER table to hold specific cooler product information
#
# **********************************************************************************************************

# ****** Creating COOLER table ....

CREATE TABLE cooler
(
product_id		INT(4)			UNSIGNED	NOT NULL	PRIMARY KEY,
cooler_id		VARCHAR(30)		UNIQUE,
cooler_fclga1200	CHAR(1)	NOT NULL,	
cooler_fclga1151	CHAR(1)	NOT NULL,	
cooler_fclga2066	CHAR(1)	NOT NULL,	
cooler_am4	CHAR(1)	NOT NULL,	
cooler_strx4	CHAR(1)	NOT NULL,	
cooler_fan_speed	INT(4)		UNSIGNED	NOT NULL,
cooler_material	VARCHAR(20)		NOT NULL,
CONSTRAINT cooler_fclga1200_chk CHECK(cooler_fclga1200 IN('Y','N')),
CONSTRAINT cooler_fclga1151_chk CHECK(cooler_fclga1151 IN('Y','N')),
CONSTRAINT cooler_fclga2066_chk CHECK(cooler_fclga2066 IN('Y','N')),
CONSTRAINT cooler_am4_chk CHECK(cooler_am4 IN('Y','N')),
CONSTRAINT cooler_strx4_chk CHECK(cooler_strx4 IN('Y','N')),
CONSTRAINT cooler_product_id_fk FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

# **********************************************************************************************************
# Create the POWER_SUPPLY table to hold specific power supply product information
#
# **********************************************************************************************************

# ****** Creating POWER_SUPPLY table ....

CREATE TABLE power_supply
(
product_id		INT(4)			UNSIGNED	NOT NULL	PRIMARY KEY,
power_supply_id		VARCHAR(30)		UNIQUE,
power_supply_wattage	INT(4)			UNSIGNED	NOT NULL,
power_supply_type	VARCHAR(20)		NOT NULL,
CONSTRAINT power_supply_product_id_fk FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

# **********************************************************************************************************
# Create the OTHER table to hold specific other associated product information
#
# **********************************************************************************************************

# ****** Creating OTHER table ....

CREATE TABLE other
(
product_id		INT(4)		UNSIGNED	NOT NULL	PRIMARY KEY,
other_id		VARCHAR(30)	UNIQUE,
other_type		VARCHAR(20)	NOT NULL,
other_attribute_1	VARCHAR(20),
other_attribute_2	VARCHAR(20),
other_attribute_3	VARCHAR(20),
CONSTRAINT other_product_id_fk FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

# **********************************************************************************************************
# Create the BUILD table to hold customer build information
#
# **********************************************************************************************************

# ****** Creating BUILD table ....

CREATE TABLE build
(
build_id		INT(4)		UNSIGNED	NOT NULL	PRIMARY KEY	AUTO_INCREMENT,
customer_id		INT(4)		UNSIGNED	NOT NULL,
staff_id		INT(4)		UNSIGNED	NOT NULL,
build_date		DATE,
build_dispatch_date	DATE,
build_status		CHAR(1)		NOT NULL,	
build_flag_usage	CHAR(1)		NOT NULL,	
build_flag_budget	CHAR(1)		NOT NULL,
build_delivery_charge	DECIMAL(7,2)	UNSIGNED	NOT NULL,	
build_total		DECIMAL(7,2)	UNSIGNED	NOT NULL,
build_vat_rate		DECIMAL(4,2)	UNSIGNED	NOT NULL,
CONSTRAINT build_status_chk CHECK(build_status IN('C','D','O','P')),
CONSTRAINT build_flag_usage_chk CHECK(build_flag_usage IN('C','G','W')),
CONSTRAINT build_flag_budget_chk CHECK(build_flag_budget IN('B','M','H')),
CONSTRAINT build_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE,
CONSTRAINT build_staff_id_fk FOREIGN KEY (staff_id) REFERENCES staff(staff_id) ON DELETE CASCADE,
CONSTRAINT build_dispatch_date_chk CHECK(build_dispatch_date >= build_date)
);
ALTER TABLE build AUTO_INCREMENT = 1011;

# ******************************************************************************************
# Create the BUILD_LINE table to hold build line information from customer builds
#
# Composite key made up of build_id from BUILD table and product_id from PRODUCT table
# ******************************************************************************************

# ****** Creating BUILD_LINE table ....

CREATE TABLE build_line
(
build_line_id	INT(4)		UNSIGNED	NOT NULL	PRIMARY KEY	AUTO_INCREMENT,
build_id		INT(4)		REFERENCES build(build_id) ON DELETE CASCADE,
product_id		INT(4)		REFERENCES product(product_id) ON DELETE CASCADE,
build_line_product_type	VARCHAR(20)	NOT NULL,
build_line_product_description	VARCHAR(100)	NOT NULL,
build_line_price	DECIMAL(7,2)	NOT NULL,
build_line_quantity	INT(3)		NOT NULL
);

COMMIT;


