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

create sequence bill_bid_seq start with 100;
create sequence order_orid_seq start with 100;
create sequence sandwich_sid_seq start with 100;
create sequence sandwich_type_stid_seq start with 100;
create sequence ingredient_iid_seq start with 100;
create sequence shop_shid_seq start with 100;
create sequence user_uid_seq start with 100;
create sequence course_cid_seq start with 100;
create sequence session_seid_seq start with 100;

CREATE TABLE BILL
(BID      INT  primary key default nextval('bill_bid_seq'),
 BDATE    DATE NOT null
);

CREATE UNIQUE INDEX MONTHLY_UNIQUE_BILL ON BILL(DATE_TRUNC('month', BDATE::timestamp));

CREATE TABLE ORDERS
(ORID         INT  primary key default nextval('order_orid_seq'),
 ODATE    DATE NOT null,
 O_BID INT,
 CONSTRAINT FK_BILL FOREIGN KEY (O_BID) REFERENCES BILL
);

CREATE TABLE COURSE
(CID     INT  primary key default nextval('course_cid_seq'),
 CNAME     varchar(40) NOT NULL,
 CONSTRAINT UNIQUE_COURSE_NAME UNIQUE (CNAME)
);

CREATE TABLE USERS
(UID       INT  primary key default nextval('user_uid_seq'),
 UNAME VARCHAR(40) not null,
 FNAME  VARCHAR(40) not null,
 LNAME varchar(40) not null,
 MAIL varchar(40) not null,
 PWD varchar(80) not null,
 ACC_NO_EXP  VARCHAR(1) not null,
 ACC_NO_LOC   VARCHAR(1) not null,
 CRED_NO_EXP  VARCHAR(1) not null,
 ENABL  VARCHAR(1) not null,
 U_CID int,
 CONSTRAINT UNIQUE_USERNAME UNIQUE (UNAME),
 CONSTRAINT UNIQUE_MAIL UNIQUE (MAIL),
 CONSTRAINT FK_COURSE FOREIGN KEY (U_CID) REFERENCES COURSE
);

CREATE TABLE SHOP
(SHID      INT  primary key default nextval('shop_shid_seq'),
 SHNAME     varchar(40) NOT NULL,
 CONSTRAINT UNIQUE_SHOP_NAME UNIQUE (SHNAME)
);

CREATE TABLE SANDWICHTYPE
(STID      INT  primary key default nextval('sandwich_type_stid_seq'),
 STNAME     varchar(40) NOT NULL,
 ST_SHID int,
 CONSTRAINT FK_SHOP FOREIGN KEY (ST_SHID) REFERENCES SHOP
);

CREATE TABLE SANDWICH
(SID       INT  primary key default nextval('sandwich_sid_seq'),
 CLUB   VARCHAR(1) not null,
 BUTTER  VARCHAR(1) not null,
 OPTIONAL VARCHAR(80),
 S_UID int,
 S_STID int,
 S_ORID int,
 CONSTRAINT FK_USER FOREIGN KEY (S_UID) REFERENCES USERS,
 CONSTRAINT FK_SANDWICHTYPE FOREIGN KEY (S_STID) REFERENCES SANDWICHTYPE,
 CONSTRAINT FK_ORDER FOREIGN KEY (S_ORID) REFERENCES ORDERS
);

CREATE TABLE INGREDIENT
(IID   INT  primary key default nextval('ingredient_iid_seq'),
 INAME VARCHAR(40) not null,
 CONSTRAINT UNIQUE_INGREDIENT_NAME UNIQUE (INAME)
);

create table SANDWICHINGREDIENTS
(
    SI_STID int,
    SI_IID int,
    CONSTRAINT FK_SANDWICHTYPE FOREIGN KEY (SI_STID) REFERENCES SANDWICHTYPE,
    CONSTRAINT FK_INGREDIENT FOREIGN KEY (SI_IID) REFERENCES INGREDIENT
);

