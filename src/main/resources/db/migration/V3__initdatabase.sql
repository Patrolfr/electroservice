create sequence hibernate_sequence start 1 increment 1;
create table categories (id int8 not null, name varchar(255), primary key (id));
create table comments (equipment_id int8 not null, hash int4 not null, value varchar(255));
create table equipments (id int8 not null, name varchar(255), service_code varchar(255), service_status varchar(255), category_id int8, primary key (id));
create table equipments_parameters (equipment_id int8 not null, parameters_id int8 not null);
create table parameters (id int8 not null, key varchar(255), value varchar(255), primary key (id));