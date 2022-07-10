# *********************
# Grant user privileges
# *********************

USE mybuild;

# customer privileges

GRANT SELECT ON customer TO mybuild_customer;
GRANT SELECT ON build_line TO mybuild_customer;
GRANT SELECT ON build TO mybuild_customer;
GRANT SELECT ON product TO mybuild_customer;
GRANT SELECT ON cpu TO mybuild_customer;
GRANT SELECT ON motherboard TO mybuild_customer;
GRANT SELECT ON ram TO mybuild_customer;
GRANT SELECT ON storage TO mybuild_customer;
GRANT SELECT ON gpu TO mybuild_customer;
GRANT SELECT ON pc_case TO mybuild_customer;
GRANT SELECT ON cooler TO mybuild_customer;
GRANT SELECT ON power_supply TO mybuild_customer;
GRANT SELECT ON other TO mybuild_customer;

GRANT INSERT ON customer TO mybuild_customer;
GRANT INSERT ON feedback TO mybuild_customer;
GRANT INSERT ON build_line TO mybuild_customer;
GRANT INSERT ON build TO mybuild_customer;

GRANT UPDATE ON customer TO mybuild_customer;
GRANT UPDATE ON build_line TO mybuild_customer;
GRANT UPDATE ON build TO mybuild_customer;
GRANT UPDATE ON product TO mybuild_customer;

# administrator privileges - access to everything (at least while testing)

GRANT SELECT ON customer TO mybuild_administrator;
GRANT SELECT ON feedback TO mybuild_administrator;
GRANT SELECT ON staff TO mybuild_administrator;
GRANT SELECT ON build_line TO mybuild_administrator;
GRANT SELECT ON build TO mybuild_administrator;
GRANT SELECT ON product TO mybuild_administrator;
GRANT SELECT ON cpu TO mybuild_administrator;
GRANT SELECT ON motherboard TO mybuild_administrator;
GRANT SELECT ON ram TO mybuild_administrator;
GRANT SELECT ON storage TO mybuild_administrator;
GRANT SELECT ON gpu TO mybuild_administrator;
GRANT SELECT ON pc_case TO mybuild_administrator;
GRANT SELECT ON cooler TO mybuild_administrator;
GRANT SELECT ON power_supply TO mybuild_administrator;
GRANT SELECT ON other TO mybuild_administrator;

GRANT INSERT ON customer TO mybuild_administrator;
GRANT INSERT ON feedback TO mybuild_administrator;
GRANT INSERT ON staff TO mybuild_administrator;
GRANT INSERT ON build_line TO mybuild_administrator;
GRANT INSERT ON build TO mybuild_administrator;
GRANT INSERT ON product TO mybuild_administrator;
GRANT INSERT ON cpu TO mybuild_administrator;
GRANT INSERT ON motherboard TO mybuild_administrator;
GRANT INSERT ON ram TO mybuild_administrator;
GRANT INSERT ON storage TO mybuild_administrator;
GRANT INSERT ON gpu TO mybuild_administrator;
GRANT INSERT ON pc_case TO mybuild_administrator;
GRANT INSERT ON cooler TO mybuild_administrator;
GRANT INSERT ON power_supply TO mybuild_administrator;
GRANT INSERT ON other TO mybuild_administrator;

GRANT UPDATE ON customer TO mybuild_administrator;
GRANT UPDATE ON feedback TO mybuild_administrator;
GRANT UPDATE ON staff TO mybuild_administrator;
GRANT UPDATE ON build_line TO mybuild_administrator;
GRANT UPDATE ON build TO mybuild_administrator;
GRANT UPDATE ON product TO mybuild_administrator;
GRANT UPDATE ON cpu TO mybuild_administrator;
GRANT UPDATE ON motherboard TO mybuild_administrator;
GRANT UPDATE ON ram TO mybuild_administrator;
GRANT UPDATE ON storage TO mybuild_administrator;
GRANT UPDATE ON gpu TO mybuild_administrator;
GRANT UPDATE ON pc_case TO mybuild_administrator;
GRANT UPDATE ON cooler TO mybuild_administrator;
GRANT UPDATE ON power_supply TO mybuild_administrator;
GRANT UPDATE ON other TO mybuild_administrator;

GRANT DELETE ON customer TO mybuild_administrator;
GRANT DELETE ON feedback TO mybuild_administrator;
GRANT DELETE ON staff TO mybuild_administrator;
GRANT DELETE ON build_line TO mybuild_administrator;
GRANT DELETE ON build TO mybuild_administrator;
GRANT DELETE ON product TO mybuild_administrator;
GRANT DELETE ON cpu TO mybuild_administrator;
GRANT DELETE ON motherboard TO mybuild_administrator;
GRANT DELETE ON ram TO mybuild_administrator;
GRANT DELETE ON storage TO mybuild_administrator;
GRANT DELETE ON gpu TO mybuild_administrator;
GRANT DELETE ON pc_case TO mybuild_administrator;
GRANT DELETE ON cooler TO mybuild_administrator;
GRANT DELETE ON power_supply TO mybuild_administrator;
GRANT DELETE ON other TO mybuild_administrator;

# warehouse privileges

GRANT SELECT ON staff TO mybuild_warehouse;
GRANT SELECT ON build_line TO mybuild_warehouse;
GRANT SELECT ON build TO mybuild_warehouse;
GRANT SELECT ON product TO mybuild_warehouse;
GRANT SELECT ON cpu TO mybuild_warehouse;
GRANT SELECT ON motherboard TO mybuild_warehouse;
GRANT SELECT ON ram TO mybuild_warehouse;
GRANT SELECT ON storage TO mybuild_warehouse;
GRANT SELECT ON gpu TO mybuild_warehouse;
GRANT SELECT ON pc_case TO mybuild_warehouse;
GRANT SELECT ON cooler TO mybuild_warehouse;
GRANT SELECT ON power_supply TO mybuild_warehouse;
GRANT SELECT ON other TO mybuild_warehouse;

GRANT UPDATE ON build TO mybuild_warehouse;

COMMIT;