CREATE TABLE SESSIONS
(SEID       INT  primary key default nextval('session_seid_seq'),
 SENAME varchar(40) not null,
 SESTDATE DATE not null,
 SEEDATE DATE not null,
 SE_UID int,
 SE_CID int,
 SE_ORID int,
 CONSTRAINT FK_INSTRUCTOR FOREIGN KEY (SE_UID) REFERENCES USERS,
 CONSTRAINT FK_COURSE FOREIGN KEY (SE_CID) REFERENCES COURSE,
 CONSTRAINT FK_DAILY_ORDER FOREIGN KEY (SE_ORID) REFERENCES ORDERS
);

CREATE OR REPLACE FUNCTION UPDATE_BILL_DATE_ON_INSERT() RETURNS TRIGGER AS $$
BEGIN
    UPDATE BILL set BDATE = cast(date_trunc('month', BDATE) AS DATE) WHERE BID=NEW.BID;
    RETURN NEW;
END; $$
LANGUAGE 'plpgsql';

insert into ingredient (iid, iname) values (1, 'Bread'), (2, 'Beef'), (3, 'Mayonnaise'), (4, 'Eggs'), (5, 'Onions'), (6, 'Tomatoes'),
                                           (7, 'Pickles'), (8, 'Ham'), (9, 'Salad'), (10, 'Salami'),
                                           (11, 'Roasted beef'), (12, 'Martino sauce'), (13, 'Eggplant'),
                                           (14, 'Cheese'), (15, 'Carrots'), (16, 'Bacon'), (17, 'Honey');
insert into shop (shid, shname) values (1, 'Vleugels'), (2, 'Pinky''s');
insert into sandwichtype (stid, stname, st_shid) values (1, 'Meat ball', 1), (2, 'Ham', 1), (3, 'Roasted beef', 1),
                                                        (4, 'Martino', 1), (5, 'Salami', 1), (6, 'Special', 2), (7, 'Club', 2);
insert into course (cid, cname) values (1, 'Java course programming'), (2, 'Dotnet course initiation'), (3, 'Angular advanced course'), (4, 'Web programming basics');
insert into users (uid, uname, fname, lname, mail, pwd, u_cid, ACC_NO_EXP, ACC_NO_LOC, CRED_NO_EXP, ENABL) values (1, 'green-goblin', 'Henry', 'Stockau', 'Henry.St@gmail.com', 'HnrSt456', 1, 'y', 'y', 'y', 'y'),
                                                          (2, 'magika_madoka', 'Charlotte', 'Morau', 'Charlotte.Morau@skynet.be', 'ChrMo456', 1, 'y', 'y', 'y', 'y'),
                                                          (3, 'SuperEdgeLord45', 'Alexandre', 'VandeMittenaere', 'luthor@hotmail.com.be', 'AlxMi456', 1, 'y', 'y', 'y', 'y'),
                                                          (4, 'Ziepler', 'Laurent', 'Zachow', 'ZcLaurent@yahoo.com', 'LauZa456', 1, 'y', 'y', 'y', 'y'),
                                                          (5, 'Artemis', 'Chloe', 'Wilowski', 'MagicaGirl@icloud.com', 'ChlWi456', 1, 'y', 'y', 'y', 'y'),
                                                          (6, 'aura', 'Maria', 'La Fiora', 'Overlord@protonmail.it', 'MarFi456', 1, 'y', 'y', 'y', 'y'),
                                                          (7, 'planet15', 'Selene', 'Kepner', 'nolife.game@gmx.com', 'SelKe456', 1, 'y', 'y', 'y', 'y');
