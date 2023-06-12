CREATE TABLE studenttab_seq (
	next_val BIGINT
);

CREATE TABLE location (
	id INT NOT NULL,
	code VARCHAR(20),
	name VARCHAR(20),
	type VARCHAR(10),
	PRIMARY KEY (id)
);

CREATE TABLE studenttab (
	id BIGINT NOT NULL,
	scourse VARCHAR(255),
	sfee DOUBLE,
	sname VARCHAR(255),
	PRIMARY KEY (id)
);

use projectdb;

select * from location;

select type,count(type) from location group by type;