--create schema
create schema schema3;

--create sysuser table
--password length shoud be larger than 60
create table schema3.sysuser(
id int(10) not null AUTO_INCREMENT,
username varchar(10) not null,
password varchar(100) not null,
primary key (id)
);

--create sysrole table
create table schema3.sysauthority(
id int(10) not null AUTO_INCREMENT,
name varchar(20) not null,
description varchar(20),
primary key (id)
);

--create bridge table
create table schema3.r_user_authority(
user_id int(10) not null,
authority_id int(10) not null
);

--init dml
insert into schema3.sysauthority(name,description) values("authority_a","for role a test");
insert into schema3.sysauthority(name,description) values("authority_b","for role b test");

--usera password usera123
--userb password userb123
--userab password userab123
--userc password userc123
insert into schema3.sysuser(username,password) values("usera","$2a$10$DbCNZwHe/OwynRDMsNU7qO9QkpL0c5ZxWypoBf7QAOBXFCjkC6LZC");
insert into schema3.sysuser(username,password) values("userb","$2a$10$ab0wFJHZx.x4dp..ZaeC8ePt2cSmYVXbNBFDVZnTD133xx.Qsb05G");
insert into schema3.sysuser(username,password) values("userab","$2a$10$DdKeZYGBi1msdaXKDjkreOO2eIczINK8.QkQubpSStukFZRHumvTK");
insert into schema3.sysuser(username,password) values("userc","$2a$10$40JDO3oL9uSi68r4GgRSae7/f2Arq2KT3zQ2ZzyNhA6bUKrodOyDS");

insert into schema3.r_user_authority(user_id,authority_id) values((select id from schema3.sysuser where username="usera"),(select id from schema3.sysauthority where name="authority_a"));
insert into schema3.r_user_authority(user_id,authority_id) values((select id from schema3.sysuser where username="userb"),(select id from schema3.sysauthority where name="authority_b"));
insert into schema3.r_user_authority(user_id,authority_id) values((select id from schema3.sysuser where username="userab"),(select id from schema3.sysauthority where name="authority_a"));
insert into schema3.r_user_authority(user_id,authority_id) values((select id from schema3.sysuser where username="userab"),(select id from schema3.sysauthority where name="authority_b"));