insert into users (uid, uname, fname, lname, mail, pwd, ACC_NO_EXP, ACC_NO_LOC, CRED_NO_EXP, ENABL) values (8, 'theCorruptor', 'Archibald', 'VandeWeyer', 'Astrologer35@outlook.com', 'ArcWe456', 'y', 'y', 'y', 'y');
insert into users (uid, uname, fname, lname, mail, pwd, ACC_NO_EXP, ACC_NO_LOC, CRED_NO_EXP, ENABL) values (9, 'plague_doctor', 'Gaetan', 'Esther', 'Gaetan.Esther@yandex.com', 'GaeEs456', 'y', 'y', 'y', 'y');
insert into bill (bid, bdate) values (1, '2022-12-01'), (2, '2023-01-01');
insert into orders (orid, odate, o_bid) values (1, '2022-12-01', 1), (2, '2022-12-02', 1), (3, '2022-12-03', 1), (4, '2022-12-11', 1),
                                               (5, '2022-12-20', 1), (6, '2022-12-27', 1), (7, '2023-01-07', 2), (8, '2023-01-13', 2);
insert into sandwich (sid, club, butter, optional, s_uid, s_stid, s_orid) values (1, 'n', 'n', null, 1, 3, 1), (2, 'y', 'n', null, 2, 5, 1), (3, 'y', 'y', 'no peanuts', 3, 1, 1), (4, 'n', 'y', 'no mayonnaise', 5, 2, 1),
                                                                                 (5, 'n', 'n', null, 7, 1, 1), (6, 'n', 'y', null, 3, 7, 2), (7, 'n', 'n', null, 4, 5, 2), (8, 'n', 'n', null, 1, 2, 2),
                                                                                 (9, 'y', 'n', 'ketchup sauce', 2, 3, 2), (10, 'y', 'n', null, 2, 4, 3), (11, 'n', 'n', null, 5, 2, 3), (12, 'y', 'n', null, 6, 1, 3),
                                                                                 (13, 'n', 'y', null, 1, 4, 4), (14, 'n', 'n', null, 5, 3, 4), (15, 'n', 'n', 'extra salad', 3, 5, 4), (16, 'n', 'n', null, 6, 6, 5),
                                                                                 (17, 'n', 'n', null, 7, 6, 6), (18, 'y', 'y', null, 2, 4, 7), (19, 'n', 'y', null, 4, 1, 8), (20, 'n', 'n', null, 5, 1, 8);
insert into sessions (seid, sename, sestdate, seedate, se_uid, se_cid, se_orid) values (1, 'Spring initiation', '2022-12-01', '2022-12-13', 8, 1, 1),
                                                                                       (2, 'Design patterns', '2022-12-14', '2022-12-18', 8, 1, 2),
                                                                                       (3, 'Data structure and algorithms', '2022-12-19', '2022-12-30', 8, 1, 3),
                                                                                       (4, 'Unit testing', '2022-12-31', '2023-01-03', 8, 1, 4),
                                                                                       (5, 'Database management', '2023-01-04', '2023-01-13', 9, 1, 5),
                                                                                       (6, 'web api''s', '2023-01-14', '2023-01-19', 9, 1, 6);
insert into sandwichingredients (si_stid, si_iid) values (1, 1), (1, 2), (1, 3),
                                                         (2, 1), (2, 2), (2, 4), (2, 5), (2, 6), (2, 7), (2, 12),
                                                         (3, 1), (3, 9), (3, 10), (3, 3),
                                                         (4, 1), (4, 10), (4, 3),
                                                         (5, 1), (5, 11), (5, 3),
                                                         (6, 1), (6, 8), (6, 14), (6, 9), (6, 6),
                                                         (7, 1), (7, 16), (7, 3), (7, 17);

commit;

/*

following trigger maps every possible keys between two tables with a many to many relationship
================================================================================================

CREATE TRIGGER UPDATE_BILL_DATE AFTER INSERT ON BILL FOR EACH ROW EXECUTE FUNCTION UPDATE_BILL_DATE_ON_INSERT();

CREATE OR REPLACE FUNCTION connect_sandwichtypes_to_ingredients()
    RETURNS trigger AS
$$
BEGIN
    INSERT INTO sandwichingredients (si_iid, si_stid)
    SELECT iid, NEW.stid FROM ingredient;
    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER UPDATE_SANDWICH_INGREDIENT AFTER INSERT ON SANDWICHTYPE FOR EACH ROW EXECUTE FUNCTION connect_sandwichtypes_to_ingredients();

 */
