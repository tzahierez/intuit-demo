CREATE TABLE IF NOT EXISTS crm_case (
     internal_id VARCHAR PRIMARY KEY NOT NULL,
     id VARCHAR NOT NULL,
     system_url VARCHAR NOT NULL,
     customer_id VARCHAR NOT NULL,
     provider VARCHAR NOT NULL,
     created_error_code VARCHAR NOT NULL,
     status VARCHAR NOT NULL,
     ticket_creation_date VARCHAR NOT NULL, -- TODO TIMESTAMP
     last_modified_date VARCHAR NOT NULL, -- TODO TIMESTAMP
     product_name VARCHAR NOT NULL
);
-- TODO - add missing indexes by eventual lookups

CREATE TABLE IF NOT EXISTS external_systems (
    id VARCHAR PRIMARY KEY NOT NULL,
    external_system_url VARCHAR NOT NULL
);



