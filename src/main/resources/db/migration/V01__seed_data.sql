
create table CUSTOMER(
  CUSTOMER_ID int not null,
  FIRST_NAME varchar(100) not null,
  LAST_NAME varchar(100) not null,
  EMAIL varchar(100) not null,
  LOCATION varchar(100) not null,
  PRIMARY KEY ( CUSTOMER_ID )
);

insert into CUSTOMER(CUSTOMER_ID, FIRST_NAME, LAST_NAME, EMAIL, LOCATION) values (10001, 'Tony', 'Stark', 'tony.stark@gmail.com','Australia');
insert into CUSTOMER(CUSTOMER_ID, FIRST_NAME, LAST_NAME, EMAIL, LOCATION) values (10002, 'Bruce', 'Banner', 'Bruce.Banner@gmail.com','US');
insert into CUSTOMER(CUSTOMER_ID, FIRST_NAME, LAST_NAME, EMAIL, LOCATION) values (10003, 'Steve', 'Rogers', 'Steve.Rogers@gmail.com','Australia');
insert into CUSTOMER(CUSTOMER_ID, FIRST_NAME, LAST_NAME, EMAIL, LOCATION) values (10004, 'Wanda', 'Maximoff', 'Wanda.Maximoff@gmail.com','US');
insert into CUSTOMER(CUSTOMER_ID, FIRST_NAME, LAST_NAME, EMAIL, LOCATION) values (10005, 'Natasha', 'Romanoff', 'Natasha.Romanoff@gmail.com','Canada');


create table PRODUCT(
  PRODUCT_CODE varchar(20) not null,
  COST int not null,
  STATUS varchar(100) not null,
  PRIMARY KEY ( PRODUCT_CODE )
);

insert into PRODUCT (PRODUCT_CODE, COST, STATUS) values ('PRODUCT_001', 50,'Active');
insert into PRODUCT (PRODUCT_CODE, COST, STATUS) values ('PRODUCT_002', 100,'Inactive');
insert into PRODUCT (PRODUCT_CODE, COST, STATUS) values ('PRODUCT_003', 200,'Active');
insert into PRODUCT (PRODUCT_CODE, COST, STATUS) values ('PRODUCT_004', 10,'Inactive');
insert into PRODUCT (PRODUCT_CODE, COST, STATUS) values ('PRODUCT_005', 50,'Active');

create table TRANSACTION(
  TRANSACTION_ID int not null,
  TRANSACTION_TIME varchar(50),
  CUSTOMER_ID int,
  QUANTITY int not null,
  COST int not null,
  PRODUCT_CODE varchar(50),
  PRIMARY KEY ( TRANSACTION_ID )
);

CREATE SEQUENCE transaction_seq
  START WITH 1
  INCREMENT BY 1;
