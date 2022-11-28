DROP TABLE if exists BILL CASCADE;
DROP table if exists ORDERS CASCADE;
DROP table if exists SANDWICH CASCADE;
DROP TABLE if exists SANDWICHTYPE CASCADE;
DROP TABLE if exists SANDWICHINGREDIENTS CASCADE;
DROP TABLE if exists INGREDIENT CASCADE;
DROP TABLE if exists SHOP CASCADE;
DROP TABLE if exists USERS CASCADE;
DROP TABLE if exists COURSE CASCADE;
DROP TABLE if exists SESSIONS CASCADE;

drop sequence if exists bill_bid_seq;
drop sequence if exists order_orid_seq;
drop sequence if exists sandwich_sid_seq;
drop sequence if exists sandwich_type_stid_seq;
drop sequence if exists ingredient_iid_seq;
drop sequence if exists shop_shid_seq;
drop sequence if exists user_uid_seq;
drop sequence if exists course_cid_seq;
drop sequence if exists session_seid_seq;

create sequence bill_bid_seq start with 1;
create sequence order_orid_seq start with 1;
create sequence sandwich_sid_seq start with 1;
create sequence sandwich_type_stid_seq start with 1;
create sequence ingredient_iid_seq start with 1;
create sequence shop_shid_seq start with 1;
create sequence user_uid_seq start with 1;
create sequence course_cid_seq start with 1;
create sequence session_seid_seq start with 1;

CREATE TABLE BILL
(BID      INT  primary key default nextval('bill_bid_seq'),
 BDATE    DATE NOT null
);

CREATE TABLE ORDERS
(ORID         INT  primary key default nextval('order_orid_seq'),
 ODATE    DATE NOT null,
 O_BID INT,
 CONSTRAINT FK_BILL FOREIGN KEY (O_BID) REFERENCES BILL
);

CREATE TABLE COURSE
(CID     INT  primary key default nextval('course_cid_seq'),
 CNAME     char(40) NOT NULL
);

CREATE TABLE USERS
(UID       INT  primary key default nextval('user_uid_seq'),
 FNAME  CHAR(40) not null,
 LNAME char(40) not null,
 PWD char(40) not null,
 U_CID int,
 CONSTRAINT FK_COURSE FOREIGN KEY (U_CID) REFERENCES COURSE
);

CREATE TABLE SANDWICH
(SID       INT  primary key default nextval('sandwich_sid_seq'),
 CLUB   CHAR(1) not null,
 BUTTER  CHAR(1) not null,
 OPTIONAL CHAR(80),
 S_UID int,
 S_ORID int,
 CONSTRAINT FK_USER FOREIGN KEY (S_UID) REFERENCES USERS,
 CONSTRAINT FK_ORDER FOREIGN KEY (S_ORID) REFERENCES ORDERS
);

CREATE TABLE SHOP
(SHID      INT  primary key default nextval('shop_shid_seq'),
 SHNAME     char(40) NOT NULL
);

CREATE TABLE SANDWICHTYPE
(STID      INT  primary key default nextval('sandwich_type_stid_seq'),
 STNAME     char(40) NOT NULL,
 ST_SHID int,
 CONSTRAINT FK_SHOP FOREIGN KEY (ST_SHID) REFERENCES SHOP
);

CREATE TABLE INGREDIENT
(IID   INT  primary key default nextval('ingredient_iid_seq'),
 INAME CHAR(40) not null
);

create table SANDWICHINGREDIENTS
(
    SI_IID int,
    SI_SHID int,
    constraint FK_INGREDIENT foreign key (SI_IID) references INGREDIENT,
    CONSTRAINT FK_SANDWICHTYPE FOREIGN KEY (SI_SHID) REFERENCES SHOP
);

CREATE TABLE SESSIONS
(SEID       INT  primary key default nextval('session_seid_seq'),
 SENAME char(40) not null,
 SESTDATE DATE not null,
 SEEDATE DATE not null,
 SE_UID int,
 SE_ORID int,
 CONSTRAINT FK_INSTRUCTOR FOREIGN KEY (SE_UID) REFERENCES USERS,
 CONSTRAINT FK_DAILY_ORDER FOREIGN KEY (SE_ORID) REFERENCES ORDERS
);

commit;
